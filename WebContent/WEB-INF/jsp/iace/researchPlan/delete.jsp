<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h3>刪除</h3>
	<s:form action="deleteSubmit" method="post" validate="true">
		<div class="container-fluid" >
			<s:hidden name="id" />
			
			<div class="row">
				<div class="col-md-3">
					<s:textfield label="計畫編號" value="%{researchPlan.planNo}" readonly="true"/>
				</div>
				<div class="col-md-5">	
					<s:textfield label="計畫名稱" value="%{researchPlan.name}" readonly="true"/>
				</div>
				<div class="col-md-3">			
					<s:textfield label="計畫主持人" value="%{researchPlan.manager}" readonly="true"/>
				</div>
				<div class="col-md-1">		
					<s:textfield label="計畫年度" value="%{researchPlan.year}" readonly="true"/>
				</div>			
			</div>
			<div class="row">
				<div class="col-md-3">			
					<s:textfield label="計畫關鍵字" value="%{researchPlan.keyword}" readonly="true"/>
				</div>
				<div class="col-md-5">			
					<s:textfield label="計畫發展階段" value="%{researchPlan.trl.code + ' ' + researchPlan.trl.name}" readonly="true"/>
				</div>
				<div class="col-md-2">			
					<s:textfield label="GRB計畫編號" value="%{researchPlan.projkey}" readonly="true"/>
				</div>
				<div class="col-md-2">
					<label class="control-label">成果報告ID</label><p>				
					<a href="<s:url value="%{'http://grbsearch.stpi.narl.org.tw/GRB_Search/grb/show_doc.jsp?id='+researchPlan.grb05Id}"/>" target="_blank">
						<s:property value="%{researchPlan.grb05Id}" />
					</a>	
				</div>		
			</div>		
			<div class="row">
				<div class="col-md-2">
					<s:textfield label="研究領域1" value="%{researchPlan.grbDomain1.code + ' ' + researchPlan.grbDomain1.name}" readonly="true"/>
				</div>
				<div class="col-md-2">
					<s:textfield label="研究領域2" value="%{researchPlan.grbDomain2.code + ' ' + researchPlan.grbDomain2.name}" readonly="true"/>
				</div>
				<div class="col-md-2">
					<s:textfield label="研究領域3" value="%{researchPlan.grbDomain3.code + ' ' + researchPlan.grbDomain3.name}" readonly="true"/>
				</div>
				<div class="col-md-2">
					<s:textfield label="研究領域4" value="%{researchPlan.grbDomain4.code + ' ' + researchPlan.grbDomain4.name}" readonly="true"/>
				</div>
				<div class="col-md-2">
					<s:textfield label="研究領域5" value="%{researchPlan.grbDomain5.code + ' ' + researchPlan.grbDomain5.name}" readonly="true"/>
				</div>
				<div class="col-md-2">
					<s:textfield label="研究領域6" value="%{researchPlan.grbDomain6.code + ' ' + researchPlan.grbDomain6.name}" readonly="true"/>
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
					<s:if test="researchPlan.technologies != null">
						<s:iterator value="researchPlan.technologies" status="stat">
							<tr>
								<td><s:property value="name"/></td>
								<td><s:property value="descriptoin"/></td>
								<td><s:property value="%{getOptionTrlCodesString()}"/></td>
								<td><s:property value="trlDesc"/></td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>					
			</table>
		</div>
		<hr>
		<div>
			<s:submit cssClass="btn btn-primary" value="確定" />
			<a class="btn btn-default" href="<s:url value="/iace/researchPlan/init"/>">回上一頁</a>		
		</div>
	</s:form>
	
	<script type="text/javascript">
	</script>	
	
</body>
</html>