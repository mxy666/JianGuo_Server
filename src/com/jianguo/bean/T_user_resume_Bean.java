package com.jianguo.bean;

public class T_user_resume_Bean {

	//用户简历表
	private int id;//ID
	private int login_id;//用户登录表关联ID
	private String nickname;//昵称
	private String name;//姓名
	private String name_image;//头像
	private String school;//学校
	private String intoschool_date;//学校
	private int sex;//性别
	private int height;//身高
	private int student;//出生日期
	private String birth_date;//学生
	private String shoe_size;//鞋码
	private String clothing_size;//服装尺码
	private String sign;//个性签名
	private String label;//标签
	private String cityId;
	private String tel;
	private String sexnew;//性别
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birthDate) {
		birth_date = birthDate;
	}
	public String getShoe_size() {
		return shoe_size;
	}
	public void setShoe_size(String shoeSize) {
		shoe_size = shoeSize;
	}
	public String getClothing_size() {
		return clothing_size;
	}
	public void setClothing_size(String clothingSize) {
		clothing_size = clothingSize;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setIntoschool_date(String intoschool_date) {
		this.intoschool_date = intoschool_date;
	}
	public String getIntoschool_date() {
		return intoschool_date;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	public void setSexnew(String sexnew) {
		this.sexnew = sexnew;
	}
	public String getSexnew() {
		return sexnew;
	}
	
}
