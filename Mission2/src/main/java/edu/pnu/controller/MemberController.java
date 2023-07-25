package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j //simple log for java
@RestController
public class MemberController {
	
	private MemberService memberService;

	public MemberController() {
		System.out.println("MemberController가 생성되었습니다.");
		log.info("MemberController가 생성되었습니다.");
		
		memberService = new MemberService();
	}
	//member data를 가져와서 돌려주는 코드
	@GetMapping("/member/{id}") //id 는 Path Value
	public Member getMember(@PathVariable Long id) {
		return memberService.getMember(id);
	}
	
	@GetMapping("/member") //모든 멤버 데이터를 리턴
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
	
	@PostMapping("/member") //새로운 멤버 입력
	public int insertMember(Member member) {
		return memberService.insertMember(member);
	}
	
	@PutMapping("/member") //멤버 이름, 암호 수정 만약에 insert랑 같이 post로 경로를 지정하면 상충되서 페이징 에러가 뜬다
	public int updateMember(Member member) {
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}") //멤버 삭제
	public int deleteMember(@PathVariable Long id) {
		return memberService.deleteMember(id);
	}
}

