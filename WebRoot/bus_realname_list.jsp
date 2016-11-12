<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.jianguo.bean.T_user_moneyout_Bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String cityName = request.getAttribute("cityName").toString();
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
	width: 1000px;
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
	function demo() {
		var b = false;
		if (confirm('确定通过吗')) {
			b = true;
		}
		return b;
	}
	function demor() {
		var b = false;
		if (confirm('确定不通过吗')) {
			b = true;
		}
		return b;
	}
</script>

	</head>

	<body>
		<center>
			<h2>
				实名审核的人
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
						真实姓名
					</th>
                    <th bordercolor="#0099FF" bordercolor="#ffffff">
						城市
					</th>
                    <th bordercolor="#0099FF" bordercolor="#ffffff">
						商家名称
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						电话
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						商家类型
					</th>
					<th colspan="2">
						操作
					</th>
				</tr>

				<c:forEach items="${list_business_login}" var="merchant"
					varStatus="aa">
					<tr>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${aa.count}&emsp;
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${merchant.real_name}
						</th>	<th bordercolor="#0099FF" bordercolor="#ffffff">
							${merchant.city}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${merchant.companyName}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${merchant.tel}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<c:if test="${merchant.about eq '1'}">
								外部商家
							</c:if>
							<c:if test="${merchant.about eq '2'}">
								个人商户
							</c:if>
							<c:if test="${merchant.about eq '0'}">
								内部商家
							</c:if>

						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<a href="T_business_realname_Pass_No_Servlet?login_id=${merchant.login_id}&city_id=${cityName}">查看详情</a>
						</th>
					</tr>
				</c:forEach>
			</table>
		</center>
	</body>
</html>
