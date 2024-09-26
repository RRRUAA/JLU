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
	<div class="width1190">

		<div class="vip">
			<h3 class="vipright-title">游客注册</h3>
			<form id="registeForm">
				<dl class="vip-touxiang">
					<dt><img src="images/upload.png" id="preview_img" width="100" height="100"/></dt>
					<dd>
						<h3><strong>点击选择文件上传头像</strong></h3>
						<div class="sctx"><input type="file" name="photo" id="tx"/><a href="javascript:;">上传</a></div>
						<p>图片格式：GIF、JPG、JPEG、PNG ，最适合尺寸100*100像素</p>
					</dd>
					<div class="clearfix"></div>
				</dl><!--vip-touxiang/-->


				<table class="grinfo">
					<span id="msg" style="color:red">${msg }</span>
					<tbody>
					</tr>
					<tr>
						<th><span class="red">*</span> 姓名：</th>
						<td>
							<input class="inp inw" type="realname" name="realname" id="realname" value=""
								   maxlength="14">
							<span id="msg1" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<th>手机号：</th>
						<td>
							<input class="inp inw" type="text" name="phone" id="phone" value="" maxlength="14">
							<span id="msg2" style="color:red"></span>
						</td>

					<tr>
						<th><span class="red">*</span> 性 &nbsp; &nbsp;别：</th>
						<td>
							<input type="radio" value="2" id="sex" name="sex">
							<label for="rbSex1">女</label>
							<input type="radio" value="1" id="sex" name="sex" checked>
							<label for="rbSex2">男</label>
							<span id="Sex_Tip"></span>
						</td>
					</tr>

					<tr>
						<th><span class="red"></span> 昵&emsp;称：</th>
						<td>
							<input class="inp inw" type="text" id="nickname" name="nickname" value="">
							<span id="msg3" style="color:red"></span>
						</td>
					</tr>


					<tr>
						<th>&nbsp;密&nbsp; &nbsp;码：</th>
						<td>
							<input class="inp inw" type="text" maxlength="15" value="" id="pwd" name="pwd">
							<span id="msg4" style="color:red"></span>
						</td>
					</tr>


					<tr>
						<th>&nbsp;</th>
						<td colspan="2">
							<label class="butt" id="butt">
								<input type="button" value="立即注册" id="add" class="member_mod_buttom"/>

							</label>
						</td>
					</tr>
					</tbody>
				</table>
			</form>
		</div><!--vip-right/-->
		<div class="clearfix"></div>
	</div><!--width1190/-->
</div>
<!--content/-->


<jsp:include page="/WEB-INF/common/footer.jsp"/>
<script type="text/javascript">

    $('#update').on("click", function (e) {

    });
    $("#tx").change(function () {
        //创建blob对象，浏览器将文件放入内存中，并生成标识
        var img_src = URL.createObjectURL($(this)[0].files[0]);
        //给img标检的src赋值
        document.getElementById("preview_img").src = img_src;
        //URL.revokeObjectURL(img_src);// 手动 回收，
    });
    $(function () {
        $("#add").click(function () {
            var tx = $("#tx").val();
            var realname = $("#realname").val();
            var phone = $("#phone").val();

            var nickname = $("#nickname").val();
            var pwd = $("#pwd").val();

            if (!tx) {
                $("#msg").html("请上传头像");
                $("#tx").focus(); // 聚焦
                return false;
            }

            if (!realname) {
                $("#msg1").html("真实姓名不能为空！");
                $("#realname").focus(); // 聚焦
                return false;
            }

            if (!phone) {
                $("#msg2").html("手机号不能为空！");
                $("#phone").focus(); // 聚焦
                return false;
            }

            if (!nickname) {
                $("#msg3").html("昵称不能为空！");
                $("#nickname").focus(); // 聚焦
                return false;
            }


            if (!pwd) {
                $("#msg4").html("密码不能为空！");
                $("#pwd").focus(); // 聚焦
                return false;
            }

            $.ajax({
                cache: true,
                type: "post",
                url: "UserServlet?action=addUser",
                data: new FormData($('#registeForm')[0]),
                processData: false,
                contentType: false,
                async: false,
                success: function (e) {
                    if (e == 'yes') {
                        alert('注册成功');
                        location.href = "LoginServlet?action=toLogin";
                    } else {
                        alert('注册失败');
                    }
                }
            })


        });
    })
</script>

</body>
</html>
