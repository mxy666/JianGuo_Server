package com.jianguo.merchant.login;

import com.google.gson.Gson;
import com.jianguo.bean.MerchantInfo;
import com.jianguo.merchant.mersql.LoginSql;
import com.jianguo.merchant.mersql.TelCodeSql;
import com.jianguo.merchant.utils.CommonUtils;
import com.jianguo.merchant.utils.HttpClientUtil;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/10/21.
 */
@WebServlet(name = "RegisterServlet",urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    /**
     * @api {post} RegisterServlet/ �̼�ע��
     * @apiName RegisterServlet
     * @apiGroup login
     *
     * @apiParam {String} tel User phone
     * @apiParam {String} smsCode User smsCode
     * @apiParam {String} password User password
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  ��¼�ɹ���
     * @apiError (Error 400) {String} code 400
     * @apiError (Error 400) {String} message ������æ�����Ժ�����
     * @apiError (Error 400) {String} codeError ����������飨���ڲ����ԣ���������ʹ�ã�
     * @apiError (Error 401) {String} code 401
     * @apiError (Error 401) {String} message ������������
     * @apiError (Error 402) {String} code 402
     * @apiError (Error 402) {String} message ��֤�����
     * @apiError (Error 403) {String} code 403
     * @apiError (Error 403) {String} message ��֤���ѹ���
     *  @apiError (Error 405) {String} code 405
     * @apiError (Error 405) {String} message �ֻ������Ѿ�����
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=GBK");
//        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("�̼Ҷ˵�¼��־��Ϣ��ʼ!");
        logger.info("RegisterServlet!");
        String smsCode =request.getParameter("smsCode");
        String smsTel =request.getParameter("tel");
        String tel ="jg"+smsTel;
        String password =request.getParameter("password");
        Map map = new HashMap();
        Gson gson=new Gson();
        PrintWriter pw = response.getWriter();
        try {
            //�ж��ֻ��ź���֤���Ƿ�Ϊ��
            if (smsCode==null||smsCode.equals("")||tel==null||tel.equals("")||password==null||password.equals("")) {
                HttpClientUtil.pushResponse(response,"401","�����������飡");
                return;
            }
            //�ж���֤���Ƿ�ƥ��
            if (!LoginSql.checkVerificationCode(tel,smsCode)) {
                HttpClientUtil.pushResponse(response,"402","��֤�����");
                return;
            }
            //�ж���֤���Ƿ񳬹���Ч��
            if (TelCodeSql.checkExpiryDate(tel,System.currentTimeMillis())) {
                HttpClientUtil.pushResponse(response,"403","��֤���ѹ��ڣ������»�ȡ��");
                return;
            }


            String token = CommonUtils.makeToken(tel);
            //�����ڸ��û���ע���������
            if (!LoginSql.checkRegister(tel)) {
                int loginId = LoginSql.insertMerchant(tel,password, token);
                LoginSql.insertMerchantInfo(loginId);
                //����token���ظ��ͻ���
                map.put("code", "200");
                map.put("message", "ע��ɹ���");
                String str = gson.toJson(map);
                pw.write(str);
                pw.flush();
                pw.close();
            }else {
                //�����û���ʾ�Ѿ����ڸ��ֻ���
                //����token���ظ��ͻ���
                map.put("code", "405");
                map.put("message", "�ֻ����Ѿ����ڣ�");
                String str = gson.toJson(map);
                pw.write(str);
                pw.flush();
                pw.close();
                return;
            }
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","������æ�����Ժ����ԣ�",e.getMessage());
            return;
        }


    }

}
