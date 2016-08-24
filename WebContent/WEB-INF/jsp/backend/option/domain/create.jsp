<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="新增"/>
</head>
<body>
<!-- 	<h2 class="itemTitle">新增</h2> -->
	<s:form action="createSubmit" method="post" validate="true" >		
		<ul>
			<li class="half">
				<b>代碼</b>
				<s:textfield name="option.code"/>
			</li>			
			<li class="half">
				<b>名稱</b>
				<s:textfield name="option.name"/>
			</li>			
		</ul>
		
		<s:submit cssClass="btn btn-default redBtn" value="儲存" />	
		<input type="button" class="grayBtn" value="回上一頁" onclick="window.location.href='<s:url value="index"/>'" />
	</s:form>
</body>
</html>