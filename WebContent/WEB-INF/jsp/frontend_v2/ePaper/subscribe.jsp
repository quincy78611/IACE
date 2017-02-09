<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('ul.categoryList li:contains("電子報訂退閱")').addClass("active");
		
		$("form").submit(function () {
			if ($("#readPolicyCheck").prop("checked")) {
				return true;
			} else {
				alert("請選擇同意接受「個人資料蒐集、處理及利用之告知暨同意條款」");
				return false;
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
						<i class="fa fa-user-circle-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>電子報
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
				<div class="large_title_03">電子報訂退閱</div>
				<div class="line_solid"></div>
				<div class="content_01 top10 text-center">
					請輸入以下資訊，即可完成電子報訂退閱！(<span style="color: #F00;"> * </span>為必填)
				</div>
				<div class="content_01 well top10">
					<s:form namespace="/f2/ePaper" action="subscribeSubmit" method="post" validate="true" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">訂 / 退閱<span style="color: #F00;">*</span></label>
							<div class="col-sm-9">
								<s:radio name="subscriber.isSubscribe" list='#{"true":"訂閱", "false":"退閱"}' value="true" />
							</div>
						</div>					
						<div class="form-group">
							<label class="col-sm-3 control-label">姓　　名<span style="color: #F00;">*</span></label>
							<div class="col-sm-9">
								<s:textfield name="subscriber.name" maxlength="100" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">聯絡電話</label>
							<div class="col-sm-9">
								<s:textfield name="subscriber.tel" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">E-MAIL<span style="color: #F00;">*</span></label>
							<div class="col-sm-9">
								<s:textfield name="subscriber.email" maxlength="200" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label>
										<input type="checkbox" id="readPolicyCheck"> 同意【<a href="<s:url value="/f2/websiteService/privacy"/>" target="_blank">個人資料蒐集、處理及利用之告知暨同意條款</a>】
									</label>
								</div>
							</div>
						</div>
						<div class="form-group form-inline">
							<label for="" class="col-sm-3 control-label">驗證碼<span style="color:#F00;">*</span></label>
							<div class="col-sm-9">
								<s:include value="/WEB-INF/jsp/captcha.jsp" />
							</div>
						</div>
						<div class="line_gray1px bottom10 top30"></div>
						<div class="form-group">
							<div class="col-sm-12 text-center">
								<button type="submit" class="btn btn-primary">確定送出</button>
								<button type="reset" class="btn btn-default">清除重填</button>
							</div>
						</div>						
					</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>