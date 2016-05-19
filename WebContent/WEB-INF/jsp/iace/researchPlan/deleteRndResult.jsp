<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h4>刪除研發成果</h4>

	<s:form action="deleteRndResultSubmit" method="post" validate="true" >
		<div class="container-fluid" >
			<s:hidden name="id"/>
			<s:hidden name="rndResultId"/>
			
			<s:textfield label="技術名稱" name="rndResult.name" readonly="true" cssClass="form-control" />				
			<s:textarea label="技術簡述" name="rndResult.descriptoin" readonly="true" cssClass="form-control"  />
			<s:textfield label="技術發展階段" name="rndResult.trl.name" readonly="true" cssClass="form-control" />			
			<s:textarea label="技術發展階段說明" name="rndResult.trlDesc" readonly="true" cssClass="form-control"/>	
		</div>
		
		<s:submit cssClass="btn btn-info" value="確定" />
		<s:url value="update.action" var="updateUrlTag">
			<s:param name="id" value="id" />
		</s:url>
		<input type="button" class="btn btn-default" value="回上一頁"
			onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
	</s:form>
</body>
</html>