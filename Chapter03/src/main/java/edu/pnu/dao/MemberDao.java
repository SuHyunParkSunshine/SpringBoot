package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {

	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/test";
	private String username = "suhyun";
	private String password = "1234";

	private Connection con;

	// Database Connection 설정(con)
	public MemberDao() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int insertMember(Member m) {
		try {
			Statement stmt = con.createStatement();
			String sql = String.format("insert into Member(name, age, nickname) values('%s','%d','%s')", m.getName(),
					m.getAge(), m.getNickname());

			return stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Member> getMembers() {
	      List<Member> list = new ArrayList<>();
	      try {
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(String.format("select * from Member"));

	         while (rs.next()) {
	            Member m = Member.builder()
	                  .id(rs.getLong("id"))
	                  .name(rs.getString("name"))
	                  .age(rs.getInt("age"))
	                  .nickname(rs.getString("nickname"))
	                  .build();
	            list.add(m);
	         }
	         return list;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return null;
	   }

	public Member getMembers(Long id) {
		String sql = "select * from Memeber where id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs != null) {
				return Member.builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("nickname"))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
