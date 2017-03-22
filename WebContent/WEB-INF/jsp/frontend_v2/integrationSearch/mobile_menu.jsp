<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<script type="text/javascript">
	$(document).ready(function() {
		$(".mobile_menu .classNameBtn").click(function(){
			var displayClassName = $(this).find(".displayClassName").html();
			$(".mobile_menu label.selectedClass").html(displayClassName);
			
			var className = $(this).find(".classNameValue").val();
			$("input[name='searchCondition.className']").val(className);	
		});
		
		var className = $("input[name='searchCondition.className']").val();
		$(".mobile_menu .classNameValue[value='"+className+"']").parents(".classNameBtn").trigger("click");
	});
</script>

<div class="col-sm-12 col-xs-12 mobile_menu bottom20">
	<div class="dropdown">
		<button class="btn btn-info btn-lg btn-block dropdown-toggle" type="button" id="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			分類選單 : <label class="selectedClass"></label> <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="" style="width: 100%;">
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value=""/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_01m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">全部</div>
			</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value="iace.entity.researchPlan.ResearchPlan"/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_02m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">研發成果</div>
			</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value="iace.entity.patent.Patent"/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_03m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">專利資料</div>
			</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value="iace.entity.talentedPeople.TalentedPeople"/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_04m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">產學人才</div>
			</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value="iace.entity.coopExample.CoopEx"/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_05m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">合作案例</div>
			</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value="iace.entity.literature.Literature"/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_06m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">法規/文獻</div>
			</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value="iace.entity.incubationCenter.IncubationCenter"/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_07m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">育成中心</div>
			</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#" class="list_link_01 classNameBtn">
					<input type="text" class="classNameValue" style="display:none;" value="OTHER"/>
					<div style="width: 20%; float: left; text-align: center;">
						<img src="<s:url value="/images/frontend-v2/icon_08m.png"/>" class="right10" alt="" height="30" />
					</div>
					<div class="displayClassName" style="width: 80%;">其它</div>
			</a></li>
			<div class="clearfix"></div>
		</ul>
	</div>
</div>
