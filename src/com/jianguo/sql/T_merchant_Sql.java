package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.util.DButil;
import org.apache.log4j.Logger;

public class T_merchant_Sql {


	 static Logger logger = Logger.getLogger("log");


	//根据ID查出商家信息 new
	public static T_merchant_Bean select_id_ad(String id){
		ResultSet rs=null;
		T_merchant_Bean t_merchant = new T_merchant_Bean();
		Connection conn=DButil.getCon();
		String sql = "select u.id,m.realName,m.cardNum,u.tel,m.companyName,m.handImage,m.cardImage,m.bussinessImage from t_merchant m inner join t_user_login u on u.id = m.login_id where m.id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_merchant.setId(Integer.parseInt(id));
				t_merchant.setLogin_id(rs.getInt("id"));
				t_merchant.setReal_name(rs.getString("realName"));
				t_merchant.setCard_num(rs.getString("cardNum")+"");
				t_merchant.setTel(rs.getString("tel")+"");
				t_merchant.setCompanyName(rs.getString("companyName")+"");
				t_merchant.setHandImage(rs.getString("handImage"));
				t_merchant.setCardImage(rs.getString("cardImage"));
				t_merchant.setBussinessImage(rs.getString("bussinessImage"));

//				t_merchant.setJob_count(rs.getInt("job_count"));
//				t_merchant.setUser_count(rs.getInt("user_count"));
//				t_merchant.setFans_count(rs.getInt("fans_count"));
//				t_merchant.setPost(rs.getInt("post"));
//				t_merchant.setRegedit_time(rs.getString("regedit_time")+"");
//				t_merchant.setLogin_time(rs.getString("login_time")+"");
//				t_merchant.setPay_password(rs.getString("pay_password")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_merchant;
	}
	public static List<T_merchant_Bean> selectAuthInfo(String city_id){


		List<T_merchant_Bean> list=new ArrayList<T_merchant_Bean>();

		try {
			Connection conn=DButil.getCon();
			String sql = "select m.id,u.tel,m.realName,u.power,m.companyName,t.city from t_merchant m  inner join t_user_login u on m.login_id = u.id inner join t_city t on t.code = m.city where 1=1 and m.reviewMerStatus = 1 ";
			if(!"boss".equals(city_id)){
				sql += " and t.city like '%"+city_id+"%' ";
			}

			logger.info("sql=="+sql);
			logger.info("city_id=="+city_id);
//            System.out.println(sql);
            PreparedStatement psmt = DButil.getPstm(conn, sql);

			ResultSet rs =psmt.executeQuery();

			while(rs.next()){
				T_merchant_Bean merchant = new T_merchant_Bean();
				merchant.setTel(rs.getString("tel"));
				merchant.setReal_name(rs.getString("realName"));
				merchant.setCompanyName(rs.getString("companyName"));
				merchant.setCity(rs.getString("city"));
				merchant.setAbout(rs.getString("power"));
				merchant.setLogin_id(Integer.parseInt(rs.getString("id")));
				list.add(merchant);
			}


			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static int update_status(int status,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_merchant set reviewMerStatus=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setInt(1, status);
			psmt.setString(2, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	//判断该商家有没有
	public static boolean check_login_id(String login_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_merchant where login_id=?";
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

	//商家信息录入
	public static int insert(String login_id,String name,String name_image,String about,String label,String score,String job_count,
			String user_count,String fans_count,String post,String regedit_time,String login_time,String pay_password){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_merchant(login_id,name,name_image,about,label,score,job_count,user_count,fans_count,post,regedit_time,login_time,pay_password) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, login_id);
		    	pst.setString(2, name);
		    	pst.setString(3, name_image);
		    	pst.setString(4, about);
		    	pst.setString(5, label);
		    	pst.setString(6, score);
		    	pst.setString(7, job_count);
		    	pst.setString(8, user_count);
		    	pst.setString(9, fans_count);
		    	pst.setString(10, post);
		    	pst.setString(11, regedit_time);
		    	pst.setString(12, login_time);
		    	pst.setString(13, pay_password);
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
	
	//根据登录ID查出商家信息
	public static T_merchant_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_merchant_Bean t_merchant = new T_merchant_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_merchant where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_merchant.setId(rs.getInt("id"));
				t_merchant.setLogin_id(rs.getInt("login_id"));
				t_merchant.setName(rs.getString("name")+"");
				t_merchant.setName_image(rs.getString("name_image")+"");
				t_merchant.setAbout(rs.getString("about")+"");
				t_merchant.setLabel(rs.getString("label")+"");
				t_merchant.setScore(rs.getDouble("score"));
				t_merchant.setJob_count(rs.getInt("job_count"));
				t_merchant.setUser_count(rs.getInt("user_count"));
				t_merchant.setFans_count(rs.getInt("fans_count"));
				t_merchant.setPost(rs.getInt("post"));
				t_merchant.setRegedit_time(rs.getString("regedit_time")+"");
				t_merchant.setLogin_time(rs.getString("login_time")+"");
				t_merchant.setPay_password(rs.getString("pay_password")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_merchant;
	}
	
	//根据ID查出商家信息
	public static T_merchant_Bean select_id(String id){
		ResultSet rs=null;
		T_merchant_Bean t_merchant = new T_merchant_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_merchant where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_merchant.setId(rs.getInt("id"));
				t_merchant.setLogin_id(rs.getInt("login_id"));
				t_merchant.setName(rs.getString("companyName")+"");
				t_merchant.setName_image(rs.getString("name_image")+"");
				t_merchant.setAbout(rs.getString("about")+"");
				t_merchant.setLabel(rs.getString("label")+"");
				t_merchant.setScore(rs.getDouble("score"));
				t_merchant.setJob_count(rs.getInt("job_count"));
				t_merchant.setUser_count(rs.getInt("user_count"));
				t_merchant.setFans_count(rs.getInt("fans_count"));
				t_merchant.setPost(rs.getInt("post"));
				t_merchant.setRegedit_time(rs.getString("regedit_time")+"");
				t_merchant.setLogin_time(rs.getString("login_time")+"");
				t_merchant.setPay_password(rs.getString("pay_password")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_merchant;
	}
	
	public static int update_pay(String pay_password,String id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_merchant set pay_password=? where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, pay_password);
			psmt.setString(2, id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
