package com.jianguo.bean;

public class T_user_realname_Bean {

	//用户实名表
	private int id; //ID
	private int login_id; //用户登录表关联ID
	private String front_image; //正面
	private String behind_image; //反面
	private String realname; //真实姓名
	private String id_number;//身份证号
	private int sex;//性别
	private int status;//性别
	private String school;//性别

	private String city;
	
	private String tel;//身份证号
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int loginId) {
		login_id = loginId;
	}
	public String getFront_image() {
		return front_image;
	}
	public void setFront_image(String frontImage) {
		front_image = frontImage;
	}
	public String getBehind_image() {
		return behind_image;
	}
	public void setBehind_image(String behindImage) {
		behind_image = behindImage;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String idNumber) {
		id_number = idNumber;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSex() {
		return sex;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchool() {
		return school;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
