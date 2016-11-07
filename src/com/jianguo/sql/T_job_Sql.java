package com.jianguo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.util.DButil;
import org.apache.log4j.Logger;

public class T_job_Sql {

    public static int insert(String city_id, String area_id, String type_id, String merchant_id, String name, String name_image, String start_date, String stop_date,
                             String address, String mode, String money, String term, String limit_sex, String count, String sum, String regedit_time,
                             String status, String hot, String alike, String reg_date, String look, String is_model, String user_count, String start_time,
                             String max, String girl_count, String girl_sum, String girl_user) {
        int num = 0;
        if (girl_count == null || girl_count.equals("")) {
            girl_count = "0";

        }
        if (girl_sum == null || girl_sum.equals("")) {
            girl_sum = "0";

        }
        if (girl_user == null || girl_user.equals("")) {
            girl_user = "0";

        }
        Logger logger = Logger.getLogger("log");
        Connection conn = DButil.getCon();
        String sql = "insert into t_job(city_id,area_id,type_id,merchant_id,name,name_image,start_date," +
                "stop_date,address,mode,money,term,limit_sex,count,sum,regedit_time,status,hot," +
                "alike,reg_date,look,is_model,user_count,start_time,max,girl_count,girl_sum,girl_user) values(?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?)";
        PreparedStatement pst = DButil.getPstm(conn, sql);
        try {
            pst.setString(1, city_id);
            pst.setString(2, area_id);
            pst.setString(3, type_id);
            pst.setString(4, merchant_id);
            pst.setString(5, name);
            pst.setString(6, name_image);
            pst.setString(7, start_date);
            pst.setString(8, stop_date);
            pst.setString(9, address);
            pst.setString(10, mode);
            pst.setString(11, money);
            pst.setString(12, term);
            pst.setString(13, limit_sex);
            pst.setString(14, count);
            pst.setString(15, sum);
            pst.setString(16, regedit_time);
            pst.setString(17, status);
            pst.setString(18, hot);
            pst.setString(19, alike);
            pst.setString(20, reg_date);
            pst.setString(21, look);
            pst.setString(22, is_model);
            pst.setString(23, user_count);
            pst.setString(24, start_time);
            pst.setString(25, max);
            pst.setString(26, girl_count);
            pst.setString(27, girl_sum);
            pst.setString(28, girl_user);

            num = pst.executeUpdate();
        } catch (SQLException e) {
            logger.error("???????job_??" + e.getMessage() == null ? "??" : e.getMessage());
            e.printStackTrace();
        } finally {
            DButil.close(conn);
            DButil.psClose(pst);
        }
        return num;
    }

    //?????????????????
    public static T_job_Bean select_regedit_time(String regedit_time) {
        ResultSet rs = null;
        T_job_Bean t_job = new T_job_Bean();

        Connection conn = DButil.getCon();
        String sql = "select * from t_job where regedit_time=?";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, regedit_time);
            rs = psmt.executeQuery();
            while (rs.next()) {
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
                /*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????

            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            //logger.error("???????job_info??"+e.getMessage()==null?"??":e.getMessage());
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return t_job;
    }

    public static T_job_Bean select_merchant_id_aa(String merchant_id) {
        ResultSet rs = null;
        T_job_Bean t_job = new T_job_Bean();
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where merchant_id=?";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, merchant_id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return t_job;
    }

    public static T_job_Bean select_id(String id) {
        ResultSet rs = null;
        T_job_Bean t_job = new T_job_Bean();
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where id=?";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????

                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return t_job;
    }

    public static T_job_Bean select_alike(String alike) {
        ResultSet rs = null;
        T_job_Bean t_job = new T_job_Bean();
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where alike=? and limit_sex=30";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, alike);
            rs = psmt.executeQuery();
            while (rs.next()) {
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return t_job;
    }

    //(city_id,"0","1","2","3","0");
    public static List<T_job_Bean> select_all(String city_id, String cityId, String status0, String status1, String status2, String status3, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and (status=? or status=? or status=? or status=?) and is_model=0 and limit_sex!=30 order by id desc limit " + count + ",400";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, cityId);
            psmt.setString(3, status0);
            psmt.setString(4, status1);
            psmt.setString(5, status2);
            psmt.setString(6, status3);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_all_ok(String city_id, String city_id1) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?)  and is_model=0 order by id desc";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
//			psmt.setString(2,status);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
				/*t_job.setLimit_sex(rs.getInt("limit_sex"));
				t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_hot(String hot, String city_id, String cityID2, String date, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
//		String mersql = "select * from t_job where hot=? and city_id=? and is_model=0 and (status=0 or status=2) order by status asc,id desc limit "+count+",10";
        String sql = "select * from t_job where hot=? and (city_id=? or city_id=? ) and is_model=0 order by status asc,id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, hot);
            psmt.setString(2, city_id);
            psmt.setString(3, cityID2);
//			psmt.setString(3,date);
//			psmt.setString(4,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
			/*	t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_lvxing(String hot, String date, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where hot=? and is_model=0 order by status asc,id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, hot);
//			psmt.setString(3,date);
//			psmt.setString(4,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_max(String city_id, String date, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where max=1 and city_id=? and is_model=0 order by id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
//			psmt.setString(3,date);
//			psmt.setString(4,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
			/*	t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter0(String city_id, String city_id1, String type_id, String area_id, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and (type_id=? or area_id=?) and is_model=0 and status=0 and hot!=3 order by status asc,id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
            psmt.setString(3, type_id);
            psmt.setString(4, area_id);
//			psmt.setString(4,date);
//			psmt.setString(5,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter000(String city_id, String city_id1, String type_id, String area_id, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and (type_id=? and area_id=?) and is_model=0 and status=0 and hot!=3 order by status asc,id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
            psmt.setString(3, type_id);
            psmt.setString(4, area_id);
//			psmt.setString(4,date);
//			psmt.setString(5,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter00(String city_id, String city_id1, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and is_model=0 and status=0 and hot!=3 order by status asc,id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
//			psmt.setString(2,date);
//			psmt.setString(3,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter1(String city_id, String city_id1, String type_id, String area_id, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and (type_id=? or area_id=?) and is_model=0 and status=0 and hot!=3 order by money desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
            psmt.setString(3, type_id);
            psmt.setString(4, area_id);
//			psmt.setString(4,date);
//			psmt.setString(5,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter111(String city_id, String city_id1, String type_id, String area_id, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and (type_id=? and area_id=?) and is_model=0 and status=0 and hot!=3 order by money desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
            psmt.setString(3, type_id);
            psmt.setString(4, area_id);
//			psmt.setString(4,date);
//			psmt.setString(5,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
			/*	t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter11(String city_id, String city_id1, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and is_model=0 and status=0 and hot!=3 order by money desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
//			psmt.setString(2,date);
//			psmt.setString(3,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter2(String city_id, String city_id1, String type_id, String area_id, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?)  and (type_id=? or area_id=?) and is_model=0 and status=0 and hot!=3 order by hot desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
            psmt.setString(3, type_id);
            psmt.setString(4, area_id);
//			psmt.setString(4,date);
//			psmt.setString(5,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter222(String city_id, String city_id1, String type_id, String area_id, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?)  and is_model=0 and status=0 and hot!=3 order by hot desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
            psmt.setString(3, type_id);
            psmt.setString(4, area_id);
//			psmt.setString(4,date);
//			psmt.setString(5,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_filter22(String city_id, String city_id1, String date, String date2, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where (city_id=? or city_id=?) and is_model=0 and status=0 and hot!=3 order by hot desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, city_id);
            psmt.setString(2, city_id1);
//			psmt.setString(2,date);
//			psmt.setString(3,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_term(String mode, String city_id, String date, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where mode=? and city_id=? and is_model=0 and status=0 and hot!=3 order by id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, mode);
            psmt.setString(2, city_id);
//			psmt.setString(3,date);
//			psmt.setString(4,date2);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_merchant_id(String merchant_id, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where merchant_id=? and limit_sex!=31 and is_model=0 order by id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, merchant_id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_merchant_id_zhong(String merchant_id, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where merchant_id=? and (status =0 or status=2)  and is_model=0 order by id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, merchant_id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_merchant_id_no_zhong(String merchant_id, String count) {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where merchant_id=? and status !=0 and status !=2 and limit_sex!=30 and is_model=0 order by id desc limit " + count + ",10";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1, merchant_id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_all_status() {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where is_model=0 and (status =0 or status =1)";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static List<T_job_Bean> select_all_status_wai() {
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        Connection conn = DButil.getCon();
        String sql = "select * from t_job where is_model=0 and max=0 and (status =0 or status =1)";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getString("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setName_image(rs.getString("name_image") + "");
                t_job.setStart_date(rs.getString("start_date") + "");
                t_job.setStop_date(rs.getString("stop_date") + "");
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
				/*t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count") + rs.getInt("girl_count"));//?????????
                t_job.setSum(rs.getInt("girl_sum") + rs.getInt("sum"));//??????????
                t_job.setRegedit_time(rs.getString("regedit_time") + "");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike") + "");
                t_job.setReg_date(rs.getString("reg_date") + "");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//?е???????
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }

    public static int update_look(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set look=look+1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int update_count(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set count=count+1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    //??????????count(???????????)
    public static int update_GirlCountss(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set girl_count=girl_count+1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    //??????????count(????????????)
    public static int update_countss(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set count=count+1  where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);

            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    //??????????????
    public static int update_Girlcount_jian(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set girl_count=girl_count-1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }


    public static int update_count_jian(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set count=count-1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int update_status(String status, String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set status=? where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, status);
            psmt.setString(2, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int update_user_count(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set user_count=user_count+1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int update_girl_user(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set girl_user=girl_user+1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int update_user_count_jian(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set user_count=user_count-1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int update_girl_user_jian(String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set girl_user=girl_user-1 where id=?";
            PreparedStatement psmt = DButil.getPstm(conn, sql);
            psmt.setString(1, id);
            num = psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int update_all(String city_id, String area_id, String type_id, String merchant_id, String name, String name_image, String start_date, String stop_date,
                                 String address, String mode, String money, String term, String limit_sex, String sum, String regedit_time,
                                 String status, String hot, String alike, String look, String is_model, String start_time, String girl_sum, String id) {
        int num = 0;
        try {
            Connection conn = DButil.getCon();
            String sql = "update t_job set city_id=?,area_id=?,type_id=?,merchant_id=?,name=?,name_image=?,start_date=?,stop_date=?,address=?,mode=?,money=?,term=?,limit_sex=?,sum=?,regedit_time=?,status=?,hot=?,alike=?,look=?,is_model=?,start_time=?,girl_sum=? where id=?";
            PreparedStatement pst = DButil.getPstm(conn, sql);
            pst.setString(1, city_id);
            pst.setString(2, area_id);
            pst.setString(3, type_id);
            pst.setString(4, merchant_id);
            pst.setString(5, name);
            pst.setString(6, name_image);
            pst.setString(7, start_date);
            pst.setString(8, stop_date);
            pst.setString(9, address);
            pst.setString(10, mode);
            pst.setString(11, money);
            pst.setString(12, term);
            pst.setString(13, limit_sex);
            pst.setString(14, sum);
            pst.setString(15, regedit_time);
            pst.setString(16, status);
            pst.setString(17, hot);
            pst.setString(18, alike);
            pst.setString(19, look);
            pst.setString(20, is_model);
            pst.setString(21, start_time);
            pst.setString(22, girl_sum);
            pst.setString(23, id);
            num = pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static List<T_job_Bean> queryAll(String name) {
        Connection conn = DButil.getCon();
        List<T_job_Bean> list = new ArrayList<T_job_Bean>();
        ResultSet rs = null;
        StringBuffer str = new StringBuffer();
        String sql = "select * from t_job where 1=1";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (name != null && !name.equals("")) {
            str.append(" and name like'%" + name + "%' ");
        }
        sql = sql + str.toString();
        System.out.println(sql + "----------------------------------------");
        PreparedStatement psmt = DButil.getPstm(conn, sql);

        try {
            rs = psmt.executeQuery();
            while (rs.next()) {
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setName(rs.getString("name") + "");
                t_job.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date") + "" + "100"))));
                t_job.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date") + "" + "100"))));
                t_job.setAddress(rs.getString("address") + "");
                t_job.setMoney(rs.getDouble("money"));

                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }


    public static List<Map<String, String>> searchXiaJia(String bus_name, String job_name) {
        Connection conn = DButil.getCon();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ResultSet rs = null;
        StringBuffer str = new StringBuffer();
        String sql = "SELECT t.`id`,m.login_id as user_id ,t.`name` AS job_name,m.`name` AS bus_name,t.`regedit_time`,c.`city`,a.`area_name`,t.`address`, \n" +
                " m.contactPhone,from_unixtime(t.start_date,'yyyy-MM-dd'),from_unixtime(t.stop_date,'yyyy-MM-dd'),t.money,m.about " +
                " FROM t_job t\n" +
                " LEFT JOIN t_merchant m ON t.`merchant_id` = m.`id`\n" +
                " LEFT JOIN t_city c ON t.`city_id` = c.`id`\n" +
                " LEFT JOIN t_area a ON t.`area_id` = a.`id`\n" +
                " WHERE 1=1 and t.status != 6 ";

        if (job_name != null && !"".equals(job_name.trim())) {
            sql += " and t.`name` LIKE '%" + job_name + "%' ";
        }
        if (bus_name != null && !"".equals(bus_name.trim())) {
            sql += " and m.`name` LIKE '%" + bus_name + "%' ";
        }


        System.out.println(sql + "----------------------------------------");
        PreparedStatement psmt = DButil.getPstm(conn, sql);

        try {
            rs = psmt.executeQuery();
            Map<String, String> map = null;
            while (rs.next()) {
                map = new HashMap<>();
                map.put("id", rs.getString("id"));
                map.put("user_id", rs.getString("user_id"));
                map.put("job_name", rs.getString("job_name"));
                map.put("bus_name", rs.getString("bus_name"));
                map.put("regedit_time", rs.getString("regedit_time"));
                map.put("city", rs.getString("city"));
                map.put("area_name", rs.getString("area_name"));
                map.put("address", rs.getString("address"));
                map.put("contactPhone", rs.getString("contactPhone"));
                map.put("start_date", rs.getString("start_date"));
                map.put("stop_date", rs.getString("stop_date"));
                map.put("money", rs.getString("money"));
                map.put("about", rs.getString("about"));

//				T_job_Bean t_job = new T_job_Bean();
//				t_job.setId(rs.getInt("id"));
//				t_job.setName(rs.getString("name")+"");
//				t_job.setStart_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+""+"100"))));
//				t_job.setStop_date(sdf.format(new Date(Long.parseLong(rs.getString("start_date")+""+"100"))));
//				t_job.setAddress(rs.getString("address")+"");
//				t_job.setMoney(rs.getDouble("money"));

                list.add(map);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DButil.close(conn);
        }
        return list;
    }


}
