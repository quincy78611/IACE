<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:if test='%{#session.sysUser.hasNamespaceStartWith("/epaper")}'>
	<li><a href="#">電子報</a>
		<ul>
			<s:if test='%{#session.sysUser.hasAuth("/epaper", "init")}'>
				<li><a href="<s:url value="/epaper/init"/>">編輯管理</a></li>
			</s:if>
			<s:if test='%{#session.sysUser.hasAuth("/epaper", "create")}'>
				<li><a href="<s:url value="/epaper/create?loadDefaultSample=true"/>">新增電子報</a></li>
			</s:if>
			<s:if test='%{#session.sysUser.hasAuth("/epaperSubscriber", "init")}'>
				<li><a href="<s:url value="/epaperSubscriber/init"/>">訂閱管理</a></li>
			</s:if>	
		</ul>
	</li>
</s:if>
