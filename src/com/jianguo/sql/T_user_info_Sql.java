package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.util.DButil;

public class T_user_info_Sql {

	//QQ-微信注册获取的信息查到数据库
	public static int insert_qq_wx(String login_id,String nickname,String name,String name_image,String school,
			String realname,String credit,String integral,String regedit_time,String login_time){
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_user_info(login_id,nickname,name,name_image,school,realname,credit,integral,regedit_time,login_time) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {
			pst.setString(1, login_id);
			pst.setString(2, nickname);
			pst.setString(3, name);
			pst.setString(4, name_image);
			pst.setString(5, school);
			pst.setString(6, realname);
			pst.setString(7, credit);
			pst.setString(8, integral);
			pst.setString(9, regedit_time);
			pst.setString(10, login_time);
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
	
//	//QQ-微信注册获取的信息查到数据库
//	public static int insert_qq_wx(String login_id,String nickname,String name_image,String regedit_time,String login_time){
//		int num=0;
//		Connection conn=DButil.getCon();
//		String sql="insert into t_user_info(login_id,nickname,name_image,regedit_time,login_time) values(?,?,?,?,?)";
//		PreparedStatement pst=DButil.getPstm(conn, sql);
//		try {
//			pst.setString(1, login_id);
//			pst.setString(2, nickname);
//			pst.setString(3, name_image);
//			pst.setString(4, regedit_time);
//			pst.setString(5, login_time);
//			num=pst.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		finally{
//			DButil.close(conn);
//			DButil.psClose(pst);
//		}
//		return num;
//	}
	
	public static T_user_info_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_user_info_Bean t_user_info = new T_user_info_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_info where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_info.setId(rs.getInt("id"));
				t_user_info.setLogin_id(rs.getInt("login_id"));
				t_user_info.setNickname(rs.getString("nickname")+"");
				t_user_info.setName(rs.getString("name")+"");
				t_user_info.setName_image(rs.getString("name_image")+"");
				t_user_info.setSchool(rs.getString("school")+"");
				t_user_info.setRealname(rs.getInt("realname"));
				t_user_info.setCredit(rs.getInt("credit"));
				t_user_info.setIntegral(rs.getInt("integral"));
				t_user_info.setRegedit_time(rs.getString("regedit_time")+"");
				t_user_info.setLogin_time(rs.getString("login_time")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_info;
	}
	
	public static int update(String name,String name_image,String school,
			String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_info set name=?,name_image=?,school=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, name);
			psmt.setString(2, name_image);
			psmt.setString(3, school);
			psmt.setString(4, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_nickname(String nickname,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_info set nickname=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, nickname);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_school(String school,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_info set school=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, school);
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
