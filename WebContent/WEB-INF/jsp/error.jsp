<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Error page</title>
<style>
h1 {
	font-size: 20px;
	font-weight: bold;
}
</style>
</head>
<body>

	<div class="alert alert-danger">
		<h1>發生錯誤，請將本畫面傳給系統管理員，謝謝！&nbsp;&nbsp;&nbsp; (<s:date name="exceptionTime" format="yyyy/MM/dd HH:mm:ss.sss" />)</h1>
	</div>
	<div>
		<h1><s:property value="exceptionName"/> : <s:property value="exceptionMessage"/></h1>
		<s:if test="exceptionStack != null">
			<s:iterator value="exceptionStack" status="stat">
				<s:property/><br/>
			</s:iterator>
		</s:if>	
	</div>

</body>
</html>