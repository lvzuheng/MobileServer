package com.byanda.mobilesystem.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IntercomUser")
public class IntercomUser {

	@Id
	@GeneratedValue
	private long id;

	@Column(name="groupId")
	private int groupId;

	@Column(name="userId")
	private int userId;

	@Column(name="userRights")
	private int userRights;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserRights() {
		return userRights;
	}

	public void setUserRights(int userRights) {
		this.userRights = userRights;
	}



}
