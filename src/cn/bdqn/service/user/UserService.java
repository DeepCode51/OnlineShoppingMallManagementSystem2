package cn.bdqn.service.user;

import java.util.List;

import cn.bdqn.entity.User;
import cn.bdqn.params.UserParam;

public interface UserService {
		//根据用户的账号  获取用户记录
		public User getUserByLoginName(String loginName);
		//用户注册
		public boolean save(User user);
		
		public void update(User user);//更新用户信息
		
		void delete(String id);//根据用户名删除用户

		public List<User> queryUserList(UserParam userParam);

		public int queryUserCount(UserParam params);

		public User queryUserById(Integer userId);
		
		public User findByLoginName(String loginName);//根据ID查询用户信息
}
