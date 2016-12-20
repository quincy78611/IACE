<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<div class="footer_blue">
	<div class="container">
		<div class="row">
			<div class="col-sm-2 col-xs-6">
				<div class="small_title_02">活動人培</div>
				<ul class="small_title_02">
					<li><a href="<s:url value="/f2/activity/init?searchCondition.category=成果發表"/>" class="list_link_04">成果發表</a></li>
					<li><a href="<s:url value="/f2/activity/init?searchCondition.category=計畫宣導"/>" class="list_link_04">計畫宣導</a></li>
					<li><a href="<s:url value="/f2/activity/init?searchCondition.category=媒合會"/>" class="list_link_04">媒合會</a></li>
					<li><a href="<s:url value="/f2/activity/init?searchCondition.category=人培課程"/>" class="list_link_04">人培課程</a></li>
					<li><a href="<s:url value="/f2/activity/init?searchCondition.category=外部活動"/>" class="list_link_04">外部活動</a></li>
					<li><a href="<s:url value="/f2/activity/init?searchCondition.category=計畫研習"/>" class="list_link_04">計畫研習</a></li>
				</ul>
			</div>
			<div class="col-sm-2 col-xs-6">
				<div class="small_title_02">公告訊息</div>
				<ul class="small_title_02">
					<li><a href="<s:url value="/f2/news/init?searchCondition.category=一般公告"/>" class="list_link_04">一般公告</a></li>
					<li><a href="<s:url value="/f2/news/init?searchCondition.category=新聞稿"/>" class="list_link_04">新聞稿</a></li>
				</ul>
			</div>
			<div class="col-sm-2 col-xs-6">
				<div class="small_title_02">產學合作案例</div>
				<ul class="small_title_02">
					<li><a href="#" class="list_link_04">商品化</a></li>
					<li><a href="#" class="list_link_04">專利推廣</a></li>
					<li><a href="#" class="list_link_04">新創事業</a></li>
				</ul>
			</div>
			<div class="col-sm-2 col-xs-6">
				<div class="small_title_02">學界研發成果</div>
				<ul class="small_title_02">
					<li><a href="<s:url value="/f2/integrationSearch/init?searchCondition.className=iace.entity.researchPlan.Technology"/>" class="list_link_04">學研成果</a></li>
					<li><a href="<s:url value="/f2/integrationSearch/init?searchCondition.className=iace.entity.patent.Patent"/>" class="list_link_04">學界專利</a></li>
					<li><a href="<s:url value="/f2/integrationSearch/init?searchCondition.className=iace.entity.talentedPeople.TalentedPeople"/>" class="list_link_04">產學人才</a></li>
				</ul>
			</div>
			<div class="col-sm-2 col-xs-6">
				<div class="small_title_02">產業情報</div>
				<ul class="small_title_02">
					<li><a href="#" class="list_link_04">產業新聞</a></li>
					<li><a href="#" class="list_link_04">產業評析</a></li>
				</ul>
			</div>
			<div class="col-sm-2 col-xs-6">
				<div class="small_title_02">媒合專區</div>
				<ul class="small_title_02">
					<li><a href="#" class="list_link_04">產學媒合服務團簡介</a></li>
					<li><a href="<s:url value="/f2/integrationSearch/init?searchCondition.className=iace.entity.literature.Literature"/>" class="list_link_04">法規政策</a></li>
					<li><a href="<s:url value="/f2/integrationSearch/init?searchCondition.className=iace.entity.literature.Literature"/>" class="list_link_04">文獻</a></li>
					<li><a href="<s:url value="/f2/integrationSearch/init?searchCondition.className=iace.entity.incubationCenter.IncubationCenter"/>" class="list_link_04">育成中心</a></li>
					<li><a href="#" class="list_link_04">問卷調查</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>