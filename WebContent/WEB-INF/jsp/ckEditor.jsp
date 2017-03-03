<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<!-- 網頁編輯器 -->
<input type="hidden" id="ckeditorImageUploadUrl" value="<s:url value="/file/ckEditorUploadFile"/>" disabled="disabled">
<script type="text/javascript" src="<s:url value="/scripts/ckeditor-4.6.2/ckeditor.js"/>"></script>
