<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		// addSearchConditionHiddenToForm
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#transferCaseForm").append($(this).clone());
			$("#form-backToUpdate").append($(this).clone());
		});
		
		$("input[name='talentedPeopleTransferCase.updateDate']").val(getCurrentDate());
		
		// cancel btn
		$(".btn-cancel").click(function() {
			$("#form-backToUpdate").submit();
		});
	});
	
	function getCurrentDate() {
		var date = new Date();
		return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
	}
</script>
<meta name="funcPathText" content="編輯" />
</head>
<body>
	<s:form action="updateSubmit" method="post" validate="true" id="transferCaseForm" >
		<s:hidden name="scrollTo"/>
		<s:hidden name="talentedPeopleId"/>
		<s:hidden name="talentedPeopleTransferCase.id"/>
		<s:hidden name="talentedPeopleTransferCase.isValid"/>
		<s:hidden name="talentedPeopleTransferCase.createTime"/>
		<s:hidden name="talentedPeopleTransferCase.createUser"/>
		<s:hidden name="talentedPeopleTransferCase.updateTime"/>
		<s:hidden name="talentedPeopleTransferCase.updateUser"/>
		<s:hidden name="talentedPeopleTransferCase.ver"/>
		
		<div class="subForm">
			<h2 class="itemTitle">成果移轉及授權案例</h2>
			
			<ul>
				<li class="half">
					<b>應用領域</b>
					<s:textfield name="talentedPeopleTransferCase.applyField" maxlength="500"/>
				</li>
				<li class="half">
					<b>對象廠商或機構</b>
					<s:textfield name="talentedPeopleTransferCase.targetOrg" maxlength="500"/>
				</li>
				<div class="clear"></div>
				<li class="half">
					<b>時間(授權期間或讓受/技轉時間)</b>
					<div>
						<div style="width:24%; float:left;">
							<s:select name="talentedPeopleTransferCase.yearStart" list="yearList" listKey="code" listValue="name" />
						</div>
						<div style="width:24%; float:left;">
							<s:select name="talentedPeopleTransferCase.monthStart" list="monthList" listKey="code" listValue="name" />
						</div>
						<div style="float:left;">~</div>
						<div style="width:24%; float:left;">
							<s:select name="talentedPeopleTransferCase.yearEnd" list="yearList" listKey="code" listValue="name" headerKey="" headerValue=""/>
						</div>
						<div style="width:24%; float:left;">	
							<s:select name="talentedPeopleTransferCase.monthEnd" list="monthList" listKey="code" listValue="name" headerKey="" headerValue=""/>
						</div>	
					</div>
				</li>
				<li class="quarter">
					<b>排序優先度 (將依數字小到大排序)</b>
					<s:textfield name="talentedPeopleTransferCase.priority" maxlength="19"/>
				</li>
				<li class="quarter">
					<b>資料更新日期</b>
					<s:textfield name="talentedPeopleTransferCase.updateDate" class="calendarBox" maxlength="10" placeholder="格式範例:2016/12/30" />
				</li>					
			</ul>
			
			<div class="clear"></div>
			<div>
				<input type="submit" class="redBtn" value="確定變更" />
				<input type="button" class="grayBtn btn-cancel" value="取消" />
			</div>
		</div>	
	</s:form>
	
	<s:include value="../form-backToIndex.jsp" />
	
	<s:form namespace="/talentedPeople" action="update" method="post" id="form-backToUpdate">
		<s:hidden name="id" value="%{talentedPeopleId}"/>
		<s:hidden name="scrollTo"/>
	</s:form>
</body>
</html>