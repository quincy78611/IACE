<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<!-- 產學合作案例 -->
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-xs-12 top50">
			<div>
				<div class="large_title_01">
					<i class="fa fa-handshake-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>產學合作案例
					<div class="pull-right">
						<a href="<s:url value="/f2/integrationSearch/init"/>"> 
							<img src="<s:url value="/images/frontend-v2/more_blue.png"/>" alt="" height="30" />
						</a>
					</div>
				</div>
				<div class="line_blue">&nbsp;</div>
				<div class="line_gray1px"></div>
			</div>
		</div>
	</div>
	<div class="row">
		<s:iterator value="coopExList" status="stat">
			<div class="col-sm-4 col-xs-12 top20">
				<div>
					<img src="data:image;base64,<s:property value="base64Thumbnail"/>" class="img-responsive img_radius" alt="" />
				</div>
				<div class="text-center top10">
					<a href="#" class="list_link_01"><s:property value="title" /></a>
				</div>
				<div class="text-center date_01">
					<s:date name="createTime" format="yyyy/MM/dd" />
				</div>
			</div>
		</s:iterator>
		<div class="col-sm-12 col-xs-0 top20">
			<div class="line_gray1px"></div>
		</div>
	</div>
</div>
