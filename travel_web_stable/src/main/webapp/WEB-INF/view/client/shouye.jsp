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
				您好，欢迎<b>${user.nickname}</b>登录旅游服务平台网站 ！
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
		<h3 class="logo" style="font-size:25px"><a href="IndexServlet?action=toIndex">旅游服务平台</a></h3>
		<!--div class="phones"><strong>17766091168</strong></div-->
		<div class="clears"></div>
	</div><!--width1190/-->
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/bootstrap/css/bootstrap.min.css">

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
	</div><!--list-nav/-->
	<!-- <div class="banner" style="background:url(images/ban.jpg) center center no-repeat;"></div> -->
	<div style="margin:0 auto;">

	</div>
	<div class="content">

		<div class="width1190">
   <span class="layui-breadcrumb" lay-separator="|">
   <c:forEach items="${categories }" var="row">
	   <a href="IndexServlet?action=toShouye2&c_id=${row.id }&p=1">${row.cname }</a>
   </c:forEach>
</span>
			<h2 class="title">景点信息<!-- <a href="CommonServlet?action=moreHouse">更多&gt;&gt;</a> --></h2>
			<div class="index-fang-list">
				<c:forEach items="${list}" var="data">
					<dl>
						<dt><a href="IndexServlet?action=toScenic2&id=${data.id }"><img src="upload/${data.photo }"
																						width="286" height="157"/></a>
						</dt>
						<dd>
							<h4><a href="IndexServlet?action=toScenic2&id=${data.id }">名称：${data.names  }</a></h4>
								<%--  <div class="hui">价格：${data.price }<span style="color:red">￥</span></div>
								 <div class="hui">等级：${data.level } <a href="IndexServlet?action=toScenic&id=${data.id }" style="color:red;">购票</a></div> --%>
							<a href="IndexServlet?action=toScenic2&id=${data.id }" style="color:blue">查看详情</a>
							<br>
						</dd>

					</dl>

				</c:forEach>

			</div>


			<div class="clears"></div>
			<div class="page" style="text-align:center;margin-top:100px;">

				<ul class="pagination">
					<c:if test="${list!=null }">
					<li class="page-item"><a class="page-link"
											 href="IndexServlet?action=toShouye&p=1">首页</a></li>
					<c:if test="${cp>1}">
						<li class="page-item"><a class="page-link"
												 href="IndexServlet?action=toShouye&p=${cp-1}">上一页</a></li>
					</c:if>
					<c:forEach begin="${cp-2>1 ? (cp-2) :1}"
							   end="${cp+2>tp?tp:(cp+2)}" var="e">
						<%--            判断是否是当前页--%>
						<c:if test="${cp==e}">
							<li class="page-item"><a class="page-link" style="background-color: #007bff;color:white"
													 href="IndexServlet?action=toShouye&p=${e}">${e}</a></li>
						</c:if>
						<c:if test="${cp!=e}">
							<li class="page-item"><a class="page-link"
													 href="IndexServlet?action=toShouye&p=${e}">${e}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${cp<tp}">
						<li class="page-item"><a class="page-link"
												 href="IndexServlet?action=toShouye&p=${cp+1}">下一页</a></li>
					</c:if>
					<li class="page-item"><a class="page-link"
											 href="IndexServlet?action=toIndex&p=${tp}">尾页</a></li>
				</ul>
				</c:if>
			</div>
		</div>
	</div><!--content/-->
</div><!--width1190/-->


<br>
<br>
<jsp:include page="/WEB-INF/common/footer.jsp"/>


</body>
</html>
