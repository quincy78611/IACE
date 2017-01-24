<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn-reset").click(function() {
			$("input[type=text]").val("");
			$("select").prop('selectedIndex', 0);
		});
		
	});
</script>
</head>
<body>
	<!-- Banner -->
	<s:include value="./banner.jsp" />

	<!-- Main -->
	<div class="container top50">
		<div class="row">
			<div class="col-sm-12 col-xs-12">
				<div>
					<div class="large_title_01">
						<i class="fa fa-file-text-o" aria-hidden="true" style="font-size: 18px; margin-right: 5px;"></i>企業需求
					</div>
					<div class="line_blue">&nbsp;</div>
					<div class="line_gray1px bottom20"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- 左選單 -->
			<div class="col-sm-3 col-xs-12 bottom20">
				<s:include value="./left_menu.jsp" />
			</div>
			<!-- 右列表 -->
			<div class="col-sm-9 col-xs-12 bottom20">
				<s:form action="index" method="post" validate="true">
					<s:hidden name="searchCondition.category" />
					<div class="well">
						<div class="row">
							<div class="col-sm-9 col-xs-12">
								<s:textfield placeholder="關鍵字" name="searchCondition.searchText" class="form-control"/>
							</div>
							<div class="col-sm-3 col-xs-12">
								<button type="submit" class="btn btn-primary" id="btn-search">
									<i class="fa fa-search-plus right5" aria-hidden="true"></i>搜尋
								</button>
								&nbsp;
								<button type="button" class="btn btn-default" id="btn-reset">清除</button>
							</div>
						</div>
					</div>
					<div>
						<s:if test="enterpriseRequireTechPagedList != null">
							<s:iterator value="enterpriseRequireTechPagedList.list" status="stat">
								<div class="row" style="font-size: 16px;line-height: 1.5em;">
									<div class="col-sm-9 col-xs-7">
										<span style="color:#999;">領域：</span><s:property value="phase1.name" />
									</div>
									<div class="col-sm-3 col-xs-5">
										<input type="button" class="btn btn-info btn-interest" value="我有興趣"/>
									</div>
								</div>
								<div class="row top20" style="font-size: 16px;line-height: 1.5em;">	
									<div class="col-sm-12">	
										<span style="color:#999;">發展方向：</span>
										<s:property value="phase2" />
									</div>
								</div>
								<div class="row top20" style="font-size: 16px;line-height: 1.5em;">									
									<div class="col-sm-12">									
										<span style="color:#999;">應用端：</span>
										<s:property value="phase3" />
									</div>
								</div>
								<div class="line_solid"></div>
							</s:iterator>
						</s:if>	
					</div>
					<!-- 換頁 -->
					<s:include value="./pagination.jsp" />
				</s:form>
			</div>
		</div>
	</div>

	<div>
		<div id="myModal" class="modal">
			<!-- Modal content -->
			<div class="modal-content">
				<span class="close">&times;</span>
				<p>聯絡人：林先生</p>
				<p>TEL：03-5919155</p>
				<p>E-Mail：linyukaun@itri.org.tw</p>
				<p>工業技術研究院產業服務中心</p>
			</div>
		</div>	
		<style>
			/* The Modal (background) */
			.modal {
			    display: none; /* Hidden by default */
			    position: fixed; /* Stay in place */
			    z-index: 10; /* Sit on top */
			    left: 0;
			    top: 0;
			    width: 100%; /* Full width */
			    height: 100%; /* Full height */
			    overflow: auto; /* Enable scroll if needed */
			    background-color: rgb(0,0,0); /* Fallback color */
			    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
			    text-align: center;
			}
			
			/* Modal Content/Box */
			.modal-content {
			    background-color: #fefefe;
			    margin: 15% auto; /* 15% from the top and centered */
			    padding: 20px;
			    border: 1px solid #888;
			    width: 80%; /* Could be more or less, depending on screen size */
			    
			}
			
			/* The Close Button */
			.close {
			    color: #aaa;
			    float: right;
			    font-size: 28px;
			    font-weight: bold;
			}
			
			.close:hover,
			.close:focus {
			    color: black;
			    text-decoration: none;
			    cursor: pointer;
			}
		</style>
		<script>
		// Get the button that opens the modal
		$(".btn-interest").click(function(){
			$("#myModal").show();
		});
	
		// When the user clicks on <span> (x), close the modal
		$(".close").click(function(){
			$("#myModal").hide();
		});
		</script>
	</div>
</body>
</html>