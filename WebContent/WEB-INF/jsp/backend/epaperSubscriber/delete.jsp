<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="編輯管理  > 刪除"/>

<script>
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
			$("#form-delete").append($(this).clone());
		});
	}
</script>
</head>
<body>
	<s:form action="deleteSubmit" method="post" validate="true" id="form-delete">
		<s:hidden name="id" />
		<ul>						
			<li class="half">
				<b>姓名</b>
				<div class="border-text">
					<s:property value="subscriber.name"/>
				</div>
			</li>
			<li class="half">
				<b>分類</b>
				<s:radio name="subscriber.isSubscribe" list='#{"true":"訂閱", "false":"退閱"}' cssClass="horizontalList" disabled="true"/>
			</li>
			<div class="clear"></div>		
			<li class="half">
				<b>電話</b>
				<div class="border-text">
					<s:property value="subscriber.tel"/>
				</div>			
			</li>
			<li class="half">
				<b>email</b>
				<div class="border-text">
					<s:property value="subscriber.email"/>
				</div>			
			</li>			
		</ul>
		<div class="clear"></div>
		
		<div class="bottom-btn-block">
			<s:submit cssClass="btn btn-info redBtn" value="確定" />
			<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
		</div>		
	</s:form>
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>