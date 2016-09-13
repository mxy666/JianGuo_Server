package com.jianguo.merchant.mersql;

import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WagesSql {

	public static boolean check(String login_id,String job_id) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			String sql = "select * from t_wages where login_id=? and job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			pstmt.setString(2, job_id);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		return b;
	}
	

	public static int insert(String login_id,String job_id,String hould_money,String real_money,String remarks,String reg_time) throws SQLException {
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_wages(login_id,job_id,hould_money,real_money,remarks,reg_time) values(?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
			pst.setString(1, login_id);
			pst.setString(2, job_id);
			pst.setString(3, hould_money);
			pst.setString(4, real_money);
			pst.setString(5, remarks);
			pst.setString(6, reg_time);
			num=pst.executeUpdate();
			DButil.close(conn);
			DButil.psClose(pst);
		return num;
	}
	

}
