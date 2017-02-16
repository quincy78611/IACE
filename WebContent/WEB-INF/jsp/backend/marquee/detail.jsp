<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="檢視"/>
</head>
<body>
	<ul>
		<li class="all">
			<b>文字</b>
			<div class="border-text">
				<s:property value="marquee.text"/>
			</div>			
		</li>
		<li class="all">
			<b>URL</b>
			<div class="border-text">
				<s:property value="marquee.url"/>
			</div>			
		</li>			
		<li class="half">
			<b>排序(數字愈大排愈前面)</b>
			<div class="border-text">
				<s:property value="marquee.sort"/>
			</div>			
		</li>
		<li class="half">
			<b>顯示狀態</b>
			<s:radio name="marquee.displayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" disabled="true"/>
		</li>
	</ul>
	<div class="clear"></div>
	
	<input type="button" class="grayBtn" value="回列表頁" onclick="window.location.href='<s:url value="index"/>'" />
</body>
</html>