package com.jianguo.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.util.DButil;
import com.mysql.jdbc.Connection;

public class T_jobQuery_sql {
private static List<T_job_Bean> list_obj;
	
public String name;
	public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


	public T_jobQuery_sql() {
		queryAll(name);
	}
	
	
	public static List<T_job_Bean> queryAll(String name){
		list_obj = new ArrayList<T_job_Bean>();
		try {
			 Connection conn=(Connection) DButil.getCon();
			Statement stmt = conn.createStatement();
		
			 StringBuffer str = new StringBuffer(); 
		     String sql="select name ,id,address,start_date,stop_date,money from t_job where 1=1";		 
		     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			 if(name!=null&&!name.equals("")){
				 str.append(" and name like'%"+name+"%' ");			 
			 }		
			 sql=sql+str.toString();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				T_job_Bean stru = new T_job_Bean();
				
				stru.setName(rs.getString(1));
				stru.setId(rs.getInt(2));
				stru.setAddress(rs.getString(3));
				stru.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString(4)+""+"100"))));
				stru.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString(5)+""+"100"))));
				stru.setMoney(rs.getDouble(6));
				list_obj.add(stru);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list_obj;
	}
	/*public static List<T_job_Bean> 	queryAll(){
		 Connection conn=(Connection) DButil.getCon();
		 List <T_job_Bean>list=new ArrayList<T_job_Bean>();
		 ResultSet rs=null;
		 StringBuffer str = new StringBuffer(); 
	     String sql="select * from t_job where 1=1";		 
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 if(name!=null&&!name.equals("")){
			 str.append(" and name like'%"+name+"%' ");			 
		 }		
		 sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
		 
		try {			
			rs=psmt.executeQuery();
			while(rs.next()){
				T_job_Bean t_job = new T_job_Bean();
				t_job.setId(rs.getInt("id"));
				t_job.setName(rs.getString("name")+"");				
				t_job.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+""+"100"))));
				t_job.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+""+"100"))));
				t_job.setAddress(rs.getString("address")+"");
				t_job.setMoney(rs.getDouble("money"));

				list.add(t_job);
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
	}*/


	public List<T_job_Bean> getList_obj() {
		return list_obj;
	}


	public void setList_obj(List<T_job_Bean> listObj) {
		list_obj = listObj;
	}
	
/*	public List<T_job_Bean> findStructureByProerties(String id,String structure){
		slist = new ArrayList<BaseStructureBean>();
		try {
			Connection conn = Connecter.creatConnection();
			Statement stmt = conn.createStatement();
			String condition="";
			if(id!=null)
				condition+= " and id="+id;
			if(structure!=null)
				condition+=" and structure='"+structure+"'";
			String sql="select * from base_structure where 1=1"+condition;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				BaseStructureBean stru = new BaseStructureBean();
				stru.setId(rs.getInt(1));
				stru.setStructure(rs.getString(2));
				slist.add(stru);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return slist;
	}*/

	
}
