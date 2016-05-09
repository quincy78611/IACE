<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<h4>刪除</h4>
	<s:form action="deleteSubmit" method="post" validate="true" >
		<div class="form-horizontal" >
			<s:hidden name="option.id" />
			<s:hidden name="option.isValid" />
			<s:hidden name="option.createTime" />
			<s:hidden name="option.createUser" />
			<s:hidden name="option.updateTime" />
			<s:hidden name="option.updateUser" />
			<s:hidden name="option.ver" />
			
			<s:textfield label="代碼" name="option.code" cssClass="form-control" cssErrorClass="form-control" readonly="true"/>
			<s:textfield label="名稱" name="option.name" cssClass="form-control" cssErrorClass="form-control" readonly="true"/>				

			<s:submit cssClass="btn btn-danger" value="確定" />	
			<input type ="button" class="btn btn-default" onclick="history.back()" value="回上一頁"/>		
		</div>
	</s:form>
</body>
</html>