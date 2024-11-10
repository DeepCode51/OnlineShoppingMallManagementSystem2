package cn.bdqn.dao.order;

import java.util.List;

import cn.bdqn.entity.Order;
import cn.bdqn.params.OrderParams;

public interface OrderDao {
	//查询订单
	public void saveOrder(Order order);
	//查询订单列表
	public List<Order> queryOrderList(OrderParams params);
	
	public Integer queryOrderCount(OrderParams params);
	
    public void deleteById(Integer id);
	
	public Order getOrderById(Integer id) ;
	
	
	
}
