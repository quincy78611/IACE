<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h3>編輯</h3>
	<s:form action="updateSubmit" method="post" validate="true">
		<div class="container-fluid" >
			<s:hidden name="consulting.id" />
			<s:hidden name="consulting.isValid" />
			<s:hidden name="consulting.createTime" />
			<s:hidden name="consulting.createUser" />
			<s:hidden name="consulting.updateTime" />
			<s:hidden name="consulting.updateUser" />
			<s:hidden name="consulting.ver" />
					
			<div class="col-md-3">
				<s:textfield label="姓名" name="consulting.name" class="form-control" />
			</div>
			<div class="col-md-9">
				<s:textfield label="單位名稱" name="consulting.organization" class="form-control" />
			</div>			
			<div class="col-md-3">
				<s:select label="單位類型" name="consulting.optionOrganizationType.code" list="optionOrganizationTypeList" listKey="code" listValue="%{code +' ' +name}" class="orgType"/>		
			</div>
			<div class="col-md-9">
				<s:textfield label="單位類型(其他)" name="consulting.orgTypeOther" disabled="true" class="form-control orgType" />
			</div>
			<div class="col-md-3">
				<s:select label="諮詢類型" name="consulting.optionConsult.code" list="optionConsultList" listKey="code" listValue="%{code +' ' +name}" class="consult"/>		
			</div>
			<div class="col-md-9">
				<s:textfield label="諮詢類型(其他)" name="consulting.consultTypeOther" disabled="true" class="form-control consult" />
			</div>
			<div class="col-md-3">
				<s:select label="產業/領域別" name="consulting.optionIndustry.code" list="optionIndustryList" listKey="code" listValue="%{code +' ' +name}" class="industry" />		
			</div>
			<div class="col-md-9">
				<s:textfield label="產業/領域別(其他)" name="consulting.industryOther" disabled="true" class="form-control industry" />
			</div>
			<div class="col-md-3">
				<s:textfield label="聯絡電話" name="consulting.phone" class="form-control" />		
			</div>
			<div class="col-md-6">
				<s:textfield label="E-MAIL" name="consulting.email" class="form-control" />		
			</div>
			<div class="col-md-3">
				<s:textfield label="諮詢日期" name="consulting.consultDate" cssClass="form-control calendarBox" >			
					<s:param name="value">
						<s:date name="consulting.consultDate" format="yyyy/MM/dd" /> 
					</s:param>
				</s:textfield>
			</div>			
			<div class="col-md-12">
				<s:textarea label="內容說明" name="consulting.content" cssClass="form-control"/>
			</div>
		</div>
 		<hr>
		<div class="container-fluid">
			<div class="col-md-4">
				<s:checkbox label="同意【個人資料蒐集、處理及利用之告知暨同意條款】" name="consulting.approvalCheckBox"/>
			</div>		
			<s:submit class="btn btn-primary" value="儲存" />
			<a class="btn btn-default" href="<s:url value="/iace/consulting/init"/>">回上一頁</a>
		</div>
	</s:form>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("select.orgType").change(function(){
				var name = $(this).find("option:selected").html();
				if (name.includes("其他")) {
					$("input[type='text'].orgType").removeAttr("disabled");
				} else {
					$("input[type='text'].orgType").val("");
					$("input[type='text'].orgType").attr("disabled", "disabled");
				}
			});
			$("select.consult").change(function(){
				var name = $(this).find("option:selected").html();
				if (name.includes("其他")) {
					$("input[type='text'].consult").removeAttr("disabled");
				} else {
					$("input[type='text'].consult").val("");
					$("input[type='text'].consult").attr("disabled", "disabled");
				}
			});
			$("select.industry").change(function(){
				var name = $(this).find("option:selected").html();
				if (name.includes("其他")) {
					$("input[type='text'].industry").removeAttr("disabled");
				} else {
					$("input[type='text'].industry").val("");
					$("input[type='text'].industry").attr("disabled", "disabled");
				}
			});
		});
	</script>

</body>
</html>