package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.util.DButil;

public class T_user_money_Sql {

	//判断该手机号是否已注册
	public static boolean check_login_id(String login_id){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_user_money where login_id=?";
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
	
	public static int insert(String login_id,String name,String money,String zhifubao,String yinhang,String kahao,String pay_password,String weixin){
		int num=0;
		Connection conn=DButil.getCon();

		String sql="insert into t_user_money(login_id,name,money,zhifubao,yinhang,kahao,pay_password,weixin) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			pst.setString(1, login_id);
			pst.setString(2, name);
			pst.setString(3, money);
			pst.setString(4, zhifubao);
			pst.setString(5, yinhang);
			pst.setString(6, kahao);
			pst.setString(7, pay_password);
			pst.setString(8, weixin);
			num=pst.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
		finally{
			DButil.close(conn);
			DButil.psClose(pst);
		}
		return num;
	}
	
	public static T_user_money_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_user_money_Bean t_user_money = new T_user_money_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_money where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_money.setId(rs.getInt("id"));
				t_user_money.setLogin_id(rs.getInt("login_id"));
				t_user_money.setName(rs.getString("name")+"");
				t_user_money.setMoney(rs.getDouble("money"));
				t_user_money.setZhifubao(rs.getString("zhifubao")+"");
				t_user_money.setZhifubao(rs.getString("zhifubao")+"");
				t_user_money.setYinhang(rs.getString("yinhang")+"");
				t_user_money.setKahao(rs.getString("kahao")+"");
				t_user_money.setPay_password(rs.getString("pay_password")+"");
//				t_user_money.setWeixin(rs.getInt("weixin"));
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_money;
	}
	
	public static int update_money(String money,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_money set money=money+? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, money);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_moneyss(String money,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_money set money=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, money);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_money_out(String money,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_money set money=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, money);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_password(String password,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_money set pay_password=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, password);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_zhiofubao(String name,String zhifubao,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_money set name=?,zhifubao=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, name);
			psmt.setString(2, zhifubao);
			psmt.setString(3, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_kahao(String name,String yinhang,String kahao,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_money set name=?,yinhang=?,kahao=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, name);
			psmt.setString(2, yinhang);
			psmt.setString(3, kahao);
			psmt.setString(4, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int update_weixin(String weixin,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_money set weixin=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, weixin);
			psmt.setString(2, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
}
