<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="funcPathText" content="新增"/>
</head>
<body>
	<s:form action="createSubmit" method="post" validate="true">
		<ul>						
			<li class="all">
				<b>標題</b>
				<s:textfield name="faq.title" maxlength="200"/>
			</li>
			<li class="half">
				<b>來源</b>
				<s:textfield name="faq.source" maxlength="200"/>
			</li>
			<li class="half">
				<b>分類</b>
				<s:select name="faq.category" list="categoryList" listKey="code" listValue="name" />
			</li>
			<li class="all">
				<b>內容</b>
				<s:textarea name="faq.content" />
				<div>
					<!-- 網頁編輯器 -->
					<s:include value="/WEB-INF/jsp/ckEditor.jsp" />
					<script type="text/javascript">
					window.onload = function() {
						CKEDITOR.replace('faq.content'); // 此處參數 'about.content' 為需要套用ckeditor 的 textarea 的 name
					};
					</script>
				</div>
			</li>		
		</ul>
		<div class="clear"></div>
			
		<h2>SEO區(網站優化加註，幫助訊息更易搜尋到)</h2>
		<ul class="common-meta-fields">
			<li class="all">
				<b>Meta Title</b>
				<s:textfield name="faq.metaTitle" maxlength="200"/>
			</li>
			<li class="all">
				<b>Meta Description</b>
				<s:textfield name="faq.metaDes" />
			</li>
			<li class="all">
				<b>Meta KeyWord</b>
				<s:textfield name="faq.metaKeyword" />
			</li>
		</ul>
		<div class="clear"></div>
		
		<h2>狀態設定區</h2>
		<ul class="common-linkiac-fields">
			<li class="quarter">
				<b>瀏覽次數</b>
				<s:textfield name="faq.clickNum" type="number" value="0" min="0"/>
			</li>
			<li class="quarter">
				<b>排序(數字愈大排愈前面)</b>
				<s:textfield name="faq.sort" type="number" value="0"/>
			</li>
			<li class="quarter">
				<b>顯示狀態</b>
				<s:radio name="faq.displayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" value="true"/>
			</li>
			<li class="quarter">
				<b>首頁顯示</b>
				<s:radio name="faq.homeDisplayStatus" list="#{'true':'開啟', 'false':'關閉'}" class="horizontalList" value="true"/>
			</li>			
		</ul>		
		<div class="clear"></div>
		
		<div style="width: 80%; text-align: center; margin: 20px auto 40px auto;">
			<s:submit cssClass="redBtn" value="送出" />
		</div>		
	</s:form>
</body>
</html>