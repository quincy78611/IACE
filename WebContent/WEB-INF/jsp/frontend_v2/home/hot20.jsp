<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<style>
.num_circle {
	background-color:#fff; 
	border-radius:12px; 
	font-size:14px; 
	width:24px; 
	height:24px;
	text-align:center;
	vertival-align:middle;
	padding:2px 0;
}
</style>


<!-- HOT 20 -->
<div class="hot20">
	<div class="container">
		<div class="row" id="">
			<div class="col-sm-12">
				<div style="width:90px; float:left; padding:16px 0;">
					<i class="fa fa-fire right5" aria-hidden="true"></i><strong>HOT 20 :</strong>
				</div>
				
				<div class="content_01" style="float:left;">
					<table class="table" style="margin-bottom:0;">
						<tbody>
							<tr>
								<td width="40" style="border:none;">
									<div class="num_circle">1</div>
								</td>
								<td width="90" style="border:none;" class="hidden-xs hidden-sm">
									<s:date name="hot20ActivityList[0].postDate" format="yyyy/MM/dd" />
								</td>
								<td width="90" style="border:none;" class="hidden-xs hidden-sm">
									<h4><span class="label label-info"><s:property value="hot20ActivityList[0].category" /></span></h4>
								</td>
								<td style="border:none;">
									<s:url value="/f2/activity/showDetail" var="detailUrlTag" escapeAmp="false">
										<s:param name="id" value="hot20ActivityList[0].id" />
										<s:param name="searchCondition.category" value="hot20ActivityList[0].category" />
									</s:url>
									<a href="<s:property value="%{#detailUrlTag}"/>" class="list_link_01">
										<div class="truncate_hot"><s:property value="hot20ActivityList[0].title" /></div>
									</a>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="table" style="margin-bottom:0;display:none;" id="hot20">
						<tbody>
							<s:iterator value="hot20ActivityList" status="stat" begin="1" end="19">
								<tr>
									<td width="40" style="border:none;">
										<div class="num_circle"><s:property value="%{#stat.index+2}"/></div>
									</td>
									<td width="90" style="border:none;" class="hidden-xs hidden-sm">
										<s:date name="postDate" format="yyyy/MM/dd" />
									</td>
									<td width="90" style="border:none;" class="hidden-xs hidden-sm">
										<h4><span class="label label-info"><s:property value="category" /></span></h4>
									</td>
									<td style="border:none;">
										<s:url value="/f2/activity/showDetail" var="detailUrlTag" escapeAmp="false">
											<s:param name="id" value="id" />
											<s:param name="searchCondition.category" value="category" />
										</s:url>
										<a href="<s:property value="%{#detailUrlTag}"/>" class="list_link_01">
											<div class="truncate_hot"><s:property value="title" /></div>
										</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				
				<div style="float:right; margin:12px 0;">
					<div>
						<a href="#####" onclick="$('#hot20').slideToggle('fast');$('#hot20_close').show();$('#hot20_open').hide();" id="hot20_open"><img src="<s:url value="/images/frontend-v2/more_01.png"/>" alt="" height="30"/></a>
						<a href="#####" onclick="$('#hot20').slideToggle('fast');$('#hot20_close').hide();$('#hot20_open').show();" style="display:none;" id="hot20_close"><img src="<s:url value="/images/frontend-v2/more_02.png"/>" alt="" height="30"/></a>						
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>