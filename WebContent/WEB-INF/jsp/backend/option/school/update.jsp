<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		addSearchConditionHiddenToForm();
		
		$("#btn-back").click(function(){				
			$("#form-backToIndex").submit();
		});
	});
</script>
<script>
	function addSearchConditionHiddenToForm() {
		$("#form-backToIndex input[type=hidden]").each(function(index){
			$("#form-update").append($(this).clone());
		});
	}
</script>
</head>
<body>
	<h2 class="itemTitle">編輯</h2>

	<s:form action="updateSubmit" method="post" validate="true" id="form-update">
		<s:hidden name="option.id" />
		<s:hidden name="option.isValid" />
		<s:hidden name="option.createTime" />
		<s:hidden name="option.createUser" />
		<s:hidden name="option.updateTime" />
		<s:hidden name="option.updateUser" />
		<s:hidden name="option.ver" />	
		
		<ul>
			<li class="half">
				<b>代碼</b>
				<s:textfield name="option.code"/>
			</li>			
			<li class="half">
				<b>名稱</b>
				<s:textfield name="option.name"/>
			</li>			
		</ul>
		<s:submit cssClass="btn btn-default redBtn" value="儲存" />	
		<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>		
	</s:form>
	
	<form action="index" method="post" id="form-backToIndex">
		<s:hidden name="searchCondition.searchText"/>
		<s:hidden name="searchCondition.pageIndex"/>
		<s:hidden name="searchCondition.pageSize"/>
	</form>	
</body>
</html>