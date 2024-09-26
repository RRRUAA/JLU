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

			<form id="addMoney">
				<input type="hidden" name="id" value="${user.id }"/>
				<!--  <input type="hidden" name="type" value="1" /> -->
				<h3 class="vipright-title">余额充值</h3>
				<table class="grinfo">
					<tbody>
					<tr>
						<th><span class="red">*</span> 充值金额：</th>
						<td>
							<input class="inp inw" type="number" name="money" id="money" maxlength="14">
							<span id="msg1" style="color:red"></span>
						</td>
					</tr>


					<tr>
						<th>&nbsp;</th>
						<td colspan="2">
							<label class="butt" id="butt">
								<input type="button" class="member_mod_buttom" id="add" value="立即充值"/>
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
        $("#add").click(function () {

            var money = $("#money").val();


            if (!money) {
                $("#msg1").html("重置金额不能为空！");
                $("#money").focus(); // 聚焦
                return false;
            }


        });

        $('#add').on("click", function (e) {
            $.ajax({
                cache: true,
                type: "post",
                url: "UserServlet?action=addMoney",
                data: $("#addMoney").serialize(),
                async: false,
                success: function (e) {
                    if (e == 'yes') {
                        alert('充值成功');
                        location.href = "IndexServlet?action=toMyYue";//查看我的余额
                    } else {
                        alert('充值失败');
                    }
                }
            })
        });


    })


</script>
</body>
</html>
