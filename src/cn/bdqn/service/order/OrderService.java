package cn.bdqn.service.order;

import java.util.List;

import cn.bdqn.entity.Order;
import cn.bdqn.entity.OrderDetail;
import cn.bdqn.entity.User;
import cn.bdqn.params.OrderDetailParam;
import cn.bdqn.params.OrderParams;
import cn.bdqn.util.Pager;
import cn.bdqn.util.ShoppingCart;

public interface OrderService {
	//订单生成的业务里的方法
	public Order payShoppingCart(User user,String address,ShoppingCart cart);
	//查询当前用户的订单
	List<Order> queryOrderList(Integer userId,Pager pager);

	public int getOrderRowCount(OrderParams params);
	
	List<OrderDetail> queryOrderDetailList(OrderDetailParam params);

	Order findById(String parameter);//根据ID获取订单
	//查询全部订单
	List<Order> queryOrderList(Pager pager);
}
