<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<s:include value="./banner.jsp" />

	<!-- HOT 20 -->
	<div class="hot20">
		<div class="container">
	    	<div class="row">
	            <div class="col-sm-12">
	            	<ul class="list-inline" style="margin-bottom:0;">
	                	<li><i class="fa fa-fire right5" aria-hidden="true"></i><strong>HOT 20 :</strong></li>
	                    <li class="content_01">2016/11/30(三) - 活動人培 - 「生技矽谷與智慧醫療」技術研討會</li>
	                    <li><a href="#"><img src="<s:url value="/images/frontend-v2/more_red.png"/>" alt="" height="30"/></a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- NEWS -->
	<div class="news">
		<div class="container">
	    	<div class="row">
	            <div class="col-sm-12">
	            	<ul class="list-inline" style="margin-bottom:0;">
	                	<li><i class="fa fa-newspaper-o right5" aria-hidden="true"></i><strong>NEWS :</strong></li>
	                    <li class="content_02">運用法人鏈結產業合作計畫成果發表會誠摯邀請您蒞臨指導</li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
	
	<s:include value="./newsAndActivity.jsp" />
	<s:include value="./relatedWebsites.jsp" />
	<s:include value="./industryInfo.jsp" />
	<s:include value="./academiaAchievement.jsp" />
	<s:include value="./coopExample.jsp" />
	<s:include value="./talentedPeople.jsp" />
	<s:include value="./policyAndLiterature.jsp" />
</body>
</html>