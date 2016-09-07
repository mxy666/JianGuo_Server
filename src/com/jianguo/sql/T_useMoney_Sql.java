package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jianguo.bean.T_use_Money_Bean;
import com.jianguo.util.DButil;
import com.jianguo.util.PageModel;

public class T_useMoney_Sql {
	
	 public static List<T_use_Money_Bean> queryAllUseMoney(){
		 Connection conn=DButil.getCon();
		 List <T_use_Money_Bean>list=new ArrayList<T_use_Money_Bean>();
		 ResultSet rs=null;
			String sql = "select * from t_money_use ";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			try {
				rs=psmt.executeQuery();
				while(rs.next()){
					T_use_Money_Bean useMoney = new T_use_Money_Bean();
					useMoney.setId(rs.getInt("id"));
					useMoney.setAdmin(rs.getString("admin"));
					useMoney.setWorkDate(rs.getString("work_date"));
					useMoney.setMerchant(rs.getString("merchant"));
					useMoney.setName(rs.getString("name"));
					useMoney.setTel(rs.getString("tel"));
					useMoney.setHouldMoney(rs.getDouble("hould_money"));
					useMoney.setMoneyOut(rs.getDouble("moneyout"));
					useMoney.setMoneyOutDate(rs.getString("moneyout_date"));
					useMoney.setRemarks(rs.getString("remarks"));
					list.add(useMoney);
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
	 
	//分页---
		public static PageModel<T_use_Money_Bean> selectAllT(PageModel<T_use_Money_Bean> page){
		    Connection conn=DButil.getCon();
			 List <T_use_Money_Bean>list=new ArrayList<T_use_Money_Bean>();
			 ResultSet rs=null;
				  String sql="select * from t_money_use order by id desc limit "+page.getFirstResult()+","+page.getPageSize();
				PreparedStatement psmt = DButil.getPstm(conn, sql);
				try {
					rs=psmt.executeQuery();
					while(rs.next()){
						T_use_Money_Bean useMoney = new T_use_Money_Bean();
						useMoney.setId(rs.getInt("id"));
						useMoney.setAdmin(rs.getString("admin"));
						useMoney.setWorkDate(rs.getString("work_date"));
						useMoney.setMerchant(rs.getString("merchant"));
						useMoney.setName(rs.getString("name"));
						useMoney.setTel(rs.getString("tel"));
						useMoney.setHouldMoney(rs.getDouble("hould_money"));
						useMoney.setMoneyOut(rs.getDouble("moneyout"));
						useMoney.setMoneyOutDate(rs.getString("moneyout_date"));
						useMoney.setRemarks(rs.getString("remarks"));
						list.add(useMoney);
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
			selectAll_count(page);
			return page;
		}
		
		//分页---->统计总条数
		public static void selectAll_count(PageModel<T_use_Money_Bean> page){
			List<T_use_Money_Bean> list=new ArrayList<T_use_Money_Bean>();
		     ResultSet rs=null;
		    Connection conn=DButil.getCon();
		    String sql="select count(id) from t_money_use";
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
	 
	 
	  public static T_use_Money_Bean queryById(String id){
		  ResultSet rs=null;
		  T_use_Money_Bean useMoney = new T_use_Money_Bean();
			Connection conn=DButil.getCon();
			String sql = "select * from t_money_use where id=?";
			PreparedStatement psmt = DButil.getPstm(conn, sql);
			try {
				psmt.setString(1,id);
				rs=psmt.executeQuery();
				while(rs.next()){
					
					useMoney.setId(rs.getInt("id"));
					useMoney.setAdmin(rs.getString("admin"));
					useMoney.setWorkDate(rs.getString("work_date"));
					useMoney.setMerchant(rs.getString("merchant"));
					useMoney.setName(rs.getString("name"));
					useMoney.setTel(rs.getString("tel"));
					useMoney.setHouldMoney(rs.getDouble("hould_money"));
					useMoney.setMoneyOut(rs.getDouble("moneyout"));
					useMoney.setMoneyOutDate(rs.getString("moneyout_date"));
					useMoney.setRemarks(rs.getString("remarks"));
				}
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DButil.close(conn);
			} 
		return useMoney;
		  
	  }
	  
	  public static int udateUseMoney(int id,String admin,String work_date,String merchant,String name,String tel,
			  String hould_money,String moneyout,String moneyout_date,String remarks){ 
		  int num=0;
		  Connection conn=DButil.getCon();
		  String sql="update t_money_use set admin=?,work_date=?,merchant=?,name=?,tel=?,hould_money=?,moneyout=?,moneyout_date=?,remarks=? where id=?";
		  PreparedStatement psmt = DButil.getPstm(conn, sql);
			try {
				psmt.setString(1, admin);
				psmt.setString(2, work_date);
				psmt.setString(3, merchant);
				psmt.setString(4, name);
				psmt.setString(5, tel);
				psmt.setString(6, hould_money);
				psmt.setString(7, moneyout);
				psmt.setString(8, moneyout_date);
				psmt.setString(9, remarks);
				psmt.setInt(10, id);
				num=psmt.executeUpdate();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		  
		  return num;
		  
	  }
	  public static int  deleteUseMoney (int id){
		  Connection conn=DButil.getCon();			
		  PreparedStatement pstmt = null;
		  int num=0;
		  String sql = "delete  from t_money_use where id=?";
			try {
				pstmt = conn.prepareStatement(sql);				
				pstmt.setInt(1, id);				
				num = pstmt.executeUpdate();							
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			 
		  return num;
	  }
	  
	  public static int addUseMoney(String admin,String work_date,String merchant,String name,String tel,
			  String hould_money,String moneyout,String moneyout_date,String remarks){
		  int num=0;
		  Connection conn=DButil.getCon();
		  String sql="insert into t_money_use(admin,work_date,merchant,name,tel,hould_money,moneyout,moneyout_date,remarks) values(?,?,?,?,?,?,?,?,?)";
		    PreparedStatement psmt=DButil.getPstm(conn, sql);
		    try {
		    	psmt.setString(1, admin);
				psmt.setString(2, work_date);
				psmt.setString(3, merchant);
				psmt.setString(4, name);
				psmt.setString(5, tel);
				psmt.setString(6, hould_money);
				psmt.setString(7, moneyout);
				psmt.setString(8, moneyout_date);
				psmt.setString(9, remarks);
				  	
				num=psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			finally{
				DButil.close(conn);
				DButil.psClose(psmt);
			}
		  return num;
		  
	  } 
}
