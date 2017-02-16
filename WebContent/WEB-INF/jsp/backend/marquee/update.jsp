<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="編輯"/>
</head>
<body>
	<s:form action="updateSubmit" method="post" validate="true" >
		<s:hidden name="marquee.id"/>
		<s:hidden name="marquee.isValid"/>
		<s:hidden name="marquee.createTime"/>
		<s:hidden name="marquee.createUser"/>
		<s:hidden name="marquee.updateTime"/>
		<s:hidden name="marquee.updateUser"/>
		<s:hidden name="marquee.ver"/>
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
				<s:textfield name="marquee.sort" type="number"/>
			</li>
			<li class="half">
				<b>顯示狀態</b>
				<s:radio name="marquee.displayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList"/>
			</li>
		</ul>
		<div class="clear"></div>
		
		<s:submit cssClass="btn btn-default redBtn" value="送出" />	
		<input type="button" class="grayBtn" value="回上一頁" onclick="window.location.href='<s:url value="index"/>'" />
	</s:form>
</body>
</html>