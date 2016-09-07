package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.util.DButil;

public class T_wages_Sql {

	//判断该手机号是否已注册
	public static boolean check(String login_id,String job_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_wages where login_id=? and job_id=?";
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
	
	public static boolean check2(String login_id,String job_id,String reg_time){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_wages where login_id=? and job_id=? and reg_time=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			pstmt.setString(2, job_id);
			pstmt.setString(3, reg_time);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static boolean check_reg(String login_id,String job_id,String reg_time){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_wages where login_id=? and job_id=? and reg_time=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			pstmt.setString(2, job_id);
			pstmt.setString(3, reg_time);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static int insert(String login_id,String job_id,String hould_money,String real_money,String remarks,String reg_time){
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_wages(login_id,job_id,hould_money,real_money,remarks,reg_time) values(?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {
			pst.setString(1, login_id);
			pst.setString(2, job_id);
			pst.setString(3, hould_money);
			pst.setString(4, real_money);
			pst.setString(5, remarks);
			pst.setString(6, reg_time);
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
	
	public static List<T_wages_Bean> select_All_name(String login_id,String count){
		List<T_wages_Bean> list=new ArrayList<T_wages_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_wages where login_id=? order by id desc limit "+count+",10";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_wages_Bean t_wages = new T_wages_Bean();
				t_wages.setId(rs.getInt("id"));
				t_wages.setLogin_id(rs.getInt("login_id"));
				t_wages.setJob_id(rs.getInt("job_id"));
				t_wages.setHould_money(rs.getDouble("hould_money"));
				t_wages.setReal_money(rs.getDouble("real_money"));
				t_wages.setRemarks(rs.getString("remarks")+"");
				t_wages.setReg_time(rs.getString("reg_time")+"");
				list.add(t_wages);
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
	
	public static List<T_wages_Bean> select_All_login_id(String login_id){
		List<T_wages_Bean> list=new ArrayList<T_wages_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_wages where login_id=? order by id desc";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_wages_Bean t_wages = new T_wages_Bean();
				t_wages.setId(rs.getInt("id"));
				t_wages.setLogin_id(rs.getInt("login_id"));
				t_wages.setJob_id(rs.getInt("job_id"));
				t_wages.setHould_money(rs.getDouble("hould_money"));
				t_wages.setReal_money(rs.getDouble("real_money"));
				t_wages.setRemarks(rs.getString("remarks")+"");
				t_wages.setReg_time(rs.getString("reg_time")+"");
				list.add(t_wages);
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
	
	public static List<T_wages_Bean> select_All(){
		List<T_wages_Bean> list=new ArrayList<T_wages_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_wages";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_wages_Bean t_wages = new T_wages_Bean();
				t_wages.setId(rs.getInt("id"));
				t_wages.setLogin_id(rs.getInt("login_id"));
				t_wages.setJob_id(rs.getInt("job_id"));
				t_wages.setHould_money(rs.getDouble("hould_money"));
				t_wages.setReal_money(rs.getDouble("real_money"));
				t_wages.setRemarks(rs.getString("remarks")+"");
				t_wages.setReg_time(rs.getString("reg_time")+"");
				list.add(t_wages);
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
	
	public static T_wages_Bean select_idssss(String id){
		ResultSet rs=null;
		T_wages_Bean t_wages = new T_wages_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_wages where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_wages.setId(rs.getInt("id"));
				t_wages.setLogin_id(rs.getInt("login_id"));
				t_wages.setJob_id(rs.getInt("job_id"));
				t_wages.setHould_money(rs.getDouble("hould_money"));
				t_wages.setReal_money(rs.getDouble("real_money"));
				t_wages.setRemarks(rs.getString("remarks")+"");
				t_wages.setReg_time(rs.getString("reg_time")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_wages;
	}
	
	public static T_enroll_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_enroll_Bean t_enroll = new T_enroll_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_enroll where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
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
	
	public static T_wages_Bean select_login_idss(String id){
		ResultSet rs=null;
		T_wages_Bean t_wages = new T_wages_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_wages where login_id=? order by id desc LIMIT 0,1";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_wages.setId(rs.getInt("id"));
				t_wages.setLogin_id(rs.getInt("login_id"));
				t_wages.setJob_id(rs.getInt("job_id"));
				t_wages.setHould_money(rs.getDouble("hould_money"));
				t_wages.setReal_money(rs.getDouble("real_money"));
				t_wages.setRemarks(rs.getString("remarks")+"");
				t_wages.setReg_time(rs.getString("reg_time")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_wages;
	}
	
}
