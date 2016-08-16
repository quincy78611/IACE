<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script>
	$(document).ready(function() {
		switchPartSetting();

		$("input[type=submit]").click(function(event) {
			event.preventDefault();
			if ($("input[type='radio']:checked").length != (3 + 15 + 10 + 14)) {
				alert("尚有題目未回答!");
				return false;
			} else {
				$("form").submit();
				return true;
			}
		});
		
// 		$("input[type=radio][value=1]").prop("checked", true); //TODO 上版前要註解掉
	});
</script>
<script>
	function switchPartSetting() {
		hideAllPart();
		$("#div-part0").show();

		$("input[type=button].goToPart0").click(function() {
			hideAllPart();
			$("#div-part0").show();
		});
		$("input[type=button].goToPart1").click(function() {
			if ($("#div-part0 input[type='radio']:checked").length != 3) {
				alert("尚有題目未回答!");
			} else {
				hideAllPart();
				$("#div-part1").show();
			}
		});
		$("input[type=button].goToPart2").click(function() {
			if ($("#div-part1 input[type='radio']:checked").length != 15) {
				alert("尚有題目未回答!");
			} else {
				hideAllPart();
				$("#div-part2").show();
			}
		});
		$("input[type=button].goToPart3").click(function() {
			if ($("#div-part2 input[type='radio']:checked").length != 10) {
				alert("尚有題目未回答!");
			} else {
				hideAllPart();
				$("#div-part3").show();
			}
		});
	}

	function hideAllPart() {
		$("#div-part0").hide();
		$("#div-part1").hide();
		$("#div-part2").hide();
		$("#div-part3").hide();
	}
</script>
<style>
table.table-qnrCooperateWay th {
	border: solid 1px;
	white-space: nowrap;
 	font-size: 0.9em;
}
table.table-qnrCooperateWay td {
	font-size: 1em;
}

</style>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/subTitle-qnrCooperateWay.css"/>" />
</head>
<body>
	<s:form action="fillInQnrPart0To3Submit" method="post" validate="true">
		<s:hidden name="schoolId" />
		<s:hidden name="qnrCoopereateWay.aggreePDPL" />
		<s:hidden name="qnrCoopereateWay.name" />
		<s:hidden name="qnrCoopereateWay.applicantId" />
		<s:hidden name="qnrCoopereateWay.email" />
		<s:hidden name="qnrCoopereateWay.address" />
		<div id="div-part0">
			<img src="<s:url value="/images/qnrCooperateWayDesc.jpg"/>" style="max-width: 800px; max-height: 800px;"> <br /> <br /> <br />
			<h2 class="itemTitle Down">基本資料</h2>
			<ul>
				<li class="all">1. 請問您目前任職單位職稱為： <s:radio name="qnrCoopereateWay.q0_1" list="#{'1':'產學合作業務一級主管（研究發展處研發長、技術合作處技合長、產學合作處產學長或產學合作中心單位主管）', '2':'技轉中心單位主管', '3':'育成中心單位主管'}" cssClass="horizontalList"/>
				</li>
				<li class="all">2. 請問您服務於學術界年資合計： <s:radio name="qnrCoopereateWay.q0_2" list="#{'1':'5年以下', '2':'5-10年', '3':'11-15年', '4':'16-20年', '5':'21-25年', '6':'26-30年', '7':'30年以上' }" cssClass="horizontalList"/>
				</li>
				<li class="all">3. 請問您參與產學合作活動/計畫相關資歷合計： <s:radio name="qnrCoopereateWay.q0_3" list="#{'1':'5年以下', '2':'5-10年', '3':'11-15年', '4':'16-20年', '5':'20年以上' }" cssClass="horizontalList"/>
				</li>
			</ul>
			<input type="button" class="goToPart1 redBtn" value="下一頁">
		</div>
		<div class="clear"></div>
		<div id="div-part1">
			<h2 class="subTitle-qnrCooperateWay Down">《第一部份》</h2>
			<div style="background-color: #056690; color: white; padding: 20px; border: 1px solid">
				<p>【說明】您目前服務的學校對於推動新興特色的重視程度，如產學合作、創業型大學、教學卓越大學等，請勾選適當的程度，皆為單選題。</p>
			</div>

			<table width="100%" class="table-qnrCooperateWay">
				<tr class="text-align-center">
					<th></th>
					<th></th>
					<th nowrap>非常同意</th>
					<th nowrap>同意</th>
					<th nowrap>稍微同意</th>
					<th nowrap>普通</th>
					<th nowrap>稍微不同意</th>
					<th nowrap>不同意</th>
					<th nowrap>非常不同意</th>
				</tr>
				<tr>
					<td>1</td>
					<td>學校一向積極發展新興特色以回應外界期待。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_1" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_1" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_1" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_1" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_1" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_1" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_1" value="1" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td>學校願意承擔發展新興特色所需之風險。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_2" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_2" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_2" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_2" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_2" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_2" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_2" value="1" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td>為發展學校新興特色，大刀闊斧的改革是必需的。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_3" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_3" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_3" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_3" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_3" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_3" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_3" value="1" /></td>
				</tr>
				<tr>
					<td>4</td>
					<td>學校一級主管非常重視校務革新工作與樹立典範。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_4" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_4" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_4" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_4" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_4" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_4" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_4" value="1" /></td>
				</tr>
				<tr>
					<td>5</td>
					<td>學校總是積極導入改善與創新措施。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_5" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_5" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_5" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_5" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_5" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_5" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_5" value="1" /></td>
				</tr>
				<tr>
					<td>6</td>
					<td>學校總是量身訂做適合的規章與實施方法。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_6" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_6" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_6" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_6" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_6" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_6" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_6" value="1" /></td>
				</tr>
				<tr>
					<td>7</td>
					<td>為發展新興特色，相較其他學校本校推行許多校務革新工作。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_7" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_7" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_7" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_7" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_7" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_7" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_7" value="1" /></td>
				</tr>
				<tr>
					<td>8</td>
					<td>學校對於校務革新總能領先其他學校。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_8" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_8" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_8" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_8" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_8" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_8" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_8" value="1" /></td>
				</tr>
				<tr>
					<td>9</td>
					<td>學校的許多革新作法，都能引起其他學校的效法與回應。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_9" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_9" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_9" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_9" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_9" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_9" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_9" value="1" /></td>
				</tr>
				<tr>
					<td>10</td>
					<td>面對其他學校的競爭，學校總是主動出擊。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_10" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_10" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_10" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_10" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_10" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_10" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_10" value="1" /></td>
				</tr>
				<tr>
					<td>11</td>
					<td>面對其他學校的競爭，學校常常是第一個導入革新措施。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_11" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_11" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_11" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_11" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_11" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_11" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_11" value="1" /></td>
				</tr>
				<tr>
					<td>12</td>
					<td>面對其他學校的競爭，本校會積極發展競爭優勢，建立模仿障礙。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_12" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_12" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_12" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_12" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_12" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_12" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_12" value="1" /></td>
				</tr>
				<tr>
					<td>13</td>
					<td>學校給予推動單位很高的自由度。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_13" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_13" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_13" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_13" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_13" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_13" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_13" value="1" /></td>
				</tr>
				<tr>
					<td>14</td>
					<td>學校注重對推動單位員工充份授權。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_14" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_14" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_14" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_14" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_14" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_14" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_14" value="1" /></td>
				</tr>
				<tr>
					<td>15</td>
					<td>教職員工在新校務發展扮演關鍵的決策角色。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_15" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_15" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_15" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_15" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_15" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_15" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q1_15" value="1" /></td>
				</tr>
			</table>

			<!-- 			<input type="button" class="goToPart0 redBtn" value="上一頁">  -->
			<input type="button" class="goToPart2 redBtn" value="下一頁">
		</div>
		<div class="clear"></div>
		<div id="div-part2">
			<h2 class="subTitle-qnrCooperateWay Down">《第二部份》</h2>
			<div style="background-color: #056690; color: white; padding: 20px; border: 1px solid">
				<p>【說明】您目前服務的學校針對研發活動及產學合作提供之組織設計與管理，請勾選適當的程度，皆為單選題。</p>
			</div>
			<table width="100%" class="table-qnrCooperateWay">
				<tr class="text-align-center">
					<th></th>
					<th></th>
					<th nowrap>非常同意</th>
					<th nowrap>同意</th>
					<th nowrap>稍微同意</th>
					<th nowrap>普通</th>
					<th nowrap>稍微不同意</th>
					<th nowrap>不同意</th>
					<th nowrap>非常不同意</th>
				</tr>
				<tr>
					<td>1</td>
					<td>針對外界不同的需求(例：教學、研究、產學合作、技術移轉等)，學校會有不同的行政單位來負責。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_1" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_1" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_1" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_1" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_1" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_1" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_1" value="1" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td>學校原有任務(例：教學、研究發展)與研究商品化活動在校內是被分開的，各自有自己的運作架構。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_2" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_2" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_2" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_2" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_2" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_2" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_2" value="1" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td>學校各行政單位都有自己的特定功能與服務對象與服務事項。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_3" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_3" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_3" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_3" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_3" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_3" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_3" value="1" /></td>
				</tr>
				<tr>
					<td>4</td>
					<td>學校任務從屬與員工單位歸屬是非常明確被界定清楚。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_4" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_4" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_4" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_4" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_4" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_4" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_4" value="1" /></td>
				</tr>
				<tr>
					<td>5</td>
					<td>學校行政體系運作可與學校所有目標發展連動。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_5" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_5" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_5" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_5" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_5" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_5" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_5" value="1" /></td>
				</tr>
				<tr>
					<td>6</td>
					<td>學校行政體系運作可以避免將資源浪費在無潛在效益的活動。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_6" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_6" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_6" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_6" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_6" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_6" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_6" value="1" /></td>
				</tr>
				<tr>
					<td>7</td>
					<td>學校會給予員工明確的目標，避免他們的工作陷入多目標衝突。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_7" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_7" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_7" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_7" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_7" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_7" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_7" value="1" /></td>
				</tr>
				<tr>
					<td>8</td>
					<td>學校行政體系鼓勵大家挑戰既有傳統與作法。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_8" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_8" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_8" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_8" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_8" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_8" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_8" value="1" /></td>
				</tr>
				<tr>
					<td>9</td>
					<td>學校行政體系具有彈性，足以快速回應外界各種需求的改變。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_9" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_9" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_9" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_9" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_9" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_9" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_9" value="1" /></td>
				</tr>
				<tr>
					<td>10</td>
					<td>學校行政體系可以快速調整，藉以回應學校發展方向的改變。</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_10" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_10" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_10" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_10" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_10" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_10" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q2_10" value="1" /></td>
				</tr>
			</table>

<!-- 			<input type="button" class="goToPart1 redBtn" value="上一頁">  -->
			<input type="button" class="goToPart3 redBtn" value="下一頁">
		</div>
		<div class="clear"></div>
		<div id="div-part3">
			<h2 class="subTitle-qnrCooperateWay Down">《第三部份》</h2>
			<div style="background-color: #056690; color: white; padding: 20px; border: 1px solid">
				<p>【說明】您目前服務的學校為推動研究商品化活動(專利申請、產學合作、技術移轉、新創事業等)所提供之相關輔導與激勵措施，請勾選適當的程度，皆為單選題。</p>
			</div>
			<table width="100%" class="table-qnrCooperateWay">
				<tr class="text-align-center">
					<th></th>
					<th></th>
					<th nowrap>非常同意</th>
					<th nowrap>同意</th>
					<th nowrap>稍微同意</th>
					<th nowrap>普通</th>
					<th nowrap>稍微不同意</th>
					<th nowrap>不同意</th>
					<th nowrap>非常不同意</th>
				</tr>
				<tr>
					<td>1</td>
					<td>學校提供研究商品化活動所需資源(如設備、空間與經費)</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_1" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_1" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_1" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_1" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_1" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_1" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_1" value="1" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td>學校擁有豐沛人力資本發展研究商品化活動</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_2" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_2" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_2" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_2" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_2" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_2" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_2" value="1" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td>學校設有專責單位與人員負責協助研究商品化活動</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_3" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_3" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_3" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_3" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_3" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_3" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_3" value="1" /></td>
				</tr>
				<tr>
					<td>4</td>
					<td>學校辦學績效與聲望有助於研究商品化活動發展</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_4" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_4" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_4" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_4" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_4" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_4" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_4" value="1" /></td>
				</tr>
				<tr>
					<td>5</td>
					<td>學校現有的產學網絡與聯盟活動有助於研究商品化活動發展</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_5" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_5" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_5" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_5" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_5" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_5" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_5" value="1" /></td>
				</tr>
				<tr>
					<td>6</td>
					<td>學校目前與鄰近產業廠商連結程度有助於研究商品化活動發展</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_6" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_6" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_6" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_6" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_6" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_6" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_6" value="1" /></td>
				</tr>
				<tr>
					<td>7</td>
					<td>學校提供教職員工與學生多樣化研究商品化訓練課程或是學程</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_7" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_7" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_7" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_7" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_7" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_7" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_7" value="1" /></td>
				</tr>
				<tr>
					<td>8</td>
					<td>學校訂定良好的進行各種研究商品化活動辦法與輔導措施</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_8" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_8" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_8" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_8" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_8" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_8" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_8" value="1" /></td>
				</tr>
				<tr>
					<td>9</td>
					<td>學校訂定良好的研究商品化收益分配的辦法</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_9" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_9" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_9" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_9" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_9" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_9" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_9" value="1" /></td>
				</tr>
				<tr>
					<td>10</td>
					<td>學校訂定良好鼓勵教職員工投入研究商品化活動的辦法</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_10" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_10" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_10" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_10" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_10" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_10" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_10" value="1" /></td>
				</tr>
				<tr>
					<td>11</td>
					<td>學校訂定良好獎勵研究商品化成果傑出教職員工的辦法</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_11" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_11" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_11" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_11" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_11" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_11" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_11" value="1" /></td>
				</tr>
				<tr>
					<td>12</td>
					<td>學校對於研究商品化成功案例會進行報導與宣傳</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_12" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_12" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_12" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_12" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_12" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_12" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_12" value="1" /></td>
				</tr>
				<tr>
					<td>13</td>
					<td>學校將研究商品化活動成果納為教職員工產出之一環</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_13" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_13" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_13" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_13" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_13" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_13" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_13" value="1" /></td>
				</tr>
				<tr>
					<td>14</td>
					<td>學校訂定多元升等機制，將研究商品化成果與個人職涯發展連結</td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_14" value="7" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_14" value="6" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_14" value="5" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_14" value="4" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_14" value="3" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_14" value="2" /></td>
					<td class="text-align-center"><input type="radio" name="qnrCoopereateWay.q3_14" value="1" /></td>
				</tr>
			</table>
<!-- 			<input type="button" class="goToPart2 redBtn" value="上一頁"> -->
			<s:submit cssClass="redBtn" value="下一頁" />
		</div>
	</s:form>
</body>
</html>