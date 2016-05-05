package com.sysvin.practice.dbAccess.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sysvin.practice.dbAccess.model.User;

import core.dao.DbConnection;

public class UserDbService {
	protected static Logger log = LogManager.getLogger(UserDbService.class);

	public UserDbService() {
		
	}
	
	public User hasUser(String email, String password) {
		Connection conn = DbConnection.getConnection();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM \"User\" WHERE \"email\" = ? and \"password\" = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getString("ID"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getBoolean("sex"));
				return user;
			}
			
		} catch (SQLException e) {
			log.error("", e);
		} finally {
			DbConnection.closeConnection(rs, ps, conn);
		}
		return null;		
	}

}
