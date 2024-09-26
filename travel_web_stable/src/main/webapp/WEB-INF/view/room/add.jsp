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
		  content="width=device-width, Scenic-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>添加房间</title>
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
						<h5>新增房间</h5>
					</div>
					<div>
						<div class="row">
							<div class="col-5">
								<form id="saveForm" enctype="multipart/form-data">
									<div class="form-group">
										<label>房间名称</label> <input type="text" name="title"
																	   class="form-control"> <small
											class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>酒店名称</label> <input type="text" name="name"
																	   class="form-control"> <small
											class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>房间类型</label>
										<select name="category" class="form-control">
											<option value="普通单人间">普通单人间</option>
											<option value="普通双人间">普通双人间</option>
											<option value="豪华单人间">豪华单人间</option>
											<option value="豪华双人间">豪华双人间</option>

										</select>
										<small
												class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>房间价格</label>
										<input type="number" name="price"
											   class="form-control">
										<small
												class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>手机号</label>
										<input type="text" name="phone"
											   class="form-control">
										<small
												class="form-text text-muted"></small>
									</div>

									<div class="form-group">
										<label>封面</label>
										<img class="layui-upload-img" style="width:200px;height:100px;" id="demo1">
										<input type="file" name="photo" id="photo">
										<small class="form-text text-muted"></small>

									</div>

									<div class="form-group">
										<label>酒店详情</label>
										<script id="container" name="note" type="text/plain"></script>
										<small class="form-text text-muted"></small>
									</div>


									<div class="form-group">
										<label>酒店地址</label>
										<textarea class="form-control" id="address" name="address" rows="3"></textarea>
										<small class="form-text text-muted"></small>
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
            url: "RoomServlet?action=addRoom",
            data: new FormData($('#saveForm')[0]),
            processData: false,
            contentType: false,
            async: false,
            success: function (e) {
                if (e == "yes") {
                    alert("新增成功！");
                    window.parent.location.href = "RoomServlet?action=RoomList";
                } else if (e == "isExist") {
                    alert("新增失败,手机号重复");
                } else {
                    alert("新增失败");
                }
            }
        })
    });
</script>


<script>

    layui.use(['form', 'jquery', 'layer', 'laydate', 'upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            laydate = layui.laydate,
            upload = layui.upload,
            $ = layui.jquery;
        form.render();//这句一定要加，占坑

        /*   laydate.render({
		  elem: '#schoolTime'
		});
		  laydate.render({
		  elem: '#day'
		});  */

        $("#photo").change(function () {
            //创建blob对象，浏览器将文件放入内存中，并生成标识
            var img_src = URL.createObjectURL($(this)[0].files[0]);
            //给img标检的src赋值
            document.getElementById("demo1").src = img_src;
            //URL.revokeObjectURL(img_src);// 手动 回收，
        });

    });


</script>


</body>
</html>
