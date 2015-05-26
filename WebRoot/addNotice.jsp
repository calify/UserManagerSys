<%@ page language="java" import="java.util.*,com.calify.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addNotice.jsp' starting page</title>
    
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
  
    <form action=ManageNotice?op=add method=post>
    	标题：<input type=text  maxlength=10 name=title /><br/>
   		 内容：<input type=text maxlength=50 name=content /><br/>
   		 图片：<input type=text name=pic value="待开发"><br/>
   <input type=submit value="提交"/>
    </form> <br>
  
  
  </body>
</html>
