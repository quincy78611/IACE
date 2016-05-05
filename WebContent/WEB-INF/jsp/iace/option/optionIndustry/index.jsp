<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>產業/領域別</title>
</head>
<body>
	<div>
		<a class="btn btn-primary" href="<s:url value="/iace/option/industry/create"/>">新增代碼</a>
	</div>
	<p>
	<div class="">
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>No.</th>
					<th>代碼</th>
					<th>名稱</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="optionIndustryList" status="stat">
					<tr>
						<td>
							<s:property value="#stat.count" />
							<s:hidden name="id" class="id"/>
						</td>						
						<td><s:property value="code" /></td>
						<td><s:property value="name" /></td>
						<td>
							<s:url value="update.action" var="updateUrlTag">
								<s:param name="id" value="id" />
							</s:url>
							<input type="button" class="btn-info" value="編輯" 
								onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
								
							<s:url value="delete.action" var="deleteUrlTag">
								<s:param name="id" value="id" />
							</s:url>
							<input type="button" class="btn-danger" value="刪除" 
								onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />								
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function () {

		});
	</script>
</body>
</html>