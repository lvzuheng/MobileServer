package com.byanda.mobilesystem.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOG_ZONE")
public class ZoneLog {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="terminalId")
	private String terminalId;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date")
	private Timestamp date;
	
	//1表示一般围栏,2表示上班围栏
	@Column(name="type")
	private int type;
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
}
