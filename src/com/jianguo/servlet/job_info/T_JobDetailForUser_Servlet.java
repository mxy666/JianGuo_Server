package com.jianguo.servlet.job_info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jianguo.bean.T_JobDetailBean;
import com.jianguo.bean.T_job_label_Bean;
import com.jianguo.bean.T_label_Bean;
import com.jianguo.bean.T_limit_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_welfare_Bean;
import com.jianguo.sql.T_JobDetail_Sql;
import com.jianguo.sql.T_attent_Sql;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.util.Frequently;

public class T_JobDetailForUser_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_JobDetailForUser_Servlet() {
		super();
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 用户端详细信息显示
	 * 	http://192.168.1.135/JianGuo_Server/T_JobDetailForUser_Servlet?only=FEE526E885B457B9E342E0E2D9DFAA2E&job_id=206
	 * http://101.200.195.147/JianGuo_Server/T_JobDetailForUser_Servlet?only=012F5970C74FE7878833C258D30441E5&job_id=193
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---T_JobDetailForUser_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String job_id =request.getParameter("job_id");
//		String merchant_id =request.getParameter("merchant_id");
//		String alike =request.getParameter("alike");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			T_JobDetailBean  job_detail=T_JobDetail_Sql.queryJobDetailById(job_id);//兼职信息
			//是否报名"1"是自己取消所以要判断
			if(!T_enroll_Sql.check_login_id_job_id(login_id, job_id) || T_enroll_Sql.check_login_id_job_id2(login_id, job_id, "1")){
				job_detail.setIsEnroll("0");
			}else{
				job_detail.setIsEnroll("1");//报名
			}
			boolean b = T_attent_Sql.check_login_id_collection(login_id, job_id);
			
			if(b == true){
				job_detail.setIsFavorite("1");//收藏
			}else{
				job_detail.setIsFavorite("0");
			}
			T_merchant_Bean merchantInfo=T_JobDetail_Sql.queryMerInfoByjobId(job_detail.getMerchant_id());//商家信息
			job_detail.setMerchant_name(merchantInfo.getName());
			job_detail.setMerchant_image(merchantInfo.getName_image());
			job_detail.setMerchant_about(merchantInfo.getAbout());
			job_detail.setMerchant_LogId(merchantInfo.getLogin_id());
			
			T_job_label_Bean jobLable=new T_job_label_Bean();
			List<String> welfare=new ArrayList<String>();
			List<String> limit=new ArrayList<String>();
			List<String> lable=new ArrayList<String>();
			//根据jobId查询标签
			boolean lableFlag=T_JobDetail_Sql.checkLable(job_id);
			if(lableFlag){
				jobLable=T_JobDetail_Sql.queryLableId(job_id);
				
				List<T_limit_Bean> limitContList=new ArrayList<T_limit_Bean>();
				List<T_welfare_Bean> welfareList=new ArrayList<T_welfare_Bean>();
				List<T_welfare_Bean> lableList=new ArrayList<T_welfare_Bean>();
				
				if(!jobLable.getLimits().equals("")&&jobLable.getLimits()!=null){
					limitContList=T_JobDetail_Sql.queryLimit(jobLable.getLimits());					
					for(int i=0;i<limitContList.size();i++){
						limit.add(i, limitContList.get(i).getLimit_name());
					}
					job_detail.setLimit(limit);
				}
				
				if(!jobLable.getWelfare().equals("")&&jobLable.getWelfare()!=null){
					welfareList=T_JobDetail_Sql.queryWelfare(jobLable.getWelfare());			
					for(int i=0;i<welfareList.size();i++){
						welfare.add(i, welfareList.get(i).getWelfare_name());
					}
					job_detail.setWelfare(welfare);
				}
				
				//List<T_limit_Bean> limitContList=T_JobDetail_Sql.queryLimit(jobLable.getLimits());
				if(!jobLable.getLabel().equals("")&&jobLable.getLabel()!=null){
					lableList=T_JobDetail_Sql.queryLable(jobLable.getLabel());	

					for(int i=0;i<lableList.size();i++){
						lable.add(i, lableList.get(i).getLableName());
					}
					if(job_detail.getWelfare()!=null&&job_detail.getWelfare().size()>0){
						job_detail.getWelfare().addAll(lable);
					}
					
				}
				
				
			
				
			
				
			
				
			
				
			}
			//T_job_label_Bean jobLable=T_JobDetail_Sql.queryLableId(job_id);
			
			
			Map map = new HashMap();
			params.put("data", job_detail);
			params.put("message", "兼职详情查看成功");
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();

			//------------------访问限制--------开始----------------------
		}else{
			params.put("message", "无效访问");
			params.put("code", "404");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
		//------------------访问限制--------结束----------------------
	}

}
