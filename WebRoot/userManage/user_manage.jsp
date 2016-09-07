<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String name =  (String)request.getAttribute("name");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>钱包详情</title>
    
<link rel="stylesheet" type="text/css" href="UI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="UI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="UI/demo.css">
	<script type="text/javascript" src="UI/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="UI/jquery.easyui.min.js"></script>
		<%--<link rel="stylesheet" type="text/css" href="css/common.css"/>	--%>
<script type="text/javascript"><%--

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



--%></script>
  </head>
  <style>

  
  </style>
  <body>
  
<script type="text/javascript">	
	
$(function(){
	$('#dg').datagrid({ 
		
		url:'T_QueryUser_Servlet', 	
		columns:[[ 
	
		{field:'login_id',title:'Id',width:100,hidden:true}, 
		{field:'name',title:'姓名',width:100},
		{field:'sex',title:'性别',width:71},
		{field:'tel',title:'电话',width:150}, 
		{field:'city_id',title:'区域',width:150},
		{field:'school',title:'学校',width:250},
		{field:'money',title:'余额',width:100},
		{field:'job_id',title:'钱包详情',width:100},
		{field:'detail',title:'用户详情查看',width:100}	
	
		]] ,
		rownumbers:true,
		singleSelect:true,
		autoRowHeight:true,
		pagination:true,
		pageSize:15,
		pageList:[15,20,25]
		//loadFilter:pagerFilter
		//toolbar:'#tb'
		
		/*
		toolbar:[{
			text:'导出',
			iconCls:'icon-save',
			handler:function(){			
			  var name =  $('#name').val();
			  var tel= $('#tel').val();				    
			  var url = "T_UserExport_Servlet?name="+encodeURI(encodeURI(name))+"&tel="+encodeURI(encodeURI(tel));
		      window.location = url;
		}
	},{
		text:'下载',
		iconCls:'icon-save',
		handler:function(){						    
		  var url = "http://192.168.1.135:8080/JianGuo_Server/downLoadFile/User.xls";
	      window.location = url;
	}
	
}]*/

		
	});
	});

function mySearch(value,name){

	$('#dg').datagrid('load',{
		name: $('#name').val(),
		tel: $('#tel').val()
	});
	
	
}

</script>

	<div id="search"  id="tb" style="padding:5px;height:auto">
		<span>姓名:</span>
		<input id="name" name="name" style="line-height:26px;border:1px solid #ccc">
		<span>电话:</span>
		<input id="tel" name="tel" style="line-height:26px;border:1px solid #ccc">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch()">Search</a>	
	</div>
	<table id="dg" ></table>

<!-- 兼职信息 window--><%--

	<form id="ff" method="post">
<div id="w" class="easyui-window" title="兼职查看" closed="true" data-options="iconCls:'icon-save'" style="width:1100px;height:550px;padding:10px;">		
			<h2>总计</h2>
			工资总额：
			<input  type="text" id="wage" name="wage"   readonly="readonly" value="${wage}" style="height:30px;border:none;"></input>
			
			<input class="easyui-textbox" type="text" name="wage" data-options="required:true"></input>
			
			
			提现总额：<input class="easyui-textbox" type="text" name="cash" data-options="required:true"></input>
			钱包余额(可修改)：<input class="easyui-textbox" type="text" name="money" data-options="required:true"></input><br/>
			修改备注:<textarea id="remark" name="remark" rows="5" cols="48"></textarea>
			&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit"   value="修改" id="update" onclick="return check()"/>
	
	
		<table id="job" class="easyui-datagrid" stytle="weight:300px;"></table>
	
</div>
</form>


--%><script type="text/javascript">
/*	function openJobWin(){
			var row = $('#dg').datagrid('getSelected');	
			if (row){		
				$('#w').window('open');						
				var loginId=row.login_id;
				$('#ff').form('load','T_updateMoney_Servlet?loginId='+loginId);


				
				$('#job').datagrid({ 
				
					url:'T_JobForUser_Servlet?loginId='+loginId, 				
					columns:[[ 				
						{field:'job_id',title:'Id',width:10,hidden:true}, 
						{field:'name',title:'兼职名称',width:150},					
						{field:'address',title:'地址',width:100},
						{field:'status1',title:'状态',width:100},
						{field:'money',title:'金额',width:150},
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
				toolbar:[{
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
				]
					
				});
				
			}else{
				$.messager.alert('提示','请先选中数据!','error');
				}
		}*/

		function openJobWin (){
			var row = $('#dg').datagrid('getSelected');
			if (row){				
			  var loginId=row.login_id;
			  var url="T_updateMoney_Servlet?loginId="+loginId;
			  window.open(url,"用户钱包信息","width=850, height=670");
			}else{
				$.messager.alert('提示','请先选中数据!','error');
				}
			
		}
	function openUserWin (){
		var row = $('#dg').datagrid('getSelected');
		if (row){				
		  var loginId=row.login_id;
		  var money=row.money;
		  var school=row.school;
		  var cityId=row.city_id;
		  var url="T_userDetail_Servlet?loginId="+loginId+"&money="+money+"&school="+encodeURI(encodeURI(school))+"&cityId="+encodeURI(encodeURI(cityId));
		  window.open(url,"用户详细信息","width=780, height=670");
		}else{
			$.messager.alert('提示','请先选中数据!','error');
			}
		
	}
</script>

  </body>
  
  
</html>
