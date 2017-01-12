<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn-reset").click(function() {
			$("input[type=text]").val("");
			$("select").prop('selectedIndex', 0);
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
						<i class="fa fa-file-text-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>影片專區
					</div>
					<div class="line_blue">&nbsp;</div>
					<div class="line_gray1px bottom20"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- 右列表 -->
			<div class="col-sm-12 col-xs-12 bottom20">
				<s:form action="index" method="post" validate="true">
					<div>
						<s:if test="videosAreaPagedList != null">
							<s:iterator value="videosAreaPagedList.list" status="stat">
								<div class="row">
									<div class="col-lg-3 col-md-4 col-sm-12 col-cs-12">
										<div class="list-thumbnail img_radius">
											<s:if test="base64Thumbnail != null && thumbnail.length > 0">
												<img src="data:image;base64,<s:property value="base64Thumbnail"/>" class="img-responsive" alt=""/>
											</s:if>
											<s:else>
												<img src="<s:url value="/images/frontend-v2/noimage-2.gif"/>" class="img-responsive" alt=""/>
											</s:else>
										</div>
									</div>
									<div class="col-lg-9 col-md-8 col-sm-12 col-cs-12">
										<div>
											<s:url value="/f2/videosArea/showDetail" var="detailUrlTag" escapeAmp="false">
												<s:param name="id" value="id" />
												<s:param name="searchCondition.pageIndex" value="searchCondition.pageIndex" />
												<s:param name="searchCondition.pageSize" value="searchCondition.pageSize" />
											</s:url>
											<a href="<s:property value="%{#detailUrlTag}"/>" class="list_link_01" style="color:#1EB0FF;">
												<s:property value="title" />
											</a>
										</div>
										<div class="date_01">日期：<s:date name="createTime" format="yyyy/MM/dd" /></div>
										<div class="date_01">地點：<s:property value="actAddress" /></div>
										<div class="top10 truncate_list"><s:property value="contentWithoutHTML"/></div>
									</div>
								</div>
								<div class="line_solid"></div>
							</s:iterator>
						</s:if>	
					</div>
					<!-- 換頁 -->
					<s:include value="./pagination.jsp" />
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>