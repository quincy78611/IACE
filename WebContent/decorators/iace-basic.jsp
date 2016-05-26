<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="<s:url value="/scripts/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/jquery.validate.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/bootstrap.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/respond.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/calendarBox.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/jquery.datetimepicker.full.js"/>"></script>
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/bootstrap.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/btn.file.browse.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquery.datetimepicker.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/PagedList.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/Site.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/iace.css"/>" />
	
    <s:if test="%{title != null && title != ''}">
   		<title><s:property value="title" /></title>
   	</s:if>
   	<s:else>
   		<title><decorator:title default="Welcome!" /></title>
   	</s:else>    
    
    <script type="text/javascript">		
		$(window).load(function(){
			//目前這作法只是權宜之計，比較好的做法應該是用更改template & theme
			//http://www.mkyong.com/struts2/working-with-struts-2-theme-template/
			//http://struts.apache.org/docs/xhtml-theme.html
			$("span.help-block").removeClass("help-block");
			$("span.glyphicon").remove();
		});
	</script>
    
    <decorator:head />
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
				<a class="navbar-brand" >鏈結產學媒合平台 IACE</a>
			</div>
		</div>
	</div>
	<div class="">
		<div class="left-menu" >
			<div class="navbar-collapse collapse">
				<ul class="nav nav-pills nav-stacked">					
					<li class="active"><a href="#">Home</a></li>
					<li class="dropdown">
					    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
					       	 研究計畫資料 <span class="caret"></span>
					    </a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<s:url value="/iace/researchPlan/init"/>">維護</a></li>
							<li><a href="<s:url value="/iace/researchPlan/create"/>">新增</a></li>
						</ul>
					</li>
					<li class="dropdown">
					    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
					       	 專利資料 <span class="caret"></span>
					    </a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<s:url value="/iace/patent/init"/>">維護</a></li>
							<li><a href="<s:url value="/iace/patent/create"/>">新增</a></li>
						</ul>
					</li>
					<li><a href="<s:url value="/iace/batchImport/init"/>">批次匯入</a></li>
					<li class="dropdown">
					    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
					       	 代碼管理 <span class="caret"></span>
					    </a>
					    <ul class="dropdown-menu" role="menu">
					    	<li><a href="<s:url value="/iace/option/country/index"/>">專利國別</a></li>
					        <li><a href="<s:url value="/iace/option/companyLocation/index"/>">公司地域別</a></li>
					        <li><a href="<s:url value="/iace/option/consult/index"/>">諮詢類型代</a></li>
					        <li><a href="<s:url value="/iace/option/cooperateMode/index"/>">合作模式</a></li>
					        <li><a href="<s:url value="/iace/option/grbDomain/index"/>">GRB領域別</a></li>
					        <li><a href="<s:url value="/iace/option/hadTecSrc/index"/>">企業已有技術來源</a></li>
					        <li><a href="<s:url value="/iace/option/industry/index"/>">產業/領域別</a></li>
<%-- 					        <li><a href="<s:url value="/iace/option/industryClass/index"/>">產業類別</a></li>					         --%>
					        <li><a href="<s:url value="/iace/option/organizationClass/index"/>">單位類別</a></li>
					        <li><a href="<s:url value="/iace/option/organizationType/index"/>">單位類型</a></li>
					        <li><a href="<s:url value="/iace/option/trl/index"/>">發展階段</a></li>
						</ul>
					</li>		
				</ul>
			</div>
		</div>
	    <div class="right-content">
		    <div class="container body-content">
		    	<s:if test="%{title != null && title != ''}">
		    		<h2><s:property value="title" /></h2>
		    	</s:if>  
				<s:if test="hasActionMessages()">
					<div class="welcome">
						<s:actionmessage />
					</div>
				</s:if>
				<s:if test="hasActionErrors()">
					<div class="errors">
						<s:actionerror />
					</div>
				</s:if>
				<decorator:body /> 
	    	</div>	
			<br>
			<div class="container">
				<p>&copy; IACE</p>
			</div>
		</div>
	</div>

</body>
</html>