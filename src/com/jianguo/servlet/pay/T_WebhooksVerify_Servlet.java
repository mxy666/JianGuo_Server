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
	 *  charge.succeeded ֧���ɹ�
		refund.succeeded �˿�ɹ�
		transfer.succeeded ��ҵ����ɹ�
	 * ��֤֪ͨ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_WebhooksVerify_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		request.setCharacterEncoding("UTF8");
		Map<String,String> head=new HashMap<String, String>();
		 
	        //��ȡͷ��������Ϣ
	        Enumeration headerNames = request.getHeaderNames();
	        String key = "";
	        String value = "";
	        while (headerNames.hasMoreElements()) {
	            key += (String) headerNames.nextElement()+"---";
	            value += request.getHeader(key)+"---";
	            head.put(key, value);
	            System.out.println(key+" "+value);
	        }
	        // ��� http body ����
	        BufferedReader reader = request.getReader();
	        StringBuffer buffer = new StringBuffer();
	        String string;
	        while ((string = reader.readLine()) != null) {
	            buffer.append(string);
	            System.out.println("---"+buffer.toString());
	        }
	        reader.close();
	        // �����첽֪ͨ����
	        Event event = Webhooks.eventParse(buffer.toString());
	        if ("charge.succeeded".equals(event.getType())) {//charge.succeeded ֧���ɹ�
	            response.setStatus(200);
	        } else if ("refund.succeeded".equals(event.getType())) {//refund.succeeded �˿�ɹ�
	            response.setStatus(200);
	        }else if("transfer.succeeded".equals(event.getType())){//��ҵ����ɹ�
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
			params.put("message", "��¼�ɹ�");			
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
