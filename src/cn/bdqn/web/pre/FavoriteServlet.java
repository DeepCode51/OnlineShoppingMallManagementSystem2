package cn.bdqn.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bdqn.entity.Product;
import cn.bdqn.entity.User;
import cn.bdqn.service.product.ProductService;
import cn.bdqn.service.product.ProductServiceImpl;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.MemcachedUtils;
import cn.bdqn.util.ReturnResult;
@WebServlet(urlPatterns={"/FavoriteServlet"},name="FavoriteServlet")
public class FavoriteServlet extends AbstractServlet {
	private ProductService productService;
	//收藏功能
	public ReturnResult addFavorite(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("id");
		Product product=productService.findById(id);
		List<Product> favoriteList=queryFavoriteList(request);
		//判断是否满了
		if(favoriteList.size()>0 && favoriteList.size()==5){
			favoriteList.remove(0);
		}
		boolean flag=false;
		for(int i=0;i<favoriteList.size();i++){
			if(favoriteList.get(i).getId().equals(product.getId())){
				flag=true;
				break;
			}
		}
		if(!flag){
			favoriteList.add(favoriteList.size(),product);
		}
		String key=getFavoriteKey(request);
		MemcachedUtils.add(key, favoriteList);
		return result.returnSuccess();
	}
	
	//	从Memcached里面获取已经收藏的Product
	public List<Product> queryFavoriteList(HttpServletRequest request) throws Exception{
		String key=getFavoriteKey(request);
		List<Product> recentProducts = (List<Product>) MemcachedUtils.get(key);
		if(EmptyUtils.isEmpty(recentProducts)){
			recentProducts=new ArrayList<Product>()	;
		}
		
		return recentProducts;
	}
	
	//	从Memcached里面获取已经收藏的key的值
	public String getFavoriteKey(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("loginUser");
		//判断用户是否登入
		String key=EmptyUtils.isEmpty(user) ? session.getId() : user.getLoginName();
		return key;
	}
	
	//收藏后返回一小块显示
	public String favoriteList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Product> favoriteList=queryFavoriteList(request);
		request.setAttribute("favoriteList", favoriteList);
		return "/pre/product/favoriteList";
	}
	
	public void init() throws ServletException {
		productService=new ProductServiceImpl();
	}


	@Override
	public Class getServletClass() {
		
		return FavoriteServlet.class;
	}

}
