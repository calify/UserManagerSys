<%@ page language="java" import="java.util.*,com.calify.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  <%
    	//判断是否为合法用户
      Object o = session.getAttribute("pass");
      
      if(!(new Operation().chkLogin(o))){
      
      response.sendRedirect("Login.jsp");
      
      }
    %>
  
  <h1>Welcome!</h1>
  
  <%
  
  out.println(o);
  
   %>
  
   <br/>
   
   <a href="ManageNotice?op=query&pagenow=1">管理公告</a><br/>
   
   <a href="Login.jsp">退出登录</a><br/>
  
  </body>

</html>
