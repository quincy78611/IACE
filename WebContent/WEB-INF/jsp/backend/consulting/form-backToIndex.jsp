<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form action="index" method="post" id="form-backToIndex">
	<s:hidden name="searchCondition.searchText"/>
	<s:hidden name="searchCondition.optionOrganizationTypeCode"/>
	<s:hidden name="searchCondition.optionConsultCode"/>
	<s:hidden name="searchCondition.optionIndustryCode"/>
	<s:hidden name="searchCondition.consultDateStart"/>
	<s:hidden name="searchCondition.consultDateEnd"/>
	<s:hidden name="searchCondition.pageIndex"/>
	<s:hidden name="searchCondition.pageSize"/>
</form>