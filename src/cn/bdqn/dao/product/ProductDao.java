package cn.bdqn.dao.product;

import java.sql.SQLException;
import java.util.List;

import cn.bdqn.entity.Product;
import cn.bdqn.params.ProductParam;

public interface ProductDao {
	//根据条件查询商品列表
	public List<Product> queryProductList(ProductParam params) throws SQLException;
	//根据条件查询商品数量
	public int queryProductCont(ProductParam params)throws SQLException;
	//根据ID查询商品
	public Product getProductById(Integer id) throws Exception;
	//库存的更新
	public void updateStock(Integer id, Integer quantity);
	
	public Integer queryProductCount(ProductParam params)throws Exception; 
	
	public void save(Product product) throws Exception;

	public void update(Product product) throws Exception;
	
	public void deleteById(Integer id) throws Exception;
	
}
