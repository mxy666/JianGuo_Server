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
    
    <title></title>
<link rel="stylesheet" type="text/css" href="UI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="UI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="UI/demo.css">
	<script type="text/javascript" src="UI/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="UI/jquery.easyui.min.js"></script>
	
<script type="text/javascript">
function openFeedbackWin(){
	var row = $('#dg').datagrid('getSelected');
	
	if (row){				
	  var loginId=row.tel;
	  var text=row.text;
	  var url="T_FeedBack_Servlet?loginId="+loginId+"&text="+encodeURI(encodeURI(text));
	  window.open(url,"用户详细信息","width=780, height=670");
	}else{
		$.messager.alert('提示','请先选中数据!','error');
		}
	
}

</script>
  </head>
  <style>
    .datagrid-wrap,.datagrid-view {
      height: 100%!important;
  }
.datagrid-body{
  	width: 940px!important;
  }
  </style>
  <body>
<div style="width:1125px;height:550px;">	
<table id="dg"  name="grid" class="easyui-datagrid" style="width:1125px;height:550px;" >  	
      <thead>  
          <tr>
             <th data-options="field:'num'">序号</th>
             <%--               
             <th data-options="field:'id',width:80" style="display:none">ID</th>                                 
             --%>
             <th data-options="field:'tel',width:180">login_id</th>  
             <th data-options="field:'text',width:400">内容</th>               
             <th data-options="field:'time',width:200">时间</th>
             <th data-options="field:'action',width:100">操作</th>                              
          </tr>  
      </thead>  
     <tbody>
        <c:forEach items="${page.list}" var="opinion"
				varStatus="aa">
				<tr>
					<td >${aa.count}&emsp;</td>						
					<%--<td style="display:none">${opinion.id}</td>									
					--%>
					<td> ${opinion.tel}</td>
					<td> ${opinion.text}</td>
					<td> ${opinion.time}</td>				
					<td><a href='javascript:void(0)' id='feedback'  onclick='openFeedbackWin()'>推送</a></td>			
				</tr>
		</c:forEach> 
                
     </tbody> 

 </table>
 </div>
 <div class="p-b"></div>

 <div class="fenye">
 <div  class="fy-l" align="center" colspan="4">共 ${page.totalPages}页 | 第 ${page.pageNo}页</div>
<div class=" fy-r" >
    [<a href="T_opinion_Servlet?pageNo=1" >首页</a> 
                | <a href="T_opinion_Servlet?pageNo=${page.prePage }" >上一页</a> | 
                <a href="T_opinion_Servlet?pageNo=${page.nextPage}" >下一页</a> | 
                <a href="T_opinion_Servlet?pageNo=${page.bottomPage}" >末页</a>] 

</div>

 </div>


  </body>
  
  
</html>
