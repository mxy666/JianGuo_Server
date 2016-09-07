package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianguo.util.DButil;

public class T_job_look_Sql {

	//判断该手机号是否已注册
	public static boolean check(String login_id,String job_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_job_look where login_id=? and job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			pstmt.setString(2, job_id);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static int insert(String login_id,String job_id,String reg_time){
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_job_look(login_id,job_id,reg_time) values(?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {
			pst.setString(1, login_id);
			pst.setString(2, job_id);
			pst.setString(3, reg_time);
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
	
}
