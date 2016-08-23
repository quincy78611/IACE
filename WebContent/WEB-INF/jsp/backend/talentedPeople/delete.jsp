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
.headShot { width:20%; }
</style>
<meta name="funcPathText" content="編輯管理 > 刪除"/>
</head>
<body>
	<s:form action="deleteSubmit" method="post" validate="true" enctype="multipart/form-data" id="form-delete">
		<s:hidden name="id"/>
		<table>
			<tr>
				<td>
					<ul>
						<li class="quarter">
							<b>姓名(中)</b>
							<div class="border-text">
								<s:property value="talentedPeople.nameCh"/>
							</div>
						</li>
						<li class="quarter">
							<b>姓名(英)</b>
							<div class="border-text">
								<s:property value="talentedPeople.nameEn"/>
							</div>							
						</li>
						<li class="quarter">
							<b>性別</b>
							<div class="border-text">
								<s:property value="talentedPeople.gender"/>
							</div>							
						</li>
						<li class="quarter">
							<b>產學經驗(年)</b>
							<div class="border-text">
								<s:property value="talentedPeople.expYear"/>
							</div>							
						</li>
						<li class="half">
							<b>連絡電話</b>
							<div class="border-text">
								<s:property value="talentedPeople.tel"/>
							</div>							
						</li>
						<li class="half">
							<b>e-mail</b>
							<div class="border-text">
								<s:property value="talentedPeople.email"/>
							</div>							
						</li>
						<li class="half">
							<b>現職單位</b>
							<div class="border-text">
								<s:property value="talentedPeople.workOrg"/>
							</div>							
						</li>
						<li class="half">
							<b>現職職位</b>
							<div class="border-text">
								<s:property value="talentedPeople.job"/>
							</div>							
						</li>
						<li class="all">
							<b>網站連結</b>
							<div class="border-text">
								<s:property value="talentedPeople.url"/>
							</div>							
						</li>
						<li class="all">
							<b>合作專長</b>
							<div class="border-text">
								
							</div>							
						</li>						
					</ul>
				</td>
				<td class="headShot">
					<img src="data:image;base64,<s:property value="talentedPeople.base64HeadShot"/>" style="max-width:150px; max-height:200px;" />
				</td>
			<tr>
		</table>
		
		<div class="clear"></div>
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="送出" />
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>		
	</s:form>
	<form action="index" method="post" id="form-backToIndex">
		<s:hidden name="searchCondition.name"/>
		<s:hidden name="searchCondition.gender"/>
		<s:hidden name="searchCondition.expYear"/>
		<s:hidden name="searchCondition.tel"/>
		<s:hidden name="searchCondition.email"/>
		<s:hidden name="searchCondition.pageIndex"/>
		<s:hidden name="searchCondition.pageSize"/>
	</form>
</body>
</html>