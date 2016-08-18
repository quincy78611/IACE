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
			var newIndex = parseInt(tbody.find("tr:last").index());
			newTr.find("input[type=file]").attr("name", "coopEx.imgs["+newIndex+"].upload");
			newTr.find("textarea").attr("name", "coopEx.imgs["+newIndex+"].fileDesc");
			fileBrowseSetting();
			newTr.find("input[type=button].cancelSelectFile").trigger("click");
		});
		
		$("input[type=button].btn-addMoreVideo").click(function(){
			var tbody = $(this).parents("table").find("tbody");
			var newTr = tbody.find("tr:last").clone();
			tbody.append(newTr);
			var newIndex = parseInt(tbody.find("tr:last").index());
			newTr.find("input[type=file]").attr("name", "coopEx.videos["+newIndex+"].upload");
			newTr.find("textarea").attr("name", "coopEx.videos["+newIndex+"].fileDesc");
			fileBrowseSetting();
			newTr.find("input[type=button].cancelSelectFile").trigger("click");
		});		
		
		$("input[type=button].btn-addMoreAttach").click(function(){
			var tbody = $(this).parents("table").find("tbody");
			var newTr = tbody.find("tr:last").clone();
			tbody.append(newTr);
			var newIndex = parseInt(tbody.find("tr:last").index());
			newTr.find("input[type=file]").attr("name", "coopEx.attachFiles["+newIndex+"].upload");
			newTr.find("textarea").attr("name", "coopEx.attachFiles["+newIndex+"].fileDesc");
			fileBrowseSetting();
			newTr.find("input[type=button].cancelSelectFile").trigger("click");
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
			readURL(this);
		});
		
		$("input[type=button].cancelSelectFile").click(function(){
			$(this).parents("tr").find(".fileName").html("");
			$(this).parents("tr").find("textarea").val("");
			$(this).parents("tr").find("input[type=file]").val("");
			$(this).parents("tr").find("img").attr('src', "");
			$(this).parents("tr").find("video").attr('src', "");
		});
	}
	
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
<style>
.table-files tr, th, td { border: solid 1px; }
.table-files td li { margin-bottom: 1px; }
</style>
<meta name="funcPathText" content="編輯管理 > 檢視"/>
</head>
<body>
	<s:form action="createSubmit" method="post" validate="true" enctype="multipart/form-data">
		<ul>
			<li class="all">
				<b>年度</b>
				<s:textfield name="coopEx.year" readonly="true"/>
			</li>
			<li class="all">
				<b>案名</b>
				<s:textfield name="coopEx.projName" readonly="true"/>
			</li>
			<li class="all">
				<b>研發團隊</b>
				<s:textfield name="coopEx.rdTeam" readonly="true"/>
			</li>
			<li class="all">
				<b>輔導團隊</b>
				<s:textfield name="coopEx.assisTeam" readonly="true"/>
			</li>			
			<li class="all">
				<b>內容</b>
				<s:textarea name="coopEx.content" rows="10" readonly="true"/>
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
				<tr>
					<td width="15%">
						<img src="" style="max-width:120px; max-height:120px;" />
					</td>
					<td>
						<ul>
							<li>
								<s:textfield name="coopEx.imgs[0].fileName" readonly="true"/>
							</li>
							<li class="all">
								<s:textarea name="coopEx.imgs[0].fileDesc" placeholder="請輸入檔案說明" readonly="true"/>
							</li>
						</ul>
					</td>
				</tr>
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
				<tr>
					<td width="15%">
						<video style="max-width:120px; max-height:120px;" poster=""></video>
					</td>
					<td>
						<ul>
							<li>
								<input type="file" name="coopEx.videos[0].upload" accept=".mp4" style="display:none;">
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="清除檔案和說明" />
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
		<table class="table-files" width="100%">
			<thead>
				<tr>
					<td>附檔清單</td>			
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<ul>
							<li>
								<input type="file" name="coopEx.attachFiles[0].upload" style="display:none;">
								<input type="button" class="btn-func btn-view btn-fake-browse" value="選擇檔案"/>
								<input type="button" class="btn-func btn-del cancelSelectFile" value="清除檔案和說明" />
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