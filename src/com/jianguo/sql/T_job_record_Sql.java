package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jianguo.bean.T_job_record_Bean;
import com.jianguo.util.DButil;

public class T_job_record_Sql {
	
	public static boolean check_login_id(String login_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_job_record where login_id=?";
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

	public static int insert(String login_id,String complete,String cancel){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_job_record(login_id,complete,cancel) values(?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, complete);
		    	pst.setString(3, cancel);
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
	
	public static T_job_record_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_job_record_Bean t_job_record = new T_job_record_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_job_record where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_job_record.setId(rs.getInt("id"));
				t_job_record.setLogin_id(rs.getInt("login_id"));
				t_job_record.setComplete(rs.getInt("complete"));
				t_job_record.setCancel(rs.getInt("cancel"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_job_record;
	}
	
	public static int update_complete(String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job_record set complete=complete+1 where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public static int update_cancel(String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job_record set cancel=cancel+1 where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
