<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
$(document).ready(function() {
	fileBrowseSetting();
	addSearchConditionHiddenToForm();
	
	rdResultSetting();
	
	addMoreTransferCaseBtnSetting();
	bindTransferCaseDeleteBtn();
	
	addMoreMainProjectBtnSetting();
	bindMainProjectDeleteBtn();
	
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
<script>
function rdResultSetting() {
	bindEditRdResultRowBtn();
	bindDeleteRdResultRowBtn();
	$("#rdResultForm").hide();
	$("#rdResultForm .btn-addConfirm").hide();
	$("#rdResultForm .btn-editConfirm").hide();	
	
	$("input[type=button]#add-rdResult").click(function(){
		$("#form-update").hide();
		resetRdResultForm();
		$("#rdResultForm").show();
		$("#rdResultForm .btn-addConfirm").show();
		$("#rdResultForm .btn-editConfirm").hide();
		$('html,body').animate({scrollTop:$('#rdResultForm').offset().top},500);
	});
	
	$("#rdResultForm .btn-addConfirm").click(function() {
		var newTr = $("#table-rdResult").find("tr.hidden-sample-tr").clone();
		setValueToRdResultRow(newTr);
		newTr.removeClass("hidden-sample-tr");
		$("#table-rdResult").find("tbody").append(newTr);
		bindEditRdResultRowBtn();
		bindDeleteRdResultRowBtn();
		resetNameForRdResult();
		$('#rdResultForm .btn-cancel').trigger("click");
	});
	
	$("#rdResultForm .btn-editConfirm").click(function(){
		var index = $("#rdResultForm .rdResultRowIndex").val();
		var editTr = $("#table-rdResult tbody tr").eq(index);
		setValueToRdResultRow(editTr);
		resetNameForRdResult();
		$('#rdResultForm .btn-cancel').trigger("click");
	});
	
	$('#rdResultForm .btn-cancel').click(function() {
		resetRdResultForm();
		$("#rdResultForm").hide();
		$("#rdResultForm .btn-addConfirm").hide();
		$("#rdResultForm .btn-editConfirm").hide();
		$("#form-update").show();
		$('html,body').animate({scrollTop:$('#add-rdResult').offset().top},300);
	});

}
function resetRdResultForm() {
	$("#rdResultForm input[type=text]").val("");
	$("#rdResultForm textarea").val("");
	$("#rdResultForm select").prop('selectedIndex', 0);
}
function bindEditRdResultRowBtn() {
	$("#table-rdResult input[type=button].btn-edit").click(function() {
		$("#form-update").hide();
		$("#rdResultForm").show();
		$("#rdResultForm .btn-addConfirm").hide();
		$("#rdResultForm .btn-editConfirm").show();
		$('html,body').animate({scrollTop:$('#rdResultForm').offset().top},300);
		
		var tr = $(this).parents("tr");
		setValueToRdResultForm(tr);
	});		
}
function bindDeleteRdResultRowBtn() {
	$("#table-rdResult input[type=button].btn-del").click(function(){
		$(this).parents("tr").remove();
		resetNameForRdResult();
	});
}
function setValueToRdResultForm( tr ) {
	var rowIndex = $("#table-rdResult tbody tr").index(tr);
	$("#rdResultForm .rdResultRowIndex").val(rowIndex);
	$("#rdResultForm .name").val(tr.find(".name").val());
	$("#rdResultForm .type").val(tr.find(".type").val());
	$("#rdResultForm .inventer").val(tr.find(".inventer").val());
	$("#rdResultForm .owner").val(tr.find(".owner").val());
	$("#rdResultForm .applicationNo").val(tr.find(".applicationNo").val());
	$("#rdResultForm .patentNo").val(tr.find(".patentNo").val());
	$("#rdResultForm .country").val(tr.find(".country").val());
	$("#rdResultForm .patentPeriodStart").val(tr.find(".patentPeriodStart").val());
	$("#rdResultForm .patentPeriodEnd").val(tr.find(".patentPeriodEnd").val());
	$("#rdResultForm .patentAbstract").val(tr.find(".patentAbstract").val());
	$("#rdResultForm .usage").val(tr.find(".usage").val());
}
function setValueToRdResultRow( tr ) {
	tr.find(".name").val($("#rdResultForm .name").val());
	tr.find("td.td-name").html($("#rdResultForm .name").val());
	tr.find(".type").val($("#rdResultForm .type").val());
	tr.find("td.td-type").html($("#rdResultForm .type").val());	
	tr.find(".inventer").val($("#rdResultForm .inventer").val());
	tr.find(".owner").val($("#rdResultForm .owner").val());
	tr.find(".patentNo").val($("#rdResultForm .patentNo").val());
	tr.find(".country").val($("#rdResultForm .country").val());
	tr.find(".patentPeriodStart").val($("#rdResultForm .patentPeriodStart").val());
	tr.find(".patentPeriodEnd").val($("#rdResultForm .patentPeriodEnd").val());
	tr.find(".patentAbstract").val($("#rdResultForm .patentAbstract").val());
	tr.find(".usage").val($("#rdResultForm .usage").val());
}
function resetNameForRdResult() {
	$("#table-rdResult > tbody > tr").each(function( index ){
		var tr = $("#table-rdResult > tbody > tr").eq(index);
		tr.find(".id").attr("name", "talentedPeople.rdResults["+index+"].id");
		tr.find(".isValid").attr("name", "talentedPeople.rdResults["+index+"].isValid");
		tr.find(".createTime").attr("name", "talentedPeople.rdResults["+index+"].createTime");
		tr.find(".createUser").attr("name", "talentedPeople.rdResults["+index+"].createUser");
		tr.find(".updateTime").attr("name", "talentedPeople.rdResults["+index+"].updateTime");
		tr.find(".updateUser").attr("name", "talentedPeople.rdResults["+index+"].updateUser");
		tr.find(".ver").attr("name", "talentedPeople.rdResults["+index+"].ver");
		tr.find(".name").attr("name", "talentedPeople.rdResults["+index+"].name");
		tr.find(".type").attr("name", "talentedPeople.rdResults["+index+"].type");
		tr.find(".inventer").attr("name", "talentedPeople.rdResults["+index+"].inventer");
		tr.find(".owner").attr("name", "talentedPeople.rdResults["+index+"].owner");
		tr.find(".patentNo").attr("name", "talentedPeople.rdResults["+index+"].patentNo");
		tr.find(".country").attr("name", "talentedPeople.rdResults["+index+"].optionCountry.id");
		tr.find(".patentPeriodStart").attr("name", "talentedPeople.rdResults["+index+"].patentPeriodStart");
		tr.find(".patentPeriodEnd").attr("name", "talentedPeople.rdResults["+index+"].patentPeriodEnd");
		tr.find(".patentAbstract").attr("name", "talentedPeople.rdResults["+index+"].patentAbstract");
		tr.find(".usage").attr("name", "talentedPeople.rdResults["+index+"].usage");
	});
}
</script>
<script>
function addMoreTransferCaseBtnSetting() {
	$("input[type=button]#add-transferCases").click(function(){
		var newTr = $("#table-transferCase").find("tr.hidden-sample-tr").clone();
		newTr.removeClass("hidden-sample-tr");
		$("#table-transferCase").find("tbody").append(newTr);
		resetNameForTransferCase();
		bindTransferCaseDeleteBtn();
	});
}
function bindTransferCaseDeleteBtn() {
	$("#table-transferCase input[type=button].btn-del").click(function(){
		$(this).parents("tr").remove();
		resetNameForTransferCase();
	});	
}
function resetNameForTransferCase() {
	$("#table-transferCase > tbody > tr").each(function( index ){
		var tr = $("#table-transferCase > tbody > tr").eq(index);
		
		tr.find(".id").attr("name", "talentedPeople.transferCases["+index+"].id");
		tr.find(".isValid").attr("name", "talentedPeople.transferCases["+index+"].isValid");
		tr.find(".createTime").attr("name", "talentedPeople.transferCases["+index+"].createTime");
		tr.find(".createUser").attr("name", "talentedPeople.transferCases["+index+"].createUser");
		tr.find(".updateTime").attr("name", "talentedPeople.transferCases["+index+"].updateTime");
		tr.find(".updateUser").attr("name", "talentedPeople.transferCases["+index+"].updateUser");
		tr.find(".ver").attr("name", "talentedPeople.transferCases["+index+"].ver");
		tr.find(".applyField").attr("name", "talentedPeople.transferCases["+index+"].applyField");
		tr.find(".targetOrg").attr("name", "talentedPeople.transferCases["+index+"].targetOrg");
		tr.find(".yearStart").attr("name", "talentedPeople.transferCases["+index+"].yearStart");
		tr.find(".monthStart").attr("name", "talentedPeople.transferCases["+index+"].monthStart");
		tr.find(".yearEnd").attr("name", "talentedPeople.transferCases["+index+"].yearEnd");
		tr.find(".monthEnd").attr("name", "talentedPeople.transferCases["+index+"].monthEnd");
	});
}
</script>
<script>
function addMoreMainProjectBtnSetting() {
	$("input[type=button]#add-mainProject").click(function(){
		var newTr = $("#table-mainProject").find("tr.hidden-sample-tr").clone();
		newTr.removeClass("hidden-sample-tr");
		$("#table-mainProject").find("tbody").append(newTr);
		resetNameForMainProject();
		bindMainProjectDeleteBtn();
	});
}
function bindMainProjectDeleteBtn() {
	$("#table-mainProject input[type=button].btn-del").click(function(){
		$(this).parents("tr").remove();
		resetNameForMainProject();
	});	
}
function resetNameForMainProject() {
	$("#table-mainProject > tbody > tr").each(function( index ){
		var tr = $("#table-mainProject > tbody > tr").eq(index);
		
		tr.find(".id").attr("name", "talentedPeople.mainProjects["+index+"].id");
		tr.find(".isValid").attr("name", "talentedPeople.mainProjects["+index+"].isValid");
		tr.find(".createTime").attr("name", "talentedPeople.mainProjects["+index+"].createTime");
		tr.find(".createUser").attr("name", "talentedPeople.mainProjects["+index+"].createUser");
		tr.find(".updateTime").attr("name", "talentedPeople.mainProjects["+index+"].updateTime");
		tr.find(".updateUser").attr("name", "talentedPeople.mainProjects["+index+"].updateUser");
		tr.find(".ver").attr("name", "talentedPeople.mainProjects["+index+"].ver");
		tr.find(".name").attr("name", "talentedPeople.mainProjects["+index+"].name");
		tr.find(".coopComName").attr("name", "talentedPeople.mainProjects["+index+"].coopComName");
		tr.find(".yearStart").attr("name", "talentedPeople.mainProjects["+index+"].yearStart");
		tr.find(".monthStart").attr("name", "talentedPeople.mainProjects["+index+"].monthStart");
		tr.find(".yearEnd").attr("name", "talentedPeople.mainProjects["+index+"].yearEnd");
		tr.find(".monthEnd").attr("name", "talentedPeople.mainProjects["+index+"].monthEnd");
	});
}
</script>
<style>
table.table-talentedPeopleInfo { margin-bottom:15px; }
table.table-talentedPeopleInfo tr:hover { background:none; }
table.table-talentedPeopleInfo td { border:none; }
table.table-talentedPeopleInfo td.headShot { width:20%; border:#e6eff5 1px solid; }
input[type=file][name="talentedPeople.uploadheadShot"] { display:none }

table#table-domain { margin:0px; }

tr.hidden-sample-tr { display:none; }
#table-rdResult li { margin-bottom:0px; }
#table-rdResult li select { padding: 7px 5px; font-size:0.76em; }
#table-transferCase td div { float:left; }
#table-mainProject td div { float:left; }
</style>
<meta name="funcPathText" content="編輯管理  > 編輯"/>
</head>
<body>
<div class="rightContent frontend">
	<s:form action="selfUpdateSubmit" method="post" validate="true" enctype="multipart/form-data" id="form-update">
		<div>
			<s:hidden name="talentedPeople.id" />
			<s:hidden name="talentedPeople.isValid" />
			<s:hidden name="talentedPeople.createTime" />
			<s:hidden name="talentedPeople.createUser" />
			<s:hidden name="talentedPeople.updateTime" />
			<s:hidden name="talentedPeople.updateUser" />
			<s:hidden name="talentedPeople.ver" />
			<s:hidden name="talentedPeople.sysUser.id"/>
		</div>
		
		<table class="table-talentedPeopleInfo">
			<tr>
				<td><b style="font-size:20px; font-weight:bold;">基本資料</b></td>
				<td><b style="font-size:20px; font-weight:bold;">照片</b></td>
			</tr>
			<tr>
				<td>
					<ul>
						<li class="quarter">
							<b>姓名(中)</b>
							<s:textfield name="talentedPeople.nameCh" maxlength="100"/>
						</li>
						<li class="quarter">
							<b>姓名(英)</b>
							<s:textfield name="talentedPeople.nameEn" maxlength="100"/>
						</li>
						<li class="quarter">
							<b>性別</b>
							<s:select name="talentedPeople.gender" list="#{ '男':'男', '女':'女' }"/>
						</li>
						<li class="quarter">
							<b>產學經驗(年)</b>
							<s:textfield name="talentedPeople.expYear" maxlength="4"/>
						</li>
						<li class="half">
							<b>連絡電話</b>
							<s:textfield name="talentedPeople.tel" maxlength="20"/>
						</li>
						<li class="half">
							<b>e-mail</b>
							<s:textfield name="talentedPeople.email" maxlength="100"/>
						</li>
						<li class="half">
							<b>現職單位</b>
							<s:textfield name="talentedPeople.workOrg" maxlength="100"/>
						</li>
						<li class="half">
							<b>現職職位</b>
							<s:textfield name="talentedPeople.job" maxlength="100"/>
						</li>
						<li class="all">
							<b>網站連結</b>
							<s:textfield name="talentedPeople.url" maxlength="1000"/>
						</li>						
					</ul>					
				</td>
				<td class="headShot text-align-center">
					<s:hidden name="talentedPeople.base64HeadShot"/>
					<img src="data:image;base64,<s:property value="talentedPeople.base64HeadShot"/>" style="max-width:150px; max-height:200px;" />
					<s:file type="file" name="talentedPeople.uploadheadShot" class="upload" accept=".jpg, .jpeg, .png, .gif" />
					<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
					<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
				</td>				
			</tr>
		</table>

<!----------------------------------------------------------------------------->
	
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
											<input type="checkbox" 
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
				<s:textfield name="talentedPeople.specialty" maxlength="1000"/>
			</li>
		</ul>
		
<!----------------------------------------------------------------------------->
		
		<b>重要研發成果(包含:專利,技術,IC佈局, 軟體…..)</b> 
		<input type="button" id="add-rdResult" value="+"/>
		<table id="table-rdResult">
			<thead>
				<tr>
					<th>研發成果名稱</th>
					<th>型式</th>
					<th width="16%"></th>
				</tr>
				<tr class="hidden-sample-tr">
					<s:hidden class="id" name="%{'talentedPeople.rdResults['+#stat.index+'].id'}" value="0"/>
					<s:hidden class="isValid" name="%{'talentedPeople.rdResults['+#stat.index+'].isValid'}"/>
					<s:hidden class="createTime" name="%{'talentedPeople.rdResults['+#stat.index+'].createTime'}"/>
					<s:hidden class="createUser" name="%{'talentedPeople.rdResults['+#stat.index+'].createUser'}"/>
					<s:hidden class="updateTime" name="%{'talentedPeople.rdResults['+#stat.index+'].updateTime'}"/>
					<s:hidden class="updateUser" name="%{'talentedPeople.rdResults['+#stat.index+'].updateUser'}"/>
					<s:hidden class="ver" name="%{'talentedPeople.rdResults['+#stat.index+'].ver'}"/>
					<s:hidden class="name" name="%{'talentedPeople.rdResults['+#stat.index+'].name'}"/>
					<s:hidden class="type" name="%{'talentedPeople.rdResults['+#stat.index+'].type'}"/>
					<s:hidden class="inventer" name="%{'talentedPeople.rdResults['+#stat.index+'].inventer'}"/>
					<s:hidden class="owner" name="%{'talentedPeople.rdResults['+#stat.index+'].owner'}"/>
					<s:hidden class="patentNo" name="%{'talentedPeople.rdResults['+#stat.index+'].patentNo'}"/>
					<s:hidden class="country" name="%{'talentedPeople.rdResults['+#stat.index+'].optionCountry.id'}"/>
					<s:hidden class="patentPeriodStart" name="%{'talentedPeople.rdResults['+#stat.index+'].patentPeriodStart'}"/>
					<s:hidden class="patentPeriodEnd" name="%{'talentedPeople.rdResults['+#stat.index+'].patentPeriodEnd'}"/>
					<s:hidden class="patentAbstract" name="%{'talentedPeople.rdResults['+#stat.index+'].patentAbstract'}"/>
					<s:hidden class="usage" name="%{'talentedPeople.rdResults['+#stat.index+'].usage'}"/>

					<td class="td-name">
						<s:property value="name"/>
					</td>
					<td class="td-type">
						<s:property value="type"/>
					</td>
					<td>
						<input type="button" class="btn-func btn-edit" value="編輯" />
						<input type="button" class="btn-func btn-del" value="刪除" />						
					</td>
				</tr>				
			</thead>
			<tbody>
				<s:iterator value="talentedPeople.rdResults" status="stat">
				<tr>
					<s:hidden class="id" name="%{'talentedPeople.rdResults['+#stat.index+'].id'}"/>
					<s:hidden class="isValid" name="%{'talentedPeople.rdResults['+#stat.index+'].isValid'}"/>
					<s:hidden class="createTime" name="%{'talentedPeople.rdResults['+#stat.index+'].createTime'}"/>
					<s:hidden class="createUser" name="%{'talentedPeople.rdResults['+#stat.index+'].createUser'}"/>
					<s:hidden class="updateTime" name="%{'talentedPeople.rdResults['+#stat.index+'].updateTime'}"/>
					<s:hidden class="updateUser" name="%{'talentedPeople.rdResults['+#stat.index+'].updateUser'}"/>
					<s:hidden class="ver" name="%{'talentedPeople.rdResults['+#stat.index+'].ver'}"/>
					<s:hidden class="name" name="%{'talentedPeople.rdResults['+#stat.index+'].name'}"/>
					<s:hidden class="type" name="%{'talentedPeople.rdResults['+#stat.index+'].type'}"/>
					<s:hidden class="inventer" name="%{'talentedPeople.rdResults['+#stat.index+'].inventer'}"/>
					<s:hidden class="owner" name="%{'talentedPeople.rdResults['+#stat.index+'].owner'}"/>
					<s:hidden class="patentNo" name="%{'talentedPeople.rdResults['+#stat.index+'].patentNo'}"/>
					<s:hidden class="country" name="%{'talentedPeople.rdResults['+#stat.index+'].optionCountry.id'}"/>
					<s:hidden class="patentPeriodStart" name="%{'talentedPeople.rdResults['+#stat.index+'].patentPeriodStart'}"/>
					<s:hidden class="patentPeriodEnd" name="%{'talentedPeople.rdResults['+#stat.index+'].patentPeriodEnd'}"/>
					<s:hidden class="patentAbstract" name="%{'talentedPeople.rdResults['+#stat.index+'].patentAbstract'}"/>
					<s:hidden class="usage" name="%{'talentedPeople.rdResults['+#stat.index+'].usage'}"/>
				
					<td class="td-name">
						<s:property value="name"/>
					</td>
					<td class="td-type">
						<s:property value="type"/>
					</td>
					<td>
						<input type="button" class="btn-func btn-edit" value="編輯" />
						<input type="button" class="btn-func btn-del" value="刪除" />						
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>

		<div class="clear"></div>

<!----------------------------------------------------------------------------->
		
		<b>成果移轉及授權案例</b>
		<input type="button" id="add-transferCases" value="+"/>
		<table id="table-transferCase">
			<thead>
				<tr>
					<th width="30%">應用領域</th>
					<th width="30%">對象廠商或機構</th>
					<th width="">時間(授權期間或讓受/技轉時間)</th>
					<th width="5%"></th>
				</tr>
				<tr class="hidden-sample-tr">
					<s:hidden class="id" value="0"/>
					
					<td>
						<s:textfield class="applyField" maxlength="500"/>
					</td>
					<td>
						<s:textfield class="targetOrg" maxlength="500"/>
					</td>
					<td>
						<s:select class="yearStart" list="yearList" listKey="code" listValue="name"/>
						<s:select class="monthStart" list="monthList" listKey="code" listValue="name"/>
						<div>~</div>
						<s:select class="yearEnd" list="yearList" listKey="code" listValue="name" headerKey="" headerValue=""/>
						<s:select class="monthEnd" list="monthList" listKey="code" listValue="name" headerKey="" headerValue=""/>
					</td>
					<td>
						<input type="button" class="btn-func btn-del" value="刪除" />
					</td>					
				</tr>
			</thead>
			<tbody>
				<s:iterator value="talentedPeople.transferCases" status="stat">
				<tr>
					<s:hidden class="id" name="%{'talentedPeople.transferCases['+#stat.index+'].id'}"/>
					<s:hidden class="isValid" name="%{'talentedPeople.transferCases['+#stat.index+'].isValid'}"/>
					<s:hidden class="createTime" name="%{'talentedPeople.transferCases['+#stat.index+'].createTime'}"/>
					<s:hidden class="createUser" name="%{'talentedPeople.transferCases['+#stat.index+'].createUser'}"/>
					<s:hidden class="updateTime" name="%{'talentedPeople.transferCases['+#stat.index+'].updateTime'}"/>
					<s:hidden class="updateUser" name="%{'talentedPeople.transferCases['+#stat.index+'].updateUser'}"/>
					<s:hidden class="ver" name="%{'talentedPeople.transferCases['+#stat.index+'].ver'}"/>
				
					<td>
						<s:textfield class="applyField" name="%{'talentedPeople.transferCases['+#stat.index+'].applyField'}" maxlength="500"/>
					</td>
					<td>
						<s:textfield class="targetOrg" name="%{'talentedPeople.transferCases['+#stat.index+'].targetOrg'}" maxlength="500"/>
					</td>
					<td>
						<s:select class="yearStart" name="%{'talentedPeople.transferCases['+#stat.index+'].yearStart'}" list="yearList" listKey="code" listValue="name" />
						<s:select class="monthStart" name="%{'talentedPeople.transferCases['+#stat.index+'].monthStart'}" list="monthList" listKey="code" listValue="name" />
						<div>~</div>
						<s:select class="yearEnd" name="%{'talentedPeople.transferCases['+#stat.index+'].yearEnd'}" list="yearList" listKey="code" listValue="name" headerKey="" headerValue=""/>
						<s:select class="monthEnd" name="%{'talentedPeople.transferCases['+#stat.index+'].monthEnd'}" list="monthList" listKey="code" listValue="name" headerKey="" headerValue=""/>
					</td>
					<td>
						<input type="button" class="btn-func btn-del" value="刪除" />
					</td>
				</tr>
				</s:iterator>
			</tbody>		
		</table>

<!----------------------------------------------------------------------------->
		
		<b>主要產學合作計畫案例</b>
		<input type="button" id="add-mainProject" value="+"/>
		<table id="table-mainProject">
			<thead>
				<tr>
					<th width="30%">合作計畫或合約名稱</th>
					<th width="30%">合作廠商名稱</th>
					<th width="">合作有效期間</th>
					<th width="5%"></th>
				</tr>
				<tr class="hidden-sample-tr">
					<s:hidden class="id" value="0"/>
				
					<td>
						<s:textfield class="name" maxlength="500"/>
					</td>
					<td>
						<s:textfield class="coopComName" maxlength="500"/>
					</td>
					<td>
						<s:select class="yearStart" list="yearList" listKey="code" listValue="name" />
						<s:select class="monthStart" list="monthList" listKey="code" listValue="name" />
						<div>~</div>
						<s:select class="yearEnd" list="yearList" listKey="code" listValue="name" />
						<s:select class="monthEnd" list="monthList" listKey="code" listValue="name" />
					</td>
					<td>
						<input type="button" class="btn-func btn-del" value="刪除" />
					</td>				
				</tr>
			</thead>
			<tbody>
				<s:iterator value="talentedPeople.mainProjects" status="stat">
				<tr>
					<s:hidden class="id" name="%{'talentedPeople.mainProjects['+#stat.index+'].id'}"/>
					<s:hidden class="isValid" name="%{'talentedPeople.mainProjects['+#stat.index+'].isValid'}"/>
					<s:hidden class="createTime" name="%{'talentedPeople.mainProjects['+#stat.index+'].createTime'}"/>
					<s:hidden class="createUser" name="%{'talentedPeople.mainProjects['+#stat.index+'].createUser'}"/>
					<s:hidden class="updateTime" name="%{'talentedPeople.mainProjects['+#stat.index+'].updateTime'}"/>
					<s:hidden class="updateUser" name="%{'talentedPeople.mainProjects['+#stat.index+'].updateUser'}"/>
					<s:hidden class="ver" name="%{'talentedPeople.mainProjects['+#stat.index+'].ver'}"/>
					
					<td>
						<s:textfield class="name" name="%{'talentedPeople.mainProjects['+#stat.index+'].name'}" maxlength="500"/>
					</td>
					<td>
						<s:textfield class="coopComName" name="%{'talentedPeople.mainProjects['+#stat.index+'].coopComName'}" maxlength="500"/>
					</td>
					<td>
						<s:select class="yearStart" name="%{'talentedPeople.mainProjects['+#stat.index+'].yearStart'}" list="yearList" listKey="code" listValue="name" />
						<s:select class="monthStart" name="%{'talentedPeople.mainProjects['+#stat.index+'].monthStart'}" list="monthList" listKey="code" listValue="name" />
						<div>~</div>
						<s:select class="yearEnd" name="%{'talentedPeople.mainProjects['+#stat.index+'].yearEnd'}" list="yearList" listKey="code" listValue="name" />
						<s:select class="monthEnd" name="%{'talentedPeople.mainProjects['+#stat.index+'].monthEnd'}" list="monthList" listKey="code" listValue="name" />
					</td>
					<td>
						<input type="button" class="btn-func btn-del" value="刪除" />
					</td>									
				</tr>
				</s:iterator>
			</tbody>			
		</table>
		
<!----------------------------------------------------------------------------->	
		
		<ul>
			<li class="all">
				<b>產學獲獎事蹟</b>
				<s:textarea name="talentedPeople.rewardHistory" rows="5" />
			</li>
			<li class="all">
				<b>其他產業相關經驗 (如:任職過業界或法人, 或擔任過業界或法人顧問……等對產學合作有助益的經驗)</b>
				<s:textarea name="talentedPeople.otherExperience" rows="5" />
			</li>
		</ul>

<!----------------------------------------------------------------------------->
		
		<div class="clear"></div>
		<div class="bottom-btn-block">
			<s:submit cssClass="redBtn" value="儲存" />
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>		
	</s:form>
	
	<form id="rdResultForm">
		<div class="subForm">
			<s:hidden class="rdResultRowIndex"/>
			<h2 class="itemTitle">重要研發成果</h2>
			<ul>
				<li class="quarter">
					<b>1.研發成果名稱</b>
					<input type="text" class="name" maxlength="500"/>
				</li>
				<li class="quarter">
					<b>2.型式</b>
					<s:select class="type" list="rdResultTypeList" listKey="code" listValue="name"/>
				</li>
				<li class="quarter">
					<b>3.發明人(創作人)</b>
					<input type="text" class="inventer" maxlength="100"/>
				</li>
				<li class="quarter">
					<b>4.所有權人</b>
					<input type="text" class="owner" maxlength="100"/>
				</li>
				<li class="quarter">
					<b>5.專利號(申請中請填申請號)</b>
					<input type="text" class="patentNo" maxlength="100"/>
				</li>
				<li class="quarter">
					<b>6.國別</b>
					<s:select class="country" list="countryList" listKey="id" listValue="name"/>
				</li>
				<li class="quarter">
					<b>7.專利期間(起)</b>
					<input type="text" class="patentPeriodStart calendarBox" maxlength="10" placeholder="格式範例:2016/12/30"/>				
				</li>
				<li class="quarter">
					<b>7.專利期間(迄)</b>
					<input type="text" class="patentPeriodEnd calendarBox" maxlength="10" placeholder="格式範例:2016/12/30"/>				
				</li>
				<li class="all">
					<b>8.摘要</b>
					<s:textarea class="patentAbstract" rows="3"/>
				</li>
				<li class="all">
					<b>9.應用產業/範圍</b>
					<input type="text" class="usage" maxlength="1000"/>
				</li>
			</ul>
			
			<input type="button" class="redBtn btn-addConfirm" value="確定新增"/>
			<input type="button" class="redBtn btn-editConfirm" value="確定變更"/>
			<input type="button" class="grayBtn btn-cancel" value="取消"/>
		</div>
	</form>

	<form action="index" method="post" id="form-backToIndex">
		<s:hidden name="searchCondition.name"/>
		<s:hidden name="searchCondition.gender"/>
		<s:hidden name="searchCondition.expYearS"/>
		<s:hidden name="searchCondition.expYearE"/>
		<s:hidden name="searchCondition.workOrg"/>
		<s:hidden name="searchCondition.job"/>
		<s:hidden name="searchCondition.specialty"/>
		<s:iterator value="searchCondition.grbDomainIdList" status="stat">
			<input type="hidden" name="searchCondition.grbDomainIdList" value="<s:property/>"/>
		</s:iterator>
		<s:hidden name="searchCondition.pageIndex"/>
		<s:hidden name="searchCondition.pageSize"/>
	</form>
</div>	
</body>
</html>