package com.jianguo.bean;

public class T_job_record_Bean {

	//用户兼职记录
	private int id;//ID
	private int login_id;//用户登录ID
	private int complete;//完成次数
	private int cancel;//取消次数
	
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
	public int getComplete() {
		return complete;
	}
	public void setComplete(int complete) {
		this.complete = complete;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	
}
