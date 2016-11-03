package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_userChat_Bean;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.util.DButil;

public class T_UserChat_Sql {

	//����LoginID��ѯ������¼
	public static T_userChat_Bean queryByLoginID(String login_id){
		ResultSet rs=null;
		T_userChat_Bean user = new T_userChat_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_info where login_id=? ";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				user.setUserId(rs.getInt("login_id")+"");
				if(rs.getString("name")==null||rs.getString("name").equals("")){
					user.setName(rs.getString("nickname"));
				}else{
					user.setName(rs.getString("name"));
				}
				user.setAvatarUrl(rs.getString("name_image"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return user;
	}
	//loginID������ѯ
	 public static List<T_userChat_Bean> queryUserInfoByGroup(String loginId) throws SQLException {
		 Connection conn=DButil.getCon();
		 List <T_userChat_Bean>list=new ArrayList<T_userChat_Bean>();
		 ResultSet rs=null;
		 String	 sql="select * from t_user_info where login_id in ('"+loginId+")";
		// sql=sql+str.toString();
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		 rs=psmt.executeQuery(sql);
				while(rs.next()){
					T_userChat_Bean user = new T_userChat_Bean();
					user.setUserId(rs.getInt("login_id")+"");
					if(rs.getString("name")==null||rs.getString("name").equals("")){
						user.setName(rs.getString("nickname"));
					}else{
						user.setName(rs.getString("name"));
					}
					user.setAvatarUrl(rs.getString("name_image"));
					list.add(user);
				}
				psmt.close();
				conn.close();
				DButil.close(conn);
		return list;
	}
	//loginID������ѯ
	public static List<T_userChat_Bean> queryMerInfoByGroup(String loginId) throws SQLException {
		Connection conn=DButil.getCon();
		List <T_userChat_Bean>list=new ArrayList<T_userChat_Bean>();
		ResultSet rs=null;
		ResultSet rs2=null;
		String	 sql="select * from t_user_info where login_id in ('"+loginId+")";
		String sql2="select * from t_merchant where login_id in ('"+loginId+")";
		// sql=sql+str.toString();
		System.out.println(sql2+"----------------------------------------");
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		PreparedStatement psmt2 = DButil.getPstm(conn, sql2);
		rs=psmt.executeQuery(sql);
		rs2=psmt2.executeQuery(sql2);
		while(rs.next()){
			T_userChat_Bean user = new T_userChat_Bean();
			user.setUserId(rs.getInt("login_id")+"");
			if(rs.getString("name")==null||rs.getString("name").equals("")){
				user.setName(rs.getString("nickname"));
			}else{
				user.setName(rs.getString("name"));
			}
			user.setAvatarUrl(rs.getString("name_image"));
			list.add(user);
		}
		while (rs2.next()){
			T_userChat_Bean user = new T_userChat_Bean();
			user.setUserId(rs2.getInt("login_id")+"");
			if(rs2.getString("name")==null||rs2.getString("name").equals("")){
				user.setName(rs2.getString("contactName"));
			}else{
				user.setName(rs2.getString("name"));
			}
			user.setAvatarUrl(rs2.getString("name_image"));
			list.add(user);
		}
		psmt.close();
		psmt2.close();
		conn.close();
		DButil.close(conn);
		return list;
	}
}
