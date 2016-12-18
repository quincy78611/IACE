<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn-back").click(function(){
			$("#form-backToIndex").submit();
		});	
	});
</script>
</head>
<body>
	<!-- Banner -->
	<s:include value="./banner.jsp" />
	
		<!-- Main -->
	<div class="container top50">
		<div class="row">
			<div class="col-sm-12 col-xs-12">
				<div>
					<div class="large_title_01">
						<i class="fa fa-file-text-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>公告訊息
					</div>
					<div class="line_blue">&nbsp;</div>
					<div class="line_gray1px bottom20"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- 左選單 -->
			<div class="col-sm-3 col-xs-12 bottom20">
				<s:include value="./left_menu.jsp" />
			</div>
			<!-- 右列表 -->
			<div class="col-sm-9 col-xs-12 bottom20">
				<div class="large_title_03"><s:property value="news.title"/></div>
				<div class="date_01">發佈日期：<s:date name="news.createTime" format="yyyy/M/d"/></div>
				<div class="date_01">資料來源：<s:property value="news.source"/></div>
				<div class="content_01">
					<ul class="list-inline text-right" style="margin-bottom:0;">
						<li class="nopadding">
							<span class="fa-stack fa-lg" style="color:#FFC642;"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-share-alt fa-stack-1x fa-inverse"></i></span>
						</li>
						<li class="nopadding">
							<span class="fa-stack fa-lg" style="color:#074790;"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-facebook fa-stack-1x fa-inverse"></i></span>
						</li>
						<li class="nopadding">
							<span class="fa-stack fa-lg" style="color:#00B3F4;"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-twitter fa-stack-1x fa-inverse"></i></span>
						</li>
						<li class="nopadding">
							<span class="fa-stack fa-lg" style="color:#DD2137;"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-pinterest-p fa-stack-1x fa-inverse"></i></span>
						</li>
						<li class="nopadding">
							<span class="fa-stack fa-lg" style="color:#EC4E20;"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-google-plus fa-stack-1x fa-inverse"></i></span>
						</li>
					</ul>
				</div>
				<div class="line_solid"></div>
				<div class="content_04">
					<span class="fa-stack fa-lg">
					<i class="fa fa-square fa-stack-2x"></i>
					<i class="fa fa-pencil-square-o fa-stack-1x fa-inverse"></i>
					</span>內容：
				</div>
				<div class="content_01 top10">
					<s:property value="news.content" escapeHtml="false"/>
				</div>
				<div class="top20">
					<button type="button" class="btn btn-default" id="btn-back">
						<i class="fa fa-angle-double-left right5" aria-hidden="true"></i>回上一頁
					</button>
				</div>
				<s:include value="./form-backToIndex.jsp" />
			</div>
		</div>
	</div>
</body>
</html>