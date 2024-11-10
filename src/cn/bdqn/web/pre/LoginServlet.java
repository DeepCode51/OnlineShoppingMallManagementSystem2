package cn.bdqn.web.pre;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.User;
import cn.bdqn.service.user.UserService;
import cn.bdqn.service.user.UserServiceImpl;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.ReturnResult;
import cn.bdqn.util.SecurityUtils;
@WebServlet(urlPatterns={"/LoginServlet"},name="LoginServlet")
public class LoginServlet extends AbstractServlet{
	
	private UserService userService;
	
	public void init() throws ServletException {
		userService=new UserServiceImpl();
	}

	@Override
	public Class getServletClass() {	
		return LoginServlet.class;
	}
	//跳转到登入页面的方法
	public String toLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/pre/login";
	}
	//用户的账号和密码和数据匹配
	public ReturnResult login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ReturnResult result=new ReturnResult();
		//获取用户输入的账号和密码
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
		//以用户输入的账号查询用户信息
		User user=userService.getUserByLoginName(loginName);
		if(EmptyUtils.isEmpty(user)){
			//如果用户输入的账号没查询到   返回用户不存在
			result.returnFail("用户不存在");
		}else{
			//如果有这个账号  再匹配他的密码
			if(user.getPassword().equals(SecurityUtils.md5Hex(password))){
				//如果匹配成功  返回给前台 user 对象
				request.getSession().setAttribute("loginUser",user);
				result.returnSuccess("登入成功");
			}else{
				result.returnFail("密码错误");
			}
		}
		return result;
	}
	//注销
	public String loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ReturnResult result=new ReturnResult();
		request.getSession().removeAttribute("loginUser");
		result.returnSuccess("注销成功");
		return "/pre/login";
	}
}
