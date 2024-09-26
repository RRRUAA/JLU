<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>

<div class="list-nav">
	<div class="width1190">


		<jsp:include page="/WEB-INF/common/nav.jsp"/>


		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--list-nav/-->
<!-- <div class="banner" style="background:url(images/ban.jpg) center center no-repeat;"></div> -->
<div class="content">
	<div class="width1190" style="width:1000px;">
		<div class="proImg fl">
			<img src="upload/${scenic.photo}"/>
		</div><!--proImg/-->
		<div class="proText fr">
			<h4 class="proTitle">景点名称：${scenic.names} </h4>
			<div class="proText1">

				发布时间：${scenic.create_date}<br>


			</div>
			<div class="xun-car">
				<!--
				<a href="IndexServlet?action=toNews" class="xwjg">返回</a>  -->
				<a href="IndexServlet?action=toNews" class="projrgwc">返回</a>

			</div><!--xun-car/-->
		</div><!--proText/-->
		<div class="clears"></div>
	</div><!--width1190/-->
	<div class="proBox" style="width:1000px;margin:10px auto;">
		<div class="proEq">
			<ul class="fl">
				<li class="proEqCur">景点详情</li>

				<!--     <li>中介信息</li> -->
			</ul>
			<div class="lxkf fr"><a href="http://wpa.qq.com/msgrd?v=3&uin=1072631488&site=qq&menu=yes"
									target="_blank"></a></div>
			<div class="clears"></div>
		</div><!--proEq/-->
		<div class="proList">
			${scenic.detail}
			<br/>
			<br/>


		</div><!--content/-->


		<jsp:include page="/WEB-INF/common/footer.jsp"/>


		</body>
		</html>
