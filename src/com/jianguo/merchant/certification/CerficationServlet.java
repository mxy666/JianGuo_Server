package com.jianguo.merchant.certification;

import com.google.gson.Gson;
import com.jianguo.bean.MerchantInfo;
import com.jianguo.merchant.mersql.CerficationSql;
import com.jianguo.merchant.mersql.LoginSql;
import com.jianguo.merchant.utils.CommonUtils;
import com.jianguo.merchant.utils.HttpClientUtil;
import com.jianguo.sql.MerchantSql;
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
@WebServlet(name = "CerficationServlet",urlPatterns = "/CerficationServlet")
public class CerficationServlet extends HttpServlet {
    /**
     * @api {post} CerficationServlet/ �����̼���Ϣ���
     * @apiName CerficationServlet
     * @apiGroup certification
     *
     * @apiParam {String} tel User phone
     * @apiParam {String} token User token
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
     * @apiError (Error 402) {String} message ǩ��У�����
     * @apiError (Error 403) {String} code 403
     * @apiError (Error 403) {String} message �ֻ����벻����
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("�̼Ҷ������־��Ϣ��ʼ!");
        logger.info("CerficationServlet!");
        String merStr =request.getParameter("merchantInfo");
        String token =request.getParameter("token");
        String merchantId =request.getParameter("merchantId");
        String loginId =request.getParameter("loginId");
        try {
            if (merStr==null||merStr.equals("")||merStr==null||merStr.equals("")) {
                HttpClientUtil.pushResponse(response,"401","�����������飡");
                return;
            }
            //�ж�token�Ƿ�ƥ��
                if (!LoginSql.checkToken(loginId,token)) {
                    HttpClientUtil.pushResponse(response,"402","ǩ��У�����");
                    return;
                }

            Map map = new HashMap();
            Gson gson=new Gson();
            MerchantInfo merchantInfo = gson.fromJson(merStr, MerchantInfo.class);
            if (null==merchantInfo.getCity()||merchantInfo.getCity().equals("")) {
                HttpClientUtil.pushResponse(response,"400","û��ѡ�����");
            }else  if (null==merchantInfo.getNickName()||merchantInfo.getNickName().equals("")) {
                HttpClientUtil.pushResponse(response,"400","û����д�ǳ�");
            }else  if (null==merchantInfo.getCompanyAddress()||merchantInfo.getCompanyAddress().equals("")) {
                HttpClientUtil.pushResponse(response,"400","û����д��˾��ϸ��ַ");
            }else  if (null==merchantInfo.getUserImage()||merchantInfo.getUserImage().equals("")) {
                HttpClientUtil.pushResponse(response,"400","û���ϴ��û�ͷ��");
            }else  if (null==merchantInfo.getContactName()||merchantInfo.getContactName().equals("")) {
                HttpClientUtil.pushResponse(response,"400","û����д��ϵ������");
            }else  if (null==merchantInfo.getContactPhone()||merchantInfo.getContactPhone().equals("")) {
                HttpClientUtil.pushResponse(response,"400","û����д��ϵ�˵绰");
            }else  if (null==merchantInfo.getEmail()||merchantInfo.getEmail().equals("")) {
                HttpClientUtil.pushResponse(response,"400","û����д��ϵ������");
            }
            CerficationSql.updateMerStatus(merchantId,merchantInfo);
            PrintWriter pw = response.getWriter();
            map.put("data",merchantInfo);
            //����token���ظ��ͻ���
            map.put("code", "200");
            map.put("message", "��¼�ɹ���");
            String str = gson.toJson(map);
            pw.write(str);
            pw.flush();
            pw.close();
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","������æ�����Ժ����ԣ�",e.getMessage());
            e.printStackTrace();
        }

    }
}
