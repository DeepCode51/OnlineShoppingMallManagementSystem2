package cn.bdqn.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.ProductCategory;
import cn.bdqn.params.ProductCategoryParam;
import cn.bdqn.util.EmptyUtils;

public class ProductCategoryDaoImpl extends BaseDao implements ProductCategoryDao {
	public ProductCategoryDaoImpl(Connection connection){
		super(connection);
	}
	//后台分页查询所有商品的分类
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		List<ProductCategory> list=new ArrayList<ProductCategory>();
		StringBuffer sql=new StringBuffer("select * from `easybuy_product_category` where 1=1");
		ResultSet rs=null;
		List<Object> params=new ArrayList<Object>();
		if(null!=parentId && !"".equals(parentId)){
			sql.append(" and parentId=?");
			params.add(parentId);
		}
		System.out.println(parentId);
		try {
			rs=this.executeQuery(sql.toString(), params.toArray());
			while(rs.next()){
				ProductCategory productCategory=new ProductCategory();
				productCategory.setId(rs.getInt("id"));
				productCategory.setIconClass(rs.getString("iconClass"));
				productCategory.setName(rs.getString("name"));
				productCategory.setParentId(rs.getInt("parentId"));
				productCategory.setType(rs.getInt("type"));
				list.add(productCategory);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return list;
	}
	
	//后台查询所有商品的分类
	@Override
	public List<ProductCategory> queryProductCategorylist(
			ProductCategoryParam param) {
		List<Object> paramsList=new ArrayList<Object>();   
		List<ProductCategory> productList=new ArrayList<ProductCategory>();
		StringBuffer sql=new StringBuffer("SELECT id,name,parentId,type,iconClass  FROM easybuy_product_category where 1=1 ");
		ResultSet resultSet=null;
		try {
			if(EmptyUtils.isNotEmpty(param.getName())){
				sql.append(" and name like ? ");
				paramsList.add("%"+param.getName()+"%");
			}
			if(EmptyUtils.isNotEmpty(param.getParentId())){
				sql.append(" and parentId = ? ");
				paramsList.add(param.getParentId());
			}
			if(EmptyUtils.isNotEmpty(param.getType())){
				sql.append(" and type = ? ");
				paramsList.add(param.getType());
			}
			if(param.isPage()){
				sql.append(" limit  " + param.getStartIndex() + "," + param.getPageSize());
			}
			resultSet=this.executeQuery(sql.toString(), paramsList.toArray());
			while (resultSet.next()) {
				ProductCategory productCategory = this.tableToClass(resultSet);
				productList.add(productCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return productList;
	}
	
	
	public ProductCategory mapToClass(Map map) throws Exception  {
		ProductCategory productCategory = new ProductCategory();
		Object idObject=map.get("id");
		Object nameObject=map.get("name");
		Object parentIdObject=map.get("parentId");
		Object typeObject=map.get("type");
		Object iconClassObject=map.get("iconClass");
		Object parentNameObject=map.get("parentName");
		productCategory.setId(EmptyUtils.isEmpty(idObject)?null:(Integer)idObject);
		productCategory.setName(EmptyUtils.isEmpty(nameObject)?null:(String)nameObject);
		productCategory.setParentId(EmptyUtils.isEmpty(parentIdObject)?null:(Integer)parentIdObject);
		productCategory.setType(EmptyUtils.isEmpty(typeObject)?null:(Integer)typeObject);
		productCategory.setIconClass(EmptyUtils.isEmpty(iconClassObject)?null:(String)iconClassObject);
		productCategory.setParentName(EmptyUtils.isEmpty(parentNameObject)?null:(String)parentNameObject);
		return productCategory;
	}
	
	
	@Override
	public Integer queryProductCategoryCount(ProductCategoryParam params) {
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count=0;
		StringBuffer sql=new StringBuffer("SELECT count(*) count FROM easybuy_product_category where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getName())){
			sql.append(" and name like ? ");
			paramsList.add("%"+params.getName()+"%");
		}
		if(EmptyUtils.isNotEmpty(params.getParentId())){
			sql.append(" and parentId = ? ");
			paramsList.add(params.getParentId());
		}
		ResultSet resultSet=this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			while (resultSet.next()) {
				count=resultSet.getInt("count");
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
	public ProductCategory queryProductCategoryById(Integer id) {
		List<Object> paramsList=new ArrayList<Object>();   
		ProductCategory productCategory=null;
		StringBuffer sql=new StringBuffer("SELECT id,name,parentId,type,iconClass  FROM easybuy_product_category where id = ? ");
		ResultSet resultSet=this.executeQuery(sql.toString(),new Object[]{id});
		try {
			while (resultSet.next()) {
				productCategory = this.tableToClass(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return productCategory;
	}
	
	public ProductCategory tableToClass(ResultSet rs) throws Exception {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(rs.getInt("id"));
		productCategory.setName(rs.getString("name"));
		productCategory.setParentId(rs.getInt("parentId"));
		productCategory.setType(rs.getInt("type"));
		productCategory.setIconClass(rs.getString("iconClass"));
		return productCategory;
	}
	@Override
	public void update(ProductCategory productCategory) {
		try {
        	Object[] params = new Object[] {productCategory.getName(),productCategory.getParentId(),productCategory.getType(),productCategory.getIconClass(),productCategory.getId()};
        	String sql = " UPDATE easybuy_product_category SET name=?,parentId=?,type=?,iconClass=? WHERE id =?  ";
    		this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResoure();
        }		
	}
	
	//新增用户信息
	@Override
	public Integer save(ProductCategory productCategory) {
		Integer id=0;
    	try {
    		String sql=" INSERT into easybuy_product_category(name,parentId,type,iconClass) values(?,?,?,?) ";
            Object param[]=new Object[]{productCategory.getName(),productCategory.getParentId(),productCategory.getType(),productCategory.getIconClass()};
            id=this.executeInsert(sql,param);
            productCategory.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResoure();
        }
    	return id;
	}
	@Override
	public void deleteById(Integer id) throws Exception {
		String sql = " delete from easybuy_product where id = ? ";
		Object params[] = new Object[] { id };
		try{
			this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
	}
	
}