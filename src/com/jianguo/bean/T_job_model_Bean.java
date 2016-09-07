package com.jianguo.bean;

public class T_job_model_Bean {

	//兼职模型
	private int id;//ID
	private String model_name;//模板名称
	private int merchant_id;//商家ID
	private int job_id;//兼职ID
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String modelName) {
		model_name = modelName;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchantId) {
		merchant_id = merchantId;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int jobId) {
		job_id = jobId;
	}
	
}
