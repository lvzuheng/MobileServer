package com.byanda.mobilesystem.bean.httpcode;

public class OrganizationList {

	private int id;
	private String name;
	private String parent;
	
	public OrganizationList(int id,String name,String parent) {
		// TODO Auto-generated constructor stub
		setId(id);
		setName(name);
		setParent(parent);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
