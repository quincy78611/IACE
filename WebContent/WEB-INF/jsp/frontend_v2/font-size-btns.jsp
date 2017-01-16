<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<script type="text/javascript">
	$(document).ready(function() {
		$(".btn-reduce-font-size").click(function(){
			$(".content_03").css("font-size", parseInt($(".content_03").css("font-size"))-1+"px");
			$(".date_01").css("font-size", parseInt($(".date_01").css("font-size"))-1+"px");
		});
		$(".btn-enlarge-font-size").click(function(){
			$(".content_03").css("font-size", parseInt($(".content_03").css("font-size"))+1+"px");
			$(".date_01").css("font-size", parseInt($(".date_01").css("font-size"))+1+"px");
		});		
	});
</script>

<ul class="list-inline text-right" style="margin-bottom: 0;">
	<li class="nopadding">
		<button type="button" class="btn btn-default btn-reduce-font-size">
			<i class="fa fa-minus-square right5" aria-hidden="true"></i>縮小
		</button>
	</li>
	<li class="nopadding">
		<button type="button" class="btn btn-default btn-enlarge-font-size">
			<i class="fa fa-plus-square right5" aria-hidden="true"></i>放大
		</button>
	</li>
</ul>