<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		$("#btn-back").click(function(){				
			$("#form-backToIndex").submit();
		});	
	});
</script>
<meta name="funcPathText" content="編輯管理 > 檢視"/>
</head>
<body>
	<ul>
		<li class="half">
			<b>帳號*</b>
			<div class="border-text">
				<s:property value="member.account"/>
			</div>
		</li>
		<li class="half">
			<b>密碼*</b>
			<div class="border-text">
				<s:property value="member.password"/>
			</div>
		</li>
		<div class="clear"></div>
		<li class="half">
			<b>姓名*</b>
			<div class="border-text">
				<s:property value="member.name"/>
			</div>
		</li>
		<li class="half">
			<b>Email*</b>
			<div class="border-text">
				<s:property value="member.email"/>
			</div>
		</li>
		<div class="clear"></div>
		<li class="half">
			<b>公司/團體/學校</b>
			<div class="border-text">
				<s:property value="member.org"/>
			</div>
		</li>
		<li class="half">
			<b>部門/系所</b>
			<div class="border-text">
				<s:property value="member.dept"/>
			</div>
		</li>
		<div class="clear"></div>
		<li class="half">
			<b>行業別 </b>
			<s:select name="member.industry" list="industryList" listKey="code" listValue="name" headerKey="" headerValue="--請選擇--" disabled="true"/>
		</li>
		<li class="half">
			<b>產業別</b>
			<div class="border-text">
				<s:iterator value="member.optIndustryList" status="stat">
					<s:property value="%{name + '; '}"/>
				</s:iterator>
			</div>	
		</li>
		<div class="clear"></div>
		<li class="half">
			<b>職務別</b>
			<s:select name="member.jobType" list="jobTypeList" listKey="code" listValue="name" headerKey="" headerValue="--請選擇--" disabled="true"/>
		</li>
		<li class="half">
			<b>職稱</b>
			<div class="border-text">
				<s:property value="member.jobTitle"/>
			</div>
		</li>
		<div class="clear"></div>
		<li class="eighth">
			<b>郵遞區號</b>
			<div class="border-text">
				<s:property value="member.postalCode"/>
			</div>
		</li>
		<li class="sevenEighth">
			<b>地址</b>
			<div class="border-text">
				<s:property value="member.address"/>
			</div>
		</li>
		<div class="clear"></div>
		<li class="half">
			<b>電話</b>
			<div class="border-text">
				<s:property value="member.tel"/>
			</div>
		</li>			
		<li class="half">
			<b>手機</b>
			<div class="border-text">
				<s:property value="member.mobile"/>
			</div>
		</li>
		<div class="clear"></div>			
		<li class="half">
			<b>技術需求領域</b>
			<s:select name="member.optDomain.id" list="optDomainList" listKey="id" listValue="name" headerKey="" headerValue="--請選擇--" disabled="true"/>
		</li>
		<li class="half">
			<b>技術需求項目</b>
			<div class="border-text">
				<s:property value="member.neededTec"/>
			</div>
		</li>
		<div class="clear"></div>
		<li class="quarter">
			<b>使用狀態</b>
			<s:radio name="member.useStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" value="true" disabled="true"/>
		</li>			
	</ul>
	
	<div class="clear"></div>
	<div class="bottom-btn-block">
		<input type="button" class="grayBtn" id="btn-back" value="回列表頁"/>
	</div>
				
	<s:include value="./form-backToIndex.jsp" />
</body>
</html>