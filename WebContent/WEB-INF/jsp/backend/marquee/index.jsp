<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		$(".btn-del").click(function(){
			if (confirm("刪除後將無法回復，確定要刪除?")) {
				var url = $(this).siblings(".deleteUrl").val();
				window.location.href=url;
			} else {
				return false;
			}
		});
	});
</script>
<meta name="funcPathText" content="編輯管理 "/>
</head>
<body>
	<input type="button" class="redBtn" value="新增" onclick="window.location.href='<s:url value="create.action"/>'" />
	<div class="clear"></div>
	<br>
	<table>
		<tr>
			<th width="2%" nowrap>No.</th>
			<th width="">文字</th>
			<th width="">連結</th>
			<th width="">排序</th>
			<th width="">顯示</th>
			<th width="">功能</th>
		</tr>
		<s:iterator value="marqueeList" status="stat">
			<tr>
				<td>
					<s:property value="#stat.count" />
					<s:hidden name="id" class="id"/>
				</td>						
				<td><s:property value="text" /></td>
				<td><a href="<s:property value="url" />" target="_blank"><s:property value="url" /></a></td>
				<td><s:property value="sort" /></td>
				<td><s:property value="displayStatus" /></td>
				<td>
					<!-- 編輯 -->
					<s:if test='%{#session.sysUser.hasAuth(namespace, "update")}'>
						<s:url value="update.action" var="updateUrlTag">
							<s:param name="id" value="id" />
						</s:url>
						<input type="button" class="btn-func btn-edit" value="編輯" 
							onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
					</s:if>
					<!-- 刪除 -->
					<s:if test='%{#session.sysUser.hasAuth(namespace, "deleteSubmit")}'>
						<s:url value="deleteSubmit.action" var="deleteUrlTag">
							<s:param name="id" value="id" />
						</s:url>
						<s:hidden value="%{#deleteUrlTag}" class="deleteUrl" disabled="true"/>
						<input type="button" class="btn-func btn-del" value="刪除" />
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>