<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		addSearchConditionHiddenToForm();
		$("#btn-back").click(function(){				
			$("#form-backToIndex").submit();
		});		
	});
</script>
<script>
	function addSearchConditionHiddenToForm() {
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#form-delete").append($(this).clone());
		});
	}
</script>
<style>
table.table-talentedPeopleInfo { margin-bottom:15px; }
table.table-talentedPeopleInfo tr:hover { background:none; }
table.table-talentedPeopleInfo td { border:none; }
table.table-talentedPeopleInfo td.headShot { width:20%; border:#e6eff5 1px solid; }

table#table-domain { margin:0px; }
</style>
<meta name="funcPathText" content="編輯管理 > 刪除"/>
</head>
<body>
	<s:form action="deleteSubmit" method="post" validate="true" enctype="multipart/form-data" id="form-delete">
		<s:hidden name="id"/>

		<table class="table-talentedPeopleInfo">
			<tr>
				<td>
					<ul>
						<li class="quarter">
							<b>姓名(中)</b>
							<div class="border-text"><s:property value="talentedPeople.nameCh"/>&nbsp;</div>
						</li>
						<li class="quarter">
							<b>姓名(英)</b>
							<div class="border-text"><s:property value="talentedPeople.nameEn"/>&nbsp;</div>
						</li>
						<li class="quarter">
							<b>性別</b>
							<div class="border-text"><s:property value="talentedPeople.gender"/>&nbsp;</div>
						</li>
						<li class="quarter">
							<b>產學經驗(年)</b>
							<div class="border-text"><s:property value="talentedPeople.expYear"/>&nbsp;</div>
						</li>
						<li class="half">
							<b>連絡電話</b>
							<div class="border-text"><s:property value="talentedPeople.tel"/>&nbsp;</div>
						</li>
						<li class="half">
							<b>e-mail</b>
							<div class="border-text"><s:property value="talentedPeople.email"/>&nbsp;</div>
						</li>
						<li class="half">
							<b>現職單位</b>
							<div class="border-text"><s:property value="talentedPeople.workOrg"/>&nbsp;</div>
						</li>
						<li class="half">
							<b>現職職位</b>
							<div class="border-text"><s:property value="talentedPeople.job"/>&nbsp;</div>
						</li>
						<li class="all">
							<b>網站連結</b>
							<a href="<s:property value="talentedPeople.url"/>">
								<s:property value="talentedPeople.url"/>
							</a>
						</li>					
					</ul>					
				</td>
				<td class="headShot text-align-center">
					<img src="data:image;base64,<s:property value="talentedPeople.base64HeadShot"/>" style="max-width:150px; max-height:200px;" />
				</td>				
			</tr>
		</table>
	
		<ul>
			<li class="all">
				<b>領域</b>
				<table id="table-domain">
					<s:iterator value="mainDomainList" status="stat">
						<tr>
							<td>
								<span style="font-weight:bold;"><s:property value="name"/></span>
								<div class="horizontalList">
									<s:iterator value="subDomainList" status="stat2">
										<div class="checkbox">
											<input type="checkbox" disabled="disabled"
												name="talentedPeople.domainsId" 
												id="<s:property value="%{'chkbox_'+id}" />"
												value="<s:property value="%{id}" />"
												<s:property value="%{talentedPeople.domainsId.contains(id) ? 'checked' : ''}"/>
											/>										
											<label for="<s:property value="%{'chkbox_'+id}" />">
												<s:property value="name"/>
											</label>
										</div>
									</s:iterator>
								</div>
							<td>
						<tr>
					</s:iterator>
				</table>				
			</li>
			<li class="all">
				<b>合作專長</b>
				<div class="border-text"><s:property value="talentedPeople.specialty"/>&nbsp;</div>
			</li>
		</ul>
		
		<div class="clear"></div>
		<div class="bottom-btn-block">
			<s:submit cssClass="redBtn" value="送出" />
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>		
	</s:form>
	<form action="index" method="post" id="form-backToIndex">
		<s:hidden name="searchCondition.name"/>
		<s:hidden name="searchCondition.gender"/>
		<s:hidden name="searchCondition.expYearS"/>
		<s:hidden name="searchCondition.expYearE"/>
		<s:hidden name="searchCondition.workOrg"/>
		<s:hidden name="searchCondition.job"/>
		<s:hidden name="searchCondition.specialty"/>
		<s:hidden name="searchCondition.grbDomainIdList"/>
		<s:hidden name="searchCondition.pageIndex"/>
		<s:hidden name="searchCondition.pageSize"/>
	</form>
</body>
</html>