$(document).ready(function() {
	addMoreAttach();
	
	$("input[type=file][name='videosArea.uploadThumbnail']").change(function() {
		setUploadToImage(this); // display subnail
	});
});

//==============================================================================
function setUploadToImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
        	$(input).parents("li").find("img").attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
//==============================================================================

function addMoreAttach() {	
	$("input[type=button].btn-addMoreAttach").click(function(){
		var newTr = $(this).parents("table").find("tr.hidden-sample-tr").clone();
		newTr.removeClass("hidden-sample-tr");
		$(this).parents("table").find("tbody").append(newTr);
		resetNameAttrForAttachTable();
	});
}

function fakeBrowseBtnOnclick(this_) {
	$(this_).parents("tr").find("input[type=file]").trigger("click");
}
function deleteFileBtnOnclick(this_) {
	var rowCount = $(this_).parents("tbody").find("tr").length;
	$(this_).parents("tr").remove();
	resetNameAttrForAttachTable();
}
function fileOnchange(this_) {
	var fileName = $(this_).get(0).files[0].name;
	$(this_).parents("tr").find(".label-fileName").html(fileName);
	readURL(this_);
}
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		
		reader.onload = function (e) {
			$(input).parents("tr").find("video").attr('src', e.target.result);
		}
		
		reader.readAsDataURL(input.files[0]);
	}
}
function resetNameAttrForAttachTable() {
	$("#table-attach > tbody > tr").each(function( index ){
		var tr = $("#table-attach > tbody > tr").eq(index);
		tr.find(".id").attr("name", "videosArea.videoList["+index+"].id");
		tr.find(".upload").attr("name", "videosArea.videoList["+index+"].upload");
		tr.find(".fileType").attr("name", "videosArea.videoList["+index+"].fileType");
		tr.find(".fileTitle").attr("name", "videosArea.videoList["+index+"].fileTitle");
	});
}