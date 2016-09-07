package com.jianguo.bean;

public class T_job_look_Bean {

	//兼职浏览表
	private int id;//ID
	private int login_id;//用户登录ID
	private int job_id;//兼职ID
	private String reg_time;//时间
	
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
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String regTime) {
		reg_time = regTime;
	}
	
}
