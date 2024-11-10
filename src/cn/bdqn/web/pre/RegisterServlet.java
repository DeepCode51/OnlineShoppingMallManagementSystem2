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
import cn.bdqn.util.Constants;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.RegUtils;
import cn.bdqn.util.ReturnResult;
import cn.bdqn.util.SecurityUtils;
@WebServlet(urlPatterns={"/RegisterServlet"},name="RegisterServlet")
public class RegisterServlet extends AbstractServlet {
	private UserService userService;
	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
		super();
	}
	public void init() throws ServletException {
		userService=new UserServiceImpl();
	}
	@Override
	public Class getServletClass() {	
		return RegisterServlet.class;
	}
	//注册页面的跳转
	public String toRegister(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "/pre/register";
	}
	//注册的方法
	public ReturnResult doRegister(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ReturnResult result=new ReturnResult();
		User user=new User();
		String loginName=request.getParameter("loginName");
		User oldUser=userService.getUserByLoginName(loginName);
		if(EmptyUtils.isNotEmpty(oldUser)){
			result.returnFail("用户已经存在");
			return result;
		}
		String sex=request.getParameter("sex");
		String password=request.getParameter("password");
		String userName=request.getParameter("userName");
		String identityCode=request.getParameter("identityCode");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		
		user.setLoginName(loginName);
		user.setPassword(SecurityUtils.md5Hex(password));
		user.setSex(EmptyUtils.isEmpty(sex)?1:0);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setIdentityCode(identityCode);
		user.setUserName(userName);
		user.setType(Constants.UserType.PRE);
		
		result=checkUser(user);
		if(result.getStatus()==Constants.ReturnResult.SUCCESS){
			boolean flag=userService.save(user);
			if(!flag){
				return result.returnFail("注册失败");
			}else{
				return result.returnSuccess("注册成功");
			}
		}else{
			return result;
		}
	}
	//对手机号码，省份证，邮箱进行验证 正则表达式
	public ReturnResult checkUser(User user){
		ReturnResult result=new ReturnResult();
		if(EmptyUtils.isEmpty(user.getMobile())){
			if(!RegUtils.checkMobile(user.getMobile())){
				return result.returnFail("手机号码格式不正确");		
			}
		}
		if(EmptyUtils.isEmpty(user.getIdentityCode())){
			if(!RegUtils.checkMobile(user.getIdentityCode())){
				return result.returnFail("身份证号码格式不正确");		
			}
		}
		if(EmptyUtils.isEmpty(user.getEmail())){
			if(!RegUtils.checkMobile(user.getEmail())){
				return result.returnFail("邮箱格式不正确");		
			}
		}
		return result.returnSuccess();
	}
}
