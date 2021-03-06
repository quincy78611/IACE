// 網頁編輯器設定 
// obj : 請傳入需要套用此編輯器的 textArea 的 jquery selector string, ex:'textArea[name="activity.content"]'
function defaultTinymceEditor(obj) {
	tinymce.init({
		selector: obj,
		plugins: [
		  'advlist autolink link image paste imagetools lists charmap print preview hr anchor pagebreak spellchecker',
		  'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
		  'save table contextmenu directionality emoticons template paste textcolor'
		],
		content_css: 'css/content.css',
		menubar: 'file edit view format tools table',
		toolbar: [
			'code fullscreen | undo redo | cut copy paste | bold italic underline strikethrough | styleselect formatselect fontselect fontsizeselect  ',
			'bullist numlist outdent indent | alignleft aligncenter alignright alignjustify | link image media searchreplace table | forecolor backcolor'
		],
		automatic_uploads: true,
		height: 400,
//		relative_urls : false,
		remove_script_host : false,
		paste_data_images: true
	});
	
	$(document).ready(function() {
		var comment = '<label style="font-size:14px; color:#b9925b; margin-right:10px">(可直接複製貼上圖片，或是直接將圖片拖曳到想要的位置)</label>';
//		$(comment).insertBefore(obj);
		$(comment).insertAfter(obj);
	});
}

function readonlyTinymceEditor(obj) {
	tinymce.init({
		selector: obj,
		plugins: [
		  'advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker',
		  'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
		  'save table contextmenu directionality emoticons template paste textcolor'
		],
		readonly : 1,
		content_css: 'css/content.css',
		menubar: '',
		toolbar: [],
		height: 400,
		relative_urls : false,
		remove_script_host : false
	});
}
	
