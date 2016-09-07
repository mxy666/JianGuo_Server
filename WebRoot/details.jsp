<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <meta name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0"/>
	<title>职位详情</title>
	<link href="./css/html_style.css" rel="stylesheet" type="text/css" />
	<link href="./css/public.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/css.css"> 
</head>
<body>
    <div class="main">
        <div class="basic-info">
            <div class="imgdiv">
                  <img src="${name_image }" width="37.5" height="37.5"id="img">
            </div>

            <div class="basis-info-text">
                <h1 id="test">${name}</h1>
                <p>工资：<em id="">${money }元/${info_term }</em></p>
                <p>招聘人数：<em>${count }/${sum }</em></p>
                <span>${regedit_time }</span>
                <span class="baom">立即报名</span>
            </div>
        </div> 
         <div class="specific-info">
            <div class="specific-info-text">
              <p>
              	<span>工作地点：</span>
              	<span class="p-span">${city_name}-${address }</span>
              </p>
              <p>
              	<span>工作日期：</span>
              	<span class="p-span">${start_date } - ${stop_date }</span>
              </p>
              <p>
              	<span>集合地点：</span>
              	<span class="p-span">${info_set_place }</span>
              </p>
              <p>
              	<span>集合时间：</span>
              	<span class="p-span">${info_set_time }</span>
              </p>
              <p>
              	<span>性别：</span>
              	<span class="p-span">${info_limit_sex }</span></p>
              <p>
              	<span>结算方式：</span>
              	<span class="p-span">${info_term }结</span>
              </p>
              <p>
              	<span>其他事项：</span>
              	<span class="p-span">${info_other }</span>
             </p>              
            </div>
        </div> 
         <div class="work-details">
           <div class="work-details-title">
               <em></em>
               <span>工作详情</span>              
           </div>
           <div class="work-details-content">
                <p>
                    <span>工作内容：</span>
                    <span id="text" class="text">${info_work_content }</span>
                </p>
                 <p class="req">
                    <span>工作要求：</span>
                    <span id="text1" class="text">${info_work_require }</span>
                </p>
                <!--<p class="cd-popup-trigger">查看更多</p>
            --></div>           
        </div> 
          <div class="work-details">
           <div class="work-details-title">
               <em></em>
               <span>商家详情</span>                      
           </div>
           <div class="work-details-content ">
                <img src="img/moren.png" width="37.5" height="37.5">
                <div class="merchants-details-text">
                    <span>${merchant_id_name }</span>
                    <span>多个职位在招</span>
                </div>
            </div>
        </div> 
        <div class="end ">
            您已经拉倒最底部了哦
        </div>
       <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.woniukeji.jianguo" class="button" >下载 app</a>
     </div>
   <div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<div class="cd-text">
           <p>
                    <span>工作内容：</span>
                    <span id="text" class="text">${info_work_content }</span>
                </p>
                 <p class="req">
                    <span>工作要求：</span>
                    <span id="text1" class="text">${info_work_require }</span>
                </p>
        </div>
	</div> <!-- cd-popup-container -->
</div> <!-- cd-popup -->

<script src="js/jquery.1.11.1.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</body>
</html>
