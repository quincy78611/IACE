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
	readonlyTinymceEditor('textArea[name="coopEx.content"]');
</script>

<script>
	$(document).ready(function(){
		addSearchConditionHiddenToForm();
		$("#btn-back").click(function(){				
			$("#form-backToIndex").submit();
		});
	});
</script>
<script>
	function addSearchConditionHiddenToForm() {
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#form-delete").append($(this).clone());
		});
	}
</script>
<style>
.table-files tr, th, td { border: solid 1px; }
.table-files td li { margin-bottom: 1px; }
</style>
<meta name="funcPathText" content="編輯管理 > 刪除"/>
</head>
<body>
	<s:form action="deleteSubmit" method="post" validate="true" enctype="multipart/form-data" id="form-delete">
		<s:hidden name="id" />
		<ul>
			<li class="half">
				<b>年度</b>
				<div class="border-text">
					<s:property value="coopEx.year"/>
				</div>
			</li>
			<li class="half">
				<b>類別</b>
				<div class="border-text">
					<s:property value="coopEx.type"/>
				</div>			
			</li>
			<li class="all">
				<b>標題</b>
				<div class="border-text">
					<s:property value="coopEx.title"/>
				</div>			
			</li>			
			<li class="all">
				<b>案名</b>
				<s:textfield name="coopEx.projName" />
			</li>			
			<li class="all">
				<b>研發團隊</b>
				<div class="border-text">
					<s:property value="coopEx.rdTeam"/>
				</div>			
			</li>
			<li class="all">
				<b>輔導團隊</b>
				<div class="border-text">
					<s:property value="coopEx.assisTeam"/>
				</div>
			</li>			
			<li class="all">
				<b>內容</b>
				<s:textarea name="coopEx.content" rows="10" disabled="true"/>
			</li>				
		</ul>
		<br>
		
		<!-- 照片 -->
		<table class="table-files">
			<thead>
				<tr>
					<td colspan="2">照片清單</td>			
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.imgs" status="stat">
				<tr>
					<td width="15%">
						<s:url namespace="/file" action="downloadThumbnail" escapeAmp="false" var="downloadThumbnailUrl">
							<s:param name="folderConfigKey" value="%{'coopExampleFolder'}" />
							<s:param name="downloadFileSubPath" value="filePath" />
							<s:param name="thumbnailWidth" value="240" />
							<s:param name="thumbnailHeight" value="240" />
						</s:url>
						<img src="<s:property value="%{#downloadThumbnailUrl}"/>" style="max-width:120px; max-height:120px;" />					
					</td>
					<td>
						<ul>
							<li>
								<s:url namespace="/file" action="downloadFile" escapeAmp="false" var="downloadImageUrl">
									<s:param name="folderConfigKey" value="%{'coopExampleFolder'}" />
									<s:param name="downloadFileSubPath" value="filePath" />
								</s:url>
								<a href="<s:property value="downloadImageUrl" />" >
									<s:property value="fileName"/>
								</a>
							</li>
							<li class="all">
								<s:textarea name="fileDesc" placeholder="無檔案說明" disabled="true"/>
							</li>
						</ul>
					</td>
				</tr>				
				</s:iterator>
			</tbody>
		</table>
		
		<!-- 影片 -->
		<table class="table-files">
			<thead>
				<tr>
					<td colspan="2">影片清單</td>			
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.videos" status="stat">
				<tr>
					<td width="15%">
						<s:url namespace="/file" action="downloadFile" escapeAmp="false" var="downloadVideoUrl">
							<s:param name="folderConfigKey" value="%{'coopExampleFolder'}" />
							<s:param name="downloadFileSubPath" value="filePath" />
						</s:url>
						<video 
							src="<s:property value="downloadVideoUrl" />" 
							controls="controls" preload="metadata"
							style="max-width:120px; max-height:120px;">
						</video>
					</td>
					<td>
						<ul>
							<li>
								<s:property value="fileName"/>
							</li>
							<li class="all">
								<s:textarea name="fileDesc" placeholder="無檔案說明" disabled="true"/>
							</li>
						</ul>
					</td>
				</tr>				
				</s:iterator>
			</tbody>
		</table>		
		
		<!-- 附檔 -->
		<table class="table-files">
			<thead>
				<tr>
					<td>附檔清單</td>			
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.attachFiles" status="stat">
				<tr>
					<td>
						<ul>
							<li>
								<s:url namespace="/file" action="downloadFile" escapeAmp="false" var="downloadAttachUrl">
									<s:param name="folderConfigKey" value="%{'coopExampleFolder'}" />
									<s:param name="downloadFileSubPath" value="filePath" />
								</s:url>
								<a href="<s:property value="downloadAttachUrl" />" >
									<s:property value="fileName"/>
								</a>
							</li>
							<li class="all">
								<s:textarea name="fileDesc" placeholder="無檔案說明" disabled="true"/>
							</li>
						</ul>
					</td>
				</tr>				
				</s:iterator>
			</tbody>
		</table>
		
		<div class="clear"></div>
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<input type="submit" value="確定刪除" class="redBtn"/>
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>		
	</s:form>
	
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>