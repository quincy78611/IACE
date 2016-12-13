<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!-- 公告訊息&活動人培 -->
<div class="container top50">
    <div class="row">
    	<!-- 公告訊息 -->
        <div class="col-sm-6 col-xs-12">
            <div>
                <div class="large_title_01">
                	<i class="fa fa-file-text-o" aria-hidden="true" style="font-size:18px; margin-right:5px;"></i>公告訊息
					<div class="pull-right">
						<a href="<s:url value="/f2/integrationSearch/init"/>">
							<img src="<s:url value="/images/frontend-v2/more_blue.png"/>" alt="" height="30"/>
						</a>
					</div>
				</div>
                <div class="line_blue">&nbsp;</div>
                <div class="line_gray1px"></div>
            </div>
            <div>
                <table class="table">
                    <tbody>                   
                    	<s:iterator value="newsList" status="stat">
	                        <tr>
	                            <td><h4><span class="label label-info"><s:property value="category"/></span></h4></td>
	                            <td class="date_01"><s:date name="createTime" format="yyyy/MM/dd"/></td>
	                            <td>
	                            	<div class="truncate" title="<s:property value="title"/>">
	                            		<a href="#" class="list_link_01"><s:property value="title"/></a>
	                            	</div>
	                            </td>
	                        </tr>
                    	</s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- 活動人培 -->
        <div class="col-sm-6 col-xs-12">
            <div>
                <div class="large_title_01">
                	<i class="fa fa-calendar-check-o" aria-hidden="true" style="font-size:18px; margin-right:5px;"></i>活動人培
					<div class="pull-right">
						<a href="<s:url value="/f2/integrationSearch/init"/>">
							<img src="<s:url value="/images/frontend-v2/more_blue.png"/>" alt="" height="30"/>
						</a>
					</div> 
                </div>
                <div class="line_blue">&nbsp;</div>
                <div class="line_gray1px"></div>
            </div>
            <div>
                <table class="table" >
                    <tbody>
                    	<s:iterator value="activityList" status="stat">
	                        <tr>
	                            <td><h4><span class="label label-info"><s:property value="category"/></span></h4></td>
	                            <td class="date_01"><s:date name="createTime" format="yyyy/MM/dd"/></td>
	                            <td><a href="#" class="list_link_01"><s:property value="title"/></a></td>
	                        </tr>                   	
                    	</s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>