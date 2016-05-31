<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h3>檢視</h3>
	<div class="container-fluid" >			
		<div class="col-md-3">
			<s:textfield label="姓名" name="consulting.name" class="form-control" readonly="true"/>
		</div>
		<div class="col-md-9">
			<s:textfield label="單位名稱" name="consulting.organization" class="form-control" readonly="true"/>
		</div>			
		<div class="col-md-3">
			<s:select label="單位類型" name="consulting.optionOrganizationType.code" list="optionOrganizationTypeList" listKey="code" listValue="%{code +' ' +name}" class="orgType" disabled="true"/>		
		</div>
		<div class="col-md-9">
			<s:textfield label="單位類型(其他)" name="consulting.orgTypeOther" class="form-control orgType" readonly="true"/>
		</div>
		<div class="col-md-3">
			<s:select label="諮詢類型" name="consulting.optionConsult.code" list="optionConsultList" listKey="code" listValue="%{code +' ' +name}" class="consult" disabled="true"/>		
		</div>
		<div class="col-md-9">
			<s:textfield label="諮詢類型(其他)" name="consulting.consultTypeOther" class="form-control consult" readonly="true"/>
		</div>
		<div class="col-md-3">
			<s:select label="產業/領域別" name="consulting.optionIndustry.code" list="optionIndustryList" listKey="code" listValue="%{code +' ' +name}" class="industry" disabled="true"/>		
		</div>
		<div class="col-md-9">
			<s:textfield label="產業/領域別(其他)" name="consulting.industryOther" class="form-control industry" readonly="true"/>
		</div>
		<div class="col-md-3">
			<s:textfield label="聯絡電話" name="consulting.phone" class="form-control industry" readonly="true"/>		
		</div>
		<div class="col-md-6">
			<s:textfield label="E-MAIL" name="consulting.email" class="form-control industry" readonly="true"/>		
		</div>
		<div class="col-md-3">
			<s:textfield label="諮詢日期" name="consulting.consultDate" cssClass="form-control" readonly="true">			
				<s:param name="value">
					<s:date name="consulting.consultDate" format="yyyy/MM/dd" /> 
				</s:param>
			</s:textfield>
		</div>			
		<div class="col-md-12">
			<s:textarea label="內容說明" name="consulting.content" cssClass="form-control" readonly="true"/>
		</div>
	</div>
		<hr>
	<div class="container-fluid">
		<div class="col-md-4">
			<s:checkbox label="同意【個人資料蒐集、處理及利用之告知暨同意條款】" name="consulting.approvalCheckBox" disabled="true"/>
		</div>
		<a class="btn btn-primary" href="<s:url value="/iace/consulting/init"/>">確定</a>	
		<s:url value="update.action" var="updateUrlTag">
			<s:param name="id" value="consulting.id" />
		</s:url>
		<s:if test="#request.context['struts.actionMapping'].name == 'showDetail'">
			<input type="button" class="btn btn-info" value="編輯" 
				onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
		</s:if>		
	</div>
</body>
</html>