<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<div class="footer_bg">
	<div class="container">
		<div class="row no-gutter">
			<div class="col-sm-7 col-xs-12 nopadding footer_content">
				<div class="pull-left" style="margin-right:10px;"><img src="<s:url value="/images/qrCode.jpg"/>" width="50" height="50" alt=""/></div>
				<div class="pull-left">
					<div>版權所有©<label id="current_year"></label>科技部產學及園區業務司</div>
					<script>
						$("label#current_year").text(new Date().getFullYear());
					</script>
					<div>Department of Academia-Industry Collaboration and Science Park Affairs, Academy and Science Park Affairs</div>
					<div>本網站設計 支援IE、Firefox及Chrome， 網頁設計最佳瀏覽解析度為1280x768以上</div>
					<div>
						<table>
							<tr><td>網站瀏覽人數：</td><td><div id="tc-b64b054876"></div></td></tr>
						</table>
						<script type="text/javascript">
							var _tcq = _tcq || [];
							_tcq.push([ 'widget', 'visits' ]);
							_tcq.push([ 'init', 'b64b054876' ]);
							(function(d, s) {
								var e = d.createElement(s);
								e.type = 'text/javascript';
								e.async = true;
								e.src = ('https:' == document.location.protocol ? 'https': 'http') + '://s.tcimg.com/w/v3/trendcounter.js';
								var f = d.getElementsByTagName(s)[0];
								f.parentNode.insertBefore(e, f);
							})(document, 'script');
						</script>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="col-sm-5 col-xs-12 nopadding footer_content text-right">
				<ul class="list-inline footer_content_02">
					<li><a href="<s:url value="/f2/websiteService/websiteMap"/>" class="footer_link">網站地圖</a></li><li>│</li><li><a href="<s:url value="/f2/customerServiceMailbox/create"/>" class="footer_link">客服信箱</a></li><li>│</li><li><a href="<s:url value="/f2/websiteService/contactUs"/>" class="footer_link">聯絡電話</a></li><li>│</li><li><a href="<s:url value="/f2/websiteService/privacy"/>" class="footer_link">隱私權宣告</a></li><li>│</li><li><a href="<s:url value="/f2/websiteService/copyright"/>" class="footer_link">版權宣告</a></li><li>│</li><li><a href="#" class="footer_link">圖片授權宣告</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>