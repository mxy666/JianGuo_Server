package com.jianguo.sql;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/9/26.
 */
public class JobForWeb {
    public static List<T_job_Bean> queryAlljob(String hot, String date, String count){
        List<T_job_Bean> list=new ArrayList<T_job_Bean>();
        ResultSet rs=null;
        Connection conn= DButil.getCon();
//		String mersql = "select * from t_job where hot=? and city_id=? and is_model=0 and (status=0 or status=2) order by status asc,id desc limit "+count+",10";
      //  String sql = "select * from t_job where hot=? and is_model=0 order by status asc,id desc limit "+count+",10";
        String sql = "select * from t_job where hot=? and is_model=0 order by status asc,id desc ";
        PreparedStatement psmt = DButil.getPstm(conn, sql);
        try {
            psmt.setString(1,hot);
            rs=psmt.executeQuery();
            while(rs.next()){
                T_job_Bean t_job = new T_job_Bean();
                t_job.setId(rs.getInt("id"));
                t_job.setCity_id(rs.getInt("city_id"));
                t_job.setArea_id(rs.getInt("area_id"));
                t_job.setType_id(rs.getInt("type_id"));
                t_job.setMerchant_id(rs.getInt("merchant_id"));
                t_job.setName(rs.getString("name")+"");
                t_job.setName_image(rs.getString("name_image")+"");
                t_job.setStart_date(rs.getString("start_date")+"");
                t_job.setStop_date(rs.getString("stop_date")+"");
                t_job.setAddress(rs.getString("address")+"");
                t_job.setMode(rs.getInt("mode"));
                t_job.setMoney(rs.getDouble("money"));
                t_job.setTerm(rs.getInt("term"));
                t_job.setLimit_sex(rs.getInt("limit_sex"));
			/*	t_job.setCount(rs.getInt("count"));
				t_job.setSum(rs.getInt("sum"));*/
                t_job.setCount(rs.getInt("count")+rs.getInt("girl_count"));//录取了多少人
                t_job.setSum(rs.getInt("girl_sum")+rs.getInt("sum"));//总共要多少人
                t_job.setRegedit_time(rs.getString("regedit_time")+"");
                t_job.setStatus(rs.getInt("status"));
                t_job.setHot(rs.getInt("hot"));
                t_job.setAlike(rs.getString("alike")+"");
                t_job.setReg_date(rs.getString("reg_date")+"");
                t_job.setLook(rs.getInt("look"));
                t_job.setIs_model(rs.getInt("is_model"));
                t_job.setUser_count(rs.getInt("user_count"));
                t_job.setMax(rs.getInt("max"));
                t_job.setGirl_count(rs.getInt("girl_count"));
                t_job.setGirl_sum(rs.getInt("girl_sum"));
                t_job.setGirl_user(rs.getInt("girl_user"));
                t_job.setBoySum(rs.getInt("sum"));//男的录取总数
                list.add(t_job);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            DButil.close(conn);
        }
        return list;
    }
}
