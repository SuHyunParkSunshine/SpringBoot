package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	
	//인스턴스 만들어서 IOC 컨테이너에 등록함.
	@Bean
	public BCryptPasswordEncoder encoder() { //암호화하는 라이브러리
		return new BCryptPasswordEncoder();  
	}

	@Bean //Configuration annotation 안에 bean 有
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.authorizeHttpRequests(auth-> {
			//case 1
//			auth.requestMatchers("/").permitAll();
//			auth.requestMatchers("/member/**").authenticated();
			
			//case 2
//			auth.requestMatchers("/").permitAll()
//				.requestMatchers("/member/**").authenticated()
//				.requestMatchers("/manager/**").hasRole("MANAGER")
//				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
//				.requestMatchers("/admin/**").hasRole("ADMIN");
			
			//case 3 => 교수님이 좋아하는 방식			
			auth.requestMatchers("/member/**").authenticated()				
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll();
		});
		
		security.csrf(csrf->csrf.disable()); //해킹 방지?? script를 url로 호출 못하게 하는 것??
				
		security.formLogin(form->{
			//form.defaultSuccessUrl("/"); //'form->form.defaultSuccessUrl("/")' formLogin을 사용하겠다는 의미
			form.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true);
		}); 
		
		security.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied")); //accessDenied가 뜨면 요 페이지로 가라
		security.logout(logt->{
			logt.invalidateHttpSession(true) // 초기화 하겠다.
				.deleteCookies("JSESSIONID") //브라우저에 잇는 session id를 지우겠다.
				.logoutSuccessUrl("/login"); //로그인 페이지로 가겠댜
		});
		
		security.userDetailsService(boardUserDetailsService);
		
		return security.build();
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("member")
			.password("{noop}qwer") //security는 무조건 암호화를 하는게 default but {noop}을 써서 암호화하는 작업을 하지 않겠다는 의미
			.roles("MEMBER");
		auth.inMemoryAuthentication()
			.withUser("manager")
			.password("{noop}qwer")
			.roles("MANAGER");
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}qwer")
			.roles("ADMIN");
	}
}
