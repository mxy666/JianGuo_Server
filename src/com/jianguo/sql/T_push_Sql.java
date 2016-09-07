package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_push_Bean;
import com.jianguo.util.DButil;

public class T_push_Sql {
	
	public static int insert(String login_id,String job_name,String title,String content,String type,
			String jpush,String html_url,String job_id,String time){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_push(login_id,job_name,title,content,type,jpush,html_url,job_id,time) values(?,?,?,?,?,?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, job_name);
		    	pst.setString(3, title);
		    	pst.setString(4, content);
		    	pst.setString(5, type);
		    	pst.setString(6, jpush);
		    	pst.setString(7, html_url);
		    	pst.setString(8, job_id);
		    	pst.setString(9, time);
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

	public static List<T_push_Bean> select_login_id(String login_id,String count){
		List<T_push_Bean> list=new ArrayList<T_push_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_push where login_id=? and jpush=0 order by id desc limit "+count+",50";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_push_Bean t_push = new T_push_Bean();
				t_push.setId(rs.getInt("id"));
				t_push.setLogin_id(rs.getInt("login_id"));
				t_push.setJob_name(rs.getString("job_name")+"");
				t_push.setTitle(rs.getString("title")+"");
				t_push.setContent(rs.getString("content")+"");
				t_push.setType(rs.getInt("type"));
				t_push.setJpush(rs.getInt("jpush"));
				t_push.setHtml_url(rs.getString("html_url")+"");
				t_push.setJob_id(rs.getInt("job_id"));
				t_push.setTime(rs.getString("time")+"");
				list.add(t_push);
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
	//ÍÆËÍ¼ÇÂ¼
	public static int insertPushRecord(int type,String message,int sum,String time){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_platform_push(type,message,sum,reg_time) values(?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setInt(1, type);		    	
		    	pst.setString(2, message);
		    	pst.setInt(3, sum);
		    	pst.setString(4, time);

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
}
