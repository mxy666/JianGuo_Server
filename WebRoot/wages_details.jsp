<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="date/WdatePicker.js"></script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript" type="text/javascript"> 
function demo() {
		var b = false;
		if (confirm('确定支付吗')) {
			b = true;
		}
		return b;
	}
function demor() {
		var b = false;
		if (confirm('确定取消支付吗')) {
			b = true;
		}
		return b;
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
	text-align: center;
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
 <center><h2>支付详情</h2>
 <table id="mytable" cellspacing="0"
			summary="The technical specifications of the Apple
PowerMac G5 series">

<tr><td><h2><img height="200dip" width="300dip" id="" alt=""src="${front_image }"></h2></td>
<td><h2><img height="200dip" width="300dip" id="" alt="" src="${behind_image }"></h2></td></tr>
<tr><td><h2>真实姓名：${realname }</h2></td><td><h2>身份证号：${id_number }</h2></td></tr>

</table>

    <table id="mytable" cellspacing="0"
			summary="The technical specifications of the Apple
PowerMac G5 series">
			<caption>
			</caption>
			
			<tr><td><h2>电话：${tel }</h2></td><td><h1>姓名：${name }</h1></td></tr>
			<tr><td><h2>方式：${type }</h2></td><td><h1>账号：${hao }</h1></td></tr>
			<tr><td><h2>余额：${login_money }</h2></td><td><h1 style="color: #0000CD">提现金额：${money }</h1></td></tr>
    	</table>
    <table border="2">
				<tr>
					<th>
						<h2><a href="T_moneyout_Pay_Servlet?id=${id }&login_id=${login_id}&cityId=${cityId}" onclick="return demo()"> 确定支付 </a></h2>
					</th>
				</tr>
			</table>
			 <form action="T_moneyout_Pay_NoServlet" method="POST"> 
		<table id="mytable" cellspacing="0"
				summary="The technical specifications of the Apple
					PowerMac G5 series">
		<tr> 
		<td width="30%" align="right" bordercolor="#0099FF" bordercolor="#ffffff">备注:</br>(建议在20字以内)</td> 
		<td width="70%" align="left" bordercolor="#0099FF" bordercolor="#ffffff">
		<textarea name="remarks" rows="4" cols="30" ></textarea></td> 
		<input type="hidden" name="id" value="${id}"/>
		<input type="hidden" name="login_id" value="${login_id}"/>
		<input type="hidden" name="money" value="${money}"/>
		<input type="hidden" name="cityId" value="${cityId}"/>
		</tr> 
		<tr> 
		<th bordercolor="#0099FF" bordercolor="#ffffff" colspan="2" align="center">
		<input type="submit" value=" 取消支付 " onclick="return demor()" /></th> 
		</tr> 
		</table> 
		</form> 
    </center>
  </body>
</html>
