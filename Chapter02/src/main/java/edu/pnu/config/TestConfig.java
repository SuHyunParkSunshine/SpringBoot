package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	public TestConfig() {
		System.out.println("=".repeat(50));
		System.out.println("TestConfig가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}
	@Bean //자바 객체, 인스턴스 -> @Configuration랑 함께 사용해야한다!!
	public TestBean testBean() {
		// spring container(spring이 관리)에 메모리를 자동으로 올려준다.
		return new TestBean(); 
	}
}

class TestBean {
	public TestBean() {
		System.out.println("=".repeat(50));
		System.out.println("TestBean가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}
}