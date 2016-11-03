package com.jianguo.bean;

import java.util.List;

public class T_JobDetailBean {
	private int id;//ID
	private String set_place;//集合地点
	private String set_time;//集合时间
	private String start_date;//工作开始日期
	private String stop_date;//工作结束日期
	private String start_time;//工作开始时间
	private String stop_time;//工作结束时间
	private String work_content;//工作内容
	private String work_require;//工作要求
	private String address;//地址
	private String  reg_date;//兼职发布日期
	private String job_name;//是否报名
	private String job_image;//是否报名
	private String job_money;//工资
	private String  limit_sex;	//性别限制（0=只招女，1=只招男，2=不限男女，3=男女各限）	
	private int finallySum;//扎聘人数  sum	+girl_sum
	private int nowCount;//现已录取几人girl_count+count

	public int getUser_count() {
		return user_count;
	}

	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}

	private int user_count;
	private String merchant_name;//是否报名
	private String merchant_tel;//是否报名	
	private String  merchant_image;//商家头像
	private int  merchant_id;//商家login_Id
	private int  merchant_LogId;//商家login_Id
	private String  merchant_about;//商家简介
	private String merchantTel;
	private int permission;//商家类型 0内部 1外部2个人商家
	private String other;//其他
	private int status;//是否过期除了0的都是过期的
	private String mode;//结算方式
	private String isEnroll;//是否报名
	private String isFavorite;//是否报名

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	private List<String> limit;
	private List<String> welfare;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSet_place() {
		return set_place;
	}
	public void setSet_place(String setPlace) {
		set_place = setPlace;
	}
	public String getSet_time() {
		return set_time;
	}
	public void setSet_time(String setTime) {
		set_time = setTime;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String startDate) {
		start_date = startDate;
	}
	public String getStop_date() {
		return stop_date;
	}
	public void setStop_date(String stopDate) {
		stop_date = stopDate;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String startTime) {
		start_time = startTime;
	}
	public String getStop_time() {
		return stop_time;
	}
	public void setStop_time(String stopTime) {
		stop_time = stopTime;
	}
	public String getWork_content() {
		return work_content;
	}
	public void setWork_content(String workContent) {
		work_content = workContent;
	}
	public String getWork_require() {
		return work_require;
	}
	public void setWork_require(String workRequire) {
		work_require = workRequire;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String regDate) {
		reg_date = regDate;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String jobName) {
		job_name = jobName;
	}
	public String getJob_image() {
		return job_image;
	}
	public void setJob_image(String jobImage) {
		job_image = jobImage;
	}
	public String getJob_money() {
		return job_money;
	}
	public void setJob_money(String jobMoney) {
		job_money = jobMoney;
	}
	public String getLimit_sex() {
		return limit_sex;
	}
	public void setLimit_sex(String limitSex) {
		limit_sex = limitSex;
	}
	public int getFinallySum() {
		return finallySum;
	}
	public void setFinallySum(int finallySum) {
		this.finallySum = finallySum;
	}

	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchantName) {
		merchant_name = merchantName;
	}
	public String getMerchant_tel() {
		return merchant_tel;
	}
	public void setMerchant_tel(String merchantTel) {
		merchant_tel = merchantTel;
	}
	public String getMerchant_image() {
		return merchant_image;
	}
	public void setMerchant_image(String merchantImage) {
		merchant_image = merchantImage;
	}
	public String getMerchant_about() {
		return merchant_about;
	}
	public void setMerchant_about(String merchantAbout) {
		merchant_about = merchantAbout;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public void setNowCount(int nowCount) {
		this.nowCount = nowCount;
	}
	public int getNowCount() {
		return nowCount;
	}
	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}
	public int getMerchant_id() {
		return merchant_id;
	}

	
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMode() {
		return mode;
	}
	public void setIsEnroll(String isEnroll) {
		this.isEnroll = isEnroll;
	}
	public String getIsEnroll() {
		return isEnroll;
	}
	public void setIsFavorite(String isFavorite) {
		this.isFavorite = isFavorite;
	}
	public String getIsFavorite() {
		return isFavorite;
	}
	public void setLimit(List<String> limit) {
		this.limit = limit;
	}
	public List<String> getLimit() {
		return limit;
	}
	public void setWelfare(List<String> welfare) {
		this.welfare = welfare;
	}
	public List<String> getWelfare() {
		return welfare;
	}
	public void setMerchant_LogId(int merchant_LogId) {
		this.merchant_LogId = merchant_LogId;
	}
	public int getMerchant_LogId() {
		return merchant_LogId;
	}
	public void setMerchantTel(String merchantTel) {
		this.merchantTel = merchantTel;
	}
	public String getMerchantTel() {
		return merchantTel;
	}


	

	
/*	private int limit_sex;//性别限制（0=只招女，1=只招男，2=不限男女）
	private int term;//期限（1=月结，2=周结，3=日结，4=小时结）
	private int mode;//结算方式
*/

	

}
