package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.util.DButil;

public class T_user_resume_Sql {

	//录入简历信息
	public static int insert_qq_wx(String login_id,String nickname,String name,String name_image,String school,
			String intoschool_date,String sex,String height,String student,String birth_date,String shoe_size,String clothing_size,
			String sign,String label){
		int num=0;
		Connection conn=DButil.getCon();
		String sql="insert into t_user_resume(login_id,nickname,name,name_image,school,intoschool_date,sex,height,student,birth_date,shoe_size,clothing_size,sign,label) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getPstm(conn, sql);
		try {
			pst.setString(1, login_id);
			pst.setString(2, nickname);
			pst.setString(3, name);
			pst.setString(4, name_image);
			pst.setString(5, school);
			pst.setString(6, intoschool_date);
			pst.setString(7, sex);
			pst.setString(8, height);
			pst.setString(9, student);
			pst.setString(10, birth_date);
			pst.setString(11, shoe_size);
			pst.setString(12, clothing_size);
			pst.setString(13, sign);
			pst.setString(14, label);
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
	public static T_user_resume_Bean select_login_id(String login_id){
		ResultSet rs=null;
		T_user_resume_Bean t_user_resume = new T_user_resume_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_user_resume where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_resume.setId(rs.getInt("id"));
				t_user_resume.setLogin_id(rs.getInt("login_id"));
				t_user_resume.setNickname(rs.getString("nickname")+"");
				t_user_resume.setName(rs.getString("name")+"");
				t_user_resume.setName_image(rs.getString("name_image")+"");
				t_user_resume.setSchool(rs.getString("school")+"");
				t_user_resume.setIntoschool_date(rs.getString("intoschool_date")+"");
				t_user_resume.setSex(rs.getInt("sex"));
				t_user_resume.setHeight(rs.getInt("height"));
				t_user_resume.setStudent(rs.getInt("student"));
				t_user_resume.setBirth_date(rs.getString("birth_date")+"");
				t_user_resume.setShoe_size(rs.getString("shoe_size")+"");
				t_user_resume.setClothing_size(rs.getString("clothing_size")+"");
				t_user_resume.setSign(rs.getString("sign")+"");
				t_user_resume.setLabel(rs.getString("label")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_resume;
	}

	public static int update(String nickname,String name,String name_image,String school,
			String intoschool_date,String sex,String height,String student,String birth_date,String shoe_size,String clothing_size,
			String sign,String label,String login_id){
		int num=0;
		try {
			Connection conn=DButil.getCon();
			String sql = "update t_user_resume set nickname=?,name=?,name_image=?,school=?,intoschool_date=?,sex=?,height=?,student=?,birth_date=?,shoe_size=?,clothing_size=?,sign=?,label=? where login_id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			psmt.setString(1, nickname);
			psmt.setString(2, name);
			psmt.setString(3, name_image);
			psmt.setString(4, school);
			psmt.setString(5, intoschool_date);
			psmt.setString(6, sex);
			psmt.setString(7, height);
			psmt.setString(8, student);
			psmt.setString(9, birth_date);
			psmt.setString(10, shoe_size);
			psmt.setString(11, clothing_size);
			psmt.setString(12, sign);
			psmt.setString(13, label);
			psmt.setString(14, login_id);
			num=psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	//只查询性别
	public static T_user_resume_Bean select_sex(String login_id){
		ResultSet rs=null;
		T_user_resume_Bean t_user_resume = new T_user_resume_Bean();
		Connection conn=DButil.getCon();
		String sql = "select sex from t_user_resume where login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_user_resume.setSex(rs.getInt("sex"));			
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_user_resume;
	}
}
