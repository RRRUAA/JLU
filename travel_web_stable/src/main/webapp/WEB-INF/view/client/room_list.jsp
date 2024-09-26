<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>
<div class="list-nav">
	<div class="width1190">
		<%--  <div class="list">
		  <!-- <h3>房源分类</h3> -->
	   <jsp:include page="/WEB-INF/common/top_left.jsp"/>
		 </div><!--list/--> --%>
		<jsp:include page="/WEB-INF/common/nav.jsp"/>
		<!--nav/-->
		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--list-nav/-->
<!-- <div class="banner" style="background:url(images/ban.jpg) center center no-repeat;"></div> -->

<div class="content">
	<div class="width1190">
		<h2 class="title">服务站点列表 <!-- <a href="CommonServlet?action=moreHouse">更多&gt;&gt;</a> --></h2>
		<div class="index-fang-list">
			<c:forEach items="${roomList}" var="data">
				<dl>
					<dt><a href="IndexServlet?action=toRoomDetail&id=${data.id }"><img src="upload/${data.photo }"
																					   width="286" height="188"/></a>
					</dt>
					<dd>
						<h4><a href="IndexServlet?action=toRoomDetail&id=${data.id }">站点名称：${data.title }</a></h4>
							<%--    <div class="hui">酒店名称：${data.name }</div> --%>
						<%--div class="hui">房间类型：${data.category }</div>
						<div class="hui">价格：<span style="color:red">${data.price }￥</span>/晚</div--%>
						<d<%-- iv class="hui">酒店地址：${data.address } <a href="IndexServlet?action=toRoomDetail&id=${data.id }" style="color:red;">购票</a></div> --%>
								--%>
							<a href="IndexServlet?action=toRoomDetail&id=${data.id }" class="btn btn-primary"
							   style="color:blue">查看详情</a>
					</dd>

				</dl>
			</c:forEach>
		</div>


		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--content/-->


<br>
<br>
<jsp:include page="/WEB-INF/common/footer.jsp"/>


</body>
</html>
