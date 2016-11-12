package com.jianguo.merchant.mersql;

import com.jianguo.bean.MerchantInfo;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.util.DButil;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginSql {

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
*插入merchant_login表
*@param tel
*@param token
*@author invinjun
*created at 2016/10/22 13:03
*/
	public static int insertMerchant(String tel,String token) throws SQLException {
		Connection conn=DButil.getCon();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "insert into t_user_login (tel,password,qqwx_token,power,status,resume,city_id,hobby,pigeon_count) values" +
				"('"+tel+"','','"+token+"',0,1,0,'',0,0)";
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
	 *插入merchant_login表
	 *@param password
	 *@param token
	 *@author invinjun
	 *created at 2016/10/22 13:03
	 */
	public static int insertMerchant(String tel,String password,String token) throws SQLException {
		Connection conn=DButil.getCon();

		String sql = "insert into t_user_login (tel,password,qqwx_token,power,status,resume,city_id,hobby,pigeon_count) values" +
				"('"+tel+"','"+password+"','"+token+"',0,1,0,'',0,0)";
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
	 *@author invinjun
	 *created at 2016/10/22 13:03
	 * @param
	 * @param
	 * @param tel
	 */
	public static int insertMerchantInfo(int loginId, String tel) throws SQLException {
		Connection conn=DButil.getCon();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String registerTime = sdf.format(now);
		String pay_pass=tel.substring(tel.length()-6);
//		String sql = "insert into t_merchant (login_id,regedit_time) values" +
//				"('"+loginId+"','"+registerTime+"')";
		String sql="insert into t_merchant (login_id,regedit_time,province,city,reviewMerStatus,about,label,pay_password) values" +
				"('"+loginId+"','"+registerTime+"','','',0,'','','"+pay_pass+"')";
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
		String sql = "select * from t_user_login where id=? and qqwx_token=?";
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

	/**
	 * updatePassword
	 *@param password
	 * @param tel
	 *@author invinjun
	 *created at 2016/9/18 17:11
	 */
	public static int updatePassword(String tel,String password) throws SQLException {
		int num=0;
		Connection conn=DButil.getCon();
		String sql = "update t_user_login set password=? where tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		psmt.setString(1, password);
		psmt.setString(2,tel);
		num=psmt.executeUpdate();
		psmt.close();
		conn.close();
		return num;
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
		String sql = "update t_user_login set qqwx_token=? where tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		psmt.setString(1, token);
		psmt.setString(2, tel);
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
		String sql = "select mer.id,mer.pay_password,mer.login_id,mer.companyName,mer.reviewMerStatus,mer.`name`,mer.name_image,mer.email,mer.contactName,mer.contactPhone,mer.province,mer.city,mer.companyAddress,login.power, login.`password`,login.power,login.qqwx_token,login.tel from t_user_login login LEFT JOIN t_merchant mer on login.id=mer.login_id where login.tel=?";
//	"select * from t_user_login login LEFT JOIN t_merchant mer on login.id=mer.login_id where login.tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				merchantInfo.setLoginId(rs.getInt("login_id"));
				merchantInfo.setMerchantId(rs.getInt("id"));
				merchantInfo.setTel(rs.getString("tel"));
				merchantInfo.setPassword(rs.getString("password"));
				merchantInfo.setToken(rs.getString("qqwx_token"));
				merchantInfo.setPermissions(rs.getInt("power"));
				merchantInfo.setMerchantInfoStatus(rs.getInt("reviewMerStatus"));
//				merchantInfo.setPayStatus(rs.getInt("payStatus"));

				merchantInfo.setName(rs.getString("name"));
				merchantInfo.setCompanyName(rs.getString("companyName"));
				merchantInfo.setPermissions(rs.getInt("power"));
				merchantInfo.setUserImage(rs.getString("name_image"));
				merchantInfo.setContactName(rs.getString("contactName"));
				merchantInfo.setContactPhone(rs.getString("contactPhone"));
				merchantInfo.setEmail(rs.getString("email"));
				merchantInfo.setProvince(rs.getString("province"));
				merchantInfo.setCity(rs.getString("city"));
				merchantInfo.setCompanyAddress(rs.getString("companyAddress"));
				merchantInfo.setPayPassword(rs.getString("pay_password"));
			}
			psmt.close();
			conn.close();
			DButil.close(conn);
		return merchantInfo;
	}

}
