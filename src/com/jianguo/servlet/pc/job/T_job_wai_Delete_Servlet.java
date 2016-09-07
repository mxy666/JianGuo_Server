package com.jianguo.servlet.pc.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_job_wai_Bean;
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
import com.jianguo.util.MD5Util;

public class T_job_wai_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_wai_Delete_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_job_wai_Delete_Servlet?only=51EDF82FC91AD97CBBB608BCDF5AAA26&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243:8080/T_job_wai_Delete_Servlet?only=51EDF82FC91AD97CBBB608BCDF5AAA26&tel=181ssss012222s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243:8080/user_agreement.jsp
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_wai_Delete_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String id =request.getParameter("id");
		String job_id =request.getParameter("job_id");

		T_job_wai_Sql.delete_id(id);
		List<T_job_wai_Bean> list_t_job_wai = T_job_wai_Sql.select_job_id(job_id);

		List<T_job_wai_Bean> list_t_job_wai2 = new ArrayList<T_job_wai_Bean>();
		for (int i = 0; i < list_t_job_wai.size(); i++) {
			T_job_wai_Bean t_job_wai = list_t_job_wai.get(i);
			if(t_job_wai.getSex() == 1){
				t_job_wai.setS_sex("ÄÐ");
			}else if(t_job_wai.getSex() == 0){
				t_job_wai.setS_sex("Å®");
			}
			list_t_job_wai2.add(t_job_wai);
		}

		request.setAttribute("job_id", job_id);
		request.setAttribute("list_t_job_wai", list_t_job_wai2);
		request.getRequestDispatcher("job_wai_list.jsp").forward(request, response);

	}
}
