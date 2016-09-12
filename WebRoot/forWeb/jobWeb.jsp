<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=2.0"/>
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="stylesheet" href="<%=basePath %>css/public.css">
    <link rel="stylesheet" href="<%=basePath %>css/web.css">
    <link rel="stylesheet" href="<%=basePath %>css/swiper.min.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.7.2.min.js"></script>
    <script src="<%=basePath %>js/swiper.min.js"></script>
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
<body onload="loadJob()">
<div class="main">
    <div class="index-title">
        <div class="dw f-l">
            <span id="click-bn">北京</span>
            <em></em>
        </div>
        <span class="title">首页</span>
        <a href="#" class="my f-r"></a>
    </div>
    <!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <a href="#">
                    <img src="<%=basePath %>img/banner-1.png"width="100%" height="100%">
                </a>
            </div>
            <div class="swiper-slide">
                <a href="#">
                    <img src="<%=basePath %>/img/banner-1.png"width="100%" height="100%">
                </a>
            </div>
            <div class="swiper-slide">
                <a href="#">
                    <img src="<%=basePath %>img/banner-1.png"width="100%" height="100%">
                </a>
            </div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    </div>
    <div class="list">
        <ul>
            <a href="#">
                <li>
                    <div class="bg-auto">
                        <div class="f-l">
                            <h1>精品兼职</h1>
                            <span>优质商家提供</span>
                        </div>
                        <em class="jp"></em>
                    </div>
                </li>
            </a>
            <a href="#">
                <li class="br0">
                    <div class="bg-auto">
                        <div class="f-l">
                            <h1 class="lx">兼职旅行</h1>
                            <span>穷游世界马上走</span>
                        </div>
                        <em class="lxing"></em>
                    </div>
                </li>
            </a>
            <a href="#">
                <li>
                    <div class="bg-auto">
                        <div class="f-l">
                            <h1 class="rj">日结兼职</h1>
                            <span>百元工资当日领</span>
                        </div>
                        <em class="rjimg"></em>
                    </div>
                </li>
            </a>
            <a href="#">
                <li class="br0">
                    <div class="bg-auto">
                        <div class="f-l">
                            <h1 class="cq">长期兼职</h1>
                            <span>长期兼职来这里</span>
                        </div>
                        <em class="cqimg"></em>
                    </div>
                </li>
            </a>

        </ul>
    </div>
    <div class="jzh-list">
        <div class="jzh-list-title">
            <em></em>
            <span>热门兼职</span>
            <!--em class="f-r"></em-->
        </div>
        <ul class="jzh-list-ul">
           <c:forEach items="${list_t_job}" var="job" varStatus="aa">
            <a href="#">
         
                <li>
                    <div class="imgDiv f-l">
                        <img src="<%=basePath %>img/ico/ico.png">
                    </div>
                    <div class="jzh-list-ul-text f-l">
                        <h1 class="h1">${job.name }</h1>
                        <div class="f-l bq">
                            <em class="Long-term"></em>
                            <em class="woman"></em>
                            <em class="man hide"></em>
                            <em class="sex"></em>
                            <em class="overdue"></em>

                        </div>
                        <div style="clear: both"></div>
                        <p>
                            <em class="ico"></em>
                            <span>${job.mode }</span>
                        </p>
                        <p>
                            <em class="ico1"></em>
                            <span>${job.start_date }-${job.stop_date }</span>
                        </p>
                        <p>
                            <em class="ico2"></em>
                            <span>${job.address }</span>
                        </p>
                    </div>
                    <div class="ovh">
                        <div class="canvas f-r">
                            <span  class="zhanmao hide">已招满</span>
                              <p class="nozhanmao">
                                <span class="span1">1</span>
                                  <span>/</span>
                                  <span class="span2">3</span>
                              </p>
                        </div>
                        <div class="money">160元/天</div>
                    </div>

                </li>
              
            </a>
              </c:forEach>
  </ul>
    </div>
<div id="Location" class="hide">
    <div class="index-title">
        <em class="Close"></em>
        <span class="title js-tiele">选择城市</span>
    </div>
    <ul class="long-term-ul"id="long-term-ul">
        <li>三亚</li>
        <li>海口</li>
        <li>北京</li>
    </ul>
</div>

    <div class="footer">
        <img src="<%=basePath %>img/ico/ico.png" class="f-l">
        <div class="f-l footer-text">
            <p>下载兼果APP</p>
            <p>兼果在手，兼职我有</p>
        </div>
        <em class="rem"></em>
    </div>


<script>
    /*轮播图*/
    var swiper = new Swiper('.swiper-container', {
         pagination: '.swiper-pagination',
        //  nextButton: '.swiper-button-next',
        // prevButton: '.swiper-button-prev',
        paginationClickable: true,
        spaceBetween: 0,
        centeredSlides: true,
        autoplay: 2500,
        autoplayDisableOnInteraction: false,
        loop: true
    });
/*兼职名称的字数控制*/
    $(document).ready(function(){
        //限制字符个数
        $(".h1").each(function(){
            //字符个数
            var maxwidth=5;
            if($(this).text().length>maxwidth){
                $(this).text($(this).text().substring(0,maxwidth));
                $(this).html($(this).html()+"…");
            }
        });
    });
/*定位*/
    $("#click-bn").click(function(){
        var Scr = $(document).scrollTop();
        var Location = document.getElementById("Location");
        var Width = document.documentElement.clientWidth;
        var Height = document.body.clientHeight;
        var Scr = $(document).scrollTop();
        var scr1 =Scr + "px";
        Location.style.width = Width+ "px";
        Location.style.height = Height + "px";
        Location.style.top = Height + Scr + "px";
        Location.style.display = "block";
        $("#Location").animate({top:scr1},1000);
        $(".Close").click(function(){
            var Scr = $(document).scrollTop();
            var Height = window.innerHeight;
            var Hid = Scr + Height + "px";
            $("#Location").animate({top:Hid},1000,function(){
                Location.style.display ="none"
            });
        });
        var obj_lis = document.getElementById("long-term-ul").getElementsByTagName("li");
        for(i=0;i<obj_lis.length;i++){
            obj_lis[i].onclick = function(){
                var li = this.innerHTML;
                var Location = document.getElementById("Location");
                var Scr = $(document).scrollTop();
                var Height = window.innerHeight;
                var Hid = Scr + Height + "px";
                $("#click-bn").html(li);
                $("#Location").animate({top:Hid},1000,function(){
                    Location.style.display ="none"
                });
            }
        }
    });
/*关闭底部*/
    $(".rem").click(function(){
        $(".footer").hide();
    });
    /*招满转换*/
    $(document).ready(function(){
        $(".nozhanmao").each(function(){
            if($(this).find(".span1").text() == $(this).find(".span2").text()){
                $(this).prev().removeClass("hide")
                $(this).addClass("hide")
            }


        });


    });
function loadJob(){
    var url="JobDetailWebServlet";
    //window.location.href=url;
    window.open(url)//参数可设你要调用的servlet,
}
</script>
</body>
</html>