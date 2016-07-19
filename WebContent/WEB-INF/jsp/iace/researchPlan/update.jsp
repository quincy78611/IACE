<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">編輯管理 > 編輯</h2>
	<div id="div-researchPlan">
		<ul>
			<li class="all">
				<label>計畫名稱</label>
				<div>
					<s:property value="researchPlan.name" />
				</div>
			</li>
			<li class="quarter">
				<b>計畫編號</b>
				<div>
					<s:property value="researchPlan.planNo" />
				</div>				
			</li>
			<li class="eighth">
				<b>計畫主持人</b>
				<div>
					<s:property value="researchPlan.manager" />
				</div>				
			</li>
			<li class="eighth">
				<b>計畫年度</b>
				<div>
					<s:property value="researchPlan.year" />
				</div>
			</li>
			<li class="quarter">
				<b>GRB計畫編號</b>
				<div>
					<s:property value="researchPlan.projkey" />
				</div>
			</li>
			<li class="half">
				<b>技術發展階段</b>
				<div>
					<s:property value="%{researchPlan.trl.showString}" />
				</div>					
			</li>
			<li class="half">
				<b>技術發展階段</b>
				<div>
					<s:if test="researchPlan.grbDomains != null">
						<s:iterator value="researchPlan.grbDomains" status="stat">
							<s:property value="showString"/>&nbsp;&nbsp;&nbsp;
						</s:iterator>
					</s:if>	
				</div>					
			</li>
			<li class="all">
				<b>計畫關鍵字</b>
				<div>
					<s:property value="researchPlan.keyword" />
				</div>				
			</li>
		</ul>
	</div>
	<div class="clear"></div>		
	<div id="div-technologhList">
		<h2 class="itemTitle Down">研發成果列表</h2>
		<table id="rndResultsTable" width="100%">
			<thead>
				<tr>
					<th nowrap width="2%">No.</th>
					<th nowrap width="20%">技術名稱</th>
					<th nowrap width="30%">技術簡述</th>
					<th nowrap width="5%">技術發展階段</th>
					<th nowrap width="">技術發展階段說明</th>
					<th nowrap width="8%">功能</th>
				</tr>
			</thead>
			<tbody>
				<s:if test="researchPlan.technologies != null">
					<s:iterator value="researchPlan.technologies" status="stat">
						<tr>
							<td><s:property value="#stat.count" /></td>
							<td><s:property value="name"/></td>
							<td><s:property value="descriptoin"/></td>
							<td style="display:none;"><!--  設定「技術發展階段」的title -->
								<s:set var="trlCodesTitle" value="''"/>
								<s:iterator value="optionTrlList" status="stat">
									<s:set var="trlCodesTitle" value='%{#trlCodesTitle+showString+"\r\n"}'/>
								</s:iterator>
							</td>
							<td title="<s:property value="#trlCodesTitle"/>">
								<s:property value="optionTrlCodesString"/>
							</td>
							<td><s:property value="trlDesc"/></td>
							<td>
								<!-- 檢視 -->
								<s:url value="showTechnologyDetail.action" var="detailUrlTag" escapeAmp="false">
									<s:param name="id" value="researchPlan.id" />
									<s:param name="TechnologyId" value="id" />
								</s:url>		
								<%-- <input type="button" class="btn-info" value="檢視" 
									onclick="window.location.href='<s:property value="detailUrlTag" />'" /> --%>
								<%-- <a href='<s:property value="detailUrlTag" />' class="view" >檢視</a> --%>
							
								<!-- 編輯 -->
								<s:url value="updateTechnology.action" var="updateUrlTag" escapeAmp="false">
									<s:param name="id" value="researchPlan.id" />
									<s:param name="TechnologyId" value="id" />
								</s:url>		
								<%-- <input type="button" class="btn-info" value="編輯" 
									onclick="window.location.href='<s:property value="updateUrlTag" />'" /> --%>
								<a href='<s:property value="updateUrlTag" />' class="edit" >編輯</a>									
							
								<!-- 刪除 -->
								<s:url value="deleteTechnology.action" var="deleteUrlTag" escapeAmp="false">
									<s:param name="id" value="researchPlan.id" />
									<s:param name="TechnologyId" value="id" />
								</s:url>		
								<%-- <input type="button" class="btn-danger" value="刪除" 
									onclick="window.location.href='<s:property value="#deleteUrlTag" />'" /> --%>
								<a href='<s:property value="deleteUrlTag" />' class="del" >刪除</a>														
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</tbody>					
		</table>
	</div>
	<div class="clear"></div>
	<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
		<input type="button" class="redBtn" value="新增研發成果" onclick="btnCreateTechnologyClick()"/>	
		<a class="grayBtn" href="<s:url value="/iace/researchPlan/init"/>">回上一頁</a>		
	</div>	
	
	<div class="clear"></div>

	<div id="div-create-technology" class="subForm">		
		<h2 class="itemTitle">新增研發成果</h2>
		<s:form action="createTechnologySubmit" method="post" validate="true" >
			<s:hidden name="id"/>
			<ul>
				<li class="all">
					<b>技術名稱</b>
					<s:textfield name="technology.name"/>
				</li>
				<li class="all">
					<b>技術簡述</b>
					<s:textarea name="technology.descriptoin" />
				</li>
				<li class="all">
					<b>技術發展階段</b>
					<s:checkboxlist name="technology.optionTrlCodes" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" />
				</li>
				<li class="all">
					<b>技術發展階段說明</b>
					<s:textarea name="technology.trlDesc" />
				</li>			
			</ul>
			
			<s:submit cssClass="redBtn" value="儲存" />
			<s:url value="update.action" var="updateUrlTag">
				<s:param name="id" value="id" />
			</s:url>
			<input type="button" class="grayBtn" value="取消"
				onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
		</s:form>
	</div>
	
	
	
	
	

	<script type="text/javascript">
		$(document).ready(function(){
			$("#div-create-technology").hide();
		});
	</script>
	<script type="text/javascript">
		function btnCreateTechnologyClick() {
			$("#div-create-technology").show();			
		}		
	</script>	


	
</body>
</html>