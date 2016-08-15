<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	$(document).ready(function(){
	});
</script>
</head>
<body>
	<h2 class="itemTitle">新增</h2>
	<s:form action="createSubmit" method="post" validate="true" >		
		<ul>
			<li class="half">
				<b>帳號</b>
				<s:textfield name="sysUser.account" autocomplete="off"/>
			</li>
			<li class="half">
				<b>密碼</b>
				<s:password name="sysUser.password" autocomplete="off"/>
			</li>			
			<li class="half">
				<b>使用者姓名</b>
				<s:textfield name="sysUser.name" autocomplete="off"/>
			</li>
			<li class="half">
				<b>角色</b>
				<s:select name="sysUser.sysRole.id" list="sysRoleList" listKey="id" listValue="+name"/>
			</li>			
		</ul>
		
		<div class="clear"></div>
		
		<s:submit cssClass="btn btn-default redBtn" value="儲存" />	
		<input type="button" class="grayBtn" value="回上一頁" onclick="window.location.href='<s:url value="index"/>'" />
	</s:form>
</body>
</html>