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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/9/9.
 */
public class LoginServlet extends HttpServlet {

    private MerchantInfo merchantInfo;

    /**
     * @api {post} LoginServlet/ ���ٵ�¼
     * @apiName LoginServlet
     * @apiGroup login
     *
     * @apiParam {String} tel Users phone
     * @apiParam {String} tel Users smsCode
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
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("�̼Ҷ˵�¼��־��Ϣ��ʼ!");
        logger.info("LoginServlet!");
        String smsCode =request.getParameter("smsCode");
        String tel =request.getParameter("tel");
        Map map = new HashMap();
        Gson gson=new Gson();
        PrintWriter pw = response.getWriter();
        try {
            //�ж��ֻ��ź���֤���Ƿ�Ϊ��
            if (smsCode==null||smsCode.equals("")||tel==null||tel.equals("")) {
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
                     LoginSql.insertMerchant(tel,token);
                }else {
                    //���ڸ����û�token
                    LoginSql.updateToken(tel,token);
                }
            //��ȡ�û���Ϣ
            merchantInfo = LoginSql.getMerchantInfo(tel);
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","������æ�����Ժ����ԣ�",e.getMessage());
            return;
        }

        map.put("merchantInfo",merchantInfo);
        //����token���ظ��ͻ���
        map.put("code", "200");
        map.put("message", "��¼�ɹ���");
        String str = gson.toJson(map);
        pw.write(str);
        pw.flush();
        pw.close();
    }

}
