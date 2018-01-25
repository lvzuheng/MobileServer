package com.byanda.mobilesystem.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ZONE")
public class Zone {
	@Id()
	@GeneratedValue
	@Column(name="zoneID")
	private int zoneID;
	
	@Column(name="zoneName")
	private String zoneName;
	
	@Column(name="zoneType")
	private int zoneType;
	
	@Column(name="terminalId")
	private String terminalId;
	
	@Column(name="zonePoints")
	private String zonePoints;
	
	@Column(name="date")
	private Timestamp date;

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public int getZoneType() {
		return zoneType;
	}

	public void setZoneType(int zoneType) {
		this.zoneType = zoneType;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getZonePoints() {
		return zonePoints;
	}

	public void setZonePoints(String zonePoints) {
		this.zonePoints = zonePoints;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
