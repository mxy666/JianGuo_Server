package com.jianguo.bean;

public class T_job_label_Bean {

	//兼职标签表
	private int id;//
	private int job_id;//兼职ID
	private String limits;//限制ID
	private String welfare;//福利ID
	private String label;//标签ID
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int jobId) {
		job_id = jobId;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setLimits(String limits) {
		this.limits = limits;
	}
	public String getLimits() {
		return limits;
	}
	
}
