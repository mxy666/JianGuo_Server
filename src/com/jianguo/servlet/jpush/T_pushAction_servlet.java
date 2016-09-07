package com.jianguo.servlet.jpush;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.servlet.jpush.action.PushAction;

public class T_pushAction_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_pushAction_servlet() {
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

		System.out.println("---T_pushAction_servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String pushWay=request.getParameter("pushWay");
		String message=request.getParameter("message");		
		String cityId=request.getParameter("cityId");
		String school=request.getParameter("school");
		String tel=request.getParameter("tel");
		String sex=request.getParameter("sex");
		String type=request.getParameter("type");
		String html_url=request.getParameter("html_url");
		String job_name=request.getParameter("job_name");
		
//		song_money

			request.setAttribute("cityId", cityId);
			request.setAttribute("school", school);
			request.setAttribute("tel", tel);
			request.setAttribute("sex", sex);
			PrintWriter out = response.getWriter(); 
			out.flush();out.println("<script>");
			out.println("alert('ÍÆËÍ³É¹¦');");
			out.println("history.back();");
			out.println("</script>");	
			System.out.println("------"+tel+"-------------------------");
			if(job_name == null || job_name == ""){
				PushAction.push(pushWay,message,cityId,school,tel,sex,type,html_url,"","");
			}else{
				String s = new String(job_name);   
				String a[] = s.split(","); 
				PushAction.push(pushWay,message,cityId,school,tel,sex,type,html_url,a[0],a[1]);
//				PushAction.push(pushWay,message,cityId,school,tel,sex,"0",html_url,"","");
			}
		
	}

}
