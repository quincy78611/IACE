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
			
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-5">
						<s:textfield label="計畫編號" name="researchPlan.planNo" cssClass="form-control" />
					</div>
					<div class="col-md-7">	
						<s:textfield label="計畫名稱" name="researchPlan.name" cssClass="form-control" />
					</div>
					<div class="col-md-4">
						<s:textfield label="計畫主持人" name="researchPlan.manager" cssClass="form-control" />
					</div>
					<div class="col-md-2">	
						<s:textfield label="計畫年度" name="researchPlan.year" cssClass="form-control" />
					</div>
					<div class="col-md-6">
						<s:textfield label="計畫關鍵字" name="researchPlan.keyword" cssClass="form-control" />
					</div>
					<div class="col-md-8">
						<s:select label="技術發展階段" name="researchPlan.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
					</div>
					<div class="col-md-2">
						<s:textfield label="GRB計畫編號" name="researchPlan.projkey" cssClass="form-control" />
					</div>
					<div class="col-md-2">
						<s:textfield label="成果報告ID" name="researchPlan.grb05Id" cssClass="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="row">
					<div class="col-md-5">
						<s:select label="研究領域清單" id="grbCodesList" name="" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" multiple="true" size="9"/>
					</div>
					<div class="col-md-2">
						<input type="button" id="btn-selectGrb" class="btn btn-sm btn-default" value="->"/>
						<input type="button" id="btn-diselectGrb" class="btn btn-sm btn-default" value="<-"/>
					</div>					
					<div class="col-md-5">
						<s:select label="已選研究領域" id="selectedGrbCodes" name="researchPlan.grbDomainCodes" list="researchPlan.grbDomains" listKey="code" listValue="%{code +' ' +name}" multiple="true" size="9"/>
					</div>
				</div>
			</div>
		</div>
		<div>
			<s:submit id="btn_save" cssClass="btn btn-primary" value="儲存" />
			<a class="btn btn-default" href="<s:url value="/iace/researchPlan/init"/>">回上一頁</a>		
		</div>
		<hr>
		<div class="col-md-3">
			<h3>研發成果列表</h3>
		</div>			
		<div class="col-md-3">
			<s:url value="createTechnology.action" var="createUrlTag" escapeAmp="false">
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
					<s:if test="researchPlan.technologies != null">
						<s:iterator value="researchPlan.technologies" status="stat">
							<tr>
								<td>
									<s:property value="name"/>
								</td>
								<td>
									<s:property value="descriptoin"/>
								</td>
								<td class="col-md-2">
									<s:property value="optionTrlCodesString"/>
								</td>
								<td>
									<s:property value="trlDesc"/>
								</td>
								<td class="col-md-1">
									<!-- 檢視 -->
									<s:url value="showTechnologyDetail.action" var="detailUrlTag" escapeAmp="false">
										<s:param name="id" value="researchPlan.id" />
										<s:param name="TechnologyId" value="id" />
									</s:url>		
									<input type="button" class="btn-info" value="檢視" 
										onclick="window.location.href='<s:property value="detailUrlTag" />'" />														
								
									<!-- 編輯 -->
									<s:url value="updateTechnology.action" var="updateUrlTag" escapeAmp="false">
										<s:param name="id" value="researchPlan.id" />
										<s:param name="TechnologyId" value="id" />
									</s:url>		
									<input type="button" class="btn-info" value="編輯" 
										onclick="window.location.href='<s:property value="updateUrlTag" />'" />									
								
									<!-- 刪除 -->
									<s:url value="deleteTechnology.action" var="deleteUrlTag" escapeAmp="false">
										<s:param name="id" value="researchPlan.id" />
										<s:param name="TechnologyId" value="id" />
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
	</s:form>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#grbCodesList option").dblclick(dblclick_grbOption);
			$("#selectedGrbCodes option").dblclick(dblclick_selectrbOption);
		});
		
		function dblclick_grbOption() {
			if ($("#selectedGrbCodes option").size() == 6) {
				alert("最多只可選取6個");
				return;
			}
			
			$("#selectedGrbCodes").append($(this));
			
			var selectList = $("#selectedGrbCodes option");
			selectList.sort(function(a,b){
				var valA = a.value.toLowerCase();
				var valB = b.value.toLowerCase();
			    if (valA < valB) {
			    	return -1;
			    } else if (valA > valB) {
			    	return 1;
			    } else {
			    	if (a.value < b.value) return -1;
			    	else if (a.value < b.value) return 1;
			    	else return 0;
			    }
			});
			$("#selectedGrbCodes").html(selectList);
			$("#selectedGrbCodes option").dblclick(dblclick_selectrbOption);
		}
		function dblclick_selectrbOption() {
			$("#grbCodesList").append($(this));
			
			var selectList = $("#grbCodesList option");			
			selectList.sort(function(a,b){
				var valA = a.value.toLowerCase();
				var valB = b.value.toLowerCase();
			    if (valA < valB) {
			    	return -1;
			    } else if (valA > valB) {
			    	return 1;
			    } else {
			    	if (a.value < b.value) return -1;
			    	else if (a.value < b.value) return 1;
			    	else return 0;
			    }
			});
			$("#grbCodesList").html(selectList);
			$("#grbCodesList option").dblclick(dblclick_grbOption);			
		}		
		
		$("#btn-selectGrb").click(function(){			
			var selectOption = $("#grbCodesList").find('option:selected');
			selectOption.each(function(){
				$(this).dblclick();
			});
		});
		$("#btn-diselectGrb").click(function(){			
			var selectOption = $("#selectedGrbCodes").find('option:selected');
			selectOption.each(function(){
				$(this).dblclick();
			});
		});

		$("#btn_save").click(function(){
			$("#selectedGrbCodes option").prop('selected', true);
		});
	</script>

	
</body>
</html>