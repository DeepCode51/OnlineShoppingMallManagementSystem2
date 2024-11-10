package cn.bdqn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import cn.bdqn.util.DateSourceUtil;
import cn.bdqn.util.EmptyUtils;

public class BaseDao {
	private Connection connection;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public BaseDao(Connection connection){
		this.connection=connection;
	}
	//查询
	public ResultSet executeQuery(String sql,Object[] params){
		ResultSet rs=null;
		try {
			pstm=connection.prepareStatement(sql);
			if(!EmptyUtils.isEmpty(params)){
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1, params[i]);
				}
			}
			rs=pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//
	public boolean closeResoure(){
		boolean flag=true;
		try {
			if(null!=rs){
				rs.close();
			}
			if(null!=pstm){
				pstm.close();
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
		return flag;
	}
	//注册 新增
	public int executeInsert(String sql,Object[] params){
		Long id=0L;
		try {
			pstm=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//为参数赋值
			for(int i=0;i<params.length;i++){
				pstm.setObject(i+1, params[i]);
			}
			pstm.executeUpdate();
			//获取主键的值的对象
			ResultSet rs=pstm.getGeneratedKeys();
			if(rs.next()){
				//获取单个主键值
				id=rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			id=null;
		}
		return id.intValue();
	}
	
	// 修改 删除 和新增
	public int executeUpdate(String sql,Object[] params){
		int updateRows=0;
		
		try {
			pstm=connection.prepareStatement(sql);
			//赋值
			for(int i=0;i<params.length;i++){
				pstm.setObject(i+1, params[i]);
			}
			updateRows=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			updateRows=-1;
		}
		return updateRows;
	}
}
