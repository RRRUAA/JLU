<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
	<title>旅游服务网站</title>
	<link rel="icon" href="favicon.ico" type="image/ico">
	<meta name="keywords" content="后台管理系统HTML模板">
	<meta name="description" content="基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
	<meta name="author" content="yinqi">
	<link href="<%=path%>/resource/boot/css/bootstrap.min.css"
		  rel="stylesheet">
	<link rel="icon" href="<%=path%>/resource/static/favicon.ico">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/index.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/main.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/html.css">
	<script src="<%=path%>/resource/static/js/vue.min.js"></script>
	<script src="<%=path%>/resource/static/js/jquery-3.3.1.min.js"></script>
	<script src="<%=path%>/resource/static/bootstrap/js/bootstrap.bundle.js"></script>
	<script src="<%=path%>/resource/static/admin/js/config.js"></script>
	<script src="<%=path%>/resource/static/admin/js/script.js"></script>
	<script type="text/javascript"
			src="<%=path%>/resource/My97DatePicker/WdatePicker.js"></script>
	<script>
        $(function () {
            const hrefArr = window.location.href.split('/');
            const name = hrefArr[hrefArr.length - 1];
            $('.menu a[href=\'' + name + '\']').addClass('active');
        });
	</script>
	<style>
        .main {
            overflow-y: auto;
        }

        .info .card {
            min-height: 330px;
        }

        .user .card {
            min-height: 500px;
        }

        .card h5 {
            margin-bottom: 20px;
        }

        .main ul li {
            margin-bottom: 3px;
        }
	</style>
	<!-- <style>
	td {
		text-align: center;
	}

	th {
		text-align: center;
	}
	</style> -->
</head>
<nav>
	<div class="logo">
		<h4>旅游服务平台网站</h4>
	</div>
	<ul class="menu">
		<li><a href="LoginServlet?action=toMain"><i class="iconfont mr-1">&#xe6a2;</i>首页</a></li>
		<c:if test="${admin !=null }">
			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6e0;</i>分类标签管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="CategoryServlet?action=CategoryList">分类标签列表</a></li>
					<li><a href="CategoryServlet?action=toAddCategory">添加分类标签</a></li>
				</ul>
			</li>
			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6e0;</i>游客管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="UserServlet?action=UserList">游客列表</a></li>
					<li><a href="UserServlet?action=toAddUser">添加游客</a></li>
				</ul>
			</li>
			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>景点管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="ScenicServlet?action=ScenicList">景点列表</a></li>
					<li><a href="ScenicServlet?action=toAddScenic">去新增景点</a></li>
				</ul>
			</li>
			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>门票管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="TicketServlet?action=TicketList">门票信息列表</a></li>
					<li><a href="TicketServlet?action=toAddTicket">去新增门票</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>景点资讯管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="NewsServlet?action=NewsList">景点资讯列表</a></li>
					<li><a href="NewsServlet?action=toAddNews">去新增景点资讯</a></li>
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>酒店房间管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="RoomServlet?action=RoomList">酒店房间列表</a></li>
					<li><a href="RoomServlet?action=toAddRoom">去新增酒店房间</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>旅游线路管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="RouteServlet?action=RouteList">旅游线路列表</a></li>
					<!-- <li><a href="RoomServlet?action=toAddRoom">去新增酒店房间</a></li> -->
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>订单管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="OrdersServlet?action=OrdersList">订单列表</a></li>
					<li><a href="YdxxServlet?action=YdxxList">酒店预订列表</a></li>
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>评论管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="CommentsServlet?action=CommentsList">评论列表</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6ad;</i>数据统计<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="LoginServlet?action=toEcharts">数据统计</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6e0;</i>个人信息<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="UserServlet?action=toUpdateAdmin">修改密码</a></li>
				</ul>
			</li>

		</c:if>

		<li><a href="LoginServlet?action=loginOut"><i class="iconfont mr-1">&#xe68c;</i>退出登录</a>
		</li>
	</ul>
</nav>