package edu.pnu.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService {

	//database에 조회
	@Autowired
	MemberRepository memRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Member> option = memRepo.findById(username);
		if(!option.isPresent()) {
			throw new UsernameNotFoundException("사용자가 없습니다.");
			//return null; // null이 반환되면 security에서 자동적으로 처리해줌
		}
		
		Member member = option.get();
		System.out.println(member);

		return new User(member.getUsername(), member.getPassword(), member.getAuthorities());
	}

}
