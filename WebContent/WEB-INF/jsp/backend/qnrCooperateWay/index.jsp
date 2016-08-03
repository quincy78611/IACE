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
	<input type="button" class="btn btn-default grayBtn" value="匯出問卷連結報表" 
		onclick="window.location.href='<s:url value="downloadQnrLinksExcel"/>'"/>	

	<div class="clear"></div>

	<table width="100%">
		<tr>
			<th nowrap>學校</th>
		</tr>
		<s:iterator value="qnrCooperateWayLinks" status="stat">
			<tr>
				<td>
					<span style="font-weight:bold">
						<s:property value="%{school.name}"/>
					</span>	
					<br/>					
					前三部分
					<s:url value="fillInQnrPDPL" var="fillInQnrPart0To3UrlTag" escapeAmp="false">
						<s:param name="encryptSchoolId" value="encryptSchoolId" />
					</s:url>					
					<a href="<s:property value="fillInQnrPart0To3UrlTag" />" class="qnr-link"></a>
					<br/>
					第四部分
					<s:url value="fillInQnrPart4" var="fillInQnrPart4UrlTag" escapeAmp="false">
						<s:param name="encryptSchoolId" value="encryptSchoolId" />
					</s:url>
					<a href="<s:property value="#fillInQnrPart4UrlTag" />" class="qnr-link"></a>
				</td>
				
			<tr>
		</s:iterator>
	</table>
</body>
</html>