package com.Order;

import java.util.List;
import java.util.Map;
public interface OrderService {
        public boolean addOrder(List<Object> params);
        public boolean addDetail(List<Object> params);
	public List<Map<String, Object>> viewOrder(List<Object> params);
        public boolean recordTotal(List<Object> params);
        public boolean delSell(String[] ids);
}
