//这是一个操作类，用于操作公告模块
//查询、添加、删除、修改
package com.calify.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.calify.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageNotice extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//解决中文乱码
		request.setCharacterEncoding("UTF-8");
		
		//接收操作类型
		String opp = request.getParameter("op");
		
		//查询单页
		if(opp.equals("query")){
			
			int pagenow = Integer.parseInt((String)request.getParameter("pagenow"));
			int pageSize = 5;		//每页显示的数目
			int pageCount;			//总页数
			
			//初始化
			ArrayList al = new ArrayList();
			Operation op = new Operation();
			
		  	op.setRowCount();
		    op.setPageCount();
		    pageCount = op.getPageCount();
			al = op.getNoticePage(pagenow);
			
			//操作模型
			
			 request.setAttribute("result", al);
			 request.setAttribute("operation", op);
			 request.setAttribute("pagecount", new Integer(pageCount));
			 
			 request.getRequestDispatcher("Notice.jsp?pagenow=" + pagenow).forward(request, response);
			
		}
		
		//添加
		else if(opp.equals("add")){
			
			String t = request.getParameter("title");
			String c = request.getParameter("content");
			
			new Operation().addNotice(t,c);
			response.sendRedirect("ManageNotice?op=query&pagenow=1");
	
		}
		
		//删除
		else if(opp.equals("del")){
			
			int id = Integer.parseInt((String)request.getParameter("id"));
			
			new Operation().delNotice(id);
			
			response.sendRedirect("ManageNotice?op=query&pagenow=1");
			
		}

		//修改
		else if(opp.equals("change")){
			
			String t = request.getParameter("title");
			String c = request.getParameter("content");
			int id = Integer.parseInt(request.getParameter("id"));

			new Operation().changeNotice(t,c,id);
			response.sendRedirect("ManageNotice?op=query&pagenow=1");
						
		}
		
		
		
		
		
		
		
		
			 	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doGet(request, response);
		
	}

}
