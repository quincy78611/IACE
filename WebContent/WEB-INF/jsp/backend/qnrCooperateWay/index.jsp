<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	$(document).ready(function() {
		$("a.qnr-link").each(function(index) {
			$(this).html($(this).prop("href"));
		});
	});

</script>
</head>
<body>
	
	<ul>
		<s:iterator value="qnrCooperateWayLinks" status="stat">
			<li class="all">
				<span style="font-weight:bold">
					<s:property value="%{school.name}"/>
				</span>
			</li>
			<li class="all">
				<b>前三部分問卷連結</b><br>
				<s:url value="fillInQnrPDPL" var="fillInQnrPart0To3UrlTag" escapeAmp="false">
					<s:param name="encryptSchoolId" value="encryptSchoolId" />
				</s:url>					
				<a href="<s:property value="fillInQnrPart0To3UrlTag" />" class="qnr-link"></a>
			</li>	
			<li class="all">
				<b>第四部分問卷連結</b><br>
				<s:url value="fillInQnrPart4" var="fillInQnrPart4UrlTag" escapeAmp="false">
					<s:param name="encryptSchoolId" value="encryptSchoolId" />
				</s:url>
				<a href="<s:property value="#fillInQnrPart4UrlTag" />" class="qnr-link"></a>
			</li>
		</s:iterator>
	</ul>
	
	<div class="clear"></div><br><br>
	
	<ul>	
		<li class="all">
			問卷連結報表
			<input type="button" class="btn btn-default grayBtn" value="匯出" 
				onclick="window.location.href='<s:url value="downloadQnrLinksExcel"/>'"/>	
		</li>
		<li class="all">
			問卷前三部分結果報表
			<input type="button" class="btn btn-default grayBtn" value="匯出" 
				onclick="window.location.href='<s:url value="downloadQnrPart0To3Excel"/>'"/>	
		</li>
		<li class="all">
			問卷第四部分結果報表
			<input type="button" class="btn btn-default grayBtn" value="匯出" 
				onclick="window.location.href='<s:url value="downloadQnrPart4Excel"/>'"/>	
		</li>
	</ul>
	
	
</body>
</html>