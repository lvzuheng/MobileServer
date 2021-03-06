package com.byanda.mobilesystem.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="userName")
	private String userName;

	@Column(name="terminalId")
	private String terminalId;

	@Column(name="terminalNum")
	private String terminalNum;

	@Column(name="longitude")
	private String longitude;

	@Column(name="latitude")
	private String latitude;

	@Column(name="lastTime")
	private Date lastTime;

	@Column(name="orgId")
	private Integer orgId;
	
	@Column(name="clockzoneId")
	private Integer clockzoneId;
	
	@Column(name="KSID")
	private Integer KSID;

	@Column(name="KQID")
	private Integer KQID;

	@Column(name="KDID")
	private Integer KDID;

	@Column(name="KSNO")
	private String KSNO;

	@Column(name="EVENTID")
	private Integer EVENTID;

	@Column(name="UID")
	private Integer UID;
	
	@Column(name="LON")
	private String LON;
	
	@Column(name="LAT")
	private String LAT;
	
	@Column(name="EID")
	private String EID;
	
	@Column(name="KDNAME")
	private String KDNAME;
	
	@Column(name="UNAME")
	private String UNAME;
	
	

	public Integer getKSID() {
		return KSID;
	}

	public void setKSID(Integer kSID) {
		KSID = kSID;
	}

	public Integer getKQID() {
		return KQID;
	}

	public void setKQID(Integer kQID) {
		KQID = kQID;
	}

	public Integer getKDID() {
		return KDID;
	}

	public void setKDID(Integer kDID) {
		KDID = kDID;
	}

	public String getKSNO() {
		return KSNO;
	}

	public void setKSNO(String kSNO) {
		KSNO = kSNO;
	}

	public Integer getEVENTID() {
		return EVENTID;
	}

	public void setEVENTID(Integer eVENTID) {
		EVENTID = eVENTID;
	}

	public Integer getUID() {
		return UID;
	}

	public void setUID(Integer uID) {
		UID = uID;
	}

	public String getLON() {
		return LON;
	}

	public void setLON(String lON) {
		LON = lON;
	}

	public String getLAT() {
		return LAT;
	}

	public void setLAT(String lAT) {
		LAT = lAT;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalNum() {
		return terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getClockzoneId() {
		return clockzoneId;
	}

	public void setClockzoneId(Integer clockzoneId) {
		this.clockzoneId = clockzoneId;
	}

	public String getEID() {
		return EID;
	}

	public void setEID(String eID) {
		EID = eID;
	}

	public String getUNAME() {
		return UNAME;
	}

	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}

	public String getKDNAME() {
		return KDNAME;
	}

	public void setKDNAME(String kDNAME) {
		KDNAME = kDNAME;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}