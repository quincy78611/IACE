<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
	$(document).ready(function () {			
		$("ul.pagination > li > input").addClass("btn btn-default btn-sm");
		
		var pageIndex = parseInt('<s:property value="consultingPagedList.pageIndex"/>');
		var pageNumber = parseInt('<s:property value="consultingPagedList.pageNumber"/>');
		var pageCount = parseInt('<s:property value="consultingPagedList.pageCount"/>');
		
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
					
		// 注意: 在此頁面的搜尋按鈕記得要加上id
	    $("#btn-search").click(function(){
	        $("#pageIndex").val(0);
	        return true;
	    });
	 	// 注意: 在此頁面的重置按鈕記得要加上id
		$("#btn-reset").click(function(){
			$("input.form-control:text").val("");
			$("select").prop('selectedIndex', 0);
		});
	});
	</script>	
</head>
<body>	
	<s:form action="index" method="post" validate="true" >
		<div class="">
			<ul>
				<li class="half">
					<s:textfield placeholder="請輸入姓名或單位名稱" name="searchCondition.searchText"/>
				</li>
				<li class="quarter">
					<s:textfield placeholder="諮詢日期(起)" name="searchCondition.consultDateStart" class="calendarBox"/>
				</li>
				<li class="quarter">
					<s:textfield placeholder="諮詢日期(訖)" name="searchCondition.consultDateEnd" class="calendarBox"/>
				</li>				
				<li class="quarter">
					<s:select name="searchCondition.optionOrganizationTypeCode" list="optionOrganizationTypeList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部單位類型"/>
				</li>
				<li class="quarter">
					<s:select name="searchCondition.optionConsultCode" list="optionConsultList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部諮詢類型"/>
				</li>
				<li class="quarter">
					<s:select name="searchCondition.optionIndustryCode" list="optionIndustryList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部產業/領域別"/>
				</li>
				<li class="quarter">
					<input type="submit" value="查詢" class="btn btn-primary redBtn" id="btn-search"/>
					<input type="button" value="清除" class="btn btn-warning grayBtn" id="btn-reset"/>
				</li>										
			</ul>
		</div>
		<div class="clear"></div>
		<div class="">
			<table width="100%" class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th nowrap width="2%">No.</th>
						<th nowrap width="">姓名</th>
						<th nowrap width="">單位名稱</th>
						<th nowrap width="">單位類型</th>
						<th nowrap width="">諮詢類型</th>
						<th nowrap width="">產業/領域別</th>
<!-- 						<th nowrap width="">聯絡電話</th> -->
<!-- 						<th nowrap width="">E-MAIL</th> -->
						<th nowrap width="">諮詢日期</th>
						<th nowrap width="24%">功能</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="consultingPagedList != null">
						<s:iterator value="consultingPagedList.list" status="stat">
							<tr>
								<td>
									<s:property value="%{consultingPagedList.itemStart + #stat.count -1}" />
									<%-- <s:property value="id" /> --%>
								</td>						
								<td><s:property value="name" /></td>
								<td><s:property value="organization" /></td>
								<td><s:property value="%{optionOrganizationType.code + ' ' + optionOrganizationType.name}" /></td>
								<td><s:property value="%{optionConsult.code + ' ' + optionConsult.name}" /></td>
								<td><s:property value="%{optionIndustry.code + ' ' + optionIndustry.name}" /></td>
<%-- 								<td><s:property value="phone" /></td> --%>
<%-- 								<td><s:property value="email" /></td> --%>
								<td><s:property value="consultDate" /></td>								
								
								<td class="col-md-1">
									<!-- 檢視 -->
									<s:url value="showDetail.action" var="detailUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-info btn-func btn-view" value="檢視" 
										onclick="window.location.href='<s:property value="detailUrlTag" />'" />
										
									<!-- 編輯 -->
									<s:url value="update.action" var="updateUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-info btn-func btn-edit" value="編輯" 
										onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
										
									<!-- 刪除 -->	
									<s:url value="delete.action" var="deleteUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-danger btn-func btn-del" value="刪除" 
										onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />								
								</td>
							</tr>
						</s:iterator>
					</s:if>	
				</tbody>
			</table>
		</div>
		
		<div class="page">
			<s:hidden id="pageIndex" name="searchCondition.pageIndex" value="0"/>
			<s:hidden id="pageSize" name="searchCondition.pageSize" value="20" />
						
			<s:set var="pgList" value="consultingPagedList"/>
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
			</ul>
		</div>		
	</s:form>
</body>
</html>

