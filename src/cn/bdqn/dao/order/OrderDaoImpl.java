package cn.bdqn.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.Order;
import cn.bdqn.params.OrderParams;
import cn.bdqn.util.EmptyUtils;

public class OrderDaoImpl extends BaseDao implements OrderDao{

	public OrderDaoImpl(Connection connection) {
		super(connection);
	}
	public Order tableToClass(ResultSet rs) throws Exception {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("userId"));
		order.setCreateTime(rs.getDate("createTime"));
		order.setCost(rs.getFloat("cost"));
		order.setUserAddress(rs.getString("userAddress"));
		order.setSerialNumber(rs.getString("serialNumber"));
		order.setLoginName(rs.getString("loginName"));
		return order;
	}

	@Override
	public void saveOrder(Order order) {
		Integer id=0;
		String sql="insert into easybuy_order (userId,loginName,userAddress,createTime,cost,serialNumber) values (?,?,?,?,?,?) ";
		Object[] params=new Object[]{order.getUserId(),order.getLoginName(),order.getUserAddress(),new Date(),order.getCost(),order.getSerialNumber()};
		id=this.executeInsert(sql, params);
		order.setId(new Integer(id).intValue());
		this.closeResoure();
	}
	//查询订单的方法
	@Override
	public List<Order> queryOrderList(OrderParams params) {
		List<Object> paramsList=new ArrayList<Object>();   
		List<Order> orderList=new ArrayList<Order>();
		StringBuffer sql=new StringBuffer(" select id,userId,loginName,userAddress,createTime,cost,serialNumber from easybuy_order  where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getUserId())){
			sql.append(" and userId = ? ");
			paramsList.add(params.getUserId());
		}
		if(EmptyUtils.isNotEmpty(params.getSort())){
			sql.append(" order by " + params.getSort()+" ");
		}
		if(params.isPage()){
			sql.append(" limit  " + params.getStartIndex() + "," + params.getPageSize());
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				Order order = this.tableToClass(resultSet);
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}	
		return orderList;
	}
	//查询订单的总条数
	@Override
	public Integer queryOrderCount(OrderParams params) {
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count=0;
		StringBuffer sql=new StringBuffer(" select count(id) count from easybuy_order  where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getUserId())){
			sql.append(" and userId = ? ");
			paramsList.add(params.getUserId());
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return count;
	}
	@Override
	public void deleteById(Integer id) {
		String sql = " delete from easybuy_order where id = ? ";
		Object params[] = new Object[] { id };
		try {
			this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
	}
	@Override
	public Order getOrderById(Integer id) {
		String sql = " select * from easybuy_order where id = ? ";
		ResultSet resultSet = null;
		Order order = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				order = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResoure();
			return order;
		}
	}
}
