package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface {

	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "suhyun";
	private String password = "1234";

	private Connection con;

	public MemberDaoH2Impl() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	@Override
	//public List<MemberVO> getMembers() {
	public Map<String, Object> getMembers() {

		ArrayList<MemberVO> list = null;
		Map<String, Object> maps = new HashMap<>();
		
		maps.put("method", "get");
		
		Statement stmt = null;
		try {
			list = new ArrayList<>();
			stmt = con.createStatement();
			String sql = "select * from Member";
			ResultSet rs = stmt.executeQuery(sql);
			maps.put("sqlstring", sql);
			
			while(rs.next()) {
				MemberVO m = MemberVO.builder()
								.id(rs.getInt("id"))
								.pass(rs.getString("pass"))
								.name(rs.getString("name"))
								.regidate(rs.getDate("regidate"))
								.build();
				list.add(m);
			}
			maps.put("result", list);
			maps.put("success", true);
			//return list;
		} catch (Exception e) {			
			e.printStackTrace();
			maps.put("success", false);
		} 
	      return maps;	
	}

	@Override
	//public MemberVO getMember(Integer id) {
	public Map<String, Object> getMember(Integer id) {
		Map<String, Object> maps = new HashMap<>();		
		maps.put("method", "get");		
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			String sql = String.format("select * from Member where id=%d", id);
			ResultSet rs = stmt.executeQuery(sql);
			maps.put("sqlstring", sql);
		
			rs.next();
			MemberVO m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			maps.put("result", m);
			maps.put("success", true);
			rs.close();
			stmt.close();
			//return m;
			
		} catch (Exception e) {			
			e.printStackTrace();
			maps.put("success", false);
		} finally {
	         try {
		            if (stmt != null)
		            	stmt.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		return maps;
	}

	@Override
	//public MemberVO addMember(MemberVO member) {
	public Map<String, Object> addMember(MemberVO member) {
		Map<String, Object> maps = new HashMap<>();
		
		maps.put("method", "post");
		String query = "insert into Member (pass, name) values(?, ?)";
		maps.put("sqlstring", query);
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			res = psmt.executeUpdate();
			
			maps.put("result", member);
			maps.put("success", true);			
						
		} catch (Exception e) {		
			e.printStackTrace();
			maps.put("success", false);
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

	@Override
	//public MemberVO updateMember(MemberVO member) {
	public Map<String, Object> updateMember(MemberVO member) {

		Map<String, Object> maps = new HashMap<>();
		
		maps.put("method", "put");
		String query = "update Member set pass=?, name=? where id =?";
		maps.put("sqlstring", query);
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.setInt(3, member.getId());
			res = psmt.executeUpdate();
			
			maps.put("result", member);
			maps.put("success", true);
			
		} catch (Exception e) {			
			e.printStackTrace();
			maps.put("success", false);
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

	@Override
	//public int deleteMember(Integer id) {
	public Map<String, Object> deleteMember(Integer id) {
		Map<String, Object> maps = new HashMap<>();
		
		maps.put("method", "delete");
		String query = "delete from Member where id=?";
		maps.put("sqlstring", query);
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			res = psmt.executeUpdate();
			
			maps.put("result", res);
			maps.put("success", true);
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
	         try {
		            if (psmt != null)
		            	psmt.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		            maps.put("success", false);
		         }
		      }
		return maps;
	}
}
