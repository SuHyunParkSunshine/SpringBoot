package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

//@Slf4j
@RestController
public class MemberController {

	private MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() {
		log.info("getMembers()");
		return memberService.getMembers();
	}

	//localhost:8080/member/1
	@GetMapping("/member/{id}") //restAPI를 가져오는 전형적인 방식(restAPI방식) -> /id 로 바로 적어주면 됨
	public MemberVO getMember(@PathVariable Integer id) {
		log.info("getMember()");
		return memberService.getMember(id);
	}
	
	//localhost:8080/member?id=1
//	@GetMapping("/member") // '?id= ' 쿼리스트링에다가 파라미터를 입력하는 방식
//	public MemberVO getMember(Integer id) {
//		log.info("getMember()");
//		return memberService.getMember(id);
//	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO member) {
		log.info("addMember()");
		return memberService.addMember(member);
	}

	@PutMapping("/member")
	public MemberVO updateMember(MemberVO member) {
		log.info("updateMember()");
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO deleteMember(@PathVariable Integer id) {
		log.info("deleteMember()");
		return memberService.deleteMember(id);
	}
}
