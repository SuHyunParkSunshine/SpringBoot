package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;
//@Controller 사용하고 싶다면 return 타입에 @ResponseBody(넘겨받은 데이터를 그대로 제이슨에 넘겨줌)를 붙여주면 사용할 수 있다. 

@RestController //-> 객체는 무조건 제이슨 || int, String은 그냥 원래 형태로 넘어감
public class BoardController {

	public BoardController() {
		System.out.println("=".repeat(50));
		System.out.println("BoardController가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}
	
	@GetMapping("/hello")
	public String hello1(String name) {
		return "Get Hello : " + name;
	}
	
	@PostMapping("/hello")
	public String hello2(String name) {
		return "Post Hello : " + name;
	}
	@PutMapping("/hello")
	public String hello3(String name) {
		return "Put Hello : " + name;
	}
	@DeleteMapping("/hello")
	public String hello4(String name) {
		return "Delete Hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목...");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다...........");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;		
	}
	
	@GetMapping("/getBoard1")
	public BoardVO getBoard1() {
		BoardVO board = new BoardVO(
				1,
				"테스트 제목...",
				"테스터",
				"테스트 내용입니다...........",
				new Date(),
				0);		
		return board;		
	}
	
	@GetMapping("/getBoard2")
	public BoardVO getBoard2() {
		return BoardVO.builder() //.builder 는 static 매소드라는 것을 보자마자 알수 잇어야 한다. '.~'로 호출 해서 사용할 수 있음으로 static이라는 것을 알아두길
				.seq(1)
				.title("테스트 제목")
				.writer("테스터")
				.content("테스트 내용입니다.....")
				.createDate(new Date())
				.cnt(0)
				.build();				
	}
	
	@GetMapping("/getBoardList1")
	public List<BoardVO> getBoardList1() {
		List<BoardVO> bvo = new ArrayList<>();
		
		for (int i = 1; i < 11; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(1);
			board.setTitle("테스트 제목" + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용입니다" + i);
			board.setCreateDate(new Date());
			board.setCnt(0);
			bvo.add(board);
		}
		return bvo;
	}
	
	@GetMapping("/getBoardList2")
	public List<BoardVO> getBoardList2() {
		List<BoardVO> bvo = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			bvo.add(BoardVO.builder()
					.seq(1)
					.title("테스트 제목" + i)
					.writer("테스터")
					.content("테스트 내용입니다....." + i)
					.createDate(new Date())
					.cnt(0)
					.build());						
		}
		return bvo;
	}
	
	@GetMapping("/board")
	public @ResponseBody BoardVO board (@RequestBody BoardVO b) {
		b.setCreateDate(new Date());
		b.setCnt(100);
		System.out.println("Board: " + b);
		return b;
	}
}
