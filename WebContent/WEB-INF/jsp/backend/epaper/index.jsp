<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript">
	var dialog;
	var form = $("#dialog-form form");

	$(document).ready(function () {
		paggingSetting();
		funcBtnSetting();
		
		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 150,
			width : 260,
			modal : true,
			buttons : {
				"送出測試信" : sendTestEmail,
				"取消" : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
			}
		});
	});
	
	function sendTestEmail() {
		var valid = true;
		var email = $("#dialog-form input[name=testEmailTo]");
		var emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
		valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com" );
		if (valid) {
			dialog.find("form").submit();
			dialog.dialog("close");
		}
		return valid;
	}

	function checkRegexp(o, regexp, n) {
		if (!(regexp.test(o.val()))) {
			o.addClass("ui-state-error");
			return false;
		} else {
			return true;
		}
	}
</script>
<script>
	function funcBtnSetting() {
		$(".btn-export").click(function(){
			var url = '<s:url value="exportRawData.action" />';
			$("form").attr('action', url);
			$("form").submit();
			$("form").attr('action', '<s:url value="index.action"/>'); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
		});		
		$(".btn-view").click(function() {
			var url = $(this).siblings(".detailUrl").val();
			$("form").attr('action', url);
			$("form").submit();
			$("form").attr('action', '<s:url value="index.action"/>'); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
		});
		$(".btn-edit").click(function() {
			var url = $(this).siblings(".updateUrl").val();
			$("form").attr('action', url);
			$("form").submit();
			$("form").attr('action', '<s:url value="index.action"/>'); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
		});
		$(".btn-del").click(function() {
			var url = $(this).siblings(".deleteUrl").val();
			$("form").attr('action', url);
			$("form").submit();
			$("form").attr('action', '<s:url value="index.action"/>'); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
		});
		$(".btn-publish").click(function() {
			var url = $(this).siblings(".publishUrl").val();
			$("form").attr('action', url);
			$("form").submit();
			$("form").attr('action', '<s:url value="index.action"/>'); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
		});
		$(".btn-sendTestEmail").click(function() {
			var id = $(this).siblings("input[name=id]").val();
			$("#dialog-form input[name=id]").val(id);
			dialog.dialog( "open" );
		});
	}
</script>
<script>	
	function paggingSetting() {
		$("ul.pagination > li > input").addClass("btn btn-default btn-sm");
		
		var pageIndex = parseInt('<s:property value="epaperPagedList.pageIndex"/>');
		var pageNumber = parseInt('<s:property value="epaperPagedList.pageNumber"/>');
		var pageCount = parseInt('<s:property value="epaperPagedList.pageCount"/>');
		
		$("ul > li > input.btn-page").click(function() {
			$("#pageIndex").val($(this).attr("value") - 1);
			return true;
		});
		$("ul.pagination > li > input.btn-page[value='"+pageNumber+"']").addClass("active");

		//第一頁按鈕
		$("ul > li > input.btn-first-page").click(function() {
			$("#pageIndex").val(0);
		});
		
		//最後一頁按鈕
		$("ul > li > input.btn-last-page").click(function() {
			$("#pageIndex").val(pageCount-1);
		});
		
		//上一頁按鈕
		if (pageIndex == 0) {
			$("ul > li > input.btn-previous-page").addClass("disabled");
		}
		$("ul > li > input.btn-previous-page").click(function() {
			if (pageIndex > 0) {
				$("#pageIndex").val(pageIndex - 1);
				return true;
			} else {
				return false;
			}
		});
		
		//下一頁按鈕
		if (pageIndex == pageCount - 1) {
			$("ul > li > input.btn-next-page").addClass("disabled");
		}
		$("ul > li > input.btn-next-page").click(function() {
			if (pageIndex < (pageCount - 1)) {
				$("#pageIndex").val(pageIndex + 1);
				return true;
			} else {
				return false;
			}
		});
		
		// 每頁筆數
		$(".select-pageSize").change(function() {
			var pageSize = $(this).find(":checked").val();
			$("#pageSize").val(pageSize);
			$("#pageIndex").val(0);
			$("form").submit();
		});
		$(".select-pageSize").val($("#pageSize").val());
		
		// 注意: 在此頁面的搜尋按鈕記得要加上id
		$("#btn-search").click(function(){
			$("#pageIndex").val(0);
			return true;
		});
	 	// 注意: 在此頁面的重置按鈕記得要加上id
		$("#btn-reset").click(function(){
			$("input.form-control:text").val("");
			$("select").prop('selectedIndex', 0);
			$("input[type=checkbox]").prop("checked", false);
			displaySelectedGrbDomains();
		});
	}
</script>

<meta name="funcPathText" content="編輯管理 " />
</head>
<body>
	<s:form action="index" method="post" validate="true" >
		<div>
			<ul>
				<li class="half">
					<s:textfield placeholder="標題" name="searchCondition.searchText" maxlength="100" />
				</li>
				<li class="quarter"> 
					<s:select name="searchCondition.year" list="#{'':'--年份--', '2017':'2017', '2016':'2016', '2015':'2015', '2014':'2014', '2013':'2013'}" />
				</li>
				<li class="quarter">
					<input type="submit" value="查詢" class="redBtn" id="btn-search"/>
					<input type="button" value="清除" class="grayBtn" id="btn-reset"/>
				</li>
			</ul>
		</div>
		<div class="clear"></div>

		<div class="page">
			<s:set var="pgList" value="epaperPagedList"/>
			<s:set var="pgIndex" value="searchCondition.pageIndex"/>
			<s:set var="pgCount" value="#pgList.pageCount"/>
			
			<ul class="pagination">
				<s:if test="#pgList != null && #pgCount > 0">
					<li><input type="submit" value="First" class="btn-first-page" /></li>
					<li><input type="submit" value=&laquo; class="btn-previous-page" /></li>
					<s:if test="#pgIndex >= 5">
						<li>......</li>
					</s:if>
					<s:iterator value="#pgList.pageNumberList" status="stat" 
						begin="%{#pgIndex < 5 ? 0 : #pgIndex - 5 }"
						end="%{#pgIndex > #pgCount - 6 ? #pgCount -1 : #pgIndex +5 }">
						<li><input type="submit" value=<s:property/> class="btn-page" /></li>
					</s:iterator>
					<s:if test="#pgIndex <= #pgCount - 6">
						<li>......</li>
					</s:if>
					<li><input type="submit" value=&raquo;	class="btn-next-page" /></li>
					<li class="next"><input type="submit" value="Last" class="btn-last-page" /></li>
				</s:if>
				<li>
					<p>共 <s:property value="#pgList.totatlItemCount"/> 筆資料</p>
				</li>
				<li>
					&nbsp;&nbsp;&nbsp;每頁筆數:
				</li>
				<li>
					<select class="select-pageSize">
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="50">50</option>
					</select>
				</li>
			</ul>
		</div>

		<div class="">
			<table>
				<tr>
					<th nowrap width="3%">No.</th>
					<th nowrap width="">期數</th>
					<th nowrap width="">標題</th>
					<th nowrap width="">發佈日</th>
					<th nowrap width="">發佈狀態</th>
					<th nowrap width=""></th>
					<th nowrap width="">功能</th>
				</tr>
				<s:if test="epaperPagedList != null">
					<s:iterator value="epaperPagedList.list" status="stat">
						<tr>
							<td>
								<s:property value="%{epaperPagedList.itemStart + #stat.count -1}" />
								<%-- <s:property value="id" /> --%>
							</td>

							<td><s:property value="no" /></td>
							<td>
								<s:url value="/f2/ePaper/read" var="urlTag" escapeAmp="false" forceAddSchemeHostAndPort="true">
									<s:param name="id" value="id" />
								</s:url>
								<a href="<s:property value="%{#urlTag}"/>" target="_blank">
									<s:property value="title" />
								</a>
							</td>
							<td><s:date name="postDate" format="yyyy/MM/dd"/></td>
							<td>
								<s:if test="publishState">
									已發佈
								</s:if>
								<s:else>
									<s:url value="publish.action" var="publishUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<s:hidden value="%{#publishUrlTag}" class="publishUrl" disabled="true"/>
									<input type="button" class="btn-publish" value="確認發佈" />
								</s:else>
							</td>
							<td>
								<s:hidden name="id" disabled="true"/>
								<input type="button" class="btn-sendTestEmail" value="寄送測試信" />
							</td>
							<td>
								<!-- 檢視 -->
								<s:if test='%{#session.sysUser.hasAuth(namespace, "showDetail")}'>
									<s:url value="showDetail.action" var="detailUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<s:hidden value="%{#detailUrlTag}" class="detailUrl" disabled="true"/>
									<input type="button" class="btn-info btn-func btn-view" value="檢視" />	
								</s:if>
								
								<!-- 編輯 -->
								<s:if test='%{#session.sysUser.hasAuth(namespace, "update")}'>
									<s:url value="update.action" var="updateUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<s:hidden value="%{#updateUrlTag}" class="updateUrl" disabled="true"/>
									<input type="button" class="btn-info btn-func btn-edit" value="編輯" />
								</s:if>
								
								<!-- 刪除 -->	
								<s:if test='%{#session.sysUser.hasAuth(namespace, "delete")}'>
									<s:url value="delete.action" var="deleteUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<s:hidden value="%{#deleteUrlTag}" class="deleteUrl" disabled="true"/>
									<input type="button" class="btn-info btn-func btn-del" value="刪除" />
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
		</div>
		
		<div class="page">
			<s:hidden id="pageIndex" name="searchCondition.pageIndex" />
			<s:hidden id="pageSize" name="searchCondition.pageSize" />
			
			<s:set var="pgList" value="epaperPagedList"/>
			<s:set var="pgIndex" value="searchCondition.pageIndex"/>
			<s:set var="pgCount" value="#pgList.pageCount"/>
			
			<ul class="pagination">
				<s:if test="#pgList != null && #pgCount > 0">
					<li><input type="submit" value="First" class="btn-first-page" /></li>
					<li><input type="submit" value=&laquo; class="btn-previous-page" /></li>
					<s:if test="#pgIndex >= 5">
						<li>......</li>
					</s:if>
					<s:iterator value="#pgList.pageNumberList" status="stat" 
						begin="%{#pgIndex < 5 ? 0 : #pgIndex - 5 }"
						end="%{#pgIndex > #pgCount - 6 ? #pgCount -1 : #pgIndex +5 }">
						<li><input type="submit" value=<s:property/> class="btn-page" /></li>
					</s:iterator>
					<s:if test="#pgIndex <= #pgCount - 6">
						<li>......</li>
					</s:if>
					<li><input type="submit" value=&raquo;	class="btn-next-page" /></li>
					<li class="next"><input type="submit" value="Last" class="btn-last-page" /></li>
				</s:if>
				<li>
					<p>共 <s:property value="#pgList.totatlItemCount"/> 筆資料</p>
				</li>
				<li>
					&nbsp;&nbsp;&nbsp;每頁筆數:
				</li>
				<li>
					<select class="select-pageSize">
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="50">50</option>
					</select>
				</li>
			</ul>
		</div>
	</s:form>
	<div class="clear"></div>
	
	<div id="dialog-form" title="寄送測試信">
		<p class="validateTips"></p>
	
		<s:form action="sendTestEmail" method="post" validate="true" >
			<input type="hidden" name="id"/>
			<label for="email">Email</label>
      		<input type="text" name="testEmailTo" class="text ui-widget-content ui-corner-all">
		</s:form>
	</div>
</body>
</html>

