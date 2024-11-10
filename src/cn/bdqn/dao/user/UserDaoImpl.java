package cn.bdqn.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.User;
import cn.bdqn.params.UserParam;
import cn.bdqn.util.EmptyUtils;

public class UserDaoImpl extends BaseDao implements UserDao {
	
	public UserDaoImpl(Connection connection) {
		super(connection);
	}
	
	
    public User tableToClass(ResultSet rs) throws Exception {
        User user = new User();
        user.setLoginName(rs.getString("loginName"));
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setSex(rs.getInt("sex"));
        user.setIdentityCode(rs.getString("identityCode"));
        user.setEmail(rs.getString("email"));
        user.setMobile(rs.getString("mobile"));
        user.setType(rs.getInt("type"));
        user.setId(rs.getInt("id"));
        return user;
    }
  //根据用户输入的的账号  获取用户记录
	@Override
	public User getUserByLoginName(String loginName) throws Exception {
		StringBuffer sql=new StringBuffer("select * from easybuy_user where 1=1");
		List<Object> params=new ArrayList<Object>();
		if(!EmptyUtils.isEmpty(loginName)){
			sql.append(" and LoginName=?");
			params.add(loginName);
		}
		ResultSet rs=this.executeQuery(sql.toString(), params.toArray());
		User user=null;
		while(rs.next()){
			user=tableToClass(rs);
		}
		return user;
	}
	//用户注册
	@Override
	public int save(User user) {
		Integer id=0;
		StringBuffer sql=new StringBuffer("INSERT into easybuy_user(loginName,userName,password,sex,identityCode,email,mobile) values(?,?,?,?,?,?,?)");
		Object[] params={user.getLoginName(),user.getUserName(),user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile()};
		id=this.executeInsert(sql.toString(), params);
		return id;
	}


	@Override
	public void update(User user) throws Exception {
		try {
        	Object[] params = new Object[] {user.getUserName(),user.getType(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getId()};
        	String sql = " UPDATE easybuy_user SET userName =?,type=?,sex =?, identityCode =?, email =?, mobile =? WHERE id =?  ";
    		this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResoure();
        }
	}


	@Override
	public void deleteById(String id) throws Exception {
		String sql = " delete from easybuy_user where id = ? ";
		Object params[] = new Object[] { id };
		try{
			this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
        	this.closeResoure();
        }
	}



	@Override
	public List<User> queryUserList(UserParam params) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		List<User> userList=new ArrayList<User>();
		StringBuffer sql=new StringBuffer("  select id,loginName,password,userName,sex,identityCode,email,mobile,type from easybuy_user where 1=1 ");
		ResultSet resultSet = null;
		try {
			if(EmptyUtils.isNotEmpty(params.getLoginName())){
				sql.append(" and loginName = ? ");
				paramsList.add(params.getLoginName());
			}
			if(EmptyUtils.isNotEmpty(params.getSort())){
				sql.append(" order by " + params.getSort()+" ");
			}
			if(params.isPage()){
				sql.append(" limit  " + params.getStartIndex() + "," + params.getPageSize());
			}
			resultSet=this.executeQuery(sql.toString(),paramsList.toArray());
			while (resultSet.next()) {
				User user = this.tableToClass(resultSet);
				userList.add(user);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return userList;
	}
	
	public Integer queryUserCount(UserParam params) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		StringBuffer sql=new StringBuffer(" select count(*) count from easybuy_user where 1=1 ");
		Integer count=0;
		if(EmptyUtils.isNotEmpty(params.getLoginName())){
			sql.append(" and loginName = ? ");
			paramsList.add(params.getLoginName());
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count=resultSet.getInt("count");
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
	public User queryUserById(Integer id) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		List<User> userList=new ArrayList<User>();
		StringBuffer sql=new StringBuffer("  select id,loginName,userName,password,sex,identityCode,email,mobile,type from easybuy_user where id=? ");
		ResultSet resultSet = this.executeQuery(sql.toString(),new Object[]{id});
		User user=null;
		try {
			while (resultSet.next()) {
				user = this.tableToClass(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return user;
	}


	@Override
	public User findByLoginName(String loginName) throws Exception {
		 User user = null;
	        try {
	        	UserParam param = new UserParam();
	        	param.setLoginName(loginName);
	            List<User> userList = queryUserList(param);
	            if (EmptyUtils.isEmpty(userList)) {
	                return null;
	            } else {
	                return userList.get(0);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	        	this.closeResoure();
	        }
	        return user;
	}
}
