<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <base href="<%=basePath%>">
    
    <title>xx管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="<%=basePath%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=basePath%>/css/bootstrap-responsive.css" rel="stylesheet">
 <link href="<%=basePath%>/css/docs.css" rel="stylesheet">
 <link href="<%=basePath%>/js/google-code-prettify/prettify.css" rel="stylesheet">
  </head>

  <body>
   <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Welcome to </a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              欢迎： <a href="#" class="navbar-link">${admin.adminName}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <a href="<%=basePath%>/hao/blog/exit" class="navbar-link">退出</a>
            </p>
            <ul class="nav">
              <!-- li class="active"><a href="index">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li-->
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">信息管理</li>
              <li class="active" id="introduce"><a href="#" target="center" >首页</a></li>
              <li id="article"><a href="#" target="center" >工作填报</a></li>
              <li id="article"><a href="#" target="center" >消息管理</a></li>
              
              <li id="webheader"><a href="#" target="center" >提醒管理</a></li>
              <li id="webfooter"><a href="<%=basePath%>/ShowReport.wx?PAGEID=CallBoardManagePage" target="center" >公告管理</a></li>
              <li id="tagManage"><a href="#" target="center" >汇总统计</a></li>
             
              <li class="nav-header">系统管理</li>
              <li><a href="<%=basePath%>/ShowReport.wx?PAGEID=adminPage" target="center">管理员管理</a></li>
             <li><a href="<%=basePath%>/ShowReport.wx?PAGEID=unitPage" target="center">部门管理</a></li>
             <li><a href="<%=basePath%>/ShowReport.wx?PAGEID=loginLogPage" target="center">登录日志</a></li>
             
              <li class="nav-header">账号管理</li>
              <li><a href="<%=basePath%>/ShowReport.wx?PAGEID=updatePwdPage" target="center">修改密码</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->

  <div class="span9">
        <iframe name="center" width="100%" height="600px" frameborder=no style="padding-top:10px;" src="<%=basePath%>/ShowReport.wx?PAGEID=CallBoardShow"></iframe>
         
         
        </div><!--/span-->

        </div><!--/span-->
      </div><!--/row-->

      <hr>
    <footer>
        ${FOOTER}
    </footer>

  	
  	
  	
  	<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=basePath%>/js/jquery.js"></script>
    <script src="<%=basePath%>/js/bootstrap-transition.js"></script>
    <script src="<%=basePath%>/js/bootstrap-alert.js"></script>
    <script src="<%=basePath%>/js/bootstrap-modal.js"></script>
    <script src="<%=basePath%>/js/bootstrap-dropdown.js"></script>
    <script src="<%=basePath%>/js/bootstrap-scrollspy.js"></script>
    <script src="<%=basePath%>/js/bootstrap-tab.js"></script>
    <script src="<%=basePath%>/js/bootstrap-tooltip.js"></script>
    <script src="<%=basePath%>/js/bootstrap-popover.js"></script>
    <script src="<%=basePath%>/js/bootstrap-button.js"></script>
    <script src="<%=basePath%>/js/bootstrap-collapse.js"></script>
    <script src="<%=basePath%>/js/bootstrap-carousel.js"></script>
    <script src="<%=basePath%>/js/bootstrap-typeahead.js"></script>
    <script src="<%=basePath%>/js/bootstrap-affix.js"></script>
 
    <script src="<%=basePath%>/js/holder/holder.js"></script>
    <script src="<%=basePath%>/js/google-code-prettify/prettify.js"></script>
 
    <script src="<%=basePath%>/js/application.js"></script>
  </body>
</html>
