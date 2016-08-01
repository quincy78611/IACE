<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(document).ready(function () {
		deleteBtnSetting();
		optionSubjectLv2ListSetting();
		optionSubjectLv3ListSetting();
	});
</script>
<script>
	function deleteBtnSetting() {
		$(".btn-del").click(function() {
			if (confirm("確定要刪除？")) {
				var url = $(this).siblings(".deleteUrl").val();
				window.location.href = url;
			}
		});
	}
</script>
<script>
	function optionSubjectLv2ListSetting() {
		$('select[name=searchLv2Code]').hide();
		
		$("select[name=searchLv1Code]").change(function() {
			var parentCode = $(this).val();			
			var select = $('select[name=searchLv2Code]');
			if (parentCode == "") {
				select.hide();
			} else {
				select.show();
			}
			
			$.getJSON(
				'getOptionSubjectListAjax', 
				{ ajaxSearchLv : 2, ajaxSearchParentCode : parentCode}, 
				function(jsonResponse) {
					$('#ajaxResponse').text(jsonResponse.dummyMsg);
					
					select.find('option').remove();
					$('<option>').val("").text("全部").appendTo(select);
					select.val("").change();
					for (var i=0; i<jsonResponse.optionSubjectList.length; i++) {
						var optionSubject = jsonResponse.optionSubjectList[i];
						$('<option>').val(optionSubject.code).text(optionSubject.code+"-"+optionSubject.name).appendTo(select);
					}
				}
			);
			
			
		});
	}
</script>
<script>
	function optionSubjectLv3ListSetting() {
		$('select[name=searchLv3Code]').hide();
		
		$("select[name=searchLv2Code]").change(function() {
			var parentCode = $(this).val();			
			var select = $('select[name=searchLv3Code]');
			if (parentCode == "") {
				select.hide();
			} else {
				select.show();
			}
			
			$.getJSON(
				'getOptionSubjectListAjax', 
				{ ajaxSearchLv : 3, ajaxSearchParentCode : parentCode}, 
				function(jsonResponse) {
					$('#ajaxResponse').text(jsonResponse.dummyMsg);
					
					select.find('option').remove();
					$('<option>').val("").text("全部").appendTo(select);
					for (var i=0; i<jsonResponse.optionSubjectList.length; i++) {
						var optionSubject = jsonResponse.optionSubjectList[i];
						$('<option>').val(optionSubject.code).text(optionSubject.code+"-"+optionSubject.name).appendTo(select);
					}
				}
			);
		});		
	}
</script>
</head>
<body>
	<s:form action="search" method="post" validate="true" >
		<ul>
			<li class="quarter">
				<s:select name="searchLv1Code" list="optionSubjectLv1List" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部"/>
			</li>
			<li class="quarter">
				<s:select name="searchLv2Code" list="{}" headerKey="" headerValue="全部"/>
			</li>
			<li class="quarter">
				<s:select name="searchLv3Code" list="{}" headerKey="" headerValue="全部"/>
			</li>							
			<li class="quarter"><input type="submit" value="查詢"
				class="btn btn-primary redBtn" id="btn-search" /> <input
				type="button" value="清除" class="btn btn-warning grayBtn"
				id="btn-reset" />
			</li>
		</ul>
	</s:form>
	<div class="clear"></div>
	<input type="button" class="redBtn" value="新增代碼" onclick="window.location.href='<s:url value="create.action"/>'" />
	<div class="clear"></div>
	<br>
	<table width="100%">
		<tr>
			<th width="2%" nowrap>No.</th>
			<th width="" nowrap>代碼</th>
			<th width="" nowrap>名稱</th>
			<th width="17%">功能</th>
		</tr>
		<s:iterator value="optionList" status="stat">
			<tr>
				<td>
					<s:property value="#stat.count" />
					<s:hidden name="id" class="id"/>
				</td>						
				<td><s:property value="code" /></td>
				<td><s:property value="name" /></td>
				<td class="col-md-2">
					<s:url value="update.action" var="updateUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<input type="button" class="btn-func btn-edit" value="編輯" 
						onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
						
					<s:url value="deleteSubmit.action" var="deleteUrlTag">
						<s:param name="id" value="id" />
					</s:url>
					<s:hidden value="%{#deleteUrlTag}" class="deleteUrl" disabled="true"/>
					<input type="button" class="btn-func btn-del" value="刪除" />								
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>