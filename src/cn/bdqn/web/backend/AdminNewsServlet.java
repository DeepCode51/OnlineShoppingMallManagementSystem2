package cn.bdqn.web.backend;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.News;
import cn.bdqn.params.NewsParams;
import cn.bdqn.service.news.NewsService;
import cn.bdqn.service.product.ProductService;
import cn.bdqn.service.news.NewsServiceImpl;
import cn.bdqn.service.product.ProductServiceImpl;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.Pager;
import cn.bdqn.web.pre.AbstractServlet;

import java.util.List;

@WebServlet(urlPatterns = {"/admin/news"},name = "adminNews")
public class AdminNewsServlet extends AbstractServlet {

	private NewsService newsService;
	
	private ProductService productService;

	public void init() throws ServletException {
		this.newsService = new NewsServiceImpl();
		this.productService = new ProductServiceImpl();
	}

	@Override
	public Class getServletClass() {
		return AdminNewsServlet.class;
	}

	/**
	 * 查询新闻列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String queryNewsList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		//获取页大小
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		int total=newsService.queryNewsCount(new NewsParams());
		Pager pager=new Pager(total,rowPerPage,currentPage);
		pager.setUrl("/admin/news?action=queryNewsList");
		List<News> newsList = newsService.getAllNews(pager);
		request.setAttribute("newsList", newsList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 7);
		return "/backend/news/newsList";
	}
	/**
	 * 查询新闻详情
	 * @param request
	 * @param response
	 * @return
	 */
	public String newsDeatil(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id = request.getParameter("id");
		News news=newsService.findNewsById(id);
		request.setAttribute("news",news);
		request.setAttribute("menu", 7);
		return "/backend/news/newsDetail";
	}

}
