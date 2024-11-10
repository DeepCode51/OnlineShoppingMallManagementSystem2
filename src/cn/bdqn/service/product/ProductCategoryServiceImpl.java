package cn.bdqn.service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.product.ProductCategoryDao;
import cn.bdqn.dao.product.ProductCategoryDaoImpl;
import cn.bdqn.dao.product.ProductDao;
import cn.bdqn.dao.product.ProductDaoImpl;
import cn.bdqn.entity.ProductCategory;
import cn.bdqn.params.ProductCategoryParam;
import cn.bdqn.util.DateSourceUtil;
import cn.bdqn.util.ProductCategoryVo;

public class ProductCategoryServiceImpl implements ProductCategoryService {
	//把数据库链接声明成一个属性
	private Connection connection;
	
	private ProductCategoryDao productCategory;
	
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		//一级分类的商品
		List<ProductCategory> pcList=new ArrayList<ProductCategory>();
		try {
			//创建一个数据库链接
			connection=DateSourceUtil.openConnection();
			productCategory=new ProductCategoryDaoImpl(connection);
			//查询全部商品分类
			if(null==parentId||"".equals(parentId)){
				parentId="0";
			}
			pcList=productCategory.queryAllProductCategory(parentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关流
			DateSourceUtil.closeConnection(connection);
		}
		return pcList;
	}
//一级二级三级显示列表
	@Override
	public List<ProductCategoryVo> queryAllProductCategory() {
		//查询一级分类的vo列表
		List<ProductCategoryVo> pc1VoList=new ArrayList<ProductCategoryVo>();
		//查询一级分类
		List<ProductCategory> pcList=queryAllProductCategory(null);
		//遍历一级分类   查询二级分类
		for(ProductCategory productCategory1:pcList){
			 
			ProductCategoryVo pc1Vo=new ProductCategoryVo();
			
			pc1Vo.setProductCategory(productCategory1);
			
			//查询二级分类的vo列表
			List<ProductCategoryVo> pc2VoList=new ArrayList<ProductCategoryVo>();
			//查询二级分类  根据查训出来的一级分类的ID 
			List<ProductCategory> pc2List=queryAllProductCategory(productCategory1.getId().toString());
			
			for(ProductCategory ProductCategory2:pc2List){
				
				ProductCategoryVo pc2Vo=new ProductCategoryVo();
				pc2Vo.setProductCategory(ProductCategory2);
				//查询3级分类的VO
				List<ProductCategoryVo> pc3VoList=new ArrayList<ProductCategoryVo>();
				//查询3级分类
				List<ProductCategory> pc3List=queryAllProductCategory(ProductCategory2.getId().toString());
				for(ProductCategory ProductCategory3:pc3List){
					ProductCategoryVo pc3Vo=new ProductCategoryVo();
					pc3Vo.setProductCategory(ProductCategory3);
					pc3VoList.add(pc3Vo);
				}
				pc2Vo.setProductCategoryVoList(pc3VoList);
				pc2VoList.add(pc2Vo);
				
			}
			pc1Vo.setProductCategoryVoList(pc2VoList);
			pc1VoList.add(pc1Vo);
		}
		return pc1VoList;
	}

	@Override
	public List<ProductCategory> queryProductCategoryList(
			ProductCategoryParam params) {
		 Connection connection = null;
	        List<ProductCategory> rtn = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(connection);
	            rtn = productCategoryDao.queryProductCategorylist(params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	        return rtn;
	    }

	@Override
	public int queryProductCategoryCount(ProductCategoryParam params) {
		 Connection connection = null;
	        int rtn = 0;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(connection);
	            rtn = productCategoryDao.queryProductCategoryCount(params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	        return rtn;
	}

	@Override
	public List<ProductCategory> queryProductCategorylistBySql(
			ProductCategoryParam params) {
		    Connection connection = null;
	        List<ProductCategory> rtn = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(connection);
	            rtn = productCategoryDao.queryProductCategorylist(params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	        return rtn;
	}

	@Override
	public ProductCategory getById(Integer id) {
		  Connection connection = null;
	        ProductCategory productCategory = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(connection);
	            productCategory =productCategoryDao.queryProductCategoryById(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	        return productCategory;
	}

	@Override
	public void modifyProductCategory(ProductCategory productCategory) {
		 Connection connection = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(connection);
	            productCategoryDao.update(productCategory);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	}

	@Override
	public void addProductCategory(ProductCategory productCategory) {
		Connection connection = null;
        try {
            connection = DateSourceUtil.openConnection();
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(connection);
            productCategoryDao.save(productCategory);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DateSourceUtil.closeConnection(connection);
        }
	}

	@Override
	public void deleteById(Integer id) {
		 Connection connection = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(connection);
	            productCategoryDao.deleteById(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	}
}
