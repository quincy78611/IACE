<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
</head>
<body>	
	<s:form action="index" method="post" validate="true" >
		<div class="container-fluid">
			<div class="col-md-3">
				<s:textfield placeholder="姓名" name="searchName" maxlength="100" cssClass="form-control" />
			</div>
			<div class="col-md-7">
				<s:textfield placeholder="單位名稱" name="searchOrganization" maxlength="500" cssClass="form-control" />
			</div>		
			<div class="col-md-2">			
				<input type="submit" value="查詢" class="btn btn-primary" id="btn-search"/>
				<input type="button" value="清除" class="btn btn-warning" id="btn-reset"/>
			</div>
		</div>
		<p>
		<div class="">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>No.</th>
						<th>姓名</th>
						<th>單位名稱</th>
						<th>單位類型</th>
						<th>諮詢類型</th>
						<th>產業/領域別</th>
						<th>聯絡電話</th>
						<th>E-MAIL</th>
						<th>諮詢日期</th>
						<th></th>
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
								<td><s:property value="phone" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="consultDate" /></td>								
								
								<td class="col-md-1">
									<!-- 檢視 -->
									<s:url value="showDetail.action" var="detailUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-info" value="檢視" 
										onclick="window.location.href='<s:property value="detailUrlTag" />'" />
										
									<!-- 編輯 -->
									<s:url value="update.action" var="updateUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-info" value="編輯" 
										onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
										
									<!-- 刪除 -->	
									<s:url value="delete.action" var="deleteUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-danger" value="刪除" 
										onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />								
								</td>
							</tr>
						</s:iterator>
					</s:if>	
				</tbody>
			</table>
		</div>

		<div>
			<s:hidden id="pageIndex" name="pageIndex" value="0"/>
			<s:hidden id="pageSize" name="pageSize" value="5" />
			
			<s:if test="consultingPagedList != null && consultingPagedList.pageCount > 0">
				<ul class="pagination">
					<li><input type="submit" value="First" class="btn-first-page" /></li>
					<li><input type="submit" value=&laquo; class="btn-previous-page" /></li>
					<s:if test="pageIndex >= 5">
						......
					</s:if>
					<s:iterator value="consultingPagedList.pageNumberList" status="stat" 
						begin="%{pageIndex < 5 ? 0 : pageIndex - 5 }"
						end="%{pageIndex > consultingPagedList.pageCount - 6 ? consultingPagedList.pageCount -1 : pageIndex +5 }">
						<li><input type="submit" value=<s:property/> class="btn-page" /></li>
					</s:iterator>
					<s:if test="pageIndex <= consultingPagedList.pageCount - 6">
						......
					</s:if>
					<li><input type="submit" value=&raquo;	class="btn-next-page" /></li>
					<li class="next"><input type="submit" value="Last" class="btn-last-page" /></li>
				</ul>
			
				<p>Displaying <s:property value="consultingPagedList.itemStart"/> - <s:property value="consultingPagedList.itemEnd"/> of <s:property value="consultingPagedList.totatlItemCount"/> item(s)</p> 
			</s:if>

		</div>
	</s:form>
	
	<script type="text/javascript">
	$(document).ready(function () {			
		$("ul.pagination > li > input").addClass("btn btn-default btn-sm");
		
		var pageIndex = '<s:property value="consultingPagedList.pageIndex"/>';
		var pageNumber = '<s:property value="consultingPagedList.pageNumber"/>';
		var pageCount = '<s:property value="consultingPagedList.pageCount"/>';
		
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
					
		// 注意: 在include此頁面的頁面的搜尋按鈕記得要加上id
	    $("#btn-search").click(function(){
	        $("#pageIndex").val(0);
	        return true;
	    });
		
		$("#btn-reset").click(function(){
			$("input.form-control:text").val("");
			$("select").prop('selectedIndex', 0);
		});
	});
	</script>
</body>
</html>

