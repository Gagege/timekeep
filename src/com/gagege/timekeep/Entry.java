package com.gagege.timekeep;

public class Entry {
	private long id;
	private int hours;
	private long date;
	private String project;
	private String client;
	private String notes;
	
	public long id() {
		return id;
	}
	public void id(long id) {
		this.id = id;
	}
	public int hours() {
		return hours;
	}
	public void hours(int hours) {
		this.hours = hours;
	}
	public long date() {
		return date;
	}
	public void date(long date) {
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
