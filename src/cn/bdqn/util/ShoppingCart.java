package cn.bdqn.util;

import java.util.ArrayList;
import java.util.List;

import cn.bdqn.entity.Product;



//购物车类
public class ShoppingCart {
	//购物车商品的集合
	private List<ShoppingCartItem> items=new ArrayList<ShoppingCartItem>();
	//总金额
	private double sum;
	
	/**
	 * @return the items
	 */
	public List<ShoppingCartItem> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}
	/**
	 * @return the sum
	 */
	public double getSum() {
		return sum;
	}
	/**
	 * @param sum the sum to set
	 */
	public void setSum(double sum) {
		this.sum = sum;
	}
	//添加到购物车 两个参数 要添加的商品  和商品的数量
	public ReturnResult addItem(Product product,Integer quantity){
		ReturnResult result=new ReturnResult();
		boolean flag=false;
		//判断购物车中是否已经由此商品
			for(int i=0;i<items.size();i++){
				//如果购物车里面的第i个 的商品的ID 与  保存的商品ID一样
				if(items.get(i).getProduct().getId().equals(product.getId())){
					//已经有的商品和新添加的商品之和 是否大于 商品的库存
					if(items.get(i).getQuantity()+quantity>product.getStock()){
						return result.returnFail("商品库存不足");
					}else{
						items.get(i).setQuantity(items.get(i).getQuantity()+quantity);
						flag=true;
					}
				}
			}
		
			if(!flag){
				//添加到购物车
				items.add(new ShoppingCartItem(product,quantity));
		               
		}
		return result.returnSuccess();
	}
	//移除一项购物车的一项     结算功能的删除按钮
	public void removeItems(int index){
		items.remove(index);
	}
	
	//修改数量
	public void modifyQuantity(int index,Integer quantity){
		items.get(index).setQuantity(quantity);
	}
	
	//计算总价格
		public float getTotalCost() {
			float sum = 0;
			for (ShoppingCartItem item : items) {
				sum = sum + item.getCost();
			}
			return sum;
		}

}
