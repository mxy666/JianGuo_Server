package com.jianguo.sql;

import com.jianguo.bean.T_wages_Bean;
import com.jianguo.util.DButil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Created by mxy on 2016/9/7.
 */
public class FinanceMerSql {

    //查询本月商家发放的工资
    public static T_wages_Bean  queryMonTotalMoney(String merId){
        T_wages_Bean money = new T_wages_Bean();
        ResultSet rs=null;
        Connection conn= DButil.getCon();
        String sql = "select SUM(real_money) money from t_wages where job_id in (select id from t_job  where merchant_id=? and date_format(regedit_time,'%Y-%m')=date_format(now(),'%Y-%m'))";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {

            psmt.setString(1,merId);
            rs=psmt.executeQuery();
            while(rs.next()){
                money.setReal_money(rs.getInt("money"));

            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            DButil.close(conn);
        }
        return money;
    }

    //查询今日的工资
    public static T_wages_Bean  queryDayTotalMoney(String merId){
        T_wages_Bean money = new T_wages_Bean();
        ResultSet rs=null;
        Connection conn= DButil.getCon();
        String sql = "select SUM(real_money) money from t_wages where job_id in (select id from t_job  where merchant_id=?  and to_days(regedit_time) = to_days(now()))";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {

            psmt.setString(1,merId);
            rs=psmt.executeQuery();
            while(rs.next()){
                money.setReal_money(rs.getInt("money"));
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            DButil.close(conn);
        }
        return money;
    }

    //查询累计发放工资
    public static T_wages_Bean  queryTotalMoney(String merId){
        T_wages_Bean money = new T_wages_Bean();
        ResultSet rs=null;
        Connection conn= DButil.getCon();
        String sql = "select SUM(real_money) money from t_wages where job_id in (select id from t_job  where merchant_id=? )";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {

            psmt.setString(1,merId);
            rs=psmt.executeQuery();
            while(rs.next()){
                money.setReal_money(rs.getInt("money"));
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            DButil.close(conn);
        }
        return money;
    }

    //查询累计发放工资
    public static T_wages_Bean  queryDayTotaluser(String merId){
        T_wages_Bean money = new T_wages_Bean();
        ResultSet rs=null;
        Connection conn= DButil.getCon();
        String sql = "select SUM(real_money) money from t_wages where job_id in (select id from t_job  where merchant_id=? )";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {

            psmt.setString(1,merId);
            rs=psmt.executeQuery();
            while(rs.next()){
                money.setReal_money(rs.getInt("money"));
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            DButil.close(conn);
        }
        return money;
    }

}
