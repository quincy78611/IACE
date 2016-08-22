<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script>
	$(document).ready(function() {
		backToPreviousPage();
		pdplSetting();
		hideOrShowApplicantData();
	});
</script>
<script type="text/javascript">
	function backToPreviousPage() {
		$("#btn-backToPreviousPage").click(function() {
			$("form").attr("action", "backToQnrPart0To3");
			$("form").submit();
		});
	}
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
<link rel="stylesheet" type="text/css" href="<s:url value="/css/qnrCooperateWay.css"/>" />
</head>
<body>
	<h2 class="subTitle-qnrCooperateWay">《第四部份》</h2>
	<div class="top-desc">【說明】請您將貴校於西元2013~2015年期間研發產出及產學合作績效，填寫下表：</div>
	<s:form action="fillInQnrPart4Submit" method="post" validate="true">
		<s:hidden name="schoolId" />
<%-- 		<s:hidden name="qnrCooperateWayId"/> --%>
	
		<table class="table-qnrCooperateWay">
			<tr class="text-align-center">
				<th>項目</th>
				<th>參與人員</th>
				<th colspan="2">企業委託研究</th>
				<th colspan="4">技術移轉</th>
				<th>創新創業</th>
			</tr>
			<tr class="text-align-center">
				<th>年度</th>
				<th>參與產學合<br/>作教師人數</th>
				<th>簽約計畫<br/>件數</th>
				<th>簽約總經費<br/>(NT$萬)</th>
				<th>專利授權<br/>件數</th>
				<th>專利授權<br/>收入(NT$萬)</th>
				<th>非專利授權<br/>之技轉件數</th>
				<th>非專利授權之<br/>技轉收入(NT$萬)</th>
				<th>新增師生新<br/>創事業家數</th>
			</tr>
			<s:iterator value="qnrCooperateWayMerits" status="stat">
				<tr>
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].year'}" />
					
					<td><s:property value="year" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c1'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c2'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c3'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c4'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c5'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c6'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c7'}" autocomplete="off" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].c8'}" autocomplete="off" /></td>
				</tr>
			</s:iterator>
		</table>
		
		<div style="margin-bottom: 23px; font-size:1.15em;">
			<span style="color:blue;">【備註】技術移轉分為「專利授權」與「非專利授權」，非專利授權收入包括資料庫（data base）、服務（service）或是技術協助（technical assistance）等技術服務收入。</span>
			<br/>
			<span style="color:red;">【備註】無相關數據填答者請填入「N/A」</span>
			<br/><br/>
			<span>【本問卷到此結束，敬請再次確認是否有漏答，謝謝!】</span>
		</div>
		
		<div class="clear"></div>
		
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
		
		<input type="button" id="btn-backToPreviousPage" class="redBtn" value="上一頁"/>
		<s:submit cssClass="redBtn" value="送出" />
	</s:form>

</body>
</html>