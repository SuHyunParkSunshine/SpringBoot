package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistance.BoardRepository;

@RestController
public class BoardController {

	@Autowired
	BoardRepository boardRepo;
	
	@GetMapping("/getBoardList")
	public List<Board> getBoardList() {
		return boardRepo.findAll();
		
	}
	
	//Read
	@GetMapping("/board")
	public Board getBoard(Long id) {
		return boardRepo.findById(id).get();
	}
	
	//Create
	@PostMapping("/board")
	public Board insertBoard(Board board) {
		if(board.getCreateDate() == null)
			board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	
	@PostMapping("/boardjson")
	public Board insertJsonBoard(@RequestBody Board board) {
		if(board.getCreateDate() == null)
			board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	
	//Update
	@PutMapping("/board")
	public Board updateBoard(Board board) {
		return boardRepo.save(board);
	}
	
	//Delete
	@DeleteMapping("/board")
	public String deleteBoard(Long id) {
		boardRepo.deleteById(id);
		return String.format("seq가 %d인 데이터가 삭제되었습니다.", id);
	}
	
	@GetMapping("/findbytitlecontaining")
	public List<Board> findByTitleContaining(String kw) {
		return boardRepo.findByTitleContaining(kw);
	}
	
	//paging 사용
//	@GetMapping("/findbytitlecontaining")
//	public List<Board> findByTitleContainingPaging(String kw) {
//		Pageable page = PageRequest.of(0,  5);
//		return boardRepo.findByTitleContaining(kw, page);
//	}
	
	@GetMapping("/findbytitlelike")
	public List<Board> findByTitleLike(String kw) {
		return boardRepo.findByTitleLike("%" + kw + "%");
	}
	
	@GetMapping("/titlecontainingandcntgreater")
	public List<Board> findByTitleContainingAndCntGreaterThan(String kw, Long num) {
		return boardRepo.findByTitleContainingAndCntGreaterThan(kw, num);
	}
	
	@GetMapping("/querymethod3")
	public List<Board> findByCntBetweenOrderBySeqAsc(Long num1, Long num2) {
		return boardRepo.findByCntBetweenOrderBySeqAsc(num1, num2);
	}
	
	@GetMapping("/querymethod4")
	public List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String kw1, String kw2) {
		return boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc(kw1, kw2);
	}
}
