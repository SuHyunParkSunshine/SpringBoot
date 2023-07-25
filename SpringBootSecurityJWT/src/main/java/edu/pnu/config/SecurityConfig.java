package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.config.auth.JWTAuthorizationFilter;
import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration authConfig;
	
	@Autowired
	private MemberRepository memberRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public PasswordEncoder bPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//		// 위의 BCryptPasswordEncoder와 같은 코드임.
//		// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화 (JS에서 호출 가능)
		http.cors(cors -> cors.disable()); // CORS 보호 비활성화 (React에서 호출 가능):RestAPI로 호출할 때

		http.authorizeHttpRequests(security -> {
			security.requestMatchers("/member/**").authenticated().requestMatchers("/manager/**")
					.hasAnyRole("MANAGER", "ADMIN").requestMatchers("/admin/**").hasRole("ADMIN").anyRequest()
					.permitAll();
		});

		http.formLogin(frmLogin -> frmLogin.disable()); //NEW!! Form을 이용한 로그인을 사용하지 않겠다는 설정
		//NEW!! 시큐리티 세션을 만들지 않겠다고 설정
		//STATELESS => 지속적으로 유지하지 않겟다. response가 된 이후에는 삭제 된다. 다음 로긴할때 JWTToken을 사용해서 기억???
		http.sessionManagement(ssmg -> ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		//아이디 비밀번호 틀렸을 때, 각각 따로 안내 하고 싶은 경우 memberRepository도 parmetre로 받고 JWTAuthenticationFilter에서 따로 코드 작성해주기!!
		http.addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager(), memberRepository));
		
		http.addFilter(new JWTAuthorizationFilter(authConfig.getAuthenticationManager(), memberRepository));
		
		return http.build();

	}

}
