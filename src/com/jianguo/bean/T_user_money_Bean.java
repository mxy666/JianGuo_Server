package com.jianguo.bean;

public class T_user_money_Bean {

	//用户钱包表
	private int id;//ID
	private int login_id;//用户登录ID
	private String name;//用户名称
	private double money;//余额
	private String zhifubao;//支付宝
	private String yinhang;//银行
	private String kahao;//卡号
	private String pay_password;//提现密码
	private String pay_status;//提现密码
	private int weixin;//用户登录ID
	private String nickname;//微信名字
	
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
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getZhifubao() {
		return zhifubao;
	}
	public void setZhifubao(String zhifubao) {
		this.zhifubao = zhifubao;
	}
	public String getYinhang() {
		return yinhang;
	}
	public void setYinhang(String yinhang) {
		this.yinhang = yinhang;
	}
	public String getKahao() {
		return kahao;
	}
	public void setKahao(String kahao) {
		this.kahao = kahao;
	}
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String payPassword) {
		pay_password = payPassword;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setWeixin(int weixin) {
		this.weixin = weixin;
	}
	public int getWeixin() {
		return weixin;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	
}
