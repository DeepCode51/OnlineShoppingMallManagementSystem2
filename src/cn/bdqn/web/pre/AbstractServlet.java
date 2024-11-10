package cn.bdqn.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.ReturnResult;
@WebServlet(urlPatterns={"/AbstractServlet"},name="AbstractServlet")
public abstract class AbstractServlet extends HttpServlet {
	
	public abstract Class getServletClass();
	
	public AbstractServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得页面传过来的参数
		String action=request.getParameter("action");
		//反射方法
		Method method=null;
		//首页网址名称
		Object result=null;
		try {
			if(EmptyUtils.isEmpty(action)){
				//如果传过来的参数为空 返回首页
				request.getRequestDispatcher("/pre/Index.jsp").forward(request,response);
			}else{
			
				//通过抽象类获得实体继承类的方法
				method=getServletClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
				//调用获取到的方法   this代表改方法所属的对象     request response代表该方法的参数
				result=method.invoke(this, request,response);
			}	
				toView(request,response,result);
			} catch (NoSuchMethodException e) {
				String viewName="404.jsp";
				request.getRequestDispatcher(viewName).forward(request,response);
				e.printStackTrace();
			} catch (Exception e) {
					if(!EmptyUtils.isEmpty(result)){
						if(result instanceof String){
							String viewName="500.jsp";
							request.getRequestDispatcher(viewName).forward(request,response);
						}else{
							ReturnResult returnR=new ReturnResult();
							returnR.returnFail("系统错误");
						}
					}else{
						String viewName="500.jsp";
						request.getRequestDispatcher(viewName).forward(request,response);
					}
				e.printStackTrace();
			}
	}
	/*public void init() throws ServletException {
		
	}*/
	public void toView(HttpServletRequest request, HttpServletResponse response,Object result) throws Exception{
		if(!EmptyUtils.isEmpty(result)){
			if(result instanceof String){
				//如果返回的是字符串 就跳转页面
				String viewName=result.toString()+".jsp";
				System.out.println(viewName);
				//返回首页
				request.getRequestDispatcher(viewName).forward(request,response);
			}else{
				write(result,response);
			}	
		}
	}
	//首页的跳转
	/*public Object execute(){
		return "/pre/Index.jsp";
	}*/
	//想Json里面传对象
	public void write(Object obj,HttpServletResponse response){
		//设置返回 的 格式 字符集
		response.setContentType("text/html;charset=utf-8");
		//把对象传进来转换成json格式
		String json=JSONObject.toJSONString(obj);
		PrintWriter writer=null;
		if(null!=response){
			try {
				writer=response.getWriter();
				//向前台传输json对象
				writer.print(json);
				//刷新
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				//关流
				writer.close();
			}
		}
	}
}
