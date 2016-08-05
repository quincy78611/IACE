<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(document).ready(function () {
		$(".btn-del").click(function() {
			var url = $(this).siblings(".deleteUrl").val();
			if(confirm("確定要刪除？")) {
				window.location.href=url;
			}
		});	
	});
</script>
</head>
<body>
	<input type="button" class="redBtn" value="新增代碼" onclick="window.location.href='<s:url value="create.action"/>'" />
	<div class="clear"></div>
	<br>
	<table width="100%">
		<tr>
			<th width="2%" nowrap>No.</th>
			<th width="" nowrap>代碼</th>
			<th width="" nowrap>名稱</th>
			<th width="17%">功能</th>
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
					<input type="button" class="btn-func btn-edit" value="編輯" 
						onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
						
					<s:url value="deleteSubmit.action" var="deleteUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<s:hidden value="%{#deleteUrlTag}" class="deleteUrl" disabled="true"/>
					<input type="button" class="btn-func btn-del" value="刪除" />								
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>