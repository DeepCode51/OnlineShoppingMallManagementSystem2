package cn.bdqn.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.bdqn.dao.user.UserDao;
import cn.bdqn.dao.user.UserDaoImpl;
import cn.bdqn.entity.User;
import cn.bdqn.params.UserParam;
import cn.bdqn.util.DateSourceUtil;

public class UserServiceImpl implements UserService {
	
	private Connection connection;
	
	private UserDao uDao;
	@Override
	public User getUserByLoginName(String loginName)  {
		User user=null;
		try {
			connection=DateSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			user=uDao.getUserByLoginName(loginName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DateSourceUtil.closeConnection(connection);
		}
		return user;
	}
	//用户注册
	@Override
	public boolean save(User user) {
		boolean flag=false;
		try {
			connection=DateSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			int cont=uDao.save(user);
			if(cont>0){
				flag=true;
			}
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}finally{
			DateSourceUtil.closeConnection(connection);
		}
		return flag;
	}
	@Override
	public void update(User user) {
		Connection connection = null;
		try {
			connection = DateSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			userDao.update(user);
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
	}
	@Override
	public void delete(String id) {
		Connection connection = null;
		try {
			connection = DateSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			userDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
	}
	@Override
	public List<User> queryUserList(UserParam userParam) {
		Connection connection = null;
		List<User> userList = null;
		try {
			connection = DateSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			userList = userDao.queryUserList(userParam);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return userList;
	}
	@Override
	public int queryUserCount(UserParam params) {
		Connection connection = null;
		int count=0;
		try {
			connection = DateSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count = userDao.queryUserCount(params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return count;
	}
	@Override
	public User queryUserById(Integer userId) {
		Connection connection = null;
		User user = null;
		try {
			connection = DateSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			user = (User) userDao.queryUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return user;
	}
	@Override
	public User findByLoginName(String loginName) {
		Connection connection = null;
		User user = null;
		try {
			connection = DateSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			user = userDao.findByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return user;
	}

}
