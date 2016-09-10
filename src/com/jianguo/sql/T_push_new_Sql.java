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

import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_platForm_Push_Bean;
import com.jianguo.bean.T_push_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.util.DButil;
import com.jianguo.util.PageModel;

public class T_push_new_Sql {
	 public static List<T_user_resume_Bean> queryAll(String cityId,String school,String tel,String sex){
		 Connection conn=DButil.getCon();
		// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 List <T_user_resume_Bean>list=new ArrayList<T_user_resume_Bean>();
		 ResultSet rs=null;
		 StringBuffer str = new StringBuffer(); 
	     String sql="select a.id,b.name,b.sex, a.tel,b.school,a.city_id from t_user_login a,t_user_resume b where b.login_id=a.id ";		 

	     if(cityId!=null&&!cityId.equals("")&&!cityId.equals("all")){
			
			 if(cityId.equals("BJ")){
				 cityId="北京";
				 //str.append(" and a.city_id like'%"+cityId+"%' "); 
			 }/*else if( cityId.equals("HN")){
				 cityId="海南";
				 //str.append(" and a.city_id like'%"+cityId+"%' "); 
			 }*/else if( cityId.equals("HK")){
				 cityId="海口";
				 //str.append(" and a.city_id like'%"+cityId+"%' "); 
			 }else if( cityId.equals("SY")){
				 cityId="三亚";
				 //str.append(" and a.city_id like'%"+cityId+"%' "); 
			 }else if( cityId.equals("HZ")){
				 cityId="杭州";
				 //str.append(" and a.city_id like'%"+cityId+"%' "); 
			 }else if( cityId.equals("XA")){
				 cityId="西安";
				// str.append(" and a.city_id like'%"+cityId+"%' "); 
			 }
				 
			 str.append(" and a.city_id like'%"+cityId+"%' ");
				
			 
		 }
		 if(tel!=null&&!tel.equals("")){
			 String []phoneStr=tel.split(",");
			 String tels=buffer(phoneStr).toString();
			 str.append(" and a.tel in ('"+tels+")");
			 
		 }
		 if(school!=null&&!school.equals("")){
			 str.append(" and b.school like'%"+school+"%' ");
			 
		 }
		 /*if(){
			0=女，1=男 
		 }*/
		 int sexNum=0;
		 if(sex!=null&&!sex.equals("")&&!sex.equals("全部")&&!sex.equals("all")){
			 if(sex.equals("0")){
				 sexNum=0;
				 
			 }else if(sex.equals("1")){
				 sexNum=1;
				 
			 }
			 
			 str.append(" and b.sex='"+sexNum+"'");
			 
		 }
		 sql=sql+str.toString();
		 System.out.println(sql+"----------------------------------------");
		 PreparedStatement psmt = DButil.getPstm(conn, sql);
			try {
				rs=psmt.executeQuery();
				while(rs.next()){
					T_user_resume_Bean pushObj = new T_user_resume_Bean();
					pushObj.setId(rs.getInt("id"));
					pushObj.setSex(rs.getInt("sex"));
					pushObj.setCityId(rs.getString("city_id"));
					pushObj.setSchool(rs.getString("school"));
					pushObj.setName(rs.getString("name"));
					pushObj.setTel(rs.getString("tel"));

				
					list.add(pushObj);
				}
					/* ResultSetMetaData md = rs.getMetaData();
					  int columnCount = md.getColumnCount();
				        while (rs.next()) {
				            Map<String, Object> rowData = new HashMap<String, Object>();
				            for (int i = 1; i <= columnCount; i++) {
				                rowData.put(md.getColumnName(i), rs.getObject(i));
				            }
				           // list.add(rowData);
				        }*/
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
		public static PageModel<T_user_resume_Bean> queryAllT(PageModel page,String cityId,String school,String tel,String sex){
		    Connection conn=DButil.getCon();
		    List <T_user_resume_Bean>list=new ArrayList<T_user_resume_Bean>();
			 ResultSet rs=null;
			 StringBuffer str = new StringBuffer();
			 String sql="select a.id, b.name,b.sex, a.tel,b.school,a.city_id from t_user_login a,t_user_resume b where b.login_id=a.id ";
			 
			 
			/* if(cityId!=null&&!cityId.equals("")&&!cityId.equals("全部")){
				 str.append(" and a.city_id like'%"+cityId+"%' ");
				 
			 }*/
			 if(cityId!=null&&!cityId.equals("")&&!cityId.equals("all")){
					
				 if(cityId.equals("BJ")){
					 cityId="北京";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }/*else if( cityId.equals("HN")){
					 cityId="海南";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }*/else if( cityId.equals("HK")){
					 cityId="海口";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }else if( cityId.equals("SY")){
					 cityId="三亚";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }else if( cityId.equals("HZ")){
					 cityId="杭州";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }else if( cityId.equals("XA")){
					 cityId="西安";
					// str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }
					 
				 str.append(" and a.city_id like'%"+cityId+"%' ");
					
				 
			 }
			
			
			 if(tel!=null&&!tel.equals("")){
				 String []phoneStr=tel.split(",");
				 String tels=buffer(phoneStr).toString();
				 str.append(" and a.tel in ('"+tels+")");
				 
			 }
			 if(school!=null&&!school.equals("")){
				 str.append(" and b.school like'%"+school+"%' ");
				 
			 }
			 /*if(){
				0=女，1=男 
			 }*/
			 int sexNum=0;
		/*	 if(sex!=null&&!sex.equals("")&&!sex.equals("全部")){
				 if(sex.equals("女")){
					 sexNum=0;
					 
				 }else if(sex.equals("男")){
					 sexNum=1;
					 
				 }
				 
				 str.append(" and b.sex='"+sexNum+"'");
				 
			 }*/
			 if(sex!=null&&!sex.equals("")&&!sex.equals("全部")&&!sex.equals("all")){
				 if(sex.equals("0")){
					 sexNum=0;
					 
				 }else if(sex.equals("1")){
					 sexNum=1;
					 
				 }
				 
				 str.append(" and b.sex='"+sexNum+"'");
				 
			 }
			 sql=sql+str.toString()+"order by a.id desc limit "+page.getFirstResult()+","+page.getPageSize();;
			 PreparedStatement psmt = DButil.getPstm(conn, sql);
				try {
					rs=psmt.executeQuery();
					
					while(rs.next()){
						T_user_resume_Bean pushObj = new T_user_resume_Bean();
						pushObj.setId(rs.getInt("id"));
						pushObj.setSex(rs.getInt("sex"));
						pushObj.setCityId(rs.getString("city_id"));
						pushObj.setSchool(rs.getString("school"));
						pushObj.setName(rs.getString("name"));
						pushObj.setTel(rs.getString("tel"));

					
						list.add(pushObj);
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
			selectAll_count(page,cityId,school,tel,sex);
			return page;
		}
		
		//分页---->统计总条数
		public static void selectAll_count(PageModel<T_user_resume_Bean> page,String cityId,String school,String tel,String sex){
			 List <T_user_resume_Bean>list=new ArrayList<T_user_resume_Bean>();
		     ResultSet rs=null;
		    Connection conn=DButil.getCon();
		    StringBuffer str = new StringBuffer();
		    String sql="select count(a.id) from t_user_login a,t_user_resume b where b.login_id=a.id";
		    if(cityId!=null&&!cityId.equals("")&&!cityId.equals("all")){
				
				 if(cityId.equals("BJ")){
					 cityId="北京";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }else if( cityId.equals("HK")){
					 cityId="海口";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }/*else if( cityId.equals("HN")){
					 cityId="海南";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }*/else if( cityId.equals("SY")){
					 cityId="三亚";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }else if( cityId.equals("HZ")){
					 cityId="杭州";
					 //str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }else if( cityId.equals("XA")){
					 cityId="西安";
					// str.append(" and a.city_id like'%"+cityId+"%' "); 
				 }
					 
				 str.append(" and a.city_id like'%"+cityId+"%' ");
					
				 
			 }
		    if(tel!=null&&!tel.equals("")){
		    	 String []phoneStr=tel.split(",");
				 String tels=buffer(phoneStr).toString();
				 str.append(" and a.tel in ('"+tels+")");
				 	
			 }
			 if(school!=null&&!school.equals("")){
				 str.append(" and b.school like'%"+school+"%' ");
				 
			 }
			 /*if(){
				0=女，1=男 
			 }*/
			 int sexNum=0;
			 if(sex!=null&&!sex.equals("")&&!sex.equals("全部")&&!sex.equals("all")){
				 if(sex.equals("0")){
					 sexNum=0;
					 
				 }else if(sex.equals("1")){
					 sexNum=1;
					 
				 }
				 
				 str.append(" and b.sex='"+sexNum+"'");
				 
			 }
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
		
		public static List<T_job_Bean> queryAll(){
			List<T_job_Bean> list=new ArrayList<T_job_Bean>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			ResultSet rs=null;
			Connection conn=DButil.getCon();
			StringBuffer str = new StringBuffer();
			
			String sql = "select* from t_job where 1=1   order by id DESC  limit 1,20 ";			 
			 sql=sql+str.toString();
			 System.out.println(sql+"----------------------------------------");
			 PreparedStatement psmt = DButil.getPstm(conn, sql);
			try {			
				rs=psmt.executeQuery();
				while(rs.next()){
					T_job_Bean t_job = new T_job_Bean();
					t_job.setId(rs.getInt("id"));
					t_job.setName(rs.getString("name")+"");
					t_job.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+"100"))));
					t_job.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("stop_date")+"100"))));
					t_job.setAddress(rs.getString("address")+"");
					t_job.setMoney(rs.getDouble("money"));
					if(rs.getInt("city_id")==1){
						t_job.setCity_id_name("三亚");
					}else if(rs.getInt("city_id")==2){
						t_job.setCity_id_name("海口");
					}else if(rs.getInt("city_id")==3){
						t_job.setCity_id_name("北京");
					}else if(rs.getInt("city_id")==4){
						t_job.setCity_id_name("杭州");
					}else if(rs.getInt("city_id")==5){
						t_job.setCity_id_name("西安");
					}
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
		}
		public static List<T_job_Bean> select_all(String name,String city){
			List<T_job_Bean> list=new ArrayList<T_job_Bean>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			ResultSet rs=null;
			Connection conn=DButil.getCon();
			StringBuffer str = new StringBuffer();
			int cityId=0;
			String sql = "select * from t_job where 1=1";
			
			if(name!=null&&!name.equals("")){
				 str.append(" and name like'%"+name+"%' ");				 
			 }
			
			if(city!=null&&!city.equals("")&&!city.equals("all")){
				
				 if(city.equals("BJ")){
					 cityId=3;					
				 }else if( city.equals("HK")){
					 cityId=2;					
				 }else if( city.equals("SY")){
					 cityId=1;					
				 }else if( city.equals("HZ")){
					 cityId=4;					
				 }else if( city.equals("XA")){
					 cityId=5;
				 }					 
				 str.append(" and city_id ="+cityId+" ");					
				 
			 }
			 sql=sql+str.toString()+" order by id DESC";
			 System.out.println(sql+"----------------------------------------");
			 PreparedStatement psmt = DButil.getPstm(conn, sql);
			try {
			
				rs=psmt.executeQuery();
				while(rs.next()){
					T_job_Bean t_job = new T_job_Bean();
					t_job.setId(rs.getInt("id"));
					t_job.setName(rs.getString("name")+"");					
					t_job.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+"100"))));
					t_job.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("stop_date")+"100"))));
					t_job.setAddress(rs.getString("address")+"");
					t_job.setMoney(rs.getDouble("money"));
					if(rs.getInt("city_id")==1){
						t_job.setCity_id_name("三亚");
					}else if(rs.getInt("city_id")==2){
						t_job.setCity_id_name("海口");
					}else if(rs.getInt("city_id")==3){
						t_job.setCity_id_name("北京");
					}else if(rs.getInt("city_id")==4){
						t_job.setCity_id_name("杭州");
					}else if(rs.getInt("city_id")==5){
						t_job.setCity_id_name("西安");
					}
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
		}
	 //推送记录查询
		/* public static List<T_push_Bean> queryRecord(String tel,String name){
			 Connection conn=DButil.getCon();
			// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			 List <T_push_Bean>list=new ArrayList<T_push_Bean>();
			 ResultSet rs=null;
			 StringBuffer str = new StringBuffer(); 
		     String mersql="select a.*,b.tel,c.name from t_push a,t_user_login b  ,t_user_resume c  where a.login_id=b.id AND b.id=c.login_id";

		   
			 
		     if(tel!=null&&!tel.equals("")){
				 str.append(" and tel ='"+tel+"' ");
				 	
			 }
			    if(name!=null&&!name.equals("")){
					 str.append(" and c.name like'%"+name+"%' ");
					 
				 }
				mersql=mersql+str.toString();
		
			 System.out.println(mersql+"-------------------");
			 PreparedStatement psmt = DButil.getPstm(conn, mersql);
				try {
					rs=psmt.executeQuery();
					while(rs.next()){
						T_push_Bean record = new T_push_Bean();
						record.setLogin_id(rs.getInt("login_id"));
						record.setTitle(rs.getString("title"));
						record.setName(rs.getString("name"));
						record.setTel(rs.getString("tel"));
						record.setContent(rs.getString("content"));
						record.setJpush(rs.getInt("jpush"));
						record.setTime(rs.getString("time"));					
						list.add(record);
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
//推送分页
		public static PageModel<T_platForm_Push_Bean> queryRecordT(PageModel<T_platForm_Push_Bean> page){
			Connection conn=DButil.getCon();
			 List <T_platForm_Push_Bean>list=new ArrayList<T_platForm_Push_Bean>();
			 ResultSet rs=null;
			 StringBuffer str = new StringBuffer(); 

		     String sql="select * from t_platform_push  ";		 
			sql=sql+str.toString()+" limit " +page.getFirstResult()+","+page.getPageSize();
		    
			// mersql=mersql+str.toString();

			 System.out.println(sql+"-------------------");
			 PreparedStatement psmt = DButil.getPstm(conn, sql);
				try {
					rs=psmt.executeQuery();
					while(rs.next()){
						T_platForm_Push_Bean record = new T_platForm_Push_Bean();
						record.setSum(rs.getInt("sum"));
						if(rs.getInt("type")==0){//短信1极光0
							record.setType("极光");
						}else{
							record.setType("短信");
						}
						record.setMessage(rs.getString("message"));
						record.setTime(rs.getString("reg_time"));					
						list.add(record);
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
			public static void selectAllRecord_count(PageModel<T_platForm_Push_Bean> page){
				 List <T_platForm_Push_Bean>list=new ArrayList<T_platForm_Push_Bean>();
			     ResultSet rs=null;
			    Connection conn=DButil.getCon();
			    StringBuffer str = new StringBuffer();
			    String sql="select count(id) from t_platform_push";		
			 
				sql=sql+str.toString()+" limit " +page.getFirstResult()+","+page.getPageSize();
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
		//商家推送
			public static int add(String tel,String message,String time){
				   int num=0;
				   Connection conn=DButil.getCon();
				   String sql="insert into t_customer(tel,message,reg_time) values(?,?,?)";
				    PreparedStatement pst=DButil.getPstm(conn, sql);
				    try {
				    	pst.setString(1, tel);
				    	pst.setString(2, message);
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
			 public static StringBuffer buffer(String []array){
			    	StringBuffer sb = new StringBuffer();
			    	for (String s : array) {
						sb.append(s);
						sb.append("','");
				}
				if (sb.charAt(sb.length() - 1) == '\'') {					
						sb.deleteCharAt(sb.length() - 1);
						if(sb.charAt(sb.length() - 1) == ','){
							sb.deleteCharAt(sb.length() - 1);
						}
				}
			    	return sb;
			    }
}
