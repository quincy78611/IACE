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
	<script type="text/javascript" src="<s:url value="/scripts/calendarBox.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/jquery.datetimepicker.full.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/menu.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/vmenuModule.js"/>"></script>
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/layout_backend.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/btn.file.browse.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquery.datetimepicker.css"/>" />
	
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
<!-- 	    <div class="Link">
	    	帳號<input name="" type="text">
	    	密碼<input name="" type="text">
	    	<a href="#" class="login">登入</a>
	    </div> -->
	</header>
	<article>
   		<h1 class="pageTitle">
   			<s:if test="%{title != null && title != ''}">
   				<s:property value="title" />
   			</s:if>
   		</h1>
    	<div class="leftMenu">
    		<div class="u-vmenu">
    			<ul>
					<li><a href="#">研發成果</a>
						<ul>
							<li><a href="<s:url value="/researchPlan/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/batchImport/init"/>">批次匯入</a></li>
						</ul>
					</li>    			
					<li><a href="#">專利資料</a>
						<ul>
							<li><a href="<s:url value="/patent/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/patent/create"/>">新增</a></li>
							<li><a href="<s:url value="/batchImport/init"/>">批次匯入</a></li>
						</ul>
					</li>
					<li><a href="#">諮詢服務表</a>
						<ul>
							<li><a href="<s:url value="/consulting/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/consulting/create"/>">新增</a></li>
						</ul>
					</li>
					<li><a href="#">企業需求單</a>
						<ul>
							<li><a href="<s:url value="/enterpriseNeed/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/enterpriseNeed/create"/>">新增</a></li>
						</ul>
					</li>					
					<li hidden="hidden"><a href="#">問卷調查模板</a>
						<ul>
							<li><a href="<s:url value="/qnrTemplate/init"/>">編輯管理</a></li>
							<li><a href="<s:url value="/qnrTemplate/create"/>">新增</a></li>
						</ul>
					</li>	
					<li hidden="hidden"><a href="#">問卷</a>
						<ul>
							<s:iterator value="qnrTemplateList" status="stat">
								<li>
									<s:url value="/questionnaire/init" var="initUrlTag" escapeAmp="false">
										<s:param name="qnrTableId" value="id" />
										<s:param name="qnrTableName" value="tableName" />
									</s:url>
									<a href='<s:property value="initUrlTag" />'>
										<s:property value="%{'維護 '+name}"/>
									</a>
								</li>
								<li>	
									<s:url value="/questionnaire/create" var="createUrlTag" escapeAmp="false">
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
					<li><a href=#>產學合作問卷</a>
						<ul>
							<li><a href="<s:url value="/qnrCooperateWay/index"/>">問卷管理</a></li>
						</ul>
					</li>
					<li><a href="#">代碼管理</a>
						<ul>
					    	<li><a href="<s:url value="/option/country/index"/>">專利國別</a></li>
					        <li><a href="<s:url value="/option/companyLocation/index"/>">公司地域別</a></li>
					        <li><a href="<s:url value="/option/consult/index"/>">諮詢類型代</a></li>
					        <li><a href="<s:url value="/option/cooperateMode/index"/>">合作模式</a></li>
					        <li><a href="<s:url value="/option/grbDomain/index"/>">GRB領域別</a></li>
					        <li><a href="<s:url value="/option/hadTecSrc/index"/>">企業已有技術來源</a></li>
					        <li><a href="<s:url value="/option/industry/index"/>">產業/領域別</a></li>	
					        <li><a href="<s:url value="/option/industryForEnterprise/index"/>">企業需求單 產業類別</a></li>				        
					        <li><a href="<s:url value="/option/organizationClass/index"/>">單位類別</a></li>
					        <li><a href="<s:url value="/option/organizationType/index"/>">單位類型</a></li>
					        <li><a href="<s:url value="/option/trl/index"/>">發展階段</a></li>
					        <li><a href="<s:url value="/option/subject/index"/>">科技部學門</a></li>
					        <li><a href="<s:url value="/option/school/index"/>">學校</a></li>
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
				科技部鏈結產學合作計畫辦公室 / 服務專線：02-27377373 / 聯絡地址：台北市和平東路二段106號<br>
				網站維護：財團法人國家實驗研究院科技政策研究與資訊中心<br> 
				請用Google Chrome 或 IE 9.0 以上版本瀏覽最佳觀看解析度1200x800
			</div>
		</div>
	</footer>
</body>
</html>