<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!-- 法規政策&文獻 -->
<div style="background-image:url('<s:url value="/images/frontend-v2/banner_index_08.jpg"/>'); background-size:cover; padding-bottom:30px;">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-xs-12 top50">
                <div>
                    <div class="large_title_01">
                        <i class="fa fa-university" aria-hidden="true" style="font-size:18px; margin-right:5px;"></i>法規政策
                        <div class="pull-right"><a href="#"><img src="<s:url value="/images/frontend-v2/more_blue.png"/>" alt="" height="30"/></a></div>
                    </div>
                    <div class="line_blue">&nbsp;</div>
                    <div class="line_gray1px_02"></div>
                </div>
                <div>
                    <table class="table">
                        <tbody>
                            <tr>
                                <td width="20%" style="border:none;" class="small_title_01"><strong>出版年分</strong></td>
                                <td style="border:none;" class="small_title_01"><strong>標題</strong></td>
                            </tr>
                            <s:iterator value="policyList" status="stat">
	                            <tr>
	                                <td class="date_01 table_border"><s:property value="publishYear"/></td>
	                                <td class="table_border">
	                                	<div class="truncate" title="<s:property value="title"/>">
	                                		<a href="#" class="list_link_01">
	                                			<s:property value="title"/>
	                                		</a>
	                                	</div>
	                                </td>
	                            </tr>                            
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-sm-6 col-xs-12 top50">
                <div>
                    <div class="large_title_01">
                        <i class="fa fa-book" aria-hidden="true" style="font-size:18px; margin-right:5px;"></i>文獻
                        <div class="pull-right"><a href="#"><img src="<s:url value="/images/frontend-v2/more_blue.png"/>" alt="" height="30"/></a></div>
                    </div>
                    <div class="line_blue">&nbsp;</div>
                    <div class="line_gray1px_02"></div>
                </div>
                <div>
                    <table class="table">
                        <tbody>
                            <tr>
                                <td width="20%" style="border:none;" class="small_title_01"><strong>出版年分</strong></td>
                                <td style="border:none;" class="small_title_01"><strong>標題</strong></td>
                            </tr>
                            <s:iterator value="literatureList" status="stat">
	                            <tr>
	                                <td class="date_01 table_border"><s:property value="publishYear"/></td>
	                                <td class="table_border">
	                                	<div class="truncate" title="<s:property value="title"/>">
	                                		<a href="#" class="list_link_01">
	                                			<s:property value="title"/>
	                                		</a>
	                                	</div>
	                                </td>
	                            </tr>                            
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>