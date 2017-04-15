package com.Cproduct;

import java.util.List;
import java.util.Map;

public interface CProductService {
	public boolean addProduct(List<Object> params);
		
	public List<Map<String, Object>> listProduct(String bookname);
	
	//批处理删除产品
	public boolean delProduct(String[] ids);
	//查询单个产品
	public Map<String, Object> viewProduct(String bookid);
        //加入购物车

	public boolean addShopcart(List<Object> params);
    public List<Map<String,Object>> addToCart(String[] ids);
	public List<Map<String,Object>> checkOut(String[] ids);

    public List<Map<String, Object>> rankProduct(String bookname);
    public List<Map<String, Object>> searchProduct(String bookname);
    public List<Map<String, Object>> kindSearch(String booktype);
}
