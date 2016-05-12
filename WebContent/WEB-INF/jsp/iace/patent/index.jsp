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
				<s:textfield placeholder="專利名稱" name="searchPatentName" maxlength="300" cssClass="form-control" />
			</div>
			<div class="col-md-2">
				<s:textfield placeholder="申請號" name="searchAppliactionNo" maxlength="100" cssClass="form-control" />
			</div>
			<div class="col-md-2">
				<s:select name="searchCountry" list="optionCountryList" listKey="code" listValue="%{code+' - '+name}" headerKey="" headerValue="請選擇申請國" />
			</div>	
			<div class="col-md-3">
				<s:select name="searchTechField" list="techFieldList" listKey="id" listValue="name" headerKey="-1" headerValue="請選擇專利技術領域" />
			</div>		
			<div class="col-md-2">			
				<input type="submit" value="查詢" class="btn btn-info" id="btn-search"/>
				<input type="button" value="重設" class="btn btn-warning" id="btn-reset"/>
			</div>
		</div>
		<p>
		<div class="">
			<a class="btn btn-primary" href="<s:url value="/iace/patent/create"/>">新增專利資料</a>
		</div>	
		<p>
		<div class="">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>No.</th>
						<th>專利名稱</th>
						<th>專利權人</th>
						<th>申請號</th>
						<th>申請日</th>
						<th>狀態</th>
						<th>專利技術領域</th>
						<th>圖示</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<s:if test="patentPagedList != null">
						<s:iterator value="patentPagedList.list" status="stat">
							<tr>
								<td>
									<s:property value="%{patentPagedList.itemStart + #stat.count -1}" />
									<%-- <s:property value="id" /> --%>
								</td>						
								<td><s:property value="name" /></td>
								<td><s:property value="assignee" /></td>
								<td><s:property value="appliactionNo" /></td>
								<td><s:date name="applicationDate" format="yyyy/M/d"/></td>
								<td><s:property value="patentStatus" /></td>
								<td><s:property value="techField.name" /></td>
								<td><s:property value="importantPicturePath" /></td>
								
								<td>
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
								</td>
							</tr>
						</s:iterator>
					</s:if>	
				</tbody>
			</table>
		</div>

    	<s:include value="/WEB-INF/jsp/common/pagination.jsp" />
	</s:form>
	
	<script type="text/javascript">
		$(document).ready(function () {
			$("#btn-reset").click(function(){
				$("input.form-control:text").val("");
				$("select").prop('selectedIndex', 0);
			});
		});
	</script>
</body>
</html>

