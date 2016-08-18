<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			var tbody = $(this).parents("table").find("tbody");
			var newTr = tbody.find("tr:last").clone();
			tbody.append(newTr);
			fileBrowseSetting();
			cleanFileAndDesc(newTr);
			resetNameAttrForImgTable();
		});
		
		$("input[type=button].btn-addMoreVideo").click(function(){
			var tbody = $(this).parents("table").find("tbody");
			var newTr = tbody.find("tr:last").clone();
			tbody.append(newTr);
			fileBrowseSetting();
			cleanFileAndDesc(newTr);
			resetNameAttrForVideoTable();
		});		
		
		$("input[type=button].btn-addMoreAttach").click(function(){
			var tbody = $(this).parents("table").find("tbody");
			var newTr = tbody.find("tr:last").clone();
			tbody.append(newTr);
			fileBrowseSetting();
			cleanFileAndDesc(newTr);
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
			$(this).parents("tr").find(".fileName").html(fileName);
			readURL(this); // display subnail
		});
		
		$("input[type=button].cancelSelectFile").click(function(){
			var rowCount = $(this).parents("tbody").find("tr").length;
			if (rowCount > 1) {
				$(this).parents("tr").remove();
				resetNameAttrForImgTable();
				resetNameAttrForVideoTable();
				resetNameAttrForAttachTable();
			}
		});
	}
</script>
<script>
	function cleanFileAndDesc(tr) {
		tr.find(".fileName").html("");
		tr.find("textarea").val("");
		tr.find("input[type=file]").val("");
		tr.find("img").attr('src', "");
		tr.find("video").attr('src', "");
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
		tr.find("input[type=file]").attr("name", "coopEx.imgs["+index+"].upload");
		tr.find("textarea").attr("name", "coopEx.imgs["+index+"].fileDesc");
	});
}

function resetNameAttrForVideoTable() {
	$("#table-video > tbody > tr").each(function( index ){
		var tr = $("#table-video > tbody > tr").eq(index);
		tr.find("input[type=file]").attr("name", "coopEx.videos["+index+"].upload");
		tr.find("textarea").attr("name", "coopEx.videos["+index+"].fileDesc");
	});
}

function resetNameAttrForAttachTable() {
	$("#table-attach > tbody > tr").each(function( index ){
		var tr = $("#table-attach > tbody > tr").eq(index);
		tr.find("input[type=file]").attr("name", "coopEx.attachFiles["+index+"].upload");
		tr.find("textarea").attr("name", "coopEx.attachFiles["+index+"].fileDesc");
	});
}
</script>
<style>
.table-files tr, th, td { border: solid 1px; }
.table-files td li { margin-bottom: 1px; }
.table-files input[type=file] { display:none; }
</style>
<meta name="funcPathText" content="新增"/>
</head>
<body>
	<s:form action="createSubmit" method="post" validate="true" enctype="multipart/form-data">
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
		<table id="table-img" class="table-files" width="100%">
			<thead>
				<tr>
					<td colspan="2">
						<input type="button" value="+ 增加照片" class="btn-addMoreImg redBtn"/>
					</td>			
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="15%">
						<img src="" style="max-width:120px; max-height:120px;" />
					</td>
					<td>
						<ul>
							<li>
								<input type="file" name="coopEx.imgs[0].upload" accept=".jpg, .jpeg, .png, .gif" >
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="fileName"></label>
							</li>
							<li class="all">
								<s:textarea name="coopEx.imgs[0].fileDesc" placeholder="請輸入檔案說明"/>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 影片 -->
		<table id="table-video" class="table-files" width="100%">
			<thead>
				<tr>
					<td colspan="2">
						<input type="button" value="+ 增加影片" class="btn-addMoreVideo redBtn"/>
					</td>			
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="15%">
						<video style="max-width:120px; max-height:120px;" poster=""></video>
					</td>
					<td>
						<ul>
							<li>
								<input type="file" name="coopEx.videos[0].upload" accept=".mp4" >
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="fileName"></label>
							</li>
							<li class="all">
								<s:textarea name="coopEx.videos[0].fileDesc" placeholder="請輸入檔案說明"/>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 附檔 -->
		<table id="table-attach" class="table-files" width="100%">
			<thead>
				<tr>
					<td>
						<input type="button" value="+ 增加附檔" class="btn-addMoreAttach redBtn"/>
					</td>			
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<ul>
							<li>
								<input type="file" name="coopEx.attachFiles[0].upload" >
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="刪除" />
							</li>
							<li>
								<label class="fileName"></label>
							</li>
							<li class="all">
								<s:textarea name="coopEx.attachFiles[0].fileDesc" placeholder="請輸入檔案說明"/>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
		
		<div class="clear"></div>
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="送出" />	
		</div>		
	</s:form>	
</body>
</html>