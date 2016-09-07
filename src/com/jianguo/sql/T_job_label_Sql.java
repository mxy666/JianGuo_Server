package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianguo.bean.T_admin_Bean;
import com.jianguo.bean.T_job_label_Bean;
import com.jianguo.util.DButil;

public class T_job_label_Sql {

	public static int insert(String job_id,String limit,String welfare,String label){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_job_label(job_id,limits,welfare,label) values(?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, job_id);
		    	pst.setString(2, limit);
		    	pst.setString(3, welfare);
		    	pst.setString(4, label);
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
	
	public static int update_welfare(String welfare,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job_label set welfare=? where job_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, welfare);
			psmt.setString(2, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_label(String label,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job_label set label=? where job_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, label);
			psmt.setString(2, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static T_job_label_Bean select_job_id(String job_id){
		ResultSet rs=null;
		T_job_label_Bean t_job_label = new T_job_label_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_job_label where job_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_job_label.setId(rs.getInt("id"));
				t_job_label.setJob_id(rs.getInt("job_id"));
				t_job_label.setLimits(rs.getString("limits")+"");
				t_job_label.setWelfare(rs.getString("welfare")+"");
				t_job_label.setLabel(rs.getString("label")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_job_label;
	}
	public static int updateLable(String limits,String welfare,String label,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job_label set limits=?,welfare=?,label=? where job_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, limits);
			psmt.setString(2, welfare);
			psmt.setString(3, label);
			psmt.setString(4, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
}
