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
		</ul><!--nav/-->
		<div class="clears"></div>
	</div><!--width1190/-->
</div>
<!--list-nav/-->
<

<div class="content">
	<div class="width1190">
		<div class="contleft">
			<ul class="leftNav">
				<li class="leftNavCur"><a href="IndexServlet?action=toAbout">关于我们</a></li>
				<li><a href="IndexServlet?action=toContactUs">联系我们</a></li>
			</ul><!--leftNav/-->
		</div><!--contleft/-->
		<div class="contright">
			<h2 class="rightat">公司简介</h2>
			<div class="about">
				<img src="images/about.jpg"/><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;从创立之初，百度便将“让人们最平等、便捷地获取信息，找到所求”作为自己的使命，成立以来，公司秉承“以用户为导向”的理念，不断坚持技术创新，致力于为用户提供“简单，可依赖”的互联网搜索产品及服务，其中包括：以网络搜索为主的功能性搜索，以贴吧为主的社区搜索，针对各区域、行业所需的垂直搜索，Mp3搜索，以及门户频道、IM等，全面覆盖了中文网络世界所有的搜索需求，根据第三方权威数据，百度在中国的搜索份额超过80%。
				<br/><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在面对用户的搜索产品不断丰富的同时，百度还创新性地推出了基于搜索的营销推广服务，并成为最受企业青睐的互联网营销推广平台。如今，中国已有数十万家企业使用了百度的搜索推广服务，不断提升企业自身的品牌及运营效率。通过持续的商业模式创新，百度正进一步带动整个互联网行业和中小企业的经济增长，推动社会经济的发展和转型。
				<br/><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为推动中国数百万中小网站的发展，百度借助超大流量的平台优势，联合所有优质的各类网站，建立了世界上最大的网络联盟，使各类企业的搜索推广、品牌营销的价值、覆盖面均大面积提升。与此同时，各网站也在联盟大家庭的互助下，获得最大的生存与发展机会。
				<br/><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作为国内的一家知名企业，百度也一直秉承“弥合信息鸿沟，共享知识社会”的责任理念，坚持履行企业公民的社会责任。成立来，百度利用自身优势积极投身公益事业，先后投入巨大资源，为盲人、少儿、老年人群体打造专门的搜索产品，解决了特殊群体上网难问题,极大地弥补了社会信息鸿沟问题。此外，在加速推动中国信息化进程、净化网络环境、搜索引擎教育及提升大学生就业率等方面，百度也一直走在行业领先的地位。2011年初，百度还特别成立了百度基金会，围绕知识教育、环境保护、灾难救助等领域，更加系统规范地管理和践行公益事业。
				<br/><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2005年，百度在美国纳斯达克上市，一举打破首日涨幅最高等多项纪录，并成为首家进入纳斯达克成分股的中国公司。通过数年来的市场表现，百度优异的业绩与值得依赖的回报，使之成为中国企业价值的代表，傲然屹立于全球资本市场。
			</div>
		</div><!--contright/-->
		<div class="clears"></div>
	</div><!--width1190/-->
	<input type="button" onclick="shareQQzone();" value="分享"></input>
</div>
<!--content/-->
<script src="http://qzonestyle.gtimg.cn/qzone/app/qzlike/qzopensl.js#jsdate=20111201" charset="utf-8"></script>
<script>
    //QQ空间分享方法:这样写可以对分享事件进行绑定
    function shareQQzone() {
        var _url = 'www.wodexiangce.cn';
        var _showcount = '0'
        var _desc = '分享旅游网';
        var _summary = '大家都来看看吧';
        var _title = '真不错';
        var _site = '我的相册网';
        var _pic = '';
        var _width = '800px';
        var _height = '500px';

        var _shareUrl = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?';
        _shareUrl += 'url=' + encodeURIComponent(_url || document.location);   //参数url设置分享的内容链接|默认当前页location
        _shareUrl += '&showcount=' + _showcount || 0;      //参数showcount是否显示分享总数,显示：'1'，不显示：'0'，默认不显示
        _shareUrl += '&desc=' + encodeURIComponent(_desc || '分享的描述');    //参数desc设置分享的描述，可选参数
        _shareUrl += '&summary=' + encodeURIComponent(_summary || '分享摘要');    //参数summary设置分享摘要，可选参数
        _shareUrl += '&title=' + encodeURIComponent(_title || document.title);    //参数title设置分享标题，可选参数
        _shareUrl += '&site=' + encodeURIComponent(_site || '');   //参数site设置分享来源，可选参数
        _shareUrl += '&pics=' + encodeURIComponent(_pic || '');   //参数pics设置分享图片的路径，多张图片以＂|＂隔开，可选参数
        window.open(_shareUrl, '_blank', 'width=' + _width + ',height=' + _height + ',top=' + (screen.height - _height) / 2 + ',left=' + (screen.width - _width) / 2 + ',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
    }
</script>
<jsp:include page="/WEB-INF/common/footer.jsp"/>


</body>
</html>
