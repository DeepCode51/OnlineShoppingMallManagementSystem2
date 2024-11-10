package cn.bdqn.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bdqn.entity.Order;
import cn.bdqn.entity.Product;
import cn.bdqn.entity.User;
import cn.bdqn.entity.UserAddress;
import cn.bdqn.service.order.CartService;
import cn.bdqn.service.order.CartServiceImpl;
import cn.bdqn.service.order.OrderService;
import cn.bdqn.service.order.OrderServiceImpl;
import cn.bdqn.service.order.UserAddressService;
import cn.bdqn.service.order.UserAddressServiceImpl;
import cn.bdqn.service.product.ProductCategoryService;
import cn.bdqn.service.product.ProductCategoryServiceImpl;
import cn.bdqn.service.product.ProductService;
import cn.bdqn.service.product.ProductServiceImpl;
import cn.bdqn.util.Constants;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.ProductCategoryVo;
import cn.bdqn.util.ReturnResult;
import cn.bdqn.util.ShoppingCart;
import cn.bdqn.util.ShoppingCartItem;
@WebServlet(urlPatterns={"/CartServlet"},name = "CartServlet")
public class CartServlet extends AbstractServlet {

	private ProductService productService;
	private ProductCategoryService productCategoryService;
	private CartService cartService;
	private UserAddressService userAddressService;
	private OrderService orderService;
	
	public CartServlet() {
		super();
	}
	//添加购物车
	public ReturnResult add(HttpServletRequest request, HttpServletResponse response){
		ReturnResult result=new ReturnResult();
		//获取前台传过来的商品的ID
		String id=request.getParameter("entityId");
		//获取前台传过来的数量
		String quantityStr=request.getParameter("quantity");
		
		Integer quantity=1;
	//如果数量不为空
		if(EmptyUtils.isEmpty(quantityStr)){
			quantity=Integer.parseInt(quantityStr);
		}
		//根据前台传过来的id 查询对应的商品
		Product product=productService.findById(id);
		if(product.getStock()<quantity){
			return result.returnFail("商品库存不足");
		}
		//获取购物车
		ShoppingCart cart=getCartFromSession(request);
		//往购物车放商品
		result=cart.addItem(product, quantity);
		if(result.getStatus()==Constants.ReturnResult.SUCCESS){
			cart.setSum(EmptyUtils.isEmpty(cart.getSum()) ? 0.0 : cart.getSum()+(product.getPrice()*quantity*1.0));
		}
		return result;
	}


	public void init() throws ServletException {
		productService=new ProductServiceImpl();
		productCategoryService=new ProductCategoryServiceImpl();
		userAddressService=new UserAddressServiceImpl();
		cartService=new CartServiceImpl();
		orderService=new OrderServiceImpl();
	}

	@Override
	public Class getServletClass() {
		
		return CartServlet.class;
	}
	 //从session中获取购物车信息
	private ShoppingCart getCartFromSession(HttpServletRequest request){
		HttpSession session=request.getSession();
		ShoppingCart cart=(ShoppingCart)session.getAttribute("cart");
		if(EmptyUtils.isEmpty(cart)){
			cart=new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	public String refreshCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
        ShoppingCart cart = getCartFromSession(request);
        //cart = cartService.calculate(cart);
        session.setAttribute("cart", cart);
		return "/common/pre/searchBar";
	}
	//去结算功能
	public String toSettlement(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ProductCategoryVo> pcList=productCategoryService.queryAllProductCategory();
		request.setAttribute("pcList", pcList);
		return "/pre/settlement/toSettlement";
	}
	
	public String settlement1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return "/pre/settlement/settlement1";
	}
	
	 private User getUserFromSession(HttpServletRequest request) {
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("loginUser");
	        return user;
	    }
	//获取用户地址
	 public String settlement2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 	User user = getUserFromSession(request);
		 	List<ProductCategoryVo> pcList=productCategoryService.queryAllProductCategory();
			request.setAttribute("pcList", pcList);
		 	List<UserAddress> userAddressList = userAddressService.queryUserAddressList(user.getId());
	        request.setAttribute("userAddressList", userAddressList);
	        return "/pre/settlement/settlement22";
	}
	 //订单生成
	 public Object settlement3(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 ShoppingCart cart=getCartFromSession(request);
		 //对购物车中商品的库存进行核对
		 ReturnResult result=checkCart(request);
		 if(result.getStatus()==Constants.ReturnResult.FAIL){
			 return result;
		 }
		 User user=getUserFromSession(request);
		 //新增地址
		 String addressId=request.getParameter("addressId");
		 String newAddress=request.getParameter("newAddress");
		 String newRemark=request.getParameter("newRemark");
		 
		 UserAddress userAddress=new UserAddress();
		 if("-1".equals(addressId)){
			 userAddress.setAddress(newAddress);
			 userAddress.setRemark(newRemark);
			 userAddressService.addUserAddress(user.getId(), newAddress, newRemark);
		 }else{
			 userAddress=userAddressService.getUserAddressById(Integer.parseInt(addressId));
		 }
		 //生成订单
		 Order order=orderService.payShoppingCart(user, userAddress.getAddress(), cart);
		 request.setAttribute("order", order);
		 clearCart(request,response);
		 return "/pre/settlement/settlement3";
	 }
	 
	 //清空购物车
	 /**
	     * 清空购物车
	     *
	     * @param request
	     * @param response
	     */
	    public ReturnResult clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        ReturnResult result = new ReturnResult();
	        //结账后清空购物车
	        request.getSession().removeAttribute("cart");
	        result.returnSuccess(null);
	        return result;
	    }
	    
	//对购物车中商品的库存进行核对
	 public ReturnResult checkCart(HttpServletRequest request) throws Exception{
		 ReturnResult result=new ReturnResult();
		 HttpSession session=request.getSession();
		 //从session中获得购物车
		 ShoppingCart cart=getCartFromSession(request);
		 for(ShoppingCartItem item:cart.getItems()){
			 Product product=productService.findById(item.getProduct().getId()+"");
			 if(product.getStock()<item.getQuantity()){
				 return result.returnFail(product.getName()+"商品数量不足");
			 }
		 }
		 return result;
	 }
	 
	//修改购物车的方法
	public ReturnResult modifyCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ReturnResult result=new ReturnResult();
		HttpSession session=request.getSession();
		String id=request.getParameter("entityId");
		String quantityStr=request.getParameter("quantity");
		if(EmptyUtils.isEmpty(id)||EmptyUtils.isEmpty(quantityStr)){
			return result.returnFail("参数不能为空");
		}
		Integer quantity=Integer.parseInt(quantityStr);
		ShoppingCart cart=getCartFromSession(request);
		Product product=productService.findById(id);
		if(quantity>product.getStock()){
			return result.returnFail("商品数量不足");
		}
		//修改购物车
		cart=cartService.modifyShoppingCart(id, quantity, cart);
		session.setAttribute("cart", cart);
		return result.returnSuccess();
	}
	
	
}
