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
				<s:textfield placeholder="計畫編號" name="searchCondition.planNo" maxlength="100" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<s:textfield placeholder="計畫名稱" name="searchCondition.planName" maxlength="4000" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<s:select name="searchCondition.grbDomainCode" list="optionGrbDomainList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部研究領域"/>
			</div>
			<div class="col-md-3">
				<s:textfield placeholder="計畫主持人" name="searchCondition.manager" maxlength="100" cssClass="form-control" />
			</div>			
			<div class="col-md-3">
				<s:textfield placeholder="計畫關鍵字" name="searchCondition.keyword" maxlength="4000" cssClass="form-control" />
			</div>	
			<div class="col-md-3">
				<s:textfield placeholder="技術名稱" name="searchCondition.rndResultName" maxlength="4000" cssClass="form-control" />
			</div>
			<div class="col-md-3">
				<s:select name="searchCondition.trlCode" list="optionTrlList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部計畫發展階段"/>
			</div>
	
			<div class="col-md-1">
				<s:textfield placeholder="年度" name="searchCondition.year" maxlength="4" cssClass="form-control" />
			</div>
			
			<div class="col-md-2">			
				<input type="submit" value="查詢" class="btn btn-info" id="btn-search"/>
				<input type="button" value="重設" class="btn btn-warning" id="btn-reset"/>
			</div>
		</div>
		<p>
<!-- 		<div class=""> -->
<%-- 			<a class="btn btn-primary" href="<s:url value="/iace/researchPlan/create"/>">新增資料</a> --%>
<!-- 		</div>	 -->
<!-- 		<p> -->
		<div class="">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>No.</th>
						<th>年度</th>
						<th>計畫編號</th>
						<th>計畫名稱</th>
						<th>主持人</th>
						<th>研究領域</th>
						<th>計畫發展階段</th>
						<th>GRB計畫編號</th>
						<th>成果報告全文連結</th>
<!-- 						<th>技術名稱</th> -->
						<th></th>
					</tr>
				</thead>
				<tbody>
					<s:if test="researchPlanPagedList != null">
						<s:iterator value="researchPlanPagedList.list" status="stat">
							<tr>
								<td>
									<s:property value="%{researchPlanPagedList.itemStart + #stat.count -1}" />
<%-- 									<s:property value="id" /> --%>
								</td>						
								<td><s:property value="year" /></td>
								<td><s:property value="planNo" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="manager" /></td>
								<td>
									<s:iterator value="grbDomains" status="stat">
										<s:property value="name" /><p>
									</s:iterator>
								</td>
								<td><s:property value="trl.code" /></td>
								<td><s:property value="projkey" /></td>
								<td>
									<a href="<s:url value="%{'http://grbsearch.stpi.narl.org.tw/GRB_Search/grb/show_doc.jsp?id='+grb05Id}"/>" target="_blank">連結</a>
								</td>
																
								<td class="col-md-2">
									<s:url value="showDetail.action" var="detailUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-info" value="檢視" 
										onclick="window.location.href='<s:property value="detailUrlTag" />'" />
								
									<s:url value="update.action" var="updateUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-info" value="編輯" 
										onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
										
									<s:url value="delete.action" var="deleteUrlTag">
										<s:param name="id" value="id" />
									</s:url>
									<input type="button" class="btn-danger" value="刪除" 
										onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />								
								
<%-- 									<s:property value="ver" /> --%>
								</td>
							</tr>
						</s:iterator>
					</s:if>	
				</tbody>
			</table>
		</div>

		<div>
			<s:hidden id="pageIndex" name="searchCondition.pageIndex" value="0"/>
			<s:hidden id="pageSize" name="searchCondition.pageSize" value="5" />
			
			<s:if test="researchPlanPagedList != null && researchPlanPagedList.pageCount > 0">
				<ul class="pagination">
					<li><input type="submit" value="First" class="btn-first-page" /></li>
					<li><input type="submit" value=&laquo; class="btn-previous-page" /></li>
					<s:if test="searchCondition.pageIndex >= 5">
						......
					</s:if>
					<s:iterator value="researchPlanPagedList.pageNumberList" status="stat" 
						begin="%{searchCondition.pageIndex < 5 ? 0 : searchCondition.pageIndex - 5 }"
						end="%{searchCondition.pageIndex > researchPlanPagedList.pageCount - 6 ? researchPlanPagedList.pageCount -1 : searchCondition.pageIndex +5 }">
						<li><input type="submit" value=<s:property/> class="btn-page" /></li>
					</s:iterator>
					<s:if test="searchCondition.pageIndex <= researchPlanPagedList.pageCount - 6">
						......
					</s:if>
					<li><input type="submit" value=&raquo;	class="btn-next-page" /></li>
					<li class="next"><input type="submit" value="Last" class="btn-last-page" /></li>
				</ul>
			
				<p>Displaying <s:property value="researchPlanPagedList.itemStart"/> - <s:property value="researchPlanPagedList.itemEnd"/> of <s:property value="researchPlanPagedList.totatlItemCount"/> item(s)</p> 
			</s:if>
		</div>
	</s:form>
	
	<script type="text/javascript">
		$("#btn-reset").click(function(){
			$("input.form-control:text").val("");
			$("select").prop('selectedIndex', 0);
		});
		
		
		$(document).ready(function () {			
 			$("ul.pagination > li > input").addClass("btn btn-default btn-sm");
			
			var pageIndex = '<s:property value="researchPlanPagedList.pageIndex"/>';
			var pageNumber = '<s:property value="researchPlanPagedList.pageNumber"/>';
			var pageCount = '<s:property value="researchPlanPagedList.pageCount"/>';
			
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
		});
	</script>
</body>
</html>

