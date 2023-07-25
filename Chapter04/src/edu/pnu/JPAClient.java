package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

		EntityManager em = emf.createEntityManager();

//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		Board b = new Board();
//		b.setTitle("Title");
//		b.setWriter("Writer");
//		b.setContent("Content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L);
//		
//		em.persist(b);
//
//		tx.commit();

		insertData(em);
		updateData(em);
		deleteData(em);

		em.close();
		emf.close();
	}

	static void insertData(EntityManager em) { // main이 static이기 때문에 static 붙여줘야 한다.
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			for (int i = 0; i < 10; i++) {
				Board b = new Board();
				b.setTitle("Title" + i);
				b.setWriter("Writer" + i);
				b.setContent("Content" + i);
				b.setCreateDate(new Date());
				b.setCnt(0L + (10 * i));

				em.persist(b);
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	private static void updateData(EntityManager em) {

		Board b = em.find(Board.class, 5L);

		b.setTitle("나는 새로운 타이블이다.");
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(b);		

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

	}
	
	private static void deleteData(EntityManager em) {

		Board b = em.find(Board.class, 9L);

		b.setTitle("나는 새로운 타이블이다.");
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			em.remove(b);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

	}
}
