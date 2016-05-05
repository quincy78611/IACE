<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產業/領域別</title>
</head>
<body>
	<h2>新增</h2>
	<s:form action="createSubmit" method="post" validate="true" >
		<div class="form-horizontal" >
			<s:textfield label="代碼" name="optionIndustry.code" cssClass="form-control" cssErrorClass="form-control" />
			<s:textfield label="名稱" name="optionIndustry.name" cssClass="form-control" cssErrorClass="form-control" />				
			<s:submit cssClass="btn btn-default" value="儲存" />	
			<input type ="button" class="btn btn-default" onclick="history.back()" value="回上一頁"/>		
		</div>
	</s:form>
</body>
</html>