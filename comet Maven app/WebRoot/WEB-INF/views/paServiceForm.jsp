<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的form表单</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/getService/savePaFuwu" method="post" enctype="multipart/form-data">
			<input type="text" name="serviceName"/>
			<input type="file" name="picFile" id="one"/>
			<input type="file" name="picFile" id="two"/>
			<input type="submit" value="提交"></input>
</form>
</body>
</html>