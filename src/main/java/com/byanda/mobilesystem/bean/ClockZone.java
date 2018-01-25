package com.byanda.mobilesystem.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clockzone")
public class ClockZone {
	
	@Id
	@GeneratedValue
	private Integer zoneId;
	
	@Column(name="zoneName")
	private String zoneName;
	
	@Column(name="zonePoints")
	private String zonePoints;
	
	@Column(name="terminalIds")
	private ArrayList<String> terminalIds;
	
	@Column(name="date")
	private Timestamp date;
	
	@Column(name="orgId")
	private Integer orgId;
	
	@Column(name="operName")
	private String operName;

	@Column(name="zoneTime")
	private String zoneTime;

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getZonePoints() {
		return zonePoints;
	}

	public void setZonePoints(String zonePoints) {
		this.zonePoints = zonePoints;
	}

	public ArrayList<String> getTerminalIds() {
		return terminalIds;
	}

	public void setTerminalIds(ArrayList<String> terminalIds) {
		this.terminalIds = terminalIds;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getZoneTime() {
		return zoneTime;
	}

	public void setZoneTime(String zoneTime) {
		this.zoneTime = zoneTime;
	}

	
}