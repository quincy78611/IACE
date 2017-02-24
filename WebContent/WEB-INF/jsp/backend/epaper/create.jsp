<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".btn-dataSetting").click(function(){
			var url = $(this).siblings(".dataSettingUrl").val();
			$("form#form-create").attr('action', url);
			$("form#form-create").submit();
			$("form#form-create").attr('action', '<s:url value="/epaper/createSubmit"/>'); // 要把action改為原本的，否則如果使用者按下瀏覽器的上一頁回到目前這個列表頁再去按搜尋就會跑到已經被改變的action所指定的那一頁
		});
		$(".btn-del").click(function(){
			$(this).parents("tr").remove();
		});
		$("#btn-preview").click(function(e){
			$("form#form-create").attr("target", "_blank");
			$("form#form-create").attr('action', '<s:url value="/epaper/createPreview"/>');
			$("form#form-create").submit();
			$("form#form-create").attr('action', '<s:url value="/epaper/createSubmit"/>'); // 要把action改為原本的
			$("form#form-create").removeAttr("target");
		});
	});
</script>


<meta name="funcPathText" content="新增"/>
</head>
<body>
	<s:form action="createSubmit" method="post" validate="true" id="form-create">
		<ul>
			<li class="all">
				<b>標題</b>
				<s:textfield name="template.title" maxlength="200"/>
			</li>
			<div class="clear"></div>
			<li class="half">
				<b>期數</b>
				<s:textfield name="template.no" maxlength="5" type="number"/>
			</li>
			<li class="half">
				<b>發佈日</b>
				<s:textfield name="template.postDate" cssClass="calendarBox" maxlength="10">
					<s:param name="value">
						<s:date name="template.postDate" format="yyyy/MM/dd" /> 
					</s:param>
				</s:textfield>
			</li>
		</ul>
		<div class="clear"></div>
		
		<!-- 公告訊息 -->
		<div>
			<input type="hidden" class="dataSettingUrl" value="<s:url value="/epaper/newsIndex" />"/>
			<input type="button" value="公告訊息設定" class="btn-dataSetting"/>
			<table>
				<thead>
					<tr>
						<th width="10%">發佈日期</th>
						<th>標題</th>
						<th width="8%"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="template.newsList" status="stat">
						<tr>
							<s:hidden name="template.newsIds" value="%{id}"/>
							<td><s:date name="postDate" format="yyyy/MM/dd"/></td>
							<td><s:property value="title"/></td>
							<td><input type="button" class="btn-info btn-func btn-del" value="刪除" /></td>
						</tr>
					</s:iterator>				
				</tbody>
			</table>
		</div>
		
		<!-- 活動人培 -->
		<div>
			<input type="hidden" class="dataSettingUrl" value="<s:url value="/epaper/activityIndex" />"/>
			<input type="button" value="活動人培設定" class="btn-dataSetting"/>
			<table>
				<thead>
					<tr>
						<th>標題</th>
						<th>日期</th>
						<th>地點</th>
						<th width="8%"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="template.activityList" status="stat">
						<tr>
							<s:hidden name="template.activityIds" value="%{id}"/>
							<td><s:property value="title"/></td>
							<td><s:property value="actDate"/></td>
							<td><s:property value="actAddress"/></td>
							<td><input type="button" class="btn-info btn-func btn-del" value="刪除" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		
		<!-- 研發焦點 -->
		<div>
			<input type="hidden" class="dataSettingUrl" value="<s:url value="/epaper/rdFocusIndex" />"/>
			<input type="button" value="研發焦點設定" class="btn-dataSetting"/>
			<table>
				<thead>
					<tr>
						<th width="10%">發佈日期</th>
						<th>標題</th>
						<th width="8%"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="template.rdFocusList" status="stat">
						<tr>
							<s:hidden name="template.rdFocusIds" value="%{id}"/>
							<td><s:date name="postDate" format="yyyy/MM/dd"/></td>
							<td><s:property value="title"/></td>
							<td><input type="button" class="btn-info btn-func btn-del" value="刪除" /></td>
						</tr>
					</s:iterator>				
				</tbody>
			</table>
		</div>
		
		<!-- 研發成果 -->
		<div>
			<input type="hidden" class="dataSettingUrl" value="<s:url value="/epaper/researchPlanIndex" />"/>
			<input type="button" value="研發成果設定" class="btn-dataSetting"/>
			<table>
				<thead>
					<tr>
						<th>研究領域</th>
						<th>計畫名稱</th>
						<th width="8%"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="template.researchPlanList" status="stat">
						<tr>
							<s:hidden name="template.researchPlanIds" value="%{id}"/>
							<td><s:property value="grbDomain1.name"/></td>
							<td><s:property value="name"/></td>
							<td><input type="button" class="btn-info btn-func btn-del" value="刪除" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		
		<!-- 專利資料 -->
		<div>
			<input type="hidden" class="dataSettingUrl" value="<s:url value="/epaper/patentIndex" />"/>
			<input type="button" value="專利資料設定" class="btn-dataSetting"/>	
			<table>
				<thead>
					<tr>
						<th>專利技術領域</th>
						<th>專利名稱</th>
						<th width="8%"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="template.patentList" status="stat">
						<tr>
							<s:hidden name="template.patentIds" value="%{id}"/>
							<td><s:property value="techField.name"/></td>
							<td><s:property value="name"/></td>
							<td><input type="button" class="btn-info btn-func btn-del" value="刪除" /></td>
						</tr>					
					</s:iterator>
				</tbody>			
			</table>
		</div>
		
		<!-- 產業情報 -->
		<div>
			<input type="hidden" class="dataSettingUrl" value="<s:url value="/epaper/industryInfoIndex" />"/>
			<input type="button" value="產業情報 設定" class="btn-dataSetting"/>
			<table>
				<thead>
					<tr>
						<th width="10%">發佈日期</th>
						<th>標題</th>
						<th width="8%"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="template.industryInfoList" status="stat">
						<tr>
							<s:hidden name="template.industryInfoIds" value="%{id}"/>
							<td><s:date name="postDate" format="yyyy/MM/dd"/></td>
							<td><s:property value="title" escapeHtml="false"/></td>
							<td><input type="button" class="btn-info btn-func btn-del" value="刪除" /></td>
						</tr>
					</s:iterator>				
				</tbody>
			</table>			
		</div>
	
		<!-- 常問集 -->
		<div>
			<input type="hidden" class="dataSettingUrl" value="<s:url value="/epaper/faqIndex" />"/>
			<input type="button" value="常問集設定" class="btn-dataSetting"/>
			<table>
				<thead>
					<tr>
						<th>標題</th>
						<th width="8%"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="template.faqList" status="stat">
						<tr>
							<s:hidden name="template.faqIds" value="%{id}"/>
							<td><s:property value="title"/></td>
							<td><input type="button" class="btn-info btn-func btn-del" value="刪除" /></td>
						</tr>
					</s:iterator>				
				</tbody>
			</table>			
		</div>
	
		<div class="clear"></div>
		<div class="bottom-btn-block">
			<input type="button" value="預覽" class="redBtn" id="btn-preview"/>
			<s:submit cssClass="redBtn" value="建立" />
		</div>
	</s:form>
</body>
</html>