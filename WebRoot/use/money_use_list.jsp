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
	
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'list.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
	






<script type="text/javascript" >
function del(){
	var msg = "您真的确定要删除吗？\n\n请确认！"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	}
 
}
function update(){
	var msg = "您真的确定要修改吗？\n\n请确认！"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	}
 
}

</script>
</head>

	<body>	
	<center>
			<h2 style="padding-top:10px;">
				用户结算列表
			</h2>
	</center>
	<tr><td> <a href="T_use_money_add_servlet?pageNo=1" class="add">新增</a></td></tr>
	<table id="dg" class="easyui-datagrid" style="width:100%">  	
      <thead>  
          <tr>
             <th data-options="field:'num'">序号</th>  
             <th data-options="field:'admin'">业务经手人</th>  
             <th data-options="field:'workDate'">工作日期</th>              
             <th data-options="field:'merchant'">商家</th>  
             <th data-options="field:'name'">姓名</th>  
             <th data-options="field:'tel'">电话号码</th>  
             <th data-options="field:'houldMoney'">应付工资</th>  
             <th data-options="field:'moneyOut'">提现金额</th>  
             <th data-options="field:'moneyOutDate'">提现日期</th> 
             <th data-options="field:'remarks'">备注</th>
             <th data-options="field:'manage'">操作</th>               
          </tr>  
      </thead>  
     <tbody>
       
        	<c:forEach items="${page.list}" var="useMoney"
					varStatus="aa">
					<tr>
						<td >${aa.count}&emsp;</td>						
						<td>${useMoney.admin}</td>
						<td> ${useMoney.workDate}</td>						
						<td> ${useMoney.merchant}</td>
						<td> ${useMoney.name}</td>
						<td> ${useMoney.tel}</td>
						<td> ${useMoney.houldMoney}</td>
						<td> ${useMoney.moneyOut}</td>
						<td> ${useMoney.moneyOutDate}</td>
						<td> ${useMoney.remarks}</td>
						<td>													
						 <a href="T_use_money_update_servlet?id=${useMoney.id}&pageNo=1" onclick="javascript:return update()">修改</a>|
						 <a href="T_use_money_delete_servlet?id=${useMoney.id}&pageNo=1" onclick="javascript:return del()">删除</a>
						</td>
					</tr>
				</c:forEach> 
					<tr><td  align="center" colspan="4">共 ${page.totalPages}页 | 第 ${page.pageNo}页</td>
 				
                <td  align="center" colspan="8">[
                <a href="T_money_use_Servlet?pageNo=1" >首页</a> 
                | <a href="T_money_use_Servlet?pageNo=${page.prePage }" >上一页</a> | 
                <a href="T_money_use_Servlet?pageNo=${page.nextPage}" >下一页</a> | 
                <a href="T_money_use_Servlet?pageNo=${page.bottomPage}" >末页</a>] </td></tr>
     </tbody>  
 </table>
<a href="T_use_money_export_servlet?pageNo=1" class="add">导出</a>
<a href="http://101.200.195.147:8080/JianGuo_Server/downLoadFile/UseMoney.xls" style="display:block"class="add">下载</a>

	</body>
</html>
