<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">

<style>
th {
	border: solid 1px;
	white-space: nowrap;
}
</style>
</head>
<body>
	<h2 class="itemTitle">《第四部份》學校於2013~2015年期間研發產出及產學合作績效</h2>
	<h3>【說明】請您將貴校於西元2013~2015年期間研發產出及產學合作績效，填寫下表：</h3>
	<s:form action="fillInQnrPart4Submit" method="post" validate="true">
		<h3>(一) 研發合作與智財推廣</h3>
		<table width="100%">
			<tr>
				<th></th>
				<th colspan="2">企業委託研究</th>
				<th colspan="2">獲證專利</th>
				<th colspan="2">專利授權</th>
				<th colspan="3">技術移轉</th>
			</tr>
			<tr>
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
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].id'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].year'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].school.id'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].isValid'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].createTime'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].createUser'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].updateTime'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].updateUser'}" />
					<s:hidden name="%{'qnrCooperateWayMerits['+#stat.index+'].ver'}" />
					<td><s:property value="year" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_1_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_1_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_2_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_2_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_3_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_3_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_4_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_4_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p1_4_3'}" /></td>
				</tr>
			</s:iterator>
		</table>

		<h3>(二) 發展資源及新創事業</h3>
		<table width="100%">
			<tr>
				<th></th>
				<th colspan="2">研發活動主管單位</th>
				<th colspan="2">產學合作主管單位</th>
				<th colspan="2">技轉中心</th>
				<th colspan="5">育成中心</th>
			</tr>
			<tr>
				<td>年度</td>
				<td>預算</td>
				<td>年平均受雇全職人數</td>
				<td>預算</td>
				<td>年平均受雇全職人數</td>
				<td>政府補助經費</td>
				<td>受雇人數</td>
				<td>政府補助經費</td>
				<td>受雇人數</td>
				<td>進駐家數</td>
				<td>育成中心回饋收入</td>
				<td>本校師生創業進駐家數</td>
			</tr>
			<s:iterator value="qnrCooperateWayMerits" status="stat">
				<tr>
					<td><s:property value="year" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_1_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_1_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_2_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_2_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_3_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_3_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_1'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_2'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_3'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_4'}" /></td>
					<td><s:textfield name="%{'qnrCooperateWayMerits['+#stat.index+'].p2_4_5'}" /></td>
				</tr>
			</s:iterator>
		</table>
		
		<s:submit cssClass="redBtn" value="送出" />
	</s:form>

</body>
</html>