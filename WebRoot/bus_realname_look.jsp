<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

        html > body td {
            font-size: 11px;
        }

        body, td, th {
            font-family: 宋体, Arial;
            font-size: 12px;
        }
    </style>

</head>

<body>
<center><h2>实名审核</h2>
    <table  cellspacing="0"
           summary="The technical specifications of the Apple
PowerMac G5 series">

        <tr>
            <td><h2><img height="300dip" width="500dip" alt="" src="${t_bus_realname.cardImage }"></h2></td>
            <td><h2><img height="300dip" width="500dip" alt="" src="${t_bus_realname.handImage }"></h2></td>
        </tr>

        <tr>
            <td><h2><img height="300dip" width="500dip" alt="" src="${t_bus_realname.bussinessImage }"></h2></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td><h2>真实姓名：${t_bus_realname.real_name }</h2></td>
            <td><h2>身份证号：${t_bus_realname.card_num }</h2></td>
        </tr>
        <tr>
            <td><h2>电话：${t_bus_realname.tel }</h2></td>
            <td><h2>公司名：${t_bus_realname.companyName }</h2></td>
        </tr>

    </table>

    <table  cellspacing="0"
           summary="The technical specifications of the Apple
PowerMac G5 series">
        <caption>
        </caption>

        <%--<tr><td><h2>电话：</h2></td><td><h1>${tel }</h1></td></tr>--%>
        <%--<tr><td><h2>姓名：</h2></td><td><h1>${realname }</h1></td></tr>--%>
        <%--<tr><td><h2>性别：</h2></td><c:if test="${sex=='1'}"> --%>
        <%--<td><h1>男</h1></td>						--%>
        <%--</c:if>	--%>
        <%--<c:if test="${sex=='0'}"> --%>
        <%--<td><h1>女</h1></td></c:if>	--%>
        <tr>
            <td></td>
            <td><h2><a href="T_bus_realname_Pass_Servlet?login_id=${t_bus_realname.login_id}&pass=1&m_id=${t_bus_realname.id}" onclick="return demo()"> 通过 </a>
            </h2></td>
        </tr>
        </table>
        <form action="T_bus_realname_Pass_Servlet" method="POST">
            <table id="mytable" cellspacing="0"
                   summary="The technical specifications of the Apple
					PowerMac G5 series">
                <tr>
                    <td width="30%" align="right" bordercolor="#0099FF" bordercolor="#ffffff">原因:</td>
                    <td width="70%" align="left" bordercolor="#0099FF" bordercolor="#ffffff">
                        <select name="beizhu">
                            <option value="网络原因实名信息上传失败，无法认证，请按要求重新上传">网络原因实名信息上传失败，无法认证，请按要求重新上传</option>
                            <option value="照片模糊，无法认证，请近距离横向拍摄并重新上传">照片模糊，无法认证，请近距离横向拍摄并重新上传</option>
                            <option value="请按要求上传实名信息，认证失败，请按要求重新上传">请按要求上传实名信息，认证失败，请按要求重新上传</option>
                            <option value="请上传原件照片，认证失败，请按要求重新上传">请上传原件照片，认证失败，请按要求重新上传</option>
                            <option value="照片显示不全，无法实名认证，请按要求重新上传">照片显示不全，无法实名认证，请按要求重新上传</option>
                            <input type="hidden" name="login_id" value="${t_bus_realname.login_id}"/>
                            <input type="hidden" name="pass" value="0"/>
                            <input type="hidden" name="m_id" value="${t_bus_realname.id}"/>
                </tr>
                <tr>
                    <td width="30%" align="right" bordercolor="#0099FF" bordercolor="#ffffff">其他:</td>
                    <td width="70%" align="left" bordercolor="#0099FF" bordercolor="#ffffff">
                        <input type="text" name="qita" style="width: 400px" value="无"/>
                    </th>
                </tr>
                <tr>
                    <th bordercolor="#0099FF" bordercolor="#ffffff" colspan="2" align="center">
                        <input type="submit" value=" 不通过 " onclick="return demor()"/></th>
                </tr>
            </table>
        </form>
</center>
</body>
</html>
