package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class LogDao {

	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "suhyun";
	private String password = "1234";

	private Connection con;

	public LogDao() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> addLog(Map<String, Object> maps) {
		
		String query = "insert into dblog (method, sqlstring, success) values (?, ?, ?)";
		
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setObject(1, maps.get("method"));
			psmt.setObject(2, maps.get("sqlstring"));
			psmt.setObject(3, maps.get("success"));
			res = psmt.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
	         try {
		            if (psmt != null)
		            	psmt.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }				
		return maps;
	}
}
