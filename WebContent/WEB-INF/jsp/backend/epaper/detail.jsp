<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="編輯管理  > 檢視"/>

<script>
$(document).ready(function() {
	$("#btn-back").click(function(){
		$("#form-backToIndex").submit();
	});
});
</script>
</head>
<body>
	<ul>
		<li class="all">
			<b>標題</b>
			<div class="border-text">
				<s:property value="epaper.title"/>
			</div>
		</li>
		<div class="clear"></div>
		<li class="half">
			<b>期數</b>
			<div class="border-text">
				<s:property value="epaper.no"/>
			</div>
		</li>
		<li class="half">
			<b>發佈日</b>
			<div class="border-text">
				<s:property value="epaper.postDate"/>
			</div>
		</li>
	</ul>
	<div class="clear"></div>
	
	<div class="bottom-btn-block">
		<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
	</div>
	
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>