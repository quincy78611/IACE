<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<script>
	$(document).ready(function(){
		$("#left-categoryList-form .categoryList li").click(function(){
			var optionDomainId = $(this).find("input").val();
			$("#left-categoryList-form input[name='searchCondition.optionDomainId']").val(optionDomainId);
			$("#left-categoryList-form").submit();
		});
		
		{
			var optionDomainId = '<s:property value="searchCondition.optionDomainId"/>';
			$("#left-categoryList-form .categoryList li input[value='"+optionDomainId+"']").parents("li").addClass("active");
			$("#left-categoryList-form input[name='searchCondition.optionDomainId']").val(optionDomainId);
		}

	});
</script>

<s:form action="index" method="post" validate="true" id="left-categoryList-form">
	<s:hidden name="searchCondition.optionDomainId"/>
	<s:hidden id="pageIndex" name="searchCondition.pageIndex" value="0"/>
	<s:hidden id="pageSize" name="searchCondition.pageSize"/>
	
	<div>
		<ul class="list-group categoryList">
			<s:iterator value="optionDomainList" status="stat">
				<li class="list-group-item">
					<i class="fa fa-cube right10" aria-hidden="true"></i> 
					<s:property value="name"/>
					<input type="text" style="display:none;" value='<s:property value="id"/>'/>
				</li>
			</s:iterator>
		</ul>
	</div>
</s:form>

<s:url value="/f2/file/downloadFile"  var="urlTag" escapeAmp="false">
	<s:param name="downloadFileSubPath" value="%{'105年-企業技術需求訪談分析.pdf'}" />
	<s:param name="downloadFileName" value="%{'download.pdf'}" />
</s:url>
<a href="<s:property value="urlTag"/>">105年-企業技術需求訪談分析(下載)</a>	
