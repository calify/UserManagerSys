//这是一个操作类
//用于检验登录信息

package com.calify.control;

import java.io.IOException;
import java.io.PrintWriter;
import com.calify.model.*;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginChkServlet extends HttpServlet {

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
		
			//取得登录信息
			String u = request.getParameter("username");
			String p = request.getParameter("password");
			
			Operation op = new Operation();
			
			if(op.checkUser(u ,p)){
				
				//新建session
				HttpSession hs = request.getSession();
				hs.setAttribute("pass", u);
				
				//转发
				request.getRequestDispatcher("Wel.jsp").forward(request, response);
			
			}
			
			else{
			
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			
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
