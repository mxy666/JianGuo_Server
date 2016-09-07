package com.jianguo.bean;

public class T_user_info_Bean {

	//用户资料表
	private int id;//ID
	private int login_id;//用户登录表关联ID
	private String nickname;//昵称
	private String name;//姓名
	private String name_image;//头像
	private String school;//学校
	private int realname;//实名认证
	private int credit;//信用值
	private int integral;//积分
	private String regedit_time;//注册时间
	private String login_time;//登录时间
	private String qiniu;//七牛
	
	private int complete_job;//完成兼职
	private int cancel_job;//取消兼职
	private String time_job;//取消兼职
	private int user_status;//
	private String remarks_job;//取消兼职
	
	private int sex_resume;//用户性别
	private String intoschool_date_resume;//用户入学时间
	private String tel;//用户入学时间
	private String hould_money;//用户入学时间
	private String real_money;//用户入学时间
	private String job_id;//用户入学时间
	private String job_id_complete;//用户入学时间
	private String job_id_cancel;//用户入学时间

	private String user_sex;//用户入学时间
	private String tu_zheng;//用户入学时间
	private String tu_fan;//用户入学时间
	
	private int pigeon_count;//鸽子数

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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName_image() {
		return name_image;
	}
	public void setName_image(String nameImage) {
		name_image = nameImage;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getRealname() {
		return realname;
	}
	public void setRealname(int realname) {
		this.realname = realname;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public String getRegedit_time() {
		return regedit_time;
	}
	public void setRegedit_time(String regeditTime) {
		regedit_time = regeditTime;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String loginTime) {
		login_time = loginTime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setQiniu(String qiniu) {
		this.qiniu = qiniu;
	}
	public String getQiniu() {
		return qiniu;
	}
	public void setComplete_job(int complete_job) {
		this.complete_job = complete_job;
	}
	public int getComplete_job() {
		return complete_job;
	}
	public void setCancel_job(int cancel_job) {
		this.cancel_job = cancel_job;
	}
	public int getCancel_job() {
		return cancel_job;
	}
	public void setTime_job(String time_job) {
		this.time_job = time_job;
	}
	public String getTime_job() {
		return time_job;
	}
	public void setRemarks_job(String remarks_job) {
		this.remarks_job = remarks_job;
	}
	public String getRemarks_job() {
		return remarks_job;
	}
	public void setSex_resume(int sex_resume) {
		this.sex_resume = sex_resume;
	}
	public int getSex_resume() {
		return sex_resume;
	}
	public void setIntoschool_date_resume(String intoschool_date_resume) {
		this.intoschool_date_resume = intoschool_date_resume;
	}
	public String getIntoschool_date_resume() {
		return intoschool_date_resume;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	public void setReal_money(String real_money) {
		this.real_money = real_money;
	}
	public String getReal_money() {
		return real_money;
	}
	public void setHould_money(String hould_money) {
		this.hould_money = hould_money;
	}
	public String getHould_money() {
		return hould_money;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setJob_id_complete(String job_id_complete) {
		this.job_id_complete = job_id_complete;
	}
	public String getJob_id_complete() {
		return job_id_complete;
	}
	public void setJob_id_cancel(String job_id_cancel) {
		this.job_id_cancel = job_id_cancel;
	}
	public String getJob_id_cancel() {
		return job_id_cancel;
	}
	public void setTu_zheng(String tu_zheng) {
		this.tu_zheng = tu_zheng;
	}
	public String getTu_zheng() {
		return tu_zheng;
	}
	public void setTu_fan(String tu_fan) {
		this.tu_fan = tu_fan;
	}
	public String getTu_fan() {
		return tu_fan;
	}
	public void setPigeon_count(int pigeon_count) {
		this.pigeon_count = pigeon_count;
	}
	public int getPigeon_count() {
		return pigeon_count;
	}
	
}
