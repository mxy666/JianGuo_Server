<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>My JSP 'file.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<script type="text/javascript">
	
	function check(){
			var names = document.getElementById("names").value;
			var tels = document.getElementById("tels").value;
			
			if(names = null || names.length==0){
				document.getElementById("lanames").innerHTML="<font color='red'>姓名不能为空！！！</font>";
				return false;
			}
			if(tels = null || tels.length==0){
				document.getElementById("latels").innerHTML="<font color='red'>电话不能为空！！！</font>";
				return false;
			}
			
			return true;
		}
		

function onlyNum()
{
 if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
  if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
}
	
	</script>

		<style type="text/css">
body {
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
	background: #E6EAE9;
}

a {
	color: #c75f3e;
}

#mytable {
	width: 500px;
	padding: 0;
	margin: 0;
}

caption {
	padding: 0 0 5px 0;
	width: 700px;
	font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	text-align: right;
}

th {
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: centrt;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA no-repeat;
}

th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

td {
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	background: #fff;
	font-size: 11px;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;
}

td.alt {
	background: #F5FAFA;
	color: #797268;
}

th.spec {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #fff no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #f5fafa no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #797268;
}

html>body td {
	font-size: 11px;
}

body,td,th {
	font-family: 宋体, Arial;
	font-size: 12px;
}
</style>

	</head>

	<body>
		<center>
			<h2>
				增加类别信息
			</h2>
			<form action="T_PC_User_Insert_Servlet" method="POST">
				<table id="mytable" cellspacing="0"
					summary="The technical specifications of the Apple
					PowerMac G5 series">
					<tr>

						<th bordercolor="#0099FF" bordercolor="#ffffff" width="30%"
							align="right">
							电话:
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff" width="70%"
							align="left">
							<!--<input onpaste="return false" id="tels" 
		style="IME-MODE: disabled; WIDTH: 150px; HEIGHT: 22px" 
		onpropertychange="if(isNaN(value)) value=value.substring(0,value.length-1);" 
		maxlength="11" size="14" name="tel" />
		-->
							<!--<input id="names" type="text" name="tel"
								onafterpaste="this.value=this.value.replace(/^1\d{10}$/,'')"
								maxlength="11" />
								
								--><input id="names" type="text" name="tel" maxlength="11" onkeydown="onlyNum();"/>

							<input id="tels" type="hidden" name="job_id" value="${job_id}" />
							<span id="latels"></span>
						</th>
					</tr>
					<tr>
						<th bordercolor="#0099FF" bordercolor="#ffffff" width="30%"
							align="right">
							姓名:
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff" width="70%"
							align="left">
							<input id="names" type="text" name="name" />
							<span id="lanames"></span>
						</th>
					</tr><%--
					<tr>
						<th width="30%" align="right" bordercolor="#0099FF"
							bordercolor="#ffffff">
							位置:
						</th>
						<th width="70%" align="left" bordercolor="#0099FF"
							bordercolor="#ffffff">
							<select name="sex">
								<option value="1">
									【男】
								</option>
								<option value="0">
									【女】
								</option>
							</select>
						</th>
					</tr>
					<tr>
						<th bordercolor="#0099FF" bordercolor="#ffffff" width="30%"
							align="right">
							学校:
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff" width="70%"
							align="left">
							<input id="schools" type="text" name="school" />
							<span id="laschools"></span>
						</th>
					</tr>--%>
					<tr>
						<th bordercolor="#0099FF" bordercolor="#ffffff" colspan="2"
							align="center">
							<input type="submit" value=" 提 交 " onclick="return check()" />
						</th>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>

