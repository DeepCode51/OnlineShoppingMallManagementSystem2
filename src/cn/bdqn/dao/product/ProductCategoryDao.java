package cn.bdqn.dao.product;

import java.util.List;

import cn.bdqn.entity.ProductCategory;
import cn.bdqn.params.ProductCategoryParam;

public interface ProductCategoryDao {
	//查询全部商品信息
	public List<ProductCategory> queryAllProductCategory(String parentId);
	//后台分页查询所有商品的分类
	public List<ProductCategory> queryProductCategorylist(ProductCategoryParam param);
	
	public Integer queryProductCategoryCount(ProductCategoryParam param);
	
	public ProductCategory queryProductCategoryById(Integer id);
	
	public void update(ProductCategory productCategory) ;
	//新增用户信息
	public Integer save(ProductCategory productCategory) ;
	
	public void deleteById(Integer id) throws Exception;
	
}
