<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">研發成果明細</h2>

		<ul>
			<li class="all">
				<b>技術名稱</b>
				<s:textfield name="technology.name" disabled="true"/>
			</li>
			<li class="all">
				<b>技術簡述</b>
				<s:textarea name="technology.descriptoin" disabled="true"/>
			</li>
			<li class="all">
				<b>技術發展階段</b>
				<s:checkboxlist name="technology.optionTrlCodes" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" disabled="true"/>
			</li>
			<li class="all">
				<b>技術發展階段說明</b>
				<s:textarea name="technology.trlDesc" disabled="true"/>
			</li>			
		</ul>
	
	<s:url value="update.action" var="updateUrlTag">
		<s:param name="id" value="id" />
	</s:url>
	<input type="button" class="grayBtn" value="回上一頁"
		onclick="window.location.href='<s:property value="#updateUrlTag" />'" />

</body>
</html>