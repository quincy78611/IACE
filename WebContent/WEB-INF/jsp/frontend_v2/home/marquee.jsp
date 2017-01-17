<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<script type="text/javascript" src="<s:url value="/scripts/jquery.marquee.min.js"/>"></script>

<style>
.marquee {
	width: 100%;;
	overflow: hidden;
}

div#abgne_marquee {
	position: relative;
	overflow: hidden; /* 超出範圍的部份要隱藏 */
	width: 100%;
	height: 18px;
}

div#abgne_marquee ul, div#abgne_marquee li {
	margin: 0;
	padding: 0;
	list-style: none;
}

div#abgne_marquee ul {
	position: absolute;
}
.num_circle {
	background-color:#fff; 
	border-radius:12px; 
	font-size:14px; 
	width:24px; 
	height:24px;
	text-align:center;
	vertival-align:middle;
	padding:2px 0;
}
</style>

<script>
	$(function() {
		// 先取得 div#abgne_marquee ul
		// 接著把 ul 中的 li 項目再重覆加入 ul 中(等於有兩組內容)
		// 再來取得 div#abgne_marquee 的高來決定每次跑馬燈移動的距離
		// 設定跑馬燈移動的速度及輪播的速度
		//$('div#abgne_marquee').height()
		var $marqueeUl = $('div#abgne_marquee ul'), $marqueeli = $marqueeUl
				.append($marqueeUl.html()).children(), _height = 25 * -1, scrollSpeed = 800, timer, speed = 3000 + scrollSpeed;

		// 幫左邊 $marqueeli 加上 hover 事件
		// 當滑鼠移入時停止計時器；反之則啟動
		$marqueeli.hover(function() {
			clearTimeout(timer);
		}, function() {
			timer = setTimeout(showad, speed);
		});

		// 控制跑馬燈移動的處理函式
		function showad() {
			var _now = $marqueeUl.position().top / _height;
			_now = (_now + 1) % $marqueeli.length;

			// $marqueeUl 移動
			$marqueeUl.animate({
				top : _now * _height
			}, scrollSpeed, function() {
				// 如果已經移動到第二組時...則馬上把 top 設 0 來回到第一組
				// 藉此產生不間斷的輪播
				if (_now == $marqueeli.length / 2) {
					$marqueeUl.css('top', 0);
				}
			});

			// 再啟動計時器
			timer = setTimeout(showad, speed);
		}

		// 啟動計時器
		timer = setTimeout(showad, speed);

		$('a').focus(function() {
			this.blur();
		});
	});

	$(document).ready(function() {
		var dvwidth = $("#dv_marquee_container").width() - 156;
		$("#dv_marquee").width(dvwidth);

		$('.marquee').marquee({
			//speed in milliseconds of the marquee
			duration : 10000,
			pauseOnHover : true
		});
	});

	$(window).resize(function() {
		var dvwidth = $("#dv_marquee_container").width() - 156;

		$("#dv_marquee").width(dvwidth);

	});
</script>


<!-- HOT 20 -->
<div class="hot20">
	<div class="container">
		<div class="row" id="">
			<div class="col-sm-12">
				<div style="width:90px; float:left; padding:16px 0;">
					<i class="fa fa-fire right5" aria-hidden="true"></i><strong>HOT 20 :</strong>
				</div>
				
				<div class="content_01" style="float:left;">
					<table class="table" style="margin-bottom:0;">
						<tbody>
							<tr>
								<td width="40" style="border:none;">
									<div class="num_circle">1</div>
								</td>
								<td width="90" style="border:none;" class="hidden-xs hidden-sm">
									<s:date name="hot20ActivityList[0].postDate" format="yyyy/MM/dd" />
								</td>
								<td width="90" style="border:none;" class="hidden-xs hidden-sm">
									<h4><span class="label label-info"><s:property value="hot20ActivityList[0].category" /></span></h4>
								</td>
								<td style="border:none;">
									<s:url value="/f2/activity/showDetail" var="detailUrlTag" escapeAmp="false">
										<s:param name="id" value="hot20ActivityList[0].id" />
										<s:param name="searchCondition.category" value="hot20ActivityList[0].category" />
									</s:url>
									<a href="<s:property value="%{#detailUrlTag}"/>" class="list_link_01">
										<div class="truncate_hot"><s:property value="hot20ActivityList[0].title" /></div>
									</a>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="table" style="margin-bottom:0;display:none;" id="hot20">
						<tbody>
							<s:iterator value="hot20ActivityList" status="stat" begin="1" end="19">
								<tr>
									<td width="40" style="border:none;">
										<div class="num_circle"><s:property value="%{#stat.index+2}"/></div>
									</td>
									<td width="90" style="border:none;" class="hidden-xs hidden-sm">
										<s:date name="postDate" format="yyyy/MM/dd" />
									</td>
									<td width="90" style="border:none;" class="hidden-xs hidden-sm">
										<h4><span class="label label-info"><s:property value="category" /></span></h4>
									</td>
									<td style="border:none;">
										<s:url value="/f2/activity/showDetail" var="detailUrlTag" escapeAmp="false">
											<s:param name="id" value="id" />
											<s:param name="searchCondition.category" value="category" />
										</s:url>
										<a href="<s:property value="%{#detailUrlTag}"/>" class="list_link_01">
											<div class="truncate_hot"><s:property value="title" /></div>
										</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				
				<div style="float:right; margin:12px 0;">
					<div>
						<a href="#####" onclick="$('#hot20').slideToggle('fast');$('#hot20_close').show();$('#hot20_open').hide();" id="hot20_open"><img src="<s:url value="/images/frontend-v2/more_01.png"/>" alt="" height="30"/></a>
						<a href="#####" onclick="$('#hot20').slideToggle('fast');$('#hot20_close').hide();$('#hot20_open').show();" style="display:none;" id="hot20_close"><img src="<s:url value="/images/frontend-v2/more_02.png"/>" alt="" height="30"/></a>						
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>

<!-- NEWS -->
<div class="news">
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<ul class="list-inline" style="margin-bottom: 0; width: 100%;">
					<li><i class="fa fa-newspaper-o right5" aria-hidden="true"></i><strong>NEWS :</strong></li>
					<li style="width: 80%;">
						<div id="abgne_marquee">
							<ul>
								<li class="content_02">運用法人鏈結產業合作計畫成果發表會誠摯邀請您蒞臨指導</li>
								<li class="content_02">日期：2017年01月17日下午1時30分至下午3時30分</li>
								<li class="content_02">地點：台灣科技大學國際大樓一樓(IB101)與展覽會場A區 (106台北市大安區基隆路四段43號)</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>