<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<script type="text/javascript" src="<s:url value="/scripts/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/jquery.validate.js"/>"></script>
	<%-- <script type="text/javascript" src="<s:url value="/scripts/bootstrap.js"/>"></script> --%>
	<%-- <script type="text/javascript" src="<s:url value="/scripts/respond.js"/>"></script> --%>
	<script type="text/javascript" src="<s:url value="/scripts/calendarBox.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/jquery.datetimepicker.full.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/menu.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/vmenuModule.js"/>"></script>
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquerysctipttop.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/layout_qnr.css"/>" />
	<%-- <link rel="stylesheet" type="text/css" href="<s:url value="/css/bootstrap.css"/>" /> --%>
	<%-- <link rel="stylesheet" type="text/css" href="<s:url value="/css/Site.css"/>" /> --%>
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/btn.file.browse.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquery.datetimepicker.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/PagedList.css"/>" />

	
    <s:if test="%{title != null && title != ''}">
   		<title><s:property value="title" /></title>
   	</s:if>
   	<s:else>
   		<title><decorator:title default="Welcome!" /></title>
   	</s:else>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".u-vmenu").vmenuModule({
				Speed : 200,
				autostart : false,
				autohide : true
			});
			
			setInterval(function () {
                /* $("#div-top-message").attr("hidden", "hidden"); */
                $("#div-top-message").hide();
            }, 5000);
		});
	</script>
	

	<decorator:head />
</head>
<body>
	<header>
		<div class="LOGO">
			<img src="<s:url value="/images/LOGO.gif"/>" class="hlogo">
		</div>
	</header>
	<article>
		<s:if test="%{title != null && title != ''}">
    		<h1 class="pageTitle"><s:property value="title" /></h1>
    	</s:if>  
    	<div class="rightContent">
    		<s:hidden name="#context['struts.actionMapping'].name" id="currentActionName"/>
			<div id="div-top-message">
				<s:if test="hasActionMessages()">
					<s:actionmessage />
				</s:if>
				<s:if test="hasActionErrors()">
					<s:actionerror />
				</s:if>			
			</div>
			<decorator:body />
    	</div>
    	<div class="clear"></div>
	</article>
	<footer>
		<div class="con">
<!-- 			<div class="subLink"> -->
<%-- 				<img src="<s:url value="/images/footerLOGO.gif"/>" > --%>
<!-- 			</div> -->
			<div class="contact">
				科技部鏈結產學合作計畫辦公室 / 服務專線：02-27377373 / 聯絡地址：台北市和平東路二段106號 / 
				網站維護：財團法人國家實驗研究院科技政策研究與資訊中心<br> 
				請用Google Chrome 或 IE 9.0 以上版本瀏覽最佳觀看解析度1200x800
			</div>
		</div>
	</footer>
</body>
</html>