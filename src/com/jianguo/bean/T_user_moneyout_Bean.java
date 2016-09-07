package com.jianguo.bean;

public class T_user_moneyout_Bean {

	//用户提现记录表
	private int id;//
	private int login_id;//用户登录ID
	private double money;//提现金额
	private int type;//(0=支付宝,1=银行)
	private int status;//(0=转到支付宝,1=转到银行卡,2=提现中)
	private String time;//时间
	private String remarks;//备注
	private String login_name;//时间
	private String login_names;//时间
	private String login_type;//时间
	private String login_nameimage;//时间
	private String login_hao;//时间
	private String login_money;//时间
	private String login_realname;//时间
	private String login_id_number;//时间
	private String login_id_city;//时间
	
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
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}
	public String getLogin_type() {
		return login_type;
	}
	public void setLogin_nameimage(String login_nameimage) {
		this.login_nameimage = login_nameimage;
	}
	public String getLogin_nameimage() {
		return login_nameimage;
	}
	public void setLogin_hao(String login_hao) {
		this.login_hao = login_hao;
	}
	public String getLogin_hao() {
		return login_hao;
	}
	public void setLogin_money(String login_money) {
		this.login_money = login_money;
	}
	public String getLogin_money() {
		return login_money;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setLogin_realname(String login_realname) {
		this.login_realname = login_realname;
	}
	public String getLogin_realname() {
		return login_realname;
	}
	public void setLogin_id_number(String login_id_number) {
		this.login_id_number = login_id_number;
	}
	public String getLogin_id_number() {
		return login_id_number;
	}
	public void setLogin_id_city(String login_id_city) {
		this.login_id_city = login_id_city;
	}
	public String getLogin_id_city() {
		return login_id_city;
	}
	public void setLogin_names(String login_names) {
		this.login_names = login_names;
	}
	public String getLogin_names() {
		return login_names;
	}
	
}
