<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>

  <head>
    <title>My JSP 'categoryBar.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
    <div class="menu_bg">
	<div class="menu">
    	<!--Begin 商品分类详情 Begin-->    
    	<div class="nav" >
        	<div class="nav_t">全部商品分类</div>
            <div class="leftNav" style="display:none">
                <ul>   
                   <c:forEach items="${pcList }" var="pc1" >
                    <li>
                    	<div class="fj">
                        	<span class="n_img"><span></span><img src="${ctx}/statics/images/nav1.png" /></span>
                            <span class="fl">${pc1.productCategory.name}</span>
                        </div>
                        <div class="zj">
                            <div class="zj_l">
                             <c:forEach items="${pc1.productCategoryVoList }" var="pc2">
                                <div class="zj_l_c">
                                    <h2> <a href="${ctx }/ProductServlet?action=queryProductList&categoryId=${pc2.productCategory.id}"></a>${pc2.productCategory.name }</h2>
                                    
                                    <c:forEach items="${ pc2.productCategoryVoList}" var="pc3">
                                    <a href="${ctx }/ProductServlet?action=queryProductList&categoryId=${pc3.productCategory.id}">${pc3.productCategory.name}</a>|
                                    </c:forEach>
                                </div> 
                                 </c:forEach>
                            </div>
                            
                          	 <div class="zj_r">
                                <a href="#"><img src="${ctx}/statics/images/n_img1.jpg" width="236" height="200" /></a>
                                <a href="#"><img src="${ctx}/statics/images/n_img2.jpg" width="236" height="200" /></a>
                            </div> 
                        </div>
                    </li>
                    </c:forEach>
                </ul>            
            </div>
        </div>  
        <!--End 商品分类详情 End-->                                                     
    	<ul class="menu_r">                                                                                                                                               
        	<li><a href="${ctx }/HomeServlet?action=index">首页</a></li>
        	
        	<c:forEach items="${pcList}" var="pcList">
            <li><a href="${ctx }/ProductServlet?action=queryProductList&categoryId=${pcList.productCategory.id}">${pcList.productCategory.name}</a></li>
            </c:forEach>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
	<script src="${ctx }/statics/js/common/n_nav.js"></script>
  </body>
</html>
