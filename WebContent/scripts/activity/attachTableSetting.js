$(document).ready(function() {
	addMoreAttach();
	fileBrowseSetting();
});

function addMoreAttach() {	
	$("input[type=button].btn-addMoreAttach").click(function(){
		var newTr = $(this).parents("table").find("tr.hidden-sample-tr").clone();
		newTr.removeClass("hidden-sample-tr");
		$(this).parents("table").find("tbody").append(newTr);
		fileBrowseSetting();
		resetNameAttrForAttachTable();
	});
}
function fileBrowseSetting() {
	$(".btn-fake-browse").click(function(){
		$(this).parents("tr").find("input[type=file]").trigger("click");
	});
	
	$("input[type=file]").change(function() {
		var fileName = $(this).get(0).files[0].name;
		$(this).parents("tr").find(".label-fileName").html(fileName);
		readURL(this);
	});
	
	$("input[type=button].cancelSelectFile").click(function(){
		var rowCount = $(this).parents("tbody").find("tr").length;
		$(this).parents("tr").remove();
		resetNameAttrForAttachTable();
	});
}
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.readAsDataURL(input.files[0]);
    }
}
function resetNameAttrForAttachTable() {
	$("#table-attach > tbody > tr").each(function( index ){
		var tr = $("#table-attach > tbody > tr").eq(index);
		tr.find(".id").attr("name", "activity.attachList["+index+"].id");
		tr.find(".upload").attr("name", "activity.attachList["+index+"].upload");
		tr.find(".fileType").attr("name", "activity.attachList["+index+"].fileType");
		tr.find(".fileTitle").attr("name", "activity.attachList["+index+"].fileTitle");
	});
}