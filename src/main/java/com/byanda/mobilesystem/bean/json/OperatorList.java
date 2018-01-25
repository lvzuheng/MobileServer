package com.byanda.mobilesystem.bean.json;

public class OperatorList {

	private Long id;
	private String name;
	private String charactor;
	private String organizationName;
	
	public OperatorList(Long id,String name,String charactor,String organizationName) {
		// TODO Auto-generated constructor stub
		setId(id);
		setName(name);
		setOrganizationName(organizationName);
		setCharactor(charactor);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}


	public String getCharactor() {
		return charactor;
	}

	public void setCharactor(String charactor) {
		this.charactor = charactor;
	}
	
}
