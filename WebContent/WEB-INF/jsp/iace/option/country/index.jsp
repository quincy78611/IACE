<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function () {

		});
	</script>
</head>
<body>
	<a class="redBtn" href="<s:url value="create.action"/>">新增代碼</a>
	<table width="100%">
		<tr>
			<th width="2%" nowrap>No.</th>
			<th width="5%" nowrap>代碼</th>
			<th width="" nowrap>名稱</th>
			<th width="20%">功能</th>
		</tr>
		<s:iterator value="optionList" status="stat">
			<tr>
				<td>
					<s:property value="#stat.count" />
					<s:hidden name="id" class="id"/>
				</td>						
				<td><s:property value="code" /></td>
				<td><s:property value="name" /></td>
				<td class="col-md-2">
					<s:url value="update.action" var="updateUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<input type="button" class="edit" value="編輯" 
						onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
						
					<s:url value="delete.action" var="deleteUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<input type="button" class="del" value="刪除" 
						onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />								
				</td>
			</tr>
		</s:iterator>
	</table>


</body>
</html>