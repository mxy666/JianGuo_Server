package com.jianguo.merchant.login;

import com.google.gson.Gson;
import com.jianguo.bean.MerchantInfo;
import com.jianguo.merchant.mersql.LoginSql;
import com.jianguo.merchant.utils.CommonUtils;
import com.jianguo.merchant.utils.HttpClientUtil;
import com.qiniu.util.Auth;

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
@WebServlet(name = "PasswordLoginServlet",urlPatterns = "/PasswordLoginServlet")
public class PasswordLoginServlet extends HttpServlet {
    /**
     * @api {post} PasswordLoginServlet/ �����¼
     * @apiName PasswordLoginServlet
     * @apiGroup login
     *
     * @apiParam {String} tel User phone
     * @apiParam {String} password User password
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  ��¼�ɹ���
     * @apiSuccess {String} tel  18101050625
     * @apiSuccess {int} loginId  10
     * @apiSuccess {String} password  ""
     * @apiSuccess {String} token  0a4148a32160ebfa78eff622357bda4e
     * @apiSuccess {String} permissions  0 (�̼�Ȩ�ޣ�1���ⲿ�̼ң�2�Ǹ����̻���0���ڲ���)
     * @apiSuccess {int} payStatus  0  (֧�������Ƿ����� 0δ����1������)
     * @apiSuccess {int} resumeStatus  0 (�̼���Ϣ�Ƿ���д 0δ��д 1����д����� 2���ͨ��)
     * @apiSuccess {String} tel  18101050625
     * @apiSuccess {String} tel  18101050625
     *
     * @apiError (Error 400) {String} code 400
     * @apiError (Error 400) {String} message ������æ�����Ժ�����
     * @apiError (Error 400) {String} codeError ����������飨���ڲ����ԣ���������ʹ�ã�
     * @apiError (Error 401) {String} code 401
     * @apiError (Error 401) {String} message ������������
     * @apiError (Error 402) {String} code 402
     * @apiError (Error 402) {String} message �������
     * @apiError (Error 403) {String} code 403
     * @apiError (Error 403) {String} message �ֻ����벻����
     * @apiError (Error 405) {String} code 405
     * @apiError (Error 405) {String} message ��δ�������룬���ŵ�¼�û�
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("�̼Ҷ˵�¼��־��Ϣ��ʼ!");
        logger.info("LoginServlet!");
        String password =request.getParameter("password");
        String tel ="jg"+request.getParameter("tel");
        Map map = new HashMap();
        Gson gson=new Gson();
        PrintWriter pw = response.getWriter();
        MerchantInfo merchantInfo;
        try {
            //�ж��ֻ��ź���֤���Ƿ�Ϊ��
            if (password==null||password.equals("")||tel==null||tel.equals("")) {
                HttpClientUtil.pushResponse(response,"401","�����������飡");
                return;
            }
            if (!LoginSql.checkRegister(tel)) {
                HttpClientUtil.pushResponse(response,"403","�˺Ų����ڣ���ע�ᣡ");
                return;
            }
            //�жϵ�ǰ�����Ƿ�Ϊ�գ�����˻����ڲ�������Ϊ�գ�˵��Ϊ���ſ��ٵ�¼����δ��������
            if (LoginSql.checkExistedPassword(tel,password).equals("")) {
                HttpClientUtil.pushResponse(response,"405","������δ���ã�");
                return;
            }
            //�ж������Ƿ�ƥ��
            if (!LoginSql.checkVerificationPassword(tel,password)) {
                HttpClientUtil.pushResponse(response,"402","�������");
                return;
            }
            String newToken = CommonUtils.makeToken(tel);
             //���ڸ����û�token
             LoginSql.updateToken(tel, newToken);
            //��ȡ�û���Ϣ,���µ�½ʱ��
            merchantInfo = LoginSql.getMerchantInfo(tel);
            LoginSql.updateLoginTime(merchantInfo.getLoginId());
            Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");
            //String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//һ��
            String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7��
            merchantInfo.setQiniuToken(qiniu_token);
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","������æ�����Ժ����ԣ�",e.getMessage());
            return;
        }

        map.put("data",merchantInfo);
        //����token���ظ��ͻ���
        map.put("code", "200");
        map.put("message", "��¼�ɹ���");
        String str = gson.toJson(map);
        pw.write(str);
        pw.flush();
        pw.close();
    }
}