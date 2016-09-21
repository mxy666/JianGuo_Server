package com.jianguo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public class resultBean {

    /**
     * errno : 0
     * errmsg : OK
     * backtrace :
     * data : {"phone":"18101050625","extra_c":{"city_id":1},"amount":222,"bind_time":"2016-09-13","package":[{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":40,"name":"专车券·接送机专享","deadline":"2016-10-13","remark":"最高抵扣40元，起止地限机场","discount":82},{"productid":"200","amount":40,"name":"专车券·接送机专享","deadline":"2016-10-13","remark":"最高抵扣40元，起止地限机场","discount":82},{"productid":"210","amount":10,"name":"快车券","deadline":"2016-10-13","remark":"最高抵扣10元","discount":95},{"productid":"210","amount":10,"name":"快车券","deadline":"2016-10-13","remark":"最高抵扣10元","discount":95},{"productid":"210","amount":1,"name":"快车券","deadline":"2016-10-13","remark":"","discount":0},{"productid":"210","amount":1,"name":"快车券","deadline":"2016-10-13","remark":"","discount":0}]}
     */

    private int errno;
    private String errmsg;
    private String backtrace;
    /**
     * phone : 18101050625
     * extra_c : {"city_id":1}
     * amount : 222
     * bind_time : 2016-09-13
     * package : [{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":20,"name":"专车券·通勤券","deadline":"2016-10-13","remark":"最高抵扣20元，限每周一至周五使用","discount":85},{"productid":"200","amount":40,"name":"专车券·接送机专享","deadline":"2016-10-13","remark":"最高抵扣40元，起止地限机场","discount":82},{"productid":"200","amount":40,"name":"专车券·接送机专享","deadline":"2016-10-13","remark":"最高抵扣40元，起止地限机场","discount":82},{"productid":"210","amount":10,"name":"快车券","deadline":"2016-10-13","remark":"最高抵扣10元","discount":95},{"productid":"210","amount":10,"name":"快车券","deadline":"2016-10-13","remark":"最高抵扣10元","discount":95},{"productid":"210","amount":1,"name":"快车券","deadline":"2016-10-13","remark":"","discount":0},{"productid":"210","amount":1,"name":"快车券","deadline":"2016-10-13","remark":"","discount":0}]
     */

    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getBacktrace() {
        return backtrace;
    }

    public void setBacktrace(String backtrace) {
        this.backtrace = backtrace;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String phone;
        /**
         * city_id : 1
         */

        private ExtraCBean extra_c;
        private int amount;
        private String bind_time;
        /**
         * productid : 200
         * amount : 20
         * name : 专车券·通勤券
         * deadline : 2016-10-13
         * remark : 最高抵扣20元，限每周一至周五使用
         * discount : 85
         */

        @SerializedName("package") private List<PackageBean> packageX;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public ExtraCBean getExtra_c() {
            return extra_c;
        }

        public void setExtra_c(ExtraCBean extra_c) {
            this.extra_c = extra_c;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBind_time() {
            return bind_time;
        }

        public void setBind_time(String bind_time) {
            this.bind_time = bind_time;
        }

        public List<PackageBean> getPackageX() {
            return packageX;
        }

        public void setPackageX(List<PackageBean> packageX) {
            this.packageX = packageX;
        }

        public static class ExtraCBean {
            private int city_id;

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }
        }

        public static class PackageBean {
            private String productid;
            private int amount;
            private String name;
            private String deadline;
            private String remark;
            private int discount;

            public String getProductid() {
                return productid;
            }

            public void setProductid(String productid) {
                this.productid = productid;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }
        }
    }
}
