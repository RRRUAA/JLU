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
			<img src="upload/${room.photo}"/>
		</div><!--proImg/-->
		<div class="proText fr">
			<h4 class="proTitle">站点名称：${room.title} </h4>
			<div class="proText1">



				联系方式：${room.phone}<br>
				站点地址：${room.address}<br>

			</div>
			<%--酒店名称：${room.name}<br>价格：${room.price}<span style="color:red">元/晚</span><br>--%>
			<div class="xun-car">
				<!--  <li class="zhiding"><a href="javascript:;">指定购房</a></li> -->
				<%--a href="YdxxServlet?action=toAddYdxx&id=${room.id }" class="xwjg">立即预订</a--%>
				<%--   <a href="GzfyServlet?action=gzfy&hid=${house.id}" class="projrgwc">关注房源</a> --%>

			</div><!--xun-car/-->
		</div><!--proText/-->
		<div class="clears"></div>
	</div><!--width1190/-->
	<div class="proBox" style="width:1000px;margin:10px auto;">
		<div class="proEq">
			<ul class="fl">
				<li class="proEqCur">站点详情</li>
				<%-- <li>评价信息(${counts })</li> --%>
				<!--     <li>中介信息</li> -->
			</ul>
			<!--div class="lxkf fr"><a href="http://wpa.qq.com/msgrd?v=3&uin=1072631488&site=qq&menu=yes"
									target="_blank"></a></div-->
			<div class="clears"></div>
		</div><!--proEq/-->
		<div class="proList">
			${room.note}
			<br/>
			<br/>

			<!--  <img src="images/fang1.jpg" /><img src="images/fang2.jpg" /><img src="images/fang3.jpg" /><img src="images/fang4.jpg" /><img src="images/fang5.jpg" /><img src="images/fang6.jpg" /><img src="images/fang7.jpg" /><img src="images/fang8.jpg" />
			 -->  </div><!--proList/-->
		<%--   <div class="proList">
		  <div class="guanzhulist">
			 <c:forEach items="${commentsList }" var="data">
			  <dl>
			   <dt><a href=""><img src="upload/${data.uphoto }" width="70" height="100" /></a></dt>
			   <dd>
				<p><a href="#"> 评论人： ${data.uname }</a></p>

				<div class="guantext">评论内容：${data.content}</div>
				<div class="guantext2">评论时间：${data.create_time} </div>
			   </dd>
			   <div class="price">¥ <strong>${g.house.price }</strong><span class="font12">元/月</span></div>
			   <div class="clearfix"></div>
			  </dl>
			  </c:forEach>

			 </div>

		 </div><!--proBox/--> --%>
	</div><!--content/-->

	<div class="zhidinggoufang">
		<h2>指定购房 <span class="close">X</span></h2>
		<form action="#" method="get">
			<div class="zhiding-list">
				<label>选择区域：</label>
				<select>
					<option>智慧园</option>
					<option>立民村</option>
					<option>塘口村</option>
					<option>勤劳村</option>
					<option>芦胜村</option>
					<option>知新村</option>
				</select>
			</div>
			<div class="zhiding-list">
				<label>方式：</label>
				<select>
					<option>租房</option>
					<option>新房</option>
					<option>二手房</option>
				</select>
			</div>
			<div class="zhiding-list">
				<label>联系方式：</label>
				<input type="text"/>
			</div>
			<div class="zhidingsub"><input type="submit" value="提交"/></div>
		</form>
		<div class="zhidingtext">
			<h3>指定购房注意事宜：</h3>
			<p>1、请详细输入您所需要购买的房源信息(精确到小区)</p>
			<p>2、制定购房申请提交后，客服中心会在24小时之内与您取得联系</p>
			<p>3、如有任何疑问，请随时拨打我们的电话：400-000-0000</p>
		</div><!--zhidingtext/-->
	</div><!--zhidinggoufang/-->
	<jsp:include page="/WEB-INF/common/footer.jsp"/>


	</body>
	</html>
