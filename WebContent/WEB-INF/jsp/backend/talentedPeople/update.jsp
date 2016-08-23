<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		fileBrowseSetting();
		addSearchConditionHiddenToForm();
		$("#btn-back").click(function(){				
			$("#form-backToIndex").submit();
		});
	});
</script>
<script>
	function addSearchConditionHiddenToForm() {
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#form-update").append($(this).clone());
		});
	}
</script>
<script>
	function fileBrowseSetting() {
		$(".btn-fake-browse").click(function(){
			$(this).parents(".headShot").find("input[type=file]").trigger("click");
		});
		
		$("input[type=file]").change(function() {
			readURL(this); // display subnail
		});
		
		$("input[type=button].cancelSelectFile").click(function(){
			$(this).parents(".headShot").find("input[type=file]").val("");
			$(this).parents(".headShot").find("input[name='talentedPeople.base64HeadShot']").val("");
			$(this).parents(".headShot").find("img").attr('src', '');
		});
	}
</script>
<script>
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	        	$(input).parents(".headShot").find("img").attr('src', e.target.result);
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}
</script>
<style>
.headShot { width:20%; }
.headShot input[type=file] { display:none; }
</style>
<meta name="funcPathText" content="編輯管理  > 編輯"/>
</head>
<body>
	<s:form action="updateSubmit" method="post" validate="true" enctype="multipart/form-data" id="form-update">
		<s:hidden name="talentedPeople.id" />
		<s:hidden name="talentedPeople.isValid" />
		<s:hidden name="talentedPeople.createTime" />
		<s:hidden name="talentedPeople.createUser" />
		<s:hidden name="talentedPeople.updateTime" />
		<s:hidden name="talentedPeople.updateUser" />
		<s:hidden name="talentedPeople.ver" />
		
		<table>
			<tr>
				<td>
					<ul>
						<li class="quarter">
							<b>姓名(中)</b>
							<s:textfield name="talentedPeople.nameCh"/>
						</li>
						<li class="quarter">
							<b>姓名(英)</b>
							<s:textfield name="talentedPeople.nameEn"/>
						</li>
						<li class="quarter">
							<b>性別</b>
							<s:select name="talentedPeople.gender" list="#{ '男':'男', '女':'女' }"/>
						</li>
						<li class="quarter">
							<b>產學經驗(年)</b>
							<s:textfield name="talentedPeople.expYear"/>
						</li>
						<li class="half">
							<b>連絡電話</b>
							<s:textfield name="talentedPeople.tel"/>
						</li>
						<li class="half">
							<b>e-mail</b>
							<s:textfield name="talentedPeople.email"/>
						</li>
						<li class="half">
							<b>現職單位</b>
							<s:textfield name="talentedPeople.workOrg"/>
						</li>
						<li class="half">
							<b>現職職位</b>
							<s:textfield name="talentedPeople.job"/>
						</li>
						<li class="all">
							<b>網站連結</b>
							<s:textfield name="talentedPeople.url"/>
						</li>
						<li class="all">
							<b>合作專長</b>
							
						</li>						
					</ul>
				</td>
				<td class="headShot">
					<img src="data:image;base64,<s:property value="talentedPeople.base64HeadShot"/>" style="max-width:150px; max-height:200px;" />
					<s:file type="file" name="talentedPeople.uploadheadShot" class="upload" accept=".jpg, .jpeg, .png, .gif" />
					<s:hidden name="talentedPeople.base64HeadShot"/>
					<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
					<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
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