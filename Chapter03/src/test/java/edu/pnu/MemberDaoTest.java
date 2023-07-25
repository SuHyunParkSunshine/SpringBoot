package edu.pnu;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberDaoTest {

	@Test //test code는 순서를 보장하지 않는다. 현재 실행한 내에서 실행된 코드랑 집에서 한 코드랑 순서가 다를것임. 시스템 환경에 따라 달라진다.
	@Order(1)
	public void insertMemberTest() {
		MemberDao memberDao = new MemberDao();
		for (int i = 1; i <=10; i++) {
		//case1. Builder를 이용한 방법
		memberDao.insertMember(
				
				Member.builder()
					.name("name" + i)
					.age(20 + i)
					.nickname("nickname" + i)
					.build()
				);
//		//case2. 기본 생성자를 이용한 방법
//		Member m = new Member();
//		m.setName("name" + i);
//		m.setAge(20 + i);
//		m.setName("nickname" + i);
//		memberDao.insertMember(m);
//			
//		//case3. 파라미터가 필요한 생성자를 이용한 방법
//		memberDao.insertMember(new Member(
//				-1L, //serialVersionUID같이 default code가 있어야지 빨간줄 안 뜸!!
//				"name" + i,
//				20 + i,
//				"nickname" + i					
//				));
		}
	}
	@Test
	@Order(2)
	public void selectAllMemberTest() {
		MemberDao memberDao = new MemberDao();
		
		List<Member> list = memberDao.getMembers();
		for(Member m : list) {
			System.out.println(m);
		}
	}
}
