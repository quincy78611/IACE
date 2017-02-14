<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<style>
#popup-message-dialog {
	display: none;
	position: fixed; /* Stay in place */
	z-index: 10; /* Sit on top */
 	left: 0;
 	top: 0;
 	width: 100%; /* Full width */ 
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	text-align: center;
}

#popup-message-dialog .modal-content {
	background-color: #fefefe;
 	margin: 20% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
}
</style>

<script>
$(document).ready(function() {
	if ($(".hasMessage").val()) {
		$("#popup-message-dialog").show();
	}
	
	$("#popup-message-dialog").find(".btn-close").click(function(){
		$("#popup-message-dialog").hide();
	});
});
</script>

<div id="popup-message-dialog" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<p>			
			<s:if test="hasActionMessages()">
				<s:actionmessage />
				<input type="hidden" class="hasMessage" value="true"/>
			</s:if>
			<s:if test="hasActionErrors()">
				<s:actionerror />
				<input type="hidden" class="hasMessage" value="true"/>
			</s:if>
		</p>
		<br>
		<input type="button" class="btn-close grayBtn" value="關閉"/>
	</div>
</div>