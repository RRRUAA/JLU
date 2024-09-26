<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="Author" contect="http://www.webqin.net">
	<title>旅游服务平台</title>
	<link rel="shortcut icon" href="images/favicon.ico"/>
	<link type="text/css" href="css/css.css" rel="stylesheet"/>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/js.js"></script>
	<script type="text/javascript">
        $(function () {
            //导航定位
            $(".nav li:eq(0)").addClass("navCur");
        })
	</script>
</head>

<body>
<div class="header">
	<div class="width1190">
		<div class="fl">
			<c:if test="${user!=null}">
				您好，欢迎<b>${user.nickname}</b>登录旅游景点服务平台！
			</c:if>
			<c:import url="http://localhost:8080/travel_web/Weather" var="weatherData" />
			<b>今日天气：${weatherData}</b>
		</div>
		<div class="fr">
			<c:if test="${user == null }">
				<a href="IndexServlet?action=toRegister">去注册</a>
				<a href="LoginServlet?action=loginOut">去登录</a>
				<%-- <img src="upload/${user.photo}" id="preview_img" style="width:40px; height:40px; border-radius:100%; overflow:hidden;"> --%>
				<a>${user.realname}</a>

			</c:if>
			<c:if test="${user != null }">
				<a href="IndexServlet?action=toMyinfo">个人中心</a>
				<a href="IndexServlet?action=toMyOrder">我的订单</a>
				<a href="LoginServlet?action=loginOut">退出登录</a>
			</c:if>

		</div>
		<div class="clears"></div>
	</div>
</div>
<div class="logo-phone">
	<div class="width1190">
		<h1 class="logo"><a href="#" style="font-size:20px;">旅游景点服务平台</a></h1>
		<!--div class="phones"><strong>17766091168</strong></div-->
		<div class="clears"></div>
	</div><!--width1190/-->
</div><!--logo-phone/-->