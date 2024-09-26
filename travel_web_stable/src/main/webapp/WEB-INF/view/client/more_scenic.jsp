<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
　　
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>

<div class="list-nav">
	<div class="width1190">

		<jsp:include page="/WEB-INF/common/nav.jsp"/>
		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--list-nav/-->

<jsp:include page="/WEB-INF/common/top.jsp"/>

<div class="width1190">

	<div class="pro-left">
		<c:forEach items="${list}" var="data">
			<dl>
				<dt><a href="IndexServlet?action=toScenic&id=${data.id }"><img src="upload/${data.scenic.photo }"
																			   width="286" height="128"/></a></dt>
				<dd>
					<h3>景点：<a href="IndexServlet?action=toScenic&id=${data.id }">${data.scenic.names  }</a></h3>
						<%--  <div class="pro-wei">
						  <img src="images/weizhi.png" width="12" height="16" /> <strong class="red">${h.xq }</strong>
						 </div> --%>
					<div class="pro-fang"> 等级：${data.level }</div>
					<br>
					<div class="pra-fang">发布日期：${data.create_date }</div>
					<br>

					<div class="pra-fang">门票价格：<strong style="color:red">${data.price }元/张</strong></div>
				</dd>
					<%--   <div class="price">  <strong>${data.price }</strong><span class="font12">元/张</span></div> --%>
				<div class="clears"></div>
			</dl>
		</c:forEach>
		<br>
		<div class="pagination clearfix style2">
			<div class="nav-link">
				<a href="IndexServlet?action=toSearchIndex&p=1" class="page-numbers">首页</a>
				<c:if test="${cp>1}">
					<a href="IndexServlet?action=toSearchIndex&p=${cp-1}" class="page-numbers"><i
							class="icon fa fa-angle-left" aria-hidden="true"></i></a>
				</c:if>
				<c:forEach begin="${cp-2>1 ? (cp-2) :1}"
						   end="${cp+2>tp?tp:(cp+2)}" var="e">
					<c:if test="${cp==e}">
						<a href="IndexServlet?action=toSearchIndex&p=${e}" class="page-numbers current">${e}</a>
					</c:if>
					<c:if test="${cp!=e}">
						<a href="IndexServlet?action=toSearchIndex&p=${e}" class="page-numbers">${e}</a>
					</c:if>
				</c:forEach>
				<c:if test="${cp<tp}">
					<a href="IndexServlet?action=toSearchIndex&p=${cp+1}" class="page-numbers"><i
							class="icon fa fa-angle-right" aria-hidden="true"></i></a>
				</c:if>
				<a href="IndexServlet?action=toSearchIndex&p=${tp}" class="page-numbers">尾页</a>
			</div>
		</div>
		<br>
	</div><!--pro-left/-->
	<div class="pro-right">
		<h2 class="right-title">景点资讯</h2>
		<div class="right-pro">

			<c:forEach items="${newsList}" var="data">
				<dl>
						<%-- <dt><a href="HouseServlet?action=detail&id=${l.id }"><img src="upload/${l.imgs.img_url }" /></a></dt>  --%>
					<dt>
						<h3><a href="IndexServlet?action=toNewsDetail&id=${data.id }">资讯标题：${data.title }</a></h3>
						<div class="pro-fang">编辑者：${data.editor }</div>
						<a href="IndexServlet?action=toNewsDetail&id=${data.id }}" class="page-numbers"
						   style="color:red">查看详情</a>
							<%--   <div class="right-price">${l.price }元/月</div> --%>
					</dt>
				</dl>
			</c:forEach>

		</div><!--right-pro/-->
	</div><!--pro-right/-->
	<div class="clears"></div>
</div>
<!--width1190/-->
</div><!--content/-->

<jsp:include page="/WEB-INF/common/footer.jsp"/>


</body>
</html>
