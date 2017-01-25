<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>


<style>
#clockdiv {
	font-family: sans-serif;
	color: #ffffff;
	display: inline-block;
	margin: auto 10px;
	font-weight: 100;
	text-align: center;
	font-size: 16px;
	position: relative;
/* 	float: left; */
	vertical-align: middle;
}

#clockdiv>div {
	padding: 5px;
	border-radius: 3px;
	background: #2872c6;
	display: inline-block;
}

#clockdiv div > span {
	padding: 5px;
	border-radius: 3px;
	background: #282870;
	display: inline-block;
}

#clockdiv div>input[type=button] {
	display:inline-block;
	text-align:center;
}

.smalltext {
	padding-top: 5px;
	font-size: 16px;
}

.modal {
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

.modal-content {
	background-color: #fefefe;
 	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
}
</style>

<script type="text/javascript" src="<s:url value="/scripts/jquery.timers-1.1.2.js"/>"></script>
<script>
var timeBeforeAlert = 15;
var sessionTime = <%= session.getMaxInactiveInterval() %>;
var endTime = new Date(Date.parse(new Date()) + (sessionTime) * 1000);
var logoutTimer = null;
var logoutAlertTimer = null;

$(document).ready(function() {
	startClock();
	startLogoutTimer();
	startLogoutAlertTimer();
	
	$("#logout-alert-dialog").find(".btn-yes").click(function(){
		restartTime();
		$("#logout-alert-dialog").hide();
	});

	$("#logout-alert-dialog").find(".btn-no").click(function(){
		$("#logout-alert-dialog").hide();
	});
});

function startClock() {
	$("#clockdiv").everyTime("1s", "clock", function(){
		var time = getTimeRemaining(endTime);
		if (time.total < 0) {
			$("#clockdiv").stopTime("clock");
		} else {
			updateClock(time);
		}
	});
}
function startLogoutTimer() {
	logoutTimer = window.setTimeout(function(){
		window.location = '<s:url value="/login/logout"/>';
	}, sessionTime*1000);
}
function startLogoutAlertTimer() {
	logoutAlertTimer = window.setTimeout(function(){
		$("#logout-alert-dialog").show();
	}, (sessionTime-timeBeforeAlert)*1000);
}

function restartTime() {
	$.ajax({
		url : '<s:url value="reflashSession.action"/>',
		type : 'GET',
		data : {
			time : new Date()
		},
		error : function(xhr) {
			alert('Ajax request 發生錯誤');
		},
		success : function(response) {
			endTime = new Date(Date.parse(new Date()) + (sessionTime) * 1000);
			clearTimeout(logoutTimer);
			startLogoutTimer();
			clearTimeout(logoutAlertTimer);
			startLogoutAlertTimer();
		}
	});
}

function updateClock(time) {
	$("#clockdiv").find(".minutes").html(('0' + time.minutes).slice(-2));
	$("#clockdiv").find(".seconds").html(('0' + time.seconds).slice(-2));
}

function getTimeRemaining(endtime) {
	var t = Date.parse(endtime) - Date.parse(new Date());
	var seconds = Math.floor((t / 1000) % 60);
	var minutes = Math.floor((t / 1000 / 60) % 60);
	var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
	var days = Math.floor(t / (1000 * 60 * 60 * 24));
	return {
		'total' : t,
		'days' : days,
		'hours' : hours,
		'minutes' : minutes,
		'seconds' : seconds
	};
}
</script>
<div id="clockdiv">
	<div>
		<span class="minutes">&nbsp;&nbsp;</span> 
		<span class="seconds">&nbsp;&nbsp;</span>
		<input type="button" class="btn-reset-clock" onclick="restartTime()" value="reset"/>
	</div>
</div>
<div id="logout-alert-dialog" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<p>即將登出，是否延長?</p>
		<br>
		<input type="button" class="btn-yes redBtn" value="是"/>
		<input type="button" class="btn-no grayBtn" value="否"/>
	</div>
</div>