package com.jianguo.sql;

import com.jianguo.bean.Area_Bean;
import com.jianguo.bean.T_area_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.util.DButil;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Job_Sql {
	public static List<Area_Bean> selectAllArea(String city_id) throws SQLException {
		List<Area_Bean> list=new ArrayList<Area_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_area where city_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1,city_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				Area_Bean area = new Area_Bean();
				area.setId(rs.getString("id"));
				area.setCity_id(rs.getString("city_id"));
				area.setArea_name(rs.getString("area_name")+"");
				list.add(area);
			}
			psmt.close();
			conn.close();
			DButil.close(conn);
		return list;
	}

	//兼职信息录入
	public static int insert(String city_id,String area_id,String type_id,String merchant_id,String name,String name_image,String start_date,String stop_date,
			String address,String mode,String money,String term,String limit_sex,String count,String sum,String regedit_time,
			String status,String hot,String alike,String reg_date,String look,String is_model,String user_count,String start_time,
			String max,String girl_count,String girl_sum,String girl_user) throws SQLException {
		   int num=0;
		   if(girl_count==null||girl_count.equals("")){
			   girl_count="0";
			   
		   }
		   if(girl_sum==null||girl_sum.equals("")){
			   girl_sum="0";
			   
		   }
		   if(girl_user==null||girl_user.equals("")){
			   girl_user="0";
			   
		   }
		   Connection conn=DButil.getCon();
		   String sql="insert into t_job(city_id,area_id,type_id,merchant_id,name,name_image,start_date," +
		   "stop_date,address,mode,money,term,limit_sex,count,sum,regedit_time,status,hot," +
		   	"alike,reg_date,look,is_model,user_count,start_time,max,girl_count,girl_sum,girl_user) values(?,?,?,?,?,?,?," +
		   	"?,?,?,?,?,?,?," +
		   	"?,?,?,?,?,?,?," +
		   	"?,?,?,?,?,?,?)";
		PreparedStatement pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pst.setString(1, city_id);
		    	pst.setString(2, area_id);
		    	pst.setString(3, type_id);
		    	pst.setString(4, merchant_id);
		    	pst.setString(5, name);
		    	pst.setString(6, name_image);
		    	pst.setString(7, start_date);
		    	pst.setString(8, stop_date);
		    	pst.setString(9, address);
		    	pst.setString(10, mode);
		    	pst.setString(11, money);
		    	pst.setString(12, term);
		    	pst.setString(13, limit_sex);
		    	pst.setString(14, count);
		    	pst.setString(15, sum);
		    	pst.setString(16, regedit_time);
		    	pst.setString(17, status);
		    	pst.setString(18, hot);
		    	pst.setString(19, alike);
		    	pst.setString(20, reg_date);
		    	pst.setString(21, look);
		    	pst.setString(22, is_model);
		    	pst.setString(23, user_count);
		    	pst.setString(24, start_time);
		    	pst.setString(25, max);
		    	pst.setString(26, girl_count);
		    	pst.setString(27, girl_sum);
		    	pst.setString(28, girl_user);
				pst.executeUpdate();
		ResultSet rs= pst.getGeneratedKeys();
		rs.next();
		 num=rs.getInt(1);
				DButil.close(conn);
				DButil.psClose(pst);
			return num;
	}
	//商家信息录入
	public static int insertJobInfo(String job_id,String tel,String address,String lon,String lat,String start_date,String stop_date,
							 String start_time,String stop_time,String set_place,String set_time,String limit_sex,String term,String other,String work_content,
							 String work_require) throws SQLException {
		int num=0;
		Logger logger = Logger.getLogger("log");
		logger.info("日志信息开始!");
		Connection conn=DButil.getCon();
		String sql="insert into t_job_info(job_id,tel,address,lon,lat,start_date,stop_date,start_time,stop_time,set_place,set_time,limit_sex,term,other,work_content,work_require) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
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
			DButil.close(conn);
			DButil.psClose(pst);
		return num;
	}
	//根据jobid查找job对象
	public static T_job_Bean getJob(String jobid) throws SQLException {
		ResultSet rs=null;
		T_job_Bean t_job = new T_job_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_job where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1,jobid);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_job.setId(rs.getInt("id"));
				t_job.setCity_id(rs.getString("city_id"));
				t_job.setArea_id(rs.getInt("area_id"));
				t_job.setType_id(rs.getInt("type_id"));
				t_job.setMerchant_id(rs.getInt("merchant_id"));
				t_job.setName(rs.getString("name")+"");
				t_job.setName_image(rs.getString("name_image")+"");
				t_job.setStart_date(rs.getString("start_date")+"");
				t_job.setStop_date(rs.getString("stop_date")+"");
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMode(rs.getInt("mode"));
				t_job.setMoney(rs.getDouble("money"));
				t_job.setTerm(rs.getInt("term"));
				t_job.setLimit_sex(rs.getInt("limit_sex"));
				t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
				t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
				t_job.setRegedit_time(rs.getString("regedit_time")+"");
				t_job.setStatus(rs.getInt("status"));
				t_job.setHot(rs.getInt("hot"));
				t_job.setAlike(rs.getString("alike")+"");
				t_job.setReg_date(rs.getString("reg_date")+"");
				t_job.setLook(rs.getInt("look"));
				t_job.setIs_model(rs.getInt("is_model"));
				t_job.setUser_count(rs.getInt("user_count"));
				t_job.setMax(rs.getInt("max"));
				t_job.setGirl_count(rs.getInt("girl_count"));
				t_job.setGirl_sum(rs.getInt("girl_sum"));
				t_job.setGirl_user(rs.getInt("girl_user"));
				t_job.setBoySum(rs.getInt("sum"));//男的录取总数
			}
			psmt.close();
			conn.close();
			DButil.close(conn);
		return t_job;
	}
	
	public static T_job_Bean select_merchant_id_aa(String merchant_id){
		ResultSet rs=null;
		T_job_Bean t_job = new T_job_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_job where merchant_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,merchant_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_job.setId(rs.getInt("id"));
				t_job.setCity_id(rs.getString("city_id"));
				t_job.setArea_id(rs.getInt("area_id"));
				t_job.setType_id(rs.getInt("type_id"));
				t_job.setMerchant_id(rs.getInt("merchant_id"));
				t_job.setName(rs.getString("name")+"");
				t_job.setName_image(rs.getString("name_image")+"");
				t_job.setStart_date(rs.getString("start_date")+"");
				t_job.setStop_date(rs.getString("stop_date")+"");
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMode(rs.getInt("mode"));
				t_job.setMoney(rs.getDouble("money"));
				t_job.setTerm(rs.getInt("term"));
				t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
				t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
				t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
				t_job.setRegedit_time(rs.getString("regedit_time")+"");
				t_job.setStatus(rs.getInt("status"));
				t_job.setHot(rs.getInt("hot"));
				t_job.setAlike(rs.getString("alike")+"");
				t_job.setReg_date(rs.getString("reg_date")+"");
				t_job.setLook(rs.getInt("look"));
				t_job.setIs_model(rs.getInt("is_model"));
				t_job.setUser_count(rs.getInt("user_count"));
				t_job.setMax(rs.getInt("max"));
				t_job.setGirl_count(rs.getInt("girl_count"));
				t_job.setGirl_sum(rs.getInt("girl_sum"));
				t_job.setGirl_user(rs.getInt("girl_user"));
				t_job.setBoySum(rs.getInt("sum"));//男的录取总数
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_job;
	}
	
	public static T_job_Bean select_id(String id){
		ResultSet rs=null;
		T_job_Bean t_job = new T_job_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_job where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_job.setId(rs.getInt("id"));
				t_job.setCity_id(rs.getString("city_id"));
				t_job.setArea_id(rs.getInt("area_id"));
				t_job.setType_id(rs.getInt("type_id"));
				t_job.setMerchant_id(rs.getInt("merchant_id"));
				t_job.setName(rs.getString("name")+"");
				t_job.setName_image(rs.getString("name_image")+"");
				t_job.setStart_date(rs.getString("start_date")+"");
				t_job.setStop_date(rs.getString("stop_date")+"");
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMode(rs.getInt("mode"));
				t_job.setMoney(rs.getDouble("money"));
				t_job.setTerm(rs.getInt("term"));
				t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
				t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
				t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
				
				t_job.setRegedit_time(rs.getString("regedit_time")+"");
				t_job.setStatus(rs.getInt("status"));
				t_job.setHot(rs.getInt("hot"));
				t_job.setAlike(rs.getString("alike")+"");
				t_job.setReg_date(rs.getString("reg_date")+"");
				t_job.setLook(rs.getInt("look"));
				t_job.setIs_model(rs.getInt("is_model"));
				t_job.setUser_count(rs.getInt("user_count"));
				t_job.setMax(rs.getInt("max"));
				t_job.setGirl_count(rs.getInt("girl_count"));
				t_job.setGirl_sum(rs.getInt("girl_sum"));
				t_job.setGirl_user(rs.getInt("girl_user"));
				t_job.setBoySum(rs.getInt("sum"));//男的录取总数
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_job;
	}
	

	//(city_id,"0","1","2","3","0");
	public static List<T_job_Bean> select_all(String city_id,String status0,String status1,String status2,String status3,String count){
		List<T_job_Bean> list=new ArrayList<T_job_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_job where city_id=? and (status=? or status=? or status=? or status=?) and is_model=0 order by id desc limit "+count+",80";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,city_id);
			psmt.setString(2,status0);
			psmt.setString(3,status1);
			psmt.setString(4,status2);
			psmt.setString(5,status3);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_Bean t_job = new T_job_Bean();
				t_job.setId(rs.getInt("id"));
				t_job.setCity_id(rs.getString("city_id"));
				t_job.setArea_id(rs.getInt("area_id"));
				t_job.setType_id(rs.getInt("type_id"));
				t_job.setMerchant_id(rs.getInt("merchant_id"));
				t_job.setName(rs.getString("name")+"");
				t_job.setName_image(rs.getString("name_image")+"");
				t_job.setStart_date(rs.getString("start_date")+"");
				t_job.setStop_date(rs.getString("stop_date")+"");
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMode(rs.getInt("mode"));
				t_job.setMoney(rs.getDouble("money"));
				t_job.setTerm(rs.getInt("term"));
				t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
				t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
				t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
				t_job.setRegedit_time(rs.getString("regedit_time")+"");
				t_job.setStatus(rs.getInt("status"));
				t_job.setHot(rs.getInt("hot"));
				t_job.setAlike(rs.getString("alike")+"");
				t_job.setReg_date(rs.getString("reg_date")+"");
				t_job.setLook(rs.getInt("look"));
				t_job.setIs_model(rs.getInt("is_model"));
				t_job.setUser_count(rs.getInt("user_count"));
				t_job.setMax(rs.getInt("max"));
				t_job.setGirl_count(rs.getInt("girl_count"));
				t_job.setGirl_sum(rs.getInt("girl_sum"));
				t_job.setGirl_user(rs.getInt("girl_user"));
				t_job.setBoySum(rs.getInt("sum"));//男的录取总数
				list.add(t_job);
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
	
	public static List<T_job_Bean> select_all_ok(String city_id){
		List<T_job_Bean> list=new ArrayList<T_job_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		String sql = "select * from t_job where city_id=? and is_model=0 order by id desc";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,city_id);
//			psmt.setString(2,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_Bean t_job = new T_job_Bean();
				t_job.setId(rs.getInt("id"));
				t_job.setCity_id(rs.getString("city_id"));
				t_job.setArea_id(rs.getInt("area_id"));
				t_job.setType_id(rs.getInt("type_id"));
				t_job.setMerchant_id(rs.getInt("merchant_id"));
				t_job.setName(rs.getString("name")+"");
				t_job.setName_image(rs.getString("name_image")+"");
				t_job.setStart_date(rs.getString("start_date")+"");
				t_job.setStop_date(rs.getString("stop_date")+"");
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMode(rs.getInt("mode"));
				t_job.setMoney(rs.getDouble("money"));
				t_job.setTerm(rs.getInt("term"));
				/*t_job.setLimit_sex(rs.getInt("limit_sex"));
				t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
				t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
				t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
				t_job.setRegedit_time(rs.getString("regedit_time")+"");
				t_job.setStatus(rs.getInt("status"));
				t_job.setHot(rs.getInt("hot"));
				t_job.setAlike(rs.getString("alike")+"");
				t_job.setReg_date(rs.getString("reg_date")+"");
				t_job.setLook(rs.getInt("look"));
				t_job.setIs_model(rs.getInt("is_model"));
				t_job.setUser_count(rs.getInt("user_count"));
				t_job.setMax(rs.getInt("max"));
				t_job.setGirl_count(rs.getInt("girl_count"));
				t_job.setGirl_sum(rs.getInt("girl_sum"));
				t_job.setGirl_user(rs.getInt("girl_user"));
				t_job.setBoySum(rs.getInt("sum"));//男的录取总数
				list.add(t_job);
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
	
	public static List<T_job_Bean> select_hot(String hot,String city_id,String date,String count){
		List<T_job_Bean> list=new ArrayList<T_job_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
//		String sql = "select * from t_job where hot=? and city_id=? and is_model=0 and (status=0 or status=2) order by status asc,id desc limit "+count+",10";
		String sql = "select * from t_job where hot=? and city_id=? and is_model=0 order by status asc,id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,hot);
			psmt.setString(2,city_id);
//			psmt.setString(3,date);
//			psmt.setString(4,date2);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_Bean t_job = new T_job_Bean();
				t_job.setId(rs.getInt("id"));
				t_job.setCity_id(rs.getString("city_id"));
				t_job.setArea_id(rs.getInt("area_id"));
				t_job.setType_id(rs.getInt("type_id"));
				t_job.setMerchant_id(rs.getInt("merchant_id"));
				t_job.setName(rs.getString("name")+"");
				t_job.setName_image(rs.getString("name_image")+"");
				t_job.setStart_date(rs.getString("start_date")+"");
				t_job.setStop_date(rs.getString("stop_date")+"");
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMode(rs.getInt("mode"));
				t_job.setMoney(rs.getDouble("money"));
				t_job.setTerm(rs.getInt("term"));
				t_job.setLimit_sex(rs.getInt("limit_sex"));
			/*	t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
				t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
				t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
				t_job.setRegedit_time(rs.getString("regedit_time")+"");
				t_job.setStatus(rs.getInt("status"));
				t_job.setHot(rs.getInt("hot"));
				t_job.setAlike(rs.getString("alike")+"");
				t_job.setReg_date(rs.getString("reg_date")+"");
				t_job.setLook(rs.getInt("look"));
				t_job.setIs_model(rs.getInt("is_model"));
				t_job.setUser_count(rs.getInt("user_count"));
				t_job.setMax(rs.getInt("max"));
				t_job.setGirl_count(rs.getInt("girl_count"));
				t_job.setGirl_sum(rs.getInt("girl_sum"));
				t_job.setGirl_user(rs.getInt("girl_user"));
				t_job.setBoySum(rs.getInt("sum"));//男的录取总数
				list.add(t_job);
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
	


	


	


	
	public static int update_count(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set count=count+1 where id=?";
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
	//商家录取后，更新count(男女各限，女的)
	public static int update_GirlCountss( String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set girl_count=girl_count+1 where id=?";
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
	
	//商家录取后，更新count(不限，单限男女)
	public static int update_countss( String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set count=count+1  where id=?";
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
	
	//录取后用户取消更新
	public static int update_Girlcount_jian(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set girl_count=girl_count-1 where id=?";
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
	
	
	
	public static int update_count_jian(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set count=count-1 where id=?";
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
	
	public static int update_status(String status,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set status=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_user_count(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set user_count=user_count+1 where id=?";
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
	
	public static int update_girl_user(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set girl_user=girl_user+1 where id=?";
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
	
	public static int update_user_count_jian(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set user_count=user_count-1 where id=?";
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
	
	public static int update_girl_user_jian(String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_job set girl_user=girl_user-1 where id=?";
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
	public static T_city_Bean selectTcity_id(String id){
		ResultSet rs=null;
		T_city_Bean t_city = new T_city_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_city where id=? or code=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			if (id.equals("898")){
				id="0898";
			}else if(id.equals("899")){
				id="0899";
			}else if(id.equals("571")){
				id="0571";
			}else if(id.equals("10")){
				id="010";
			}else if(id.equals("27")){
				id="027";
			}else if(id.equals("29")){
				id="029";
			}
			psmt.setString(1,id);
			psmt.setString(2,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				//t_city.setId(rs.getInt("id"));
				t_city.setId(rs.getString("code")+"");
				t_city.setCity(rs.getString("city")+"");
				t_city.setCode(rs.getString("code")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_city;
	}

	public static List<T_job_Bean> selectHot(String hot,String city_id,String cityID2,String date,String count){
		List<T_job_Bean> list=new ArrayList<T_job_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
//		String mersql = "select * from t_job where hot=? and city_id=? and is_model=0 and (status=0 or status=2) order by status asc,id desc limit "+count+",10";
		String sql = "select * from t_job where hot=? and (city_id=? or city_id=? ) and is_model=0 order by status asc,id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,hot);
			psmt.setString(2,city_id);
			psmt.setString(3,cityID2);
//			psmt.setString(3,date);
//			psmt.setString(4,date2);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_Bean t_job = new T_job_Bean();
				t_job.setId(rs.getInt("id"));
				t_job.setCity_id(rs.getString("city_id"));
				t_job.setArea_id(rs.getInt("area_id"));
				t_job.setType_id(rs.getInt("type_id"));
				t_job.setMerchant_id(rs.getInt("merchant_id"));
				t_job.setName(rs.getString("name")+"");
				t_job.setName_image(rs.getString("name_image")+"");
				t_job.setStart_date(rs.getString("start_date")+"");
				t_job.setStop_date(rs.getString("stop_date")+"");
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMode(rs.getInt("mode"));
				t_job.setMoney(rs.getDouble("money"));
				t_job.setTerm(rs.getInt("term"));
				t_job.setLimit_sex(rs.getInt("limit_sex"));
			/*	t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
				t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
				t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
				t_job.setRegedit_time(rs.getString("regedit_time")+"");
				t_job.setStatus(rs.getInt("status"));
				t_job.setHot(rs.getInt("hot"));
				t_job.setAlike(rs.getString("alike")+"");
				t_job.setReg_date(rs.getString("reg_date")+"");
				t_job.setLook(rs.getInt("look"));
				t_job.setIs_model(rs.getInt("is_model"));
				t_job.setUser_count(rs.getInt("user_count"));
				t_job.setMax(rs.getInt("max"));
				t_job.setGirl_count(rs.getInt("girl_count"));
				t_job.setGirl_sum(rs.getInt("girl_sum"));
				t_job.setGirl_user(rs.getInt("girl_user"));
				t_job.setBoySum(rs.getInt("sum"));//男的录取总数
				list.add(t_job);
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
	public static List<T_job_Bean> 	queryAll(String name){
		 Connection conn=DButil.getCon();
		 List <T_job_Bean>list=new ArrayList<T_job_Bean>();
		 ResultSet rs=null;
		 StringBuffer str = new StringBuffer(); 
	     String sql="select * from t_job where 1=1";		 
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 if(name!=null&&!name.equals("")){
			 str.append(" and name like'%"+name+"%' ");			 
		 }		
		 sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		 
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_Bean t_job = new T_job_Bean();
				t_job.setId(rs.getInt("id"));
				t_job.setName(rs.getString("name")+"");				
				t_job.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+""+"100"))));
				t_job.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+""+"100"))));
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMoney(rs.getDouble("money"));

				list.add(t_job);
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
