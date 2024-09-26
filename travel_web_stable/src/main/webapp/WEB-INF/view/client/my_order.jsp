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
		</ul><!--nav/-->
		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--list-nav/-->


<div class="content">
	<div class="width1190">
		<jsp:include page="/WEB-INF/common/left.jsp"/>
		<div class="vip-right">
			<h3 class="vipright-title">我的订单</h3>
			<ul class="guanzhueq">
				<li class="guanzhueqcur"><a href="javascript:;">订单列表</a></li>

				<div class="clearfix"></div>
			</ul><!--guanzhueq/-->
			<div class="guanzhulist">
				<c:forEach items="${ordersList }" var="data">
					<dl>
							<%-- <dt><a href=""><img src="upload/${data.scenic.photo}" width="190" height="128" /></a></dt>  --%>
						<dd>
								<%--   <h3><a href="HouseServlet?action=detail&id=${g.house.id }">${g.house.title }</a></h3> --%>
							<div class="guantext">订单号：
									${data.ordernum }&emsp;下单人：${data.uname}&emsp;数量：${data.quantity}</div>
							<div class="guantext"></div>
							<div class="guantext">总金额：${data.total_price}</div>
							<div class="guantext">下单时间：${data.create_time}</div>
						</dd>
						<div>
								<%--  ${data.status} --%>
							<c:if test="${data.status==1 }">
								已评价
							</c:if>
							<c:if test="${data.status==0 }">
								<a href="IndexServlet?action=toComment&tid=${data.tid }&oid=${data.id}"
								   style="color:#acce22">去评论</a>
							</c:if>
							</li></div>


						<div class="clearfix"></div>
					</dl>
				</c:forEach>

			</div><!--guanzhulist/-->

		</div><!--vip-right/-->
		<div class="clearfix"></div>
	</div><!--width1190/-->
</div>
<!--content/-->

<jsp:include page="/WEB-INF/common/footer.jsp"/>


</body>
</html>
