<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>findUserByPhone</title>
</head>
<body>
	<h2>findUserByPhone</h2>
	<div class="">
		<s:form action="findUserByPhone" method="post" validate="true">
			<div class="container " >
				<div class="col-md-3">
					<s:textfield name="user.phone" class="form-control" placeholder="phone"/>
				</div>
				<div class="col-md-2">
					<s:submit value="find" class="btn btn-primary"/>
				</div>
			</div>			
		</s:form>
	</div>		

	<div class="">
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>userId</th>
					<th>password</th>
					<th>name</th>
					<th>enableDate</th>
					<th>disableDate</th>
					<th>phone</th>
					<th>email</th>
					<th>gender</th>
					<th>birthday</th>
					<th>address</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="userList" status="rowstatus">
					<tr>
						<td><s:property value="userId" /></td>
						<td><s:property value="password" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="enableDate" /></td>
						<td><s:property value="disableDate" /></td>
						<td><s:property value="phone" /></td>
						<td><s:property value="email" /></td>
						<td><s:property value="gender" /></td>
						<td><s:property value="birthday" /></td>
						<td><s:property value="address" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>	
	</div>


</body>
<!-- ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

</html>



