<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<div class="">
		<div class="row">
			<s:form action="batchImportResearchPlan" method="post" validate="true" enctype="multipart/form-data">	
				<div class="col-md-2">
					<h4>研究計畫</h4>
				</div>
				<div class="col-md-2">
					<input type="button" class="btn btn-default" value="下載範例格式"/>
				</div>				
				<div class="col-md-5">
					<input type="text" id="research_plan_file_display" class="form-control" readonly="readonly"/>
				</div>
				<div class="col-md-1">
					<span class="btn btn-default btn-file"> 瀏覽 
						<input type="file" id="btn_research_plan_file" name="uploadFile" class="btn-file">
					</span>
				</div>
				<div class="col-md-1">
					<input type="submit" value="開始匯入" class="btn btn-info" />
				</div>
			</s:form>		
		</div>
		<hr>
		<div class="row">
			<s:form action="batchImportPatent" method="post" validate="true" enctype="multipart/form-data">
				<div class="col-md-2">
					<h4>專利資料</h4>
				</div>
				<div class="col-md-2">
					<input type="button" class="btn btn-default" value="下載範例格式"/>
				</div>				
				<div class="col-md-5">
					<input type="text" id="patten_file_display" class="form-control" readonly="readonly"/>
				</div>
				<div class="col-md-1">
					<span class="btn btn-default btn-file"> 瀏覽 
						<input type="file" id="btn_patten_file" name="uploadFile" class="btn-file">
					</span>
				</div>
				<div class="col-md-1">
					<input type="submit" value="開始匯入" class="btn btn-info" />
				</div>
			</s:form>
		</div>		
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

		});

		$("input[type=file]#btn_research_plan_file").change(function() {
			$("input[type=text]#research_plan_file_display").val($(this).val());
		});
		$("input[type=file]#btn_patten_file").change(function() {
			$("input[type=text]#patten_file_display").val($(this).val());
		});
	</script>
</body>
</html>