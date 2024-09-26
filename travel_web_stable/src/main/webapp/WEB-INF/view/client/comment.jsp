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
			<h3 class="vipright-title">门票景点信息</h3>

			<dl class="vip-touxiang">
				<dt><img src="upload/${scenic.photo }" width="300" height="80"/></dt>
				<dd>
					<h3>${scenic.names }</h3>
					<div class="sctx">门票价格：${ticket.price }</div>
					<p>景点等级：${ticket.level }</p>
				</dd>
				<div class="clearfix"></div>
			</dl><!--vip-touxiang/-->
			</form>
			<form id="addComment">
				<%-- <input type="hidden" name="id" value="${user.id }" /> --%>
				<input type="hidden" name="oid" value="${oid}"/>
				<h3 class="vipright-title">评价景点</h3>
				<table class="grinfo">
					<tbody>
					<%--    <tr>
					   <th><span class="red">*</span> 姓名：</th>
					   <td>
					   <input class="inp inw" type="text" name="realname" id="realname" value="${user.realname}" maxlength="14">
						   <span id="msg1" style="color:red"></span>
					   </td>
					   </tr> --%>
					<tr>
						<th valign="top">评价内容：</th>
						<td>
							<textarea id="content" name="content" class="grtextarea"></textarea>
							<span id="msg1" style="color:red"></span>
							<br>
							<span class="fgrey">(128字符以内)</span>
						</td>
					</tr>


					<tr>
						<th>&nbsp;</th>
						<td colspan="2">
							<label class="butt" id="butt">
								<input type="button" class="member_mod_buttom" id="add" value="立即评价"/>
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

            var content = $("#content").val();


            if (!content) {
                $("#msg1").html("评价内容不许为空！");
                $("#content").focus(); // 聚焦
                return false;
            }


        });


        $('#add').on("click", function (e) {
            $.ajax({
                cache: true,
                type: "post",
                url: "CommentsServlet?action=addComments",
                data: $("#addComment").serialize(),
                async: false,
                success: function (e) {
                    if (e == 'yes') {
                        alert('评价成功');
                        location.href = "IndexServlet?action=toMyComment";
                        /* layer.msg('修改成功', {
						 icon: 1,
						 time: 2000 //2秒关闭（如果不配置，默认是3秒）
					   }, function(){
							window.parent.location.href="IndexServlet?action=toMyinfo";
					   });   */

                    } else {
                        alert('评价失败');

                    }
                }
            })
        });

    })


</script>
</body>
</html>
