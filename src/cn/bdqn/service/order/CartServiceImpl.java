package cn.bdqn.service.order;

import cn.bdqn.util.ShoppingCart;
import cn.bdqn.util.ShoppingCartItem;

public class CartServiceImpl implements CartService{
	//修改购物车的方法
	@Override
	public ShoppingCart modifyShoppingCart(String productId, Integer quantity,
			ShoppingCart cart) throws Exception {
		for(ShoppingCartItem item :cart.getItems()) {
			if(item.getProduct().getId().toString().equals(productId)){
				if(quantity==0 || quantity<0){
					cart.getItems().remove(item);
					break;
				}else{
					item.setQuantity(quantity);
				}
			}
		}
		//商品总金额的重新计算
		cart=calculate(cart);
		return cart;
	}
	//商品总金额的重新计算
	@Override
	public ShoppingCart calculate(ShoppingCart cart) throws Exception {
		double sum=0.0;
		for(ShoppingCartItem item :cart.getItems()) {
			
			sum+=item.getQuantity() * item.getProduct().getPrice();
			
			item.setCost(item.getQuantity()*item.getProduct().getPrice());
		}
		cart.setSum(sum);
		return cart;
	}

}
