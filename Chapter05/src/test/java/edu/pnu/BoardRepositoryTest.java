package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistance.BoardRepository;

@SpringBootTest
class BoardRepositoryTest {
   @Autowired
   private BoardRepository boardRepo;

//   @Test
//   @Order(1)
   void testInsertBoard() {
	   for(int i = 1; i <= 100; i++) {
		   boardRepo.save(Board.builder()
				   .title("title" + i)
				   .writer("writer" + i)
				   .content("content" + i)
				   .createDate(new Date())
				   .cnt(0L)
				   .build());
	   }
		   
   }
   
//   void contextLoads() {
//      Board board = new Board();
//      
//      board.setTitle("첫 번째 게시글");
//      board.setWriter("테스터");
//      board.setContent ("잘 등록되나요?");
//      board.setCreateDate(new Date());
//      board.setCnt(0L);
//      boardRepo.save(board);
//   }
   
   //@Test
   public void testGetBoard() {
	   Board board = boardRepo.findById(1L).get();
	   //상기의 한 줄 코드는 하기의 두줄 코드와 동일
//	   Optional<Board> opt = boardRepo.findById(1L);
//	   Board board = opt.get();
	   System.out.println(board);
   }
   
   //@Test
   public void testUpdateBoard() {
	   {
	   Board board = boardRepo.findById(1L).get();
	   System.out.println("수정 전");
	   System.out.println(board);
	   
	   board.setTitle("제목 수정");
	   boardRepo.save(board);
	   }
	   {
		   Board board = boardRepo.findById(1L).get();
		   System.out.println("수정 후");
		   System.out.println(board);
	   }
   }
   //@Test
   public void testDeleteBoard() {
	   boardRepo.deleteById(2L);;
   }
   
   //@Test
   public void testfindAll() {
	   List<Board> list =  boardRepo.findAll();
	   System.out.println("모든 데이터를 출력합니다.");
	   for(Board b : list) {		   
		   System.out.println(b);
	   }
   }
   
   //@Test
   public void testQueryAnnotationTest1() {
	   //select b from Board b where b.title like '%title1%' order by b.seq desc
	   List<Board> list = boardRepo.queryAnnotationTest1("title1");
	   for(Board b : list) {
		   System.out.println(b);
	   }
   }
   
   //@Test
   public void testQueryAnnotationTest2() {
	   //select b from Board b where b.title like %:searchKeyword% order by b.seq desc
	   List<Board> list = boardRepo.queryAnnotationTest2("title1");
	   for(Board b : list) {
		   System.out.println(b);
	   }
   }
   
   //@Test
   public void testQueryAnnotationTest3() {
	   //select b from Board b where b.title like %:searchKeyword% order by b.seq desc
	   List<Object[]> list = boardRepo.queryAnnotationTest3("title1");
	   for(Object[] b : list) {
		   System.out.println(Arrays.toString(b));
	   }
   }

   //@Test
   public void testQueryAnnotationTest4() {
	   //select b from Board b where b.title like %:searchKeyword% order by b.seq desc
	   List<Object[]> list = boardRepo.queryAnnotationTest4("title1");
	   for(Object[] b : list) {
		   System.out.println(Arrays.toString(b));
	   }
   }
   @Test
   public void testQueryAnnotationTest5() {
	   //query문에서 sorting이 되어 있으면 pageable 만들면서 sorting해도 query문의 order by가 우선시 된다 
	   Pageable paging = PageRequest.of(5, 5, Sort.Direction.DESC, "seq");
	   List<Board> list = boardRepo.queryAnnotationTest5(paging);
	   for(Board b : list) {
		   System.out.println("--->" + b);
	   }
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}