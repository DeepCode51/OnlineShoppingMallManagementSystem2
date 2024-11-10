<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<%@include file="/common/pre/header.jsp" %>
<div id="searchBar">
<%@include file="/common/pre/searchBar.jsp" %>
</div>
<!--End Header End--> 
<!--Begin Menu Begin-->
<div class="menu_bg">
	<div class="menu">
    	<!--Begin 商品分类详情 Begin-->
    	<%@include file="/common/pre/categoryBar.jsp" %>
    	<!--End 商品分类详情 End-->  
    </div>
</div>   
<!--End Menu End--> 
<div class="i_bg">
	<!-- <div class="postion">
    	<span class="fl">全部 > 美妆个护 > 香水 > 香奈儿</span>
    </div> -->
    
    <div class="content mar_20">
    	<div class="l_history">
        	<div class="his_t">
            	<span class="fl">浏览历史</span>
                <span class="fr"><a href="#">清空</a></span>
            </div>
        	<ul>
            	<li>
                    <div class="img"><a href="#"><img src="${ctx }/statics/images/images/his_1.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx }/statics/images/his_2.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>768.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx }/statics/images/images/his_3.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>680.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx }/statics/images/images/his_4.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx }/statics/images/images/his_5.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
        	</ul>
        </div>
        	<div class="list_t">
            	<span class="fl list_or">
                	<a href="#" class="now">默认</a>
                    <a href="#">
                    	<span class="fl">销量</span>                        
                        <span class="i_up">销量从低到高显示</span>
                        <span class="i_down">销量从高到低显示</span>                                                     
                    </a>
                    <a href="#">
                    	<span class="fl">价格</span>                        
                        <span class="i_up">价格从低到高显示</span>
                        <span class="i_down">价格从高到低显示</span>     
                    </a>
                    <a href="#">新品</a>
                </span>
            </div>
            <div class="list_c">
                <ul class="cate_list">
                <c:forEach items="${pList }" var="pList">
                    <li>
                    	<div class="img"><a href="${ctx }/ProductServlet?action=queryProductDeatil&id=${pList.id}"><img src="${ctx}/files/${pList.fileName}" width="210" height="185" /></a></div>
                        <div class="price">
                            <font>￥<span>${pList.price}</span></font> &nbsp; 26R
                        </div>
                        <div class="name"><a href="#">${pList.name}</a></div>
                        <div class="carbg">
                        	<a href="#" class="ss" >收藏</a>
                            <a href="#" class="j_car" onclick="addCartByParam('${pList.id}',1)">加入购物车</a>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
                
                <div class="pages">
                	<a href="#" class="p_pre">上一页</a><a href="#" class="cur">1</a><a href="#">2</a><a href="#">3</a>...<a href="#">20</a><a href="#" class="p_pre">下一页</a>
                </div>
            </div>
        </div>
    </div>
    
    <!--Begin Footer Begin -->
 <%@include file="/common/pre/footer.jsp" %> 
    <!--End Footer End -->    
</div>
	<script type="text/javascript" src="${ctx}/statics/js/cart/cart.js"></script>
 	<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/jquery.bxslider_e88acd1b.js"></script>
    
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>    
            
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>
    
    
	<script type="text/javascript" src="${ctx}/statics/js/common/n_nav.js"></script>    
    <script type="text/javascript" src="${ctx}/statics/js/common/milk_ban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/paper_ban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/baby_ban.js"></script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>


