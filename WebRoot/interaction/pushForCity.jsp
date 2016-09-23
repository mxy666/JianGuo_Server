<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.jianguo.bean.T_user_moneyout_Bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	
	<head>
		<base href="<%=basePath%>">

		<title>推送</title>
	<link rel="stylesheet" type="text/css" href="UI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="UI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="UI/demo.css">
	<script type="text/javascript" src="UI/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="UI/jquery.easyui.min.js"></script>
	


	
<style type="text/css">
	*{margin:0;padding:0;}	
	body { font-size: 13px; line-height: 130%; padding: 60px }
	#panel { width: 100%; border: 1px solid #a9a9a9 }
	.head { padding: 5px; background: #CAE8EA; cursor: pointer }
	.content { padding: 10px; text-indent: 2em; border-top: 1px solid #a9a9a9;display:block; }
	.tablist input{width:20px;height:20px}
	.p-r{position: relative;}
	.p-b{position: absolute;width: 100%;height: 100%;top: 0;left: 0;}
</style>

<script type="text/javascript" >
$("#devtable").datagrid({   
	
    onClickRow: function (index, row) {  //easyui封装好的时间（被单机行的索引，被单击行的值）  
	alert(1);
    //需要传递的值
    var CourseName = row["name"];
    var TeacherCourseID = row["id"];
alert(CourseName+"-----"+TeacherCourseID);}
    
});  
function pagerFilter(data){
	if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
		data = {
			total: data.length,
			rows: data
		}
	}
	var dg = $(this);
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');
	pager.pagination({
		onSelectPage:function(pageNum, pageSize){
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh',{
				pageNumber:pageNum,
				pageSize:pageSize
			});
			dg.datagrid('loadData',data);
		}
	});
	if (!data.originalRows){
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = (data.originalRows.slice(start, end));
	return data;
}
function mySearch(value,name){
	if(name=='name')
		$("#devtable").datagrid('load',{name:value});
	
}

function mySearch(value,name){
	$('#devtable').datagrid('load',{
		name: $('#job_name').val(),
		city: $('#job_city').combobox('getValue')
	});
	
	
}

</script>
</head>

	<body>
	
		<form action="T_to_push_Servlet?pageNo=1&city=other" method="post">
	<div id="panel">
	<h5 class="head">查询</h5>
	<div class="content" >

	<CENTER>
			<span class="lable">地区：</span>			

	<select  name="cityId" style="width:319px;height:34px;">

		<option value="BJ"
			<c:if test="${cityId=='BJ'}">selected="true"</c:if> >
			北京
		</option>
		<option value="SY"
		 	<c:if test="${cityId=='SY'}">selected="true"</c:if> >
			三亚
		</option>
		<option value="HK"
		 <c:if test="${cityId=='HK'}">selected="true"</c:if> >
			海口
		</option>
		<option value="HZ"
		 	<c:if test="${cityId=='HZ'}">selected="true"</c:if> >
			杭州
		</option>
			<option value="XA"
		 	<c:if test="${cityId=='XA'}">selected="true"</c:if> >
			西安
		</option>
		<option value="WH"
				<c:if test="${cityId=='WH'}">selected="true"</c:if> >
			武汉
		</option>
</select>
			
			
			<span class="lable">学校：</span><input id="school" name="school" type="text" class="input"  value="${school}"/></br>
			<span class="lable"style="margin-left:24px;" >性别：</span>
			<select  name="sex" style="width:319px;height:34px;">	
				<option value="all"
					<c:if test="${sex=='all'}">selected="true"</c:if> 
				>全部</option>
					<option value="1"
						<c:if test="${sex=='1'}">selected="true"</c:if> >
						男</option>
					<option value="0"
						<c:if test="${sex=='0'}">selected="true"</c:if> >
						女</option>
			</select>
			<%--<input id="sex" name="sex" type="text" class="input"  value="${sex}"/>
			--%><span class="lable" >电话：</span><input id="tel" name="tel" type="text" class="input"  value="${tel}"/>
			<%--<span class="lable" >兼职名称：</span><input id="name" name="name" type="text" class="input"  value="${name}"/>
			--%>
			<div style="overflow: hidden; width:200px">
		
			<input id="city_id" name="button" type="submit" class="add" value="查询"/>
			
			</div>
			
			
	</CENTER>
		
	</div>
</div>
</form>

 <div class="p-r">
 		<table id="dg"  name="grid" class="easyui-datagrid" style="width:1020px;height:424px;" >  	
      <thead>  
          <tr>
             <th data-options="field:'num'">序号</th>               
             <th data-options="field:'name',width:80">姓名</th>                         
             <th data-options="field:'sex',width:60">性别</th> 
             <th data-options="field:'tel',width:180">电话号码</th>  
             <th data-options="field:'school',width:300">学校</th>               
             <th data-options="field:'city_id',width:350">区域</th>               
          </tr>  
      </thead>  
     <tbody>
        	<c:forEach items="${page.list}" var="pushObj"
					varStatus="aa">
					<tr>
						<td >${aa.count}&emsp;</td>						
						<td>${pushObj.name}</td>						
						<c:if test="${pushObj.sex=='1'}"> 
						<td>男</td>
						
						</c:if>	
						<c:if test="${pushObj.sex=='0'}"> 
						<td>女</td>
						</c:if>						
						<td> ${pushObj.tel}</td>
						<td> ${pushObj.school}</td>
						<td> ${pushObj.cityId}</td>				
		
					</tr>
				</c:forEach> 
                
     </tbody> 

 </table>
 <div class="p-b"></div>
 
 </div>
 <div class="fenye">
 <div  class="fy-l" align="center" colspan="4"> 第 ${page.pageNo}页</div>
<div class=" fy-r" >
    [<a href="T_to_push_Servlet?pageNo=1&cityId=${cityId }&school=${school }&sex=${sex }&tel=${tel }&city=other" >首页</a> 
                | <a href="T_to_push_Servlet?pageNo=${page.prePage }&cityId=${cityId }&school=${school }&sex=${sex }&tel=${tel }&city=other" >上一页</a> | 
                <a href="T_to_push_Servlet?pageNo=${page.nextPage}&cityId=${cityId }&school=${school }&sex=${sex }&tel=${tel }&city=other" >下一页</a> | 
                <a href="T_to_push_Servlet?pageNo=${page.bottomPage}&cityId=${cityId }&school=${school }&sex=${sex }&tel=${tel }&city=other" >末页</a>] 

</div>

 </div>

	<form action="T_pushAction_servlet" method="post">
<div>
<div align="center"> 

		
  <input id="city_id" name="cityId" type="hidden" class="input"  value="${cityId}"/>
  <input id="city_id" name="school" type="hidden" class="input"  value="${school}"/>
  <input id="city_id" name="tel" type="hidden" class="input"  value="${tel}"/>
  <input id="city_id" name="sex" type="hidden" class="input"  value="${sex}"/>
 
 <!-- 兼职选择 -->

<div id="w" class="easyui-window hide " title="兼职信息" data-options="iconCls:'icon-save'" closed="true" style="width:900px;height:600px;padding:10px;">  
<div id="tools"  id="tb" style="padding:5px;height:auto">
		<span>兼职名称:</span>
		<input id="job_name" name="job_name" style="line-height:12px;border:1px solid #ccc">
		<span>城市:</span>
		<select id="job_city" name="job_city" class="easyui-combobox" panelHeight="auto" style="width:160px">
				<option value="all">全部</option>
				<option value="SY">三亚</option>
				<option value="BJ">北京</option>
				<option value="HZ">杭州</option>
				<option value="HK">海口</option>
				<option value="XA">西安</option>
				<option value="WH">武汉</option>
		</select>
		<%--<input id="name" name="name" style="line-height:16px;border:1px solid #ccc">	
		--%><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch()">Search</a>	
	</div> 
<table id="devtable" class="easyui-datagrid" data-options="
		url:'T_QueryJob_Servlet',
			
				fit:true,
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:false,			
				pagination:true,
				pageSize:10,
				pageList:[10,15],
				loadFilter:pagerFilter,
				toolbar:'#tools' ">
		<thead>
		<tr>
	
			<%--<th field="check"checkbox="true">框</th>
			--%><th field="id" >兼职名称</th>
			<%--<th field="check" checkbox="true">框</th>
			--%><th field="name" >ID</th>
			<th field="start_date">开始日期</th>
			<th field="stop_date">结束日期</th>
			<th field="address">工作地址</th>
			<th field="city_id_name">地区</th>
			<th field="money">工资</th>
		</tr>
		</thead>
	</table>


</div> 
    <!-- 页面调转 -->

 <div style="overflow: hidden;margin-bottom:40px;border: 1px solid;padding:20px;background: #fff;">
 		 <div style="float: left;">
	   	 兼职名称：<input type="text" size="60"  name="job_name" class="txtValue" readonly="readonly">
	 </div>
	 	<input id="name" name="button" class="add" type="button" onclick="$('#w').window('open'); bb()"value="选择兼职" style="margin:7px 20px 0 21px"/>
     <div style="float: left;margin-right: 10%;">
     	页面调转：
     	
	     <select  name="type" style="width:153px;height:34px;">	
			<option value="5">单条兼职</option>
	
		</select>
	</div>
	  <div id="way" class="pushWay"style="float: left;margin-top: 10px; ">
		 推送方式：<select id="cc" name="pushWay" style="width: 50px;">  
			 	<option value="light">极光</option>  
	  		 	<!--<option value="sms">短信</option>    -->
		   		  </select> 
		 </div> 
		 <div id="way" class="pushButton" style="width:60px;display:block;    margin: auto;">
 		<input id="push" name="push" type="submit" class="add" value="推送"/>
 	</div>
		    
 </div>

</div>

  


</div>
   	
 
</form>
<form action="T_pushAction_servlet" method="post">
 <input id="city_id" name="cityId" type="hidden" class="input"  value="${cityId}"/>
  <input id="city_id" name="school" type="hidden" class="input"  value="${school}"/>
  <input id="city_id" name="tel" type="hidden" class="input"  value="${tel}"/>
  <input id="city_id" name="sex" type="hidden" class="input"  value="${sex}"/>
<div style="overflow: hidden;    margin-bottom: 40px;border: 1px solid;padding:20px;background: #fff;">
	<div class="message" style="display:block;float:left;width: 100%;">
		<span style="float: left;">推送内容: </span><textarea id="message" name="message" cols="52" rows="4" style="float: left;  "></textarea> 
			<div style="float: left;margin-right: 10%;  margin-left: 10%;">
     	页面调转：
     	
	     <select  name="type" style="width:153px;height:34px;">	
			<option value="3">主页</option>
			<option value="2" >实名</option>
			<option value="0">我的兼职</option>
			<option value="1">钱包</option>
	
		</select>
	</div>
	<div id="way" class="pushWay"style="float: left;margin-top: 10px; ">
		 推送方式：<select id="cc" name="pushWay" style="width: 50px;">  
			 	<option value="light">极光</option>  
	  		 		<%--<option value="sms">短信</option>   
		   		  --%></select> 
		 </div> 
		</div>
		<div id="way" class="pushButton" style="width:60px;display:block;    margin: auto;">
 		<input id="push" name="push" type="submit" class="add" value="推送"onclick="return check()"/>
 	</div>
   </div> 	
   

</form>
<form action="T_pushAction_servlet" method="post">
 <input id="city_id" name="cityId" type="hidden" class="input"  value="${cityId}"/>
  <input id="city_id" name="school" type="hidden" class="input"  value="${school}"/>
  <input id="city_id" name="tel" type="hidden" class="input"  value="${tel}"/>
  <input id="city_id" name="sex" type="hidden" class="input"  value="${sex}"/>
<div style="border: 1px solid;background: #fff;padding:20px">
   	 <div style="overflow: hidden;padding: 20px 0px 0px 15px;width: 100%;">
 	 	<span style="float: left;margin-top: -17px;width: 100%;">
 		<span style="float: left;"> 地址：</span>
 		<input name="html_url" type="text" class="input"  value="${html_url}"style="width: 320px; display: block; float: left;"/>
 		<div style="float: left;margin-right: 10%;  margin-left: 10%;">
     	页面调转：
     	
	     <select  name="type" style="width:153px;height:34px;">	
				<option value="4" >活动</option>
	
		</select>
	</div>	
	<div style="overflow: hidden; margin-top: 64px;">
		 <span style="float: left;">推送内容: </span>
 		<textarea id="message" name="message" cols="52" rows="4" style="float: left;  "></textarea> 
	<div>
	<div id="way" class="pushWay"style="float: left;margin-top: 10px; ">
		 推送方式：<select id="cc" name="pushWay" style="width: 50px;">  
			 	<option value="light">极光</option>  
	  		 	<!--<option value="sms">短信</option>    -->
		   		  </select> 
		 </div> 
		<div id="way" class="pushButton" style="width:60px;display:block;    margin: auto;">
 		<input id="push" name="push" type="submit" class="add" value="推送"/>
 	</div>
 	</div>	  
   </div>
</form>
<script type="text/javascript">
      function aa(){
            var oBox = document.getElementsByClassName('datagrid-row');
            
              for (var i = 0; i < oBox.length; i++) { 
             
				   oBox[i].onclick=function(){
		 
		 			
		               $(this).each(function(){
		               
		                    var j = $(this).find('td').eq(0).text();
		                    var i = $(this).find('td').eq(1).text();
		                    var val = j + "," + i;
		                    $(".txtValue").val(val);
		                    
		                });		               
		                
            		};

                };
        	}
        setInterval("aa()",100);
      
function check(){
var message = document.getElementById("message").value;
				
	if(message = null || message.length==0){
		//document.getElementById("admin1").innerHTML="<font color='red'>业务经手人不能为空！！！</font>";
		alert("推送内容不能为空！！！");
		return false;
	}	}
</script>
   </body>
</html>
