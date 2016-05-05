<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login Form Using taglib</title>
</head>

<body>
	<p>Loading ...123</p>
	<s:form action="execute">
		<s:textfield key="loginName" />
		<s:password key="password" />
		<s:submit />
	</s:form>
	<p><a href="<s:url action='execute'/>">Hello World</a></p>
</body>
</html>
