<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<h4>編輯研發成果</h4>

	<s:form action="updateTechnologySubmit" method="post" validate="true" >
		<div class="form-horizontal" >
			<s:hidden name="id"/>
			<s:hidden name="technology.id" />
			<s:hidden name="technology.isValid" />
			<s:hidden name="technology.createTime" />
			<s:hidden name="technology.createUser" />
			<s:hidden name="technology.updateTime" />
			<s:hidden name="technology.updateUser" />
			<s:hidden name="technology.ver" />
			
			<s:textfield label="技術名稱" name="technology.name" cssClass="form-control" />				
			<s:textarea label="技術簡述" name="technology.descriptoin" cssClass="form-control" />
<%-- 			<s:select label="技術發展階段" name="technology.optionTrlCodes" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" multiple="true"/> --%>
			<s:checkboxlist label="技術發展階段" name="technology.optionTrlCodes" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" />
			<s:textarea label="技術發展階段說明" name="technology.trlDesc" cssClass="form-control" />
		</div>
		
		<s:submit cssClass="btn btn-info" value="確定" />
		<s:url value="update.action" var="updateUrlTag">
			<s:param name="id" value="id" />
		</s:url>
		<input type="button" class="btn btn-default" value="回上一頁"
			onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
	</s:form>
	
	<script type="text/javascript">
	
	</script>
</body>
</html>