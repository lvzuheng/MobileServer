package com.byanda.mobilesystem.bean;

public class UserList {
	private String terminalId;
	private String name;
	private String orgName;
	private String status;
	private String lon;
	private String lat;
	
	public UserList(String  terminalId,String name,String orgName,String status,String lon,String lat) {
		// TODO Auto-generated constructor stub
		setTerminalId(terminalId);
		setName(name);
		setOrgName(orgName);
		setLon(lon);
		setLat(lat);
		setStatus(status);
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getTerminalId() {
		return terminalId;
	}


	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}


}
