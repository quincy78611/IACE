<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<style>
.menu_fixed {
	z-index: 9999;
	position: fixed;
	top: 0px;
	width: 100%;
}
</style>
<script type="text/javascript">
	//選單列黏住上方浮動
	$(window).bind('scroll', function() {
		if ($(window).scrollTop() > 110) {
			$('#main_menu').addClass('menu_fixed');
		} else {
			$('#main_menu').removeClass('menu_fixed');
		}
	});
</script>

<!-- Top 電腦版 -->
<div class="container pc_menu">
    <div class="row">
        <div class="col-md-3 col-sm-3">
        	<div>
        		<a href="<s:url value="/f2/home/init"/>">
        			<img src="<s:url value="/images/frontend-v2/logo.jpg"/>" alt="" style="max-height:100px;"/>
        		</a>
        	</div>
        </div>
        <div class="col-md-9 col-sm-9 text-right">
            <div class="input-group pull-right" style="width:300px; margin-top:15px;">
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-btn">
                <button class="btn btn-info" type="button" style="background-color:#1eb4da;">
                <i class="fa fa-search" aria-hidden="true"></i>
                </button>
                </span>
            </div>
            <div class="clearfix"></div>
            <div class="menu_list">
            	<ul class="list-inline">
                    <li class="dropdown left15">
                    <a href="#" class="dropdown-toggle menu_link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="dropdownMenu1">活動/人培<span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">成果發表</a></li>
                            <li><a href="#">計畫宣導</a></li>
                            <li><a href="#">媒合會</a></li>
                            <li><a href="#">人培課程</a></li>
                            <li><a href="#">外部活動</a></li>
                            <li><a href="#">計畫研習</a></li>
                        </ul>
                    </li>
                    <li class="dropdown left15">
                    <a href="#" class="dropdown-toggle menu_link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="dropdownMenu2">公告訊息<span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <li><a href="#">一般公告</a></li>
                            <li><a href="#">新聞稿</a></li>
                        </ul>
                    </li>
                    <li class="dropdown left15">
                    <a href="#" class="dropdown-toggle menu_link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="dropdownMenu3">產學合作案例<span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
                            <li><a href="#">商品化</a></li>
                            <li><a href="#">專利推廣</a></li>
                            <li><a href="#">新創事業</a></li>
                        </ul>
                    </li>
                    <li class="dropdown left15">
                    <a href="#" class="dropdown-toggle menu_link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="dropdownMenu4">學界研發成果<span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
                            <li><a href="#">學研成果</a></li>
                            <li><a href="#">學界專利</a></li>
                            <li><a href="#">產學人才</a></li>
                        </ul>
                    </li>
                    <li class="dropdown left15">
                    <a href="#" class="dropdown-toggle menu_link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="dropdownMenu5">產業情報<span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu5">
                            <li><a href="#">產業新聞</a></li>
                            <li><a href="#">產業評析</a></li>
                        </ul>
                    </li>
                    <li class="dropdown left15">
                    <a href="#" class="dropdown-toggle menu_link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="dropdownMenu6">媒合專區<span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu6">
                            <li><a href="#">產學媒合服務團簡介</a></li>
                            <li><a href="#">法規政策</a></li>
                            <li><a href="#">文獻</a></li>
                            <li><a href="#">育成中心</a></li>
                            <li><a href="#">問卷調查</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="blue_menu pc_menu" id="main_menu">
	<div class="container">
    	<div class="row">
            <div class="col-sm-2">
            	<a href="<s:url value="/f2/home/init"/>" class="blue_menu_link">
            		<i class="fa fa-home right5" aria-hidden="true"></i>首頁
            	</a>
            </div>
            <div class="col-sm-2">
            	<a href="#" class="blue_menu_link"><i class="fa fa-pencil right5" aria-hidden="true"></i>產學合作計畫</a>
            </div>
            <div class="col-sm-2 text-center">
            	<a href="#" class="blue_menu_link"><i class="fa fa-phone right5" aria-hidden="true"></i>我要諮詢</a>
            </div>
            <div class="col-sm-2 text-center">
            	<a href="#" class="blue_menu_link"><i class="fa fa-book right5" aria-hidden="true"></i>常問集</a>
            </div>
            <div class="col-sm-2 text-right">
            	<a href="#" class="blue_menu_link"><i class="fa fa-user-circle-o right5" aria-hidden="true"></i>會員中心</a>
            </div>
            <div class="col-sm-2 text-right">
            	<a href="#" class="blue_menu_link"><i class="fa fa-envelope-open right5" aria-hidden="true"></i>電子報</a>
            </div>
        </div>
    </div>
</div>