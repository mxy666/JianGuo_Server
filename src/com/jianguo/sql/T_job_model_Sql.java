package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_job_model_Bean;
import com.jianguo.util.DButil;

public class T_job_model_Sql {
	
	public static boolean check(String merchant_id,String job_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_job_model where merchant_id=? and job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, merchant_id);
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

	public static int insert(String model_name,String merchant_id,String job_id){
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_job_model(model_name,merchant_id,job_id) values(?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {
			pst.setString(1, model_name);
			pst.setString(2, merchant_id);
			pst.setString(3, job_id);
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
	
	public static List<T_job_model_Bean> select_All_name(String merchant_id,String count){
		List<T_job_model_Bean> list=new ArrayList<T_job_model_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_job_model where merchant_id=? order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,merchant_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_model_Bean t_job_model = new T_job_model_Bean();
				t_job_model.setId(rs.getInt("id"));
				t_job_model.setModel_name(rs.getString("model_name")+"");
				t_job_model.setMerchant_id(rs.getInt("merchant_id"));
				t_job_model.setJob_id(rs.getInt("job_id"));
				list.add(t_job_model);
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return list;
	}
	
	public static int delete(String merchant_id,String job_id){
		int num=0;
		PreparedStatement pstmt = null;
		Connection conn=DButil.getCon();
		try {
			String sql = "delete from t_job_model where merchant_id=? and job_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, merchant_id);
			pstmt.setString(2, job_id);
			num = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
