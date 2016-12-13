<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<div class="col-sm-12 col-xs-12">
	<div class="well well-sm">
		<table class="table content_03" style="margin-bottom: 0;">
			<tbody>
				<tr>
					<td width="10%" style="border: none;"><h4><span class="label label-info">研發成果</span></h4></td>
					<td width="10%" style="border: none;" class="date_01">計畫名稱</td>
					<td style="border: none;">
						<a href="#" class="list_link_01">
							<s:property value="technology.researchPlan.name"/>
						</a>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="date_01">主持人</td>
					<td><s:property value="technology.researchPlan.manager"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="date_01">年度</td>
					<td><s:property value="technology.researchPlan.year"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="date_01">研究領域</td>
					<td>
						<s:if test="technology.researchPlan.grbDomains != null">
							<s:iterator value="technology.researchPlan.grbDomains" status="stat">
								<s:property value="showString"/>&nbsp;&nbsp;&nbsp;
							</s:iterator>
						</s:if>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="date_01">技術名稱</td>
					<td><s:property value="technology.name"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="date_01">技術簡述</td>
					<td><s:property value="technology.descriptoin"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
