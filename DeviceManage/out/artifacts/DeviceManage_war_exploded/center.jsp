<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <base href="<%=basePath%>">
    
    <title>center</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="<%=basePath%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=basePath%>/assets/css/bootstrap-responsive.css" rel="stylesheet">
  </head>
  
  <body>
    but hd has seen it all before<br>
  </body>
</html>
