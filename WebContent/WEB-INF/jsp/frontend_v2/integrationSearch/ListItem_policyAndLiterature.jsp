<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<div class="col-sm-12 col-xs-12">
	<div class="well well-sm">
		<table class="table content_03" style="margin-bottom: 0;" border="0">
			<tbody>
				<tr>
					<td width="10%" style="border: none;"><h4>
						<span class="label label-purple">法規/文獻</span>
					</h4></td>
					<td width="10%" style="border: none;" class="date_01">題名</td>
					<td style="border: none;"><a href="#" class="list_link_01"><s:property value="literature.title"/></a></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="date_01">語文</td>
					<td><s:property value="literature.language"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="date_01">出版年</td>
					<td><s:property value="literature.publishYear"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>