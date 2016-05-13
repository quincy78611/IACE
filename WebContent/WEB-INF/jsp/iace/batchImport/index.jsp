<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
</head>
<body>	
	
	<s:form action="batchImportPatent" method="post" validate="true" enctype="multipart/form-data">
		<h4>專利資料</h4>
		<div class="container-fluid">
			<div class="col-md-3">
				
				<span class="btn btn-default btn-file">
					瀏覽
                    <input class="upload" name="uploadFile" type="file" >
                </span>
			</div>
			<div class="col-md-2">
				<input type="submit" value="開始匯入" class="btn btn-info"/>
			</div>
		</div>
		
	</s:form>
	
	
	
	
	<script type="text/javascript">
		$(document).ready(function () {
			
		});
	</script>
</body>
</html>