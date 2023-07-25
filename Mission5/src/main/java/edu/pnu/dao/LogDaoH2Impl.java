package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl implements LogDaoInterface {

	@Autowired
	private DataSource dataSource;

	@Override
	public void addLog(String method, String sqlstring, boolean success) {
		String query = "insert into dblog (method, sqlstring, success) values (?, ?, ?)";

		PreparedStatement psmt = null;
		
		if (sqlstring == null) sqlstring = "";

		try (Connection con = dataSource.getConnection()){
			psmt = con.prepareStatement(query);
			psmt.setString(1, method);
			psmt.setString(2, sqlstring);
			psmt.setBoolean(3, success);
			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				if (psmt != null)
//					psmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

}
