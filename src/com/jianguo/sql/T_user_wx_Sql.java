package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_wx_Bean;
import com.jianguo.util.DButil;

public class T_user_wx_Sql {

	public static boolean check(String login_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_wx where login_id=?";
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
	
	public static int insert(String login_id,String openid,String nickname,String sex,String province,String city,
			String country,String headimgurl,String privilege,String unionid){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_user_wx(login_id,openid,nickname,sex,province,city,country,headimgurl,privilege,unionid) values(?,?,?,?,?,?,?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, openid);
		    	pst.setString(3, nickname);
		    	pst.setString(4, sex);
		    	pst.setString(5, province);
		    	pst.setString(6, city);
		    	pst.setString(7, country);
		    	pst.setString(8, headimgurl);
		    	pst.setString(9, privilege);
		    	pst.setString(10, unionid);
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
	public static T_user_wx_Bean select_nickname(String login_id){
		ResultSet rs=null;
		T_user_wx_Bean t_user_wx = new T_user_wx_Bean();
		Connection conn=DButil.getCon();
		String sql = "select id,nickname from t_user_wx where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_wx.setId(rs.getInt("id"));
				t_user_wx.setNickname(rs.getString("nickname")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_wx;
	}
	
}
