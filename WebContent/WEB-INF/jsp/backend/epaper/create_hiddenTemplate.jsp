<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="hidden-template-value">
	<s:hidden name="template.title"/>
	<s:hidden name="template.postDate"/>
	<s:hidden name="template.no"/>
	<s:iterator value="template.newsIds" status="stat">
		<input type="hidden" name="template.newsIds" value="<s:property/>"/>
	</s:iterator>
	<s:iterator value="template.activityIds" status="stat">
		<input type="hidden" name="template.activityIds" value="<s:property/>"/>
	</s:iterator>
	<s:iterator value="template.rdFocusIds" status="stat">
		<input type="hidden" name="template.rdFocusIds" value="<s:property/>"/>
	</s:iterator>	
	<s:iterator value="template.researchPlanIds" status="stat">
		<input type="hidden" name="template.researchPlanIds" value="<s:property/>"/>
	</s:iterator>	
	<s:iterator value="template.patentIds" status="stat">
		<input type="hidden" name="template.patentIds" value="<s:property/>"/>
	</s:iterator>	
	<s:iterator value="template.industryInfoIds" status="stat">
		<input type="hidden" name="template.industryInfoIds" value="<s:property/>"/>
	</s:iterator>	
	<s:iterator value="template.faqIds" status="stat">
		<input type="hidden" name="template.faqIds" value="<s:property/>"/>
	</s:iterator>	
</div>