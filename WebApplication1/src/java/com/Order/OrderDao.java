
package com.Order;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jdbc.JdbcUtils;
public class OrderDao implements OrderService {
    private JdbcUtils jdbcUtils;
    public OrderDao() {
		
		jdbcUtils = new JdbcUtils();
	}   
    
    
    @Override
	public boolean addOrder(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into ord(oid,username,total) values(?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
		
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}
        
         @Override
	public boolean delSell(String[] ids){
		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = false;
		try{
			jdbcUtils.getConnection();
			if(ids!=null){
				
				String[] sql = new String[ids.length];
				String[] sql2 = new String[ids.length];
				for(int i = 0 ; i< ids.length; i++){
					sql[i] = "delete from book where bookid = '"+ids[i]+"'";
					sql2[i] = "delete from shopcart where scid = '"+ids[i]+"'";
				}
				flag = jdbcUtils.deleteByBatch(sql);
				flag2 = jdbcUtils.deleteByBatch(sql2);
				if(flag && flag2){
					flag3 = true;
				}
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		} finally{
		
			jdbcUtils.releaseConn();
		}	
		
		return flag3;
	}
        
        @Override
        public boolean addDetail(List<Object>params){
            		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into orddetail (oid,bookname,bookquantity) values(?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			
			
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
        }
        
//	@Override
//	public List<Map<String, Object>> listOrder(String username) {
//		
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		List<Object> params  = new ArrayList<Object>();		
//		try {
//			jdbcUtils.getConnection();			
//			String sql = "select * from ord where username = ? ";	
//                        params.add(username);
//						
//			list = jdbcUtils.findMoreResult(sql, params);			
//			
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		} finally{
//			
//			jdbcUtils.releaseConn();
//			
//		}
//		
//		
//		return list;
//	}


        

	@Override
	public List<Map<String, Object>> viewOrder(List<Object> params) {
		
		List<Map<String, Object>> list = null;
		try {
			jdbcUtils.getConnection();
			
			String sql = "select bookname,bookquantity from orddetail where oid = ?";
			
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally{
			
			jdbcUtils.releaseConn();
		}
		
		
		return list;
	}
        
        @Override
        public boolean recordTotal(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into ord(oid,username,total) values(?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			
			jdbcUtils.releaseConn();
			
		}
		
		return flag;
	}
}
