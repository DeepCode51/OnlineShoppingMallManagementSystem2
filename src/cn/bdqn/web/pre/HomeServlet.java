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

import cn.bdqn.entity.News;
import cn.bdqn.entity.ProductCategory;
import cn.bdqn.service.news.NewsService;
import cn.bdqn.service.news.NewsServiceImpl;
import cn.bdqn.service.product.ProductCategoryService;
import cn.bdqn.service.product.ProductCategoryServiceImpl;
import cn.bdqn.util.ProductCategoryVo;

@WebServlet(urlPatterns={"/HomeServlet"},name="HomeServlet")

public class HomeServlet extends AbstractServlet {
	
	private ProductCategoryService productCategoryService;
	
	private NewsService newsService;
	public HomeServlet() {
		super();
	}
	public void init ()throws ServletException{
		productCategoryService=new ProductCategoryServiceImpl();
		newsService=new NewsServiceImpl();
	}
	public String index(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		List<ProductCategoryVo> pcList=productCategoryService.queryAllProductCategory();
		List<News> newsList=newsService.queryAllNews();
		request.setAttribute("pcList",pcList);
		request.setAttribute("newsList", newsList);
		return "/pre/Index";
	}
	@Override
	public Class getServletClass() {
		//返回该类的实例
		return HomeServlet.class;
	}
	

}
