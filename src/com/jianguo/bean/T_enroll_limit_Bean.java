package com.jianguo.bean;

public class T_enroll_limit_Bean {

	//用户报名限制
	private int id;//ID
	private int login_id;//用户登录ID
	private int count;//数量
	private int date;//日期
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	
}
