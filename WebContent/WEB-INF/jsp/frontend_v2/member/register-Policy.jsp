<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('ul.categoryList li:contains("加入會員")').addClass("active");
		
		$("#btn-agree").click(function(){
			if ($("#readPolicyCheck").prop("checked")) {
				window.location.href='<s:url value="/f2/member/register"/>';
			} else {
				alert("請選擇同意接受「本人已閱讀、瞭解並同意接受Link-IAC網站會員服務條款，約定書之所有內容，本項同意得以電子文件方式表達，勾選表示同意並開始註冊會員資料。」");
			}
		});
	});
</script>
</head>
<body>
	<!-- Banner -->
	<s:include value="./banner.jsp" />
	
	<!-- Main -->
	<div class="container top50">
		<div class="row">
			<div class="col-sm-12 col-xs-12">
				<div>
					<div class="large_title_01">
						<i class="fa fa-user-circle-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>會員中心
					</div>
					<div class="line_blue">&nbsp;</div>
					<div class="line_gray1px bottom20"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- 左選單 -->
			<div class="col-sm-3 col-xs-12 bottom20">
				<s:include value="./left_menu.jsp" />
			</div>
			<!-- 右列表 -->
			<div class="col-sm-9 col-xs-12">
				<div class="large_title_03">加入會員</div>
				<div class="content_01 top10 text-center">
					在您註冊之前，請先閱讀本站的服務條款
				</div>
				<div class="form-group">
					<label class="checkbox-inline"> 
						<input type="checkbox" id="readPolicyCheck" />
						本人已閱讀、瞭解並同意接受Link-IAC網站會員服務條款，約定書之所有內容，本項同意得以電子文件方式表達，勾選表示同意並開始註冊會員資料。 
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button type="button" class="btn btn-primary" id="btn-agree">同意</button>
						<button type="button" class="btn btn-default" onclick="window.location.href='<s:url value="/f2/home/init"/>'">不同意</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>		