package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_hobby_time_Bean;
import com.jianguo.bean.T_hobby_type_Bean;
import com.jianguo.util.DButil;

public class T_hobby_Sql {
	
	public static boolean check_type(String login_id,String type){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_hobby_type where login_id=? and type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			pstmt.setString(2, type);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static int insert_type(String login_id,String type){
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_hobby_type(login_id,type) values(?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {
			pst.setString(1, login_id);
			pst.setString(2, type);
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
	
	public static int insert_time(String login_id,String time){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_hobby_time(login_id,time) values(?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, time);
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
	
	public static List<T_hobby_type_Bean> select_type(String login_id){
		List<T_hobby_type_Bean> list=new ArrayList<T_hobby_type_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_hobby_type where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_hobby_type_Bean t_hobby_type = new T_hobby_type_Bean();
				t_hobby_type.setId(rs.getInt("id"));
				t_hobby_type.setLogin_id(rs.getInt("login_id"));
				t_hobby_type.setType(rs.getInt("type"));
				list.add(t_hobby_type);
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
	
	public static List<T_hobby_type_Bean> select_lll(String type){
		List<T_hobby_type_Bean> list=new ArrayList<T_hobby_type_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_hobby_type where type=0 or type=?";
//		String mersql = "select b.login_id FROM t_hobby_type b where b.login_id in(SELECT  a.login_id from t_hobby_time a where a.time='"+time+"') AND b.type in("+type+",0)";
		
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
//			psmt.setInt(1,time);
			psmt.setString(1,type);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_hobby_type_Bean t_hobby_type = new T_hobby_type_Bean();
				t_hobby_type.setId(rs.getInt("id"));
				t_hobby_type.setLogin_id(rs.getInt("login_id"));
				t_hobby_type.setType(rs.getInt("type"));
				list.add(t_hobby_type);
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
	
	public static List<T_hobby_time_Bean> select_time(String login_id){
		List<T_hobby_time_Bean> list=new ArrayList<T_hobby_time_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_hobby_time where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_hobby_time_Bean t_hobby_time = new T_hobby_time_Bean();
				t_hobby_time.setId(rs.getInt("id"));
				t_hobby_time.setLogin_id(rs.getInt("login_id"));
				t_hobby_time.setTime(rs.getInt("time"));
				list.add(t_hobby_time);
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
	
	public static T_hobby_time_Bean select_time_aa(String login_id){
		ResultSet rs=null;
		T_hobby_time_Bean t_hobby_time = new T_hobby_time_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_hobby_time where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_hobby_time.setId(rs.getInt("id"));
				t_hobby_time.setLogin_id(rs.getInt("login_id"));
				t_hobby_time.setTime(rs.getInt("time"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_hobby_time;
	}
	
	public static int delete_type(String login_id){
		int num=0;
		PreparedStatement pstmt = null;
		Connection conn=DButil.getCon();
		try {
			String sql = "delete from t_hobby_type where login_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			num = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int delete_time(String login_id){
		int num=0;
		PreparedStatement pstmt = null;
		Connection conn=DButil.getCon();
		try {
			String sql = "delete from t_hobby_time where login_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			num = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
