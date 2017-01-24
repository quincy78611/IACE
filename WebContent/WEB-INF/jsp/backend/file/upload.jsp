<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="上傳檔案"/>
</head>
<body>
	<s:form namespace="/file" action="uploadFile" method="post" validate="true" enctype="multipart/form-data">
		<ul>
			<li class="all">
				<b>請選擇欲上傳的檔案</b>
				<s:file type="file" name="upload"/>
			</li>
<!-- 			<li class="all"> -->
<!-- 				<b>下載時顯示的檔名(p.s.中文可能會亂碼)</b> -->
<%-- 				<s:textfield name="downloadFileName" placeholder="請輸入檔名(包括副檔名)，若未輸入則由系統亂數設定"/> --%>
<!-- 			</li> -->
			<s:if test="uploadSuccess">
				<li class="all">
					<b>下載連結</b>
					<s:url value="/f2/file/downloadFile" var="urlTag" escapeAmp="false" forceAddSchemeHostAndPort="true">
						<s:param name="downloadFileSubPath" value="downloadFileSubPath" />
						<s:param name="downloadFileName" value="downloadFileName" />
					</s:url>
					<div>
						<a href="<s:property value="urlTag"/>"><s:property value="urlTag"/></a>
					</div>
				</li>
			</s:if>
		</ul>
		<div class="clear"></div>
		<s:submit cssClass="redBtn" value="上傳" />
	</s:form>
</body>