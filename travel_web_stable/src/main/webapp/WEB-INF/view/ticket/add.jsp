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
	<title>添加门票</title>
	<link rel="icon" href="<%=path%>/resource/static/favicon.ico">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/index.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/layui/css/layui.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/main.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/html.css">
	<script src="<%=path%>/resource/static/js/vue.min.js"></script>
	<script src="<%=path%>/resource/static/js/jquery-3.3.1.min.js"></script>
	<script src="<%=path%>/resource/static/bootstrap/js/bootstrap.bundle.js"></script>
	<script src="<%=path%>/resource/static/admin/js/config.js"></script>
	<script src="<%=path%>/resource/static/admin/js/script.js"></script>
	<script src="<%=path%>/resource/layui/layui.js"></script>
	<script>
        $(function () {
            const hrefArr = window.location.href.split('/');
            const name = hrefArr[hrefArr.length - 1];
            $('.menu a[href=\'' + name + '\']').addClass('active');
        });
	</script>
</head>
<body>

<div id="app" class="d-flex">
	<jsp:include page="/WEB-INF/common/line.jsp"/>

	<main>
		<jsp:include page="/WEB-INF/common/head.jsp"/>

		<div id="body">
			<main>
				<div class="main">

					<div class="title-box">
						<h5>新增门票</h5>
					</div>
					<div>
						<div class="row">
							<div class="col-5">
								<form id="saveForm">
									<div class="form-group">
										<label>景区级别</label>
										<select name="level" class="form-control">
											<option value="国家A级旅游景区">国家A级旅游景区</option>
											<option value="国家AA级旅游景区">国家AA级旅游景区</option>
											<option value="国家AAA级旅游景区">国家AAA级旅游景区</option>
											<option value="国家AAAA级旅游景区">国家AAAA级旅游景区</option>
											<option value="国家AAAAA级旅游景区">国家AAAAA级旅游景区</option>

										</select>
										<small
												class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>景点</label> <select name="sid" class="form-control">
										<c:forEach items="${scenicList}" var="data">
											<option value="${data.id }">${data.names }</option>
										</c:forEach>
									</select> <small class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>票价</label> <input type="text" name="price"
																   class="form-control"> <small
											class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>库存</label> <input type="number" name="sotock"
																   class="form-control"> <small
											class="form-text text-muted"></small>
									</div>

								</form>
							</div>
						</div>
					</div>

					<div class="submit-box">
						<button type="button" id="save" class="btn btn-primary">确定提交</button>
						<!--             <button type="button" onclick="window.history.back()" class="btn btn-outline-secondary">取消</button>
-->
					</div>
				</div>
			</main>


		</div>

	</main>
</div>
<script>
    $("#save").click(function () {
        $.ajax({
            cache: true,
            type: "post",
            url: "TicketServlet?action=addTicket",
            data: $("#saveForm").serialize(),
            async: false,
            success: function (e) {
                if (e == "yes") {
                    alert("新增成功！");
                    window.parent.location.href = "TicketServlet?action=TicketList";
                } else if (e == "isExist") {
                    alert("新增失败,手机号重复");
                } else {
                    alert("新增失败");
                }
            }
        })
    });
</script>

</body>
</html>
