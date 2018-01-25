package com.byanda.mobilesystem.bean;

public class Position {

	private String user;
	private double lon;
	private double lat;
	
	public Position(String user,double lon,double lat) {
		// TODO Auto-generated constructor stub
		setUser(user);
		setLon(lon);
		setLat(lat);
	}
	public Position() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
}
