<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {

	});
</script>

<style>
.table-files tr, th, td { border: solid 1px; }
.table-files td li { margin-bottom: 1px; }
</style>
<meta name="funcPathText" content="編輯管理 > 刪除"/>
</head>
<body>
	<s:form action="deleteSubmit" method="post" validate="true" enctype="multipart/form-data">
		<s:hidden name="id" />
		<ul>
			<li class="all">
				<b>年度</b>
				<s:textfield name="coopEx.year" disabled="true"/>
			</li>
			<li class="all">
				<b>案名</b>
				<s:textfield name="coopEx.projName" disabled="true"/>
			</li>
			<li class="all">
				<b>研發團隊</b>
				<s:textfield name="coopEx.rdTeam" disabled="true"/>
			</li>
			<li class="all">
				<b>輔導團隊</b>
				<s:textfield name="coopEx.assisTeam" disabled="true"/>
			</li>			
			<li class="all">
				<b>內容</b>
				<s:textarea name="coopEx.content" rows="10" disabled="true"/>
			</li>				
		</ul>
		<br>
		
		<!-- 照片 -->
		<table class="table-files" width="100%">
			<thead>
				<tr>
					<td colspan="2">照片清單</td>			
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.imgs" status="stat">
				<tr>
					<td width="15%">
						<img src="data:image;base64,<s:property value="base64Img"/>" style="max-width:120px; max-height:120px;" />
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
		
		<!-- 影片 -->
		<table class="table-files" width="100%">
			<thead>
				<tr>
					<td colspan="2">影片清單</td>			
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.videos" status="stat">
				<tr>
					<td width="15%">
						<s:url value="downloadVideo.action" var="downloadVideoUrl">
							<s:param name="videoId" value="id" />
						</s:url>					
						<video 
							src="<s:property value="downloadVideoUrl" />" 
							controls="controls" preload="none"
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
		<table class="table-files" width="100%">
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
								<s:url value="downloadAttach.action" var="downloadAttachUrl">
									<s:param name="attachFileId" value="id" />
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
			<input type="button" class="grayBtn" value="回列表頁" onclick="window.location.href='<s:url value="index"/>'" />	
		</div>		
	</s:form>	
</body>
</html>