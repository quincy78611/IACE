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
	<title>科技部iACE鏈結產學媒合平台</title>
	
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/frontend/layout.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/alert.css"/>" />	
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquery.datetimepicker.css"/>" />
	
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/rightContentElement.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/btn.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquerysctipttop.css"/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/pageBtnList.css"/>" />
	
	<style>
		.rightContent { width:1024px; margin: -20px auto 15px auto; }
		.frontend table th { background: #a0c7d5; }
	</style>
	
	<script type="text/javascript" src="<s:url value="/scripts/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/jquery.validate.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/frontend/backtotop.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/frontend/menu.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/calendarBox.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/scripts/jquery.datetimepicker.full.js"/>"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			setInterval(function () {
				$("#div-top-message").hide();
			}, 5000);
		});
	</script>
	
	<decorator:head />
</head>
<body>
	<header>
		<div id="con">
			<div id="LOGO">
				<a href="<s:url value="/home/init"/>">
					<img src="<s:url value="/images/frontend/LOGO.png"/>" >
				</a>
			</div>
			<div id="login">
				<s:if test="#session.sysUser == null">
					<s:form namespace="/login" action="loginSubmit" method="post" validate="true" >
						<input type="text" name="sysUser.account" autocomplete="off" placeholder="帳號"/>
						<input type="password" name="sysUser.password" autocomplete="off" placeholder="密碼"/>
						<input type="submit" value="登入" class="btnRed" />
<!-- 						<input type="submit" value="忘記密碼" class="btnGray" /> -->
					</s:form>
				</s:if>
				<s:else>
					<label><s:property value="%{#session.sysUser.name}"/>&nbsp;&nbsp;</label>
					<a href="<s:url value="/login/logout"/>" class="btnB">登出</a>
				</s:else>
			</div>
			<div class="clear"></div>
			<div id="link">
				<a href="<s:url value="/home/init"/>">首頁</a>│
				<a>關於I-ACE</a>│
				<a>聯絡我們</a>
			</div>
			<div class="clear"></div>
			<s:if test="%{title != null && title != ''}">
				<h1><s:property value="title" /></h1>
			</s:if>
		</div>
	</header>
	<article>
		<div class="">
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
		<div id="footerZone">
			<div id="LOGO"><img src="<s:url value="/images/frontend/LOGO-S.png"/>" ></div>
			<div id="qrcode"><img src="<s:url value="/images/frontend/qrCode.gif"/>" ></div>
			<div id="con">版權所有©2016科技部產學及園區業務司<br>Department of Academia-Industry Collaboration and Science Park Affairs, Academy and Science Park Affairs<br>本網站設計支援IE、Firefox及Chrome，網頁設計 最佳瀏覽解析度為1024x768以上</div>
			<div id="twca">
				<div id="twcaseal" class="ssl-logo MEDIUM">
					<img src="<s:url value="/images/TWCA-SSL-LOGO-MEDIUM.gif"/>" >
				</div>
				<script type="text/javascript" charset="utf-8" >
					var twca_cn="iace.stpi.narl.org.tw";
				</script>
				<script type="text/javascript" src="//ssllogo.twca.com.tw/twcaseal_v3.js"charset="utf-8"></script> 
			</div>
		</div>
		<div class="clear"></div>
	</footer>
</body>
</html>