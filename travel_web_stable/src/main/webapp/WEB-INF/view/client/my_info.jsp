<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>
<div class="list-nav">
	<div class="width1190">
		<%-- <div class="list">
		 <h3>房源分类</h3>
		 <jsp:include page="/WEB-INF/common/top_left.jsp"/>
		</div><!--list/--> --%>
		<jsp:include page="/WEB-INF/common/nav.jsp"/>
		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--list-nav/-->
<!-- <div class="banner" style="background:url(images/ban.jpg) center center no-repeat;"></div> -->

<div class="content">
	<div class="width1190">
		<jsp:include page="/WEB-INF/common/left.jsp"/>
		<div class="vip-right">

			<form id="updateInfo">
				<input type="hidden" name="id" value="${user.id }"/>
				<!--  <input type="hidden" name="type" value="1" /> -->
				<h3 class="vipright-title">修改资料</h3>
				<table class="grinfo">
					<tbody>
					<tr>
						<th><span class="red">*</span> 姓名：</th>
						<td>
							<input class="inp inw" type="text" name="realname" id="realname" value="${user.realname}"
								   maxlength="14">
							<span id="msg1" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span> 手机号：</th>
						<td>
							<input class="inp inw" type="text" name="phone" id="phone" value="${user.phone}"
								   maxlength="14">
							<span id="msg2" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span> 昵称：</th>
						<td>
							<input class="inp inw" type="text" name="nickname" id="nickname" value="${user.nickname}"
								   maxlength="14">
							<span id="msg3" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span> 性 &nbsp; &nbsp;别：</th>
						<td>
							<input type="radio" value="女" name="sex" <c:if test="${user.sex=='女' }">checked</c:if>>
							<label for="rbSex1">女</label>
							<input type="radio" value="男" name="sex" <c:if test="${user.sex=='男' }">checked</c:if>>
							<label for="rbSex2">男</label>
							<span id="Sex_Tip"></span>
						</td>
					</tr>
					<tr>
						<th><span class="red"></span> 密 &nbsp; &nbsp;码：</th>
						<td>
							<input class="inp inw" type="text" id="pwd" name="pwd" value="${user.pwd }">
							<span id="msg4" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<th><span class="red"></span> 余 &nbsp; &nbsp;额：</th>
						<td>
							<input class="inp inw" type="text" id="money" name="money" value="${user.money }" readonly>
							<span id="msg4" style="color:red"></span>
						</td>
					</tr>


					<tr>
						<th>&nbsp;</th>
						<td colspan="2">
							<label class="butt" id="butt">
								<input type="button" class="member_mod_buttom" id="update" value="完成修改"/>
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

<script>
    $(function () {
        $("#update").click(function () {

            var realname = $("#realname").val();
            var phone = $("#phone").val();
            var nickname = $("#nickname").val();

            var pwd = $("#pwd").val();


            if (!realname) {
                $("#msg1").html("真实姓名不能为空！");
                $("#realname").focus(); // 聚焦
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


            if (!phone) {
                $("#msg2").html("手机号不能为空！");
                $("#phone").focus(); // 聚焦 */
                return false;
            }
        });


        $('#update').on("click", function (e) {
            $.ajax({
                cache: true,
                type: "post",
                url: "UserServlet?action=updateUser",
                data: $("#updateInfo").serialize(),
                async: false,
                success: function (e) {
                    if (e == 'yes') {
                        alert('修改成功');
                        location.href = "IndexServlet?action=toMyinfo";
                        /* layer.msg('修改成功', {
						 icon: 1,
						 time: 2000 //2秒关闭（如果不配置，默认是3秒）
					   }, function(){
							window.parent.location.href="IndexServlet?action=toMyinfo";
					   });   */

                    } else {
                        alert('修改失败');
                        layer.msg('修改失败,请登录后重试', {
                            icon: 5,
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        });
                    }
                }
            })
        });

    })


</script>
</body>
</html>
