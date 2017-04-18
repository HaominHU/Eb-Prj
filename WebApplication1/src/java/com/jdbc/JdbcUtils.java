package com.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;

//import com.mysql.jdbc.Driver;

public class JdbcUtils {

	
	private final String USERNAME = "root";
	
	private final String PASSWORD = "root";
	
	private final String DRIVER = "com.mysql.jdbc.Driver";
	
	private final String URL = "jdbc:mysql://localhost:8889/usedBookStore?zeroDateTimeBehavior=convertToNull";

	
	private Connection connection;
	
	private PreparedStatement pstmt;
	
	private ResultSet resultSet;
	
	
	private Statement stmt;

	public JdbcUtils() {
		
		try {
			Class.forName(DRIVER);
			System.out.println("success");
		} catch (ClassNotFoundException e) {
			
			System.out.println("failure");
		}

	}

	
	public Connection getConnection() {

		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (Exception e) {
			
			System.out.println("Connection exception !");
		}

		return connection;

	}
	
	
	
	
	public boolean deleteByBatch(String[] sql) throws SQLException{
		boolean flag = false;
		stmt = connection.createStatement();
		if (sql!=null) { 
			
			for(int i = 0 ; i<sql.length ; i++){
				stmt.addBatch(sql[i]);
			}
			
			int[] count = stmt.executeBatch();
			if (count!=null) {
				flag = true;
			}
		}	
		return flag;		
	}

	
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;
		int index = 1; 
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i)); 
			}
		}

		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;

	}
        
//        public boolean updateByPreparedStatement1(String sql, List<Object> params)
//			throws SQLException {
//		boolean flag = false;
//		int result = -1;               
//		pstmt = connection.prepareStatement(sql);
//                pstmt.setObject(1, params.get(1));
//                pstmt.setObject(2, params.get(0));                
//                result = pstmt.executeUpdate();
//                flag = result > 0 ? true : false;
//		return flag;
//
//	}

	
	public  Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery(); 

		ResultSetMetaData metaData = pstmt.getMetaData(); 
		int cols_len = metaData.getColumnCount(); 

		while (resultSet.next()) {
			for (int i = 0; i < cols_len; i++) {
				String col_name = metaData.getColumnName(i + 1); 
				Object col_value = resultSet.getObject(col_name);
				if (col_value == null) {
					col_value = "";
				}
				map.put(col_name, col_value);
			}

		}

		return map;
	}
        
        public List<Map<String, Object>> findMoreResult2(String sql) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		pstmt = connection.prepareStatement(sql);
		
		resultSet = pstmt.executeQuery(sql); 
		ResultSetMetaData metaData = resultSet.getMetaData(); 

		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			int cols_len = metaData.getColumnCount(); 
			for (int i = 0; i < cols_len; i++) {
				String col_name = metaData.getColumnName(i + 1); 
                                
				Object col_value = resultSet.getObject(col_name); 
				if (col_value == null) {
					col_value = "";
				}
				map.put(col_name, col_value);
			}
			list.add(map);
		}
		return list;
	}

	
	public List<Map<String, Object>> findMoreResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		pstmt = connection.prepareStatement(sql);
		int index = 1; 
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery(); 
		ResultSetMetaData metaData = resultSet.getMetaData(); 

		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			int cols_len = metaData.getColumnCount(); 
			for (int i = 0; i < cols_len; i++) {
				String col_name = metaData.getColumnName(i + 1); 
														
				Object col_value = resultSet.getObject(col_name); 
				if (col_value == null) {
					col_value = "";
				}

				map.put(col_name, col_value);
			}
			list.add(map);
		}
		return list;

	}

	
//	public <T> T findSimpleRefResult(String sql, List<Object> params,
//			Class<T> cls) throws Exception {
//		T resultObject = null;
//		int index = 1; 
//		pstmt = connection.prepareStatement(sql);
//		if (params != null && !params.isEmpty()) {
//			for (int i = 0; i < params.size(); i++) {
//				pstmt.setObject(index++, params.get(i)); 
//			}
//		}
//		resultSet = pstmt.executeQuery(); 
//
//		ResultSetMetaData metaData = resultSet.getMetaData(); 
//		int cols_len = metaData.getColumnCount(); 
//		while (resultSet.next()) {
//			
//			resultObject = cls.newInstance(); 
//			for (int i = 0; i < cols_len; i++) {
//				String col_name = metaData.getColumnName(i + 1); 
//				Object col_value = resultSet.getObject(col_name); 
//				if (col_value == null) {
//					col_value = "";
//				}
//				Field field = cls.getDeclaredField(col_name);
//				field.setAccessible(true);
//				field.set(resultObject, col_value);
//			}
//
//		}
//
//		return resultObject;
//	}

	
//	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
//			Class<T> cls) throws Exception {
//		List<T> list = new ArrayList<T>();
//		int index = 1; 
//		pstmt = connection.prepareStatement(sql);
//		if (params != null && !params.isEmpty()) {
//			for (int i = 0; i < params.size(); i++) {
//				pstmt.setObject(index++, params.get(i));
//			}
//		}
//		resultSet = pstmt.executeQuery(); 
//
//		ResultSetMetaData metaData = resultSet.getMetaData(); 
//		int cols_len = metaData.getColumnCount(); 
//		while (resultSet.next()) {
//			
//			T resultObject = cls.newInstance();
//			for (int i = 0; i < cols_len; i++) {
//				String col_name = metaData.getColumnName(i + 1); 
//				Object col_value = resultSet.getObject(col_name); 
//				if (col_value == null) {
//					col_value = "";
//				}
//				Field field = cls.getDeclaredField(col_name);
//				field.setAccessible(true); 
//				field.set(resultObject, col_value);
//			}
//			list.add(resultObject);
//
//		}
//
//		return list;
//	}
	
       
        
	
	public void releaseConn(){
		if (resultSet!=null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		if(stmt!=null){
			
			try {
				stmt.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		if (connection!=null) {
			try {
				connection.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}

	
}