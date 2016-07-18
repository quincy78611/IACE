<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">刪除</h2>
	<s:form action="deleteSubmit" method="post" validate="true">
		<s:hidden name="id" />	
		
		<ul>
			<li class="all">
				<b>計畫名稱</b>
				<s:textfield name="researchPlan.name" readonly="true"/>
			</li>
			<li class="half">
				<b>計畫編號</b>
				<s:textfield name="researchPlan.planNo" readonly="true"/>
			</li>
			<li class="quarter">
				<b>計畫主持人</b>
				<s:textfield name="researchPlan.manager" readonly="true"/>
			</li>
			<li class="quarter">
				<b>計畫年度</b>
				<s:textfield name="researchPlan.year" readonly="true" placeholder="請輸入民國年"/>
			</li>
			<li class="half">
				<b>GRB計畫編號</b>
				<s:textfield name="researchPlan.projkey" readonly="true"/>
			</li>
			<li class="half">
				<b>成果報告ID</b>
				<s:textfield name="researchPlan.grb05Id" readonly="true"/>
			</li>
			<li class="half">
				<b>計畫關鍵字</b>
				<s:textfield name="researchPlan.keyword" readonly="true"/>
			</li>
			<li class="half">
				<b>技術發展階段</b>
				<s:select name="researchPlan.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue=""/>
			</li>
			<li class="third">
				<b>研究領域清單</b>
				<s:select id="grbCodesList" name="" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" multiple="true" size="9"/>
			</li>
			<li class="third">
				<%-- <input type="button" id="btn-selectGrb" class="right" value="->"/>
				<input type="button" id="btn-diselectGrb" class="left" value="<-"/> --%>
				<a href="###" id="btn-selectGrb" class="right"></a>
				<a href="###" id="btn-diselectGrb" class="left"></a>
			</li>
			<li class="third">
				<b>已選研究領域</b>
				<s:select id="selectedGrbCodes" name="researchPlan.grbDomainCodes" list="researchPlan.grbDomains" listKey="code" listValue="%{code +' ' +name}" multiple="true" size="9"/>
			</li>
		</ul>
		<div class="clear"></div>
		
		<h2 class="itemTitle Down">
			研發成果列表
		</h2>
		<table id="rndResultsTable" width="100%">
			<thead>
				<tr>
					<th nowrap width="20%">技術名稱</th>
					<th nowrap width="35%">技術簡述</th>
					<th nowrap width="5%">技術發展階段</th>
					<th nowrap width="35%">技術發展階段說明</th>
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
							<td>
								<s:property value="optionTrlCodesString"/>
							</td>
							<td>
								<s:property value="trlDesc"/>
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</tbody>					
		</table>

		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="確定" />
			<a class="grayBtn" href="<s:url value="/iace/researchPlan/init"/>">回上一頁</a>		
		</div>
	</s:form>
	
	<script type="text/javascript">
	</script>	
	
</body>
</html>