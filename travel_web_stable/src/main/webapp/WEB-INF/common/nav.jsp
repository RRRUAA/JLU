<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<ul class="nav">

	<!--     <li><a href="IndexServlet?action=toShouye">推荐</a></li> -->
	<li><a href="IndexServlet?action=toIndex">首页</a></li>
	<li><a href="IndexServlet?action=toSearchIndex">门票大厅</a></li>
	<!--li><a href="IndexServlet?action=toNews">景点资讯</a></li-->
	<li><a href="IndexServlet?action=toShouye">景点推荐</a></li>
	<!--li><a href="IndexServlet?action=toRoute">旅游线路</a></li-->
	<li><a href="IndexServlet?action=toSituationIndex">景点状况</a></li>
	<!--   <li><a href="IndexServlet?action=toAbout">关于我们</a></li> -->
	<!-- li><a href="IndexServlet?action=toContactUs">联系我们</a></li-->
	<div class="clears"></div>
</ul>
   
 