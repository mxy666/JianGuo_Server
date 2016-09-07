package com.jianguo.servlet.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class T_WebhooksVerify_Servlet extends HttpServlet {

	
	/**
	 * Constructor of the object.
	 */
	public T_WebhooksVerify_Servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}
	

	/**
	 * The webHooksVerify . <br>
	 *
	 *  charge.succeeded 支付成功
		refund.succeeded 退款成功
		transfer.succeeded 企业付款成功
	 * 验证通知
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_WebhooksVerify_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		request.setCharacterEncoding("UTF8");
		Map<String,String> head=new HashMap<String, String>();
		 
	        //获取头部所有信息
	        Enumeration headerNames = request.getHeaderNames();
	        String key = "";
	        String value = "";
	        while (headerNames.hasMoreElements()) {
	            key += (String) headerNames.nextElement()+"---";
	            value += request.getHeader(key)+"---";
	            head.put(key, value);
	            System.out.println(key+" "+value);
	        }
	        // 获得 http body 内容
	        BufferedReader reader = request.getReader();
	        StringBuffer buffer = new StringBuffer();
	        String string;
	        while ((string = reader.readLine()) != null) {
	            buffer.append(string);
	            System.out.println("---"+buffer.toString());
	        }
	        reader.close();
	        // 解析异步通知数据
	        Event event = Webhooks.eventParse(buffer.toString());
	        if ("charge.succeeded".equals(event.getType())) {//charge.succeeded 支付成功
	            response.setStatus(200);
	        } else if ("refund.succeeded".equals(event.getType())) {//refund.succeeded 退款成功
	            response.setStatus(200);
	        }else if("transfer.succeeded".equals(event.getType())){//企业付款成功
	        	response.setStatus(200);
	        }else {
	            response.setStatus(500);
	        }
	        
	       /* try {
				WebhooksVertifyAction.runVerify();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        
	        params.put("Type", event.getType());			
	        params.put("buffer", buffer.toString());			
	        params.put("key", key);			
	        params.put("value", value);			
			params.put("message", "登录成功");			
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
	      
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
