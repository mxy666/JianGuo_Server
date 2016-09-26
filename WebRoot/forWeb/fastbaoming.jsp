<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
/*    String sex="";
    String identify="";
    if(request.getAttribute("sex").toString()!=null){
    sex=request.getAttribute("sex").toString();
    }
    if(request.getAttribute("identify").toString()!=null){
        identify=request.getAttribute("identify").toString();
    }*/


%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn">
<head>
    <meta name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0"/>
    <meta charset="utf-8">
    <title>极速报名-兼果兼职</title>
    <link rel="stylesheet" href="<%=basePath %>css/public.css">
    <link rel="stylesheet" href="<%=basePath %>css/web.css">
    <link rel="stylesheet" href="<%=basePath %>css/swiper.min.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7acf5cba21c41b3480d4bfc3e8204874&plugin=AMap.CitySearch"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>


    <script>
        (function(){
            function change(){
                var font=document.documentElement.clientWidth/(320/20);
                document.documentElement.style.fontSize=font+'px';
            }
            window.addEventListener('resize',change,false);
            change();
        })()
    </script>
</head>
<body onload="showCityInfo()">
<div class="sign-main">


    <div class="index-title">
        <div class="dw f-l dw-sigh">
            <%--<em></em>--%>
        </div>
        <span class="title title-login">极速报名</span>
        <a href="../webLogin.jsp" class="login-sign f-r">去登录</a>
    </div>
    <div class="Remarks">
        填写资料验证手机，可更快上岗并确保工资到帐
    </div>
    <form id="baoming" action="JobWebBaoMServlet" method="post">
        <div class="dw f-l" style="display: none">

                <input id="tip" type="text" name="city" /><%--<span id="tip"></span>--%>


        </div>
        <div class="form-main">
            <input id="jobId" name="jobId" type="hidden"  value=${jobId}/>
            <ul class="form-ul">
                <li>
                    <span>姓　　名</span>
                    <input type="text" id="name" name="name" placeholder="请输入真实姓名" value="${name}" class="name"/>
                </li>
                <li>
                    <span>性　　别</span>
                    <label>
                        <input  type="radio"  name="sex" <c:if test="${sex==0}">checked="true"</c:if> value="0" class="ra sex0"/>
                        <span class="f65">男</span>
                    </label>
                    <label>
                        <input  type="radio"  name="sex" <c:if test="${sex==1}">checked="true"</c:if> value="1" class="ra sex1"/>
                        <span  class="f65">女</span>
                    </label>
                </li>
                <li>
                    <span>出生年份</span>
                    <input type="text" id="birthdate" name="birthdate" placeholder="请输入出生年份" value="${birthdate}" class="birthdate"/>
                </li>
                <li>
                    <span>身　　份</span>
                    <label>
                        <input type="radio"  name="identify" <c:if test="${identify==0}">checked="true"</c:if> value="0" class="ra identify0"/>
                        <span  class="f65">学生</span>
                    </label>
                    <label>
                        <input type="radio"   name="identify"  <c:if test="${identify==1}">checked="true"</c:if> value="1" class="ra identify1"/>
                        <span  class="f65">非学生</span>
                    </label>
                </li>
                <li class="mt8">
                    <span>手 机 号</span>
                    <input type="text" name="phone"placeholder="请输入手机号" value="${phone}" maxlength="11" onkeyup='this.value=this.value.replace(/\D/gi,"")'class="Telephone"/>
                    <p class="err"></p>
                </li>
                <li>
                    <span>验 证 码</span>
                    <input type="text" name="smscode"placeholder="请输入验证码" class="w5"/>
                    <a href="javascript:;" class="yz">获取验证码</a>
                </li>
            </ul>
            <div class="div-conform">
                <button class="conform" onclick="submitForm()">完成并报名</button>
            </div>
        </div>
    </form>
    <span class="noyanz">无法获取验证码？</span>
    <div class="Explain hide">
        <h1>无法获取验证码</h1>
        <div>
            <h3>您可以尝试下列解决方式：</h3>
            <p>1、短信可能有2-10分钟延迟，可以稍等一下。</p>
            <p>2、查看是否误设置了短信拦截</p>
            <p>3、确认手机没有停机现象，保证服务正常</p>
        </div>
        <div class="tel">
            <p>如上述方法依旧无法解决，</p>
            <p>请拨打 010-5335-0021 获取帮助！</p>
        </div>
        <p class="close">
            <em></em>
        </p>
    </div>
</div>
</body>
<script>
    //不能收到验证码？
    $(".noyanz").click(function(){
        $(".Explain").show();
        $(".close").on("click",function(){
            $(".Explain").hide();
        });
    });
    //验证码倒计时
    $(".yz").click(function(){

        var tel = $(".Telephone").val();
        var name=$(".name").val();
       // var sex=document.getElementById('sex').checked;
        var birthdate=$(".birthdate").val();
        var identify =document.getElementsByName('identify');
       // var phone=document.getElementsByName("phone");
        var sex= document.getElementsByName("sex");
        for(var i = 0; i < sex.length; i ++) {

            if(sex[i].checked)

            {

                sex = sex[i].value;
            }
        }
        for(var i = 0; i < identify.length; i ++) {

            if(identify[i].checked)

            {

                identify = identify[i].value;
            }
        }
        var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;


        if (reg.test(tel)) {
            var wait=60;
            timeOut();


            function timeOut(){

                if(wait==0){
                    $('.yz').text("重新发送");
                }else{
                    var urls = "T_webSms_Servlet?phone="+tel+"&fastTel=fastBaoming&name="+name+"&sex="+sex+"&birthdate="+birthdate+"&identify="+identify+"";
                    window.location = urls;

                    setTimeout(function(){
                        wait--;
                        $('.yz').text(wait + "s");
                        timeOut();
                    },1000)

                }
            }
            $(".err").hide();
        }else{
            $(".err").text("请输入正确的手机号码！").show()
        };

    });
    function submitForm(){

        $('#baoming').form('submit');
    }


    //获取用户所在城市信息
    function showCityInfo() {
        //实例化城市查询类
        var citysearch = new AMap.CitySearch();
        //自动获取用户IP，返回当前城市
        citysearch.getLocalCity(function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                if (result && result.city && result.bounds) {
                    var cityinfo = result.city;
                    var citybounds = result.bounds;
                    document.getElementById('tip').value = cityinfo;
                    //地图显示当前城市
                    map.setBounds(citybounds);
                }
            } else {

                document.getElementById('tip').value=result.info

            }
        });
    }
</script>
</html>
