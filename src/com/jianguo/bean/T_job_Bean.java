package com.jianguo.bean;

import java.util.List;

public class T_job_Bean {

	private int id;//ID
	private String city_id;//城市ID
	private int area_id;//地区ID
	private int type_id;//种类ID
	private int merchant_id;//商家ID
	private String name;//名称
	private String name_image;//头像
	private String start_date;//开始时间
	private String stop_date;//结束时间
	private String address;//地址
	private int mode;//结算方式
	private double money;//钱
	private int term;//期限（1=月结，2=周结，3=日结，4=小时结）
	private int limit_sex;//性别限制（0=只招女，1=只招男，2=不限男女）
	private int count;//已有人数
	private int sum;//总人数
	private int day;//几天前发布
	private String regedit_time;//注册时间
	private int status;//状态（0=已下架，1=招聘中，2=已招满）
	private int hot;//分类（0=热门兼职，1=精品兼职，2=普通兼职）
	private String alike;//相同
	private String reg_date;//相同
	private int look;//浏览量
	private int is_model;//是否模板
	private int user_count;//报名人数
	private int max;//长期
	private int girl_count;//女的录取数
	private int girl_sum;//女的总数
	private int girl_user;//女的报名数
	
	
	
	private String sex;//相同
	private String model_name;//相同
	private String city_id_name;//相同
	private String area_id_name;//相同
	private String type_id_name;//相同
	private String merchant_id_name;//相同
	private String info_start_time;//相同
	private String info_stop_time;//相同
	private String info_set_place;//相同
	private String info_set_time;//相同
	private String info_limit_sex;//相同
	private String info_term;//相同
	private String info_other;//相同
	private String info_work_content;//相同
	private String info_work_require;//相同
	private String info_tel;//相同
	private String remarks;//相同
	
	private String nv_job_id;//是否报名
	private String nv_sum;//是否报名
	private String nv_count;//是否报名
	
	private String user_status;//相同

	private List<String> limit_name;//相同
	private List<String> welfare_name;//相同
	private List<String> label_name;//相同
	
	//private int boyCount;
	private int boySum;
	
	private String status1;
	
	public List<String> getLimit_name() {
		return limit_name;
	}
	public void setLimit_name(List<String> limitName) {
		limit_name = limitName;
	}
	public List<String> getWelfare_name() {
		return welfare_name;
	}
	public void setWelfare_name(List<String> welfareName) {
		welfare_name = welfareName;
	}
	public List<String> getLabel_name() {
		return label_name;
	}
	public void setLabel_name(List<String> labelName) {
		label_name = labelName;
	}
	private String userName;//相同

	public String getInfo_start_time() {
		return info_start_time;
	}
	public void setInfo_start_time(String infoStartTime) {
		info_start_time = infoStartTime;
	}
	public String getInfo_stop_time() {
		return info_stop_time;
	}
	public void setInfo_stop_time(String infoStopTime) {
		info_stop_time = infoStopTime;
	}
	public String getInfo_set_place() {
		return info_set_place;
	}
	public void setInfo_set_place(String infoSetPlace) {
		info_set_place = infoSetPlace;
	}
	public String getInfo_set_time() {
		return info_set_time;
	}
	public void setInfo_set_time(String infoSetTime) {
		info_set_time = infoSetTime;
	}
	public String getInfo_limit_sex() {
		return info_limit_sex;
	}
	public void setInfo_limit_sex(String infoLimitSex) {
		info_limit_sex = infoLimitSex;
	}
	public String getInfo_term() {
		return info_term;
	}
	public void setInfo_term(String infoTerm) {
		info_term = infoTerm;
	}
	public String getInfo_other() {
		return info_other;
	}
	public void setInfo_other(String infoOther) {
		info_other = infoOther;
	}
	public String getInfo_work_content() {
		return info_work_content;
	}
	public void setInfo_work_content(String infoWorkContent) {
		info_work_content = infoWorkContent;
	}
	public String getInfo_work_require() {
		return info_work_require;
	}
	public void setInfo_work_require(String infoWorkRequire) {
		info_work_require = infoWorkRequire;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String cityId) {
		city_id = cityId;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchantId) {
		merchant_id = merchantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_image() {
		return name_image;
	}
	public void setName_image(String nameImage) {
		name_image = nameImage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getLimit_sex() {
		return limit_sex;
	}
	public void setLimit_sex(int limitSex) {
		limit_sex = limitSex;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getRegedit_time() {
		return regedit_time;
	}
	public void setRegedit_time(String regeditTime) {
		regedit_time = regeditTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getHot() {
		return hot;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getMode() {
		return mode;
	}
	public void setAlike(String alike) {
		this.alike = alike;
	}
	public String getAlike() {
		return alike;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStop_date(String stop_date) {
		this.stop_date = stop_date;
	}
	public String getStop_date() {
		return stop_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setCity_id_name(String city_id_name) {
		this.city_id_name = city_id_name;
	}
	public String getCity_id_name() {
		return city_id_name;
	}
	public void setArea_id_name(String area_id_name) {
		this.area_id_name = area_id_name;
	}
	public String getArea_id_name() {
		return area_id_name;
	}
	public void setType_id_name(String type_id_name) {
		this.type_id_name = type_id_name;
	}
	public String getType_id_name() {
		return type_id_name;
	}
	public void setMerchant_id_name(String merchant_id_name) {
		this.merchant_id_name = merchant_id_name;
	}
	public String getMerchant_id_name() {
		return merchant_id_name;
	}
	public void setInfo_tel(String info_tel) {
		this.info_tel = info_tel;
	}
	public String getInfo_tel() {
		return info_tel;
	}
	public void setLook(int look) {
		this.look = look;
	}
	public int getLook() {
		return look;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setIs_model(int is_model) {
		this.is_model = is_model;
	}
	public int getIs_model() {
		return is_model;
	}
	public void setNv_job_id(String nv_job_id) {
		this.nv_job_id = nv_job_id;
	}
	public String getNv_job_id() {
		return nv_job_id;
	}
	public void setNv_sum(String nv_sum) {
		this.nv_sum = nv_sum;
	}
	public String getNv_sum() {
		return nv_sum;
	}
	public void setNv_count(String nv_count) {
		this.nv_count = nv_count;
	}
	public String getNv_count() {
		return nv_count;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}
	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
	public int getUser_count() {
		return user_count;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMax() {
		return max;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getStatus1() {
		return status1;
	}
	public void setGirl_count(int girl_count) {
		this.girl_count = girl_count;
	}
	public int getGirl_count() {
		return girl_count;
	}
	public void setGirl_sum(int girl_sum) {
		this.girl_sum = girl_sum;
	}
	public int getGirl_sum() {
		return girl_sum;
	}
	public void setGirl_user(int girl_user) {
		this.girl_user = girl_user;
	}
	public int getGirl_user() {
		return girl_user;
	}
	public void setBoySum(int boySum) {
		this.boySum = boySum;
	}
	public int getBoySum() {
		return boySum;
	}
	
}
