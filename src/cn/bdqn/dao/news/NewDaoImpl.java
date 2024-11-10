package cn.bdqn.dao.news;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.News;
import cn.bdqn.params.NewsParams;
import cn.bdqn.util.EmptyUtils;

public class NewDaoImpl extends BaseDao implements NewsDao {
	
	public NewDaoImpl(Connection connection) {
		super(connection);
	}
	 public News tableToClass(ResultSet rs) throws Exception{
		News news = new News();
		
		news.setId(rs.getInt("id"));
		news.setTitle(rs.getString("title"));
		news.setContent(rs.getString("content"));
		news.setCreateTime(rs.getDate("createTime"));	
		return news;
	 }
	//查询新闻
	@Override
	public List<News> queryAllNews() throws Exception {
		List<News> list=new ArrayList<News>();
		StringBuffer sql=new StringBuffer("select id,title,content,createTime from easybuy_news ");
		sql.append(" limit 0,5");
		ResultSet rs=this.executeQuery(sql.toString(),null);
		while(rs.next()){
			list.add(tableToClass(rs));
		}
		return list;
	}
	@Override
	public List<News> queryNewsList(NewsParams params) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		List<News> newsList=new ArrayList<News>();
		StringBuffer sql=new StringBuffer(" select id,title,content,createTime FROM easybuy_news where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getTitle())){
			sql.append(" and title like ? ");
			paramsList.add(params.getTitle());
		}
		if(EmptyUtils.isNotEmpty(params.getSort())){
			sql.append(" order by " + params.getSort()+" ");
		}
		if(params.isPage()){
			sql.append(" limit  " + params.getStartIndex() + "," + params.getPageSize());
		}
		
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				News news = this.tableToClass(resultSet);
				newsList.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
				
		return newsList;
	}
	@Override
	public Integer queryNewsCount(NewsParams params) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count = 0;
		StringBuffer sql=new StringBuffer(" select count(*) as count FROM easybuy_news where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getTitle())){
			sql.append(" and title like ? ");
			paramsList.add(params.getTitle());
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return count;
	}
	@Override
	public News getNewsById(Integer id) throws Exception {
		String sql = " select * from easybuy_news where id = ? ";
		ResultSet resultSet = null;
		News news = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				news = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResoure();
			return news;
		}
	}
}
