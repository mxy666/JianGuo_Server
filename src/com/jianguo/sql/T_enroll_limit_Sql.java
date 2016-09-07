package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jianguo.bean.T_admin_Bean;
import com.jianguo.bean.T_enroll_limit_Bean;
import com.jianguo.util.DButil;

public class T_enroll_limit_Sql {
	
	//判断该手机号是否已注册
	public static boolean check(String login_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_enroll_limit where login_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static int insert(String login_id,String count,String date){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_enroll_limit(login_id,count,date) values(?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, count);
		    	pst.setString(3, date);
				num=pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			finally{
				DButil.close(conn);
				DButil.psClose(pst);
			}
			return num;
	}
	
	public static T_enroll_limit_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_enroll_limit_Bean t_enroll_limit = new T_enroll_limit_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll_limit where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_enroll_limit.setId(rs.getInt("id"));
				t_enroll_limit.setLogin_id(rs.getInt("login_id"));
				t_enroll_limit.setCount(rs.getInt("count"));
				t_enroll_limit.setDate(rs.getInt("date"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_enroll_limit;
	}
	
	public static int update_count(String date,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll_limit set count=count+1,date=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, date);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_count0(String date,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll_limit set count=1,date=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, date);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
