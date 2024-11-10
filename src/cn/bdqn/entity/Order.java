package cn.bdqn.entity;

import java.util.Date;
import java.util.List;

public class Order {
	//订单ID
	private Integer id;
	//用户名ID
	private Integer userId;
	//账户名
	private String loginName;
	//收货地址
	private String userAddress;
	//下单时间
	private Date createTime;
	//订单总金额
	private double cost;
	//订单号
	private String serialNumber;
	
	private List<OrderDetail> orderDetailList;
	/**
	 * @return the orderDetailList
	 */
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	/**
	 * @param orderDetailList the orderDetailList to set
	 */
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
}
