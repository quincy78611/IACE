<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="新增"/>
<script type="text/javascript" src="<s:url value="/scripts/videosArea/attachTableSetting.js"/>"></script>
<script>
	$(document).ready(function(){
		
	});
</script>
<style>
table.table-files input[type=file] { display:none; }
tr.hidden-sample-tr { display:none; }
</style>
</head>
<body>
	<s:form action="createSubmit" method="post" validate="true" enctype="multipart/form-data" id="form-create">
		<ul>						
			<li class="all">
				<b>標題</b>
				<s:textfield name="videosArea.title" maxlength="200"/>
			</li>
			<li class="all">
				<b>來源</b>
				<s:textfield name="videosArea.source" maxlength="200"/>
			</li>
			<li class="half">
				<b>活動日期</b>
				<s:textfield name="videosArea.actDate" maxlength="200"/>			
			</li>
			<li class="half">
				<b>活動地點</b>
				<s:textfield name="videosArea.actAddress" maxlength="200"/>
			</li>
			<div class="clear"></div>
			<li class="half">
				<b>主辦單位</b>
				<s:textfield name="videosArea.organizer" maxlength="200"/>
			</li>
			<li class="half">
				<b>指導單位</b>
				<s:textfield name="videosArea.advisor" maxlength="200"/>
			</li>			
			<li class="all">
				<b>內容</b>
				<s:textarea name="videosArea.content" />
				<div>
					<!-- 網頁編輯器 -->
					<s:include value="/WEB-INF/jsp/ckEditor.jsp" />
					<script type="text/javascript">
					window.onload = function() {
						CKEDITOR.replace('videosArea.content'); // 此處參數 'about.content' 為需要套用ckeditor 的 textarea 的 name
					};
					</script>
				</div>
			</li>
			<li class="all">
				<b>代表縮圖</b>
				<s:file name="videosArea.uploadThumbnail" accept=".jpg, .jpeg, .png, .gif"/>
				<img src="" style="max-width:400px; max-height:300px;" />
			</li>		
		</ul>
		
		<!-- 影片 -->
		<table id="table-attach" class="table-files">
			<thead>
				<tr>
					<td colspan="2">
						<input type="button" value="+ 增加影片" class="btn-addMoreAttach redBtn"/>
					</td>
				</tr>
				<tr class="hidden-sample-tr">
					<td>
						<video 
							controls="controls" preload="metadata"
							style="max-width:400px; max-height:300px;">
						</video>
					</td>
					<td>
						<s:file type="file" class="upload" accept=".mp4" onchange="fileOnchange(this)"/>
						<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案" onclick="fakeBrowseBtnOnclick(this)"/>
						<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" onclick="deleteFileBtnOnclick(this)"/>

						<label class="label-fileName">
							<span style="color:#AA3333;">(尚未選取檔案)</span>
						</label>
						<s:textfield class="fileTitle" placeholder="檔案標題"/>
					</td>
				</tr>				
			</thead>
			<tbody>
			</tbody>
		</table>
		
			
		<h2>SEO區(網站優化加註，幫助訊息更易搜尋到)</h2>
		<ul class="common-meta-fields">
			<li class="all">
				<b>Meta Title</b>
				<s:textfield name="videosArea.metaTitle" maxlength="200"/>
			</li>
			<li class="all">
				<b>Meta Description</b>
				<s:textfield name="videosArea.metaDes" />
			</li>
			<li class="all">
				<b>Meta KeyWord</b>
				<s:textfield name="videosArea.metaKeyword" />
			</li>
		</ul>
		<div class="clear"></div>
		
		<h2>狀態設定區</h2>
		<ul class="common-linkiac-fields">
			<li class="quarter">
				<b>瀏覽次數</b>
				<s:textfield name="videosArea.clickNum" type="number" value="0" min="0"/>
			</li>
			<li class="quarter">
				<b>排序(數字愈大排愈前面)</b>
				<s:textfield name="videosArea.sort" type="number" value="0"/>
			</li>
			<li class="quarter">
				<b>顯示狀態</b>
				<s:radio name="videosArea.displayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" value="true"/>
			</li>
			<li class="quarter">
				<b>首頁顯示</b>
				<s:radio name="videosArea.homeDisplayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" value="false"/>
			</li>			
		</ul>		
		<div class="clear"></div>
		
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="送出" />
		</div>		
	</s:form>
	
<!-- ======================================================================= -->
</body>
</html>