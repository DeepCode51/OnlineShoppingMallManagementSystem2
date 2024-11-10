package cn.bdqn.dao.order;

import java.util.List;

import cn.bdqn.entity.UserAddress;

public interface UserAddressDao {
	//查询用户地址的集合
	public List<UserAddress> queryUserAddressList(Integer UserId);
	//查询用户的地址
	public Integer saveUserAddress(UserAddress userAddress);
	// 
	public UserAddress getUserAddressById(Integer addressId);
	
}
