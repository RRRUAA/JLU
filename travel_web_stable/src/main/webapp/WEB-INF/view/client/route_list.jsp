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

<%--   <jsp:include page="/WEB-INF/common/top.jsp"/>  --%>

<div class="width1190">
	<div class="pro-left">
		<c:forEach items="${routeList}" var="data">
			<br>
			<dl>
				<dt><a href="#"><img src="upload/${data.user.photo }" width="80" height="100"/></a></dt>
				<dd>
					<h3>线路编号：<a href="#">${data.route_no }</a></h3>

					<div class="pro-fang">线路名称：${data.name} 发布者：${data.user.nickname }</div>
					<div class="pro-fang">出发地：${data.start_spot} 目的地：${data.end_spot }</div>

					<div class="pra-fang">发布日期：${data.create_time }</div>

						<%--  <a href="IndexServlet?action=toRouteDetail&id=${data.id }" style="color:blue">查看详情</a> --%>

				</dd>
					<%--   <div class="price">  <strong>${data.price }</strong><span class="font12">元/张</span></div> --%>
				<div class="clears"></div>
			</dl>
		</c:forEach>
	</div>
	<div class="pro-right">
		<h2 class="right-title">最新景点</h2>
		<div class="right-pro">

			<c:forEach items="${scenicList}" var="data">
				<dl>
					<dt><a href="IndexServlet?action=toScenic&id=${data.id }"><img src="upload/${data.photo }"/></a>
					</dt>
					<dd>
						<h3><a href="IndexServlet?action=toScenic2&id=${data.id }">${data.names }</a></h3>
						<div class="pro-fang">发布时间：${data.create_date }</div>
							<%--  <div class="right-price">${l.price }元/月</div> --%>
					</dd>
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
