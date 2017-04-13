package com.Cproduct;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jdbc.JdbcUtils;

public class CProductDao implements CProductService {

	private JdbcUtils jdbcUtils;
	public CProductDao() {
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public boolean addProduct(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into product(proid,proname,proprice,proaddress,proimage) values(?,?,?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			// close
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}

	@Override
	public List<Map<String, Object>> listProduct(String bookname ,int start ,int end) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from book where 1=1 and bookname like ? limit ? ,?";	
			if(bookname.equals("")){
				sql = "select * from book limit ? ,?";
				params.add(start);
				params.add(end);
				
			}else{				
				params.add("%"+bookname+"%");
				params.add(start);
				params.add(end);
			}		
					
			list = jdbcUtils.findMoreResult(sql, params);			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally{
			
			
			jdbcUtils.releaseConn();
			
		}
		
		
		return list;
	}
        
        @Override
	public List<Map<String, Object>> searchProduct(String bookname ,int start ,int end) {
	
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from book where 1=1 and bookname like ? limit ? ,?";	
			if(bookname.equals("")){
				sql = "select * from book limit ? ,?";
				params.add(start);
				params.add(end);
				
			}else{				
				params.add("%"+bookname+"%");
				params.add(start);
				params.add(end);
			}		
					
			list = jdbcUtils.findMoreResult(sql, params);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			
			jdbcUtils.releaseConn();
			
		}
		
		
		return list;
	}
        
        @Override
	public List<Map<String, Object>> rankProduct(String proname ,int start ,int end) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from product order by proprice";	
//			if(proname.equals("")){
//				sql = "select * from product limit ? ,?";
//				params.add(start);
//				params.add(end);
//				
//			}else{				
//				params.add("%"+proname+"%");
//				params.add(start);
//				params.add(end);
//			}		
					
			list = jdbcUtils.findMoreResult(sql, params);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			
			jdbcUtils.releaseConn();
			
		}
		
		
		return list;
	}

	
	@Override
	public int getItemCount(String proname) {
		int count = 0;
		Map<String, Object> map = null;
		List<Object> params = null;		
		try {
			jdbcUtils.getConnection();			
			String sql = "select count(*) totalCount from product where 1=1 and proname like ?";	
			if(proname.equals("")){
				sql = "select count(*) totalCount from product";
				
			}else{
				params = new ArrayList<Object>();
				params.add("%"+proname+"%");
			}
		map = jdbcUtils.findSimpleResult(sql, params);
		count = Integer.parseInt(map.get("totalCount").toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtils.releaseConn();
		}
		
		
		return count;
	}

	@Override
	public boolean delProduct(String[] ids) {
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			if (ids!=null) {
				String[] sql = new String[ids.length];
				for(int i = 0 ; i< ids.length; i++){
					sql[i] = "delete from product where proid = '"+ids[i]+"'";
					System.out.println(sql[i]);
				}
				flag = jdbcUtils.deleteByBatch(sql);	
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtils.releaseConn();
		}	
		
		return flag;
	}

	@Override
	public Map<String, Object> viewProduct(String proid) {
		Map<String, Object> map = null;
		try {
			jdbcUtils.getConnection();
			List<Object> params = new ArrayList<Object>();
			params.add(proid);
			String sql = "select * from product where proid = ?";
			map = jdbcUtils.findSimpleResult(sql, params);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtils.releaseConn();
		}
		
		
		return map;
	}
        
        @Override
        public List<Map<String,Object>> addToCart(String[] ids){
        
            List<Map<String,Object>> map= new ArrayList<Map<String,Object>>();
            List<Object> params = new ArrayList<Object>();
            try{
                jdbcUtils.getConnection();
                
               if (ids!=null) {
				String sql ="select * from book where ";
                                for(int i=0;i<ids.length;i++)
                                {
                                    String temp = " bookid =  '" + ids[i]+ "'";
                                    sql+= temp;
                                    if(i!=ids.length-1)
                                    {
                                        sql+=" or ";
                                    }
					System.out.println(sql);
				}
                                map = jdbcUtils.findMoreResult2(sql);
            }
            }
            
            catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtils.releaseConn();
		}
		
		
		return map;
	}

	 @Override
        public boolean updateNumber(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "update product set number = ? where product.proid = ?";
			flag = jdbcUtils.updateByPreparedStatement1(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}
}
