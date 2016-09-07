package com.jianguo.bean;

public class T_Money_Bean {
	private double money;//余额
	private double cash;//提现总额
	private double wage;//工资
	public void setMoney(double money) {
		this.money = money;
	}
	public double getMoney() {
		return money;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public double getCash() {
		return cash;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	public double getWage() {
		return wage;
	}
}
