package com.jianguo.bean;

public class T_type_Bean {

	//兼职种类表
	private int id;//ID
	private String type_name;//种类名称
	private String is_type;//种类名称
	private String login_id;//种类名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String typeName) {
		type_name = typeName;
	}
	public void setIs_type(String is_type) {
		this.is_type = is_type;
	}
	public String getIs_type() {
		return is_type;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_id() {
		return login_id;
	}
	
}
