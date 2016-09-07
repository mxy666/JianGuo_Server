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
    
    <title>用户详细信息</title>
    
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
		
		var sex = document.getElementById("sex").value;
		var money = document.getElementById("money").value;
		var school = document.getElementById("school").value;
		var cityId = document.getElementById("cityId").value;
			
		if(sex = null || sex.length==0){
			alert("性别不能为空并且只能填男或女！！！");
			return false;
		}
		//if(sex != "男"||sex!="女"){
			//alert("性别只能填男或女！！！");
			//return false;
			//}	
		
		if(school = null || school.length==0){
			alert("学校不能为空！！！");
			return false;
		}
		
		if(cityId = null || cityId.length==0){
			alert("区域不能为空！！！");
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
	<form action="T_updateUser_Servlet" method="post">
	<div id="user" title="Window Layout"  data-options="iconCls:'icon-save'" style="width:780px;height:540px;padding:5px;align:center;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'east',split:true" style="width:300px">
	
				<table cellpadding="5">
	    		<tr>
	    			<td><h3>真实姓名:</h3></td>
	    			<td style="display:none;"><input  type="hidden" name="login_id" readonly="readonly"  value="${login_id}" style="height:40px;"></input></td>
	    			<td ><input  type="text" name="realname" readonly="readonly"  value="${realname}" style="height:40px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td><h3>性别(可修改):</h3></td>
	    			<c:if test="${sex=='1'}"> 
						<td><input  type="text" id="sex" name="sex"  value="男"style="height:40px;"></input></td>						
					</c:if>	
					<c:if test="${sex=='0'}"> 
						<td><input  type="text" id="sex" name="sex"  value="女"style="height:40px;"></input></td>						
	    			</c:if>
	    		
	    		</tr>
	    		<tr>
	    			<td><h3>身份证号:</h3></td>
	    			<td><input type="text" name="id_number" readonly="readonly"  value="${id_number}"style="height:40px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td><h3>电话:</h3></td>
	    			<td><input  type="text" name="tel" readonly="readonly" value="${tel}"style="height:40px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td><h3>金额:</h3></td>
	    			<td><input type="text" id="money"name="money" readonly="readonly" value="${money}"style="height:40px;" ></input></td>
	    		</tr>
	    		<tr>
	    			<td><h3>学校(可修改):</h3></td>
	    			<td><input type="text" id="school" name="school"  value="${school}"style="height:40px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td><h3>区域(可修改):</h3></td>
	    			<td><input  type="text" id="cityId" name="cityId"  value="${cityId}"style="height:40px;"></input></td>
	    		</tr>
	    		<tr>
	    		</tr><tr>
	    		</tr>
	    		<tr style="align:center">	    			
	    			<td><input type="submit"   value="修改" id="update" onclick="return check()"/></td>
	    			<td><input type="button"  value="取消" id="close" onclick="Close()"/></td>
	    		</tr>
	    	</table>
	
			</div>
			<div data-options="region:'center'" style="padding:10px;">
				<table cellpadding="5">
		    		<tr>
		    			<td>正面:</td>
		    			<td><img height="250dip" width="400dip" id="" alt=""src="${front_image }"></td>
		    		</tr>
		    		<tr>
		    			<td>反面:</td>
		    			<td><img height="250dip" width="400dip" id="" alt="" src="${behind_image }"></td>
		    		</tr>
		    		
		    	</table>
			</div>
			
		</div>
	</div>
   </from>
<script type="text/javascript">
		
</script>

  </body>
  
  
</html>
