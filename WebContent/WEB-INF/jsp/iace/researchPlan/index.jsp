<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
</head>
<body>	
	<s:form action="index" method="post" validate="true" >
		<div id="searchZone">
			<ul>
				<li>
					<s:textfield placeholder="計畫編號" name="searchCondition.planNo" maxlength="100" cssClass="form-control" />
				</li>
				<li>
					<s:textfield placeholder="計畫名稱" name="searchCondition.planName" maxlength="4000" cssClass="form-control" />
				</li>
				<li>
					<s:select name="searchCondition.grbDomainCode" list="optionGrbDomainList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部研究領域"/>
				</li>
				<li>
					<s:textfield placeholder="計畫主持人" name="searchCondition.manager" maxlength="100" cssClass="form-control" />
				</li>
				<li>
					<s:textfield placeholder="計畫關鍵字" name="searchCondition.keyword" maxlength="4000" cssClass="form-control" />
				</li>
				<li>
					<s:textfield placeholder="技術名稱" name="searchCondition.rndResultName" maxlength="4000" cssClass="form-control" />
				</li>
				<li>
					<s:select name="searchCondition.trlCode" list="optionTrlList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部計畫發展階段"/>
				</li>
				<li>
					<s:textfield placeholder="年度" name="searchCondition.year" maxlength="4" cssClass="form-control" />
				</li>		
			</ul>
			<div class="send">
				<input type="submit" value="查詢" class="redBtn" id="btn-search"/>
				<br>
				<input type="button" value="清除" class="grayBtn" id="btn-reset"/>
			</div>
		</div>
		<div class="clear"></div>
		<div class="">
			<table width="100%">
				<tr>
					<th nowrap width="2%">No.</th>
					<th nowrap width="3%">年度</th>
					<th nowrap width="10%">計畫編號</th>
					<th nowrap width="">計畫名稱</th>
					<th nowrap width="5%">主持人</th>
					<th nowrap width="10%">研究領域</th>
					<th nowrap width="10%">計畫發展階段</th>
					<th nowrap width="10%">GRB計畫編號</th>
					<th nowrap width="5%">成果報告</th>
					<th nowrap width="5%">功能</th>
				</tr>
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
									<p><s:property value="name" /></p>
								</s:iterator>
							</td>
							<td><s:property value="trl.code" /></td>
							<td><s:property value="projkey" /></td>
							<td>
								<a href="<s:url value="%{'http://grbsearch.stpi.narl.org.tw/GRB_Search/grb/show_doc.jsp?id='+grb05Id}"/>" target="_blank">連結</a>
							</td>
															
							<td class="col-md-1">
								<s:url value="showDetail.action" var="detailUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
								</s:url>
								<input type="button" class="btn-info view" value="檢視" 
									onclick="window.location.href='<s:property value="detailUrlTag" />'" />
							
								<s:url value="update.action" var="updateUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
								</s:url>
								<input type="button" class="btn-info edit" value="編輯" 
									onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
									
								<s:url value="delete.action" var="deleteUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
								</s:url>
								<input type="button" class="btn-danger del" value="刪除" 
									onclick="window.location.href='<s:property value="#deleteUrlTag" />'" />								
							
<%-- 									<s:property value="ver" /> --%>
							</td>
						</tr>
					</s:iterator>
				</s:if>	
			</table>
		</div>

		<div class="page">
			<s:hidden id="pageIndex" name="searchCondition.pageIndex" value="0"/>
			<s:hidden id="pageSize" name="searchCondition.pageSize" value="5" />
			
			<s:if test="researchPlanPagedList != null && researchPlanPagedList.pageCount > 0">
				<ul class="pagination">
					<li><input type="submit" value="First" class="btn-first-page" /></li>
					<li><input type="submit" value=&laquo; class="btn-previous-page" /></li>
					<s:if test="searchCondition.pageIndex >= 5">
						<li><p>......</p></li>
					</s:if>
					<s:iterator value="researchPlanPagedList.pageNumberList" status="stat" 
						begin="%{searchCondition.pageIndex < 5 ? 0 : searchCondition.pageIndex - 5 }"
						end="%{searchCondition.pageIndex > researchPlanPagedList.pageCount - 6 ? researchPlanPagedList.pageCount -1 : searchCondition.pageIndex +5 }">
						<li><input type="submit" value=<s:property/> class="btn-page" /></li>
					</s:iterator>
					<s:if test="searchCondition.pageIndex <= researchPlanPagedList.pageCount - 6">
						<li><p>......</p></li>
					</s:if>
					<li><input type="submit" value=&raquo;	class="btn-next-page" /></li>
					<li class="next"><input type="submit" value="Last" class="btn-last-page" /></li>
					<li>				
						<%-- <p>Displaying <s:property value="researchPlanPagedList.itemStart"/> - <s:property value="researchPlanPagedList.itemEnd"/> of <s:property value="researchPlanPagedList.totatlItemCount"/> item(s)</p> --%> 				
						<p>共 <s:property value="researchPlanPagedList.totatlItemCount"/> 筆資料</p>
					</li>
				</ul>

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
			
			var pageIndex = parseInt('<s:property value="researchPlanPagedList.pageIndex"/>');
			var pageNumber = parseInt('<s:property value="researchPlanPagedList.pageNumber"/>');
			var pageCount = parseInt('<s:property value="researchPlanPagedList.pageCount"/>');
			
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

