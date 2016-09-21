package com.jianguo.shixiseng;

import com.jianguo.bean.ShiXiSheng;
import com.jianguo.util.DButil;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/9/21.
 */
@WebServlet(name = "GetUserInfo")
public class GetUserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("验证码日志信息开始!");
        logger.info("QuickSmsServlet!");
        String token = request.getParameter("tokenId");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static ShiXiSheng getUserInfo(String token) throws SQLException {
        Connection conn = DButil.getCon();
        PreparedStatement pstmt;
        ResultSet rs;
        ShiXiSheng shiXiSheng = new ShiXiSheng();
        String sql = "select * from t_user_login login LEFT JOIN t_user_info info ON login.id=info.login_id WHERE qqwx_token=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, token);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            shiXiSheng.setTokenId(rs.getString("qqwx_token"));
            shiXiSheng.setNickName(rs.getString("nickname"));
            shiXiSheng.setRealName(rs.getString("name"));
            shiXiSheng.setAge(rs.getString("age"));
            shiXiSheng.setTel(rs.getString("tel"));
            shiXiSheng.setCity(rs.getString(""));
            shiXiSheng.setBirthday(rs.getString(""));
//            shiXiSheng.setGender(rs.get);
            shiXiSheng.setSchool(rs.getString("school"));
        }
        pstmt.close();
        conn.close();
        return shiXiSheng;
    }



}
