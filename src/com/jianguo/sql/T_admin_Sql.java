package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_admin_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.util.DButil;

public class T_admin_Sql {
	
	//判断该手机号是否已注册
	public static boolean check(String city,String username,String password){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_admin where city=? and username=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static T_admin_Bean select_status(String username){
		ResultSet rs=null;
		T_admin_Bean t_admin = new T_admin_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_admin where username=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,username);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_admin.setId(rs.getInt("id"));
				t_admin.setCity(rs.getString("city")+"");
				t_admin.setUsername(rs.getString("username")+"");
				t_admin.setPassword(rs.getString("password")+"");
				t_admin.setStatus(rs.getInt("status"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_admin;
	}
	
	public static int update_status(String status,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_admin set status=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	public static int queryCityId(String city){
		if(city.equals("sanya")){
			city="三亚";
		}else if(city.equals("beijing")){
			city="北京";
		}else if(city.equals("haikou")){
			city="海口";
		}else if(city.equals("hangzhou")){
			city="杭州";
		}else if(city.equals("xian")){
			city="西安";
		}
		
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		
		String sql = "select * from t_city where city=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		List IdList = new ArrayList();
		 T_city_Bean cityID =new T_city_Bean();      
		try {
			
			psmt.setString(1,city);
			rs=psmt.executeQuery();
			
			  while(rs.next()){             
				 
				  cityID.setId(rs.getInt("id"));              				           
				              }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return cityID.getId();
	}
	

}
