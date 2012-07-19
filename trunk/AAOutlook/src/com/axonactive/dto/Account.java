package com.axonactive.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String label;
	private String url;
	private String username;
	private String password;
	private List<Item> items;
	
	public Account(){
		url = username = password = "";
		items = new ArrayList<Item>();
	}
	
	public Account(int id, String label, String url, String username, String password){
		this.id = id;
		this.label = label;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
