<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">編輯</h2>
	<s:form action="updateSubmit" method="post" validate="true" >	
		<s:hidden name="sysFunction.id" />
		<s:hidden name="sysFunction.isValid" />
		<s:hidden name="sysFunction.createTime" />
		<s:hidden name="sysFunction.createUser" />
		<s:hidden name="sysFunction.updateTime" />
		<s:hidden name="sysFunction.updateUser" />
		<s:hidden name="sysFunction.ver" />	
		
		<ul>
			<li class="half">
				<b>功能名稱</b>
				<s:textfield name="sysFunction.displayName"/>
			</li>			
			<li class="half">
				<b>NameSpace</b>
				<s:textfield name="sysFunction.namespace"/>
			</li>	
			<li class="third">
				<b>ActionName1</b>
				<s:textfield name="sysFunction.actionName1"/>
			</li>
			<li class="third">
				<b>ActionName2</b>
				<s:textfield name="sysFunction.actionName2"/>
			</li>
			<li class="third">
				<b>ActionName3</b>
				<s:textfield name="sysFunction.actionName3"/>
			</li>
		</ul>
		<div class="clear"></div>
		
		<s:submit cssClass="btn btn-default redBtn" value="儲存" />	
		<input type="button" class="grayBtn" value="回上一頁" onclick="window.location.href='<s:url value="index"/>'" />
	</s:form>
</body>
</html>