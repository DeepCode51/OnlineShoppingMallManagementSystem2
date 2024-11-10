package cn.bdqn.util;

import java.io.Serializable;

import cn.bdqn.entity.Product;

//购物车里面存放的物品
public class ShoppingCartItem implements Serializable{
	//商品
	private Product product;
	//数量
	private Integer quantity;
	//价格
	private float cost;
	
	
	//构造方法
	public ShoppingCartItem(Product product,Integer quantity) {
		//商品
		this.product = product;
		//商品数量
		this.quantity = quantity;
		//商品总价等于 商品单件价格*商品数量
		this.cost = product.getPrice() * quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
}
