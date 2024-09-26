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
		  content="width=device-width, data-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>添加公告</title>
	<link rel="icon" href="<%=path%>/resource/static/favicon.ico">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/index.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/main.css">
	<link rel="stylesheet"
		  href="<%=path%>/layui/css/layui.css">
	<link rel="stylesheet"
		  href="<%=path%>/resource/static/admin/css/html.css">
	<script src="<%=path%>/resource/static/js/vue.min.js"></script>
	<script src="<%=path%>/resource/static/js/jquery-3.3.1.min.js"></script>
	<script src="<%=path%>/resource/static/bootstrap/js/bootstrap.bundle.js"></script>
	<script src="<%=path%>/resource/static/admin/js/config.js"></script>
	<script src="<%=path%>/resource/static/admin/js/script.js"></script>
	<script src="<%=path%>/layui/layui.js"></script>
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
						<h5>修改公告</h5>
					</div>
					<div>
						<div class="row">
							<div class="col-5">
								<form id="saveForm">
									<input type="hidden" name="id" value="${data.id }">
									<div class="form-group">
										<label>标题</label> <input type="text" name="title" value="${data.title }"
																   class="form-control"> <small
											class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>内容</label>
										<script id="container" name="content"
												type="text/plain">${data.content }</script>
										<small
												class="form-text text-muted"></small>
									</div>

								</form>
							</div>
						</div>
					</div>

					<div class="submit-box">
						<button type="button" id="save" class="btn btn-primary">确定提交</button>
						<a type="button" href="BoardServlet?action=BoardList" class="btn btn-outline-secondary">返回</a>
					</div>
				</div>
			</main>
		</div>
	</main>
</div>
<script src="<%=path %>/resource/layui/layui.js"></script>
<script src="<%=path %>/resource/js/ueditor/ueditor.config.js"></script>
<script src="<%=path %>/resource/js/ueditor/ueditor.all.min.js"></script>
<script src="<%=path %>/resource/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    var ue = UE.getEditor('container', {
        initialFrameWidth: 800,
        //   initialFrameHeight:400,
    });
</script>
<script>
    $("#save").click(function () {
        $.ajax({
            cache: true,
            type: "post",
            url: "BoardServlet?action=updateBoard",
            data: $("#saveForm").serialize(),
            async: false,
            success: function (e) {
                if (e == 'yes') {
                    alert("修改成功！");
                    window.parent.location.href = "BoardServlet?action=BoardList";
                } else {
                    alert("修改失败！");
                }
            }
        })
    });
</script>

</body>
</html>
