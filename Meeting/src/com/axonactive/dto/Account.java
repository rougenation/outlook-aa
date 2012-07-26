package com.axonactive.dto;

import java.util.List;

public class Account {

	private String id;
	private String name;
	private String username;
	private String password;
	private List<Meeting> meetings;

	public Account(){
		id = name = username = password = "";
	}
	
	public Account(String id, String name, String username, String password){
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
	
	
}
