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
});
</script>
<script>
	function addSearchConditionHiddenToForm() {
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#form-update").append($(this).clone());
		});
	}
</script>
</head>
<body>
	<s:form action="updateSubmit" method="post" validate="true" id="form-update">
		<s:hidden name="epaper.id"/>
		<s:hidden name="epaper.isValid"/>
		<s:hidden name="epaper.createTime"/>
		<s:hidden name="epaper.createUser"/>
		<s:hidden name="epaper.updateTime"/>
		<s:hidden name="epaper.updateUser"/>
		<s:hidden name="epaper.ver"/>
		<s:hidden name="epaper.publishState"/>
		<s:hidden name="epaper.fileName"/>
		<ul>
			<li class="all">
				<b>標題</b>
				<s:textfield name="epaper.title" maxlength="200"/>
			</li>
			<div class="clear"></div>
			<li class="half">
				<b>期數</b>
				<s:textfield name="epaper.no" maxlength="5"/>
			</li>
			<li class="half">
				<b>發佈日</b>
				<s:textfield name="epaper.postDate" cssClass="calendarBox" maxlength="10">
					<s:param name="value">
						<s:date name="epaper.postDate" format="yyyy/MM/dd" /> 
					</s:param>
				</s:textfield>
			</li>
			<div class="clear"></div>
			<li class="all">
				<b>連結</b>
				<div class="border-text">
					<s:url value="%{epaper.url}" var="urlTag" escapeAmp="false" forceAddSchemeHostAndPort="true"/>
					<a href="<s:property value="%{#urlTag}"/>" target="_blank">
						<s:property value="%{#urlTag}"/>
					</a>
				</div>
			</li>
		</ul>
		<div class="clear"></div>
		
		<div class="bottom-btn-block">
			<s:submit cssClass="redBtn" value="儲存" />
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>
	</s:form>
	
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>