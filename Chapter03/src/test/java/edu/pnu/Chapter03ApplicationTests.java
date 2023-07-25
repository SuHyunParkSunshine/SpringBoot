package edu.pnu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
@WebMvcTest
class Chapter03ApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	//localhost:8080/hello?name=둘리 했을때 제대로 뜨는지 테스트 하는 
	void contextLoads() throws Exception {
		mockMvc.perform(get("/hello").param("name", "둘리")) //get method 호출 parametre를 둘리로 줘가지구 뜨냐?
		.andExpect(status().isOk())
		//.andExpect(content().string("Hello : 둘리"))
		.andDo(print());
	}

}
