package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public List<MemberVO> getMembers() {

		ArrayList<MemberVO> list = null;
		
		Statement stmt = null;
		try {
			list = new ArrayList<>();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Member");
			
			while(rs.next()) {
				MemberVO m = MemberVO.builder()
								.id(rs.getInt("id"))
								.pass(rs.getString("pass"))
								.name(rs.getString("name"))
								.regidate(rs.getDate("regidate"))
								.build();
				list.add(m);
			}
			return list;
		} catch (Exception e) {			
			e.printStackTrace();
		} 
	      return null;	
	}

	@Override
	public MemberVO getMember(Integer id) {

		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));
		
			rs.next();
			MemberVO m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			rs.close();
			stmt.close();
			return m;
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
	         try {
		            if (stmt != null)
		            	stmt.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {

		String query = "insert into Member (pass, name) values(?, ?)";
		
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
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
		return member;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {

		String query = "update Member set pass=?, name=? where id =?";
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.setInt(3, member.getId());
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
		return member;
	}

	@Override
	public int deleteMember(Integer id) {
		String query = "delete from Member where id=?";
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
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
		return res;
	}
}
