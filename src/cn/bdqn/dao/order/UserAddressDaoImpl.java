package cn.bdqn.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.UserAddress;
import cn.bdqn.util.DateSourceUtil;
import cn.bdqn.util.EmptyUtils;

public class UserAddressDaoImpl extends BaseDao implements UserAddressDao {

	public UserAddressDaoImpl(Connection connection) {
		super(connection);
		
	}
	
	public UserAddress tableToClass(ResultSet rs) throws Exception {
		 UserAddress userAddress = new UserAddress();
	     userAddress.setId(rs.getInt("id"));
	     userAddress.setAddress(rs.getString("address"));
	     userAddress.setUserId(rs.getInt("userId"));
	     userAddress.setCreateTime(rs.getDate("createTime"));
	     userAddress.setRemark(rs.getString("remark"));
	     return userAddress;
	}
	
	
	
	@Override
	public List<UserAddress> queryUserAddressList(Integer UserId) {
		List<Object> paramsList=new ArrayList<Object>();
		List<UserAddress> userAddresseList=new ArrayList<UserAddress>();
		StringBuffer sql=new StringBuffer("select * from easybuy_user_address where 1=1");
		if(EmptyUtils.isEmpty(UserId)){
			sql.append(" and userId=?");
			paramsList.add(UserId);
		}
		ResultSet resultSet=this.executeQuery(sql.toString(),paramsList.toArray());	
		try {
			while(resultSet.next()){
				UserAddress userAddress=this.tableToClass(resultSet);
				userAddresseList.add(userAddress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return userAddresseList;
	}

	@Override
	public Integer saveUserAddress(UserAddress userAddress) {
		
		return null;
	}

	@Override
	public UserAddress getUserAddressById(Integer addressId) {
		List<Object> paramsList=new ArrayList<Object>();   
		StringBuffer sql=new StringBuffer(" select id,userId,address,createTime,isDefault,remark from easybuy_user_address  where id=? ");
		UserAddress userAddress =null;
		ResultSet resultSet = this.executeQuery(sql.toString(),new Object[]{addressId});
		try {
			while (resultSet.next()) {
				userAddress= this.tableToClass(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return userAddress;
	}
}
	

