<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">刪除</h2>

	<s:form action="deleteSubmit" method="post" validate="true" >
		<s:hidden name="patent.id" />
		<s:hidden name="patent.isValid" />
		<s:hidden name="patent.createTime" />
		<s:hidden name="patent.createUser" />
		<s:hidden name="patent.updateTime" />
		<s:hidden name="patent.updateUser" />
		<s:hidden name="patent.ver" />
	
		<div class="container-fluid" >

	<ul>
		<li class="all">
			<b>專利名稱</b>
			<s:textfield name="patent.name" cssClass="form-control" readonly="true" />
		</li>
		<li class="all">
			<b>專利權人</b>
			<s:textfield name="patent.assignee" cssClass="form-control"  readonly="true" />
		</li>	
		<li class="all">
			<b>發明人</b>
			<s:textfield name="patent.invertor" cssClass="form-control" readonly="true" />
		</li>
		<li class="quarter">
			<b>國際分類號</b>
			<s:textfield name="patent.ipc" cssClass="form-control" readonly="true" />
		</li>			
		<li class="quarter">
			<b>申請國別</b>
			<s:textfield name="patent.country" cssClass="form-control" readonly="true" />
		</li>
		<li class="quarter">
			<b>申請號</b>
			<s:textfield name="patent.appliactionNo" cssClass="form-control" readonly="true" />
		</li>
		<li class="quarter">
			<b>申請日</b>
			<s:textfield name="patent.applicationDate" cssClass="form-control" readonly="true" >			
				<s:param name="value">
					<s:date name="patent.applicationDate" format="yyyy/MM/dd" /> 
				</s:param>
			</s:textfield>
		</li>
		<li class="quarter">
			<b>公開號</b>
			<s:textfield name="patent.openNo" cssClass="form-control" readonly="true" />
		</li>						
		<li class="quarter">
			<b>公開日</b>
			<s:textfield name="patent.openDate" cssClass="form-control" readonly="true" >
				<s:param name="value">
					<s:date name="patent.openDate" format="yyyy/MM/dd" /> 
				</s:param>				
			</s:textfield>
		</li>						
		<li class="quarter">
			<b>公告號</b>
			<s:textfield name="patent.publicationNo" cssClass="form-control" readonly="true" />
		</li>						
		<li class="quarter">
			<b>公告日</b>
			<s:textfield name="patent.publicationDate" cssClass="form-control" readonly="true" >
				<s:param name="value">
					<s:date name="patent.publicationDate" format="yyyy/MM/dd" /> 
				</s:param>			
			</s:textfield>
		</li>
		<li class="quarter">
			<b>專利類別</b>
			<s:textfield name="patent.category" cssClass="form-control" readonly="true" />
		</li>
		<li class="quarter">
			<b>專利狀態</b>
			<s:textfield name="patent.patentStatus" cssClass="form-control" readonly="true" />
		</li>			
		<li class="half">
			<b>專利家族</b>
			<s:textfield name="patent.familyNo" cssClass="form-control" readonly="true" />
		</li>
		<li class="all">
			<b>專利技術摘要</b>
			<s:textfield name="patent.techAbstract" cssClass="form-control" readonly="true" />
		</li>
		<li class="all">
			<b>重要圖式</b>
			<div>
				<img id="patent_img" src="data:image;base64,<s:property value="patent.base64PatentPicture"/>" style="max-width:800px; max-height:800px;">
			</div>
		</li>
		<li class="all"></li>
		<li class="half">
			<b>專利技術領域</b>
			<s:select name="patent.techField.name" list="techFieldList" listKey="name" listValue="name" disabled="true"/>
		</li>
		<li class="half">
			<b>應用範圍/產業</b>
			<s:textfield name="patent.usage" cssClass="form-control" readonly="true" />
		</li>									
		<li class="all">
			<b>技術發展階段</b>
			<s:select name="patent.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" disabled="true"/>
		</li>									
		<li class="all">
			<b>技術發展階段說明</b>
			<s:textfield name="patent.trlDesc" cssClass="form-control" readonly="true" />
		</li>									
	</ul>
	<div class="clear"></div>
	<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
		<s:submit cssClass="btn btn-info redBtn" value="確定" />	
		<a class="grayBtn" href="<s:url value="/iace/patent/init"/>">回上一頁</a>
	</div>	
		
	</s:form>
</body>
</html>