package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.util.DButil;

public class T_enroll_Sql {
	
	public static boolean check_login_id_job_id(String login_id,String job_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_enroll where login_id=? and job_id=?";
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
	
	public static boolean check_login_id_job_id2(String login_id,String job_id,String status){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_enroll where login_id=? and job_id=? and status=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			pstmt.setString(2, job_id);
			pstmt.setString(3, status);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static int insert(String login_id,String job_id,String status,String login_time,String state,String type){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_enroll(login_id,job_id,status,login_time,state,type) values(?,?,?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, job_id);
		    	pst.setString(3, status);
		    	pst.setString(4, login_time);
		    	pst.setString(5, state);
		    	pst.setString(6, type);
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
	
	public static T_enroll_Bean select_login_id_job_id(String login_id,String job_id){
		ResultSet rs=null;
		T_enroll_Bean t_enroll = new T_enroll_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where login_id=? and job_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			psmt.setString(2,job_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_enroll;
	}
	
	public static T_enroll_Bean select_login_id_job_time(String login_id,String job_id){
		ResultSet rs=null;
		T_enroll_Bean t_enroll = new T_enroll_Bean();
		Connection conn=DButil.getCon();
		String sql = "select status,login_time from t_enroll where login_id=? and job_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			psmt.setString(2,job_id);
			rs=psmt.executeQuery();
			while(rs.next()){
//				t_enroll.setId(rs.getInt("id"));
//				t_enroll.setLogin_id(rs.getInt("login_id"));
//				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
//				t_enroll.setState(rs.getInt("state"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_enroll;
	}
	
//	public static List<T_enroll_Bean> select_job_id(String job_id){
//		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
//		ResultSet rs=null;
//
//		Connection conn=DButil.getCon();
//		String sql = "select * from t_enroll where job_id=?";
//		PreparedStatement psmt = DButil.getPstm(conn, sql);
//		try {
//			psmt.setString(1,job_id);
//			rs=psmt.executeQuery();
//			while(rs.next()){
//				T_enroll_Bean t_enroll = new T_enroll_Bean();
//				t_enroll.setId(rs.getInt("id"));
//				t_enroll.setLogin_id(rs.getInt("login_id"));
//				t_enroll.setJob_id(rs.getInt("job_id"));
//				t_enroll.setStatus(rs.getInt("status"));
//				t_enroll.setLogin_time(rs.getString("login_time")+"");
//				list.add(t_enroll);
//			}
//			psmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			DButil.close(conn);
//		}
//		return list;
//	}
	
	public static List<T_enroll_Bean> select_job_id_status(String job_id,String status,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=? and status=? and state=0 order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_statusssss(String job_id,String status){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=? and status=? and state=1 order by id desc";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_s(String job_id){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=? order by id desc";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_statuss(String job_id,String status,String status2,String status3,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=? and (status=? or status=? or status=?) and state=0 order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,status);
			psmt.setString(3,status2);
			psmt.setString(4,status3);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status_IDs(String job_id,String status,String status2){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select id from t_enroll where job_id=? and (status=? or status=?) and state=0";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,status);
			psmt.setString(3,status2);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
//				t_enroll.setLogin_id(rs.getInt("login_id"));
//				t_enroll.setJob_id(rs.getInt("job_id"));
//				t_enroll.setStatus(rs.getInt("status"));
//				t_enroll.setLogin_time(rs.getString("login_time")+"");
//				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status_nv(String job_id,String job_id_nv,String status,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where (job_id=? or job_id=?) and status=? and state=0 order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,job_id_nv);
			psmt.setString(3,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status_nvs(String job_id,String job_id_nv,String status,String status2,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where (job_id=? or job_id=?) and (status=? or status=?) and state=0 order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,job_id_nv);
			psmt.setString(3,status);
			psmt.setString(4,status2);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status_count(String job_id,String job_id_nv,String status){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=?  and status=? and state=0";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			//psmt.setString(2,job_id_nv);
			psmt.setString(2,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	//Ω·À„
	public static List<T_enroll_Bean> select_job_id_status_countnew(String job_id,String status){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=?  and status=? and state=0";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			
			psmt.setString(2,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status_nv_IDs(String job_id,String job_id_nv,String status,String status2){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select id from t_enroll where (job_id=? or job_id=?) and (status=? or status=?) and state=0";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,job_id_nv);
			psmt.setString(3,status);
			psmt.setString(4,status2);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
//				t_enroll.setLogin_id(rs.getInt("login_id"));
//				t_enroll.setJob_id(rs.getInt("job_id"));
//				t_enroll.setStatus(rs.getInt("status"));
//				t_enroll.setLogin_time(rs.getString("login_time")+"");
//				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status2(String job_id,String status3,String status5,String status8,
			String status9,String status10,String status11,String status12,String status13,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=? and (status=? or status=? or status=? or status=? or status=? or status=? or status=? or status=?) order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,status3);
			psmt.setString(3,status5);
			psmt.setString(4,status8);
			psmt.setString(5,status9);
			psmt.setString(6,status10);
			psmt.setString(7,status11);
			psmt.setString(8,status12);
			psmt.setString(9,status13);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status2_all(String job_id,String status3,String status5,String status8,
			String status9,String status10,String status11,String status12,String status13){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select id from t_enroll where job_id=? and (status=? or status=? or status=? or status=? or status=? or status=? or status=? or status=?) order by id desc";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,status3);
			psmt.setString(3,status5);
			psmt.setString(4,status8);
			psmt.setString(5,status9);
			psmt.setString(6,status10);
			psmt.setString(7,status11);
			psmt.setString(8,status12);
			psmt.setString(9,status13);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
//				t_enroll.setLogin_id(rs.getInt("login_id"));
//				t_enroll.setJob_id(rs.getInt("job_id"));
//				t_enroll.setStatus(rs.getInt("status"));
//				t_enroll.setLogin_time(rs.getString("login_time")+"");
//				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_job_id_status3(String job_id,String status2,String status4,String status6,String status7,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where job_id=? and (status=? or status=? or status=? or status=?) order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,job_id);
			psmt.setString(2,status2);
			psmt.setString(3,status4);
			psmt.setString(4,status6);
			psmt.setString(5,status7);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_login_id_status1(String login_id,String status0,String status1,String status2,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where login_id=? and (status=? or status=? or status=?) order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			psmt.setString(2,status0);
			psmt.setString(3,status1);
			psmt.setString(4,status2);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_login_id_status2(String login_id,String status3,String status4,
			String status5,String status6,String status7,String status8,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where login_id=? and (status=? or status=? or status=? or status=? or status=? or status=?) order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			psmt.setString(2,status3);
			psmt.setString(3,status4);
			psmt.setString(4,status5);
			psmt.setString(5,status6);
			psmt.setString(6,status7);
			psmt.setString(7,status8);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_login_id_status3(String login_id,String status9,String status10,
			String status11,String status12,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where login_id=? and (status=? or status=? or status=? or status=?) order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			psmt.setString(2,status9);
			psmt.setString(3,status10);
			psmt.setString(4,status11);
			psmt.setString(5,status12);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_login_id_status4(String login_id,String count){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where login_id=? order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static List<T_enroll_Bean> select_login_id_status(String login_id,String status){
		List<T_enroll_Bean> list=new ArrayList<T_enroll_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where login_id=? and status=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			psmt.setString(2,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_enroll_Bean t_enroll = new T_enroll_Bean();
				t_enroll.setId(rs.getInt("id"));
				t_enroll.setLogin_id(rs.getInt("login_id"));
				t_enroll.setJob_id(rs.getInt("job_id"));
				t_enroll.setStatus(rs.getInt("status"));
				t_enroll.setLogin_time(rs.getString("login_time")+"");
				t_enroll.setState(rs.getInt("state"));
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
	
	public static int update_status(String status, String login_id,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set status=? where login_id=? and job_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, login_id);
			psmt.setString(3, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_status(String status,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set status=? where job_id=? and status=5";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_statusss(String status,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set status=? where job_id=? and status=8";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_statusww(String status,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set status=? where job_id=? and status=8";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_status00(String status,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set status=? where job_id=? and(status=0 or status=1 or status=3 or status=4)";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_status2(String status,String login_id,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set status=? where login_id=? and job_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, login_id);
			psmt.setString(3, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_status23(String status,String login_id,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set status=? where login_id=? and job_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, status);
			psmt.setString(2, login_id);
			psmt.setString(3, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_state(String state,String login_id,String job_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_enroll set state=? where login_id=? and job_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, state);
			psmt.setString(2, login_id);
			psmt.setString(3, job_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int delete(String login_id,String job_id){
		int num=0;
		PreparedStatement pstmt = null;
		Connection conn=DButil.getCon();
		try {
			String sql = "delete from t_enroll where login_id=? and job_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, login_id);
			pstmt.setString(2, job_id);
			num = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
