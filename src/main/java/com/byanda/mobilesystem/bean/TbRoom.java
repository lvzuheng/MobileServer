package com.byanda.mobilesystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBROOM")

public class TbRoom {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="userName")
	private String userName;
//	@Column(name="terminalId")
//	private String terminalId;
	
//	@Column(name="channel")
//	private int channel; 
	
//	@Column(name="room")
//	private String room;
	
	@Column(name="roomName")
	private String roomName;
	
	@Column(name="authority")
	private int authority;
	
	@Column(name="roomId")
	private int roomId;
	
	
	public TbRoom(){
		
	}
	
	public TbRoom(String roomName){
		this.roomName = roomName;
	}
	
	public TbRoom(int roomId){
		this.roomId = roomId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	
}
