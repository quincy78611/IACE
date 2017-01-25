<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:if test='%{#session.sysUser.hasNamespaceStartWith("/file")}'>
	<li><a href="#">檔案中心</a>
		<ul>
			<s:if test='%{#session.sysUser.hasAuth("/file", "uploadFile")}'>
				<li><a href="<s:url value="/file/uploadFile"/>">上傳檔案</a></li>
			</s:if>
		</ul>
	</li>
</s:if>