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
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/layout.css"/>" />
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

<%-- 	<script type="text/javascript">
		$(window).load(function() {
			//目前這作法只是權宜之計，比較好的做法應該是用更改template & theme
			//http://www.mkyong.com/struts2/working-with-struts-2-theme-template/
			//http://struts.apache.org/docs/xhtml-theme.html
			$("span.help-block").removeClass("help-block");
			$("span.glyphicon").remove();
		});
	</script> --%>
	
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
<!-- 	    <div class="Link">
	    	帳號<input name="" type="text">
	    	密碼<input name="" type="text">
	    	<a href="#" class="login">登入</a>
	    </div> -->
	</header>
	<article>
		<s:if test="%{title != null && title != ''}">
    		<h1 class="pageTitle"><s:property value="title" /></h1>
    	</s:if>  
    	<div class="leftMenu">
    		<div class="u-vmenu">
    			<ul>
					<li><a href="#">研發成果</a>
						<ul>
							<li><a href="<s:url value="/iace/researchPlan/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/iace/batchImport/init"/>">批次匯入</a></li>
						</ul>
					</li>    			
					<li><a href="#">專利資料</a>
						<ul>
							<li><a href="<s:url value="/iace/patent/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/iace/patent/create"/>">新增</a></li>
							<li><a href="<s:url value="/iace/batchImport/init"/>">批次匯入</a></li>
						</ul>
					</li>
					<li><a href="#">諮詢服務表</a>
						<ul>
							<li><a href="<s:url value="/iace/consulting/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/iace/consulting/create"/>">新增</a></li>
						</ul>
					</li>
					<li><a href="#">問卷調查模板</a>
						<ul>
							<li><a href="<s:url value="/iace/qnrTemplate/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/iace/qnrTemplate/create"/>">新增</a></li>
						</ul>
					</li>	
					<li><a href="#">問卷</a>
						<ul>
							<s:iterator value="qnrTemplateList" status="stat">
								<li>
									<s:url value="/iace/questionnaire/init" var="initUrlTag" escapeAmp="false">
										<s:param name="qnrTableId" value="id" />
										<s:param name="qnrTableName" value="tableName" />
									</s:url>
									<a href='<s:property value="initUrlTag" />'>
										<s:property value="%{'維護 '+name}"/>
									</a>
								</li>
								<li>	
									<s:url value="/iace/questionnaire/create" var="createUrlTag" escapeAmp="false">
										<s:param name="qnrTableId" value="id" />
										<s:param name="qnrTableName" value="tableName" />
									</s:url>
									<a href='<s:property value="createUrlTag" />'>
										<s:property value="%{'填寫 '+name}"/>
									</a>									
								</li>	
							</s:iterator>
						</ul>
					</li>
					<li><a href=#>精進大學產學合作發展機制調查問卷</a>
						<ul>
							<li><a href="<s:url value="/iace/qnrCooperateWay/index"/>">問卷連結</a></li>
						</ul>
					</li>
					<li><a href="#">代碼管理</a>
						<ul>
					    	<li><a href="<s:url value="/iace/option/country/index"/>">專利國別</a></li>
					        <li><a href="<s:url value="/iace/option/companyLocation/index"/>">公司地域別</a></li>
					        <li><a href="<s:url value="/iace/option/consult/index"/>">諮詢類型代</a></li>
					        <li><a href="<s:url value="/iace/option/cooperateMode/index"/>">合作模式</a></li>
					        <li><a href="<s:url value="/iace/option/grbDomain/index"/>">GRB領域別</a></li>
					        <li><a href="<s:url value="/iace/option/hadTecSrc/index"/>">企業已有技術來源</a></li>
					        <li><a href="<s:url value="/iace/option/industry/index"/>">產業/領域別</a></li>					        
					        <li><a href="<s:url value="/iace/option/organizationClass/index"/>">單位類別</a></li>
					        <li><a href="<s:url value="/iace/option/organizationType/index"/>">單位類型</a></li>
					        <li><a href="<s:url value="/iace/option/trl/index"/>">發展階段</a></li>
					        <li><a href="<s:url value="/iace/option/subject/index"/>">科技部學門</a></li>
					        <li><a href="<s:url value="/iace/option/school/index"/>">學校</a></li>
						</ul>
					</li>
				</ul>
    		</div>

    	</div>
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
			<div class="subLink">
				<img src="<s:url value="/images/footerLOGO.gif"/>" >
			</div>
			<div class="contact">
				科技部鏈結產學合作計畫辦公室/服務專線：02-27377373/聯絡地址：台北市和平東路二段106號<br>
				網站維護：財團法人國家實驗研究院科技政策研究與資訊中心<br> 請用Google Chrome 或 IE 9.0 以上版本瀏覽
				最佳觀看解析度1200x800
			</div>
		</div>
	</footer>
</body>
</html>