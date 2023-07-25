package edu.pnu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/home")
	public String home() {
		// [/WEB-INF/board/home.jsp] 를 호출하라고 하는 의미
		return "home";
	}

	@GetMapping("/home1")
	public void home1() {
		// [/WEB-INF/board/home1.jsp] 를 호출하라고 하는 의미
		// return 값이 없을 시 url 주소로 jsp파일을 찾아서 값을 도출함
	}
	
	@GetMapping("/model")
	public String model(Model model) {
				
		model.addAttribute("data", "홍길동");
		
		return "model";
	}
}
