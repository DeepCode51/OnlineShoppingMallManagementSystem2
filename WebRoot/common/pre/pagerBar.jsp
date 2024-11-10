<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<div class="pages">
    <c:if test="${pager.pageCount>=1}">
      <a href="${ctx}/${pager.url}&currentPage=1" class="p_pre">首页</a>
        <c:if test="${pager.currentPage>1}">
            <a href="${ctx}/${pager.url}&currentPage=${pager.currentPage-1}" class="p_pre">上一页</a>
        </c:if>
        <c:forEach  var="temp" begin="${pager.currentPage>3?pager.currentPage-3:1}" end="${pager.pageCount-pager.currentPage>3?pager.currentPage+3:pager.pageCount}" step="1">
            <c:if test="${pager.currentPage==temp}">
                <a href="${ctx}/${pager.url}&currentPage=${temp}" class="cur">${temp}</a>
            </c:if>
            <c:if test="${pager.currentPage!=temp}">
                <a href="${ctx}/${pager.url}&currentPage=${temp}">${temp}</a>
            </c:if>
        </c:forEach>
      <c:if test="${pager.currentPage<pager.pageCount}">
         <a href="${ctx}/${pager.url}&currentPage=${pager.currentPage+1}" class="p_pre">下一页</a>
      </c:if>
      <a href="${ctx}/${pager.url}&currentPage=${pager.pageCount}" class="p_pre">尾页</a>
    </c:if>
    <c:if test="${pager.pageCount==0}">
        暂无记录
    </c:if>
</div>
