
package com.Order;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jdbc.JdbcUtils;
public class OrderDao implements OrderService {
    private JdbcUtils jdbcUtils;
    public OrderDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}   
    
    @Override
    public List<Map<String, Object>> mosttotalOrder(String username) {
	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "SELECT o1.username FROM `ord` o1 GROUP BY o1.username HAVING COUNT(o1.username)>= ALL (SELECT COUNT(o2.username) FROM `ord` o2 GROUP BY o2.username)";						
			list = jdbcUtils.findMoreResult(sql, params);			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			jdbcUtils.releaseConn();
		}
		
		
		return list;
    }
    @Override
	public boolean addOrder(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into ord(oid,username,total) values(?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			// 关闭数据库连接
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}
        
        @Override
        public boolean addDetail(List<Object>params){
            		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into orddetail (oid,bookname,bookquantity) values(?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			// 关闭数据库连接
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
        }
	
        @Override
	public boolean delSell(String[] ids, String[] ids2){
		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = false;
		try{
			jdbcUtils.getConnection();
			if(ids!=null && ids2!=null){
				//flag3 = true;
				String[] sql = new String[ids.length];
				String[] sql2 = new String[ids2.length];
				for(int i = 0 ; i< ids.length; i++){
					sql[i] = "delete from book where bookid = '"+ids[i]+"'";
				}
				for(int j = 0;j<ids2.length;j++){
					sql2[j] = "delete from shopCart where sbookname = '"+ids2[j]+"'";
				}
				flag = jdbcUtils.deleteByBatch(sql);
				flag2 = jdbcUtils.deleteByBatch(sql2);
				if(flag && flag2){
					flag3 = true;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}	
		
		return flag3;
	}

	// @Override
	// public boolean delSc(String[] ids){
	// 	boolean flag = false;
	// 	try{
	// 		jdbcUtils.getConnection();
	// 		if(ids != null){
	// 			String[] sql = new String[ids.length];
	// 			for(int i = 0;i<ids.length;i++){
	// 				sql[i] = "delete from shopCart where sbookname = '"+ids[i]+"'";
	// 			}
	// 		}
	// 	}
	// 	catch (Exception e) {
	// 		// TODO: handle exception
	// 		e.printStackTrace();
	// 	} finally{
	// 		// 关闭数据库连接
	// 		jdbcUtils.releaseConn();
	// 	}	
	// 	return flag;
	// }
        
	@Override
	public List<Map<String, Object>> listOrder(String username ,int start ,int end) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from ord where username = ? limit ? ,?";	
			if(username.equals("1234")){
				sql = "select * from ord limit ? ,?";
				params.add(start);
				params.add(end);
				
			}else{				
				params.add(username);
				params.add(start);
				params.add(end);
			}		
					
			list = jdbcUtils.findMoreResult(sql, params);			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			
			
			jdbcUtils.releaseConn();
			
		}
		
		
		return list;
	}

	//查询总记录数
	@Override
	public int getItemCount(String username) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> map = null;
		List<Object> params = null;		
		try {
			jdbcUtils.getConnection();			
			String sql = "select count(*) totalCount from ord where 1=1 and username like ?";	
			if(username.equals("")){
				sql = "select count(*) totalCount from ord";
				
			}else{
				params = new ArrayList<Object>();
				params.add("%"+username+"%");
			}
		map = jdbcUtils.findSimpleResult(sql, params);
		count = Integer.parseInt(map.get("totalCount").toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}
		
		
		return count;
	}
        

	@Override
	public List<Map<String, Object>> viewOrder(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = null;
		try {
			jdbcUtils.getConnection();
			//List<Object> params = new ArrayList<Object>();
			String sql = "select bookname,bookquantity from orddetail where oid = ?";
			//map = jdbcUtils.findSimpleResult(sql, params);
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}
		
		
		return list;
	}
        
        @Override
        public boolean recordTotal(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into ord(oid,username,total) values(?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			// 关闭数据库连接
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}

    @Override
    public boolean updateOrder(List<Object> params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delOrder(String[] ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
