<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>
<!-- 引入 layui.css -->

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

				游玩人数：${ticket.queuenum}<span style="color:red">人</span><br>



			</div>
			<%--
			门票等级：${ticket.level}<br>
				门票数量：${ticket.sotock}<br>
				发布时间：${scenic.create_date}<br>
				--%>
			<div class="xun-car">

				<a href="IndexServlet?action=toRoom&sid=${scenic.id }" class="xwjg">服务站点详情</a>
				<%--   <a href="GzfyServlet?action=gzfy&hid=${house.id}" class="projrgwc">关注房源</a> --%>

			</div><!--xun-car/-->
		</div><!--proText/-->
		<div class="clears"></div>
	</div><!--width1190/-->

	<div class="proBox" style="width:1000px;margin:10px auto;">
		<div class="proEq">
			<ul class="fl">
				<li class="proEqCur">景点详情</li>
				<li>服务站点(${counts })</li>
				<!--     <li>中介信息</li> -->
			</ul>
			<!--div class="lxkf fr"><a href="http://wpa.qq.com/msgrd?v=3&uin=1072631488&site=qq&menu=yes"
									target="_blank"></a></div-->
			<div class="clears"></div>
		</div><!--proEq/-->
		<div class="proList">
			${scenic.detail}
			<br/>
			<br/>

			<!--  <img src="images/fang1.jpg" /><img src="images/fang2.jpg" /><img src="images/fang3.jpg" /><img src="images/fang4.jpg" /><img src="images/fang5.jpg" /><img src="images/fang6.jpg" /><img src="images/fang7.jpg" /><img src="images/fang8.jpg" />
			 -->  </div><!--proList/-->
		<div class="proList">
			<div class="guanzhulist">
				<c:forEach items="${roomList }" var="data">
					<dl>
						<dt><a href=""><img src="upload/${data.photo }" width="70" height="100"/></a></dt>
						<dd>
							<p><a href="#"> ${data.name }</a></p>

							<div class="guantext">服务站介绍：${data.note}</div>
							<%--div class="guantext2">评论时间：${data.create_time} </div--%>
						</dd>
							<%--  <div class="price">¥ <strong>${g.house.price }</strong><span class="font12">元/月</span></div> --%>
						<div class="clearfix"></div>
					</dl>
				</c:forEach>

			</div>
		</div>
	</div><!--proBox/-->
</div>
<!--content/-->


<div class="clears"></div>
</div><!--width1190/-->
</div><!--content/-->


</div>

<jsp:include page="/WEB-INF/common/footer.jsp"/>


</body>
</html>
