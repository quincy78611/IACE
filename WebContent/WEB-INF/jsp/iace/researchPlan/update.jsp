<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h3>編輯</h3>
<%-- 	<h6><s:property value="researchPlan.id" /></h6> --%>

	<s:form action="updateSubmit" method="post" validate="true">
		<div class="container-fluid" >
			<s:hidden name="researchPlan.id" />
			<s:hidden name="researchPlan.isValid" />
			<s:hidden name="researchPlan.createTime" />
			<s:hidden name="researchPlan.createUser" />
			<s:hidden name="researchPlan.updateTime" />
			<s:hidden name="researchPlan.updateUser" />
			<s:hidden name="researchPlan.ver" />
			
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
				<s:select label="技術發展階段" name="researchPlan.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			</div>
			<div class="col-md-3">
				<s:textfield label="GRB計畫編號" name="researchPlan.projkey" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<s:textfield label="成果報告ID" name="researchPlan.grb05Id" cssClass="form-control" />
			</div>
		</div>			
		<hr>
		<div class="col-md-3">
			<h3>研發成果列表</h3>
		</div>			
		<div class="col-md-3">
			<s:url value="createRndResult.action" var="createUrlTag" escapeAmp="false">
				<s:param name="id" value="researchPlan.id" />				
			</s:url>		
			<input type="button" class="btn btn-info" value="新增研發成果" 
				onclick="window.location.href='<s:property value="#createUrlTag" />'" />														
		</div>
		<div class="">
			<table id="rndResultsTable" class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>技術名稱</th>
						<th>技術簡述</th>
						<th>技術發展階段</th>
						<th>技術發展階段說明</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<s:if test="researchPlan.rndResults != null">
						<s:iterator value="researchPlan.rndResults" status="stat">
							<s:hidden name="%{'researchPlan.rndResults['+#stat.index+'].id'}" />
							<s:hidden name="%{'researchPlan.rndResults['+#stat.index+'].isValid'}" />
							<s:hidden name="%{'researchPlan.rndResults['+#stat.index+'].createTime'}" />
							<s:hidden name="%{'researchPlan.rndResults['+#stat.index+'].createUser'}" />
							<s:hidden name="%{'researchPlan.rndResults['+#stat.index+'].updateTime'}" />
							<s:hidden name="%{'researchPlan.rndResults['+#stat.index+'].updateUser'}" />
							<s:hidden name="%{'researchPlan.rndResults['+#stat.index+'].ver'}" />
							<tr>
								<td>
<%-- 									<s:property value="id"/> --%>
									<s:textfield name="%{'researchPlan.rndResults['+#stat.index+'].name'}" cssClass="form-control" />
								</td>
								<td><s:textfield name="%{'researchPlan.rndResults['+#stat.index+'].descriptoin'}" cssClass="form-control" /></td>
								<td class="col-md-2">
									<s:select name="%{'researchPlan.rndResults['+#stat.index+'].trl.code'}" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
								</td>
								<td><s:textfield name="%{'researchPlan.rndResults['+#stat.index+'].trlDesc'}" cssClass="form-control" /></td>
								<td class="col-md-1">
									<!-- 檢視 -->
									<s:url value="showRndResultDetail.action" var="detailUrlTag" escapeAmp="false">
										<s:param name="id" value="researchPlan.id" />
										<s:param name="rndResultId" value="id" />
									</s:url>		
									<input type="button" class="btn-default" value="檢視" 
										onclick="window.location.href='<s:property value="detailUrlTag" />'" />														
								
									<!-- 刪除 -->
									<s:url value="deleteRndResult.action" var="deleteUrlTag" escapeAmp="false">
										<s:param name="id" value="researchPlan.id" />
										<s:param name="rndResultId" value="id" />
									</s:url>		
									<input type="button" class="btn-danger" value="刪除" 
										onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />														
								</td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>					
			</table>
		</div>
		<hr>
		<div>
			<s:submit cssClass="btn btn-primary" value="儲存" />
<!-- 			<input type="button" value="重設" class="btn btn-default" onclick="this.form.reset();" /> -->
			<a class="btn btn-default" href="<s:url value="/iace/researchPlan/init"/>">回上一頁</a>		
		</div>
	</s:form>
	
	<script type="text/javascript">
	</script>	
	
</body>
</html>