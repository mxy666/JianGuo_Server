package com.jianguo.bean;

public class T_job_label_Bean {

	//��ְ��ǩ��
	private int id;//
	private int job_id;//��ְID
	private String limits;//����ID
	private String welfare;//����ID
	private String label;//��ǩID
	
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
