package com.jianguo.servlet.finance;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jianguo.bean.AddUser;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_wai_Bean;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_enroll_limit_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_record_Sql;
import com.jianguo.sql.T_job_wai_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.DButil;
import com.jianguo.util.MD5Util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public InsertUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Logger logger = Logger.getLogger("log");
		logger.info("日志信息开始!");
		logger.info("InsertUserServlet!");
		String user =request.getParameter("user");
		String job_id =request.getParameter("job_id");
		Map map = new HashMap();
		Gson gson=new Gson();
		String code="200";
		PrintWriter pw = response.getWriter();
		if (user==null||user.equals("")||job_id==null||job_id.equals("")){
			code="500";
			map.put("message", "参数错误请检查");
			map.put("code", code);
			String str = gson.toJson(map);
			pw.write(str);
			pw.flush();
			pw.close();
			return;
		}
		List<AddUser> userList;
		Type type = new TypeToken<ArrayList<AddUser>>() {}.getType();
		userList= gson.fromJson(user,type);
		T_job_Bean jobInfo = T_job_Sql.select_id(job_id);
		List<AddUser> addUsers = InsertUserDB(userList, jobInfo, job_id,logger);
		if (addUsers.size()>0){
			code="201";
			String users = gson.toJson(addUsers);
			map.put("list_user",users);
			map.put("message", "补加人员部分录入成功");
			map.put("code", code);
		}else {
			code="200";
			map.put("message", "补加人员录入成功");
			map.put("code", code);
		}
		String str = gson.toJson(map);
		pw.write(str);
		pw.flush();
		pw.close();

	}
/**
*InsertUserDB
 * *@author invinjun
*created at 2016/9/6 16:38
 * @param users
 * @param jobInfo
 * @param logger
 * @param job_id
 */
	private List<AddUser> InsertUserDB(List<AddUser> users, T_job_Bean jobInfo,  String job_id,Logger logger) {
		//时间信息初始化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String registerTime = sdf.format(new java.util.Date());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String limitDate = sdf2.format(new java.util.Date());
		List<AddUser> alreadyUsers=new ArrayList<AddUser>();
		for (AddUser user : users) {
			//如果已经存在该用户
			if (T_user_login_Sql.check_tel(user.getTel())) {
				alreadyUsers.add(user);
				break;
			}
			try {
				long random =(long)((Math.random()*9+1)*100000);
				String psCode = random+"";
				String str_psd = MD5Util.MD5(psCode);
				//插入login表数据、userinfo表、resume表、money表、limit限制表
				T_user_login_Sql.insert_tel(user.getTel(), str_psd, "1", "1", "0", "0", "0");
				T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(user.getTel());
				T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(),user.getName(), "http://v3.jianguojob.com/moren.png","无","0","0","0", registerTime, registerTime);
				T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(),user.getName(), "http://v3.jianguojob.com/moren.png","无","","0","0","0","","","","","");
				T_user_money_Sql.insert(t_user_login.getId()+"", "0", "8.88", "0", "0", "0", "0", "0");
				T_enroll_limit_Sql.insert(t_user_login.getId()+"", "0", limitDate);
				//报名表加入并将状态设置为9 已完成状态,最后一个参数区分是否为长期
				T_enroll_Sql.insert(t_user_login.getId()+"",job_id , "9",registerTime,"0",jobInfo.getMax() == 0?"0":"1");
				//兼职记录表插入数据
				T_job_record_Sql.insert(t_user_login.getId()+"", "1", "0");
				//添加外录人员信息表
				T_job_wai_Sql.insert(job_id, user.getTel(), user.getName(), "0", "无");
				T_job_Sql.update_count(job_id);

			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e!=null?e.getMessage():"sql错误");
			}
		}
		return alreadyUsers;
	}

}
