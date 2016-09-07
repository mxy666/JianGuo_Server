package com.jianguo.bean;

public class T_user_wx_Bean {

	//用户微信表
	private int id;//ID
	private int login_id;//用户登录ID
	private String openid;//普通用户的标识，对当前开发者帐号唯一
	private String nickname;//普通用户昵称
	private int sex;//普通用户性别，1为男性，2为女性
	private String province;//普通用户个人资料填写的省份
	private String city;//普通用户个人资料填写的城市
	private String country;//国家，如中国为CN
	private String headimgurl;//用户头像
	private String privilege;//用户特权信息	同一用户的unionid是唯一的。
	private String unionid;//用户统一标识。针对一个微信开放平台帐号下的应用，
	
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
