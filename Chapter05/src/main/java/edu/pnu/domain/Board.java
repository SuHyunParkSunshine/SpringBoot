package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@NoArgsConstructor
@AllArgsConstructor

@Entity //database에 연결된는 클래스라는 의미
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //(GeneratedValue(IDENTITY) --> id를 auto increment로 설정해주는 옵션, 자동으로 id를 증가 시켜주는 것)
	private Long seq;
	private String title;
	@Column(length = 32) //varchar를 dafault(255) -> 32로 변경
	private String writer;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
}
