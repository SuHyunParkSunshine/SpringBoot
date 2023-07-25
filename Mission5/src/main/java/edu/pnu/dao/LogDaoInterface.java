package edu.pnu.dao;

public interface LogDaoInterface {
	
	void addLog(String method, String sqlstring, boolean success);
}
