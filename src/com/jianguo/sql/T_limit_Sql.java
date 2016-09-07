package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_label_Bean;
import com.jianguo.bean.T_limit_Bean;
import com.jianguo.bean.T_welfare_Bean;
import com.jianguo.util.DButil;

public class T_limit_Sql {

	public static List<T_limit_Bean> select_All_limit(){
		List<T_limit_Bean> list=new ArrayList<T_limit_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_limit";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_limit_Bean t_city = new T_limit_Bean();
				t_city.setId(rs.getInt("id"));
				t_city.setLimit_name(rs.getString("limit_name")+"");
				list.add(t_city);
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
	
	public static List<T_welfare_Bean> select_All_welfare(){
		List<T_welfare_Bean> list=new ArrayList<T_welfare_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_welfare";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_welfare_Bean t_welfare = new T_welfare_Bean();
				t_welfare.setId(rs.getInt("id"));
				t_welfare.setWelfare_name(rs.getString("welfare_name")+"");
				list.add(t_welfare);
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
	
	public static List<T_label_Bean> select_All_label(){
		List<T_label_Bean> list=new ArrayList<T_label_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_label";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_label_Bean t_label = new T_label_Bean();
				t_label.setId(rs.getInt("id"));
				t_label.setLabel_name(rs.getString("label_name")+"");
				list.add(t_label);
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
	
}
