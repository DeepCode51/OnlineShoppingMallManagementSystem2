<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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
    <%@ include file="/common/backend/leftBar.jsp"%>
    <div class="m_right" id="content">
      <div class="mem_tit">商品列表</div>
      <br>
      <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
        <tbody>
        <tr>
          <td width="10%">商品名称</td>
          <td width="10%">商品图片</td>
          <td width="5%">库存</td>
          <td width="10%">价格</td>
          <td width="10%" colspan="2">操作</td>
        </tr>
        <c:forEach items="${productList}" var="temp">
          <tr>
            <td>${temp.name}</td>
            <td>
              <a href="${ctx}/Product?action=queryProductDeatil&id=${temp.id}" target="_blank">
                  <img src="${ctx}/files/${temp.fileName}" width="50" height="50"/>
              </a>
            </td>
            <td>${temp.stock}</td>
            <td>${temp.price}</td>
            <td><a href="${ctx}/admin/product?action=toUpdateProduct&id=${temp.id}">修改</a></td>
            <td><a href="javascript:void(0);" onclick="deleteById('${temp.id}');">删除</a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <%@ include file="/common/pre/pagerBar.jsp" %>
    </div>
  </div>
  <%@ include file="/common/pre/footer.jsp" %>
</div>
</body>
</html>


