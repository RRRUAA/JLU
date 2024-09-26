<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>


<div class="content">
	<div class="width1190">
		<form action="IndexServlet?action=toSituationIndex" method="post" class="pro-search">
			<table>

				<tr>
					<th>人数：</th>
					<td>
						<!--
							<input type="text" class="protext" name="name"   style="width:440px;"/> -->
						<input type="number" class="protext" name="price1" value="${price1 }" style="width:40px;"/> -
						<input type="number" class="protext" name="price2" value="${price2 }" style="width:40px;"/> 人
						<input type="submit" value="确定" class="btn btn-success btn-lg  btn-block"/>

					</td>
				</tr>

			</table>
			<div class="paixu">
				<strong>排序：</strong>
				<a href="#" class="pai-cur">默认</a>
				<a href="IndexServlet?action=toSearchIndex&orderBy=1">人数 &or;</a>
				<!--a href="IndexServlet?action=toSearchIndex&orderBy=2">最新 &or;</a-->
			</div>
		</form><!--pro-search/-->
	</div><!--width1190/-->
</div>
