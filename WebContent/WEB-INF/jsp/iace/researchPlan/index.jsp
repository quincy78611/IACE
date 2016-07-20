<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">		
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
	<h2 class="itemTitle">編輯管理</h2>
	<s:form action="index" method="post" validate="true" >
		<div class="">
			<ul>
				<li class="third">
					<s:textfield placeholder="計畫編號" name="searchCondition.planNo" maxlength="100" cssClass="form-control" />
				</li>
				<li class="third">
					<s:textfield placeholder="計畫名稱" name="searchCondition.planName" maxlength="4000" cssClass="form-control" />
				</li>
				<li class="third">
					<s:select name="searchCondition.grbDomainCode" list="optionGrbDomainList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部研究領域"/>
				</li>
				<li class="third">
					<s:textfield placeholder="計畫主持人" name="searchCondition.manager" maxlength="100" cssClass="form-control" />
				</li>
				<li class="third">
					<s:textfield placeholder="計畫關鍵字" name="searchCondition.keyword" maxlength="4000" cssClass="form-control" />
				</li>
				<li class="third">
					<s:textfield placeholder="技術名稱" name="searchCondition.rndResultName" maxlength="4000" cssClass="form-control" />
				</li>
				<li class="third">
					<s:select name="searchCondition.trlCode" list="optionTrlList" listKey="code" listValue="%{code +'-'+ name}" headerKey="" headerValue="全部計畫發展階段"/>
				</li>
				<li class="third">
					<s:textfield placeholder="年度" name="searchCondition.year" maxlength="4" cssClass="form-control" />
				</li>
				<li class="third">
					<input type="submit" value="查詢" class="redBtn" id="btn-search"/>
					<input type="button" value="清除" class="grayBtn" id="btn-reset"/>
				</li>	
			</ul>
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
								<%-- <s:property value="id" /> --%>
							</td>						
							<td><s:property value="year" /></td>
							<td><s:property value="planNo" /></td>
							<td><s:property value="name" /></td>
							<td><s:property value="manager" /></td>
							<td style="display:none;"><!--  設定「研究領域」的title -->
								<s:set var="grbDomainsTitle" value="''"/>
								<s:iterator value="grbDomains" status="stat">
									<s:set var="grbDomainsTitle" value='%{#grbDomainsTitle+showString+"\r\n"}'/>
								</s:iterator>
							</td>
							<td title="<s:property value="grbDomainsTitle"/>">
								<s:iterator value="grbDomains" status="stat">
									<p><s:property value="name" /></p>
								</s:iterator>
							</td>
							<td title="<s:property value="trl.showString"/>">
								<s:property value="trl.code" />
							</td>
							<td><s:property value="projkey" /></td>
							<td>
								<a href="<s:url value="%{'http://grbsearch.stpi.narl.org.tw/GRB_Search/grb/show_doc.jsp?id='+grb05Id}"/>" target="_blank">連結</a>
							</td>															
							<td>							
								<s:url value="update.action" var="updateUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
								</s:url>
								<input type="button" class="btn-func btn-edit" value="編輯" 
									onclick="window.location.href='<s:property value="#updateUrlTag" />'" />
							</td>
						</tr>
					</s:iterator>
				</s:if>	
			</table>
		</div>

		<div class="page">
			<s:hidden id="pageIndex" name="searchCondition.pageIndex" value="0"/>
			<s:hidden id="pageSize" name="searchCondition.pageSize" value="20" />
			
			<s:set var="pgList" value="researchPlanPagedList"/>
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

