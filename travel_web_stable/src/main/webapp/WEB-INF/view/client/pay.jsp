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
		<!--nav/-->
		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--list-nav/-->


<div class="content">

	&emsp;<h2>购买门票<span class="close">X</span></h2>
	<form id="addForm">
		<input type="hidden" name="tid" id="tid" value="${id}">
		<div class="zhiding-list">
			<label>支付方式：</label>
			<select name="pay_way">
				<option value="微信支付">微信支付</option>
				<option value="支付宝">支付宝</option>
				<option value="云闪付">云闪付</option>
			</select>
		</div>
		<div class="zhiding-list">
			<label>购票数量：</label>
			<input type="number" name="quantity"/>
		</div>
		<div class="zhidingsub"><input id="save" value="购票"/></div>
	</form>
	<div class="zhidingtext">
		<h3>购票事宜：</h3>
		<p>1、请详细输入您所需要购买的门票数量并选择支付方式)</p>
		<p>2、购票提交后，客服中心会在24小时之内与您取得联系</p>
		<p>3、如有任何疑问，请随时拨打我们的电话：400-000-0000</p>
	</div><!--zhidingtext/-->


	<br>
	<br>
	<jsp:include page="/WEB-INF/common/footer.jsp"/>

	<script>

        $("#save").click(function () {
            $.ajax({
                cache: true,
                type: "post",
                url: "OrdersServlet?action=addOrders",
                data: $("#addForm").serialize(),
                async: false,
                success: function (e) {
                    if (e == 'yes') {
                        alert("购票成功！");
                        location.href = "IndexServlet?action=toMyOrder";
                    } else if (e == 'noMoney') {
                        alert("下单失败，余额不足！");
                    } else if (e == 'noTickets') {
                        alert("下单失败，库存不足！");
                    } else {
                        alert("下单失败,请登录后重试！");
                        location.href = "LoginServlet?action=toLogin";
                    }
                }
            })
        });

        function addCart(re) {
            $("#tid").val(re);
        }
	</script>
	</body>
	</html>
