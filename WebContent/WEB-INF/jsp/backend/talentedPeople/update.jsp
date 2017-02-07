<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<s:url value="/scripts/talentedPeople/headshotFuncSetting.js"/>"></script>
<%-- <script type="text/javascript" src="<s:url value="/scripts/talentedPeople/tableFuncSetting.js"/>"></script> --%>
<script>
$(document).ready(function() {
	$("#btn-back").click(function(){
		$("#form-backToIndex").submit();
	});
	
	var originUrl = '<s:url value="/talentedPeople/updateSubmit"/>';
	$(".btn-create").click(function(){
		var url = $(this).siblings(".createUrl").val();
		$("#form-update").attr('action', url);
		$("#form-update").submit();
		$("#form-update").attr('action', originUrl); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
	});
	$(".btn-edit").click(function() {
		var url = $(this).siblings(".updateUrl").val();
		$("#form-update").attr('action', url);
		$("#form-update").submit();
		$("#form-update").attr('action', originUrl); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
	});
	$(".btn-del").click(function(){
		if (confirm("確定要刪除?")) {
			var url = $(this).siblings(".deleteUrl").val();
			$("#form-update").attr('action', url);
			$("#form-update").submit();
			$("#form-update").attr('action', originUrl); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
		}
	});
	
	var scrollTo = '<s:property value="scrollTo"/>';
	if (scrollTo != null && scrollTo != "") {
		$('html,body').animate({scrollTop:$(scrollTo).offset().top},300);
	}
});
</script>
<script>
	//addSearchConditionHiddenToForm
	$(document).ready(function() {
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#form-update").append($(this).clone());
		});
	});
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
	<s:form namespace="/talentedPeople" action="updateSubmit" method="post" validate="true" enctype="multipart/form-data" id="form-update">
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
							<label>
								<input type="checkbox" name="talentedPeople.isPublicTel" value="true" 
									<s:property value="%{talentedPeople.isPublicTel ? 'checked' : ''}"/> />公開
							</label>
							<s:textfield name="talentedPeople.tel" maxlength="200"/>
						</li>
						<li class="half">
							<b>e-mail</b>
							<label>
								<input type="checkbox" name="talentedPeople.isPublicEmail" value="true" 
									<s:property value="%{talentedPeople.isPublicEmail ? 'checked' : ''}"/> />公開
							</label>
							<s:textfield name="talentedPeople.email" maxlength="200"/>
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
				<b>領域(可複選)</b>
				
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
		<div class="clear"></div>
		
<!----------------------------------------------------------------------------->
		
		<div id="div-rdResult">
			<div>
				<b>重要研發成果(包含:專利,技術,IC佈局, 軟體…..)</b> 
				<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleRdResult", "create")}'>
					<s:url value="/talentedPeopleRdResult/create" var="createUrlTag" escapeAmp="false">
						<s:param name="talentedPeopleId" value="talentedPeople.id" />
						<s:param name="scrollTo" value="%{'#div-rdResult'}" />
					</s:url>
					<s:hidden value="%{#createUrlTag}" class="createUrl" disabled="true"/>
					<input type="button" class="btn-create" value="+" />	
				</s:if>
				<label>
					<input type="checkbox" name="talentedPeople.isPublicRdResult" value="true" 
						<s:property value="%{talentedPeople.isPublicRdResult ? 'checked' : ''}"/> />公開
				</label>
			</div>
			<table id="table-rdResult">
				<thead>
					<tr>
						<th width="3%">No.</th>
						<th>研發成果名稱</th>
						<th>型式</th>
						<th>資料更新日期</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="talentedPeople.rdResults" status="stat">
					<tr>
						<td><s:property value="%{#stat.index+1}"/></td>
						<td><s:property value="name"/></td>
						<td><s:property value="type"/></td>
						<td><s:property value="updateDate"/></td>
						<td>
							<!-- 編輯 -->
							<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleRdResult", "update")}'>
								<s:url value="/talentedPeopleRdResult/update" var="updateUrlTag" escapeAmp="false">
									<s:param name="talentedPeopleId" value="talentedPeople.id" />
									<s:param name="id" value="id" />
									<s:param name="scrollTo" value="%{'#div-rdResult'}"/>
								</s:url>
								<s:hidden value="%{#updateUrlTag}" class="updateUrl" disabled="true"/>
								<input type="button" class="btn-info btn-func btn-edit" value="編輯" />
							</s:if>
							
							<!-- 刪除 -->
							<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleRdResult", "deleteSubmit")}'>
								<s:url value="/talentedPeopleRdResult/deleteSubmit" var="deleteUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
									<s:param name="scrollTo" value="%{'#div-rdResult'}"/>
								</s:url>
								<s:hidden value="%{#deleteUrlTag}" class="deleteUrl" disabled="true"/>
								<input type="button" class="btn-func btn-del" value="刪除" />	
							</s:if>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>

<!----------------------------------------------------------------------------->
		<div id="div-transferCase">
			<div>
				<b>成果移轉及授權案例</b>
				<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleTransferCase", "create")}'>
					<s:url value="/talentedPeopleTransferCase/create" var="createUrlTag" escapeAmp="false">
						<s:param name="talentedPeopleId" value="talentedPeople.id" />
						<s:param name="scrollTo" value="%{'#div-transferCase'}" />
					</s:url>
					<s:hidden value="%{#createUrlTag}" class="createUrl" disabled="true"/>
					<input type="button" class="btn-create" value="+" />
				</s:if>
				<label>
					<input type="checkbox" name="talentedPeople.isPublicTransferCase" value="true" 
						<s:property value="%{talentedPeople.isPublicTransferCase ? 'checked' : ''}"/> />公開
				</label>
			</div>
			<table id="table-transferCase">
				<thead>
					<tr>
						<th width="3%">No.</th>
						<th>應用領域</th>
						<th>對象廠商或機構</th>
						<th>時間(授權期間或讓受/技轉時間)</th>
						<th>資料更新日期</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="talentedPeople.transferCases" status="stat">
					<tr>
						<td class="td-No">
							<s:property value="%{#stat.index+1}" />
						</td>
						<td class="td-applyField">
							<s:property value="applyField"/>
						</td>
						<td class="td-targetOrg">
							<s:property value="targetOrg"/>
						</td>
						<td class="td-time">
							<s:set var="start" value="%{yearStart+'年'+monthStart+'月'}"/>
							<s:set var="end" value="%{yearEnd+'年'+monthEnd+'月'}"/>
							<s:property value="%{#start+(#end != null ? ' ~ '+#end : '')}" />
						</td>
						<td class="td-updateDate">
							<s:property value="updateDate"/>
						</td>
						<td>
							<!-- 編輯 -->
							<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleTransferCase", "update")}'>
								<s:url value="/talentedPeopleTransferCase/update" var="updateUrlTag" escapeAmp="false">
									<s:param name="talentedPeopleId" value="talentedPeople.id" />
									<s:param name="id" value="id" />
									<s:param name="scrollTo" value="%{'#div-transferCase'}"/>
								</s:url>
								<s:hidden value="%{#updateUrlTag}" class="updateUrl" disabled="true"/>
								<input type="button" class="btn-info btn-func btn-edit" value="編輯" />
							</s:if>
							
							<!-- 刪除 -->
							<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleTransferCase", "deleteSubmit")}'>
								<s:url value="/talentedPeopleTransferCase/deleteSubmit" var="deleteUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
									<s:param name="scrollTo" value="%{'#div-transferCase'}"/>
								</s:url>
								<s:hidden value="%{#deleteUrlTag}" class="deleteUrl" disabled="true"/>
								<input type="button" class="btn-func btn-del" value="刪除" />	
							</s:if>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>

<!----------------------------------------------------------------------------->

		<div id="div-mainProject">
			<b>主要產學合作計畫案例</b>
			<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleMainProject", "create")}'>
				<s:url value="/talentedPeopleMainProject/create" var="createUrlTag" escapeAmp="false">
					<s:param name="talentedPeopleId" value="talentedPeople.id" />
					<s:param name="scrollTo" value="%{'#div-mainProject'}" />
				</s:url>
				<s:hidden value="%{#createUrlTag}" class="createUrl" disabled="true"/>
				<input type="button" class="btn-create" value="+" />
			</s:if>
			<label>
				<input type="checkbox" name="talentedPeople.isPublicMainProject" value="true" 
					<s:property value="%{talentedPeople.isPublicMainProject ? 'checked' : ''}"/> />公開
			</label>
			<table id="table-mainProject">
				<thead>
					<tr>
						<th width="3%">No.</th>
						<th>合作計畫或合約名稱</th>
						<th>合作廠商名稱</th>
						<th>合作有效期間</th>
						<th>資料更新日期</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="talentedPeople.mainProjects" status="stat">
					<tr>
						<td class="td-No">
							<s:property value="%{#stat.index+1}" />
						</td>
						<td class="td-name">
							<s:property value="name"/>
						</td>
						<td class="td-coopComName">
							<s:property value="coopComName"/>
						</td>
						<td class="td-name">
							<s:set var="start" value="%{yearStart+'年'+monthStart+'月'}"/>
							<s:set var="end" value="%{yearEnd+'年'+monthEnd+'月'}"/>
							<s:property value="%{#start+(#end != null ? ' ~ '+#end : '')}" />
						</td>
						<td class="td-updateDate">
							<s:property value="updateDate"/>
						</td>
						<td>
							<!-- 編輯 -->
							<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleMainProject", "update")}'>
								<s:url value="/talentedPeopleMainProject/update" var="updateUrlTag" escapeAmp="false">
									<s:param name="talentedPeopleId" value="talentedPeople.id" />
									<s:param name="id" value="id" />
									<s:param name="scrollTo" value="%{'#div-mainProject'}"/>
								</s:url>
								<s:hidden value="%{#updateUrlTag}" class="updateUrl" disabled="true"/>
								<input type="button" class="btn-info btn-func btn-edit" value="編輯" />
							</s:if>
							
							<!-- 刪除 -->
							<s:if test='%{#session.sysUser.hasAuth("/talentedPeopleMainProject", "deleteSubmit")}'>
								<s:url value="/talentedPeopleMainProject/deleteSubmit" var="deleteUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
									<s:param name="scrollTo" value="%{'#div-mainProject'}"/>
								</s:url>
								<s:hidden value="%{#deleteUrlTag}" class="deleteUrl" disabled="true"/>
								<input type="button" class="btn-func btn-del" value="刪除" />	
							</s:if>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		
<!----------------------------------------------------------------------------->	
		
		<ul>
			<li class="all">
				<b>產學獲獎事蹟</b>
				<label>
					<input type="checkbox" name="talentedPeople.isPublicRewardHistory" value="true" 
						<s:property value="%{talentedPeople.isPublicRewardHistory ? 'checked' : ''}"/> />公開
				</label>
				<s:textarea name="talentedPeople.rewardHistory" rows="5" />
			</li>
			<li class="all">
				<b>其他產業相關經驗 (如:任職過業界或法人, 或擔任過業界或法人顧問……等對產學合作有助益的經驗)</b>
				<label>
					<input type="checkbox" name="talentedPeople.isPublicOtherExperience" value="true" 
						<s:property value="%{talentedPeople.isPublicOtherExperience ? 'checked' : ''}"/> />公開
				</label>
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

	<s:include value="./form-backToIndex.jsp" />
	
</body>
</html>