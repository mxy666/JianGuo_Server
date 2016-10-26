package com.jianguo.merchant.mersql;

import com.jianguo.bean.MerchantInfo;
import com.jianguo.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CerficationSql {

	private static String pass;

	//判断手机号是否已经注册过
	public static boolean checkRegister(String tel) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
			String sql = "select * from t_user_login where tel=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		return b;
	}
/**
*更新商家信息
*@param merchantInfo
*@author invinjun
*created at 2016/10/22 13:03
*/
	public static int updateMerStatus(String loginId,MerchantInfo merchantInfo) throws SQLException {
		Connection conn=DButil.getCon();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		StringBuffer sql = "insert into t_user_login (tel,password,qqwx_token,power,payStatus,status,resume,city_id,hobby,pigeon_count) values" +
//				"('"+tel+"','','"+token+"',0,0,1,0,'',0,0)";
		String sql="UPDATE t_merchant SET `name`=?,name_image=?,contactName=?,contactPhone=?,email=?,province=?,city=?,companyAddress=?,cardNum=?,cardImage=?,realName=?,"+
				"handImage=?,bussinessImage=?,reviewMerStatus=1,about=? WHERE login_id=?";
		PreparedStatement pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pst.setString(1,merchantInfo.getName());
		pst.setString(2,merchantInfo.getUserImage());
		pst.setString(3,merchantInfo.getContactName());
		pst.setString(4,merchantInfo.getContactPhone());
		pst.setString(5,merchantInfo.getEmail());
		pst.setString(6,merchantInfo.getProvince());
		pst.setString(7,merchantInfo.getCity());
		pst.setString(8,merchantInfo.getCompanyAddress());
		pst.setString(9,merchantInfo.getCardNum());
		pst.setString(10,merchantInfo.getCardImg());
		pst.setString(11,merchantInfo.getRealName());
		pst.setString(12,merchantInfo.getHandImg());
		pst.setString(13,merchantInfo.getBussinessImg());
		pst.setString(14,merchantInfo.getAbout());
		pst.setString(15,loginId);
		int num = pst.executeUpdate();
		pst.close();
		conn.close();
		return num;
	}
	/**
	 *插入merchant_login表
	 *@param password
	 *@param token
	 *@author invinjun
	 *created at 2016/10/22 13:03
	 */
	public static int insertMerchant(String tel,String password,String token) throws SQLException {
		Connection conn=DButil.getCon();
		String sql = "insert into t_user_login (tel,password,qqwx_token,power,payStatus,status,resume,city_id,hobby,pigeon_count) values" +
				"('"+tel+"','"+password+"','"+token+"',0,0,1,0,'',0,0)";
		PreparedStatement pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		 pst.executeUpdate();
		ResultSet rs= pst.getGeneratedKeys();
		rs.next();
		int num=rs.getInt(1);
		pst.close();
		conn.close();
		return num;
	}
	/**
	 *插入merchant表
	 *@param
	 *@param
	 *@author invinjun
	 *created at 2016/10/22 13:03
	 */
	public static int insertMerchantInfo(int loginId) throws SQLException {
		Connection conn=DButil.getCon();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String registerTime = sdf.format(now);
//		String sql = "insert into t_merchant (login_id,regedit_time) values" +
//				"('"+loginId+"','"+registerTime+"')";
		String sql="insert into t_merchant (login_id,regedit_time,province,city,reviewStatus,about,label) values" +
				"('"+loginId+"','"+registerTime+"','','',0,'','')";
		PreparedStatement pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pst.executeUpdate();
		ResultSet rs= pst.getGeneratedKeys();
		rs.next();
		int num=rs.getInt(1);
		pst.close();
		conn.close();
		return num;

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
	 *检查手机号对token是否正确
	 *@param tel
	 *@param token
	 *@author invinjun
	 *created at 2016/9/12 9:31
	 */
	public static boolean checkVerificationToken(String tel, String token) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
		String sql = "select * from t_user_login where tel=? and qqwx_token=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, tel);
		pstmt.setString(2, token);
		rs = pstmt.executeQuery();
		b = rs.next();
		pstmt.close();
		conn.close();
		return b;
	}
	/**
	 *检查手机号对token是否正确
	 *@param login_id
	 *@param token
	 *@author invinjun
	 *created at 2016/9/12 9:31
	 */
	public static boolean checkToken(String login_id, String token) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
		String sql = "select * from t_user_login where login_id=? and qqwx_token=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, login_id);
		pstmt.setString(2, token);
		rs = pstmt.executeQuery();
		b = rs.next();
		pstmt.close();
		conn.close();
		return b;
	}
	/**
	 *检查手机号密码是否未设置
	 *@param tel
	 *@param password
	 *@author invinjun
	 *created at 2016/9/12 9:31
	 */
	public static String checkExistedPassword(String tel, String password) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
		String sql = "select * from t_user_login where tel=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, tel);
		rs = pstmt.executeQuery();
		while(rs.next()){
			pass = rs.getString("password");
		}
		pstmt.close();
		conn.close();
		return pass;
	}
	/**
	 *检查手机号密码是否正确
	 *@param tel
	 *@param password
	 *@author invinjun
	 *created at 2016/9/12 9:31
	 */
	public static boolean checkVerificationPassword(String tel, String password) throws SQLException {
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt;
		ResultSet rs ;
		String sql = "select * from t_user_login where tel=? and password=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, tel);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		b = rs.next();
		pstmt.close();
		conn.close();
		return b;
	}
	/**
	 * updateLoginTime
	 *@param loginId
	 *@author invinjun
	 *created at 2016/9/18 17:11
	 */
	public static int updateLoginTime(int loginId) throws SQLException {
		int num=0;
		Connection conn=DButil.getCon();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String loginTime = sdf.format(now);
		String sql = "update t_merchant set login_time=? where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		psmt.setString(1, loginTime);
		psmt.setInt(2,loginId);
		num=psmt.executeUpdate();
		psmt.close();
		conn.close();
		return num;
	}


}
