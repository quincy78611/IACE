<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="新增"/>
</head>
<body>
	<s:form action="createSubmit" method="post" validate="true" >		
		<ul>
			<li class="all">
				<b>文字</b>
				<s:textfield name="marquee.text" maxlength="100"/>
			</li>
			<li class="all">
				<b>URL</b>
				<s:textfield name="marquee.url" maxlength="500"/>
			</li>			
			<li class="half">
				<b>排序(數字愈大排愈前面)</b>
				<s:textfield name="marquee.sort" type="number" value="0"/>
			</li>
			<li class="half">
				<b>顯示狀態</b>
				<s:radio name="marquee.displayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" value="true"/>
			</li>
		</ul>
		<div class="clear"></div>
		
		<s:submit cssClass="btn btn-default redBtn" value="送出" />	
		<input type="button" class="grayBtn" value="回列表頁" onclick="window.location.href='<s:url value="index"/>'" />
	</s:form>
</body>
</html>