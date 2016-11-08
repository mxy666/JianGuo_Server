package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.util.DButil;

public class T_user_login_Sql {

	//判断该手机号是否已注册
	public static boolean check_tel(String tel){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_login where tel=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	//判断该手机号是否已注册
	public static boolean check_tel_power(String tel,String power){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_login where tel=? and power=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, power);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	//判断该QQWX号是否已注册
	public static boolean check_qqwx(String qqwx_token){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_login where qqwx_token=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qqwx_token);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	//判断该手机号和密码是否正确
	public static boolean check_tel_password(String tel,String password){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_login where tel=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	//判断该手机号和密码是否正确
	public static boolean check_tel_password_power(String tel,String password,String power){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_login where tel=? and password=? and power=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, password);
			pstmt.setString(3, power);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	//用户注册（手机号）
	public static int insert_tel(String tel,String password,String power,String status,String resume,String hobby,String pigeon_count ){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_user_login(tel,password,power,status,resume,hobby,pigeon_count) values(?,?,?,?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, tel);
		    	pst.setString(2, password);
		    	pst.setString(3, power);
		    	pst.setString(4, status);
		    	pst.setString(5, resume);
		    	pst.setString(6, hobby);
		    	pst.setString(7, pigeon_count);
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
	
	//用户注册（QQWX）
	public static int insert_qqwx(String tel,String password,String qqwx_token,String power,String status){
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_user_login(tel,password,qqwx_token,power,status) values(?,?,?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {
			pst.setString(1, tel);
			pst.setString(2, password);
			pst.setString(3, qqwx_token);
			pst.setString(4, power);
			pst.setString(5, status);
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
	public static T_user_login_Bean select_token(String token){
		ResultSet rs=null;
		T_user_login_Bean t_user_login = new T_user_login_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_login where qqwx_token=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,token);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_login.setId(rs.getInt("id"));
				t_user_login.setTel(rs.getString("tel")+"");
				t_user_login.setPassword(rs.getString("password")+"");
				t_user_login.setQqwx_token(rs.getString("qqwx_token")+"");
				t_user_login.setStatus(rs.getInt("status"));
				t_user_login.setResume(rs.getInt("resume"));
				t_user_login.setHobby(rs.getInt("hobby"));
				t_user_login.setCity_id(rs.getString("city_id")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_login;
	}
	
	//根据tel查询数据
	public static T_user_login_Bean select_tel(String tel){
		ResultSet rs=null;
		T_user_login_Bean t_user_login = new T_user_login_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_login where tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,tel);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_login.setId(rs.getInt("id"));
				t_user_login.setTel(rs.getString("tel")+"");
				t_user_login.setPassword(rs.getString("password")+"");
				t_user_login.setQqwx_token(rs.getString("qqwx_token")+"");
				t_user_login.setStatus(rs.getInt("status"));
				t_user_login.setResume(rs.getInt("resume"));
				t_user_login.setHobby(rs.getInt("hobby"));
				t_user_login.setCity_id(rs.getString("city_id")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_login;
	}
	
	public static List<T_user_login_Bean> select_list(String status){
		List<T_user_login_Bean> list=new ArrayList<T_user_login_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_user_login where status=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_user_login_Bean t_user_login = new T_user_login_Bean();
				t_user_login.setId(rs.getInt("id"));
				t_user_login.setTel(rs.getString("tel")+"");
				t_user_login.setPassword(rs.getString("password")+"");
				t_user_login.setQqwx_token(rs.getString("qqwx_token")+"");
				t_user_login.setStatus(rs.getInt("status"));
				t_user_login.setResume(rs.getInt("resume"));
				t_user_login.setHobby(rs.getInt("hobby"));
				t_user_login.setCity_id(rs.getString("city_id")+"");
				list.add(t_user_login);
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

	/**
	 * 根据地区查询
	 * @param status
	 * @return
	 */
	public static List<T_user_login_Bean> select_list_area(String status,String cityName){
		List<T_user_login_Bean> list=new ArrayList<T_user_login_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_user_login where status=? ";

		if("武汉".equals(cityName)||"杭州".equals(cityName)){
			sql +=" and city_id like '%"+cityName+"%'";
		}


		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,status);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_user_login_Bean t_user_login = new T_user_login_Bean();
				t_user_login.setId(rs.getInt("id"));
				t_user_login.setTel(rs.getString("tel")+"");
				t_user_login.setPassword(rs.getString("password")+"");
				t_user_login.setQqwx_token(rs.getString("qqwx_token")+"");
				t_user_login.setStatus(rs.getInt("status"));
				t_user_login.setResume(rs.getInt("resume"));
				t_user_login.setHobby(rs.getInt("hobby"));
				t_user_login.setCity_id(rs.getString("city_id")+"");
				list.add(t_user_login);
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





	//根据tel查询数据
	public static T_user_login_Bean select_id(String id){
		ResultSet rs=null;
		T_user_login_Bean t_user_login = new T_user_login_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_login where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_login.setId(rs.getInt("id"));
				t_user_login.setTel(rs.getString("tel")+"");
				t_user_login.setPassword(rs.getString("password")+"");
				t_user_login.setQqwx_token(rs.getString("qqwx_token")+"");
				t_user_login.setStatus(rs.getInt("status"));
				t_user_login.setResume(rs.getInt("resume"));
				t_user_login.setHobby(rs.getInt("hobby"));
				t_user_login.setCity_id(rs.getString("city_id")+"");
				t_user_login.setPigeon_count(rs.getInt("pigeon_count"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_login;
	}
	
	public static int update_password(String password,String tel){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_login set password=? where tel=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, password);
			psmt.setString(2, tel);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_tel(String tel,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_login set tel=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, tel);
			psmt.setString(2, id);
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
			String sql = "update t_user_login set status=? where id=?";
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
	
	public static int update_resume(String resume,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_login set resume=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, resume);
			psmt.setString(2, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_city_id(String city_id,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_login set city_id=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, city_id);
			psmt.setString(2, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_hobby(String hobby,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_login set hobby=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, hobby);
			psmt.setString(2, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	/*用户账户密码登录 */	
public static boolean checkForAccount(String account,String password){
		Connection conn=DButil.getCon();
		boolean result = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_login where tel=? and password=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);		
			rs = pstmt.executeQuery();
			result = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}  
	




	
}
