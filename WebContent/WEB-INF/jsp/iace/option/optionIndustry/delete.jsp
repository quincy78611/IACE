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
	<h2>刪除</h2>

	<s:form action="deleteSubmit" method="post" validate="true" >
		<div class="form-horizontal" >
			<s:hidden name="optionIndustry.id" />
			<s:hidden name="optionIndustry.isValid" />
			<s:hidden name="optionIndustry.createTime" />
			<s:hidden name="optionIndustry.createUser" />
			<s:hidden name="optionIndustry.updateTime" />
			<s:hidden name="optionIndustry.updateUser" />
			<s:hidden name="optionIndustry.ver" />
			
			<s:textfield label="代碼" name="optionIndustry.code" cssClass="form-control" cssErrorClass="form-control" readonly="true"/>
			<s:textfield label="名稱" name="optionIndustry.name" cssClass="form-control" cssErrorClass="form-control" readonly="true"/>				

			<s:submit cssClass="btn btn-danger" value="確定" />	
			<input type ="button" class="btn btn-default" onclick="history.back()" value="回上一頁"/>		
		</div>
	</s:form>
</body>
</html>