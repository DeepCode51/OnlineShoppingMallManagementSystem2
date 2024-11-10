<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/pre/header.jsp" %>
    <script src="${ctx}/statics/js/backend/backend.js"></script>
</head>
<body>
<%@ include file="/common/backend/SearchBar.jsp" %>
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="/common/backend/leftBar.jsp" %>
        <div class="m_right" id="content">
        <div class="mem_tit">
        	<c:choose>
                    <c:when test="${empty product.id || product.id==0}">
                     	 添加商品 
                    </c:when>
                    <c:otherwise>
                      	修改商品 
                    </c:otherwise>
                </c:choose>
       </div><br>
           
            <form action="${ctx}/admin/product?action=addProduct" method="post" enctype="multipart/form-data" id="productAdd" onsubmit="return checkProduct();">
            <table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="135" align="right">一级分类</td>
                    <td colspan="3" style="font-family:'宋体';">
                        <select name="categoryLevel1Id" style="background-color:#f6f6f6;" id="productCategoryLevel1"
                                onchange="queryProductCategoryList(this,'productCategoryLevel2');">
                            <option value="" selected="selected">请选择...</option>
                            <c:forEach items="${productCategoryList1}" var="temp">
                                <option value="${temp.id}"
                                        <c:if test="${product.categoryLevel1Id==temp.id}">selected="selected"</c:if> >${temp.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">二级分类</td>
                    <td>
                        <select name="categoryLevel2Id" style="background-color:#f6f6f6;"
                                id="productCategoryLevel2"
                                onchange="queryProductCategoryList(this,'productCategoryLevel3');">
                            <option value="0" selected="selected">请选择...</option>
                            <c:forEach items="${productCategoryList2}" var="temp">
                                <option value="${temp.id}"
                                        <c:if test="${product.categoryLevel2Id==temp.id}">selected="selected"</c:if> >${temp.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">三级分类</td>
                    <td>
                        <select name="categoryLevel3Id" style="background-color:#f6f6f6;"
                                id="productCategoryLevel3">
                            <option value="0" selected="selected">请选择...</option>
                            <c:forEach items="${productCategoryList3}" var="temp">
                                <option value="${temp.id}"
                                        <c:if test="${product.categoryLevel3Id==temp.id}">selected="selected"</c:if> >${temp.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">商品名称</td>
                    <td>
                        <input type="text" value="${product.name}" class="add_ipt" name="name" id="name"/>（必填）
                        <input type="hidden" name="id" value="${product.id}" id="id">
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">商品图片</td>
                    <td>
                        <c:if test="${product.fileName!=null && product.fileName!=''}">
                            <img src="${ctx}/files/${product.fileName}" width="50" height="50"/>
                        </c:if>
                        <input type="file" class="text" name="photoFile"/>
                        <input type="hidden" class="text" name="fileName" id="photo" value="${product.fileName}"/>
                        <span></span>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">单价</td>
                    <td>
                        <input type="text" value="${product.price}" class="add_ipt" name="price" id="price"/>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">库存</td>
                    <td>
                        <input type="text" value="${product.stock}" class="add_ipt" name="stock" id="stock"/>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">描述</td>
                    <td>
                        <textarea name="description">${product.description}</textarea>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <c:choose>
                            <c:when test="${empty product.id}">
                                <input type="submit" value="商品上架" class="s_btn">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="确定修改" class="s_btn">
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    <%@ include file="/common/pre/footer.jsp" %>
</div>
</body>
</html>


