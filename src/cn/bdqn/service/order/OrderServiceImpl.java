package cn.bdqn.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import cn.bdqn.dao.order.OrderDao;
import cn.bdqn.dao.order.OrderDaoImpl;
import cn.bdqn.dao.order.OrderDetailDao;
import cn.bdqn.dao.order.OrderDetailDaoImpl;
import cn.bdqn.dao.product.ProductDao;
import cn.bdqn.dao.product.ProductDaoImpl;
import cn.bdqn.entity.Order;
import cn.bdqn.entity.OrderDetail;
import cn.bdqn.entity.User;
import cn.bdqn.params.OrderDetailParam;
import cn.bdqn.params.OrderParams;
import cn.bdqn.util.DateSourceUtil;
import cn.bdqn.util.Pager;
import cn.bdqn.util.ShoppingCart;
import cn.bdqn.util.ShoppingCartItem;
import cn.bdqn.util.StringUtils;

public class OrderServiceImpl implements OrderService {
	private Connection connection;
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private ProductDao productDao;
	//订单的生成   添加订单  订单详情添加  库存的更改 事物的控制
	@Override
	public Order payShoppingCart(User user, String address, ShoppingCart cart) {
		Order order=new Order();
		try {
			connection=DateSourceUtil.openConnection();
			orderDao=new OrderDaoImpl(connection);
			orderDetailDao=new OrderDetailDaoImpl(connection);
			productDao=new ProductDaoImpl(connection);
			//开启事物的控制   整体控制事物的提交   false为不自动提交
			connection.setAutoCommit(false);
			//订单的生成
			order.setUserId(user.getId());
			order.setUserAddress(address);
			order.setCreateTime(new Date());
			order.setCost(cart.getTotalCost());
			order.setSerialNumber(StringUtils.randomUUID());
			order.setLoginName(user.getLoginName());
			orderDao.saveOrder(order);
			//订单详情的添加
			for(ShoppingCartItem item:cart.getItems()){
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setOrderId(order.getId());
				orderDetail.setCost(item.getCost());
				orderDetail.setProduct(item.getProduct());
				orderDetail.setQuantity(item.getQuantity());
				orderDetailDao.saveOrderDetail(orderDetail, order.getId());
				//修改库存
				productDao.updateStock(item.getProduct().getId(), item.getQuantity());
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			order=null;
			e.printStackTrace();
		}finally{
			DateSourceUtil.closeConnection(connection);
			return order;
		}
	}
	@Override
	public List<Order> queryOrderList(Integer userId, Pager pager) {
		Connection connection = null;
		List<Order> orderList = null;
		try {
			connection = DateSourceUtil.openConnection();
			OrderDao orderDao = new OrderDaoImpl(connection);
			OrderParams params = new OrderParams();
			params.setUserId(userId);
			params.setStartIndex((pager.getCurrentPage() - 1) * pager.getRowPerPage());
			params.setPageSize(pager.getRowPerPage());
			params.setSort(" createTime desc ");
			orderList = orderDao.queryOrderList(params);
			for (int i = 0; i < orderList.size(); i++) {
				Order order = orderList.get(i);
				OrderDetailParam orderDetailParam=new OrderDetailParam();
				orderDetailParam.setOrderId(order.getId());
				order.setOrderDetailList(queryOrderDetailList(orderDetailParam));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return orderList;
	}
	
	//订单总条数
	@Override
	public int getOrderRowCount(OrderParams params) {
		Connection connection = null;
		int rtn = 0;
		try {
			connection = DateSourceUtil.openConnection();
			OrderDao orderDao = new OrderDaoImpl(connection);
			rtn = orderDao.queryOrderCount(params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return rtn;
	}
	@Override
	public List<OrderDetail> queryOrderDetailList(OrderDetailParam params) {
		Connection connection = null;
		List<OrderDetail> rtn = null;
		try {
			connection = DateSourceUtil.openConnection();
			OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(connection);
			rtn = orderDetailDao.queryOrderDetailList(params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return rtn;
		
	}
	@Override
	public Order findById(String id) {
		Connection connection = null;
		Order order = null;
		try {
			connection = DateSourceUtil.openConnection();
			OrderDao orderDao = new OrderDaoImpl(connection);
			order = (Order) orderDao.getOrderById(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return order;
	}

	
	
	@Override
	public List<Order> queryOrderList(Pager pager) {
		Connection connection = null;
		List<Order> orderList = null;
		try {
			connection = DateSourceUtil.openConnection();
			OrderDao orderDao = new OrderDaoImpl(connection);
			OrderParams params = new OrderParams();
			params.openPage((pager.getCurrentPage() - 1) * pager.getRowPerPage(), pager.getRowPerPage());
			orderList=orderDao.queryOrderList(params);
			for (int i = 0; i < orderList.size(); i++) {
				Order order = orderList.get(i);
				OrderDetailParam orderDetailParam=new OrderDetailParam();
				orderDetailParam.setOrderId(order.getId());
				order.setOrderDetailList(queryOrderDetailList(orderDetailParam));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return orderList;
	}
}
