package com.jianguo.sql;

import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mxy on 2016/9/22.
 */
public class WebJobSql {

    //判断该手机号是否已注册
    public static T_user_login_Bean check_tel(String tel) {
        ResultSet rs = null;
        T_user_login_Bean t_user_login = new T_user_login_Bean();
        Connection conn = DButil.getCon();
        String sql = "select id,tel from t_user_login where tel=?";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, tel);
            rs = psmt.executeQuery();
            while (rs.next()) {
                t_user_login.setId(rs.getInt("id"));
                t_user_login.setTel(rs.getString("tel"));

            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return t_user_login;
    }
}