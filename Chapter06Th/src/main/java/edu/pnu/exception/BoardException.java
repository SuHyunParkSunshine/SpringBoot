package edu.pnu.exception;

public class BoardException extends RuntimeException{
	//RuntimeException 실행 시점에 발생하는 예외를 처리해주는 것
	private static final long serialVersionUID = 1L;
	
	public BoardException(String message) {
		super(message);
	}
}
