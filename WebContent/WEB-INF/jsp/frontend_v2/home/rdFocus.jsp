<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>


<div class="container top30">
	<div class="row">
		<!-- 研發焦點 -->
		<div class="col-sm-12 col-xs-12">
			<div>
				<div class="large_title_01">
					<i class="fa fa-file-text-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>研發焦點
					<div class="pull-right">
						<s:url value="/f2/rdFocus/init" var="urlTag" escapeAmp="false">
							<s:param name="searchCondition.category" value="@iace.entity.rdFocus.RdFocus@getCategoryList()[0].code" />
						</s:url>
						<a href="<s:property value="urlTag"/>"> 
							<img src="<s:url value="/images/frontend-v2/more_blue.png"/>" alt="" height="30" />
						</a>
					</div>
				</div>
				<div class="line_blue">&nbsp;</div>
				<div class="line_gray1px"></div>
			</div>
			<div>
				<table class="table" style="table-layout:fixed;">
					<tbody>
						<s:iterator value="rdFocusList" status="stat">
						<tr>
							<td style="border:none; width:80px;">
								<span class="label label-info"><s:property value="categoryShort" /></span>
							</td>
							<td style="border:none;">
								<s:url value="/f2/rdFocus/showDetail" var="detailUrlTag" escapeAmp="false">
									<s:param name="id" value="id" />
									<s:param name="searchCondition.category" value="category" />
								</s:url>
							
								<div class="single-line-truncate">
									<span class="date_01"><s:date name="postDate" format="yyyy/MM/dd" /></span>&nbsp;
									<a href="<s:property value="%{#detailUrlTag}"/>" class="list_link_01">
										<s:property value="title" />
									</a>
								</div>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>