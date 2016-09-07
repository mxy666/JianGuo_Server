package com.jianguo.bean;

import java.util.List;

public class T_city_Bean {

	private int id;
	private String city;
	private String code;
	private List<T_area_Bean> list_t_area;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setList_t_area(List<T_area_Bean> list_t_area) {
		this.list_t_area = list_t_area;
	}
	public List<T_area_Bean> getList_t_area() {
		return list_t_area;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
}
