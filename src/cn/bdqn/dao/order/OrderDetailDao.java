package cn.bdqn.dao.order;

import java.sql.SQLException;
import java.util.List;

import cn.bdqn.entity.OrderDetail;
import cn.bdqn.params.OrderDetailParam;

public interface OrderDetailDao {
	
	    public void saveOrderDetail(OrderDetail detail,int orderId) throws SQLException;

		public void deleteById(OrderDetail detail) throws Exception;
		
		public OrderDetail getOrderDetailById(Integer id)throws Exception; 
		
		public List<OrderDetail> queryOrderDetailList(OrderDetailParam params)throws Exception; 
		
		//public Integer queryOrderDetailCount(OrderDetailParam params)throws Exception; 

}
