<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		addMore();
		fileBrowseSetting();
	});
</script>
<script>
	function addMore() {	
		$("input[type=button].btn-addMoreImg").click(function(){
			var newTr = $(this).parents("table").find("tr.hidden-sample-tr").clone();
			newTr.removeClass("hidden-sample-tr");
			$(this).parents("table").find("tbody").append(newTr);
			fileBrowseSetting();
			resetNameAttrForImgTable();
		});
		
		$("input[type=button].btn-addMoreVideo").click(function(){
			var newTr = $(this).parents("table").find("tr.hidden-sample-tr").clone();
			newTr.removeClass("hidden-sample-tr");
			$(this).parents("table").find("tbody").append(newTr);
			fileBrowseSetting();
			resetNameAttrForVideoTable();
		});		
		
		$("input[type=button].btn-addMoreAttach").click(function(){
			var newTr = $(this).parents("table").find("tr.hidden-sample-tr").clone();
			newTr.removeClass("hidden-sample-tr");
			$(this).parents("table").find("tbody").append(newTr);
			fileBrowseSetting();
			resetNameAttrForAttachTable();
		});
	}
</script>
<script>
	function fileBrowseSetting() {
		$(".btn-fake-browse").click(function(){
			$(this).parents("tr").find("input[type=file]").trigger("click");
		});
		
		$("input[type=file]").change(function() {
			var fileName = $(this).get(0).files[0].name;
			$(this).parents("tr").find(".label-fileName").html(fileName);
			readURL(this); // display subnail
		});
		
		$("input[type=button].cancelSelectFile").click(function(){
			var rowCount = $(this).parents("tbody").find("tr").length;
			$(this).parents("tr").remove();
			resetNameAttrForImgTable();
			resetNameAttrForVideoTable();
			resetNameAttrForAttachTable();
		});
	}
</script>
<script>
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	        	$(input).parents("tr").find("img").attr('src', e.target.result);
	        	$(input).parents("tr").find("video").attr('src', e.target.result);
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}
</script>
<script>
function resetNameAttrForImgTable() {
	$("#table-img > tbody > tr").each(function( index ){
		var tr = $("#table-img > tbody > tr").eq(index);
		
		tr.find(".id").attr("name", "coopEx.imgs["+index+"].id");
		tr.find(".filePath").attr("name", "coopEx.imgs["+index+"].filePath");
		tr.find(".fileContentType").attr("name", "coopEx.imgs["+index+"].fileContentType");
		tr.find(".fileName").attr("name", "coopEx.imgs["+index+"].fileName");
		tr.find(".fileDesc").attr("name", "coopEx.imgs["+index+"].fileDesc");
		tr.find(".upload").attr("name", "coopEx.imgs["+index+"].upload");
	});
}

function resetNameAttrForVideoTable() {
	$("#table-video > tbody > tr").each(function( index ){
		var tr = $("#table-video > tbody > tr").eq(index);
		
		tr.find(".id").attr("name", "coopEx.videos["+index+"].id");
		tr.find(".filePath").attr("name", "coopEx.videos["+index+"].filePath");
		tr.find(".fileContentType").attr("name", "coopEx.videos["+index+"].fileContentType");
		tr.find(".fileName").attr("name", "coopEx.videos["+index+"].fileName");
		tr.find(".fileDesc").attr("name", "coopEx.videos["+index+"].fileDesc");
		tr.find(".upload").attr("name", "coopEx.videos["+index+"].upload");
	});
}

function resetNameAttrForAttachTable() {
	$("#table-attach > tbody > tr").each(function( index ){
		var tr = $("#table-attach > tbody > tr").eq(index);

		tr.find(".id").attr("name", "coopEx.attachFiles["+index+"].id");
		tr.find(".filePath").attr("name", "coopEx.attachFiles["+index+"].filePath");
		tr.find(".fileContentType").attr("name", "coopEx.attachFiles["+index+"].fileContentType");
		tr.find(".fileName").attr("name", "coopEx.attachFiles["+index+"].fileName");
		tr.find(".fileDesc").attr("name", "coopEx.attachFiles["+index+"].fileDesc");
		tr.find(".upload").attr("name", "coopEx.attachFiles["+index+"].upload");
	});
}
</script>
<style>
table.table-files tr, th, td { border: solid 1px; }
table.table-files td li { margin-bottom: 1px; }
table.table-files input[type=file] { display:none; }
table.table-files tr.hidden-sample-tr { display:none; }
</style>
<meta name="funcPathText" content="編輯管理 > 編輯"/>
</head>
<body>
	<s:form action="updateSubmit" method="post" validate="true" enctype="multipart/form-data">
		<s:hidden name="id"/>
		<s:hidden name="coopEx.id"/>
		<s:hidden name="coopEx.isValid"/>
		<s:hidden name="coopEx.createTime"/>
		<s:hidden name="coopEx.createUser"/>
		<s:hidden name="coopEx.updateTime"/>
		<s:hidden name="coopEx.updateUser"/>
		<s:hidden name="coopEx.ver"/>
		
		<ul>
			<li class="all">
				<b>年度</b>
				<s:textfield name="coopEx.year" />
			</li>
			<li class="all">
				<b>案名</b>
				<s:textfield name="coopEx.projName" />
			</li>
			<li class="all">
				<b>研發團隊</b>
				<s:textfield name="coopEx.rdTeam" />
			</li>
			<li class="all">
				<b>輔導團隊</b>
				<s:textfield name="coopEx.assisTeam" />
			</li>			
			<li class="all">
				<b>內容</b>
				<s:textarea name="coopEx.content" rows="10"/>
			</li>				
		</ul>
		
		<!-- 照片 -->
		<table id="table-img" class="table-files">
			<thead>
				<tr>
					<td colspan="2">
						<input type="button" value="+ 增加照片" class="btn-addMoreImg redBtn"/>
					</td>			
				</tr>
				<tr class="hidden-sample-tr">
					<s:hidden class="id" value="-1"/>
					<s:hidden class="filePath"/>
					<s:hidden class="fileContentType"/>
					<s:hidden class="fileName"/>
					<td width="15%">
						<img src="" style="max-width:120px; max-height:120px;" />
					</td>
					<td>
						<ul>
							<li>
								<s:file type="file" class="upload" accept=".jpg, .jpeg, .png, .gif" />
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="label-fileName">
									<span style="color:#AA3333;">(尚未選取檔案)</span>
								</label>
							</li>
							<li class="all">
								<s:textarea class="fileDesc" placeholder="請輸入檔案說明。(若沒有選擇任何檔案，則在此輸入的說明將不會被記錄)"/>
							</li>
						</ul>
					</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.imgs" status="stat">
				<tr>
					<s:hidden class="id" name="%{'coopEx.imgs['+#stat.index+'].id'}"/>
					<s:hidden class="filePath" name="%{'coopEx.imgs['+#stat.index+'].filePath'}"/>
					<s:hidden class="fileContentType" name="%{'coopEx.imgs['+#stat.index+'].fileContentType'}"/>
					<s:hidden class="fileName" name="%{'coopEx.imgs['+#stat.index+'].fileName'}"/>
					<td width="15%">
						<img src="data:image;base64,<s:property value="base64Img"/>" style="max-width:120px; max-height:120px;" />
					</td>
					<td>
						<ul>
							<li>
								<s:file type="file" class="upload" name="%{'coopEx.imgs['+#stat.index+'].upload'}" accept=".jpg, .jpeg, .png, .gif" />
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="label-fileName">
									<s:property value="fileName"/>
								</label>
							</li>
							<li class="all">
								<s:textarea class="fileDesc" name="%{'coopEx.imgs['+#stat.index+'].fileDesc'}" placeholder="請輸入檔案說明。(若沒有選擇任何檔案，則在此輸入的說明將不會被記錄)"/>
							</li>
						</ul>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<!-- 影片 -->
		<table id="table-video" class="table-files">
			<thead>
				<tr>
					<td colspan="2">
						<input type="button" value="+ 增加影片" class="btn-addMoreVideo redBtn"/>
					</td>			
				</tr>
				<tr class="hidden-sample-tr">
					<s:hidden class="id" value="-1"/>
					<s:hidden class="filePath"/>
					<s:hidden class="fileContentType"/>
					<s:hidden class="fileName"/>
					<td width="15%">
						<video 
							controls="controls" preload="none"
							style="max-width:120px; max-height:120px;">
						</video>
					</td>
					<td>
						<ul>
							<li>
								<s:file type="file" class="upload" accept=".mp4" />
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="label-fileName">
									<span style="color:#AA3333;">(尚未選取檔案)</span>
								</label>
							</li>
							<li class="all">
								<s:textarea class="fileDesc" placeholder="請輸入檔案說明。(若沒有選擇任何檔案，則在此輸入的說明將不會被記錄)"/>
							</li>
						</ul>
					</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.videos" status="stat">
				<tr>
					<s:hidden class="id" name="%{'coopEx.videos['+#stat.index+'].id'}"/>
					<s:hidden class="filePath" name="%{'coopEx.videos['+#stat.index+'].filePath'}"/>
					<s:hidden class="fileContentType" name="%{'coopEx.videos['+#stat.index+'].fileContentType'}"/>
					<s:hidden class="fileName" name="%{'coopEx.videos['+#stat.index+'].fileName'}"/>
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
								<s:file type="file" class="upload" name="%{'coopEx.videos['+#stat.index+'].upload'}" accept=".mp4" />
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="label-fileName">
									<s:property value="fileName"/>
								</label>
							</li>
							<li class="all">
								<s:textarea class="fileDesc" name="%{'coopEx.videos['+#stat.index+'].fileDesc'}" placeholder="請輸入檔案說明。(若沒有選擇任何檔案，則在此輸入的說明將不會被記錄)"/>
							</li>
						</ul>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<!-- 附檔 -->
		<table id="table-attach" class="table-files">
			<thead>
				<tr>
					<td>
						<input type="button" value="+ 增加附檔" class="btn-addMoreAttach redBtn"/>
					</td>			
				</tr>
				<tr class="hidden-sample-tr">
					<s:hidden class="id" value="-1"/>
					<s:hidden class="filePath"/>
					<s:hidden class="fileContentType"/>
					<s:hidden class="fileName"/>

					<td>
						<ul>
							<li>
								<s:file type="file" class="upload" />
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="label-fileName">
									<span style="color:#AA3333;">(尚未選取檔案)</span>
								</label>
							</li>
							<li class="all">
								<s:textarea class="fileDesc" placeholder="請輸入檔案說明。(若沒有選擇任何檔案，則在此輸入的說明將不會被記錄)"/>
							</li>
						</ul>
					</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="coopEx.attachFiles" status="stat">
				<tr>
					<s:hidden class="id" name="%{'coopEx.attachFiles['+#stat.index+'].id'}"/>
					<s:hidden class="filePath" name="%{'coopEx.attachFiles['+#stat.index+'].filePath'}"/>
					<s:hidden class="fileContentType" name="%{'coopEx.attachFiles['+#stat.index+'].fileContentType'}"/>
					<s:hidden class="fileName" name="%{'coopEx.attachFiles['+#stat.index+'].fileName'}"/>

					<td>
						<ul>
							<li>
								<s:file type="file" class="upload" name="%{'coopEx.attachFiles['+#stat.index+'].upload'}" />
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="label-fileName">
									<s:property value="fileName"/>
								</label>
							</li>
							<li class="all">
								<s:textarea class="fileDesc" name="%{'coopEx.attachFiles['+#stat.index+'].fileDesc'}" placeholder="請輸入檔案說明。(若沒有選擇任何檔案，則在此輸入的說明將不會被記錄)"/>
							</li>
						</ul>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<div class="clear"></div>
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="送出" />
			<input type="button" class="grayBtn" value="回列表頁" onclick="window.location.href='<s:url value="index"/>'" />
		</div>		
	</s:form>	
</body>
</html>