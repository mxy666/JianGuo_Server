package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.util.DButil;

public class MerchantSql {
	public static List<T_enroll_Bean> queryUser(String job_id,String status){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer(); 
		String sql = "select * from t_enroll where job_id="+job_id+" and state=0 ";
		if(status.equals("0")){
			 str.append(" and status in (0)");
		}else if(status.equals("1")){
			str.append(" and status in (3,5,8)");
		}else if(status.equals("2")){
			str.append(" and status in (2,4,6,7,13)");
		}
		
		 sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);	
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				//t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				//t_enroll.setStatus(rs.getInt("status"));
				//t_enroll.setLogin_time(rs.getString("login_time")+"");
				//t_enroll.setState(rs.getInt("state"));
				list.add(t_enroll);
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
	//根据性别差人
	public static List<T_enroll_Bean>  queryByID(String login_id,String sex){
		ResultSet rs=null;
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		Connection conn=DButil.getCon();
		String sql = "select login_id from t_user_resume where login_id in ('"+login_id+") and sex="+sex;
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean resume = new T_enroll_Bean();
				
				resume.setLogin_id(rs.getInt("login_id"));
				list.add(resume);
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
