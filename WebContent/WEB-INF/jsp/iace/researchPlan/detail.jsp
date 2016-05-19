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
		<div class="row">
			<div class="col-md-3">
				<label class="control-label">計畫編號</label><p>
				<s:property value="researchPlan.planNo"/>
			</div>
			<div class="col-md-4">
				<label class="control-label">計畫名稱</label><p>
				<s:property value="researchPlan.name"/>		
			</div>
			<div class="col-md-3">
				<label class="control-label">計畫主持人</label><p>
				<s:property value="researchPlan.manager"/>			
			</div>
			<div class="col-md-2">
				<label class="control-label">計畫年度</label><p>
				<s:property value="researchPlan.year"/>			
			</div>			
		</div>	
		<div class="row">
			<div class="col-md-2">
				<label class="control-label">研究領域1</label><p>
				<s:property value="%{researchPlan.grbDomain1.code + ' ' + researchPlan.grbDomain1.name}"/>			
			</div>
			<div class="col-md-2">
				<label class="control-label">研究領域2</label><p>
				<s:property value="%{researchPlan.grbDomain2.code + ' ' + researchPlan.grbDomain2.name}"/>			
			</div>
			<div class="col-md-2">
				<label class="control-label">研究領域3</label><p>
				<s:property value="%{researchPlan.grbDomain3.code + ' ' + researchPlan.grbDomain3.name}"/>			
			</div>
			<div class="col-md-2">
				<label class="control-label">研究領域4</label><p>
				<s:property value="%{researchPlan.grbDomain4.code + ' ' + researchPlan.grbDomain4.name}"/>			
			</div>
			<div class="col-md-2">
				<label class="control-label">研究領域5</label><p>
				<s:property value="%{researchPlan.grbDomain5.code + ' ' + researchPlan.grbDomain5.name}"/>			
			</div>
			<div class="col-md-2">
				<label class="control-label">研究領域6</label><p>
				<s:property value="%{researchPlan.grbDomain6.code + ' ' + researchPlan.grbDomain6.name}"/>			
			</div>
		</div>	
		<div class="row">
			<div class="col-md-3">
				<label class="control-label">計畫關鍵字</label><p>
				<s:property value="researchPlan.keyword"/>				
			</div>
			<div class="col-md-3">
				<label class="control-label">計畫發展階段</label><p>
				<s:property value="%{researchPlan.trl.code + ' ' + researchPlan.trl.name"/>				
			</div>
			<div class="col-md-3">
				<label class="control-label">GRB計畫編號</label><p>
				<s:property value="researchPlan.projkey"/>				
			</div>
			<div class="col-md-3">
				<label class="control-label">成果報告全文連結</label><p>
				<a href="<s:url value="%{'http://grbsearch.stpi.narl.org.tw/GRB_Search/grb/show_doc.jsp?id='+researchPlan.grb05Id}"/>" target="_blank">連結</a>	
			</div>		
		</div>
	</div>			
	<hr>
	
	<h3>研發成果列表</h3>
	<div class="">
		<table id="rndResultsTable" class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>技術名稱</th>
					<th>技術簡述</th>
					<th>技術發展階段</th>
					<th>技術發展階段說明</th>
				</tr>
			</thead>
			<tbody>
				<s:if test="researchPlan.rndResults != null">
					<s:iterator value="researchPlan.rndResults" status="stat">
						<tr>
							<td><s:property value="name"/></td>
							<td><s:property value="descriptoin"/></td>
							<td><s:property value="trl.code"/></td>
							<td><s:property value="trlDesc"/></td>
						</tr>
					</s:iterator>
				</s:if>
			</tbody>					
		</table>
	</div>
	<hr>
	<div>
		<a class="btn btn-default" href="<s:url value="/iace/researchPlan/init"/>">確定</a>		
	</div>
	
	
</body>
</html>