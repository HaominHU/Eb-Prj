package com.register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.JdbcUtils;

public class RegisterDao implements RegisterService {

	private JdbcUtils jdbcUtils;
	public RegisterDao() {
		
		jdbcUtils = new JdbcUtils();
	}

	
	@Override
	public boolean registerUser(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into cUserInfo(username,pswd,name,email,phone,street,city,state,zip) values(?,?,?,?,?,?,?,?,?)";			
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
					
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}

}