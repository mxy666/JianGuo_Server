package com.jianguo.bean;

public class T_attent_Bean {

	private int id;//ID
	private int login_id;//用户登录表关联ID
	private int follow;//关注
	private int collection;//收藏
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFollow() {
		return follow;
	}
	public void setFollow(int follow) {
		this.follow = follow;
	}
	public int getCollection() {
		return collection;
	}
	public void setCollection(int collection) {
		this.collection = collection;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public int getLogin_id() {
		return login_id;
	}
	
}
