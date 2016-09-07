package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_job_model_Bean;
import com.jianguo.bean.T_job_wai_Bean;
import com.jianguo.util.DButil;

public class T_job_wai_Sql {

	public static int insert(String job_id,String tel,String name,String sex,String school){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_job_wai(job_id,tel,name,sex,school) values(?,?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, job_id);
		    	pst.setString(2, tel);
		    	pst.setString(3, name);
		    	pst.setString(4, sex);
		    	pst.setString(5, school);
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
	
	public static List<T_job_wai_Bean> select_job_id(String job_id){
		List<T_job_wai_Bean> list=new ArrayList<T_job_wai_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_job_wai where job_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_wai_Bean t_job_wai = new T_job_wai_Bean();
				t_job_wai.setId(rs.getInt("id"));
				t_job_wai.setJob_id(rs.getInt("job_id"));
				t_job_wai.setTel(rs.getString("tel")+"");
				t_job_wai.setName(rs.getString("name")+"");
				t_job_wai.setSex(rs.getInt("sex"));
				t_job_wai.setSchool(rs.getString("school")+"");
				list.add(t_job_wai);
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
	
	public static int delete_id(String id){
		int num=0;
		PreparedStatement pstmt = null;
		Connection conn=DButil.getCon();
		try {
			String sql = "delete from t_job_wai where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			num = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
