<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
$(document).ready(function() {
});
</script>
<style>
.projName {
	font-size: 1.2em;
	font-weight: bold;
	margin-buttom: 5px;
	margin-right: 10px;
}
</style>
<meta name="funcPathText" content="編輯管理 "/>
</head>
<body>
	<table>
		<s:iterator value="coopExList" status="stat">
			<tr>
				<td width="15%">
					<img src="data:image;base64,<s:property value="firstBase64Img"/>" style="max-width:120px; max-height:120px;" />
				</td>
				<td>
					<label class="projName"><s:property value="projName"/></label><s:property value="year"/>
					<div style="color:#3366FF;">
						<p>研發團隊:<s:property value="rdTeam"/></p>
						<p>輔導團隊:<s:property value="assisTeam"/></p>					
					</div>
					<p style="overflow:hidden;"><s:property value="content"/></p>
				</td>
				<td width="5%">
					<!-- 檢視 -->
					<s:url value="showDetail.action" var="detailUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<input type="button" class="btn-func btn-view" value="檢視" 
						onclick="window.location.href='<s:property value="#detailUrlTag" />'" />

					<s:url value="update.action" var="updateUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<input type="button" class="btn-func btn-edit" value="編輯" 
						onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
						
					<s:url value="delete.action" var="deleteUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<input type="button" class="btn-func btn-del" value="刪除" 
						onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>