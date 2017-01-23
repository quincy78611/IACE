<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:if test='%{#session.sysUser.hasNamespaceStartWith("/rdFocus")}'>
	<li><a href="#">研發焦點</a>
		<ul>
			<s:if test='%{#session.sysUser.hasAuth("/rdFocus", "init")}'>
				<li><a href="<s:url value="/rdFocus/init"/>">編輯管理</a></li>
			</s:if>
			<s:if test='%{#session.sysUser.hasAuth("/rdFocus", "create")}'>
				<li><a href="<s:url value="/rdFocus/create"/>">新增</a></li>
			</s:if>	
		</ul>
	</li>
</s:if>
