package com.gagege.timekeep;

import java.util.Date;

public class Entry {
	private int id;
	private int hours;
	private Date date;
	private String project;
	private String client;
	private String notes;
	
	public int id() {
		return id;
	}
	public void id(int id) {
		this.id = id;
	}
	public int hours() {
		return hours;
	}
	public void hours(int hours) {
		this.hours = hours;
	}
	public Date date() {
		return date;
	}
	public void date(Date date) {
		this.date = date;
	}
	public String project() {
		return project;
	}
	public void project(String project) {
		this.project = project;
	}
	public String client() {
		return client;
	}
	public void client(String client) {
		this.client = client;
	}
	public String notes() {
		return notes;
	}
	public void notes(String notes) {
		this.notes = notes;
	}
}
