<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:if test='%{#session.sysUser.hasNamespaceStartWith("/videosArea")}'>
	<li><a href="#">影音專區</a>
		<ul>
			<s:if test='%{#session.sysUser.hasAuth("/videosArea", "init")}'>
				<li><a href="<s:url value="/videosArea/init"/>">編輯管理</a></li>
			</s:if>
			<s:if test='%{#session.sysUser.hasAuth("/videosArea", "create")}'>
				<li><a href="<s:url value="/videosArea/create"/>">新增</a></li>
			</s:if>	
		</ul>
	</li>
</s:if>
