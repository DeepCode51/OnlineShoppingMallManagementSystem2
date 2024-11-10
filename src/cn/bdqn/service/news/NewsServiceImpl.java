package cn.bdqn.service.news;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.news.NewDaoImpl;
import cn.bdqn.dao.news.NewsDao;
import cn.bdqn.entity.News;
import cn.bdqn.params.NewsParams;
import cn.bdqn.util.DateSourceUtil;
import cn.bdqn.util.Pager;

public class NewsServiceImpl implements NewsService {
	private Connection connection;
	private NewsDao newsDao;
	//查询新闻
	@Override
	public List<News> queryAllNews(){
		
		List<News> list= new ArrayList<News>();
		try {
			
			connection=DateSourceUtil.openConnection();
			newsDao=new NewDaoImpl(connection);
			list=newsDao.queryAllNews();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DateSourceUtil.closeConnection(connection);
		}
		return list;
	}
	@Override
	public List<News> getAllNews(Pager pager) {
		List<News> rtn = new ArrayList<News>();
		Connection connection=null;
		try {
			connection=DateSourceUtil.openConnection();
			NewsDao newsDao = new NewDaoImpl(DateSourceUtil.openConnection());
			NewsParams params = new NewsParams();
			params.openPage((pager.getCurrentPage() - 1) * pager.getRowPerPage(),pager.getRowPerPage());
			rtn=newsDao.queryNewsList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			DateSourceUtil.closeConnection(connection);
		}
		return rtn;
	}
	@Override
	public Integer queryNewsCount(NewsParams param) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DateSourceUtil.openConnection();
			NewsDao newsDao = new NewDaoImpl(connection);
			count=newsDao.queryNewsCount(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
			return count;
		}
	}
	@Override
	public News findNewsById(String parameter) {
		News news = null;
		Connection connection=null;
		try {
			connection=DateSourceUtil.openConnection();
			NewsDao newsDao = new NewDaoImpl(connection);
			news = newsDao.getNewsById(Integer.parseInt(parameter));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DateSourceUtil.closeConnection(connection);
		}
		return news;
	}
	
}
