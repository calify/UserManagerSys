//这是一个操作类，用来操作user表和notice表
//增、删、改、查

package com.calify.model;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Operation {
	
		private Connection cn = null;
		private Statement sm = null;
		private ResultSet rs = null;

		private int pageCount = 0; 	//总页数
		private int rowCount = 0;	//当前页数
		private int pageSize = 5;	//每页显示的数目
		
		//关闭资源
		public void close(){
			
			try{
				
				if(rs != null){
				rs.close();
				rs = null;
				}
				
				if(sm != null){	
					sm.close();
					sm = null;
				}
				
				if(cn != null){
					cn.close();
					cn = null;
					}
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
		}
		
		public int getPageCount() {
			return pageCount;
		}

		public int getRowCount() {
			return rowCount;
		}
		
		public int getPageSize(){
			
			return pageSize;
			
		}
		
		public void setPageCount() {
			
			if( this.getRowCount() % this.getPageSize() == 0){
		
			this.pageCount = this.getRowCount() / this.getPageSize();
			
			}
			
			else{
				
				this.pageCount = this.getRowCount() / this.getPageSize() + 1;
				
			}
		
		}
		
		public void setRowCount() {
			
			try{
				cn = new Conn().getConn();
				sm = cn.createStatement();				

				//查找记录数
				rs = sm.executeQuery("select count(*) from notice;");
				
				if(rs.next()){
					
					this.rowCount = rs.getInt(1);
					
				}
			}catch(Exception e){
				
				e.printStackTrace();
				
			}finally{
				
				this.close();
				
			}
			
		}
		
		//查询单页公告
		public ArrayList getNoticePage(int pageNow){
			
			ArrayList al = new ArrayList();
			
			try{
				
				cn = new Conn().getConn();
				sm = cn.createStatement();	
				
				//查询语句
				rs = sm.executeQuery("select noticeId,title,content,date from notice limit " + ((pageNow-1)*pageSize) + "," + pageSize + ";");
				
				while(rs.next()){
					
					NoticeBean nb = new NoticeBean();
					nb.setNoticeId(rs.getInt(1));
					nb.setTitle(rs.getString(2));
					nb.setContent(rs.getString(3));
					nb.setDate(rs.getDate(4));
					al.add(nb);
				
				}
				
			}catch(Exception e){
				
				e.printStackTrace();
				
				}finally{
					
					this.close();
					
					}
			
			return al;
		}
		
		//查询一条公告
		
		
		
		
		//删除公告
		public void delNotice(){
			//...待开发
		}
		
		//修改公告
		public void changeNotice(String title, String content, int id){
			
			try{
				
				cn = new Conn().getConn();
				sm = cn.createStatement();
				
				sm.executeUpdate("UPDATE NOTICE SET title = '" + title +"', content = '" + content + "' where noticeId = " + id  );
				
			}catch(Exception e){
			
				e.printStackTrace();
			
			}finally{
				
				close();
			
			}
			
			
		}
		
		//增加公告
		public void addNotice(String title, String content){
			
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String ds = sf.format(d);
			try{
				
				cn = new Conn().getConn();
				sm = cn.createStatement();
				
				sm.executeUpdate("insert into notice(title,content,date)values('" + title + "','" + content + "','" + ds + "')");
				
			}catch(Exception e){
			
				e.printStackTrace();
			
			}finally{
				
				close();
			
			}
			
		}	
		
		
		//验证是否为合法用户
		public boolean chkLogin(Object o){
			
			if(o == null){
				
				return false;
			
			}

			else{
				
				return true;
			}
			
		}
		
		//验证登录信息
		public boolean checkUser(String u ,String p){
			
			boolean b =false;
			
			try{
				
			cn = new Conn().getConn();
			sm = cn.createStatement();

			//查询语句
			rs = sm.executeQuery("select password from user where userName = '" + u + "';");
			
			if(rs.next()){
				
				if(rs.getString(1).equals(p)){					
					b = true;
					
				}
				
				else{
					
					b = false;
					
				}
				
			}
			
			else{
				
				b = false;
				
			}
			
			
			}catch(Exception e){
				
				e.printStackTrace();
				
			}finally{
				
				this.close();
				
			}
			
			return b;
		}
	
}
