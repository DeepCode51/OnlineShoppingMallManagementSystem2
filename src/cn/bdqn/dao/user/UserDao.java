package cn.bdqn.dao.user;

import java.util.List;

import cn.bdqn.entity.User;
import cn.bdqn.params.UserParam;

public interface UserDao {
	
	//根据用户的账号  获取用户记录
	public User getUserByLoginName(String loginName) throws Exception;
	//用户注册
	public int save(User user);

	void update(User user) throws Exception;//更新用户信息
	
	public void deleteById(String id) throws Exception;
	
	public List<User> queryUserList(UserParam params)throws Exception;
	
    public Integer queryUserCount(UserParam params) throws Exception;
	
	public User queryUserById(Integer id) throws Exception;

	User findByLoginName(String loginName) throws Exception;//根据ID查询用户信息
}
