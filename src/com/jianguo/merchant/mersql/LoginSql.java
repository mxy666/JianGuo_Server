package com.jianguo.merchant.mersql;

import com.jianguo.bean.T_city_Bean;
import com.jianguo.util.DButil;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginSql {
	private static Connection conn=DButil.getCon();
	static boolean b = true;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	//判断手机号是否已经注册过
	public static boolean checkRegister(String tel){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
		try {
			String sql = "select * from t_merchant_login where tel=?";
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

	public static boolean insertMerchant(String tel){
		try {
			String sql = "insert into t_merchant_login (tel,password,token,permission,resume_status,review_status,pay_status) values" +
					"('"+tel+"','111111','','','','','')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	/**
	*检查手机号对应验证码是否正确
	*@param tel
	*@param code
	*@author invinjun
	*created at 2016/9/12 9:31
	*/
	public static boolean checkVerificationCode(String tel, String code){
		boolean b = true;
		try {
			String sql = "select * from t_merchant_login where tel=?";
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


}
