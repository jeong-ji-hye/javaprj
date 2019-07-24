package com.newlecture.web.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Notice;

public class OracleMemberDao implements MemberDao{

	@Override
	public Member get(String id) {
		Member member = null;
		
		String sql = "select * from member where id=?";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");// Connection
			PreparedStatement st = con.prepareStatement(sql);// Statement
			st.setString(1, id);
			ResultSet rs = st.executeQuery();// ResultSet
			
			if (rs.next()) {
				member = new Member(rs.getString("id"), rs.getString("pwd"),
									rs.getInt("gender"), rs.getInt("age"),
									rs.getString("birthday"), rs.getString("phone"),
									rs.getString("regdate"), rs.getString("boss_id"),
									rs.getString("type"));
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // DriverManager
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

}
