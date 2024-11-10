package cn.bdqn.util;

import java.util.List;

import cn.bdqn.entity.ProductCategory;

public class ProductCategoryVo{
	//商品分类属性
	private ProductCategory productCategory;
	//下级分类                                                   
	private List<ProductCategoryVo> productCategoryVoList;

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductCategoryVo> getProductCategoryVoList() {
		return productCategoryVoList;
	}

	public void setProductCategoryVoList(
			List<ProductCategoryVo> productCategoryVoList) {
		this.productCategoryVoList = productCategoryVoList;
	}
	
	
	
}
