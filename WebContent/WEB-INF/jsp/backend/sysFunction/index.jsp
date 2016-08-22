<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
</script>
</head>
<body>
	<input type="button" class="redBtn" value="新增系統功能" onclick="window.location.href='<s:url value="create.action"/>'" />
	<div class="clear"></div>
	<br>
	<table>
		<tr>
			<th width="2%" nowrap>No.</th>
			<th width="" nowrap>功能名稱</th>
			<th width="" nowrap>NameSpace</th>
			<th width="" nowrap>ActionName</th>
			<th width="">功能</th>
		</tr>
		<s:iterator value="sysFunctionList" status="stat">
			<tr>
				<td>
					<s:property value="#stat.count" />
					<s:hidden name="id" class="id"/>
				</td>						
				<td><s:property value="displayName" /></td>
				<td><s:property value="namespace" /></td>
				<td>
					<s:property value="%{actionName1}" />
					<s:if test="actionName2 != null">
						<s:property value="%{', ' + actionName2}" />
					</s:if>
					<s:if test="actionName3 != null">
						<s:property value="%{', ' + actionName3}" />
					</s:if>
				</td>
				<td class="col-md-2">
					<s:url value="update.action" var="updateUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<input type="button" class="btn-func btn-edit" value="編輯" 
						onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>