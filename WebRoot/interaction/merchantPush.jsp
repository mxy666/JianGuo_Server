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
    
    <title>商家推送</title>
    
<link rel="stylesheet" type="text/css" href="UI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="UI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="UI/demo.css">
	<script type="text/javascript" src="UI/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="UI/jquery.easyui.min.js"></script>
		<%--<link rel="stylesheet" type="text/css" href="css/common.css"/>	--%>
		
<style scoped="scoped">
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
		.pushButton{
		margin:0 auto;
		width: 30px;
   		height: 25px;
    	padding: 10px;
    	}
    	.phone{
    	height:45px;
    	width:323px;
    	}
	</style>		
<script type="text/javascript">
function check(){
	var phone = document.getElementById("phone").value;
	var message = document.getElementById("message").value;	
	if(phone = null || phone.length==0){	
		alert("电话号码不能为空！！！");
		return false;
	}	
	if(message = null || message.length==0){
		
		alert("推送内容不能为空！！！");
		return false;
	}
}


</script>
  </head>

  <body>
 <center>
<form action="T_PushForMer_Servlet" method="post">
<div style="margin:20px 0;">
	
		<table cellpadding="5">
			<tr>
				<td>请输入手机号:</td>
				<td><%--<input id="phone" name="phone" class="phone" type="text"  onKeyUp="value=value.replace(/[^\d]/g,'')"  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${phone}">			--%>
				<input id="phone" name="phone" class="phone" type="text"  value="${phone}">
				</td>
			</tr>
			<tr>
				<td>推送内容:</td>
				<td><textarea id="message" name="message" cols="50" rows="6" ></textarea> </td>
			</tr>				
		</table>
	<div class="pushButton">
		<input id="push" name="push" type="submit" class="add" value="推送" onclick="return check()"/>
	</div>
		
		
</div>
</form>	

</center>
  </body>
  
  
</html>
