package cn.bdqn.service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.product.ProductDao;
import cn.bdqn.dao.product.ProductDaoImpl;
import cn.bdqn.entity.Product;
import cn.bdqn.params.ProductParam;
import cn.bdqn.util.DateSourceUtil;
import cn.bdqn.util.EmptyUtils;

public class ProductServiceImpl implements ProductService {
	
	 private Connection connection;
	 
	 private ProductDao productDao;
	 
	//根据条件查询商品列表
	@Override
	public List<Product> queryProductList(ProductParam params) {
		//商品列表集合
		List<Product> pList=new ArrayList<Product>();
		try {
			connection=DateSourceUtil.openConnection();
			productDao=new ProductDaoImpl(connection);
			pList=productDao.queryProductList(params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			DateSourceUtil.closeConnection(connection);
		}
		return pList;
	}
	//根据条件查询商品数量
	@Override
	public int queryProductCont(ProductParam params) {
		Integer num=0;
		try {
			connection=DateSourceUtil.openConnection();
			productDao=new ProductDaoImpl(connection);
			num=productDao.queryProductCont(params);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}finally{
			DateSourceUtil.closeConnection(connection);
		}
		return num;
	}
	//根据ID查询商品
	 public Product findById(String id) {//根据ID查询商品
	        Connection connection = null;
	        Product product = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductDao productDao = new ProductDaoImpl(connection);
	            product = productDao.getProductById(Integer.parseInt(id));
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	DateSourceUtil.closeConnection(connection);
	        }
	        return product;
	    }
	@Override
	public int getProductCountBycategory(Integer categoryId) {
		Connection connection = null;
        int count = 0;
        try {
            connection = DateSourceUtil.openConnection();
            ProductDao productDao = new ProductDaoImpl(connection);
            ProductParam param=new ProductParam();
            param.setCategoryLevel1Id(categoryId);
            count = productDao.queryProductCount(param);
            if(count>0) return count;
            param.setCategoryLevel2Id(categoryId);
            count = productDao.queryProductCount(param);
            if(count>0) return count;
            param.setCategoryLevel3Id(categoryId);
            count = productDao.queryProductCount(param);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DateSourceUtil.closeConnection(connection);
        }
		return count;
	}
	@Override
	 public int getProductRowCount(ProductParam params) {
        Connection connection = null;
        int count = 0;
        try {
            connection = DateSourceUtil.openConnection();
            ProductDao productDao = new ProductDaoImpl(connection);
            count = productDao.queryProductCount(params);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	DateSourceUtil.closeConnection(connection);
        }
        return count;
    }
	@Override
	 public int getProductRowCount(String categoryId, int level,String keyWord) {//查询商品记录数
        Connection connection = null;
        int rtn = 0;
        try {
            connection = DateSourceUtil.openConnection();
            ProductDao productDao = new ProductDaoImpl(connection);
            ProductParam params = new ProductParam();
            Long id = null;
            if (EmptyUtils.isNotEmpty(categoryId)) {
                id = Long.parseLong(categoryId);
                params.setCategoryId(id==0L?null:id.intValue());
            }
            if(!EmptyUtils.isEmpty(keyWord)){
            	params.setKeyword(keyWord);
            }
            rtn = productDao.queryProductCount(params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DateSourceUtil.closeConnection(connection);
        }
        return rtn;
    }
	@Override
	public List<Product> queryProductsList(ProductParam params) {
		 Connection connection = null;
	        List<Product> rtn = new ArrayList<Product>();
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductDao productDao = new ProductDaoImpl(connection);
	            //设置查询参数
	            rtn = productDao.queryProductList(params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	        return rtn;
	}
	@Override
	public Integer saveOrUpdate(Product product) {
		  Connection connection = null;
	        try {
	            connection = DateSourceUtil.openConnection();
	            ProductDao productDao = new ProductDaoImpl(connection);
	            ProductParam params = new ProductParam();
	            if(EmptyUtils.isEmpty(product.getId())){
	                productDao.save(product);
	            } else {
	                productDao.update(product);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DateSourceUtil.closeConnection(connection);
	        }
	        return null;
	}
	@Override
	public void deleteById(Integer id) {
	    Connection connection = null;
        try {
            connection = DateSourceUtil.openConnection();
            ProductDao productDao = new ProductDaoImpl(connection);
            //设置查询参数
            productDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DateSourceUtil.closeConnection(connection);
        }
	}

}
