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
	<script type="text/javascript" src="date/WdatePicker.js">
	
	function demoer() {
		var b = false;
		if (confirm('确定不支付吗')) {
			b = true;
		}
		return b;
	}
	
	</script>
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
	width: 1200px;
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

		<script type="text/javascript">
	function check() {
		var username = document.getElementById("usernames").value;
		var comment = document.getElementById("comments").value;
		if (username = null || username.length == 0) {
			document.getElementById("labusernames").innerHTML = "__tag_118$55_IU不号能为空！！！__tag_118$83_";
			return false;
		}
		if (comment = null || comment.length == 0) {
			document.getElementById("labcomments").innerHTML = "__tag_122$54_评论不能为空！！！__tag_122$81_";
			return false;
		}
		return true;
	}
	function demoer() {
		var b = false;
		if (confirm('确定不支付吗')) {
			b = true;
		}
		return b;
	}
</script>

	</head>

	<body>
		<center>
			<h2>
				用户提现列表
			</h2>

			<table id="mytable" cellspacing="0"
				summary="The technical specifications of the Apple
PowerMac G5 series">
				<caption>
				</caption>
				<tr>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						编号
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						图片
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						真实姓名
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						支付姓名
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						地区
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						方式
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						账号
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						余额
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						提现金额
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						时间
					</th>
					<th colspan="3">
						操作
					</th>
				</tr>

				<c:forEach items="${list_t_user_moneyout}" var="t_user_moneyout"
					varStatus="aa">
					<tr>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${aa.count}&emsp;
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<img height="70dip" width="100dip" id="" alt=""
								src="${t_user_moneyout.login_nameimage }">
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.login_names}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.login_name}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.login_id_city}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.login_type}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.login_hao}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.login_money}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.money}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_user_moneyout.time}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<a
								href="T_wages_List_Servlet?login_id=${t_user_moneyout.login_id }">详情</a>
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<a href="T_wages_Details_Servlet?id=${t_user_moneyout.id }&cityId=${t_user_moneyout.login_id_city}">支付</a>
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<a href="T_user_moneyout_Delete_Servlet?id=${t_user_moneyout.id }" onclick="return demoer()">删除</a>
						</th>
					</tr>
				</c:forEach>
			</table>
		</center>
	</body>
</html>
