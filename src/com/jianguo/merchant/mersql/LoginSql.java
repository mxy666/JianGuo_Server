package com.jianguo.merchant.mersql;

import com.jianguo.bean.MerchantInfo;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.util.DButil;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginSql {
	//判断手机号是否已经注册过
	public static boolean checkRegister(String tel) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
			String sql = "select * from merchant_login where tel=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		return b;
	}

	public static int insertMerchant(String tel,String token) throws SQLException {
		Connection conn=DButil.getCon();
		PreparedStatement pstmt;
		ResultSet rs ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String registerTime = sdf.format(now);
			String sql = "insert into merchant_login (tel,password,token,permissions,resume_status,pay_status,login_time,register_time) values" +
					"('"+tel+"','','"+token+"',0,0,0,'"+registerTime+"','"+registerTime+"')";
			pstmt = conn.prepareStatement(sql);
		int i = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return i;
	}
	/**
	*检查手机号对应验证码是否正确
	*@param tel
	*@param code
	*@author invinjun
	*created at 2016/9/12 9:31
	*/
	public static boolean checkVerificationCode(String tel, String code) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
			String sql = "select * from tel_code where tel=? and code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, code);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		return b;
	}
	/**
	* updateToken
	*@param tel
	*@param token
	*@author invinjun
	*created at 2016/9/18 17:11
	*/
	public static int updateToken(String tel,String token) throws SQLException {
		int num=0;
		Connection conn=DButil.getCon();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String loginTime = sdf.format(now);
		String sql = "update merchant_login set token=?,login_time=? where tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		psmt.setString(1, token);
		psmt.setString(2,loginTime);
		psmt.setString(3, tel);
		num=psmt.executeUpdate();
		psmt.close();
		conn.close();
		return num;
	}
	public static MerchantInfo getMerchantInfo(String login_id) throws SQLException {
		ResultSet rs=null;
		MerchantInfo merchantInfo = new MerchantInfo();
		Connection conn=DButil.getCon();
//		String sql = "select * from t_user_info where login_id=?";
		String sql = "select * from merchant_login login LEFT JOIN merchant mer on login.id=mer.login_id where login.tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				merchantInfo.setLoginId(rs.getInt("id"));
				merchantInfo.setTel(rs.getString("tel"));
				merchantInfo.setPassword(rs.getString("password"));
				merchantInfo.setToken(rs.getString("token"));
				merchantInfo.setPermissions(rs.getInt("permissions"));
				merchantInfo.setResumeStatus(rs.getInt("resume_status"));
				merchantInfo.setPayStatus(rs.getInt("pay_status"));

				merchantInfo.setNickName(rs.getString("nick_name"));
				merchantInfo.setName(rs.getString("name"));
				merchantInfo.setUserImage(rs.getString("user_image"));
				merchantInfo.setContactName(rs.getString("contact_name"));
				merchantInfo.setContactPhone(rs.getString("contact_phone"));
				merchantInfo.setCompanyAddress(rs.getString("company_address"));
				merchantInfo.setEmail(rs.getString("email"));
				merchantInfo.setCity(rs.getString("city"));
			}
			psmt.close();
			conn.close();
			DButil.close(conn);
		return merchantInfo;
	}

}
