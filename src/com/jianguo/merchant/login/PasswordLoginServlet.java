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
     * @api {post} PasswordLoginServlet/ 密码登录
     * @apiName PasswordLoginServlet
     * @apiGroup login
     *
     * @apiParam {String} tel User phone
     * @apiParam {String} password User password
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
     * @apiError (Error 402) {String} message 密码错误
     * @apiError (Error 403) {String} code 403
     * @apiError (Error 403) {String} message 手机号码不存在
     * @apiError (Error 405) {String} code 405
     * @apiError (Error 405) {String} message 尚未设置密码，短信登录用户
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("商家端登录日志信息开始!");
        logger.info("LoginServlet!");
        String password =request.getParameter("password");
        String tel ="jg"+request.getParameter("tel");
        Map map = new HashMap();
        Gson gson=new Gson();
        PrintWriter pw = response.getWriter();
        MerchantInfo merchantInfo;
        try {
            //判断手机号和验证码是否为空
            if (password==null||password.equals("")||tel==null||tel.equals("")) {
                HttpClientUtil.pushResponse(response,"401","参数错误请检查！");
                return;
            }
            if (!LoginSql.checkRegister(tel)) {
                HttpClientUtil.pushResponse(response,"403","账号不存在，请注册！");
                return;
            }
            //判断当前密码是否为空，如果账户存在并且密码为空，说明为短信快速登录，尚未设置密码
            if (LoginSql.checkExistedPassword(tel,password).equals("")) {
                HttpClientUtil.pushResponse(response,"405","密码尚未设置！");
                return;
            }
            //判断密码是否匹配
            if (!LoginSql.checkVerificationPassword(tel,password)) {
                HttpClientUtil.pushResponse(response,"402","密码错误！");
                return;
            }
            String newToken = CommonUtils.makeToken(tel);
             //存在更新用户token
             LoginSql.updateToken(tel, newToken);
            //获取用户信息,更新登陆时间
            merchantInfo = LoginSql.getMerchantInfo(tel);
            LoginSql.updateLoginTime(merchantInfo.getLoginId());
            Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");
            //String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
            String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
            merchantInfo.setQiniuToken(qiniu_token);
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","服务器忙，请稍后再试！",e.getMessage());
            return;
        }

        map.put("data",merchantInfo);
        //生成token返回给客户端
        map.put("code", "200");
        map.put("message", "登录成功！");
        String str = gson.toJson(map);
        pw.write(str);
        pw.flush();
        pw.close();
    }
}
