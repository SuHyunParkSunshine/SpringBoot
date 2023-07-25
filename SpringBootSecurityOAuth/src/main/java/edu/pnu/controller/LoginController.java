package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	private MemberService memberService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@GetMapping("/login")
	public void login() {
	}

	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}

	@GetMapping("/accessDenied")
	public void accessDenied() {
	}

	// 로그인 세션 정보 확인용 URL
	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		
		if(user == null) {
			return "user is Null";
		}
		return user.toString();
	}

	@GetMapping("/join")
	public void join() {
	}

	@PostMapping("/join")
	public String joinProc(Member member) {

		if (member.getPassword() != null && !member.getPassword().isEmpty() && member.getUsername() != null
				&& !member.getUsername().isEmpty()) {

			member.setEnabled(true);
			member.setRole("ROLE_MEMBER");
			member.setPassword(encoder.encode(member.getPassword()));

			memberService.save(member);
			return "redirect:login";
//		return "welcome";
		}
		return "redirect:join";

	}
}
