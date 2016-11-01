package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_opinion_Bean;
import com.jianguo.bean.T_push_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.util.DButil;
import com.jianguo.util.PageModel;


public class T_opinion_Sql {
	
	public static int insert(String tel,String text,String time){
		   int num=0;
		   Connection conn=DButil.getCon();
		   String sql="insert into t_opinion(tel,text,time) values(?,?,?)";
		    PreparedStatement pst=DButil.getPstm(conn, sql);
		    try {
		    	pst.setString(1, tel);
		    	pst.setString(2, text);
		    	pst.setString(3, time);
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
	
	public static T_opinion_Bean select_tel(String tel){
		ResultSet rs=null;
		T_opinion_Bean t_enroll_limit = new T_opinion_Bean();
		Connection conn=DButil.getCon();
		String sql = "select * from t_opinion where tel=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setString(1,tel);
			rs=psmt.executeQuery();
			while(rs.next()){
				t_enroll_limit.setId(rs.getInt("id"));
				t_enroll_limit.setTel(rs.getString("tel_no")+"");
				t_enroll_limit.setText(rs.getString("text")+"");
				t_enroll_limit.setTime(rs.getString("time")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return t_enroll_limit;
	}
	
	//意见反馈
	public static List<T_opinion_Bean> queryAll(){
		List<T_opinion_Bean> list=new ArrayList<T_opinion_Bean>();
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		//StringBuffer str = new StringBuffer();
		
		String sql = " select o.*,l.`tel` as tel_no  from t_opinion o "
		+ " LEFT JOIN `t_user_login` l on o.`tel` = l.`id` "
		+ "ORDER BY id DESC ";
		// sql=sql+str.toString();
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();
			while(rs.next()){
				T_opinion_Bean opinion = new T_opinion_Bean();
				opinion.setId(rs.getInt("id"));
				opinion.setTel(rs.getString("tel_no"));
				opinion.setText(rs.getString("text"));
				opinion.setTime(rs.getString("time"));
			
				list.add(opinion);
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
//意见反馈分页
	public static PageModel<T_opinion_Bean> queryAllT(PageModel page){
		Connection conn=DButil.getCon();
		List<T_opinion_Bean> list=new ArrayList<T_opinion_Bean>();
		ResultSet rs=null;
		//StringBuffer str = new StringBuffer();

		String sql = " select o.*,l.`tel` as tel_no  from t_opinion o "
				+ " LEFT JOIN `t_user_login` l on o.`tel` = l.`id` "
				+ "  ORDER BY id DESC "
				+ "limit "+page.getFirstResult()+","+page.getPageSize();
		
//		String sql = "select * from t_opinion where 1=1 ORDER BY id DESC limit "+page.getFirstResult()+","+page.getPageSize();
		// sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
		
			rs=psmt.executeQuery();
			while(rs.next()){
				T_opinion_Bean opinion = new T_opinion_Bean();
				opinion.setId(rs.getInt("id"));
				opinion.setTel(rs.getString("tel_no"));
				opinion.setText(rs.getString("text"));
				opinion.setTime(rs.getString("time"));
			
				list.add(opinion);
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
			page.setList(list);
			selectAllRecord_count(page);
			return page;
		}
		
		//分页---->统计总条数
		public static void selectAllRecord_count(PageModel<T_opinion_Bean> page){
			 List <T_opinion_Bean>list=new ArrayList<T_opinion_Bean>();
		     ResultSet rs=null;
		    Connection conn=DButil.getCon();
		    StringBuffer str = new StringBuffer();
		    String sql="select count(a.id)from t_opinion a where 1=1";		
		 
		    
			sql=sql+str.toString();
		    Statement sta=DButil.getSta(conn);
		    int i = 0;
		    try { 
				rs=sta.executeQuery(sql);
				while(rs.next())
				{  
				i=rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DButil.staClose(sta);
				DButil.close(conn);
			}
			page.setTotalRecords(i);
		}
}
