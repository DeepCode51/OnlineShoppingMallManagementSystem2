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
      <p></p>
      <p></p>
      <div class="mem_tit">订单列表</div>
      <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
        <tbody>
        <c:forEach items="${orderList}" var="temp">
          <tr class="td_bg">
            <td>用户名:${temp.loginName}</td>
            <td><a href="${ctx}/admin/order?action=queryOrderDeatil&orderId=${temp.id}">订单号:${temp.serialNumber}</a></td>
            <td>地址:${temp.userAddress}</td>
            <td>￥${temp.cost}</td>
          </tr>
          <tr>
          </tr>
          <tr>
            <td colspan="4">
              <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                  <td width="20%">商品名称</td>
                  <td width="20%">商品图片</td>
                  <td width="25%">数量</td>
                  <td width="25%">价格</td>
                </tr>
                <c:forEach items="${temp.orderDetailList}" var="temp">
                  <tr>
                    <td>${temp.product.name}</td>
                    <td>
                      <a href="${ctx}/Product?action=queryProductDeatil&id=${temp.product.id}" target="_blank">
                        <img src="${ctx}/files/${temp.product.fileName}" width="50" height="50">
                      </a>
                    </td>
                    <td>${temp.quantity}</td>
                    <td>${temp.cost}</td>
                  </tr>
                </c:forEach> 
                </tbody>
              </table>
            </td>
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


