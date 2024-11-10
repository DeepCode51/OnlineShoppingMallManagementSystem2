package cn.bdqn.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.Product;
import cn.bdqn.params.ProductParam;
import cn.bdqn.service.product.ProductCategoryService;
import cn.bdqn.service.product.ProductCategoryServiceImpl;
import cn.bdqn.service.product.ProductService;
import cn.bdqn.service.product.ProductServiceImpl;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.Pager;
import cn.bdqn.util.ProductCategoryVo;
@WebServlet(urlPatterns={"/ProductServlet"},name="ProductServlet")
public class ProductServlet extends AbstractServlet {
	//商品列表业务层
	private ProductService productService;
	//商品分类业务层
	private ProductCategoryService productCategoryService;
	@Override
	public Class getServletClass() {
		return ProductServlet.class;
	}
	//初始化
	public void init() throws ServletException{
		productService=new ProductServiceImpl();
		productCategoryService=new ProductCategoryServiceImpl();
	}
	 
	public String queryProductList(HttpServletRequest request, HttpServletResponse response){
		//接收前台传过来的类别参数
		String categoryId=request.getParameter("categoryId");
		//接收前台传过来的关键字
		String keyWord=request.getParameter("keyWord");
		//接收前台传过来的当前页
		String currentPagrstr=request.getParameter("currentPagrstr");
		//每页显示多少
		String pageSizeStr=request.getParameter("pageSizeStr");
		//把每页显示多少转换成int类型
		int rowPerpage=EmptyUtils.isNotEmpty(pageSizeStr) ? Integer.parseInt(pageSizeStr) : 8;
		//获取当前页转换成int类型
		int currentPage=EmptyUtils.isNotEmpty(currentPagrstr) ? Integer.parseInt(currentPagrstr) : 1;
		
		ProductParam params=new ProductParam ();
		//所属分类ID
		params.setCategoryId(EmptyUtils.isNotEmpty(categoryId) ? Integer.parseInt(categoryId) : null);
		//接收前台关键字并赋值
		params.setKeyword(keyWord);
		//设置分页 
		params.openPage((currentPage-1)*rowPerpage, rowPerpage);
		//查询出来的总条数
		int total=productService.queryProductCont(params);
		//
		Pager pager=new Pager(total,rowPerpage,currentPage);
		pager.setUrl("/ProductServlet?action=queryProductList&categoryId="+(EmptyUtils.isNotEmpty(categoryId) ? (categoryId) : null)+"&keyWord="+(EmptyUtils.isNotEmpty(keyWord) ? (keyWord) : null));
		//二级分类的列表
		List<ProductCategoryVo> productCategoryVo=productCategoryService.queryAllProductCategory();
		List<Product> productList=productService.queryProductList(params);
		//商品列表
    	request.setAttribute("pList", productList);
    	//分页
    	request.setAttribute("pager", pager);
    	//总数量
    	request.setAttribute("total", total);
    	//关键字
    	request.setAttribute("keyWord", keyWord);
    	//二级分类列表
    	request.setAttribute("pcList", productCategoryVo);
		return "/pre/product/productList";
	}
	
	//查询商品详情
	public String queryProductDeatil(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("id");
        Product product = productService.findById(id);
        List<ProductCategoryVo> productCategoryVoList = productCategoryService.queryAllProductCategory();
        request.setAttribute("product", product);
        request.setAttribute("pcList", productCategoryVoList);
        return "/pre/product/productDeatil";
    }

}
