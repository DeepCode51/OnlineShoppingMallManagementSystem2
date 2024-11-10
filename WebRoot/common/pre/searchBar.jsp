<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
    
    <title>My JSP 'searchBar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <div class="top">
    <div class="logo"><a href="Index.html"><img src="${ctx}/statics/images/logo.png" /></a></div>
    <div class="search">
    	<form>
        	<input type="text" value="" class="s_ipt" />
            <input type="submit" value="搜索" class="s_btn" />
        </form>                      
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
    </div>
     <div class="i_car">	
    	<div class="car_t">购物车 
    	[<c:if test="${sessionScope.cart.items !=null && sessionScope.cart.items.size()>0 }">
    	 <span>${sessionScope.cart.items.size()}</span> 
    	 </c:if>
    	 
    	 <c:if test="${sessionScope.cart.items ==null || sessionScope.cart.items.size()==0 }">
    	 <span> </span> 
    	 </c:if>
    	 ]
    	 </div> 
        <div class="car_bg">
       		<!--Begin 购物车未登录 Begin-->
        	<div class="un_login">还未登录！<a href="Login.html" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
            <c:forEach items="${sessionScope.cart.items}" var="item">
            	<li>
                	<div class="img"><a href="#"><img src="${ctx}/files/${item.product.fileName}" width="58" height="58" /></a></div>
                    <div class="name"><a href="#">${item.product.name}</a></div>
                    <div class="price"><font color="#ff4e00">${item.product.price}</font> X${item.quantity}</div>
                </li>
             </c:forEach>
            </ul>
            <div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span>${sessionScope.cart.sum}</span></div>
            <c:if test="${sessionScope.loginUser!=null }">
            <div class="price_a"><a href="${ctx }/CartServlet?action=toSettlement">去购物车结算</a></div>
            </c:if>
            
            <c:if test="${sessionScope.loginUser==null }">
            <div class="price_a"><a href="${ctx}/LoginServlet?action=toLogin">去登入</a></div>
            </c:if>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>
  </body>
</html>
