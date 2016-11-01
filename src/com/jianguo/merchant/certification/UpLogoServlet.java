package com.jianguo.merchant.certification;

import com.google.gson.Gson;
import com.jianguo.bean.MerchantInfo;
import com.jianguo.merchant.mersql.CerficationSql;
import com.jianguo.merchant.mersql.LoginSql;
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
@WebServlet(name = "UpLogoServlet",urlPatterns = "/UpLogoServlet")
public class UpLogoServlet extends HttpServlet {
    /**
     * @api {post} UpLogoServlet/ 上传头像
     * @apiName UpLogoServlet
     * @apiGroup certification
     * @apiParam {String} loginId User loginId
     * @apiParam {String} token User token
     *  @apiParam {String} merchantId User merchantId
     *  @apiParam {String} logoUrl User 用户头像
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  审核提交成功！

     * @apiError (Error 400) {String} code 400
     * @apiError (Error 400) {String} message 服务器忙，请稍后重试
     * @apiError (Error 400) {String} codeError 代码错误详情（供内部测试，查找问题使用）
     * @apiError (Error 401) {String} code 401
     * @apiError (Error 401) {String} message 参数错误请检查
     * @apiError (Error 402) {String} code 402
     * @apiError (Error 402) {String} message 签名校验错误
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("UpLogoServlet!");
        String logo_url =request.getParameter("logoUrl");
        String token =request.getParameter("token");
        String merchantId =request.getParameter("merchantId");
        String loginId =request.getParameter("loginId");
        try {
            if (logo_url==null||logo_url.equals("")||logo_url==null||logo_url.equals("")) {
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
             CerficationSql.updateMerLogo(merchantId, logo_url);
            PrintWriter pw = response.getWriter();
            //生成token返回给客户端
            map.put("code", "200");
            map.put("message", "上传成功！");
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
