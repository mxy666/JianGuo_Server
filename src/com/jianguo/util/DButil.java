package com.jianguo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DButil {
	//生成连接的方法
	public static Connection getCon(){

		Properties pro = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		try {
			InputStream is = DButil.class.getClassLoader()
					.getResourceAsStream("DB.properties");
			// System.out.println(is.toString());
			pro.load(is);
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
//			 System.out.println(driver + ":" + url + ":" + username + ":"
//			 + password);
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username,
					password);
			return conn;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//生成preparedstatement的方法
	public static PreparedStatement getPstm(Connection conn, String sql)
	{    PreparedStatement pstm=null;
	  try {
		pstm=conn.prepareStatement(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	     
		return pstm;
	}
	//关闭连接
	public static void close(Connection conn)
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭preparedstatement
	public static void psClose(PreparedStatement pstm){
		if (pstm == null)
		return;
	try {
		pstm.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public static Statement getSta(Connection conn)
	{   Statement sta=null;
	try {
		sta = conn.createStatement();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return sta;
	}
	//关闭statement
	public static void staClose(Statement sta)
	{if (sta == null)
		return;
	try {
		sta.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
