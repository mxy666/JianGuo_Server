package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.util.DButil;
import org.apache.log4j.Logger;

public class T_job_info_Sql {
	
	//商家信息录入
	public static int insert(String job_id,String tel,String address,String lon,String lat,String start_date,String stop_date,
			String start_time,String stop_time,String set_place,String set_time,String limit_sex,String term,String other,String work_content,
			String work_require){
		   int num=0;
			Logger logger = Logger.getLogger("log");
			logger.info("日志信息开始!");
		   Connection conn=DButil.getCon();
		   String sql="insert into t_job_info(job_id,tel,address,lon,lat,start_date,stop_date,start_time,stop_time,set_place,set_time,limit_sex,term,other,work_content,work_require) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, job_id);
		    	pst.setString(2, tel);
		    	pst.setString(3, address);
		    	pst.setString(4, lon);
		    	pst.setString(5, lat);
		    	pst.setString(6, start_date);
		    	pst.setString(7, stop_date);
		    	pst.setString(8, start_time);
		    	pst.setString(9, stop_time);
		    	pst.setString(10, set_place);
		    	pst.setString(11, set_time);
		    	pst.setString(12, limit_sex);
		    	pst.setString(13, term);
		    	pst.setString(14, other);
		    	pst.setString(15, work_content);
		    	pst.setString(16, work_require);
				num=pst.executeUpdate();
			} catch (SQLException e) {
				logger.error("发布兼职job_info异常"+e.getMessage()==null?"空":e.getMessage());
				e.printStackTrace();
			}	
			finally{
				DButil.close(conn);
				DButil.psClose(pst);
			}
			return num;
	}
	
	//根据兼职ID兼职详情信息
	public static T_job_info_Bean select_job_id(String job_id){
		ResultSet rs=null;
		T_job_info_Bean t_job_info = new T_job_info_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_job_info where job_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_job_info.setId(rs.getInt("id"));
				t_job_info.setJob_id(rs.getInt("job_id"));
				t_job_info.setTel(rs.getString("tel")+"");
				t_job_info.setAddress(rs.getString("address")+"");
				t_job_info.setLon(rs.getDouble("lon"));
				t_job_info.setLat(rs.getDouble("lat"));
				t_job_info.setStart_date(rs.getString("start_date")+"");
				t_job_info.setStop_date(rs.getString("stop_date")+"");
				t_job_info.setStart_time(rs.getString("start_time")+"");
				t_job_info.setStop_time(rs.getString("stop_time")+"");
				t_job_info.setSet_place(rs.getString("set_place")+"");
				t_job_info.setSet_time(rs.getString("set_time")+"");
				t_job_info.setLimit_sex(rs.getInt("limit_sex"));
				t_job_info.setTerm(rs.getInt("term"));
				t_job_info.setOther(rs.getString("other")+"");
				t_job_info.setWork_content(rs.getString("work_content")+"");
				t_job_info.setWork_require(rs.getString("work_require")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_job_info;
	}
	
	public static List<T_job_info_Bean> select_all_status(){
		List<T_job_info_Bean> list=new ArrayList<T_job_info_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_job_info";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_info_Bean t_job_info = new T_job_info_Bean();
				t_job_info.setId(rs.getInt("id"));
				t_job_info.setJob_id(rs.getInt("job_id"));
				t_job_info.setTel(rs.getString("tel")+"");
				t_job_info.setAddress(rs.getString("address")+"");
				t_job_info.setLon(rs.getDouble("lon"));
				t_job_info.setLat(rs.getDouble("lat"));
				t_job_info.setStart_date(rs.getString("start_date")+"");
				t_job_info.setStop_date(rs.getString("stop_date")+"");
				t_job_info.setStart_time(rs.getString("start_time")+"");
				t_job_info.setStop_time(rs.getString("stop_time")+"");
				t_job_info.setSet_place(rs.getString("set_place")+"");
				t_job_info.setSet_time(rs.getString("set_time")+"");
				t_job_info.setLimit_sex(rs.getInt("limit_sex"));
				t_job_info.setTerm(rs.getInt("term"));
				t_job_info.setOther(rs.getString("other")+"");
				t_job_info.setWork_content(rs.getString("work_content")+"");
				t_job_info.setWork_require(rs.getString("work_require")+"");
				list.add(t_job_info);
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
	
	public static int update_all(String tel,String address,String lon,String lat,String start_date,String stop_date,
			String start_time,String stop_time,String set_place,String set_time,String limit_sex,String term,String other,String work_content,
			String work_require,String id) throws SQLException {
		int num=0;
			Connection conn=DButil.getCon();
			String sql = "update t_job_info set tel=?,address=?,lon=?,lat=?,start_date=?,stop_date=?,start_time=?,stop_time=?,set_place=?,set_time=?,limit_sex=?,term=?,other=?,work_content=?,work_require=? where job_id=?";
			PreparedStatement pst = DButil.getPstm(conn, sql);
	    	pst.setString(1, tel);
	    	pst.setString(2, address);
	    	pst.setString(3, lon);
	    	pst.setString(4, lat);
	    	pst.setString(5, start_date);
	    	pst.setString(6, stop_date);
	    	pst.setString(7, start_time);
	    	pst.setString(8, stop_time);
	    	pst.setString(9, set_place);
	    	pst.setString(10, set_time);
	    	pst.setString(11, limit_sex);
	    	pst.setString(12, term);
	    	pst.setString(13, other);
	    	pst.setString(14, work_content);
	    	pst.setString(15, work_require);
	    	pst.setString(16, id);
			num=pst.executeUpdate();
			pst.close();
			conn.close();
		return num;
	}
	
}
