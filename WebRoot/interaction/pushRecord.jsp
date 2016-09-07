<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="UI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="UI/themes/icon.css">
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
</style>

<script type="text/javascript" >

</script>
</head>

	<body>	
	<%--	<form action="T_to_pushRecord_Servlet?pageNo=1" method="post">
	<div id="panel">
	<h5 class="head">查询</h5>
	<div class="content" >

	<CENTER>
			
			<span class="lable" >电话：</span><input id="tel" name="tel" type="text" class="input"  value="${tel}"/>
			<span class="lable" >用户名称：</span><input id="name" name="name" type="text" class="input"  value="${name}"/>
			<input id="logId" name="button" type="submit" class="add" value="查询"/>
			
	</CENTER>
		
	</div>
</div>--%>
</form>
	<table id="dg"  name="grid" class="easyui-datagrid" style="width:1014px;height:424px;" >  	
      <thead>  
          <tr>
             <th data-options="field:'num'">序号</th>               
           <th data-options="field:'sum',width:100">条数</th>    
              <th data-options="field:'type',width:80">类型</th>                         
             <th data-options="field:'message',width:360">内容</th>            
             <th data-options="field:'time',width:250">时间</th>               
          </tr>  
      </thead>  
     <tbody>
        	<c:forEach items="${page.list}" var="record"
					varStatus="aa">
					<tr>
						<td >${aa.count}&emsp;</td>	
						<td> ${record.sum}</td>		
						<td> ${record.type}</td>				
						<td>${record.message}</td>								
						<td> ${record.time}</td>				
		
					</tr>
				</c:forEach> 
                
     </tbody> 

 </table>
 <div class="fenye">
 <div  class="fy-l" align="center" colspan="4">共 ${page.totalPages}页 | 第 ${page.pageNo}页</div>
<div class=" fy-r" >
    [<a href="T_to_pushRecord_Servlet?pageNo=1&tel=${tel }&name=${name }" >首页</a> 
                | <a href="T_to_pushRecord_Servlet?pageNo=${page.prePage }&tel=${tel }&name=${name}" >上一页</a> | 
                <a href="T_to_pushRecord_Servlet?pageNo=${page.nextPage}&tel=${tel }&name=${name }" >下一页</a> | 
                <a href="T_to_pushRecord_Servlet?pageNo=${page.bottomPage}&tel=${tel }&name=${name }" >末页</a>] 

</div>

 </div>

   </body>
</html>
