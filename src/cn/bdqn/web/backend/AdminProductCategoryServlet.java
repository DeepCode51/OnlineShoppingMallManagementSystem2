package cn.bdqn.web.backend;
import cn.bdqn.entity.ProductCategory;
import cn.bdqn.params.ProductCategoryParam;
import cn.bdqn.service.product.ProductCategoryService;
import cn.bdqn.service.product.ProductCategoryServiceImpl;
import cn.bdqn.service.product.ProductService;
import cn.bdqn.service.product.ProductServiceImpl;
import cn.bdqn.util.Constants;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.Pager;
//import cn.bdqn.util.Params;
import cn.bdqn.util.ReturnResult;
import cn.bdqn.web.pre.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * Created by bdqn on 2016/5/8.
 */
@WebServlet(urlPatterns = { "/admin/productCategory" }, name = "adminProductCategory")
public class AdminProductCategoryServlet extends AbstractServlet{

    private ProductCategoryService productCategoryService;
    
    private ProductService productService;

    public void init() throws ServletException {
        this.productCategoryService = new ProductCategoryServiceImpl();
        this.productService=new ProductServiceImpl();
    }
    /**
     * 订单的主页面
     * @param request
     * @param response
     * @return
     */
    public String index(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //获取当前页数
        String currentPageStr = request.getParameter("currentPage");
        //获取页大小
        String pageSize = request.getParameter("pageSize");
        int rowPerPage  = EmptyUtils.isEmpty(pageSize)?8:Integer.parseInt(pageSize);
        int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
        ProductCategoryParam params =new ProductCategoryParam();
        int total=productCategoryService.queryProductCategoryCount(params);
        Pager pager=new Pager(total,rowPerPage,currentPage);
        params.openPage((pager.getCurrentPage()-1)*pager.getRowPerPage(),pager.getRowPerPage());
        pager.setUrl("/admin/productCategory?action=index");
        List<ProductCategory> productCategoryList=productCategoryService.queryProductCategorylistBySql(params);
        request.setAttribute("productCategoryList", productCategoryList);
        request.setAttribute("pager", pager);
        request.setAttribute("menu", 4);
        return "/backend/productCategory/productCategoryList";
    }
    /**
     * 添加商品分类
     * @param request
     * @param response
     * @return
     */
    public String toAddProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //查询一级分类 全部
        List<ProductCategory> productCategoryList=null;
        ProductCategoryParam params =new ProductCategoryParam();
        params.setType(1);
        productCategoryList=productCategoryService.queryProductCategoryList(params);
        request.setAttribute("productCategoryList1",productCategoryList);
        request.setAttribute("productCategory",new ProductCategory());
        return "/backend/productCategory/toAddProductCategory";
    }
    /**
     * 修改商品分类
     * @param request
     * @param response
     * @return
     */
    public String toUpdateProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String id=request.getParameter("id");
        ProductCategory productCategory=productCategoryService.getById(Integer.parseInt(id));
        List<ProductCategory> productCategoryList1=null;
        List<ProductCategory> productCategoryList2=null;
        List<ProductCategory> productCategoryList3=null;
        request.setAttribute("productCategory",productCategory);
        //判断分类级别
        if(productCategory.getType()>=1){
        	ProductCategoryParam params =new ProductCategoryParam();
        	params.setType(1);
            productCategoryList1=productCategoryService.queryProductCategoryList(params);
        }
        if(productCategory.getType()>=2){
        	ProductCategoryParam params =new ProductCategoryParam();
        	params.setType(2);
            productCategoryList2=productCategoryService.queryProductCategoryList(params);
            request.setAttribute("parentProductCategory",productCategoryService.getById(productCategory.getParentId()));
        }
        if(productCategory.getType()>=3){
            List<ProductCategory> productCategoryList=null;
            ProductCategoryParam params =new ProductCategoryParam();
            params.setType(3);
            productCategoryList3=productCategoryService.queryProductCategoryList(params);
        }
        request.setAttribute("productCategoryList1",productCategoryList1);
        request.setAttribute("productCategoryList2",productCategoryList2);
        request.setAttribute("productCategoryList3",productCategoryList3);
        return "/backend/productCategory/toAddProductCategory";
    }

    /**
     * 查询子分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult queryProductCategoryList(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        String parentId=request.getParameter("parentId");
        List<ProductCategory> productCategoryList=null;
        ProductCategoryParam params =new ProductCategoryParam();
        params.setParentId(EmptyUtils.isEmpty(parentId)?0:Integer.parseInt(parentId));
        productCategoryList=productCategoryService.queryProductCategoryList(params);
        result.setStatus(Constants.ReturnResult.SUCCESS);
        result.setData(productCategoryList);
        return result;
    }
    /**
     * 修改商品分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult modifyProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        Integer parentId=0;
        String id=request.getParameter("id");
        String productCategoryLevel1=request.getParameter("productCategoryLevel1");
        String productCategoryLevel2=request.getParameter("productCategoryLevel2");
        String name=request.getParameter("name");
        String type=request.getParameter("type");
        if(type.equals("1")){
            parentId =0;
        }else if(type.equals("2")){
            parentId =Integer.parseInt(productCategoryLevel1);
        }else{
            parentId =Integer.parseInt(productCategoryLevel2);
        }
        ProductCategory productCategory  =new ProductCategory();
        productCategory.setId(Integer.parseInt(id));
        productCategory.setParentId(parentId);
        productCategory.setName(name);
        productCategory.setType(Integer.parseInt(type));
        productCategoryService.modifyProductCategory(productCategory);
        result.returnSuccess();
        return result;
    }
    /**
     * 添加商品分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult addProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        Integer parentId=0;
        //获取分类级别
        String type=request.getParameter("type");
        String productCategoryLevel1=request.getParameter("productCategoryLevel1");
        String productCategoryLevel2=request.getParameter("productCategoryLevel2");
        String name=request.getParameter("name");
        if(type.equals("1")){
            parentId =0;
        }else if(type.equals("2")){
            parentId =Integer.parseInt(productCategoryLevel1);
        }else{
            parentId =Integer.parseInt(productCategoryLevel2);
        }
        ProductCategory productCategory =new ProductCategory();
        productCategory.setName(name);
        productCategory.setParentId(parentId);
        productCategory.setType(Integer.parseInt(type));
        productCategory.setIconClass("");
        productCategoryService.addProductCategory(productCategory);
        result.returnSuccess();
        return result;
    }
    /**
     * 删除商品分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult deleteProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ReturnResult result=new ReturnResult();
        //获取分类id
        String id=request.getParameter("id");
        String type=request.getParameter("type");
        //查询是否有子类
        ProductCategoryParam productCategoryParam=new ProductCategoryParam();
        productCategoryParam.setParentId(Integer.parseInt(id));
        int count=productCategoryService.queryProductCategoryCount(productCategoryParam);
        if(count>0){
        	return result.returnFail("已经存在子分类，不能删除");
        }
        //查询是否有商品
        count=productService.getProductCountBycategory(Integer.parseInt(id));
        if(count>0){
        	return result.returnFail("已经存在商品，不能删除");
        }
        productCategoryService.deleteById(Integer.parseInt(id));
        result.returnSuccess();
        return result;
    }

    @Override
    public Class getServletClass() {
        return AdminProductCategoryServlet.class;
    }
}
