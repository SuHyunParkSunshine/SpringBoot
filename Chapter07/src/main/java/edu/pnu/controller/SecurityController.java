package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class SecurityController {

	@GetMapping("/")
	public String index() {
		System.out.println("index 요청입니다.");
		return "index";		
	}
	
	@GetMapping("/member")
	public void forMember() {
		System.out.println("Member 요청입니다."); //member.html을 찾음
		//return "member"; //restController용
	}
	
	@GetMapping("/manager")
	public void forManager() {
		System.out.println("Manager 요청입니다.");
		//return "manager"; //restController용
	}
	
	@GetMapping("/admin")
	public void forAdmin() {
		System.out.println("Admin 요청입니다.");
		//return "admin"; //restController용
	}
}