<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">新增</h2>
	<s:form action="createSubmit" method="post" validate="true" >		
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