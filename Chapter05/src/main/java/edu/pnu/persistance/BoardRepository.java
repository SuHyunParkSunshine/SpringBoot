package edu.pnu.persistance;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByTitleContaining(String kw);
	
	//자동으로 페이징을 해서 출력해준다
	//Page<Board> 
	List<Board> findByTitleContaining(String kw, Pageable paging);
	
	List<Board> findByTitleLike(String kw);
	
	List<Board> findByTitleContainingAndCntGreaterThan(String kw, Long num);
	
	List<Board> findByCntBetweenOrderBySeqAsc(Long num1, Long num2);
	
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String kw1, String kw2);
	
	@Query("Select b from Board b where b.title like %?1% order by b.seq desc")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Board> queryAnnotationTest2(String searchKeyword);
	
	@Query("select b.seq, b.title, b.writer, b.createDate from Board b "
			+ "where b.title like %:searchKeyword% order by b.seq desc")
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	@Query(value="select seq, title, writer, create_date"
			+ " from board where title like '%'||:sk||'%' order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest4(@Param("sk") String searchKeyword);
	
//	@Query("select b from Board b")
	@Query("select b from Board b order by b.seq asc")
	List<Board> queryAnnotationTest5(Pageable paging);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
