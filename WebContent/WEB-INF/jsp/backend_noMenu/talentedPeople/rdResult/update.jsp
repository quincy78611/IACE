<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		$("input[name='talentedPeopleRdResult.updateDate']").val(getCurrentDate());
		
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
	<s:form action="selfUpdateSubmit" method="post" validate="true" id="rdResultForm" >
		<s:hidden name="scrollTo"/>
		<s:hidden name="talentedPeopleId"/>
		<s:hidden name="talentedPeopleRdResult.id"/>
		<s:hidden name="talentedPeopleRdResult.isValid"/>
		<s:hidden name="talentedPeopleRdResult.createTime"/>
		<s:hidden name="talentedPeopleRdResult.createUser"/>
		<s:hidden name="talentedPeopleRdResult.updateTime"/>
		<s:hidden name="talentedPeopleRdResult.updateUser"/>
		<s:hidden name="talentedPeopleRdResult.ver"/>
	
		<div class="subForm">
			<h2 class="itemTitle">重要研發成果</h2>
			<ul>
				<li class="quarter">
					<b>1.研發成果名稱</b> 
					<s:textfield name="talentedPeopleRdResult.name" maxlength="500"/>
				</li>
				<li class="quarter">
					<b>2.型式</b> 
					<s:select name="talentedPeopleRdResult.type" list="rdResultTypeList" listKey="code" listValue="name" />
				</li>
				<li class="quarter">
					<b>3.發明人(創作人)</b> 
					<s:textfield name="talentedPeopleRdResult.inventer" maxlength="100"/>
				</li>
				<li class="quarter">
					<b>4.所有權人</b>
					<s:textfield name="talentedPeopleRdResult.owner" maxlength="100"/>
				</li>
				<div class="clear"></div>
				<li class="quarter">
					<b>5.專利號(申請中請填申請號)</b>
					<s:textfield name="talentedPeopleRdResult.patentNo" maxlength="100"/>
				</li>
				<li class="quarter">
					<b>6.國別</b> 
					<s:select name="talentedPeopleRdResult.optionCountry.id" list="countryList" listKey="id" listValue="name" />
				</li>
				<li class="quarter">
					<b>7.專利期間(起)</b> 
					<s:textfield name="talentedPeopleRdResult.patentPeriodStart" class="calendarBox" maxlength="10" placeholder="格式範例:2016/12/30" />
				</li>
				<li class="quarter">
					<b>7.專利期間(迄)</b>
					<s:textfield name="talentedPeopleRdResult.patentPeriodEnd" class="calendarBox" maxlength="10" placeholder="格式範例:2016/12/30" />
				</li>	
				<div class="clear"></div>
				<li class="all">
					<b>8.摘要(請說明成果重點與特色)</b> 
					<s:textarea name="talentedPeopleRdResult.patentAbstract" rows="3" />
				</li>
				<li class="all">
					<b>9.應用產業/範圍(請說明成果可應用於何領域或產品)</b> 
					<s:textfield name="talentedPeopleRdResult.usage" maxlength="1000"/>
				</li>
				<li class="half">
					<b>排序優先度 (將依數字小到大排序)</b> 
					<s:textfield name="talentedPeopleRdResult.priority" maxlength="19"/>
				</li>
				<li class="half">
					<b>資料更新日期</b> 
					<s:textfield name="talentedPeopleRdResult.updateDate" class="calendarBox" maxlength="10" placeholder="格式範例:2016/12/30" />
				</li>
			</ul>

			<div class="clear"></div>
			<div>
				<input type="submit" class="redBtn" value="確定變更" />
				<input type="button" class="grayBtn btn-cancel" value="取消" />
			</div>
		</div>
	</s:form>

	<s:form namespace="/f/talentedPeople" action="selfUpdate" method="post" id="form-backToUpdate">
		<s:hidden name="id" value="%{talentedPeopleId}"/>
		<s:hidden name="scrollTo"/>
	</s:form>
</body>