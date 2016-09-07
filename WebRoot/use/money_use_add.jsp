<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>

	
	
<script type="text/javascript">
	
	function check(){
			var admin = document.getElementById("admin").value;
			var work_date = document.getElementById("work_date").value;
			var merchant = document.getElementById("merchant").value;
			var name = document.getElementById("name").value;
			var tel = document.getElementById("tel").value;
			var hould_money = document.getElementById("hould_money").value;
			var moneyout = document.getElementById("moneyout").value;
			var moneyout_date = document.getElementById("moneyout_date").value;
						
			if(admin = null || admin.length==0){
				//document.getElementById("admin1").innerHTML="<font color='red'>业务经手人不能为空！！！</font>";
				alert("业务经手人不能为空！！！");
				return false;
			}
			
			if(work_date = null || work_date.length==0){
				//document.getElementById("work_date1").innerHTML="<font color='red'>工作日期不能为空！！！</font>";
				alert("工作日期不能为空！！！");
				return false;
			}
			
			if(merchant = null || merchant.length==0){
				//document.getElementById("merchant1").innerHTML="<font color='red'>商家不能为空！！！</font>";
				alert("商家不能为空！！！");
				return false;
			}
			
			if(name = null || name.length==0){
				//document.getElementById("name1").innerHTML="<font color='red'>姓名不能为空！！！</font>";
				alert("姓名不能为空！！！");
				return false;
			}
			
			if(tel = null || tel.length==0){
				//document.getElementById("tel1").innerHTML="<font color='red'>电话号码不能为空！！！</font>";
				alert("电话号码不能为空！！！");
				return false;
			}
			
			if(hould_money = null || hould_money.length==0){
				//document.getElementById("hould_money1").innerHTML="<font color='red'>应付工资不能为空！！！</font>";
				alert("应付工资不能为空！！！");
				return false;
			}
						
			if(moneyout = null || moneyout.length==0){
				//document.getElementById("moneyout1").innerHTML="<font color='red'>提现金额不能为空！！！</font>";
				alert("提现金额不能为空！！！");
				return false;
			}
			if(moneyout_date = null || moneyout_date.length==0){
				//document.getElementById("moneyout_date1").innerHTML="<font color='red'>提现日期不能为空！！！</font>";
				alert("提现日期不能为空！！！");
				return false;
			}
			
		
		}
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
	</script>
  </head>
 
</form> 
  <body>
  <center>
 <h2>用户结算新增</h2>
   <form id="myForm" method="post" action="T_money_use_AddAction_Servlet?pageNo=1">
		<div class=content>
			<input id="id" name="id" type="hidden" value=""/>
			<span class="lable">业务经手人:</span><input id="admin" name="admin" type="text"  value="" /><span id="admin1"></span><br>
			<span class="lable">工作日期:</span><input id="work_date" name="work_date" type="text" class="input" value=""/><span id="work_date1"></span><br>
			<span class="lable">商家:</span><input id="merchant" name="merchant" type="text" class="input"  value=""/><span id="merchant1"></span><br>  
			<span class="lable">姓名:</span><input id="name" name="name" type="text" class="input"  value=""/><span id="name1"></span><br>
			<span class="lable">电话号码:</span><input id="tel" name="tel" type="text" class="input" value=""/><span id="tel1"></span><br> 
			<span class="lable">应付工资:</span><input id="hould_money" name="hould_money" class="input"  type="text" value=""onkeyup="checkNum(this)"/><span id="hould_money1"></span><br>
			<span class="lable">提现金额:</span><input id="moneyout" name="moneyout" type="text" class="input" value=""onkeyup="checkNum(this)"/><span id="moneyout1"></span><br>
			<span class="lable">提现日期:</span><input id="moneyout_date" name="moneyout_date" class="input"  type="text" value=""/><span id="moneyout_date1"></span><br>
			<span class="lable">备注:</span><input id="remarks" name="remarks" type="text" class="input"  value=""/><br> 
			
		<br/>
			<input type="submit" class="button" value="新增" id="add" onclick="return check()"/>
		</div>
	</center>
  </body>
</html>
