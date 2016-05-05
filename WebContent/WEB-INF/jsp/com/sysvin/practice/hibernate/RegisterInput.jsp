<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Form - DbConnect Test</title>
<script type="text/javascript">
	
$(window).load(function(){
	//目前這作法只是權宜之計，比較好的做法應該是用更改template & theme
	//http://www.mkyong.com/struts2/working-with-struts-2-theme-template/
	//http://struts.apache.org/docs/xhtml-theme.html
	$("span.help-block").removeClass("help-block");
	$("span.glyphicon").remove();
});
</script>
</head>
<body>
	<div class="container body-content">
		<h2>Login</h2>
		<s:form action="register" method="post" validate="true" >
			<div class="form-horizontal" >
				<s:textfield label="ID" name="user.userId" cssClass="form-control" cssErrorClass="form-control" />
				<s:textfield label="Password" name="user.password" cssClass="form-control" />				
				<s:textfield label="Name" name="user.name" cssClass="form-control" />
				<s:textfield label="Phone" name="user.phone" cssClass="form-control" />
				<s:textfield label="E-mail" name="user.email" cssClass="form-control" />
				<s:textfield label="birthday" name="user.birthday" cssClass="form-control calendarBox" />
				<s:textfield label="address" name="user.address" cssClass="form-control"/>
				<s:submit value="Register" cssClass="btn btn-default" />			
			</div>

		</s:form>
	</div>
</body>


</html>