package com.jianguo.bean;

public class T_enroll_Bean {

	//报名表
	private int id;//ID
	private int login_id;//用户ID
	private int job_id;//兼职ID
	private int status;//用户状态
	private String login_time;//用户报名时间
	private int state;//用户状态
	private int type;//兼职类型
	
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
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int jobId) {
		job_id = jobId;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String loginTime) {
		login_time = loginTime;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return state;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	
}
