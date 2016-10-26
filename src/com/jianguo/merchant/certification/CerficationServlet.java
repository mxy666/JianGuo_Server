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
     * @api {post} CerficationServlet/ 个人商家信息审核
     * @apiName CerficationServlet
     * @apiGroup certification
     *
     * @apiParam {String} tel User phone
     * @apiParam {String} token User token
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  登录成功！
     * @apiSuccess {String} tel  18101050625
     * @apiSuccess {int} loginId  10
     * @apiSuccess {String} password  ""
     * @apiSuccess {String} token  0a4148a32160ebfa78eff622357bda4e
     * @apiSuccess {String} permissions  0 (商家权限（1是外部商家，2是个人商户，0是内部）)
     * @apiSuccess {int} payStatus  0  (支付密码是否设置 0未设置1已设置)
     * @apiSuccess {int} resumeStatus  0 (商家信息是否填写 0未填写 1已填写审核中 2审核通过)
     * @apiSuccess {String} tel  18101050625
     * @apiSuccess {String} tel  18101050625
     *
     * @apiError (Error 400) {String} code 400
     * @apiError (Error 400) {String} message 服务器忙，请稍后重试
     * @apiError (Error 400) {String} codeError 代码错误详情（供内部测试，查找问题使用）
     * @apiError (Error 401) {String} code 401
     * @apiError (Error 401) {String} message 参数错误请检查
     * @apiError (Error 402) {String} code 402
     * @apiError (Error 402) {String} message 签名校验错误
     * @apiError (Error 403) {String} code 403
     * @apiError (Error 403) {String} message 手机号码不存在
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("商家端审核日志信息开始!");
        logger.info("CerficationServlet!");
        String merStr =request.getParameter("merchantInfo");
        String token =request.getParameter("token");
        String merchantId =request.getParameter("merchantId");
        String loginId =request.getParameter("loginId");
        try {
            if (merStr==null||merStr.equals("")||merStr==null||merStr.equals("")) {
                HttpClientUtil.pushResponse(response,"401","参数错误请检查！");
                return;
            }
            //判断token是否匹配
                if (!LoginSql.checkToken(loginId,token)) {
                    HttpClientUtil.pushResponse(response,"402","签名校验错误！");
                    return;
                }

            Map map = new HashMap();
            Gson gson=new Gson();
            MerchantInfo merchantInfo = gson.fromJson(merStr, MerchantInfo.class);
            if (null==merchantInfo.getCity()||merchantInfo.getCity().equals("")) {
                HttpClientUtil.pushResponse(response,"400","没有选择城市");
            }else  if (null==merchantInfo.getNickName()||merchantInfo.getNickName().equals("")) {
                HttpClientUtil.pushResponse(response,"400","没有填写昵称");
            }else  if (null==merchantInfo.getCompanyAddress()||merchantInfo.getCompanyAddress().equals("")) {
                HttpClientUtil.pushResponse(response,"400","没有填写公司详细地址");
            }else  if (null==merchantInfo.getUserImage()||merchantInfo.getUserImage().equals("")) {
                HttpClientUtil.pushResponse(response,"400","没有上传用户头像");
            }else  if (null==merchantInfo.getContactName()||merchantInfo.getContactName().equals("")) {
                HttpClientUtil.pushResponse(response,"400","没有填写联系人姓名");
            }else  if (null==merchantInfo.getContactPhone()||merchantInfo.getContactPhone().equals("")) {
                HttpClientUtil.pushResponse(response,"400","没有填写联系人电话");
            }else  if (null==merchantInfo.getEmail()||merchantInfo.getEmail().equals("")) {
                HttpClientUtil.pushResponse(response,"400","没有填写联系人邮箱");
            }
            CerficationSql.updateMerStatus(merchantId,merchantInfo);
            PrintWriter pw = response.getWriter();
            map.put("data",merchantInfo);
            //生成token返回给客户端
            map.put("code", "200");
            map.put("message", "登录成功！");
            String str = gson.toJson(map);
            pw.write(str);
            pw.flush();
            pw.close();
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","服务器忙，请稍后再试！",e.getMessage());
            e.printStackTrace();
        }

    }
}
