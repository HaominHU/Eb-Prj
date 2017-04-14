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
	public List<Map<String, Object>> listProduct(String bookname) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from book where 1=1 and bookname like ? ";	
			if(bookname.equals("")){
				sql = "select * from book ";
				
				
			}else{				
				params.add("%"+bookname+"%");
				
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
	public List<Map<String, Object>> searchProduct(String bookname) {
	
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from book where 1=1 and bookname like ? ";	
			if(bookname.equals("")){
				sql = "select * from book ";
				
				
			}else{				
				params.add("%"+bookname+"%");
				
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
	public List<Map<String, Object>> rankProduct(String bookname) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from book order by bookprice";	
		
					
			list = jdbcUtils.findMoreResult(sql, params);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			
			jdbcUtils.releaseConn();
			
		}
		
		
		return list;
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
	public Map<String, Object> viewProduct(String bookid) {
		Map<String, Object> map = null;
		try {
			jdbcUtils.getConnection();
			List<Object> params = new ArrayList<Object>();
			params.add(bookid);
			String sql = "select * from book where bookid = ?";
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
				
                                
                for(int i=0;i<ids.length;i++){
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
        public List<Map<String,Object>> checkOut(String[] ids){
        
            List<Map<String,Object>> map= new ArrayList<Map<String,Object>>();
            List<Object> params = new ArrayList<Object>();
            try{
                jdbcUtils.getConnection();
                
               if (ids!=null) {
				String sql ="select * from book where ";
                                for(int i=0;i<ids.length;i++)
                                {
                                    String temp = " bookname =  '" + ids[i]+ "'";
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
		public boolean addShopcart(List<Object> params){
			boolean flag = false;
			try {
				jdbcUtils.getConnection();
				String sql = "insert into shopCart(scid,susername,sbookname) values(?,?,?)";
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
        public List<Map<String, Object>> kindSearch(String booktype) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from book where 1=1 and bookkind like ? ";	
			if(booktype.equals("")){
				sql = "select * from book ";
				
				
			}else{				
				params.add("%"+booktype+"%");
			
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
	
}
