package com.jianguo.merchant.mersql;

import com.jianguo.bean.T_city_Bean;
import com.jianguo.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginSql {
	private static Connection conn=DButil.getCon();
	static boolean b = true;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	//判断手机号是否已经注册过
	public static boolean checkRegister(String tel){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
		try {
			String sql = "select * from t_merchant_login where tel=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static boolean InsertMerchant(String tel){
		try {
			String sql = "insert into t_merchant_login (tel,password,token,permission,resume_status,review_status,pay_status) values" +
					"('"+tel+"','111111','','','','','')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public  int update_status(String status,String id){
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
