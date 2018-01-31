package com.byanda.mobilesystem.bean.httpcode;

public class LogList {
	
	private String operator;
	private String content;
	private String date;
	private String orgName;
	
	public LogList(String operator,String date,String content,String orgName) {
		// TODO Auto-generated constructor stub
		setOperator(operator);
		setDate(date);
		setContent(content);
		setOrgName(orgName);
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
