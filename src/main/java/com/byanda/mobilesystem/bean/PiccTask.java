package com.byanda.mobilesystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PICCTASK")
public class PiccTask {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="strcaseNewNo")
	private String strcaseNewNo;
	
	@Column(name="ncaseType")
	private String ncaseType;
	
	@Column(name="nremark1")
	private String nremark1;
	
	@Column(name="strReason")
	private String strReason;
	
	@Column(name="strRemark1")
	private String strRemark1;
	
	@Column(name="strlicenseNo")
	private String strlicenseNo;
	
	@Column(name="strlocation")
	private String strlocation;
	
	@Column(name="strRiskLevel")
	private String strRiskLevel;
	
	@Column(name="strHurt")
	private String strHurt;
	
	@Column(name="strDriver")
	private String strDriver;
	
	@Column(name="strStartOffTime")
	private String strStartOffTime;
	
	@Column(name="strReportTime")
	private String strReportTime;
	
	@Column(name="operName")
	private String operName;
	
	@Column(name="terminalId")
	private String terminalId;
	
	@Column(name="flag")
	private Integer flag;
	
	
	

	public PiccTask() {
		super();
	}

	public PiccTask(String password, String strcaseNewNo, String ncaseType, String nremark1, String strReason,
			String strRemark1, String strlicenseNo, String strlocation, String strRiskLevel, String strHurt,
			String strDriver, String strStartOffTime, String strReportTime, String operName, String terminalId,
			Integer flag) {
		super();
		//this.id = id;
		this.password = password;
		this.strcaseNewNo = strcaseNewNo;
		this.ncaseType = ncaseType;
		this.nremark1 = nremark1;
		this.strReason = strReason;
		this.strRemark1 = strRemark1;
		this.strlicenseNo = strlicenseNo;
		this.strlocation = strlocation;
		this.strRiskLevel = strRiskLevel;
		this.strHurt = strHurt;
		this.strDriver = strDriver;
		this.strStartOffTime = strStartOffTime;
		this.strReportTime = strReportTime;
		this.operName = operName;
		this.terminalId = terminalId;
		this.flag = flag;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStrcaseNewNo() {
		return strcaseNewNo;
	}

	public void setStrcaseNewNo(String strcaseNewNo) {
		this.strcaseNewNo = strcaseNewNo;
	}

	public String getNcaseType() {
		return ncaseType;
	}

	public void setNcaseType(String ncaseType) {
		this.ncaseType = ncaseType;
	}

	public String getNremark1() {
		return nremark1;
	}

	public void setNremark1(String nremark1) {
		this.nremark1 = nremark1;
	}

	public String getStrReason() {
		return strReason;
	}

	public void setStrReason(String strReason) {
		this.strReason = strReason;
	}

	public String getStrRemark1() {
		return strRemark1;
	}

	public void setStrRemark1(String strRemark1) {
		this.strRemark1 = strRemark1;
	}

	public String getStrlicenseNo() {
		return strlicenseNo;
	}

	public void setStrlicenseNo(String strlicenseNo) {
		this.strlicenseNo = strlicenseNo;
	}

	public String getStrlocation() {
		return strlocation;
	}

	public void setStrlocation(String strlocation) {
		this.strlocation = strlocation;
	}

	public String getStrRiskLevel() {
		return strRiskLevel;
	}

	public void setStrRiskLevel(String strRiskLevel) {
		this.strRiskLevel = strRiskLevel;
	}

	public String getStrHurt() {
		return strHurt;
	}

	public void setStrHurt(String strHurt) {
		this.strHurt = strHurt;
	}

	public String getStrDriver() {
		return strDriver;
	}

	public void setStrDriver(String strDriver) {
		this.strDriver = strDriver;
	}

	public String getStrStartOffTime() {
		return strStartOffTime;
	}

	public void setStrStartOffTime(String strStartOffTime) {
		this.strStartOffTime = strStartOffTime;
	}

	public String getStrReportTime() {
		return strReportTime;
	}

	public void setStrReportTime(String strReportTime) {
		this.strReportTime = strReportTime;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
