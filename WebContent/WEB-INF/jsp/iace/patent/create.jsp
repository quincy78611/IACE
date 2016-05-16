<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h4>新增</h4>

	<s:form action="createSubmit" method="post" validate="true" enctype="multipart/form-data">
		<div class="form-horizontal" >
			<s:textfield label="專利名稱" name="patent.name" cssClass="form-control" />				
			<s:textfield label="專利權人" name="patent.assignee" cssClass="form-control"  />
			<s:textfield label="發明人" name="patent.invertor" cssClass="form-control" />
			<s:textfield label="申請國別" name="patent.country" cssClass="form-control" />
			<s:textfield label="申請號" name="patent.appliactionNo" cssClass="form-control" />			
			<s:textfield label="申請日" name="patent.applicationDate" cssClass="form-control calendarBox" >			
				<s:param name="value">
					<s:date name="patent.applicationDate" format="yyyy/MM/dd" /> 
				</s:param>
			</s:textfield>
			<s:textfield label="公開號" name="patent.openNo" cssClass="form-control" />			
			<s:textfield label="公開日" name="patent.openDate" cssClass="form-control calendarBox" >
				<s:param name="value">
					<s:date name="patent.openDate" format="yyyy/MM/dd" /> 
				</s:param>				
			</s:textfield>	
			<s:textfield label="公告號" name="patent.publicationNo" cssClass="form-control" />			
			<s:textfield label="公告日" name="patent.publicationDate" cssClass="form-control calendarBox" >
				<s:param name="value">
					<s:date name="patent.publicationDate" format="yyyy/MM/dd" /> 
				</s:param>			
			</s:textfield>
			<s:textfield label="專利類別" name="patent.category" cssClass="form-control" />
			<s:textfield label="專利狀態" name="patent.patentStatus" cssClass="form-control" />
			<s:textfield label="專利家族" name="patent.familyNo" cssClass="form-control" />
			<s:textfield label="國際分類號" name="patent.ipc" cssClass="form-control" />
			<s:textfield label="專利技術摘要" name="patent.techAbstract" cssClass="form-control" />
			<s:label label="重要圖式" class="control-label" />
			<img id="patent_img" src="data:image;base64,<s:property value="patent.base64PatentPicture"/>" style="max-width:800px; max-height:800px;">
			<span class="btn btn-default btn-file">
				瀏覽
                <input class="upload" name="uploadPatentImg" type="file" onchange = "readURL(this);">
			</span>
			<s:textfield label="重要圖示代碼" name="patent.importantPictureCode" cssClass="form-control" />
			<s:select label="專利技術領域" name="patent.techField.name" list="techFieldList" listKey="name" listValue="name" />
			<s:textfield label="應用範圍/產業" name="patent.usage" cssClass="form-control" />
			<s:select label="技術發展階段" name="patent.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			<s:textfield label="技術發展階段說明" name="patent.trlDesc" cssClass="form-control" />
		</div>
		<s:submit cssClass="btn btn-info" value="儲存" />
		<input type="button" value="重設" class="btn btn-warning" onclick="this.form.reset();" />
		<a class="btn btn-default" href="<s:url value="/iace/patent/init"/>">回上一頁</a>
		<!-- <input type ="button" class="btn btn-default" onclick="history.back()" value="回上一頁"/> -->	
	</s:form>
	
	<script type="text/javascript">
	    function readURL(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	
	            reader.onload = function (e) {
	                $('#patent_img').attr('src', e.target.result);
	            };
	
	            reader.readAsDataURL(input.files[0]);
	        }
	    }
	</script>	
</body>
</html>