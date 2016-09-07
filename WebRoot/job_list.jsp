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
	width: 2200px;
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
		if (confirm('确定支付吗')) {
			b = true;
		}
		return b;
	}
</script>

	</head>

	<body>
		<center>
			<h2>
				结算列表
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
						名称
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						电话
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						工作日期
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						金额
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						工作地址
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						集合地点
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						集合时间
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						工作内容
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						工作要求
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						商家名称
					</th>
					<th bordercolor="#0099FF" bordercolor="#ffffff">
						商家电话
					</th>
					<th colspan="2">
						操作
					</th>
				</tr>

				<c:forEach items="${list_t_job_info}" var="t_job_info"
					varStatus="aa">
					<tr>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${aa.count}&emsp;
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<img height="20dip" width="20dip" id="" alt=""
								src="${t_job_info.job_image }">
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.job_name}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.tel}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.start_time}/${t_job_info.stop_time}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.job_money}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.address}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.set_place}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.set_time}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.work_content}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.work_require}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.job_merchant_name}
						</th>
						<th bordercolor="#0099FF" bordercolor="#ffffff">
							${t_job_info.job_merchant_tel}
						</th>

						<th bordercolor="#0099FF" bordercolor="#ffffff">
							<a href="T_job_wai_List_Servlet?job_id=${t_job_info.job_id}">补加</a>
							<!--<a href="T_job_wai_OKList_Servlet?job_id=${t_job_info.job_id}">查看</a>
						--></th>
					</tr>
				</c:forEach>
			</table>
		</center>
	</body>
</html>
