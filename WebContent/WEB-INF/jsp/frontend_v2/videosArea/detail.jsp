<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn-back").click(function() {
			$("#form-backToIndex").submit();
		});
	});
</script>
<style>
.videos iframe { width:100%; height:auto; }
</style>
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
						<i class="fa fa-calendar-check-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>影音專區
					</div>
					<div class="line_blue">&nbsp;</div>
					<div class="line_gray1px bottom20"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- 右列表 -->
			<div class="col-sm-9 col-xs-12 bottom20">
				<div class="large_title_03">
					<s:property value="videosArea.title" />
				</div>
				<div class="date_01">
					發佈日期：<s:date name="videosArea.createTime" format="yyyy/M/d" />
				</div>
				<div class="date_01">
					資料來源：<s:property value="videosArea.source" />
				</div>
				<div class="content_01">
					<div class="list-inline text-right" style="margin-bottom: 0;">
						<s:include value="../share-buttons.jsp" />
					</div>	
				</div>
				<div class="line_solid"></div>
				<div class="content_04">
					<span class="fa-stack fa-lg"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-calendar fa-stack-1x fa-inverse"></i></span>
					活動日期：<s:property value="videosArea.actDate" />
				</div>
				<div class="content_04">
					<span class="fa-stack fa-lg"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-map-marker fa-stack-1x fa-inverse"></i></span>
					活動地點：<s:property value="videosArea.actAddress" />
				</div>
				<div class="top20">
					<table class="table table-striped content_01">
						<tbody>
							<tr class="info">
								<td width="15%">主辦單位</td>
								<td><s:property value="videosArea.organizer" /></td>
							</tr>
							<tr>
								<td>指導單位</td>
								<td><s:property value="videosArea.advisor" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="content_04">
					<span class="fa-stack fa-lg"> 
						<i class="fa fa-square fa-stack-2x"></i> 
						<i class="fa fa-pencil-square-o fa-stack-1x fa-inverse"></i>
					</span>
					內容：
				</div>
				<div class="content_01 top10">
					<s:property value="videosArea.content" escapeHtml="false"/>
				</div>
				<div class="line_solid"></div>
				<div class="content_04">
					<span class="fa-stack fa-lg"> 
						<i class="fa fa-square fa-stack-2x"></i> 
						<i class="fa fa-camera-retro fa-stack-1x fa-inverse"></i>
					</span>
					影片：
				</div>
				<div class="row videos">
					<s:iterator value="videosArea.videoList" status="stat">
						<div class="col-sm-4 col-xs-12" style="margin-top:15px">
							<s:url namespace="/file" action="downloadFile" escapeAmp="false" var="downloadVideoUrl">
								<s:param name="folderConfigKey" value="%{'videoFolder'}" />
								<s:param name="downloadFileSubPath" value="fileSubPath" />
							</s:url>
							<video 
								src="<s:property value="downloadVideoUrl" />" 
								controls="controls" preload="metadata" 
								style="max-width:100%; ">
							</video>
							<div class="text-center" style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">
								<s:property value="fileTitle"/>
							</div>
						</div>	
					</s:iterator>
				</div>
				<div class="line_solid"></div>
				<div class="top20">
					<button type="button" class="btn btn-default" id="btn-back">
						<i class="fa fa-angle-double-left right5" aria-hidden="true"></i>回列表
					</button>
				</div>
				<s:include value="./form-backToIndex.jsp" />
			</div>
		</div>
	</div>
</body>
</html>