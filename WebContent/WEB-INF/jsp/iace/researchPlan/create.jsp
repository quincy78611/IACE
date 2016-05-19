<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h3>新增</h3>
	<s:form action="createSubmit" method="post" validate="true">
		<div class="container-fluid" >			
			<div class="col-md-3">
				<s:textfield label="計畫編號" name="researchPlan.planNo" cssClass="form-control" />
			</div>
			<div class="col-md-5">	
				<s:textfield label="計畫名稱" name="researchPlan.name" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<s:textfield label="計畫主持人" name="researchPlan.manager" cssClass="form-control" />
			</div>
			<div class="col-md-1">	
				<s:textfield label="計畫年度" name="researchPlan.year" cssClass="form-control" />
			</div>			
			<div class="col-md-2">
				<s:select label="研究領域1" name="researchPlan.grbDomain1.code" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />		
			</div>
			<div class="col-md-2">
				<s:select label="研究領域2" name="researchPlan.grbDomain2.code" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			</div>
			<div class="col-md-2">
				<s:select label="研究領域3" name="researchPlan.grbDomain3.code" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			</div>
			<div class="col-md-2">
				<s:select label="研究領域4" name="researchPlan.grbDomain4.code" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />		
			</div>
			<div class="col-md-2">
				<s:select label="研究領域5" name="researchPlan.grbDomain5.code" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			</div>
			<div class="col-md-2">
				<s:select label="研究領域6" name="researchPlan.grbDomain6.code" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			</div>
			<div class="col-md-3">
				<s:textfield label="計畫關鍵字" name="researchPlan.keyword" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<s:select label="計畫發展階段" name="researchPlan.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			</div>
			<div class="col-md-3">
				<s:textfield label="GRB計畫編號" name="researchPlan.projkey" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<s:textfield label="成果報告全文連結代碼" name="researchPlan.grb05Id" cssClass="form-control" />
			</div>
		</div>			
		<hr>
		<div>
			<s:submit cssClass="btn btn-primary" value="儲存" />
			<input type="button" value="重設" class="btn btn-default" onclick="this.form.reset();" />
			<a class="btn btn-default" href="<s:url value="/iace/researchPlan/init"/>">回上一頁</a>		
		</div>
	</s:form>
	
	<script type="text/javascript">
	</script>	
	
</body>
</html>