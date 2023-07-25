package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistance.BoardRepository;

@Component
public class BoardDataInitialiser implements ApplicationRunner {

	@Autowired
	BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Random rd = new Random();
		
		   for(int i = 1; i <= 100; i++) {
			   boardRepo.save(Board.builder()
					   .title("title" + i)
					   .writer("writer" + i)
					   .content("content" + i)
					   .createDate(new Date())
					   .cnt(rd.nextLong(0, 101))
					   .build());
		   }
		
	}

}
