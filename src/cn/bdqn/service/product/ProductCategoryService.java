package cn.bdqn.service.product;

import java.util.List;

import cn.bdqn.entity.ProductCategory;
import cn.bdqn.params.ProductCategoryParam;
import cn.bdqn.util.ProductCategoryVo;

public interface ProductCategoryService {
	
	//查询所有商品的分类信息
	public List<ProductCategory> queryAllProductCategory(String parentId);
	//查询全部商品信息
	public List<ProductCategoryVo> queryAllProductCategory();
	//后台查询所有商品分类
	 /**
     * 查询商品分类列表
     * @param params
     * @return
     */
    public List<ProductCategory> queryProductCategoryList(ProductCategoryParam params);
    /**
     * 查询数目
     * @param params
     * @return
     */
    public int queryProductCategoryCount(ProductCategoryParam params);
    /**
     * 根据sql查询商品分类
     * @param params
     * @return
     */
    public List<ProductCategory> queryProductCategorylistBySql(ProductCategoryParam params);
    /**
     * 根据id查询商品分类
     * @param id
     * @return
     */
    public ProductCategory getById(Integer id);
    /**
     * 修改商品分类
     * @param params
     */
    public void modifyProductCategory(ProductCategory productCategory);
    /**
     * 添加商品分类
     * @param params
     */
    public void addProductCategory(ProductCategory productCategory);
    /**
	 * 根据id删除商品
	 * @param id
	 */
	public void deleteById(Integer id);
}
