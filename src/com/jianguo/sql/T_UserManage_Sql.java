package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jianguo.bean.T_Money_Bean;
import com.jianguo.bean.T_UserManage_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.util.DButil;
import com.jianguo.util.PageModel;

public class T_UserManage_Sql {
	
	
	
	
//用户管理分页	
public static List<T_UserManage_Bean> queryByPage(int currentPage,int pageSize){
		
		List<T_UserManage_Bean> list=new ArrayList<T_UserManage_Bean>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		
		/*String sql = "select a.id as login_id ,a.tel,a.city_id, b.sex,b.name,b.school,d.money from  t_user_login a,t_user_resume b,t_user_money d where a.id=b.login_id and b.login_id=d.login_id LIMIT 0,50";			 */
		String sql = "select a.id as login_id ,a.tel,a.city_id, b.sex,b.name,b.school,d.money from  t_user_login a,t_user_resume b,t_user_money d where a.id=b.login_id and b.login_id=d.login_id  "
						+" order by login_id desc LIMIT "+(currentPage-1)*pageSize+" , "+pageSize;

		//sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
				T_UserManage_Bean users = new T_UserManage_Bean();
				users.setLogin_id(rs.getInt("login_id"));
				users.setTel(rs.getString("tel"));			
				users.setSex((rs.getInt("sex")==1)?"男":"女" );
				users.setName(rs.getString("name")+"");
				users.setCity_id(rs.getString("city_id"));
				users.setSchool(rs.getString("school")+"");
				users.setMoney(rs.getDouble("money"));
				users.setCity_id(rs.getString("city_id"));
				users.setJob_id("<a href='javascript:void(0)' id='cate_name_color'  onclick='openJobWin()'>" +"钱包详情" + "</a>");
				users.setDetail("<a href='javascript:void(0)' id='cate_name_color'  onclick='openUserWin()'>" +"用户详情" + "</a>");
				list.add(users);
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
	//总记录数
public static int queryCount(String name,String tel){
	 List <T_user_resume_Bean>list=new ArrayList<T_user_resume_Bean>();
    ResultSet rs=null;
   Connection conn=DButil.getCon();
   StringBuffer str = new StringBuffer();
   String sql="select count(a.id)  from  t_user_login a,t_user_resume b,t_user_money d where a.id=b.login_id and b.login_id=d.login_id ";
   if(name!=null&&!name.equals("")){
		 str.append(" and b.name like'%"+name+"%' ");
		 
	 }
	 if(tel!=null&&!tel.equals("")){
		 str.append(" and a.tel ="+tel+" ");
		 	
	 }
	 sql=sql+str.toString();
	 System.out.println(sql+"----------------------------------------");
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
	return i;
}



public static List<T_UserManage_Bean> selectAll(){
		
		List<T_UserManage_Bean> list=new ArrayList<T_UserManage_Bean>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		
		/*String sql = "select a.id as login_id ,a.tel,a.city_id, b.sex,b.name,b.school,d.money from  t_user_login a,t_user_resume b,t_user_money d where a.id=b.login_id and b.login_id=d.login_id LIMIT 0,50";			 */
		String sql = "select a.id as login_id ,a.tel,a.city_id, b.sex,b.name,b.school,d.money from  t_user_login a,t_user_resume b,t_user_money d where a.id=b.login_id and b.login_id=d.login_id  "
						+" order by login_id desc LIMIT 0,300";

		//sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
				T_UserManage_Bean users = new T_UserManage_Bean();
				users.setLogin_id(rs.getInt("login_id"));
				users.setTel(rs.getString("tel"));			
				users.setSex((rs.getInt("sex")==1)?"男":"女" );
				users.setName(rs.getString("name")+"");
				users.setCity_id(rs.getString("city_id"));
				users.setSchool(rs.getString("school")+"");
				users.setMoney(rs.getDouble("money"));
				users.setCity_id(rs.getString("city_id"));
				users.setJob_id("<a href='javascript:void(0)' id='cate_name_color'  onclick='openJobWin()'>" +"钱包详情" + "</a>");
				users.setDetail("<a href='javascript:void(0)' id='cate_name_color'  onclick='openUserWin()'>" +"用户详情" + "</a>");
				list.add(users);
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
	
	//用户查询
	public static List<T_UserManage_Bean> queryAll(String name,String tel){
		
		List<T_UserManage_Bean> list=new ArrayList<T_UserManage_Bean>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		
		String sql = "select a.id as login_id ,a.tel,a.city_id, b.sex,b.name,b.school,d.money from  t_user_login a,t_user_resume b,t_user_money d where a.id=b.login_id and b.login_id=d.login_id";			 
		
		if(name!=null&&!name.equals("")){
			 str.append(" and b.name like'%"+name+"%' ");
			 
		 }
		 if(tel!=null&&!tel.equals("")){
			 str.append(" and a.tel ="+tel+" ");
			 	
		 }
		sql=sql+str.toString()+"  order by login_id desc";
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
				T_UserManage_Bean users = new T_UserManage_Bean();
				users.setLogin_id(rs.getInt("login_id"));
				users.setTel(rs.getString("tel"));			
				users.setSex((rs.getInt("sex")==1)?"男":"女" );
				users.setName(rs.getString("name")+"");
				users.setSchool(rs.getString("school")+"");
				users.setCity_id(rs.getString("city_id"));
				users.setMoney(rs.getDouble("money"));
				users.setCity_id(rs.getString("city_id"));
				users.setDetail("<a href='javascript:void(0)' id='cate_name_color'  onclick='openUserWin()'>" +" 查看详情" + "</a>");
				users.setJob_id("<a href='javascript:void(0)' id='cate_name_color'  onclick='openJobWin()'>" +" 查看兼职" + "</a>");
				list.add(users);
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
	
	//兼职查询
	public static List<T_job_Bean> queryJob(String loginId){
	List<T_job_Bean> list=new ArrayList<T_job_Bean>();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	ResultSet rs=null;
	Connection conn=DButil.getCon();
	StringBuffer str = new StringBuffer();
	
	String sql = "SELECT a.job_id,b.*,a.login_id  FROM t_enroll a,t_job b WHERE a.job_id = b.id";			 
	if(loginId!=null&&!loginId.equals("")){
		 str.append(" and a.login_id ="+loginId+" ");		 	
	 }
	
	sql=sql+str.toString();
	 System.out.println(sql+"----------------------------------------");
	 PreparedStatement psmt = DButil.getPstm(conn, sql);
	try {			
		rs=psmt.executeQuery();
		while(rs.next()){
			T_job_Bean users = new T_job_Bean();
			int status=rs.getInt("status");//（0=录取中，1=已招满，2=工作中，3=去结算，4=去评价，5=已完成，6=已下架）
			users.setId(rs.getInt("job_id"));
			users.setName(rs.getString("name"));	
			users.setAddress(rs.getString("address"));
			users.setMoney(rs.getDouble("money"));		
			users.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+"100"))));
			users.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("stop_date")+"100"))));
			if(status==0){
				users.setStatus1("录取中");
			}else if(status==1){
				users.setStatus1("已招满");
			}else if(status==2){
				users.setStatus1("工作中");
			}else if(status==3){
				users.setStatus1("去结算");
			}else if(status==4){
				users.setStatus1("去评价");
			}else if(status==5){
				users.setStatus1("已完成");
			}else if(status==6){
				users.setStatus1("已下架");
			}


			
			list.add(users);
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
	
	public static List<T_job_Bean> queryExportJob(String loginId){
		List<T_job_Bean> list=new ArrayList<T_job_Bean>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		
		String sql = "SELECT a.job_id,b.*,a.login_id,c.`name` AS userName FROM t_enroll a,t_job b,t_user_resume c WHERE a.job_id = b.id AND a.login_id=c.login_id";			 
		if(loginId!=null&&!loginId.equals("")){
			 str.append(" and a.login_id ="+loginId+" ");		 	
		 }
		
		sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_Bean users = new T_job_Bean();
				users.setId(rs.getInt("job_id"));
				users.setUserName(rs.getString("name"));	
				users.setName(rs.getString("userName"));	
				users.setAddress(rs.getString("address"));
				users.setMoney(rs.getDouble("money"));		
				users.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+"100"))));
				users.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("stop_date")+"100"))));
				
				list.add(users);
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
	//----------用户信息更新-------------------
	public static Boolean  updateUser(String login_id,int sexNum,String money,String school,String cityId){
		
		List<T_UserManage_Bean> list=new ArrayList<T_UserManage_Bean>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		String sql = "UPDATE t_user_login l,t_user_resume r,t_user_money m SET l.city_id=?,r.sex=?,r.school=?,m.money=? where l.id=r.login_id AND r.login_id=m.login_id AND l.id=?";		
		int num=0;
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
			
			try {
				psmt.setString(1, cityId);
				psmt.setInt(2, sexNum);
				psmt.setString(3, school);
				psmt.setString(4, money);
				psmt.setString(5, login_id);			
				num=psmt.executeUpdate();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return true;
	}
	//钱包情况查询  余额
	public static T_Money_Bean queryMoney(String loginId){
		T_Money_Bean money = new T_Money_Bean();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		
		//String sql = "select m.money ,SUM(o.money) cash,SUM(w.real_money ) wage from t_user_money m,t_user_moneyout o,t_wages w where m.login_id=o.login_id and o.login_id=w.login_id and w.job_id <>0  ";			 
		String sql = "select m.money from t_user_money m where 1=1 ";
		if(loginId!=null&&!loginId.equals("")){
			 str.append(" and m.login_id ="+loginId+" ");		 	
		 }
		
		sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
			
				money.setMoney(rs.getDouble("money"));	//余额
				//money.setWage(rs.getDouble("wage"));
				//money.setCash(rs.getDouble("cash"));			
				
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
			return money;
		}
	//用户钱包更新查询总工资
	public static T_Money_Bean queryWage(String loginId){
		T_Money_Bean money = new T_Money_Bean();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		
		//String sql = "select m.money ,SUM(o.money) cash,SUM(w.real_money ) wage from t_user_money m,t_user_moneyout o,t_wages w where m.login_id=o.login_id and o.login_id=w.login_id and w.job_id <>0  ";			 
		String sql = "select SUM(w.real_money ) wage from t_wages w where w.job_id <>0  ";
		if(loginId!=null&&!loginId.equals("")){
			 str.append(" and w.login_id ="+loginId+" ");		 	
		 }
		
		sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
			
				
				money.setWage(rs.getDouble("wage"));//总工资
				//money.setCash(rs.getDouble("cash"));			
				
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
			return money;
		}	
	//用户钱包更新查询提现金额
	public static T_Money_Bean queryOutCash(String loginId){
		T_Money_Bean money = new T_Money_Bean();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		StringBuffer str = new StringBuffer();
		
		//String sql = "select m.money ,SUM(o.money) cash,SUM(w.real_money ) wage from t_user_money m,t_user_moneyout o,t_wages w where m.login_id=o.login_id and o.login_id=w.login_id and w.job_id <>0  ";			 
		String sql = "select SUM(o.money) cash from t_user_moneyout o  where 1=1  ";
		if(loginId!=null&&!loginId.equals("")){
			 str.append(" and o.login_id ="+loginId+" ");		 	
		 }
		
		sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
			
				
				//money.setWage(rs.getDouble("wage"));//总工资
				money.setCash(rs.getDouble("cash"));//提现金额			
				
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
			return money;
		}
	
	//钱包更新
public static Boolean  updateMoney(String login_id,String money,String remark){
		
		List<T_UserManage_Bean> list=new ArrayList<T_UserManage_Bean>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date(); 
		ResultSet rs=null;
		Connection conn=DButil.getCon();
		Statement st = null;
		StringBuffer str = new StringBuffer();
		String sql = "update t_user_money set money="+money+" where login_id="+login_id;
		
		String addSql="insert INTO t_wages(login_id,job_id,hould_money,real_money,remarks,reg_time) values ("
										  +login_id+",0,0,"+money+",'"+remark+"','"+sdf.format(now)+"')";
		int result=0;
		int addResult=0;
			try {
		      st = conn.createStatement();
			  result = st.executeUpdate(sql);
			  addResult=st.executeUpdate(addSql);
			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
				DButil.close(conn);
			}
			 if(result>0&&addResult>0){
					return true;
				}else{
					return false;
				}
	}
//查询用户昵称为空的人
public static List<T_UserManage_Bean> queryNullUser(){
	
	List<T_UserManage_Bean> list=new ArrayList<T_UserManage_Bean>();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	ResultSet rs=null;
	Connection conn=DButil.getCon();
	StringBuffer str = new StringBuffer();
	
	/*String sql = "select a.id as login_id ,a.tel,a.city_id, b.sex,b.name,b.school,d.money from  t_user_login a,t_user_resume b,t_user_money d where a.id=b.login_id and b.login_id=d.login_id LIMIT 0,50";			 */
	String sql = "select * from t_user_info i where i.nickname is  NULL";

	//sql=sql+str.toString();
	 System.out.println(sql+"----------------------------------------");
	 PreparedStatement psmt = DButil.getPstm(conn, sql);
	try {			
		rs=psmt.executeQuery();
		while(rs.next()){
			T_UserManage_Bean users = new T_UserManage_Bean();
			users.setLogin_id(rs.getInt("login_id"));
			
			list.add(users);
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
//更新用户昵称为空的人 兼果+login_id
public static Boolean  updateNullName(String  loginId){
	
	//List<T_UserManage_Bean> list=new ArrayList<T_UserManage_Bean>();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date(); 
	ResultSet rs=null;
	Connection conn=DButil.getCon();
	Statement st = null;
	StringBuffer str = new StringBuffer();
	int result=0;

		String sql = "update  t_user_info  set nickname='兼果"+loginId+"'  where login_id="+loginId;
	System.out.println("---------"+sql);	
		
			try {
		      st = conn.createStatement();
			  result = st.executeUpdate(sql);
			
			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
				DButil.close(conn);
			}
	 if(result>0){
			return true;
		}else{
			return false;
		}
}
}
