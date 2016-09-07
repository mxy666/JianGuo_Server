package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_attent_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.util.DButil;

public class T_user_realname_Sql {
	
	//判断该手机号是否已注册
	public static boolean check_login_id(String login_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_realname where login_id=?";
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

	//实名认证录入
	public static int insert(String login_id,String front_image,String behind_image,String realname,
			String id_number,String sex){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_user_realname(login_id,front_image,behind_image,realname,id_number,sex) values(?,?,?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, front_image);
		    	pst.setString(3, behind_image);
		    	pst.setString(4, realname);
		    	pst.setString(5, id_number);
		    	pst.setString(6, sex);
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
	
	//根据token查询数据
	public static T_user_realname_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_user_realname_Bean t_user_realname = new T_user_realname_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_realname where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_realname.setId(rs.getInt("id"));
				t_user_realname.setLogin_id(rs.getInt("login_id"));
				t_user_realname.setFront_image(rs.getString("front_image")+"");
				t_user_realname.setBehind_image(rs.getString("behind_image")+"");
				t_user_realname.setRealname(rs.getString("realname")+"");
				t_user_realname.setId_number(rs.getString("id_number")+"");
				t_user_realname.setSex(rs.getInt("sex"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_realname;
	}
	
	public static List<T_user_realname_Bean> select(String login_id){
		List<T_user_realname_Bean> list=new ArrayList<T_user_realname_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_user_realname where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_user_realname_Bean t_user_realname = new T_user_realname_Bean();
				t_user_realname.setId(rs.getInt("id"));
				t_user_realname.setLogin_id(rs.getInt("login_id"));
				t_user_realname.setFront_image(rs.getString("front_image")+"");
				t_user_realname.setBehind_image(rs.getString("behind_image")+"");
				t_user_realname.setRealname(rs.getString("realname")+"");
				t_user_realname.setId_number(rs.getString("id_number")+"");
				t_user_realname.setSex(rs.getInt("sex"));
				list.add(t_user_realname);
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
	
	public static int update(String front_image,String behind_image,String realname,String id_number,String sex,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
//			login_id,front_image,behind_image,realname,sex
			String sql = "update t_user_realname set front_image=?,behind_image=?,realname=?,id_number=?,sex=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, front_image);
			psmt.setString(2, behind_image);
			psmt.setString(3, realname);
			psmt.setString(4, id_number);
			psmt.setString(5, sex);
			psmt.setString(6, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
