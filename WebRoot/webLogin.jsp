<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0"/>
<title>登录</title>
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link href="css/login-res.css" rel="stylesheet" type="text/css" />
<!--link href="css/style.css" type="text/css" rel="stylesheet" /-->

</head>
<body>
	
	
<div class="main"> 
	<div class="title-login">
		<a href="#" class="back"></a>
		<span>登录</span>
		<a href="register.jsp">注册</a>
	</div>
		<div class="login-con-title">
			<div class="log-t-l backo">手机号快捷登录</div>
			<div class="log-t-r">账号密码登录</div>                
		</div>
		<div class="form">
		<!-- 便捷登录，手机号登录 -->
			<form id="sms" action="T_WebLogin_Servlet" method="post">
				<div class="div-phone">
					<em></em>
					<input type="text" id="phone" name="phone" class="infos" value="${phone}" placeholder="请输入手机号" />
					<a class="send1" onclick="sends.send();">验证</a>
				</div>
				<div class="div-ranks">
					<em></em>
					<input type="text" id="ranks" name="sms_code" class="infos" placeholder="请输入验证码"  />
				</div>
				<div class="div-text">
					未注册过的手机号将创建兼果账号
				</div>
				<div class="div-conform">
				<button id="conform_1" class="conform" onclick="submitForm_1()">登录</button>
				</div>
			</form>
		</div>

		<div class="form hide">
		<!-- 账号密码登录 -->
			<form  id="acc_pw" action="T_passWordLogin_Servlet" method="post">
				<div class="div-phone">
					<em></em>
					<input type="text" id="account" name="account" class="infos" placeholder="请输手机号" />

				</div>
				<div class="div-ranks">
					<em></em>
					<input type="text" id="password" name="password" class="infos" placeholder="请输入密码"  />
				</div>
				<div class="div-conform">
					<!--<input class="conform" type="submit" value=" " onclick="return check()"/>-->
					<button id="conform" class="conform" onclick="submitForm()">登录</button>
				</div>
			</form>
		</div>
	</div>


<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

function submitForm(){

	$('#acc_pw').form('submit');
}
function submitForm_1(){
	
	$('#sms').form('submit');
}

var sends = {
	checked:1,
	send:function(){
			var numbers = /^1\d{10}$/;
			var val = $('#phone').val().replace(/\s+/g,""); //获取输入手机号码
			if($('.form').find('span').length == 0 && $('.div-phone a').attr('class') == 'send1'){
				if(!numbers.test(val) || val.length ==0){
					$('.form').append('<span class="error">请输入正确的手机号</span>');
					setInterval("$('.form span').remove()", 2000);
					return false;
				}
			}
			var url = "T_webSms_Servlet?phone="+val+"&fastTel=fast";
		     window.location = url;
			if(numbers.test(val)){
				var time = 60;			
				function timeCountDown(){
					if(time==0){
						clearInterval(timer);
						$('.div-phone a').addClass('send1').removeClass('send0').html("重新验证");
						sends.checked = 1;
						return true;
					}
					$('.div-phone a').html(time+"S");
					time--;
					return false;
					sends.checked = 0;
				}
				$('.div-phone a').addClass('send0').removeClass('send1');
				timeCountDown();
				var timer = setInterval(timeCountDown,1000);
			}
	}

}

$(document).ready(function(){
	$('.login-con-title div').click(function(){
		var i=$(".login-con-title div").index(this);
		$('.form').hide();
		$('.form').eq(i).show();
	    $(".login-con-title div").removeClass('backo');
		$(this).addClass('backo');

	});
});
</script>

</body>
</html>