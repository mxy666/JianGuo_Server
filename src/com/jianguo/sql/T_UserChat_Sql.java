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

	//根据LoginID查询单条记录
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
	//loginID批量查询
	 public static List<T_userChat_Bean> queryByGroup(String loginId){
		 Connection conn=DButil.getCon();
		// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 List <T_userChat_Bean>list=new ArrayList<T_userChat_Bean>();
		 ResultSet rs=null;
		 StringBuffer str = new StringBuffer(); 
	     String sql="select * from t_user_info where login_id in ('"+loginId+")";		 		
		// sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
			try {
				rs=psmt.executeQuery();
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DButil.close(conn);
			}
		return list;
	}
	
}
