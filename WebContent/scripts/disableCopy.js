$(document).ready(function() {
	$(".disableCopy").bind({
		copy : function(e){
			alert('禁止複製，請尊重撰稿人住著作權!');
			e.preventDefault();
			return false;
		},
		cut : function(){
			alert('禁止複製，請尊重撰稿人住著作權!');
			e.preventDefault();
			return false;
		}
	});
});