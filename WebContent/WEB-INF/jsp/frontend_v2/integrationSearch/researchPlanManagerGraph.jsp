<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<script type="text/javascript" src="<s:url value="/scripts/graph/d3.v3.js"/>"></script>
<script type="text/javascript" src="<s:url value="/scripts/graph/cola.min.js"/>"></script>
<style>
@media screen and (min-width: 1024px) {
	#svg-container {
		width: 100%;
		height: auto;
		border: 2px #000000 solid;
	}
	svg {
		width: 100%;
		height: 40vw; /* 這邊如果使用auto，在IE上會變非常小 */
/* 		background-color: rgba(200, 200, 200, 0.5); */
	}
	.node {
		stroke: #fff;
		stroke-width: 1px;
		cursor: move;
	}
	.type-keyword {
		fill: #337ab7;
	}
	.type-manager {
		fill: #1fb5da;
	}
	.link {
		stroke: #999;
		stroke-width: 1px;
		stroke-opacity: 1;
	}
	.label {
		fill: #ffffff;
		font-family: Verdana;
		font-size: 12px;
		text-anchor: middle;
		cursor: pointer;
	}
}

@media screen and (max-width: 1023px) {
	#div-network-diagram {
		display: none;
		width:0px;
		height:0px;
	}
	#svg-container {
		display: none;
		width:0px;
		height:0px;
	}
	svg {
		display: none;
		width:0px;
		height:0px;
	}
}
</style>

<div id="div-network-diagram">
	<button class="btn btn-info btn-svg-toggle" id="btn-svg-open" type="button">
		<i class="fa fa-plus right5" aria-hidden="true"></i>展開網絡圖
	</button>
	<button class="btn btn-info btn-svg-toggle" id="btn-svg-close" type="button">
		<i class="fa fa-minus right5" aria-hidden="true"></i>收合網絡圖
	</button>
	<div id="svg-container">
		<label>註1：本網絡圖只代表主持人之研究計畫中出現完全相符之關鍵詞</label><br>
		<label>註2：點選「關鍵詞」可返回前畫面</label>
	</div>
	<s:hidden name="searchCondition.researchPlanManager"/>
	<s:hidden name="svgDisplayStatus"/>
</div>

<script>
	$("#btn-svg-open").click(function(){
		$(".btn-svg-toggle").toggle();
		$("#svg-container").slideDown();
		$("input[name=svgDisplayStatus]").val(true);
	});
	$("#btn-svg-close").click(function(){
		$(".btn-svg-toggle").toggle();
		$("#svg-container").slideUp();
		$("input[name=svgDisplayStatus]").val(false);
	});
	
	$(document).ready(function() {
// 		$("#svg-container").toggle();//不可以用toggle否則在firefox上的Text標籤會無法正確顯示

		var svgDisplayStatus = $("input[name=svgDisplayStatus]").val();
		if (svgDisplayStatus == "true") {
			$("#btn-svg-open").hide();
			$("input[name=svgDisplayStatus]").val(true);
		} else {
			$("#btn-svg-close").hide();
			$("#svg-container").slideUp();
			$("input[name=svgDisplayStatus]").val(false);
		}
	}); 
</script>
<script>
	var res_data_jsonString = '<s:property value="rpManagerJsonString"/>'.replace(/&quot;/g,'\"');
	var res_data = JSON.parse(res_data_jsonString);
	var width = 800, height = 600;
// 	var width = (res_data.nodes.length^0.5)*10+400;
// 	var height = (res_data.nodes.length^0.5)*8+300;

	//==========================================================================
	
	var cola = cola.d3adaptor()
		.linkDistance(120)
		.avoidOverlaps(true)
		.size([width, height]);

	var svg = d3.select("#svg-container").append("svg")
		.attr("viewBox", "0 0 "+width+" "+height)
		.attr("width", width)
		.attr("height", height);
	
	cola.nodes(res_data.nodes)
		.links(res_data.links)
		.start();

	var link = svg.selectAll(".link")
		.data(res_data.links)
		.enter().append("line")
		.attr("class", "link");

	var node = svg.selectAll(".node")
		.data(res_data.nodes)
		.enter().append("rect")
		.attr("class", function (d) { return "node "+d.type; })
		.attr("width", function (d) { return d.width; })
		.attr("height", function (d) { return d.height; })
		.attr("rx", 5).attr("ry", 5)
		.call(cola.drag);

	var label = svg.selectAll(".label")
		.data(res_data.nodes)
		.enter().append("text")
		.attr("class", "label")
		.attr("type", function (d) { return d.type; })
		.attr("value", function (d) { return d.nodeValue; })
		.text(function (d) { return d.displayText; })
		.call(cola.drag);

	node.append("title")
		.text(function (d) { return d.displayText; });

	cola.on("tick", function () {
		link.attr("x1", function (d) { return d.source.x; })
			.attr("y1", function (d) { return d.source.y; })
			.attr("x2", function (d) { return d.target.x; })
			.attr("y2", function (d) { return d.target.y; });

		node.attr("x", function (d) { return d.x - d.width / 2; })
			.attr("y", function (d) { return d.y - d.height / 2; });

		label.attr("x", function (d) { return d.x; })
			 .attr("y", function (d) {
				 var h = this.getBBox().height;
				 return d.y + h/4;
			 });
	});
</script>
<script>
	$("#svg-container text").click(function(){
		var type = $(this).attr("type");
		if (type == "type-keyword") {
			$("input[name='searchCondition.researchPlanManager']").val("");
		}
		if (type == "type-manager") {
			var value = $(this).attr("value");
			$("input[name='searchCondition.researchPlanManager']").val(value);
		}
		$("input[name='searchCondition.pageIndex']").val(0);
		$(this).parents("form").submit();
	})
	
	$("#btn-search").click(function() {
		$("input[name='searchCondition.researchPlanManager']").val("");
		$("input[name=svgDisplayStatus]").val(false);
	});
	
	$("#btn-reset").click(function(){
		$("input[name='searchCondition.researchPlanManager']").val("");
		$("input[name=svgDisplayStatus]").val(false);
	});
</script>



