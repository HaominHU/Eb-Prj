package com.Cproduct;

import java.util.List;
import java.util.Map;

public interface CProductService {
	public boolean addProduct(List<Object> params);
	public List<Map<String, Object>> listProduct(String bookname);
	public boolean delProduct(String[] ids);
	public Map<String, Object> viewProduct(String bookid);
        public List<Map<String,Object>> addToCart(String[] ids);
        public List<Map<String,Object>> checkOut(String[] ids);
        public List<Map<String, Object>> rankProduct(String bookname);
        public List<Map<String, Object>> searchProduct(String bookname);
        public List<Map<String, Object>> kindSearch(String booktype);
        public List<Map<String,Object>> viewMytrade(List<Object> params);
        public List<Map<String,Object>> viewShoppingCart(List<Object> params);
        public boolean addShopcart(List<Object> params);
        public List<Map<String,Object>> viewOrder(String username);
        public boolean del(String[] ids);
}
