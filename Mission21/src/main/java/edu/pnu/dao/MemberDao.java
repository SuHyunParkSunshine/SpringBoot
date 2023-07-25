package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Member;

@Repository
public class MemberDao {
	@Autowired
	private DataSource dataSource;

	public Member getMember(Long id) {
		try {
			Statement stmt = dataSource.getConnection().createStatement();

			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));

			rs.next();
			Member m = Member.builder().id(rs.getLong("id")).pass(rs.getString("pass")).name(rs.getString("name"))
					.regidate(rs.getDate("regidate")).build();
			
			rs.close();
			stmt.close();
			return m;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Member> getMembers() {
		
		ArrayList<Member> list = null;
		
		try {
			list = new ArrayList<>();
			Statement stmt = dataSource.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from Member");
			
			while(rs.next()) {
				Member m = Member.builder().id(rs.getLong("id")).pass(rs.getString("pass")).name(rs.getString("name"))
						.regidate(rs.getDate("regidate")).build();
				
				list.add(m);
			}
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insertMember(Member member) {
		
		String query = "insert into Member (pass, name) values (?,?)";
		
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			
			psmt = dataSource.getConnection().prepareStatement(query);		
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			res = psmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int updateMember(Member member) {
		String query = "update Member set pass=?, name=? where id=?";
		
		PreparedStatement psmt = null;
		int res = 0;
		
		try {			
			psmt = dataSource.getConnection().prepareStatement(query);		
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.setLong(3, member.getId());
			res = psmt.executeUpdate();	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int deleteMember(Long id) {
		String query = "delete from Member where id = ?";
		PreparedStatement psmt = null;
		int res = 0;
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setLong(1, id);
			res = psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
