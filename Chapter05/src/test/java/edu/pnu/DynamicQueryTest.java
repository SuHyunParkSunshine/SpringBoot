package edu.pnu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistance.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;

	private void test(String searchCondition, String searchKeyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		if (searchCondition.equals("TITLE")) {
			// select b from Board b where b.title like '%'||:searchKeyword||'%'
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
			// select b from Board b where b.content like '%'||:searchKeyword||'%'
		} else if (searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}

		Iterable<Board> list = boardRepo.findAll(builder);
		for (Board b : list) {
			System.out.println("--->" + b);
		}
	}

	private void test1(Map<String, String> map) {

		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		Set<String> keys = map.keySet();
		
		for (String key : keys) {
			if (key.equals("TITLE")) {				
				builder.and(qboard.title.like("%" + map.get("TITLE") + "%"));				
			} else if (key.equals("CONTENT")) {
				builder.or(qboard.content.like("%" + map.get("CONTENT") + "%"));
			}			
		}
		Iterable<Board> list = boardRepo.findAll(builder);
		for (Board b : list) {
			System.out.println("--->" + b);
		}
	}

	@Test
	public void testDynamicQuery() {
		// test("TITLE", "title1");
		// test("CONTENT", "content2");

		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "content2");
		test1(map);
	}

}
