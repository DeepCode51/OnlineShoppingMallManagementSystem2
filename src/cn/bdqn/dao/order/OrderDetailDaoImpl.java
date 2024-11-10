package cn.bdqn.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.dao.product.ProductDao;
import cn.bdqn.dao.product.ProductDaoImpl;
import cn.bdqn.entity.OrderDetail;
import cn.bdqn.entity.Product;
import cn.bdqn.params.OrderDetailParam;
import cn.bdqn.util.EmptyUtils;

public class OrderDetailDaoImpl extends BaseDao implements OrderDetailDao {
	
	ProductDaoImpl productDao;
	
	 public OrderDetailDaoImpl(Connection connection) {
		super(connection);
		
	}

	public OrderDetail tableToClass(ResultSet rs) throws Exception {
	        OrderDetail orderDetail = new OrderDetail();
	        orderDetail.setId(rs.getInt("id"));
	        orderDetail.setOrderId(rs.getInt("orderId"));
	        int i=rs.getInt("productId");
	        System.out.println(i);
	        Product p=productDao.getProductById(i);
	        System.out.println(p);
	        orderDetail.setProduct(p);
	        orderDetail.setProductId(rs.getInt("productId"));
	        orderDetail.setQuantity(rs.getInt("quantity"));
	        orderDetail.setCost(rs.getFloat("cost"));
	        return orderDetail;
	    }
    //保存订单详情
	@Override
	public void saveOrderDetail(OrderDetail detail, int orderId)
			throws SQLException {
		Integer id=0; 
		String sql=" insert into easybuy_order_detail(orderId,productId,quantity,cost) values(?,?,?,?) ";
        try {
            Object param[]=new Object[]{detail.getOrderId(),detail.getProduct().getId(),detail.getQuantity(),detail.getCost()};
            id=this.executeInsert(sql,param);
            detail.setId(id);
        } catch (Exception e) {
			
            e.printStackTrace();
        }finally{
        	this.closeResoure();
        }
		
	}

	@Override
	public void deleteById(OrderDetail detail) throws Exception {
		String sql = " delete from easybuy_order_detail where id = ? ";
		Object params[] = new Object[] { detail.getId() };
		try {
		this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
	}
//查询订单数字表
	@Override
	public OrderDetail getOrderDetailById(Integer id) throws Exception {
		String sql = " select orderId,productId,quantity,cost from easybuy_order_detail where id = ? ";
		ResultSet resultSet = null;
		OrderDetail orderDetail = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				orderDetail = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResoure();
			return orderDetail;
		}
	}

	@Override
	public List<OrderDetail> queryOrderDetailList(OrderDetailParam params)
			throws Exception {
		List<OrderDetail> orderDetailList = null;
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(" select id,orderId,productId,quantity,cost FROM easybuy_order_detail where 1=1 ");
		
		if(EmptyUtils.isNotEmpty(params.getOrderId())){
			sql.append(" and orderId=? ");
			paramsList.add(params.getOrderId());
		}
		if(EmptyUtils.isNotEmpty(params.getSort())){
			sql.append(" order by " + params.getSort()+" ");
		}
		ResultSet resultSet = this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			orderDetailList=new ArrayList<OrderDetail>();
			while (resultSet.next()) {
				OrderDetail orderDetail = this.tableToClass(resultSet);
				orderDetailList.add(orderDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
			return orderDetailList;
		}
	}

}
