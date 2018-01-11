<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
  </head>
  
  <body>
    <form action="login/loginByPhone.do" method="post">
			<input type="text" name="phone" id="phone"/>
			<input type="text" name="code" id="code"/><span id="getCode">获取验证码</span>
			<input type="submit"></input>
	</form>
	
	
<div style="font-size: 40px;text-align: center;">
	<a href="login/loginByWX.do">微信公众号授权登陆</a>
</div>
  </body>
  <script type="text/javascript">
  	$("#getCode").click(function(){
  		var phone = $("#phone").val();
  		 $.post("login/getPhoneCode.do",
		  {
		    phone:phone
		  },
		  function(data,status){
		    alert("Data: " + data + "\nStatus: " + status);
		  });
  	})
  </script>
</html>
