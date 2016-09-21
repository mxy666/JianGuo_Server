package com.jianguo.merchant.mersql;

import com.jianguo.bean.T_tel_code_Bean;
import com.jianguo.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelCodeSql {
	
	//检查手机号码是否存在
	public static boolean checkTel(String tel) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			String sql = "select * from tel_code where tel=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		return b;
	}
	//控制短信验证码访问频率
	public static boolean checkTime(String tel,long currentMillis) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b ;
		PreparedStatement pstmt ;
		ResultSet rs ;
		String sql = "select timemillis timemillis from tel_code where tel=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, tel);
		rs = pstmt.executeQuery();
		rs.next();
		long oldTimeMillis = rs.getLong("timemillis");
		if (currentMillis-oldTimeMillis>30000) {
			b=true;
		}else {
			b=false;
		}
		pstmt.close();
		conn.close();
		return b;
	}
	//判断验证码是否过期10分钟内有效
	public static boolean checkExpiryDate(String tel,long currentMillis) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b ;
		PreparedStatement pstmt ;
		ResultSet rs ;
		String sql = "select timemillis from tel_code where tel=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, tel);
		rs = pstmt.executeQuery();
		rs.next();
		long oldTimeMillis = rs.getLong("timemillis");
		if (currentMillis-oldTimeMillis>600000) {
			b=true;
		}else {
			b=false;
		}
		pstmt.close();
		conn.close();
		return b;
	}
	public static boolean check_tel_code(String tel,String code){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from t_tel_code where tel=? and code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, code);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	//数据库插入新的验证码数据
	public static int insert(String tel,String code) throws SQLException {
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into tel_code(tel,code,timemillis) values(?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    	pst.setString(1, tel);
		    	pst.setString(2, code);
				pst.setLong(3,System.currentTimeMillis());
				num=pst.executeUpdate();
				DButil.close(conn);
				DButil.psClose(pst);
			return num;
	}
	//更新code验证码和发送时间戳
	public static int updateTel(String code,String tel) throws SQLException {
		int num=0;
			Connection conn=DButil.getCon();
			String sql = "update tel_code set code=? ,timemillis=? where tel=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, code);
		psmt.setLong(2,System.currentTimeMillis());
			psmt.setString(3, tel);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		return num;
	}
	
	public static T_tel_code_Bean querySmsCode(String tel){
		ResultSet rs=null;
		T_tel_code_Bean code = new T_tel_code_Bean();
		Connection conn=DButil.getCon();
		String sql = "select code from tel_code where tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,tel);
			rs=psmt.executeQuery();
			while(rs.next()){
				code.setCode(rs.getInt("code"));			
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return code;
	}
	
	
}
