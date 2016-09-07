package com.jianguo.servlet.jpush;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_push_new_Sql;
import com.jianguo.util.PageModel;

public class T_to_push_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_to_push_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---T_to_push_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
		
		String cityId=request.getParameter("cityId");
		String school=request.getParameter("school");
		String tel=request.getParameter("tel");
		String sex=request.getParameter("sex");
		String job_namesss=request.getParameter("job_namesss");
		
		String name =request.getParameter("name");
		String city =request.getParameter("city");
		int pageNo =Integer.parseInt(request.getParameter("pageNo"));
		PageModel page = new PageModel();
		if(pageNo != 0 ){
			page.setPageNo(pageNo);
		}
		page = (PageModel) T_push_new_Sql.queryAllT(page,cityId,school,tel,sex);
		request.setAttribute("page", page);
		
		List<T_user_resume_Bean>pushObj =T_push_new_Sql.queryAll(cityId,school,tel,sex);
/*		T_user_resume_Bean obj=new T_user_resume_Bean();
		if(cityId.equals("全部")){
			obj.setCityId("全部");			
		}else if(cityId.equals("北京")){
			obj.setCityId("北京");		
		}else if(cityId.equals("三亚")){
			obj.setCityId("三亚");		
		}else if(cityId.equals("海南")){
			obj.setCityId("海南");		
		}else if(cityId.equals("西安")){
			obj.setCityId("西安");		
		}else{
			obj.setCityId("全部");		
		}
		pushObj.set(0, obj);*/
//		for(int i=0;i<pushObj.size(); i++){
//			if(pushObj.get(i).getSex()==0){
//				pushObj.get(i).setSexnew("女");
//				System.out.println(pushObj.get(i).getSex()+"---"+pushObj.get(i).getSexnew());
//			}else if(pushObj.get(i).getSex()==1){
//				System.out.println(pushObj.get(i).getSex()+"==="+pushObj.get(i).getSexnew());
//				pushObj.get(i).setSexnew("男");
//			}
//			pushObj.add(e);
//		}
		
		//List<T_job_Bean> list_t_job = T_job_Sql.queryAll(name);

		
//		if(cityId != "" || cityId != null){
			request.setAttribute("cityId", cityId);
//		}else if(school != "" || school != null){
			request.setAttribute("school", school);
			System.out.println(school+"--------");
//		}else if(tel != "" || tel != null){
			request.setAttribute("tel", tel);
//		}else if(sex != "" || sex != null){
			request.setAttribute("sex", sex);
//		}
			request.setAttribute("job_namesss", job_namesss);
		
		request.setAttribute("pushObj", pushObj);
		//request.setAttribute("list_t_job", list_t_job);
		if(city.equals("boss")){
			request.getRequestDispatcher("interaction\\push.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("interaction\\pushForCity.jsp").forward(request, response);
		}
		
		
	}

	

	
}
