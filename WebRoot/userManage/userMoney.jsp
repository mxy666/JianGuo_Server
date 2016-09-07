<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String login_id =  (String)request.getAttribute("login_id");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户钱包信息</title>
    
	<link rel="stylesheet" type="text/css" href="UI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="UI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="UI/demo.css">
	<script type="text/javascript" src="UI/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="UI/jquery.easyui.min.js"></script>
	
	<script type="text/javascript">
	
	function checkNum(obj) {  
	     //检查是否是非数字值  
	     if (isNaN(obj.value)) {  
	         obj.value = "";  
	     }  
	     if (obj != null) {  
	         //检查小数点后是否对于两位http://blog.csdn.net/shanzhizi  
	         if (obj.value.toString().split(".").length > 1 && obj.value.toString().split(".")[1].length > 2) {  
	             alert("小数点后多于两位！");  
	             obj.value = "";  
	         }  
	     }  
	 } 
	
	function check(){
		

		var money = document.getElementById("money").value;
		var remark = document.getElementById("remark").value;
					
		if(money = null || money.length==0){
			alert("金额不能为空！！！");
			return false;
		}
		
		if(remark = null || remark.length==0){
			alert("备注不能为空！！！");
			return false;
		}
	}

	  function Close() {

		window.close();
}
	
	</script>
  </head>

  <body>
  
<!-- 用户详细信息窗口 -->
	<form action="T_modifyMoney_Servlet?loginId=<%= login_id%>" method="post">
		<h2>总计</h2>
		工资总额：<input  type="text" id="wage" name="wage" readonly="readonly" value="${wage}" style="height:30px;border:none;"></input>
		提现总额：<input type="text" id="cash" name="cash" readonly="readonly" value="${cash}" style="height:30px;border:none;"></input>
		钱包余额(可修改)：<input type="text" id="money" name="money"  value="${money}"onkeyup="checkNum(this)"></input><br/>
		修改备注:<textarea id="remark" name="remark" rows="5" cols="48"></textarea>
		&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit"   value="修改" id="update" onclick="return check()"/>&nbsp&nbsp&nbsp&nbsp&nbsp
			<input type="button"  value="取消" id="close" onclick="Close()"/>
	
		<table id="job" class="easyui-datagrid" stytle="weight:300px;"></table>
</form>
 
<script type="text/javascript">
function pagerFilter(data){
	if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
		data = {
			total: data.length,
			rows: data
		}
	}
	var dg = $(this);
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');
	pager.pagination({
		onSelectPage:function(pageNum, pageSize){
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh',{
				pageNumber:pageNum,
				pageSize:pageSize
			});
			dg.datagrid('loadData',data);
		}
	});
	if (!data.originalRows){
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = (data.originalRows.slice(start, end));
	return data;
}
$(function(){
var loginId=<%=login_id%>;
$('#job').datagrid({ 
	
	url:'T_JobForUser_Servlet?loginId='+loginId, 				
	columns:[[ 				
		{field:'job_id',title:'Id',width:10,hidden:true}, 
		{field:'name',title:'兼职名称',width:150},					
		{field:'address',title:'地址',width:150},
		{field:'status1',title:'状态',width:80},
		{field:'money',title:'金额',width:100},
		{field:'start_date',title:'开始时间',width:150}, 
		{field:'stop_date',title:'结束时间',width:150}								
	]] ,
	
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination:true,
	pageSize:10,
	pageList:[10,15],
	loadFilter:pagerFilter
	/*toolbar:[{
		text:'导出',
		iconCls:'icon-save',
		handler:function(){		
		  var loginId=row.login_id;								    
		  var url = "T_JobExport_Servlet?loginId="+loginId;
	      window.location = url;
	}},{
		text:'下载',
		iconCls:'icon-save',
		handler:function(){						    
		var url = "http://192.168.1.135:8080/JianGuo_Server/downLoadFile/Job.xls";
	    window.location = url;
	}
}
]*/
	

});
});
</script>

  </body>
  
  
</html>
