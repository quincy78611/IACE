<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<h2 class="itemTitle">新增</h2>
	<s:form action="createSubmit" method="post" validate="true">
		<ul>
			<li class="all">
				<b>計畫名稱</b>
				<s:textfield name="researchPlan.planNo" />
			</li>
			<li class="half">
				<b>計畫編號</b>
				<s:textfield name="researchPlan.planNo" />
			</li>
			<li class="quarter">
				<b>計畫主持人</b>
				<s:textfield name="researchPlan.manager" />
			</li>
			<li class="quarter">
				<b>計畫年度</b>
				<s:textfield name="researchPlan.year" placeholder="請輸入民國年"/>
			</li>
			<li class="half">
				<b>GRB計畫編號</b>
				<s:textfield name="researchPlan.projkey" />
			</li>
			<li class="half">
				<b>成果報告ID</b>
				<s:textfield name="researchPlan.grb05Id" />
			</li>
			<li class="half">
				<b>計畫關鍵字</b>
				<s:textfield name="researchPlan.keyword" />
			</li>
			<li class="half">
				<b>技術發展階段</b>
				<s:select name="researchPlan.trl.code" list="optionTrlList" listKey="code" listValue="%{code +' ' +name}" headerKey="" headerValue="" />
			</li>
			<li class="third">
				<b>研究領域清單</b>
				<s:select id="grbCodesList" name="" list="optionGrbDomainList" listKey="code" listValue="%{code +' ' +name}" multiple="true" size="9"/>
			</li>
			<li class="third">
				<%-- <input type="button" id="btn-selectGrb" class="right" value="->"/>
				<input type="button" id="btn-diselectGrb" class="left" value="<-"/> --%>
				<a href="###" id="btn-selectGrb" class="right"></a>
				<a href="###" id="btn-diselectGrb" class="left"></a>
			</li>
			<li class="third">
				<b>已選研究領域</b>
				<s:select id="selectedGrbCodes" name="researchPlan.grbDomainCodes" list="{}" listKey="code" listValue="%{code +' ' +name}" multiple="true" size="9"/>
			</li>
		</ul>
		<div class="clear"></div>
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="儲存" />
			<a class="grayBtn" href="<s:url value="/iace/researchPlan/init"/>">回上一頁</a>			
		</div>
	</s:form>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#grbCodesList option").dblclick(dblclick_grbOption);
			$("#selectedGrbCodes option").dblclick(dblclick_selectrbOption);
		});
		
		function dblclick_grbOption() {
			if ($("#selectedGrbCodes option").size() == 6) {
				alert("最多只可選取6個");
				return;
			}
			
			$("#selectedGrbCodes").append($(this));
			
			var selectList = $("#selectedGrbCodes option");
			selectList.sort(function(a,b){
				var valA = a.value.toLowerCase();
				var valB = b.value.toLowerCase();
			    if (valA < valB) {
			    	return -1;
			    } else if (valA > valB) {
			    	return 1;
			    } else {
			    	if (a.value < b.value) return -1;
			    	else if (a.value < b.value) return 1;
			    	else return 0;
			    }
			});
			$("#selectedGrbCodes").html(selectList);
			$("#selectedGrbCodes option").dblclick(dblclick_selectrbOption);
		}
		function dblclick_selectrbOption() {
			$("#grbCodesList").append($(this));
			
			var selectList = $("#grbCodesList option");			
			selectList.sort(function(a,b){
				var valA = a.value.toLowerCase();
				var valB = b.value.toLowerCase();
			    if (valA < valB) {
			    	return -1;
			    } else if (valA > valB) {
			    	return 1;
			    } else {
			    	if (a.value < b.value) return -1;
			    	else if (a.value < b.value) return 1;
			    	else return 0;
			    }
			});
			$("#grbCodesList").html(selectList);
			$("#grbCodesList option").dblclick(dblclick_grbOption);			
		}		
		
		$("#btn-selectGrb").click(function(){			
			var selectOption = $("#grbCodesList").find('option:selected');
			selectOption.each(function(){
				$(this).dblclick();
			});
		});
		$("#btn-diselectGrb").click(function(){			
			var selectOption = $("#selectedGrbCodes").find('option:selected');
			selectOption.each(function(){
				$(this).dblclick();
			});
		});

		$("#btn_save").click(function(){
			$("#selectedGrbCodes option").prop('selected', true);
		});
	</script>
	
</body>
</html>