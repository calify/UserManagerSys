<%@ page language="java" import="java.util.*,com.calify.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManageNotice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
	function del(){
		return window.confirm("确定删除吗？");
	}
	</script>
	
  </head>
  
  <body>
    
    <%
        	//判断是否为合法用户
          Object o = session.getAttribute("pass");
          
          if(!(new Operation().chkLogin(o))){
          
          response.sendRedirect("Login.jsp");
          
          }
     %>
        
   
   <%
   
	  ArrayList al = (ArrayList) request.getAttribute("result");
	  Operation op = (Operation) request.getAttribute("operation");
	  int pageCount= (Integer)(request.getAttribute("pagecount"));   
   	  int pageNow = Integer.parseInt((String)request.getParameter("pagenow"));
   	  int pageSize = 5;		//每页显示5条记录

   %>
   
   
 <table border="1">
   <tr>
    <td>id</td>
   	<td>标题</td>
   	<td>内容</td>
   	<td>日期</td>
   	<td>操作</td>
   </tr>
   
   <% 
   for(int i = 0; i < al.size(); i++){
   
   	NoticeBean nb = (NoticeBean)al.get(i);
   	  
   	out.println("<tr>");
   	out.println("<td>");
   	out.println(nb.getNoticeId());
   	out.println("</td>");
   	out.println("<td>");
   	out.println(nb.getTitle());
   	out.println("</td>");
   	out.println("<td>");
   	out.println(nb.getContent());
   	out.println("</td>");
   	out.println("<td>");
   	out.println(nb.getDate());
   	out.println("</td>");
   	out.println("<td>");
   	out.println("<a href=\"Change.jsp?id=" + nb.getNoticeId() + "\">修改</a><br>");
   	out.println("<a onclick=\"return del();\" href=\"ManageNotice?op=del&id=" + nb.getNoticeId() + "\">删除</a><br>");
   	out.println("</td>");
   	out.println("</tr>");
    
   }
   
   %>
   
   </table><br/>
  
   <% if(pageNow != 1 ){
      
 	out.println("<a href=\"ManageNotice?op=query&pagenow=" + (pageNow-1) + "\">上一页</a>");
    
    }

   %>
   
   <%
   for(int j = pageNow+1; j<pageNow+5 && j<=pageCount; j++)
   out.println("<a href=\"ManageNotice?op=query&pagenow=" + j + "\">" + j + "</a>");
   
   %>
   
    
   <% if(pageNow != pageCount ){
      
 	out.println("<a href=\"ManageNotice?op=query&pagenow=" + (pageNow+1) + " \">下一页</a>");
    
    }
    
    %>
    <br/><a href="addNotice.jsp">添加公告</a><br/>
    <a href="Wel.jsp">返回 首页</a>
  </body>
</html>
