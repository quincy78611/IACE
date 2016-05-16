<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<div>
	<s:hidden id="pageIndex" name="pageIndex" />
	<s:hidden id="pageSize" name="pageSize" value="5" />
	
	<s:if test="patentPagedList != null && patentPagedList.pageCount > 0">
		<ul class="pagination">
			<li><input type="submit" value=&laquo; class="btn btn-default btn-sm btn-previous-page" /></li>
			
			<s:iterator value="patentPagedList.pageNumberList" status="stat">
				<li><input type="submit" value=<s:property/> class="btn-page" /></li>
			</s:iterator>
			
			<li><input type="submit" value=&raquo;	class="btn btn-default btn-sm btn-next-page" /></li>
		</ul>
	
		<p>Displaying <s:property value="patentPagedList.itemStart"/> - <s:property value="patentPagedList.itemEnd"/> of <s:property value="patentPagedList.totatlItemCount"/> item(s)</p> 
	</s:if>
</div>


<script type="text/javascript">
	$(document).ready(function () {
		$("ul.pagination > li > input").addClass("btn btn-default btn-sm");
		
		var pageIndex = <s:property value="patentPagedList.pageIndex"/>;
		var pageCount = <s:property value="patentPagedList.pageCount"/>;
		
		$("ul > li > input.btn-page").click(function() {
			$("#pageIndex").val($(this).attr("value") - 1);
			return true;
		});
		$("ul.pagination > li > input.btn-page").eq(pageIndex).addClass("active");
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