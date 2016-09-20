<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0"/>
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/didi.css">
    <link rel="shortcut icon" href="../img/ico.png">

    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
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
<body>
<div class="main">
    <div class="bg-title">
        <div class="didi-title">
            <div class="jianguo">
                <em></em>
                <b>兼果</b>
            </div>
            <div class="didi">
                <em></em>
                <b>滴滴出行</b>
            </div>

        </div>
    </div>

    <div class="didi-ban">
        <p class="bg-didi"></p>
        <span>兼果给你200元滴滴出行专属大礼包</span>
    </div>
    <div class="bg-img"></div>

    <div class="main-index">
        <div class="title-font">滴滴企业用户特权</div>
        <ul>
            <li>
                <div>
                    <p>1. 200元</p>
                    <p>滴滴出行劵</p>
                </div>

            </li>
            <li>
                <div>
                    <p>2. 优先</p>
                    <p>单排特权</p>
                </div>


            </li>
            <li>
                <div>
                    <p>3. 4.5星</p>
                    <p>级司机接送</p>
                </div>

            </li>
            <li>
                <div>
                    <p>4. 140</p>
                    <p>万乘车保险</p>
                </div>

            </li>
        </ul>
        <form id="didiFrom" method="post" action="/DidiTicketsServlet">

            <div class="p-r hei">
                <input type="text" name="phone" id="mobile" placeholder="请输入您的手机号">
                <div id="moileMsg"></div>
                <button onclick="return checkSubmitMobil()" class="buttom">点击领取</button>
            </div>

        </form>
    </div>


</div>
<script>
    function checkSubmitMobil() {
        if ($("#mobile").val() == "") {

            $("#moileMsg").html("<font color='red'>手机号码不能为空！</font>");
            $("#mobile").focus();
            return false;
        }

        if (!$("#mobile").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {

            $("#moileMsg").html("<font color='red'>手机号码格式不正确！请重新输入！</font>");
            $("#mobile").focus();
            return false;
        }else{


                $('#didiFrom').form('submit');

        }
    }
</script>

</body>
</html>