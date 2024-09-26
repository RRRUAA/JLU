<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>
　　
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<h3 class="vipright-title">我的预订酒店信息</h3>
			<ul class="guanzhueq">
				<li class="guanzhueqcur"><a href="javascript:;">预订信息列表</a></li>

				<div class="clearfix"></div>
			</ul><!--guanzhueq/-->
			<div class="guanzhulist">
				<c:forEach items="${ydxxs }" var="data">
					<dl>
							<%--  <dt><a href=""><img src="upload/${data.uphoto}" width="80" height="120" /></a></dt>  --%>
						<dd>
								<%--   <h3><a href="HouseServlet?action=detail&id=${g.house.id }">${g.house.title }</a></h3> --%>
							<div class="guantext">订单编号：${data.ydbh}</div>
							<div class="guantext">下单人：${data.user.nickname}</div>
							<div class="guantext">手机号：${data.user.phone}</div>
							<div class="guantext">酒店名称：${data.room.name}</div>
							<div class="guantext">房间类型：${data.room.category}</div>
							<div class="guantext">总金额：${data.total_price}</div>
							<div class="guantext">开始时间：${data.start_date}</div>
							<div class="guantext">结束时间：${data.end_date}</div>
							<div class="guantext">下单时间：<fmt:formatDate value="${data.create_time}"
																		   pattern="yyyy-MM-dd HH:mm:ss"/></div>
						</dd>
						<div>
							<input id="${data.id}" value="${data.id}" type="hidden" class="weui-input"/>
							<a class="btn btn-danger btn-sm delete"
							   href="javascript:;">删除</a></li></div>
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
<script>

    $('a.delete').click(function (e) {
        var id = $(e.currentTarget).parent().find('.weui-input').attr('id');
        /* alert(id); */
        if (confirm("确定删除吗?")) {
            $.ajax({
                cache: true,
                url: "YdxxServlet?action=deleteYdxx",
                data: {"id": id},
                type: "post",
                async: false,
                success: function (data) {
                    console.log(data)
                    if (data == 'yes') {
                        alert("删除成功！");
                        document.location.reload();//当前页面
                    } else {
                        alert("删除失败！");
                    }
                }
            });
        }

    })

</script>

</body>
</html>
