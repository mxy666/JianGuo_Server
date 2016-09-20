package com.jianguo.servlet.login;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mxy on 2016/9/19.
 */

public class LoginFilter implements Filter {

    public static final String logout_page = "/login.html";
    public void destroy(){

    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String currentURL = request.getRequestURI();
        String ctxPath = request.getContextPath();
        //������Ŀ����ʱ����ҳ�浱ǰ·��
        String targetURL = currentURL.substring(ctxPath.length());
        HttpSession session = request.getSession(false);
        //�Ե�ǰҳ������жϣ������ǰҳ�治Ϊ��¼ҳ��
        if(!("/login.html".equals(targetURL))){
            System.out.println("1"+targetURL+"ctxPath:"+ctxPath+"currentURL:"+currentURL);
            //�ڲ�Ϊ��½ҳ��ʱ���ٽ����жϣ�������ǵ�½ҳ��Ҳû��session����ת����¼ҳ�棬
            if(session == null || session.getAttribute("userName") == null){
                response.sendRedirect(logout_page);
                return;
            }else{
                //�����ʾ��ȷ����ȥѰ����һ��������������ڣ������������ҳ����ת
                filterChain.doFilter(request, response);
                return;
            }
        }else{
            //�����ʾ�����ǰҳ���ǵ�½ҳ�棬��ת����½ҳ��
            filterChain.doFilter(request, response);
            return;
        }

    }
    public void init(FilterConfig filterConfig)throws ServletException{

    }


}
