<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, Ticket-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>门票列表</title>
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
	<script>
        $(function () {
            const hrefArr = window.location.href.split('/');
            const name = hrefArr[hrefArr.length - 1];
            $('.menu a[href=\'' + name + '\']').addClass('active');
        });
	</script>
	<style>
        th, tr, td {
            border: 1px solid #ced4da;
        }
	</style>
</head>
<body>

<div id="app" class="d-flex">
	<jsp:include page="/WEB-INF/common/line.jsp"/>
	<main>
		<jsp:include page="/WEB-INF/common/head.jsp"/>
		<div id="body">
			<main>
				<div class="main">
					<div class="search">
						<form class="form-inline float-left" action="TicketServlet?action=TicketList"
							  method="post">
							<div class="form-group">
								<input type="text" name="key" value="${key}"
									   class="form-control" placeholder="景区等级">
							</div>
							<button type="submit" class="btn btn-primary">
								<i class="iconfont"></i>搜索
							</button>
						</form>


						<!-- <a class="btn btn-primary float-right" href="Ticket_create.html"><i
							class="iconfont"></i>新增</a> -->
					</div>

					<div>
						<table class="table">
							<thead>
							<tr>
								<th scope="col">景区等级</th>
								<th scope="col">景区名称</th>
								<th scope="col">门票价格</th>
								<th scope="col">门票库存</th>
								<th scope="col">上架时间</th>
								<th scope="col">操作</th>
							</tr>
							</thead>
							<tbody>

							<c:forEach items="${list}" var="data">
								<tr>
									<td>${data.level }</td>
									<td>${data.scenic.names }</td>
									<td>${data.price }￥</td>
									<td>${data.sotock }</td>
									<td>${data.create_date }</td>

									<td>
										<input id="${data.id}" value="${data.id}" type="hidden" class="weui-input"/>
										<a class="btn btn-primary btn-sm"
										   href="TicketServlet?action=toUpdateTicket&id=${data.id }">修改</a> <a
											class="btn btn-danger btn-sm delete"
											href="javascript:;">删除</a></td>
								</tr>
							</c:forEach>

							</tbody>
						</table>
					</div>

					<div class="page">
						<ul class="pagination">
							<li class="page-item"><a class="page-link"
													 href="TicketServlet?action=TicketList&p=1">首页</a></li>
							<c:if test="${cp>1}">
								<li class="page-item"><a class="page-link"
														 href="TicketServlet?action=TicketList&p=${cp-1}">上一页</a>
								</li>
							</c:if>
							<c:forEach begin="${cp-2>1 ? (cp-2) :1}"
									   end="${cp+2>tp?tp:(cp+2)}" var="e">
								<%--            判断是否是当前页--%>
								<c:if test="${cp==e}">
									<li class="page-item"><a class="page-link"
															 style="background-color: #007bff;color:white"
															 href="TicketServlet?action=TicketList&p=${e}">${e}</a></li>
								</c:if>
								<c:if test="${cp!=e}">
									<li class="page-item"><a class="page-link"
															 href="TicketServlet?action=TicketList&p=${e}">${e}</a></li>
								</c:if>
							</c:forEach>
							<c:if test="${cp<tp}">
								<li class="page-item"><a class="page-link"
														 href="TicketServlet?action=TicketList&p=${cp+1}">下一页</a>
								</li>
							</c:if>
							<li class="page-item"><a class="page-link"
													 href="TicketServlet?action=TicketList&p=${tp}">尾页</a></li>
						</ul>

					</div>
				</div>
			</main>
		</div>

	</main>
</div>
<script>

    $('a.delete').click(function (e) {

        if (confirm("确定删除吗?")) {
            var id = $(e.currentTarget).parent().find('.weui-input').attr('id');
            $.ajax({
                cache: true,
                url: "TicketServlet?action=deleteTicket",
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
