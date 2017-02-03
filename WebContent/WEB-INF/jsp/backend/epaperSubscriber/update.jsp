<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="編輯管理  > 編輯"/>

<script>
$(document).ready(function() {
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
	<s:form action="updateSubmit" method="post" validate="true" id="form-update">
		<s:hidden name="subscriber.id"/>
		<s:hidden name="subscriber.isValid"/>
		<s:hidden name="subscriber.createTime"/>
		<s:hidden name="subscriber.createUser"/>
		<s:hidden name="subscriber.updateTime"/>
		<s:hidden name="subscriber.updateUser"/>
		<s:hidden name="subscriber.ver"/>
		<ul>						
			<li class="half">
				<b>姓名</b>
				<s:textfield name="subscriber.name" maxlength="100"/>
			</li>
			<li class="half">
				<b>分類</b>
				<s:radio name="subscriber.isSubscribe" list='#{"true":"訂閱", "false":"退閱"}' cssClass="horizontalList" />
			</li>
			<div class="clear"></div>		
			<li class="half">
				<b>電話</b>
				<s:textfield name="subscriber.tel" maxlength="50"/>
			</li>
			<li class="half">
				<b>email</b>
				<s:textfield name="subscriber.email" maxlength="200"/>
			</li>			
		</ul>
		<div class="clear"></div>
		
		<div class="bottom-btn-block">
			<s:submit cssClass="redBtn" value="儲存" />
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>		
	</s:form>
	
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>