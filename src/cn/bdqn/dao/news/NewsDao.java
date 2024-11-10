package cn.bdqn.dao.news;

import java.util.List;

import cn.bdqn.entity.News;
import cn.bdqn.params.NewsParams;

public interface NewsDao {
	//查询新闻
	public List<News> queryAllNews() throws Exception;
	
	public List<News> queryNewsList(NewsParams params)throws Exception; 
	
	public Integer queryNewsCount(NewsParams params)throws Exception; 
	
	public News getNewsById(Integer id)throws Exception; 
	
}
