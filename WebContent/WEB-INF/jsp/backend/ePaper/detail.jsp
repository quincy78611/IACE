<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="編輯管理  > 編輯"/>

<script>
$(document).ready(function() {
	$("#btn-back").click(function(){				
		$("#form-backToIndex").submit();
	});
});
</script>
</head>
<body>
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
		<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
	</div>		
	
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>