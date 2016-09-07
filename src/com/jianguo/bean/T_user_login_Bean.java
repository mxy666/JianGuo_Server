package com.jianguo.bean;

public class T_user_login_Bean {

	//用户登录表
	private int id;//ID
	private String tel;//密码
	private String password;//电话
	private String qqwx_token;//QQ
	private int status;//状态
	private int resume;//是否填写简历
	private int hobby;//求职意向设置
	private String qiniu;//七牛
	private String city_id;//城市
	private int pigeon_count;//鸽子数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setQqwx_token(String qqwx_token) {
		this.qqwx_token = qqwx_token;
	}
	public String getQqwx_token() {
		return qqwx_token;
	}
	public void setQiniu(String qiniu) {
		this.qiniu = qiniu;
	}
	public String getQiniu() {
		return qiniu;
	}
	public void setResume(int resume) {
		this.resume = resume;
	}
	public int getResume() {
		return resume;
	}
	public void setHobby(int hobby) {
		this.hobby = hobby;
	}
	public int getHobby() {
		return hobby;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setPigeon_count(int pigeon_count) {
		this.pigeon_count = pigeon_count;
	}
	public int getPigeon_count() {
		return pigeon_count;
	}
	
}
