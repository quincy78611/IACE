<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="編輯管理  > 編輯"/>

<script>
$(document).ready(function() {
	addSearchConditionHiddenToForm();
	$("#btn-back").click(function(){				
		$("#form-backToIndex").submit();
	});
	
	addMore();
	fileBrowseSetting();
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
	function addMore() {	
		$("input[type=button].btn-addMoreAttach").click(function(){
			var newTr = $(this).parents("table").find("tr.hidden-sample-tr").clone();
			newTr.removeClass("hidden-sample-tr");
			$(this).parents("table").find("tbody").append(newTr);
			fileBrowseSetting();
			resetNameAttrForAttachTable();
		});
	}
</script>
<script>
	function fileBrowseSetting() {
		$(".btn-fake-browse").click(function(){
			$(this).parents("tr").find("input[type=file]").trigger("click");
		});
		
		$("input[type=file]").change(function() {
			var fileName = $(this).get(0).files[0].name;
			$(this).parents("tr").find(".label-fileName").html(fileName);
			readURL(this);
		});
		
		$("input[type=button].cancelSelectFile").click(function(){
			var rowCount = $(this).parents("tbody").find("tr").length;
			$(this).parents("tr").remove();
			resetNameAttrForAttachTable();
		});
	}
</script>
<script>
function resetNameAttrForAttachTable() {
	$("#table-attach > tbody > tr").each(function( index ){
		var tr = $("#table-attach > tbody > tr").eq(index);

		tr.find(".id").attr("name", "rdFocus.attachs["+index+"].id");;
		tr.find(".upload").attr("name", "rdFocus.attachs["+index+"].upload");
		tr.find(".fileType").attr("name", "rdFocus.attachs["+index+"].fileType");
		tr.find(".fileTitle").attr("name", "rdFocus.attachs["+index+"].fileTitle");
	});
}
</script>
<script>
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.readAsDataURL(input.files[0]);
	    }
	}
</script>
<style>
/* table.table-files tr, th, td { border: solid 1px; } */
/* table.table-files td li { margin-bottom: 1px; } */
table.table-files input[type=file] { display:none; }
table.table-files tr.hidden-sample-tr { display:none; }
table.table-files .label-fileName { min-height:35px; padding: 0px 5px; }
</style>
</head>
<body>
	<s:form action="updateSubmit" method="post" validate="true" id="form-update" enctype="multipart/form-data">
		<s:hidden name="rdFocus.id"/>
		<s:hidden name="rdFocus.isValid"/>
		<s:hidden name="rdFocus.createTime"/>
		<s:hidden name="rdFocus.createUser"/>
		<s:hidden name="rdFocus.updateTime"/>
		<s:hidden name="rdFocus.updateUser"/>
		<s:hidden name="rdFocus.ver"/>
		<ul>						
			<li class="all">
				<b>標題</b>
				<s:textfield name="rdFocus.title" maxlength="200"/>
			</li>
			<li class="all">
				<b>來源</b>
				<s:textfield name="rdFocus.source" maxlength="200"/>
			</li>
			<li class="half">
				<b>發布日期</b>
				<s:textfield name="rdFocus.postDate" cssClass="calendarBox" maxlength="10">
					<s:param name="value">
						<s:date name="rdFocus.postDate" format="yyyy/MM/dd" /> 
					</s:param>
				</s:textfield>
			</li>
			<li class="half">
				<b>分類</b>
				<s:select name="rdFocus.category" list="categoryList" listKey="code" listValue="name" />
			</li>
			<li class="all">
				<b>內容</b>
				<s:textarea name="rdFocus.content" />
				<div>
					<!-- 網頁編輯器 -->
					<s:include value="/WEB-INF/jsp/ckEditor.jsp" />
					<script type="text/javascript">
					window.onload = function() {
						CKEDITOR.replace('rdFocus.content'); // 此處參數 'about.content' 為需要套用ckeditor 的 textarea 的 name
					};
					</script>
				</div>
			</li>
		</ul>
		<!-- 附檔 -->
		<table id="table-attach" class="table-files">
			<thead>
				<tr>
					<td colspan="3">
						<input type="button" value="+ 增加附檔" class="btn-addMoreAttach redBtn"/>
					</td>
				</tr>
				<tr class="hidden-sample-tr">
					<td>
						<s:file type="file" class="upload" />
						<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
						<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />

						<label class="label-fileName">
							<span style="color:#AA3333;">(尚未選取檔案)</span>
						</label>
					</td>
					<td>
						<s:select class="fileType" list="fileTypeList" listKey="code" listValue="name"/>
					</td>
					<td>						
						<s:textfield class="fileTitle" placeholder="檔案標題"/>
					</td>
				</tr>				
			</thead>
			<tbody>
				<s:iterator value="rdFocus.attachs" status="stat">
					<tr>
						<s:hidden class="id" name="%{'rdFocus.attachs['+#stat.index+'].id'}"/>
						
						<td>
							<s:file type="file" name="%{'rdFocus.attachs['+#stat.index+'].upload'}" class="upload" />
							<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
							<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							
							<label class="label-fileName">
								<s:url namespace="/file" action="downloadFile" escapeAmp="false" var="downloadAttachUrl">
									<s:param name="folderConfigKey" value="%{'rdFocusAttachFolder'}" />
									<s:param name="downloadFileSubPath" value="fileSubPath" />
								</s:url>
								<a href="<s:property value="downloadAttachUrl" />" >
									<s:property value="uploadFileName"/>
								</a>
							</label>
						</td>
						<td>
							<s:select class="fileType" name="%{'rdFocus.attachs['+#stat.index+'].fileType'}" list="fileTypeList" listKey="code" listValue="name"/>
						</td>
						<td>						
							<s:textfield class="fileTitle" name="%{'rdFocus.attachs['+#stat.index+'].fileTitle'}" placeholder="檔案標題"/>
						</td>						
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="clear"></div>
			
		<h2>SEO區(網站優化加註，幫助訊息更易搜尋到)</h2>
		<ul class="common-meta-fields">
			<li class="all">
				<b>Meta Title</b>
				<s:textfield name="rdFocus.metaTitle" maxlength="200"/>
			</li>
			<li class="all">
				<b>Meta Description</b>
				<s:textfield name="rdFocus.metaDes" />
			</li>
			<li class="all">
				<b>Meta KeyWord</b>
				<s:textfield name="rdFocus.metaKeyword" />
			</li>
		</ul>
		<div class="clear"></div>
		
		<h2>狀態設定區</h2>
		<ul class="common-linkiac-fields">
			<li class="quarter">
				<b>瀏覽次數</b>
				<s:textfield name="rdFocus.clickNum" type="number" min="0"/>
			</li>
			<li class="quarter">
				<b>排序(數字愈大排愈前面)</b>
				<s:textfield name="rdFocus.sort" type="number"/>
			</li>
			<li class="quarter">
				<b>顯示狀態</b>
				<s:radio name="rdFocus.displayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList"/>
			</li>
			<li class="quarter">
				<b>首頁顯示</b>
				<s:radio name="rdFocus.homeDisplayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList"/>
			</li>			
		</ul>		
		<div class="clear"></div>
		
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="儲存" />
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>		
	</s:form>
	
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>