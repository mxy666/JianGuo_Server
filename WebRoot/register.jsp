<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0"/>
<title>注册</title>
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link href="css/login-res.css" rel="stylesheet" type="text/css" />

</head>
<body>
	
	
<div class="main"> 
	<div class="title-login">
		<a href="#" class="back"></a>
		<span>注册</span>
		
	</div>
	<div class="login-con res-con">		 
		<form id="sms" action="T_webRergister_Servlet" method="post">
		<div class="form">	
			<div class="div-phone">
				<em></em>
				<input type="text" id="phone" name="phone" class="infos" placeholder="请输入手机" value="${phone}"/>
				<a  class="send1" onclick="sends.send();">验证</a>
			</div>
			<div class="div-ranks">
				<em></em>
				<input type="text" id="sms_code" name="sms_code" class="infos" placeholder="请输入验证码"  />
			</div>	
			<div class="div-pass">
				<em></em>
				<input type="password" id="pwd1" class="infos" name="password" placeholder="请输入您的密码（6~32位）"  />
			</div>
			<div class="div-qr">
				<em></em>
				<input type="password" id="pwd2" onblur="return checkPass();" class="infos" name="password1" placeholder="请再输入一次您的密码"  />
			</div>			
		</div>	
		 <div class="radio" > 
			<label class="label">
				<input type="checkbox" name="sex" value="" checked="checked" id="checkbox"/>
				<div class="option"></div>
				<span class="opt-text">我已阅读并同意<a href="#">《兼果用户协议》</a></span>
			</label>
 		</div>
		
		<div class="div-conform">
			<button id="register" class="conform">确认注册</button>
		</div>
	<span class="error hide" id="errorpwd">两次密码输入不正确</span>
	</form>
		
	</div>
</div>	

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
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
			var url = "T_webSms_Servlet?phone="+val+"&regFlag=reg";
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


	function checkPass(){
	var pwd1=document.getElementById("pwd1").value;
	var pwd2=document.getElementById("pwd2").value;
	if(pwd1!=pwd2){
	document.getElementById("errorpwd").style.display = "block";
	setInterval("document.getElementById('errorpwd').remove()", 2000);
	setInterval("location.reload()", 2500);
	return false;

	}

	}
	function checkbox(){
	if($('#checkbox').is(':checked')) {
	$(".conform").removeAttr("disabled","disabled").css("background","#fdc82f")
	}else{
	$(".conform").attr("disabled","disabled").css("background","#d8d8d8")
	}
	}
	setInterval("checkbox()", 100);
</script>

</body>
</html>