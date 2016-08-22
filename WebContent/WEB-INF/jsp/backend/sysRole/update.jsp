<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
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
	<h2 class="itemTitle">編輯</h2>
	<s:form action="updateSubmit" method="post" validate="true" >
		<s:hidden name="id"/>
		<s:hidden name="sysRole.id"/>
		<s:hidden name="sysRole.isValid"/>
		<s:hidden name="sysRole.createTime"/>
		<s:hidden name="sysRole.createUser"/>
		<s:hidden name="sysRole.updateTime"/>
		<s:hidden name="sysRole.updateUser"/>		
		<s:hidden name="sysRole.ver"/>		
		<ul>
			<li class="all">
				<b>腳色名稱</b>
				<s:textfield name="sysRole.name"/>
			</li>
		</ul>
		<div class="clear"></div>
		
		<b>權限設定</b>
		<table>
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
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].id'}"/>
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].isValid'}"/>
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].createTime'}"/>
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].createUser'}"/>
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].updateTime'}"/>
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].updateUser'}"/>
				<s:hidden name="%{'sysRole.authList['+#stat.index+'].ver'}"/>
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