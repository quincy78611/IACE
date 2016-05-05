<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Login Success</title>
</head>
<body>
    <div align="center">
    	<s:hidden value="user.userUuid"/>
        <s:property value="user.userId" />
        <s:property value="user.password" />
        <s:property value="user.name" />
        <s:property value="user.enableDate" />
        <s:property value="user.disableDate" />
        <s:property value="user.phone" />
        <s:property value="user.email" />
        <s:property value="user.gender" />
        <s:property value="user.birthday" />
        <s:property value="user.address" />
    </div>
</body>
</html>