package com.jianguo.bean;

public class T_area_Bean {

	//地区表
	private String id;//ID

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	private String city_id;//城市ID
	private String area_name;//地区名称
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String areaName) {
		area_name = areaName;
	}
	
}
