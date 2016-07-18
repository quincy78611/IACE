<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">新增研發成果</h2>

	<s:form action="createTechnologySubmit" method="post" validate="true" >
		<s:hidden name="id"/>
		
		<ul>
			<li class="all">
				<b>技術名稱</b>
				<s:textfield name="technology.name"/>
			</li>
			<li class="all">
				<b>技術簡述</b>
				<s:textarea name="technology.descriptoin" />
			</li>
			<li class="all">
				<b>技術發展階段</b>
				<s:checkboxlist name="technology.optionTrlCodes" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" />
			</li>
			<li class="all">
				<b>技術發展階段說明</b>
				<s:textarea name="technology.trlDesc" />
			</li>			
		</ul>
		
		<s:submit cssClass="redBtn" value="確定" />
		<s:url value="update.action" var="updateUrlTag">
			<s:param name="id" value="id" />
		</s:url>
		<input type="button" class="grayBtn" value="回上一頁"
			onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
	</s:form>
	
	<script type="text/javascript">
	</script>
</body>
</html>