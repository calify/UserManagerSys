//连接数据库

package com.calify.model;

import java.sql.*;

public class Conn {
	
	private Connection ct = null;
	private String DBURL="jdbc:mysql://127.0.0.1:3306/test1";				//数据库地址
	private String DBUSER="root";			//数据库用户名
	private String DBPWD="root";			//数据库密码
	
	public Connection getConn(){
		
		try{
			
			//加载驱动
			Class.forName("org.gjt.mm.mysql.Driver");
			
			//连接数据库
			ct = DriverManager.getConnection(DBURL,DBUSER,DBPWD);
			
		}catch(Exception e){
		
			e.printStackTrace();
		
		}

		return ct;
	
	};

}
