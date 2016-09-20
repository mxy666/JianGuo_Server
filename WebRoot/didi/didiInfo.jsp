<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    String flag=request.getAttribute("flag").toString();

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0"/>
    <meta charset="utf-8">
    <title>领取结果</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/didi.css">


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

        $(document).ready(function(){
            var a =  <%=flag%>;
            if(a == 7){
                $(".hide").show();//显示已领取
                $(".show").hide();
            }else{
                $(".hide").hide();
                $(".show").show();
            }
        });

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
    <div class="bg-img">

    </div>

    <div class="yell">
        <div class="yell-title">
            <div class="qian">
                <p class="show">恭喜获得</p>
                <p class="hide">您已领取</p>
                <p>
                    <span>${amount}</span>
                    <em>元</em>
                </p>
            </div>
        </div>
        <div class="yell-main">
            <h1>打车劵已放入账号:&nbsp;<span>${phone}</span></h1>
            <c:forEach items="${infos}" var="infos" varStatus="aa">
            <div class="yell-text">

                <div class="text-main">

                    <div class="f-r mt">
                        <p class="zhe shows">

                            <c:if test="${infos.discount/10==0.0}"><span>￥</span>${infos.amount}</c:if>
                            <c:if test="${infos.discount/10!=0.0}">${infos.discount/10}<span>折</span></c:if>
                        </p>

                    </div>
                    <div class="f-l font-x">
                         ${infos.name}
                             <p class="youx1">有效期至${infos.deadline}</p>
                    </div>
                    <p class="youx f-l">
                            ${infos.remark}
                    </p>

                </div>

            </div>
            </c:forEach>

                </div>
            </div>
        </div>



</body>
</html>
