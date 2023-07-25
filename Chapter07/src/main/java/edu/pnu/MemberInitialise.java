package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

//IOC 컨테이너에 등록을 시겨야지 실행가눙
@Component
public class MemberInitialise implements ApplicationRunner {

	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("qwer"))
				.role("ROLE_MEMBER") //'ROLE_' 이 boot에서 사용하는 규칙
				.enabled(true) // 이 아이디가 사용한 것이냐를 묻는 것
				.build());
		
		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("qwer"))
				.enabled(true)
				.role("ROLE_MANAGER")
				.build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("qwer"))
				.enabled(true)
				.role("ROLE_ADMIN")
				.build());
	}

}
