package cn.bdqn.service.order;

import cn.bdqn.util.ShoppingCart;

public interface CartService {
	//修改购物车的方法
	public ShoppingCart modifyShoppingCart(String productId,Integer quantity,ShoppingCart cart) throws Exception;
	//商品总金额的重新计算
	public ShoppingCart calculate(ShoppingCart cart) throws Exception;
}
