package cn.bdqn.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import cn.bdqn.dao.order.UserAddressDao;
import cn.bdqn.dao.order.UserAddressDaoImpl;
import cn.bdqn.entity.UserAddress;
import cn.bdqn.util.DateSourceUtil;

public class UserAddressServiceImpl implements UserAddressService {
	 
	@Override
	public List<UserAddress> queryUserAddressList(Integer UserId) {
		Connection connection=null;
		UserAddressDao userAddressdao=null;
		List<UserAddress> userAdressList=new ArrayList<UserAddress>();
		try {
			connection=DateSourceUtil.openConnection();
			
			userAddressdao=new UserAddressDaoImpl(connection);
			userAdressList=userAddressdao.queryUserAddressList(UserId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DateSourceUtil.closeConnection(connection);
		}
		return userAdressList;
	}

	@Override
	public Integer saveUserAddress(UserAddress userAddress) {
		
		return null;
	}
//根据ID查
	@Override
	public UserAddress getUserAddressById(Integer addressId) {
		 Connection connection = null;
	        UserAddress userAddress= null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
	            userAddress = (UserAddress) userAddressDao.getUserAddressById(addressId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	        return  userAddress;
	}
	//添加
	 @Override
	    public Integer addUserAddress(Integer id, String address,String remark) {
	        Connection connection = null;
	        Integer userAddressId = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
	            UserAddress userAddress=new UserAddress();
	            userAddress.setUserId(id);
	            userAddress.setAddress(address);
	            userAddress.setRemark(remark);
	            userAddressId = userAddressDao.saveUserAddress(userAddress);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	        	DateSourceUtil.closeConnection(connection);
	        }
	        return userAddressId;
	    }
	
}
