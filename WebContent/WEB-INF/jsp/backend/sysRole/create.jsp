<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	$(document).ready(function(){
		$("#select-all").change(function(){
			if ($(this).is(':checked')) {
				$("input[type=checkbox]").prop('checked', true);
			}
			else {
				$("input[type=checkbox]").prop('checked', false);
			}
		});		
	});
</script>
</head>
<body>
	<h2 class="itemTitle">新增</h2>
	<s:form action="createSubmit" method="post" validate="true" >		
		<ul>
			<li class="all">
				<b>腳色名稱</b>
				<s:textfield name="sysRole.name"/>
			</li>
		</ul>
		<div class="clear"></div>
		
		<b>權限設定</b>
		<table width ="100%">
			<tr>
				<th>
					<label for="select-all">
						<input type="checkbox" id="select-all"/>全選						
					</label>
				</th>
			<tr>
			<s:iterator value="sysRole.authList" status="stat">
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].sysRole.id'}"/>
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].sysFunction.id'}"/>
				<tr>
					<td>
						<s:checkbox label="%{sysFunction.displayName}" name="%{'sysRole.authList['+#stat.index+'].enable'}" fieldValue="true"/>		
					</td>		
				</tr>
			</s:iterator>				
		</table>
		
		<div class="clear"></div>
		<s:submit cssClass="btn btn-default redBtn" value="儲存" />	
		<input type="button" class="grayBtn" value="回上一頁" onclick="window.location.href='<s:url value="index"/>'" />
	</s:form>
</body>
</html>