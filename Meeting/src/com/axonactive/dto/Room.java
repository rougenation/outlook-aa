package com.axonactive.dto;

import java.util.List;

public class Room {

	private String id;
	private String name;
	private String mailbox;
	private List<Meeting> meetings;

	public Room(){
		id = name = "";
	}
	
	public Room(String id, String name, String mailbox){
		this.id = id;
		this.name = name;
		this.mailbox = mailbox;
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

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
}
