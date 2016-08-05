<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("input[type=file]#btn_file").change(function() {
			$("input[type=text]#file_display").val($(this).val());
		});
	});
</script>
<style>
.batch-import {
	padding: 15px;
	margin-top: 23px;
	margin-bottom: 23px;
	border: 1px solid transparent;
/* 	border-color: #000000; */
}
</style>
</head>
<body>
	<div class="subForm">
		<h2 class="itemTitle Down">單筆新增</h2>
		<s:form action="createSubmit" method="post" validate="true" >		
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
		<s:form action="batchImport" method="post" validate="true" enctype="multipart/form-data">
			<h2 class="itemTitle Down">批次匯入</h2>
			<ul>
				<li class="all">
					<b>注意1: 只接受代碼長度為4的資料，其餘資料都將被忽略</b>
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
	
	<input type="button" class="grayBtn" value="回上一頁" onclick="window.location.href='<s:url value="index"/>'" />
</body>
</html>