package cn.bdqn.service.product;

import java.sql.SQLException;
import java.util.List;

import cn.bdqn.entity.Product;
import cn.bdqn.params.ProductParam;

public interface ProductService {
		//根据条件查询商品列表
		public List<Product> queryProductList(ProductParam params);
		//根据条件查询商品数量
		public int queryProductCont(ProductParam params);
		//根据ID查询商品
		public Product findById(String id) ;
		
		public int getProductCountBycategory(Integer categoryId);
		/**
		 * 根据分类查询商品数目
		 * @param categoryId
		 * @param level
		 * @return
		 */
		int getProductRowCount(String categoryId,int level,String keyWord);
		
		/**
		 * 查询商品数目
		 * @param params
		 * @return
		 */
		 public int getProductRowCount(ProductParam params);
		 
		 /**
			 * 查询商品列表
			 * @param params
			 * @return
			 */
			List<Product> queryProductsList(ProductParam params);
			/**
			 * 保存商品返回id
			 * @param product
			 * @return
			 */
			Integer saveOrUpdate(Product product);//保存一款商品
			/**
			 * 根据id删除商品
			 * @param id
			 */
			public void deleteById(Integer id);
}
