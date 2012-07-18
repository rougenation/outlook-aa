package com.axonactive.dto;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String subject;
	private String location;
	private String content;
	private Date startTime;
	private Date endTime;
	
	public Item(){
		id = subject = location = content = "";
		startTime = new Date();
		endTime = new Date();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
