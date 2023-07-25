package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //simple log for java
@RestController //객체로 만들어져서 container에 올라가게 됨
@RequiredArgsConstructor //case 4
public class MemberController {
	
	//case 1: 필드에 설정하는 방법 => 잘 안 씀
	//@Autowired
	//case 4: final을 할당해주고 설정하는 방법 => lombok annotation을 이용한 방법, 최근 많이 쓰는 방법
	private final MemberService memberService;

	//case 2: 생성자에서 설정하는 방법
	//@Autowired
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	//case 3: Setter를 이용한 방법
//	@Autowired
//	private void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	
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

