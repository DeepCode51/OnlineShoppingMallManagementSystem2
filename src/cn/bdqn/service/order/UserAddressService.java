package cn.bdqn.service.order;

import java.util.List;

import cn.bdqn.entity.UserAddress;

public interface UserAddressService {
	//查询用户地址的集合
	public List<UserAddress> queryUserAddressList(Integer UserId);
	//查询用户的地址
	public Integer saveUserAddress(UserAddress userAddress);
	// 根据ID查查询地址
	public UserAddress getUserAddressById(Integer addressId);
	//添加地址
	public Integer addUserAddress(Integer id, String address,String remark) throws Exception;
}
