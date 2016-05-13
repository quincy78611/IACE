<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h4>刪除</h4>

	<s:form action="deleteSubmit" method="post" validate="true" >
		<div class="form-horizontal" >
			<s:hidden name="patent.id" />
			<s:hidden name="patent.isValid" />
			<s:hidden name="patent.createTime" />
			<s:hidden name="patent.createUser" />
			<s:hidden name="patent.updateTime" />
			<s:hidden name="patent.updateUser" />
			<s:hidden name="patent.ver" />
			<s:textfield label="專利名稱" name="patent.name" readonly="true" cssClass="form-control" />				
			<s:textfield label="專利權人" name="patent.assignee" readonly="true" cssClass="form-control"  />
			<s:textfield label="發明人" name="patent.invertor" readonly="true" cssClass="form-control" />
			<s:textfield label="申請國別" name="patent.country" readonly="true" cssClass="form-control" />
			<s:textfield label="申請號" name="patent.appliactionNo" readonly="true" cssClass="form-control" />			
			<s:textfield label="申請日" name="patent.applicationDate" readonly="true" cssClass="form-control calendarBox" >			
				<s:param name="value">
					<s:date name="patent.applicationDate" format="yyyy/MM/dd" /> 
				</s:param>
			</s:textfield>
			<s:textfield label="公開號" name="patent.openNo" readonly="true" cssClass="form-control" />			
			<s:textfield label="公開日" name="patent.openDate" readonly="true" cssClass="form-control calendarBox" >
				<s:param name="value">
					<s:date name="patent.openDate" format="yyyy/MM/dd" /> 
				</s:param>				
			</s:textfield>	
			<s:textfield label="公告號" name="patent.publicationNo" readonly="true" cssClass="form-control" />			
			<s:textfield label="公告日" name="patent.publicationDate" readonly="true" cssClass="form-control calendarBox" >
				<s:param name="value">
					<s:date name="patent.publicationDate" format="yyyy/MM/dd" /> 
				</s:param>			
			</s:textfield>
			<s:textfield label="專利類別" name="patent.category" readonly="true" cssClass="form-control" />
			<s:textfield label="專利狀態" name="patent.patentStatus" readonly="true" cssClass="form-control" />
			<s:textfield label="專利家族" name="patent.familyNo" readonly="true" cssClass="form-control" />
			<s:textfield label="國際分類號" name="patent.ipc" readonly="true" cssClass="form-control" />
			<s:textfield label="專利技術摘要" name="patent.techAbstract" readonly="true" cssClass="form-control" />
			<s:textfield label="重要圖式-檔案路徑" name="patent.importantPicturePath" readonly="true" cssClass="form-control" />
			<s:textfield label="重要圖示代碼" name="patent.importantPictureCode" readonly="true" cssClass="form-control" />
			<s:textfield label="專利技術領域" name="patent.techField.name" readonly="true" cssClass="form-control" />
			<s:textfield label="應用範圍/產業" name="patent.usage" readonly="true" cssClass="form-control" />
			<s:textfield label="技術發展階段" name="patent.trl.code" readonly="true" cssClass="form-control" />
			<s:textfield label="技術發展階段說明" name="patent.trlDesc" readonly="true" cssClass="form-control" />
			<s:textfield label="建立時間" name="patent.createTime"  readonly="true" cssClass="form-control">
				<s:param name="value">
					<s:date name="patent.createTime" format="yyyy/MM/dd HH:mm:ss" /> 
				</s:param>			
			</s:textfield>		
			<s:textfield label="建立人員" name="patent.createUser" readonly="true" cssClass="form-control" />
			<s:textfield label="異動時間" name="patent.updateTime" readonly="true" cssClass="form-control">
				<s:param name="value">
					<s:date name="patent.updateTime" format="yyyy/MM/dd HH:mm:ss" /> 
				</s:param>		
			</s:textfield>
			<s:textfield label="異動人員" name="patent.updateUser" readonly="true" cssClass="form-control" />
		</div>
		
		<s:submit cssClass="btn btn-info" value="確定" />		
		<a class="btn btn-default" href="<s:url value="/iace/patent/init"/>">回上一頁</a>
	</s:form>
</body>
</html>