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
	height: 19px;
}

div#abgne_marquee ul, div#abgne_marquee li {
	margin: 0;
	padding: 0;
	list-style: none;
}

div#abgne_marquee ul {
	position: absolute;
}

div#abgne_marquee ul li, div#abgne_marquee ul li a {
	font-size: 15px;
	color: #FFF;
	line-height: 1.6em;
}
</style>

<script>
	$(function() {
		// 先取得 div#abgne_marquee ul
		// 接著把 ul 中的 li 項目再重覆加入 ul 中(等於有兩組內容)
		// 再來取得 div#abgne_marquee 的高來決定每次跑馬燈移動的距離
		// 設定跑馬燈移動的速度及輪播的速度
		//$('div#abgne_marquee').height()
		var $marqueeUl = $('div#abgne_marquee ul');
		var $marqueeli = $marqueeUl.append($marqueeUl.html()).children(); 
		var _height = 24 * -1;
		var scrollSpeed = 800;
		var timer;
		var speed = 3000 + scrollSpeed;

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
								<s:iterator value="marqueeList" status="stat">
									<s:if test="displayStatus">
										<li>
											<s:if test="hasLink">
												<a href="<s:property value="url"/>" target="_blank">
													<div class="news_marquee"><s:property value="text"/></div>
												</a>
											</s:if>
											<s:else>
												<div class="news_marquee"><s:property value="text"/></div>
											</s:else>
										</li>
									</s:if>
								</s:iterator>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>