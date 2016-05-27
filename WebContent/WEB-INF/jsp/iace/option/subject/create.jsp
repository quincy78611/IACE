<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h4>新增</h4>
	<s:form action="createSubmit" method="post" validate="true" >
		<div class="container-fluid" >
			<div class="col-md-3">
				<s:textfield label="代碼" name="option.code" cssClass="form-control"/>
				<s:textfield label="名稱" name="option.name" cssClass="form-control"/>					
			</div>
		</div>			
		<s:submit cssClass="btn btn-default" value="儲存" />	
		<input type ="button" class="btn btn-default" onclick="history.back()" value="回上一頁"/>		
	</s:form>
</body>
</html>