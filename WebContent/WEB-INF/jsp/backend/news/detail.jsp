<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<!-- 網頁編輯器 -->
<script type="text/javascript" src="<s:url value="/scripts/tinymce/tinymce.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/scripts/tinymce/jquery.tinymce.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/scripts/tinymce/defaultEditorSetting.js"/>"></script>
<script>
	readonlyTinymceEditor('textArea[name="news.content"]');
</script>

<script>
	$(document).ready(function() {
		$("#btn-back").click(function(){				
			$("#form-backToIndex").submit();
		});	
	});
</script>
<style>
textarea[disabled] { width:100%; resize:none; border:none; background-color:#ffffff; color:#000000;}
</style>
<meta name="funcPathText" content="編輯管理 > 檢視"/>
</head>
<body>
	<ul>						
		<li class="all">
			<b>標題</b>
			<div class="border-text">
				<s:property value="news.title"/>
			</div>
		</li>
		<li class="all">
			<b>來源</b>
			<div class="border-text">
				<s:property value="news.source"/>
			</div>
		</li>
		<li class="half">
			<b>發布日期</b>
			<div class="border-text">
				<s:date name="news.postDate" format="yyyy/MM/dd"/>
			</div>
		</li>
		<li class="half">
			<b>分類</b>
			<div class="border-text">
				<s:property value="news.category"/>
			</div>
		</li>
		<li class="all">
			<b>內容</b>
			<s:textarea name="news.content"/>	
		</li>		
	</ul>
	<div class="clear"></div>
	
	<!-- 附檔 -->
	<table id="table-attach" class="table-files">
		<thead>
			<tr><th colspan="3">附檔</th></tr>
		</thead>
		<tbody>
			<s:iterator value="news.attachs" status="stat">
				<tr>						
					<td>						
						<label class="label-fileName">
							<s:url namespace="/file" action="downloadFile" escapeAmp="false" var="downloadAttachUrl">
								<s:param name="folderConfigKey" value="%{'newsAttachFolder'}" />
								<s:param name="downloadFileSubPath" value="fileSubPath" />
							</s:url>
							<a href="<s:property value="downloadAttachUrl" />" >
								<s:property value="uploadFileName"/>
							</a>
						</label>
					</td>
					<td>
						<s:select class="fileType" name="%{'news.attachs['+#stat.index+'].fileType'}" list="fileTypeList" listKey="code" listValue="name" disabled="true"/>
					</td>
					<td>
						<s:property value="fileTitle"/>						
					</td>						
				</tr>
			</s:iterator>
		</tbody>
	</table>	
	<div class="clear"></div>
		
	<h2>SEO區(網站優化加註，幫助訊息更易搜尋到)</h2>
	<ul class="common-meta-fields">
		<li class="all">
			<b>Meta Title</b>
			<div class="border-text">
				<s:property value="news.metaTitle"/>
			</div>				
		</li>
		<li class="all">
			<b>Meta Description</b>
			<div class="border-text">
				<s:property value="news.metaDes"/>
			</div>				
		</li>
		<li class="all">
			<b>Meta KeyWord</b>
			<div class="border-text">
				<s:property value="news.metaKeyword"/>
			</div>				
		</li>
	</ul>
	<div class="clear"></div>
	
	<h2>狀態設定區</h2>
	<ul class="common-linkiac-fields">
		<li class="quarter">
			<b>瀏覽次數</b>
			<div class="border-text">
				<s:property value="news.clickNum"/>
			</div>				
		</li>
		<li class="quarter">
			<b>排序(數字愈大排愈前面)</b>
			<div class="border-text">
				<s:property value="news.sort"/>
			</div>				
		</li>
		<li class="quarter">
			<b>顯示狀態</b>
			<s:radio name="news.displayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" disabled="true"/>
		</li>
		<li class="quarter">
			<b>首頁顯示</b>
			<s:radio name="news.homeDisplayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" disabled="true"/>
		</li>			
	</ul>		
	<div class="clear"></div>	
	
	<div class="bottom-btn-block">
		<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
	</div>
				
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>