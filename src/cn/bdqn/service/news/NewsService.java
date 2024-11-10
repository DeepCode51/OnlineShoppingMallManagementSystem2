package cn.bdqn.service.news;

import java.util.List;

import cn.bdqn.entity.News;
import cn.bdqn.params.NewsParams;
import cn.bdqn.util.Pager;

public interface NewsService {
		//查询新闻
		public List<News> queryAllNews() throws Exception;
		
		/**
		 * 查询所有的新闻
		 * @param pager
		 * @return
		 */
		List<News> getAllNews(Pager pager);//获取当页新闻
		
		/***
		 * 查询数目
		 * @param param
		 * @return
		 */
		Integer queryNewsCount(NewsParams param);
		/**
		 * 根据id查询新闻
		 * @param parameter
		 * @return
		 */
		News findNewsById(String parameter);//根据ID获取新闻

}
