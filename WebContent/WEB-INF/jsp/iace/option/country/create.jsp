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
				<b>代碼</b>
				<s:textfield name="option.code"/>
			</li>			
			<li class="half">
				<b>名稱</b>
				<s:textfield name="option.name"/>
			</li>			
		</ul>
		
		<s:submit cssClass="btn btn-default redBtn" value="儲存" />	
		<input type ="button" class="btn btn-default grayBtn" onclick="history.back()" value="回上一頁"/>		
	</s:form>
</body>
</html>