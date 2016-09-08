<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[type=file]#btn_research_plan_file").change(function() {
				$("input[type=text]#research_plan_file_display").val($(this).val());
			});
			$("input[type=file]#btn_patten_file").change(function() {
				$("input[type=text]#patten_file_display").val($(this).val());
			});
		});
	</script>
</head>
<body>
	<div class="">
		<div class="row">
			<s:form action="batchImportResearchPlan" method="post" validate="true" enctype="multipart/form-data">
				<h2 class="itemTitle Down">研發成果</h2>
				<ul>
					<li class="half">
						<input type="text" id="research_plan_file_display" class="form-control" readonly="readonly"/>
					</li>
					<li class="quarter">
						<span class="btn btn-default btn-file"> 選擇批次匯入檔案 
							<input type="file" id="btn_research_plan_file" name="uploadFile" class="btn-file">
						</span>
					</li>
					<li class="eighth">
						<input type="submit" value="匯入" class="btn btn-info redBtn" />
					</li>
					<li class="eighth">						
						<input type="button" class="btn btn-default grayBtn" value="下載範例" 
							onclick="window.location.href='<s:url value="downloadResearchPlanBatchSample"/>'"/>	
					</li>
				</ul>

			</s:form>		
		</div>
		<br>
		<div class="row">
			<s:form action="batchImportPatent" method="post" validate="true" enctype="multipart/form-data">
				<h2 class="itemTitle Down">專利資料</h2>
				<ul>
					<li class="half">
						<input type="text" id="patten_file_display" class="form-control" readonly="readonly"/>
					</li>
					<li class="quarter">
						<span class="btn btn-default btn-file"> 選擇批次匯入檔案
							<input type="file" id="btn_patten_file" name="uploadFile" class="btn-file">
						</span>
					</li>
					<li class="eighth">
						<input type="submit" value="匯入" class="btn btn-info redBtn" />
					</li>
					<li class="eighth">
						<input type="button" class="btn btn-default grayBtn" value="下載範例"
							onclick="window.location.href='<s:url value="downloadPatentBatchSample"/>'"/>
					</li>									
				</ul>
			</s:form>
		</div>		
	</div>
</body>
</html>