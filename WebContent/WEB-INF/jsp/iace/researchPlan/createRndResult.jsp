<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h4>新增研發成果</h4>

	<s:form action="createRndResultSubmit" method="post" validate="true" >
		<div class="form-horizontal" >
			<s:hidden name="id"/>
<%-- 			<s:hidden name="rndResult.id" /> --%>
<%-- 			<s:hidden name="rndResult.isValid" /> --%>
<%-- 			<s:hidden name="rndResult.createTime" /> --%>
<%-- 			<s:hidden name="rndResult.createUser" /> --%>
<%-- 			<s:hidden name="rndResult.updateTime" /> --%>
<%-- 			<s:hidden name="rndResult.updateUser" /> --%>
<%-- 			<s:hidden name="rndResult.ver" /> --%>
<%-- 			<s:hidden name="rndResult.researchPlan.id" /> --%>
			
			<s:textfield label="技術名稱" name="rndResult.name" cssClass="form-control" />				
			<s:textarea label="技術簡述" name="rndResult.descriptoin" cssClass="form-control"  />
			<s:select label="技術發展階段" name="rndResult.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />			
			<s:textarea label="技術發展階段說明" name="rndResult.trlDesc" cssClass="form-control" />
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