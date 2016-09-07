package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_area_Bean;
import com.jianguo.bean.T_banner_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_school_Bean;
import com.jianguo.bean.T_type_Bean;
import com.jianguo.util.DButil;

public class T_school_Sql {
	
	public static List<T_school_Bean> select_All_name(String name){
		List<T_school_Bean> list=new ArrayList<T_school_Bean>();
		ResultSet rs=null;

		Connection conn=DButil.getCon();
		String sql = "select * from t_school where name like '%"+name+"%'";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_school_Bean t_school = new T_school_Bean();
				t_school.setId(rs.getInt("id"));
				t_school.setCity_id(rs.getInt("city_id"));
				t_school.setName(rs.getString("name")+"");
				list.add(t_school);
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
	
	public static List<T_city_Bean> select_All_city(){
		List<T_city_Bean> list=new ArrayList<T_city_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_city";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_city_Bean t_city = new T_city_Bean();
				t_city.setId(rs.getInt("id"));
				t_city.setCity(rs.getString("city")+"");
				t_city.setCode(rs.getString("code")+"");
				list.add(t_city);
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
	
	public static List<T_city_Bean> select_All_city_id(String id){
		List<T_city_Bean> list=new ArrayList<T_city_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_city where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_city_Bean t_city = new T_city_Bean();
				t_city.setId(rs.getInt("id"));
				t_city.setCity(rs.getString("city")+"");
				t_city.setCode(rs.getString("code")+"");
				list.add(t_city);
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
	
	public static List<T_area_Bean> select_All_area(String city_id){
		List<T_area_Bean> list=new ArrayList<T_area_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_area where city_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,city_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_area_Bean area = new T_area_Bean();
				area.setId(rs.getInt("id"));
				area.setCity_id(rs.getInt("city_id"));
				area.setArea_name(rs.getString("area_name")+"");
				list.add(area);
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
	
	public static List<T_type_Bean> select_All_type(){
		List<T_type_Bean> list=new ArrayList<T_type_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_type";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_type_Bean t_type = new T_type_Bean();
				t_type.setId(rs.getInt("id"));
				t_type.setType_name(rs.getString("type_name")+"");
				list.add(t_type);
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
	
	public static List<T_type_Bean> select_All_typeaaa(String login_id){
		List<T_type_Bean> list=new ArrayList<T_type_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
//		String sql = "select a.id,a.type_name,b.type,b.login_id from t_type a left join t_hobby_type b on a.id = b.type where b.login_id=? or b.login_id is null";
		String sql = "select * from t_type a left join t_hobby_type b on a.id=b.type and b.login_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,login_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				T_type_Bean t_type = new T_type_Bean();
				t_type.setId(rs.getInt("a.id"));
				t_type.setType_name(rs.getString("a.type_name")+"");
				t_type.setIs_type(rs.getString("b.type")+"");
				t_type.setLogin_id(rs.getString("b.login_id")+"");
				list.add(t_type);
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
	
	public static List<T_banner_Bean> select_All_banner(){
		List<T_banner_Bean> list=new ArrayList<T_banner_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "select * from t_banner";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_banner_Bean t_banner = new T_banner_Bean();
				t_banner.setId(rs.getInt("id"));
				t_banner.setImage(rs.getString("image")+"");
				t_banner.setUrl(rs.getString("url")+"");
				list.add(t_banner);
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
	
	public static T_city_Bean select_t_city_id(String id){
		ResultSet rs=null;
		T_city_Bean t_city = new T_city_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_city where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_city.setId(rs.getInt("id"));
				t_city.setCity(rs.getString("city")+"");
				t_city.setCode(rs.getString("code")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_city;
	}
	
	public static T_area_Bean select_t_area_id(String id){
		ResultSet rs=null;
		T_area_Bean area = new T_area_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_area where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				area.setId(rs.getInt("id"));
				area.setCity_id(rs.getInt("city_id"));
				area.setArea_name(rs.getString("area_name")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return area;
	}
	
	public static T_type_Bean select_t_type_id(String id){
		ResultSet rs=null;
		T_type_Bean t_type = new T_type_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_type where id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,id);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_type.setId(rs.getInt("id"));
				t_type.setType_name(rs.getString("type_name")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_type;
	}

	//根据token查询数据
//	public static T_school_Bean select_name(String name){
//		ResultSet rs=null;
//		T_school_Bean t_school = new T_school_Bean();
//		Connection conn=DButil.getCon();
//		String sql = "select * from t_school where like '%"+name+"%";
//		PreparedStatement psmt = DButil.getPstm(conn, sql);
//		try {
////			psmt.setString(1,token);
//			rs=psmt.executeQuery();
//			while(rs.next()){
//				t_school.setId(rs.getInt("id"));
//				t_school.setCity_id(rs.getInt("city_id"));
//				t_school.setName(rs.getString("name")+"");
//			}
//			psmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			DButil.close(conn);
//		}
//		return t_school;
//	}
	
}
