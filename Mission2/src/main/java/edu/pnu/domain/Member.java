package edu.pnu.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
//data를 가져와서 객체로 만들어서 사용하기 위한 클라서
public class Member {
	private Long id;
	private String pass;
	private String name;
	private Date regidate;
}
