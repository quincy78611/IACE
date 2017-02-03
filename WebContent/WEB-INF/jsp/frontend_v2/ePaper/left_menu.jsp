<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<ul class="list-group categoryList">
	<li class="list-group-item" onclick="window.location.href='<s:url value="/f2/ePaper/init"/>'">
		<i class="fa fa-cube right10" aria-hidden="true"></i>電子報歷史區
	</li>
	<li class="list-group-item" onclick="window.location.href='<s:url value="/f2/ePaper/subscribe"/>'">
		<i class="fa fa-cube right10" aria-hidden="true"></i>電子報訂退閱
	</li>
</ul>