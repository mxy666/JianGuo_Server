package com.jianguo.bean;

/**
 * �̼���Ϣʵ��bean
 * Created by Administrator on 2016/9/18.
 */
public class MerchantInfo {
    private String tel;
    private int loginId;
    private String password;
    private String token;
    private int permissions;//�̼�Ȩ�� 0���ⲿ�̼ң�1���ڲ�
    private int resumeStatus;//�Ƿ���д�̼�������Ϣ 0δ��д 1�Ѿ���д
    private int payStatus;//�Ƿ�������֧������

    private String name;
    private String nickName;
    private String userImage;
    private String contactName;
    private String contactPhone;
    private String email;
    private String province;
    private String city;
    private String companyAddress;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public int getResumeStatus() {
        return resumeStatus;
    }

    public void setResumeStatus(int resumeStatus) {
        this.resumeStatus = resumeStatus;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
