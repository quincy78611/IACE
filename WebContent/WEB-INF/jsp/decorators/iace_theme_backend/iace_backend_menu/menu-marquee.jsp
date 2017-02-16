<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:if test='%{#session.sysUser.hasNamespaceStartWith("/marquee")}'>
	<li><a href="#">首頁跑馬燈</a>
		<ul>
			<s:if test='%{#session.sysUser.hasAuth("/marquee", "index")}'>
				<li><a href="<s:url value="/marquee/index"/>">編輯管理</a></li>
			</s:if>
			<s:if test='%{#session.sysUser.hasAuth("/marquee", "create")}'>
				<li><a href="<s:url value="/marquee/create"/>">新增</a></li>
			</s:if>	
		</ul>
	</li>
</s:if>
