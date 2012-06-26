package com.gagege.timekeep;

import java.text.DateFormat;
import java.util.Date;

public class Entry {
	private long id;
	private double hours;
	private long date;
	private String project;
	private String client;
	private String notes;
	
	public Entry(long id, int hours, long date, String project, String client,
			String notes) {
		this.id = id;
		this.hours = hours;
		this.date = date;
		this.project = project;
		this.client = client;
		this.notes = notes;
	}
	
	public Entry() {
	}
	
	public long id() {
		return id;
	}
	public void id(long id) {
		this.id = id;
	}
	public double hours() {
		return hours;
	}
	public void hours(double hours) {
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
	public String prettyDate() {
		Date d = new Date(date);
		return DateFormat.getDateInstance().format(d);
	}
}
