package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jianguo.bean.T_JobDetailBean;
import com.jianguo.bean.T_job_label_Bean;
import com.jianguo.bean.T_label_Bean;
import com.jianguo.bean.T_limit_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_welfare_Bean;
import com.jianguo.util.DButil;

public class T_JobDetail_Sql {
	
	public static T_JobDetailBean queryJobDetailById(String job_id){
		ResultSet rs=null;
		T_JobDetailBean jobDetail = new T_JobDetailBean();
		Connection conn=DButil.getCon();
		String sql = "select i.*,j.`status`,j.`mode`,j.merchant_id,j.reg_date, j.`name`,j.name_image,j.money,j.term,j.limit_sex,SUM(j.sum + j.girl_sum) AS finallySum,SUM(j.count + j.girl_count) AS nowCount "
					+"FROM t_job j,t_job_info i where j.id=i.job_id and i.job_id="+job_id;
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			//psmt.setString(1,job_id);
			rs=psmt.executeQuery();
			while(rs.next()){
				jobDetail.setId(rs.getInt("job_id"));
				jobDetail.setJob_name(rs.getString("name"));
				jobDetail.setJob_image(rs.getString("name_image"));
				//jobDetail.setJob_id(rs.getInt("job_id"));
				jobDetail.setAddress(rs.getString("address")+"");
				jobDetail.setStart_date(rs.getString("start_date")+"");
				jobDetail.setStop_date(rs.getString("stop_date")+"");
				jobDetail.setStart_time(rs.getString("start_time")+"");
				jobDetail.setStop_time(rs.getString("stop_time")+"");
				jobDetail.setSet_place(rs.getString("set_place")+"");
				jobDetail.setSet_time(rs.getString("set_time")+"");
				jobDetail.setWork_content(rs.getString("work_content")+"");
				jobDetail.setWork_require(rs.getString("work_require")+"");
				jobDetail.setReg_date(rs.getString("reg_date")+"");
				jobDetail.setFinallySum(rs.getInt("finallySum"));
				jobDetail.setNowCount(rs.getInt("nowCount"));
				jobDetail.setMerchant_id(rs.getInt("merchant_id"));
				jobDetail.setMerchantTel(rs.getString("tel"));
				jobDetail.setStatus(rs.getInt("status"));			
				jobDetail.setLimit_sex(rs.getInt("limit_sex")+"");
				
				if(rs.getInt("term")==0){//（0=月结，1=周结，2=日结，3=小时结，4=次，5=义工）
					jobDetail.setJob_money(rs.getInt("money")+"元/"+"月");
				
				}else if(rs.getInt("term")==1){
					jobDetail.setJob_money(rs.getInt("money")+"元/"+"周");
				
				}else if(rs.getInt("term")==2){
					jobDetail.setJob_money(rs.getInt("money")+"元/"+"天");
				
				}else if(rs.getInt("term")==3){
					jobDetail.setJob_money(rs.getInt("money")+"元/"+"小时");
				
				}else if(rs.getInt("term")==4){
					jobDetail.setJob_money(rs.getInt("money")+"元/"+"次");
				
				}else if(rs.getInt("term")==5){
					jobDetail.setJob_money("义工");
				
				}
				//0=月结，1=周结，2=日结，3=旅行）
				if(rs.getInt("mode")==0){
					jobDetail.setMode("月结");
				
				}else if(rs.getInt("mode")==1){
					jobDetail.setMode("周结");
				
				}else if(rs.getInt("mode")==2){
					jobDetail.setMode("日结");
				
				}else if(rs.getInt("mode")==3){
					jobDetail.setMode("旅行");
				
				}else if(rs.getInt("mode")==4){
					jobDetail.setMode("完工结");
				
				}			
				jobDetail.setOther(rs.getString("other")+"");
				jobDetail.setWork_content(rs.getString("work_content")+"");
				jobDetail.setWork_require(rs.getString("work_require")+"");
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return jobDetail;
	}
	//根据商家Id查询商家信息
	public static T_merchant_Bean queryMerInfoByjobId(int merId){
		ResultSet rs=null;
		T_merchant_Bean merInfo = new T_merchant_Bean();
		Connection conn=DButil.getCon();
		String sql = "select name,name_image,about,login_id from t_merchant  where  id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			psmt.setInt(1,merId);
			rs=psmt.executeQuery();
			while(rs.next()){
				merInfo.setName(rs.getString("name"));
				merInfo.setName_image(rs.getString("name_image"));
				merInfo.setAbout(rs.getString("about")+"");
				if(rs.getInt("login_id")==8220){
					merInfo.setLogin_id(13);
				}else if(rs.getInt("login_id")==8221){
					merInfo.setLogin_id(8);
				}else if(rs.getInt("login_id")==8222){
					merInfo.setLogin_id(8000);
				}else if(rs.getInt("login_id")==8223){
					merInfo.setLogin_id(13495);
				}else if(rs.getInt("login_id")==8224){
					merInfo.setLogin_id(7);
				}else if(rs.getInt("login_id")==10632){
					merInfo.setLogin_id(10632);
				}else{
					merInfo.setLogin_id(rs.getInt("login_id"));
				}
				
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return merInfo;
	}
	//查询是否有标签
	public static boolean checkLable(String jobId){
		Connection conn=DButil.getCon();
		boolean b = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from t_job_label where job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jobId);
			rs = pstmt.executeQuery();
			b = rs.next();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public static T_job_label_Bean queryLableId(String  jobId){
		ResultSet rs=null;
		T_job_label_Bean jobLable = new T_job_label_Bean();
		Connection conn=DButil.getCon();
		String sql = "select l.job_id,l.limits,l.welfare,l.label from t_job_label l where l.job_id=?";
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		String strWel="";
		String strLable="";
		String strlimt="";
		try {
			psmt.setString(1,jobId);
			rs=psmt.executeQuery();
			
			while(rs.next()){
				
				strWel=rs.getString("welfare");
				if(strWel.length()>0){
					strWel=strWel.substring(0, strWel.length()-1);
				}
				
				
				strLable=rs.getString("label");
				if(strLable.length()>0){
					strLable=strLable.substring(0, strLable.length()-1);
				}
				
				
				strlimt=rs.getString("limits");
				if(strlimt.length()>0){
					strlimt=strlimt.substring(0, strlimt.length()-1);
				}
			
				
				jobLable.setLabel(strLable);
				jobLable.setWelfare(strWel);
				jobLable.setLimits(strlimt);
				
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return jobLable;
	}
	//查询限制标签内容
	public static List<T_limit_Bean>  queryLimit(String  limitId){
		List<T_limit_Bean> list=new ArrayList<T_limit_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "SELECT * from t_limit where id in("+limitId+")";
		System.out.println(sql+"----------------------------------------");
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();		
			while(rs.next()){
				T_limit_Bean limitContent = new T_limit_Bean();
				limitContent.setLimit_name(rs.getString("limit_name"));
				list.add(limitContent);
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
	public static List<T_welfare_Bean>  queryWelfare(String  welfareId){
		List<T_welfare_Bean> list=new ArrayList<T_welfare_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "SELECT * from t_welfare where id in("+welfareId+")";
		System.out.println(sql+"----------------------------------------");
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();		
			while(rs.next()){
				T_welfare_Bean welCont = new T_welfare_Bean();
				welCont.setWelfare_name(rs.getString("welfare_name"));
				list.add(welCont);
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
	//查询兼职标签
	public static List<T_welfare_Bean>  queryLable(String  lableId){
		List<T_welfare_Bean> list=new ArrayList<T_welfare_Bean>();
		ResultSet rs=null;
		
		Connection conn=DButil.getCon();
		String sql = "SELECT * from t_label where id in("+lableId+")";
		System.out.println(sql+"----------------------------------------");
		PreparedStatement psmt = DButil.getPstm(conn, sql);
		try {
			rs=psmt.executeQuery();		
			while(rs.next()){
				T_welfare_Bean labelCont = new T_welfare_Bean();
				labelCont.setLableNme(rs.getString("label_name"));
				list.add(labelCont);
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
	
}
