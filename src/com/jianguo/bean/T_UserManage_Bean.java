package com.jianguo.bean;

public class T_UserManage_Bean {
	
	private int id;//ID
	private int login_id;//用户登录表关联ID
	private String name;
	private String tel;
	private String sex;
	private String city_id ;
	private String school;
	private double money;
	private String job_id;//新增
	private String detail;//新增
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String cityId) {
		city_id = cityId;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String jobId) {
		job_id = jobId;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDetail() {
		return detail;
	}
	
	
}
