<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script>
	$(document).ready(function() {
		setValueBackToRadio();
		switchPartSetting();
		part4controlSetting();

		$("input[type=submit]").click(function(event) {
			event.preventDefault();
			if (isFinishPart4()) {
				$("form").submit();
				return true;
			} else {
				alert("尚有題目未回答!");
				return false;
			}
		});
		
// 		$("#table-part1 input[type=radio][value=1]").prop("checked", true); //TODO 上版前要註解掉
// 		$("#table-part2 input[type=radio][value=1]").prop("checked", true); //TODO 上版前要註解掉
// 		$("#table-part3 input[type=radio][value=1]").prop("checked", true); //TODO 上版前要註解掉
	});
</script>
<script>
	function setValueBackToRadio() {
		var q1_1 = "<s:property value="qnrCoopereateWay.q1_1"/>";
		$("input[name='qnrCoopereateWay.q1_1'][value="+q1_1+"]").prop("checked", true);		
		var q1_2 = "<s:property value="qnrCoopereateWay.q1_2"/>";
		$("input[name='qnrCoopereateWay.q1_2'][value="+q1_2+"]").prop("checked", true);		
		var q1_3 = "<s:property value="qnrCoopereateWay.q1_3"/>";
		$("input[name='qnrCoopereateWay.q1_3'][value="+q1_3+"]").prop("checked", true);
		var q1_4 = "<s:property value="qnrCoopereateWay.q1_4"/>";
		$("input[name='qnrCoopereateWay.q1_4'][value="+q1_4+"]").prop("checked", true);
		var q1_5 = "<s:property value="qnrCoopereateWay.q1_5"/>";
		$("input[name='qnrCoopereateWay.q1_5'][value="+q1_5+"]").prop("checked", true);
		var q1_6 = "<s:property value="qnrCoopereateWay.q1_6"/>";
		$("input[name='qnrCoopereateWay.q1_6'][value="+q1_6+"]").prop("checked", true);
		var q1_7 = "<s:property value="qnrCoopereateWay.q1_7"/>";
		$("input[name='qnrCoopereateWay.q1_7'][value="+q1_7+"]").prop("checked", true);
		var q1_8 = "<s:property value="qnrCoopereateWay.q1_8"/>";
		$("input[name='qnrCoopereateWay.q1_8'][value="+q1_8+"]").prop("checked", true);
		var q1_9 = "<s:property value="qnrCoopereateWay.q1_9"/>";
		$("input[name='qnrCoopereateWay.q1_9'][value="+q1_9+"]").prop("checked", true);
		var q1_10 = "<s:property value="qnrCoopereateWay.q1_10"/>";
		$("input[name='qnrCoopereateWay.q1_10'][value="+q1_10+"]").prop("checked", true);
		var q1_11 = "<s:property value="qnrCoopereateWay.q1_11"/>";
		$("input[name='qnrCoopereateWay.q1_11'][value="+q1_11+"]").prop("checked", true);
		var q1_12 = "<s:property value="qnrCoopereateWay.q1_12"/>";
		$("input[name='qnrCoopereateWay.q1_12'][value="+q1_12+"]").prop("checked", true);
		var q1_13 = "<s:property value="qnrCoopereateWay.q1_13"/>";
		$("input[name='qnrCoopereateWay.q1_13'][value="+q1_13+"]").prop("checked", true);
		var q1_14 = "<s:property value="qnrCoopereateWay.q1_14"/>";
		$("input[name='qnrCoopereateWay.q1_14'][value="+q1_14+"]").prop("checked", true);
		var q1_15 = "<s:property value="qnrCoopereateWay.q1_15"/>";
		$("input[name='qnrCoopereateWay.q1_15'][value="+q1_15+"]").prop("checked", true);

		var q2_1 = "<s:property value="qnrCoopereateWay.q2_1"/>";
		$("input[name='qnrCoopereateWay.q2_1'][value="+q2_1+"]").prop("checked", true);
		var q2_2 = "<s:property value="qnrCoopereateWay.q2_2"/>";
		$("input[name='qnrCoopereateWay.q2_2'][value="+q2_2+"]").prop("checked", true);
		var q2_3 = "<s:property value="qnrCoopereateWay.q2_3"/>";
		$("input[name='qnrCoopereateWay.q2_3'][value="+q2_3+"]").prop("checked", true);
		var q2_4 = "<s:property value="qnrCoopereateWay.q2_4"/>";
		$("input[name='qnrCoopereateWay.q2_4'][value="+q2_4+"]").prop("checked", true);
		var q2_5 = "<s:property value="qnrCoopereateWay.q2_5"/>";
		$("input[name='qnrCoopereateWay.q2_5'][value="+q2_5+"]").prop("checked", true);
		var q2_6 = "<s:property value="qnrCoopereateWay.q2_6"/>";
		$("input[name='qnrCoopereateWay.q2_6'][value="+q2_6+"]").prop("checked", true);
		var q2_7 = "<s:property value="qnrCoopereateWay.q2_7"/>";
		$("input[name='qnrCoopereateWay.q2_7'][value="+q2_7+"]").prop("checked", true);
		var q2_8 = "<s:property value="qnrCoopereateWay.q2_8"/>";
		$("input[name='qnrCoopereateWay.q2_8'][value="+q2_8+"]").prop("checked", true);
		var q2_9 = "<s:property value="qnrCoopereateWay.q2_9"/>";
		$("input[name='qnrCoopereateWay.q2_9'][value="+q2_9+"]").prop("checked", true);
		var q2_10 = "<s:property value="qnrCoopereateWay.q2_10"/>";
		$("input[name='qnrCoopereateWay.q2_10'][value="+q2_10+"]").prop("checked", true);
		
		var q3_1 = "<s:property value="qnrCoopereateWay.q3_1"/>";
		$("input[name='qnrCoopereateWay.q3_1'][value="+q3_1+"]").prop("checked", true);
		var q3_2 = "<s:property value="qnrCoopereateWay.q3_2"/>";
		$("input[name='qnrCoopereateWay.q3_2'][value="+q3_2+"]").prop("checked", true);
		var q3_3 = "<s:property value="qnrCoopereateWay.q3_3"/>";
		$("input[name='qnrCoopereateWay.q3_3'][value="+q3_3+"]").prop("checked", true);
		var q3_4 = "<s:property value="qnrCoopereateWay.q3_4"/>";
		$("input[name='qnrCoopereateWay.q3_4'][value="+q3_4+"]").prop("checked", true);
		var q3_5 = "<s:property value="qnrCoopereateWay.q3_5"/>";
		$("input[name='qnrCoopereateWay.q3_5'][value="+q3_5+"]").prop("checked", true);
		var q3_6 = "<s:property value="qnrCoopereateWay.q3_6"/>";
		$("input[name='qnrCoopereateWay.q3_6'][value="+q3_6+"]").prop("checked", true);
		var q3_7 = "<s:property value="qnrCoopereateWay.q3_7"/>";
		$("input[name='qnrCoopereateWay.q3_7'][value="+q3_7+"]").prop("checked", true);
		var q3_8 = "<s:property value="qnrCoopereateWay.q3_8"/>";
		$("input[name='qnrCoopereateWay.q3_8'][value="+q3_8+"]").prop("checked", true);
		var q3_9 = "<s:property value="qnrCoopereateWay.q3_9"/>";
		$("input[name='qnrCoopereateWay.q3_9'][value="+q3_9+"]").prop("checked", true);
		var q3_10 = "<s:property value="qnrCoopereateWay.q3_10"/>";
		$("input[name='qnrCoopereateWay.q3_10'][value="+q3_10+"]").prop("checked", true);
		var q3_11 = "<s:property value="qnrCoopereateWay.q3_11"/>";
		$("input[name='qnrCoopereateWay.q3_11'][value="+q3_11+"]").prop("checked", true);
		var q3_12 = "<s:property value="qnrCoopereateWay.q3_12"/>";
		$("input[name='qnrCoopereateWay.q3_12'][value="+q3_12+"]").prop("checked", true);
		var q3_13 = "<s:property value="qnrCoopereateWay.q3_13"/>";
		$("input[name='qnrCoopereateWay.q3_13'][value="+q3_13+"]").prop("checked", true);
		var q3_14 = "<s:property value="qnrCoopereateWay.q3_14"/>";
		$("input[name='qnrCoopereateWay.q3_14'][value="+q3_14+"]").prop("checked", true);
	}
</script>
<script>
	function switchPartSetting() {
		hideAllPart();
		
		var submitPageFrom = "<s:property value="submitPageFrom"/>";
		if (submitPageFrom == null || submitPageFrom == "") {
			$("#div-part0").show();
		} else {
			$("#div-part3").show();
		}		

		$("input[type=button].goToPart0").click(function() {
			hideAllPart();
			$("#div-part0").show();
		});
		$("input[type=button].goToPart1").click(function() {
			if (isFinishPart0()) {
				hideAllPart();
				$("#div-part1").show();
			} else {
				alert("尚有題目未回答!");
			}
		});
		$("input[type=button].goToPart2").click(function() {
			if (isFinishPart1()) {
				hideAllPart();
				$("#div-part2").show();
			} else {
				alert("尚有題目未回答!");
			}
		});
		$("input[type=button].goToPart3").click(function() {
			if (isFinishPart2()) {
				hideAllPart();
				$("#div-part3").show();
			} else {
				alert("尚有題目未回答!");
			}
		});
		
		$("input[type=button].goToPart4").click(function() {
			if (isFinishPart3()) {
				hideAllPart();
				$("#div-part4").show();
			} else {
				alert("尚有題目未回答!");
			}
		});
	}
	
	function isFinishPart0() {
		if ($("#div-part0 input[type='checkbox']:checked").length < 1) {
			return false;
		}
		if ($("#div-part0 input[type='radio']:checked").length != 2) {
			return false;
		}
		return true;
	}
	
	function isFinishPart1() {
		return $("#div-part1 input[type='radio']:checked").length == 15;
	}
	
	function isFinishPart2() {
		return $("#div-part2 input[type='radio']:checked").length == 10;
	}
	
	function isFinishPart3() {
		return $("#div-part3 input[type='radio']:checked").length == 14;
	}
	
	function isFinishPart4() {
		var q0_1 = [false, false, false];
		$("input[name='qnrCoopereateWay.q0_1']:checked").map(function(index){
			q0_1[$(this).val()-1] = true;
		);
		
		var numRadioNeedToCheck = 0;
		if (q0_1[0] == true) {
			numRadioNeedToCheck = 9;
		}
		else {
			if (q0_1[1] == true) {
				numRadioNeedToCheck += 3;
			}
			if (q0_1[2] == true) {
				numRadioNeedToCheck += 1;
			}
		} 
			
		return $("#div-part4 input[type='radio']:checked").length >= numRadioNeedToCheck;
	}

	function hideAllPart() {
		$("#div-part0").hide();
		$("#div-part1").hide();
		$("#div-part2").hide();
		$("#div-part3").hide();
		$("#div-part4").hide();
	}
</script>
<script>
	function part4controlSetting() {
		$("input[name='qnrCoopereateWay.q0_1']").click(function() {		
			var q0_1 = [false, false, false];
			$("input[name='qnrCoopereateWay.q0_1']:checked").map(function(index){
				q0_1[$(this).val()-1] = true;
			});

			$("div#div-part4 input[type=radio]").attr("disabled", true);
			if (q0_1[0] == true) {
				$("div#div-part4 input[type=radio]").removeAttr("disabled");
			} 
			if (q0_1[1] == true) {
				$("div#div-part4 input[type=radio][name='qnrCoopereateWay.q4_6']").removeAttr("disabled");
				$("div#div-part4 input[type=radio][name='qnrCoopereateWay.q4_7']").removeAttr("disabled");
				$("div#div-part4 input[type=radio][name='qnrCoopereateWay.q4_8']").removeAttr("disabled");
			}
			if (q0_1[2] == true) {
				$("div#div-part4 input[type=radio][name='qnrCoopereateWay.q4_9']").removeAttr("disabled");
			}
		});
	}
</script>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/qnrCooperateWay.css"/>" />
</head>
<body>
	<s:form action="fillInQnrSubmit" method="post" validate="true">
		<s:hidden name="schoolId" />
		<s:hidden name="qnrCoopereateWay.aggreePDPL" />
		<s:hidden name="qnrCoopereateWay.name" />
		<s:hidden name="qnrCoopereateWay.email" />
		<s:hidden name="qnrCoopereateWay.address" />
		<s:hidden name="qnrCoopereateWay.applicantId" />
		
		<div id="div-part0" style="font-size: 1.15em;">
			<img src="<s:url value="/images/qnrCooperateWayDesc.jpg"/>" style="max-width: 1000px; max-height: 1000px; margin-bottom:30px;">
			<h2 class="itemTitle Down">基本資料</h2>
			<ul>
				<li class="all">
					1. 請問您目前任職單位職稱為：
					 <s:checkboxlist name="qnrCoopereateWay.q0_1" list="#{'1':'產學合作業務一級主管 (研究發展處研發長, 技術合作處技合長, 產學合作處產學長或產學合作中心單位主管)', '2':'技轉中心單位主管', '3':'育成中心單位主管'}" cssClass="horizontalList"/>
				</li>
				<li class="all">
					2. 請問您服務於學術界年資合計： 
					<s:radio name="qnrCoopereateWay.q0_2" list="#{'1':'5年以下', '2':'5-10年', '3':'11-15年', '4':'16-20年', '5':'21-25年', '6':'26-30年', '7':'30年以上' }" cssClass="horizontalList"/>
				</li>
				<li class="all">
					3. 請問您參與產學合作活動/計畫相關資歷合計： 
					<s:radio name="qnrCoopereateWay.q0_3" list="#{'1':'5年以下', '2':'5-10年', '3':'11-15年', '4':'16-20年', '5':'20年以上' }" cssClass="horizontalList"/>
				</li>
			</ul>
			<input type="button" class="goToPart1 redBtn" value="下一頁">
		</div>
		<div class="clear"></div>
		
		<div id="div-part1">
			<h2 class="subTitle-qnrCooperateWay">《第一部份》學校對於推動新興特色的發展導向</h2>
			<div class="top-desc">
				<p>【說明】您目前服務的學校對於推動新興特色的發展導向，如產學合作、創業型大學、教學卓越大學等，請勾選適當的程度，皆為單選題。</p>
			</div>

			<table class="table-qnrCooperateWay" id="table-part1">
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

			<input type="button" class="goToPart0 redBtn" value="上一頁"> 
			<input type="button" class="goToPart2 redBtn" value="下一頁">
		</div>
		<div class="clear"></div>
		
		<div id="div-part2">
			<h2 class="subTitle-qnrCooperateWay">《第二部份》學校推動研究商品化活動的支援體系</h2>
			<div class="top-desc">
				<p>【說明】您目前服務的學校針對研發活動及產學合作提供之支援體系，請勾選適當的程度，皆為單選題。</p>
			</div>
			<table class="table-qnrCooperateWay" id="table-part2">
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

			<input type="button" class="goToPart1 redBtn" value="上一頁"> 
			<input type="button" class="goToPart3 redBtn" value="下一頁">
		</div>
		<div class="clear"></div>
		
		<div id="div-part3">
			<h2 class="subTitle-qnrCooperateWay">《第三部份》學校針對研究商品化活動提供之相關配套措施</h2>
			<div class="top-desc">
				<p>【說明】您目前服務的學校為推動研究商品化活動(專利申請、產學合作、技術移轉、新創事業等)所提供之相關輔導與激勵措施，請勾選適當的程度，皆為單選題。</p>
			</div>
			<table class="table-qnrCooperateWay" id="table-part3">
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
			<input type="button" class="goToPart2 redBtn" value="上一頁">
			<input type="button" class="goToPart4 redBtn" value="下一頁">
		</div>
		<div class="clear"></div>

		<div id="div-part4">
			<h2 class="subTitle-qnrCooperateWay">《第四部份》學校2015年度產學資源投入及產出</h2>
			<div class="top-desc">
				<p>【說明】請您依據貴校過去一整年度(2015年)產學合作能力表現，勾選最為符合學校能力範疇之選項，皆為單選題。</p>
				<p>【備註】此部份問項請依您的職掌範疇進行填答：</p>
				<p>若您目前任職產學合作業務一級主管（研究發展處研發長、技術合作處技合長、產學合作處產學長或產學合作中心單位主管）請填寫下方「問題1~9」；</p>
				<p>若任職技轉中心單位主管請填寫下方「問題6~8」；</p>
				<p>育成中心單位主管請填寫下方「問題9」。</p>				
			</div>
			
			<div class="clear"></div>
			<ul>
				<li class="all">
					1.	請問貴校參與產學相關推動業務(含產學研發中心、技轉中心、創新育成中心等或相關行政單位)之全職聘僱人員總數為：
					<s:radio name="qnrCoopereateWay.q4_1" list="#{'1':'7人以下', '2':'8-14人', '3':'15-21人', '4':'22-28人', '5':'29人以上'}" cssClass="horizontalList"/>
				</li>
				<li class="all">
					2.	請問貴校實質參與產學合作研究計畫教職人員佔全校教職人員總數比例約為：
					<s:radio name="qnrCoopereateWay.q4_2" list="#{'1':'10%以下', '2':'11-20%', '3':'21-33%', '4':'34-50%', '5':'51%以上'}" cssClass="horizontalList"/>
				</li>
				<li class="all">
					3.	請問貴校獲得政府 (科技部、經濟部、農委會等各部會)補助之產學合作計畫經費年度總額約為：
					<s:radio name="qnrCoopereateWay.q4_3" list="#{'1':'1,000萬元以下', '2':'1,000-5,000萬元', '3':'5,000萬-1億元', '4':'1-2億元', '5':'2億元以上'}" cssClass="horizontalList"/>
				</li>
				<li class="all">
					4.	請問貴校2015年度受企業委託之研究計畫(不含政府補助計畫)簽約總件數：
					<s:radio name="qnrCoopereateWay.q4_4" list="#{'1':'20件以下', '2':'21-50件', '3':'51-100件', '4':'100-150件', '5':'150件以上'}" cssClass="horizontalList"/>
				</li>				
				<li class="all">
					5.	請問貴校2015年度受企業委託之研究計畫(不含政府補助計畫)簽約總經費：
					<s:radio name="qnrCoopereateWay.q4_5" list="#{'1':'500萬元以下', '2':'500-1,000萬元', '3':'1,000-5,000萬元', '4':'5,000萬-1億元', '5':'1億元以上'}" cssClass="horizontalList"/>
				</li>				
				<li class="all">
					6.	請問貴校2015年度獲證專利總件數(含政府補助、學校自主申請之專利案件)：
					<s:radio name="qnrCoopereateWay.q4_6" list="#{'1':'25件以下', '2':'26-50件', '3':'51-75件', '4':'76-100件', '5':'100件以上'}" cssClass="horizontalList"/>
				</li>				
				<li class="all">
					7.	請問貴校2015年度技術移轉總件數(含政府補助、學校自主簽訂之產學合作計畫成果)：
					<s:radio name="qnrCoopereateWay.q4_7" list="#{'1':'25件以下', '2':'26-50件', '3':'51-75件', '4':'76-100件', '5':'100件以上'}" cssClass="horizontalList"/>
				</li>				
				<li class="all">
					8.	請問貴校2015年度技術移轉合約授權收入總額(含政府補助計畫、學校自主簽訂之產學合作計畫)：
					<s:radio name="qnrCoopereateWay.q4_8" list="#{'1':'1,000萬元以下', '2':'1,000-5,000萬元', '3':'5,000萬-1億元', '4':'1-1.5億元', '5':'1.5億元以上'}" cssClass="horizontalList"/>
				</li>
				<li class="all">
					9.	請問貴校創新育成中心進駐企業中，屬於校內師生創業的比例約為：
					<s:radio name="qnrCoopereateWay.q4_9" list="#{'1':'10%以下', '2':'11-20%', '3':'21-33%', '4':'34-50%', '5':'51%以上'}" cssClass="horizontalList"/>
				</li>
			</ul>
			
			<div style="margin:50px 0px 20px 0px; font-size:20px; ">
				<span>【本問卷到此結束，敬請再次確認是否有漏答，謝謝!】</span>
			</div>
			
			<input type="button" class="goToPart3 redBtn" value="上一頁">
			<s:submit cssClass="redBtn" value="完成送出" />
		</div>

	</s:form>
</body>
</html>