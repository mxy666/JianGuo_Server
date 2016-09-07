package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.util.DButil;

public class PigeonSql {
	
	public static boolean queryByID(String login_id,String job_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_pigeon where login_id=? and job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
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
	
	//向鸽子表中插入一条数据
	public static int addPigeon(String login_id,String job_id,String merID){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_user_pigeon(login_id,job_id,merchand_id) values(?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, job_id);
		    	pst.setString(3, merID);		    	
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
	
	//用户鸽子标记更新
	public static int update_UserPigeon(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_login set pigeon_count=pigeon_count+1  where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	//标记鸽子后返回数据

	public static T_user_info_Bean selectByLogin_id(String login_id){
		ResultSet rs=null;
		T_user_info_Bean t_user_info = new T_user_info_Bean();
		Connection conn=DButil.getCon();
		String sql = "select a.*,b.*,c.sex,c.intoschool_date from t_user_info a, t_user_login b,t_user_resume c where a.login_id=b.id and b.id=c.login_id and c.login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_info.setLogin_id(rs.getInt("login_id"));
				t_user_info.setNickname(rs.getString("nickname")+"");
				t_user_info.setName(rs.getString("name")+"");
				t_user_info.setSex_resume(rs.getInt("sex"));
				t_user_info.setName_image(rs.getString("name_image")+"");
				t_user_info.setSchool(rs.getString("school")+"");
				t_user_info.setIntoschool_date_resume(rs.getString("intoschool_date")+"");				
				t_user_info.setRealname(rs.getInt("realname"));//是否实名
				t_user_info.setTel(rs.getString("tel"));
				t_user_info.setPigeon_count(rs.getInt("pigeon_count"));
				t_user_info.setCredit(rs.getInt("credit"));
				//t_user_info.setIntegral(rs.getInt("integral"));积分
				t_user_info.setRegedit_time(rs.getString("regedit_time")+"");
				t_user_info.setLogin_time(rs.getString("login_time")+"");
		/*		
				list_t_user_info":[" +
						"{"id":80,"login_id":71,"
						nickname":"兼果","name":"哈哈","
						name_image":"http://v3.jianguojob.com/moren.png",
							"school":"ff","realname":0,"credit":0,
							"integral":0,"regedit_time":"2016-07-11 14:56:32",
							"login_time":"2016-07-11 14:56:32",
							"complete_job":1,"cancel_job":0,
							"time_job":"2016-05-14 11:33:18",
							"user_status":0,"remarks_job":"",
							"sex_resume":0,"intoschool_date_resume":"","tel":"18301535973"}]},"code":"200"}*/

				
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
}
