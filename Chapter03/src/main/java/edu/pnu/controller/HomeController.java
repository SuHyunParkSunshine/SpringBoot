package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController {
	public HomeController() {
		System.out.println("HomeController가 생성되었습니다.");
		
		//info level이상만 찍겠다(info + warn + error) => "logging.level.edu.pnu=trace"(application.properties)
		log.info("HomeController가 생성되었습니다.");
		log.error("HomeController ERROR가 생성되었습니다.");
		log.warn("HomeController WARN가 생성되었습니다.");
		log.info("HomeController INFO가 생성되었습니다."); //default value
		log.debug("HomeController DEBUG가 생성되었습니다.");
		log.trace("HomeController TRACE가 생성되었습니다."); //level이 제일 상세한 것
	}

	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
}
