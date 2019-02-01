<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎登录${SystemName}系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="<%=basePath%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=basePath%>/assets/css/bootstrap-responsive.css" rel="stylesheet">
     <script src="<%=basePath%>/js/jquery.js"></script>
    <style type="text/css">
      body {
        padding-top: 160px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }
 
      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 1px 1px 5px rgba(0,0,0,.05);
           -moz-box-shadow: 1px 1px 5px rgba(0,0,0,.05);
                box-shadow: 1px 1px 5px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
 
    </style>
    
    
  </head>
  
  <body>
    <div class="container">
 
      <form method="post" class="form-signin" action="<%=basePath%>/ga/login/in">
        <h2 class="form-signin-heading">欢迎登录${SystemName}系统</h2>
        <input type="text" name="loginName" id="loginName" class="input-block-level" placeholder="用户名">
        <input type="password" name="loginPwd" id="loginPwd" class="input-block-level" placeholder="密码">
       
        <button class="btn btn-large btn-primary" type="submit">登录</button>
      </form>
 
    </div> <!-- /container -->
    
  </body>
</html>
