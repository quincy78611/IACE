<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!-- Menu 手機版 -->
<nav class="mobile_menu" style="border:none; background-color:#FFF; width:100%; margin-bottom:0px;">
    <div class="container-fluid nopadding">
        <div class="pull-left"><a href="index.html"><img src="<s:url value="/images/frontend-v2/logo.jpg"/>" alt="" style="height:60px; margin-left:10px;"/></a></div>
        <div class="pull-right" style="padding:15px 10px;">
        	<a href="#" onclick="$('#menu_dropdown').slideToggle('fast');"><i class="fa fa-bars fa-2x" style="color:#1fb5da;" aria-hidden="true"></i></a>
        </div>
        <div class="pull-right" style="padding:15px 10px;">
        	<a href="#"><i class="fa fa-search fa-2x" style="color:#1fb5da;"></i></a>
        </div>
        <div class="clear-fix"></div>
        <div id="menu_dropdown">
            <ul style="background-color:#F8F8F8;">
                <li style="border-bottom:#DDD 1px solid" role="presentation" class="dropdown"> <a href="#" class="dropdown-toggle menu_link_mobile right5" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">活動/人培<span class="caret"></span></a> 
                    <ul class="dropdown-menu" style="margin-left:20px; padding-left:10px;">
                        <li><a href="#" class="menu_link_mobile">成果發表</a></li>
                        <li><a href="#" class="menu_link_mobile">計畫宣導</a></li>
                        <li><a href="#" class="menu_link_mobile">媒合會</a></li>
                        <li><a href="#" class="menu_link_mobile">人培課程</a></li>
                        <li><a href="#" class="menu_link_mobile">外部活動</a></li>
                        <li><a href="#" class="menu_link_mobile">計畫研習</a></li>
                    </ul>
                </li>
                <li style="border-bottom:#DDD 1px solid" role="presentation" class="dropdown"> <a href="#" class="dropdown-toggle menu_link_mobile right5" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">公告訊息<span class="caret"></span></a> 
                    <ul class="dropdown-menu" style="margin-left:20px; padding-left:10px;">
                        <li><a href="#" class="menu_link_mobile">一般公告</a></li>
                        <li><a href="#" class="menu_link_mobile">新聞稿</a></li>
                    </ul>
                </li>
                <li style="border-bottom:#DDD 1px solid" role="presentation" class="dropdown"> <a href="#" class="dropdown-toggle menu_link_mobile right5" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">產學合作案例<span class="caret"></span></a> 
                    <ul class="dropdown-menu" style="margin-left:20px; padding-left:10px;">
                        <li><a href="#" class="menu_link_mobile">商品化</a></li>
                        <li><a href="#" class="menu_link_mobile">專利推廣</a></li>
                        <li><a href="#" class="menu_link_mobile">新創事業</a></li>
                    </ul>
                </li>
                <li style="border-bottom:#DDD 1px solid" role="presentation" class="dropdown"> <a href="#" class="dropdown-toggle menu_link_mobile right5" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">學界研發成果<span class="caret"></span></a> 
                    <ul class="dropdown-menu" style="margin-left:20px; padding-left:10px;">
                        <li><a href="#" class="menu_link_mobile">學研成果精選</a></li>
                        <li><a href="#" class="menu_link_mobile">學界專利選輯</a></li>
                        <li><a href="#" class="menu_link_mobile">I-ACE搜尋平台</a></li>
                    </ul>
                </li>
                <li style="border-bottom:#DDD 1px solid" role="presentation" class="dropdown"> <a href="#" class="dropdown-toggle menu_link_mobile right5" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">產業情報<span class="caret"></span></a> 
                    <ul class="dropdown-menu" style="margin-left:20px; padding-left:10px;">
                        <li><a href="#" class="menu_link_mobile">產業新聞</a></li>
                        <li><a href="#" class="menu_link_mobile">產業評析</a></li>
                    </ul>
                </li>
                <li style="border-bottom:#DDD 1px solid" role="presentation" class="dropdown"> <a href="#" class="dropdown-toggle menu_link_mobile right5" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">媒合專區<span class="caret"></span></a> 
                    <ul class="dropdown-menu" style="margin-left:20px; padding-left:10px;">
                        <li><a href="#" class="menu_link_mobile">產學媒合服務團簡介</a></li>
                        <li><a href="#" class="menu_link_mobile">法規政策</a></li>
                        <li><a href="#" class="menu_link_mobile">文獻</a></li>
                        <li><a href="#" class="menu_link_mobile">育成中心</a></li>
                        <li><a href="#" class="menu_link_mobile">問卷調查</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse --> 
    </div>
</nav>