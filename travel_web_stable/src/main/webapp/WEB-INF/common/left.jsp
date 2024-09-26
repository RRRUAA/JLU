<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<div class="vip-left">
	<div class="vipNav">
		<h3 class="vipTitle">个人中心</h3>
		<dl>
			<dt class="vipIcon3">账户设置</dt>
			<dd>
				<a href="IndexServlet?action=toMyYue">余额充值</a>
				<a href="IndexServlet?action=toMyinfo">个人信息</a>

				<!--a href="<%=path %>/RouteServlet?action=toAddRoute">发布旅游路线</a>
				<a href="IndexServlet?action=toMyRoute">我的旅游路线</a-->
				<a href="IndexServlet?action=toMyOrder">我的门票订单</a>
				<!--a href="IndexServlet?action=toMyYdxx">我的酒店预订订单</a-->
				<a href="IndexServlet?action=toMyComment">我的评价</a>
			</dd>

		</dl>
	</div><!--vipNav/-->
</div>