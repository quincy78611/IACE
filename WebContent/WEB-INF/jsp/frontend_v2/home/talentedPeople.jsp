<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<!-- 產學人才 -->
<div class="talent">
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-6 col-sm-6 col-xs-12 top50">
				<div>
					<div class="large_title_02">
						<i class="fa fa-users" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>產學人才
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
			<div class="col-sm-offset-6 col-sm-6 col-xs-12">
				<div class="top10">
					<table class="table">
						<tbody>
							<tr>
								<td width="20%" style="border: none;" class="small_title_02"><strong>姓名</strong></td>
								<td style="border: none;" class="small_title_02"><strong>合作專長</strong></td>
							</tr>
							<s:iterator value="talentedPeopleList" status="stat">
								<tr>
									<td class="small_title_02"><s:property value="nameCh" /></td>
									<td><a href="#" class="list_link_02"><s:property value="specialty" /></a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>