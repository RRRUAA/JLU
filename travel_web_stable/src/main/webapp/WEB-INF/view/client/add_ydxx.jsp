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
		<form id="addYdxx">
			<input type="hidden" name="uid" value="${user.id }"/>
			<input type="hidden" name="room_id" value="${id }"/>
			<h3 class="vipright-title">预订</h3>
			<table class="grinfo">
				<tbody>
				<tr>
					<th><span class="red">*</span> 天数：</th>
					<td>
						<input class="inp inw" type="number" name="days" id="days" maxlength="14">
						<span id="msg1" style="color:red"></span>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>开始日期：</th>
					<td>
						<input class="inp inw" type="date" name="start_date" id="start_date" maxlength="14">
						<span id="msg2" style="color:red"></span>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span> 结束日期：</th>
					<td>
						<input class="inp inw" type="date" name="end_date" id="end_date" maxlength="14">
						<span id="msg3" style="color:red"></span>
					</td>
				</tr>


				<tr>
					<th><span class="red"></span> 备&emsp;注</th>
					<td>
						<textarea id="note" name="note" class="grtextarea"></textarea>
						<span id="msg4" style="color:red"></span>
					</td>
				</tr>


				<tr>
					<th>&nbsp;</th>
					<td colspan="2">
						<label class="butt" id="butt">
							<input type="button" class="member_mod_buttom" id="save" value="确定预订"/>
						</label>
					</td>
				</tr>
				</tbody>
			</table>
		</form>

	</div>
</div>


<jsp:include page="/WEB-INF/common/footer.jsp"/>
<script>
    $(function () {
        $("#save").click(function () {

            var days = $("#days").val();
            var start_date = $("#start_date").val();
            var end_date = $("#end_date").val();


            if (!days) {
                $("#msg1").html("天数不能为空！");
                $("#days").focus(); // 聚焦
                return false;
            }

            if (!start_date) {
                $("#msg3").html("开始日期不能为空！");
                $("#start_date").focus(); // 聚焦
                return false;
            }
            if (!end_date) {
                $("#msg3").html("结束日期不能为空！");
                $("#end_date").focus(); // 聚焦
                return false;
            }


        });


        $('#save').on("click", function (e) {
            $.ajax({
                cache: true,
                type: "post",
                url: "YdxxServlet?action=addYdxx",
                data: $("#addYdxx").serialize(),
                async: false,
                success: function (e) {
                    if (e == 'yes') {
                        alert('预订成功');
                        location.href = "IndexServlet?action=toMyYdxx";
                        /* layer.msg('修改成功', {
						 icon: 1,
						 time: 2000 //2秒关闭（如果不配置，默认是3秒）
					   }, function(){
							window.parent.location.href="IndexServlet?action=toMyinfo";
					   });   */

                    } else {
                        alert('预订失败,请先登录');
                        location.href = "LoginServlet?action=toLogin";
                        /*  layer.msg('发布失败,请登录后重试', {
							  icon: 5,
							  time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}); */
                    }
                }
            })
        });

    })


</script>

</body>
</html>
