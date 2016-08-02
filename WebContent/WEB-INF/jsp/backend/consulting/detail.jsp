<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">編輯管理 > 檢視</h2>

	<ul>
		<li class="half">
			<b>姓名</b>
			<s:textfield name="consulting.name" class="form-control" readonly="true"/>
		</li>
		<li class="half">
			<b>單位名稱</b>
			<s:textfield name="consulting.organization" class="form-control" readonly="true"/>
		</li>
		<li class="half">
			<b>單位類型</b>
			<s:select name="consulting.optionOrganizationType.code" list="optionOrganizationTypeList" listKey="code" listValue="%{code +' ' +name}" class="orgType" disabled="true"/>
		</li>
		<li class="half">
			<b>單位類型(其他)</b>
			<s:textfield name="consulting.orgTypeOther" class="form-control orgType" readonly="true"/>
		</li>
		<li class="half">
			<b>諮詢類型</b>
			<s:select name="consulting.optionConsult.code" list="optionConsultList" listKey="code" listValue="%{code +' ' +name}" class="consult" disabled="true"/>
		</li>
		<li class="half">
			<b>諮詢類型(其他)</b>
			<s:textfield name="consulting.consultTypeOther" class="form-control consult" readonly="true"/>
		</li>
		<li class="half">
			<b>產業/領域別</b>
			<s:select name="consulting.optionIndustry.code" list="optionIndustryList" listKey="code" listValue="%{code +' ' +name}" class="industry" disabled="true"/>
		</li>
		<li class="half">
			<b>產業/領域別(其他)</b>
			<s:textfield name="consulting.industryOther" class="form-control industry" readonly="true"/>
		</li>
		<li class="quarter">
			<b>聯絡電話</b>
			<s:textfield name="consulting.phone" class="form-control" readonly="true"/>	
		</li>
		<li class="half">
			<b>E-MAIL</b>
			<s:textfield name="consulting.email" class="form-control" readonly="true"/>		
		</li>
		<li class="quarter">
			<b>諮詢日期</b>
			<s:textfield name="consulting.consultDate" cssClass="form-control calendarBox" readonly="true">			
				<s:param name="value">
					<s:date name="consulting.consultDate" format="yyyy/MM/dd" /> 
				</s:param>
			</s:textfield>				
		</li>
		<li class="all">
			<b>內容說明</b>
			<s:textarea name="consulting.content" cssClass="form-control" readonly="true"/>
		</li>		
		<li class="all">
			<s:checkbox label="同意【個人資料蒐集、處理及利用之告知暨同意條款】" name="consulting.approvalCheckBox" disabled="true"/>
		</li>
	</ul>
	<div class="clear"></div>
	<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
		<s:url value="update.action" var="updateUrlTag">
			<s:param name="id" value="consulting.id" />
		</s:url>
		<s:if test="#request.context['struts.actionMapping'].name == 'showDetail'">
			<input type="button" class="btn btn-info redBtn" value="編輯" 
				onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
		</s:if>
		<input type="button" class="grayBtn" value="回上一頁" onclick="window.location.href='<s:url value="/consulting/init"/>'" />
	
	</div>
	
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
			$("select.orgType").trigger('change');
			
			$("select.consult").change(function(){
				var name = $(this).find("option:selected").html();
				if (name.includes("其他")) {
					$("input[type='text'].consult").removeAttr("disabled");
				} else {
					$("input[type='text'].consult").val("");
					$("input[type='text'].consult").attr("disabled", "disabled");
				}
			});
			$("select.consult").trigger('change');
			
			$("select.industry").change(function(){
				var name = $(this).find("option:selected").html();
				if (name.includes("其他")) {
					$("input[type='text'].industry").removeAttr("disabled");
				} else {
					$("input[type='text'].industry").val("");
					$("input[type='text'].industry").attr("disabled", "disabled");
				}
			});
			$("select.industry").trigger('change');
		});
	</script>	
</body>
</html>