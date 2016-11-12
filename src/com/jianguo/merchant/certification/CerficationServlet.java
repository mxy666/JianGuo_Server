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
     * @apiParam {String} loginId User loginId
     * @apiParam {String} token User token
     *  @apiParam {String} merchantId User merchantId
     *   @apiParam {String} merchantInfo �̼������Ϣjson����
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  ����ύ�ɹ���

     * @apiError (Error 400) {String} code 400
     * @apiError (Error 400) {String} message ������æ�����Ժ�����
     * @apiError (Error 400) {String} codeError ����������飨���ڲ����ԣ���������ʹ�ã�
     * @apiError (Error 401) {String} code 401
     * @apiError (Error 401) {String} message ������������
     * @apiError (Error 402) {String} code 402
     * @apiError (Error 402) {String} message ǩ��У�����
     * @apiError (Error 403) {String} code 403
     * @apiError (Error 403) {String} message ȱ�������Ϣ
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
//                if (!LoginSql.checkToken(loginId,token)) {
//                    HttpClientUtil.pushResponse(response,"402","ǩ��У�����");
//                    return;
//                }

            Map map = new HashMap();
            Gson gson=new Gson();
            MerchantInfo merchantInfo = gson.fromJson(merStr, MerchantInfo.class);
            if (null==merchantInfo.getCity()||merchantInfo.getCity().equals("")) {
                HttpClientUtil.pushResponse(response,"403","û��ѡ�����");
            }else  if (null==merchantInfo.getCompanyAddress()||merchantInfo.getCompanyAddress().equals("")) {
                HttpClientUtil.pushResponse(response,"403","û����д��˾��ϸ��ַ");
            }else  if (null==merchantInfo.getUserImage()||merchantInfo.getUserImage().equals("")) {
                HttpClientUtil.pushResponse(response,"403","û���ϴ��û�ͷ��");
            }else  if (null==merchantInfo.getContactName()||merchantInfo.getContactName().equals("")) {
                HttpClientUtil.pushResponse(response,"403","û����д��ϵ������");
            }else  if (null==merchantInfo.getContactPhone()||merchantInfo.getContactPhone().equals("")) {
                HttpClientUtil.pushResponse(response,"403","û����д��ϵ�˵绰");
            }else  if (null==merchantInfo.getEmail()||merchantInfo.getEmail().equals("")) {
                HttpClientUtil.pushResponse(response,"403","û����д��ϵ������");
            }
            int status = CerficationSql.updateMerInfo(merchantId, merchantInfo);
                CerficationSql.updateMerStatus(loginId, String.valueOf(merchantInfo.getPermissions()));
             if (status==0){
                 HttpClientUtil.pushResponse(response,"403","û�и��˻�");
             }
            PrintWriter pw = response.getWriter();
            //����token���ظ��ͻ���
            map.put("code", "200");
            map.put("message", "�ύ��˳ɹ���");
            String str = gson.toJson(map);
            pw.write(str);
            pw.flush();
            pw.close();
        } catch (SQLException e) {
            logger.info("CerficationServlet! error="+e.getMessage());
            HttpClientUtil.pushResponse(response,logger,"400","������æ�����Ժ����ԣ�",e.getMessage());
            e.printStackTrace();
        }

    }
}
