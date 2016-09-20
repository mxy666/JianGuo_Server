package com.jianguo.app.didi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class DidiBean {


    /**
     * amount : 222
     * bind_time : 2016-09-13
     * errno : 0
     * extra_c : {"city_id":1}
     * package : [{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":40,"deadline":"2016-10-13","discount":82,"name":"专车券·接送机专享","productid":"200","remark":"最高抵扣40元，起止地限机场"},{"amount":40,"deadline":"2016-10-13","discount":82,"name":"专车券·接送机专享","productid":"200","remark":"最高抵扣40元，起止地限机场"},{"amount":10,"deadline":"2016-10-13","discount":95,"name":"快车券","productid":"210","remark":"最高抵扣10元"},{"amount":10,"deadline":"2016-10-13","discount":95,"name":"快车券","productid":"210","remark":"最高抵扣10元"},{"amount":1,"deadline":"2016-10-13","discount":0,"name":"快车券","productid":"210","remark":""},{"amount":1,"deadline":"2016-10-13","discount":0,"name":"快车券","productid":"210","remark":""}]
     * phone : 13163153160
     */

    private DataBean data;
    /**
     * data : {"amount":222,"bind_time":"2016-09-13","errno":0,"extra_c":{"city_id":1},"package":[{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":20,"deadline":"2016-10-13","discount":85,"name":"专车券·通勤券","productid":"200","remark":"最高抵扣20元，限每周一至周五使用"},{"amount":40,"deadline":"2016-10-13","discount":82,"name":"专车券·接送机专享","productid":"200","remark":"最高抵扣40元，起止地限机场"},{"amount":40,"deadline":"2016-10-13","discount":82,"name":"专车券·接送机专享","productid":"200","remark":"最高抵扣40元，起止地限机场"},{"amount":10,"deadline":"2016-10-13","discount":95,"name":"快车券","productid":"210","remark":"最高抵扣10元"},{"amount":10,"deadline":"2016-10-13","discount":95,"name":"快车券","productid":"210","remark":"最高抵扣10元"},{"amount":1,"deadline":"2016-10-13","discount":0,"name":"快车券","productid":"210","remark":""},{"amount":1,"deadline":"2016-10-13","discount":0,"name":"快车券","productid":"210","remark":""}],"phone":"13163153160"}
     * errmsg : 已领过
     * errno : 7
     */

    private String errmsg;
    private int errno;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public static class DataBean {
        private int amount;
        private String bind_time;
        private int errno;
        /**
         * city_id : 1
         */

        private ExtraCBean extra_c;
        private String phone;
        /**
         * amount : 20
         * deadline : 2016-10-13
         * discount : 85
         * name : 专车券·通勤券
         * productid : 200
         * remark : 最高抵扣20元，限每周一至周五使用
         */

        @SerializedName("package")
        private List<PackageBean> packageX;

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

        public int getErrno() {
            return errno;
        }

        public void setErrno(int errno) {
            this.errno = errno;
        }

        public ExtraCBean getExtra_c() {
            return extra_c;
        }

        public void setExtra_c(ExtraCBean extra_c) {
            this.extra_c = extra_c;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
            private int amount;
            private String deadline;
            private int discount;
            private String name;
            private String productid;
            private String remark;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProductid() {
                return productid;
            }

            public void setProductid(String productid) {
                this.productid = productid;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
