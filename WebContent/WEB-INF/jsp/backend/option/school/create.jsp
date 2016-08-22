<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		addSearchConditionHiddenToForm();
		
		$("#btn-back").click(function(){				
			$("#form-backToIndex").submit();
		});
		
		$("input[type=file]#btn_file").change(function() {
			$("input[type=text]#file_display").val($(this).val());
		});
	});
</script>
<script>
	function addSearchConditionHiddenToForm() {
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#form-create").append($(this).clone());
			$("#form-batchImport").append($(this).clone());
		});
	}
</script>
<style>
.batch-import {
	padding: 15px;
	margin-top: 23px;
	margin-bottom: 23px;
	border: 1px solid transparent;
}
</style>
<meta name="funcPathText" content="新增"/>
</head>
<body>
	<div class="subForm">
		<h2 class="itemTitle Down">單筆新增</h2>
		<s:form action="createSubmit" method="post" validate="true" id="form-create">		
			<ul>
				<li class="half">
					<b>代碼</b>
					<s:textfield name="option.code"/>
				</li>			
				<li class="half">
					<b>名稱</b>
					<s:textfield name="option.name"/>
				</li>			
			</ul>
			
			<s:submit cssClass="btn btn-default redBtn" value="儲存" />	
			
		</s:form>
	</div>
		
	<div class="subForm batch-import">
		<s:form action="batchImport" method="post" validate="true" enctype="multipart/form-data" id="form-batchImport">
			<h2 class="itemTitle Down">批次匯入</h2>
			<ul>
				<li class="all">
					<b>注意1: 代碼欄皆為文字非數字</b>
				</li>
				<li class="all">
					<b>注意2: 將忽略已存在代碼</b>
				</li>
				<li class="half">
					<input type="text" id="file_display" class="form-control" readonly="readonly"/>
				</li>
				<li class="quarter">
					<span class="btn btn-default btn-file"> 選擇批次匯入檔案 
						<input type="file" id="btn_file" name="uploadFile" class="btn-file">
					</span>
				</li>
				<li class="eighth">
					<input type="submit" value="匯入" class="btn btn-info redBtn" />
				</li>
				<li class="eighth">						
					<input type="button" class="btn btn-default grayBtn" value="下載範例" 
						onclick="window.location.href='<s:url value="downloadBatchSample"/>'"/>	
				</li>
			</ul>
		</s:form>
	</div>
	
	<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>	
	
	<form action="index" method="post" id="form-backToIndex">
		<s:hidden name="searchCondition.searchText"/>
		<s:hidden name="searchCondition.pageIndex"/>
		<s:hidden name="searchCondition.pageSize"/>
	</form>	
</body>
</html>