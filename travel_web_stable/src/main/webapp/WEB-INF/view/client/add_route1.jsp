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

			<form id="addRoute">

				<!--  <input type="hidden" name="type" value="1" /> -->
				<h3 class="vipright-title">发布旅游线路</h3>
				<%--   <input type="text" name="u_id" value="${sessionScope.user.id }" /> --%>
				<table class="grinfo">
					<tbody>
					<tr>
						<th><span class="red">*</span> 线路编号：</th>
						<td>
							<input class="inp inw" type="text" name="route_no" id="route_no" placeholder="101"
								   maxlength="14">
							<span id="msg1" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>线路名称：</th>
						<td>
							<input class="inp inw" type="text" name="name" id="name" maxlength="14">
							<span id="msg2" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span> 出发地：</th>
						<td>
							<input class="inp inw" type="text" name="start_spot" id="start_spot" maxlength="14">
							<span id="msg3" style="color:red"></span>
						</td>
					</tr>

					<tr>
						<th><span class="red">*</span> 目的地：</th>
						<td>
							<input class="inp inw" type="text" name="end_spot" id="end_spot" maxlength="14">
							<span id="msg4" style="color:red"></span>
						</td>
					</tr>

					<tr>
						<th><span class="red"></span> 线路详情</th>
						<td>
							<textarea id="note" name="note" class="grtextarea"></textarea>
							<span id="msg4" style="color:red"></span>
						</td>
					</tr>


					<tr>
						<th>&nbsp;</th>
						<td colspan="2">
							<label class="butt" id="butt">
								<input type="button" class="member_mod_buttom" id="save" value="确认发布"/>
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
        $("#save").click(function () {

            var route_no = $("#route_no").val();
            var name = $("#name").val();
            var start_spot = $("#start_spot").val();
            var end_spot = $("#end_spot").val();


            if (!route_no) {
                $("#msg1").html("线路编号不能为空！");
                $("#route_no").focus(); // 聚焦
                return false;
            }

            if (!name) {
                $("#msg3").html("线路名称不能为空！");
                $("#name").focus(); // 聚焦
                return false;
            }
            if (!start_spot) {
                $("#msg3").html("出发地不能为空！");
                $("#start_spot").focus(); // 聚焦
                return false;
            }

            if (!end_spot) {
                $("#msg4").html("目的地不能为空！");
                $("#end_spot").focus(); // 聚焦
                return false;
            }


        });


        $('#save').on("click", function (e) {
            $.ajax({
                cache: true,
                type: "post",
                url: "RouteServlet?action=addRoute",
                data: $("#addRoute").serialize(),
                async: false,
                success: function (e) {
                    if (e == 'yes') {
                        alert('发布成功');
                        location.href = "IndexServlet?action=toMyRoute";
                        /* layer.msg('修改成功', {
						 icon: 1,
						 time: 2000 //2秒关闭（如果不配置，默认是3秒）
					   }, function(){
							window.parent.location.href="IndexServlet?action=toMyinfo";
					   });   */

                    } else {
                        alert('发布失败');
                        layer.msg('发布失败,请登录后重试', {
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
