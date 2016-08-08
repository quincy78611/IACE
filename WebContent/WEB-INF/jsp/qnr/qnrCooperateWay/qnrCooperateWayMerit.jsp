<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script>
	$(document).ready(function() {
		pdplSetting();
		hideOrShowApplicantData();
	});
</script>
<script>
	function pdplSetting() {
		$("input[type=checkbox][name='qnrCoopereateWay.aggreePDPL']").click(function(){
			hideOrShowApplicantData();			
		});
	}
	
	function hideOrShowApplicantData() {
		if($("input[type=checkbox][name='qnrCoopereateWay.aggreePDPL']").prop("checked")) {
			$("#li-name").show();
			$("#li-applicantId").show();
			$("#li-email").show();
			$("#li-address").show();
		} else {
			$("#li-name").hide();
			$("#li-applicantId").hide();
			$("#li-email").hide();
			$("#li-address").hide();
			
			$("#li-name input").val("");
			$("#li-applicantId input").val("");
			$("#li-email input").val("");
			$("#li-address input").val("");
		}
	}
</script>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/subTitle-qnrCooperateWay.css"/>" />
<style>
th {
	border: solid 1px;
	white-space: nowrap;
}

.applicant-data {
	overflow-y: auto;
	padding: 15px;
	margin-bottom: 23px;
	border: 1px solid transparent;
	border-color: #000000;
}
</style>
</head>
<body>
	<h2 class="subTitle-qnrCooperateWay">《第四部份》學校於2013~2015年期間研發產出及產學合作績效</h2>
	<h3>【說明】請您將貴校於西元2013~2015年期間研發產出及產學合作績效，填寫下表：</h3>
	<s:form action="fillInQnrPart4Submit" method="post" validate="true">
		<s:hidden name="qnrCooperateWayId"/>
	
		<h3>(一) 研發合作與智財推廣</h3>
		<table width="100%">
			<tr class="text-align-center">
				<th>項目</th>
				<th colspan="2">企業委託研究</th>
				<th colspan="2">獲證專利</th>
				<th colspan="2">專利授權</th>
				<th colspan="3">技術移轉</th>
			</tr>
			<tr class="text-align-center">
				<th>年度</th>
				<th>計畫件數</th>
				<th>總經費(NT$萬)</th>
				<th>獲證件數(國內)</th>
				<th>獲證件數(國外)</th>
				<th>廠商家數</th>
				<th>授權收入</th>
				<th>件數</th>
				<th>廠商家數</th>
				<th>技轉收入</th>
			</tr>
			<s:iterator value="qnrCooperateWayMerits" status="stat">
				<tr>
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].year'}" />
					
					<td><s:property value="year" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_1_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_1_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_2_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_2_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_3_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_3_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_4_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_4_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_4_3'}" autocomplete="off" /></td>
				</tr>
			</s:iterator>
		</table>

		<h3>(二) 發展資源及新創事業</h3>
		<table width="100%">
			<tr class="text-align-center">
				<th></th>
				<th colspan="2">研發活動主管單位</th>
				<th colspan="2">產學合作主管單位</th>
				<th colspan="2">技轉中心</th>
				<th colspan="5">育成中心</th>
			</tr>
			<tr class="text-align-center">
				<th>年度</th>
				<th>預算</th>
				<th>年平均受雇<br>全職人數</th>
				<th>預算</th>
				<th>年平均受雇<br>全職人數</th>
				<th>政府補<br>助經費</th>
				<th>受雇人數</th>
				<th>政府補<br>助經費</th>
				<th>受雇人數</th>
				<th>進駐家數</th>
				<th>育成中心<br>回饋收入</th>
				<th>本校師生創<br>業進駐家數</th>
			</tr>
			<s:iterator value="qnrCooperateWayMerits" status="stat">
				<tr>
					<td><s:property value="year" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_1_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_1_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_2_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_2_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_3_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_3_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_3'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_4'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_5'}" autocomplete="off" /></td>
				</tr>
			</s:iterator>
		</table>
		
		<div class="applicant-data">
			<span>【同意事項】</span><br>
			<span>
				本人已閱讀並瞭解上述告知事項，並同意科技部、工研院、「運用法人鏈結產學合作計畫」網站及「鏈結產學媒合平台」(Industry-Academia Catalyst E-Platform, I-ACE) 網站在符合上述告知事項範圍內，蒐集、處理及利用本人的個資。本項同意得以電子文件方式表達。
			</span>			
			<ul>
				<li class="all">
					<s:checkbox label="同意" name="qnrCoopereateWay.aggreePDPL"/>
				</li>
				<li class="quarter" id="li-name">
					<b>姓名</b>				
					<s:textfield name="qnrCoopereateWay.name"/>
				</li>
				<li class="quarter" id="li-applicantId">
					<b>身份證字號</b>
					<s:textfield name="qnrCoopereateWay.applicantId"/>
				</li>
				<li class="half" id="li-email">
					<b>Email</b>
					<s:textfield name="qnrCoopereateWay.email" type="email"/>
				</li>	
				<li class="all" id="li-address">
					<b>地址</b>				
					<s:textfield name="qnrCoopereateWay.address"/>
				</li>			
			</ul>			
		</div>
		
		<s:submit cssClass="redBtn" value="送出" />
	</s:form>

</body>
</html>