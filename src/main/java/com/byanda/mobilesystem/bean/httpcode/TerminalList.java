package com.byanda.mobilesystem.bean.httpcode;

public class TerminalList {
	
	private Long id;
	private String userName;
	private String terminalId;
	private String terminalNum;
	private String orgName;
	
	public TerminalList(Long id,String userName,String terminalId,String terminalNum,String orgName) {
		// TODO Auto-generated constructor stub
		setId(id);
		setUserName(userName);
		setTerminalNum(terminalNum);
		setTerminalId(terminalId);
		setOrgName(orgName);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
