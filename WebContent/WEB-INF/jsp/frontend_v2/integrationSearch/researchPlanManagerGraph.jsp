<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<script type="text/javascript" src="<s:url value="/scripts/graph/d3.v3.js"/>"></script>
<script type="text/javascript" src="<s:url value="/scripts/graph/cola.min.js"/>"></script>
<style>
#svg-container {
	width:100%;
	height:auto;
	margin: 20px auto 20px auto;
	text-align: center;
}

svg {
	border: 1px #000000 solid;
	display: block;
/*   	position: absolute; */
  	width: 100%;
}

.node {
	stroke: #fff;
	stroke-width: 1px;
	cursor: move;
}

.type-keyword {
	fill: rgb(255, 50, 50);
}
.type-manager {
	fill: rgb(31, 119, 180);
}

.link {
	stroke: #999;
	stroke-width: 3px;
	stroke-opacity: 1;
}

.label {
	fill: #ffffff;
	font-family: Verdana;
	font-size: 0.9em;
	text-anchor: middle;
	cursor: move;
}
</style>

<div id="svg-container"></div>

<script>
	var width = 960, height = 500;
	var res_data_jsonString = '<s:property value="rpManagerJsonString"/>'.replace(/&quot;/g,'\"');
	var res_data = JSON.parse(res_data_jsonString);
	
	//==========================================================================
	
	var cola = cola.d3adaptor()
		.linkDistance(120)
		.avoidOverlaps(true)
		.size([width, height]);

	var svg = d3.select("#svg-container").append("svg")
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
		.attr("class", "node")
		.attr("class", function (d) { return d.type; })
		.attr("width", function (d) { return d.width; })
		.attr("height", function (d) { return d.height; })
		.attr("rx", 5).attr("ry", 5)
		.call(cola.drag);

	var label = svg.selectAll(".label")
		.data(res_data.nodes)
	   .enter().append("text")
		.attr("class", "label")
		.text(function (d) { return d.name; })
		.call(cola.drag);

	node.append("title")
		.text(function (d) { return d.name; });

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




