<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String name =  (String)request.getAttribute("name");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户反馈</title>
    
	<link rel="stylesheet" type="text/css" href="UI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="UI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="UI/demo.css">
	<script type="text/javascript" src="UI/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="UI/jquery.easyui.min.js"></script>
	
	<script type="text/javascript">
	

	
	function check(){
	
		var message = document.getElementById("message").value;		
		if(message = null || message.length==0){
			
			alert("推送内容不能为空！！！");
			return false;
		}
	}

function Close() {

		window.close();
}
	
	</script>
  </head>

  <body>
  
<!-- 用户详细信息窗口 -->
<form action="T_psuhFeedBack_Servlet" method="post">
	<div id="user" title="Window Layout"  data-options="iconCls:'icon-save'" style="width:780px;height:540px;padding:5px;align:center;">
		<input  type="hidden" name="login_id" readonly="readonly"  value="${login_id}" style="height:40px;">
		意见：<textarea id="text" name="text" cols="50" rows="8" >${text}</textarea>
		反馈内容：<textarea id="message" name="message" cols="50" rows="8"></textarea><br><br><br><br>
		<center>
			<div class="pushButton">
				<input id="push" name="push" type="submit" class="add" value="推送" onclick="return check()"/>
				&nbsp&nbsp<input type="button"  value="取消" id="close" onclick="Close()"/>
			</div>
		</center>
	</div>
  </from>


  </body>
  
  
</html>
