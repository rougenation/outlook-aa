package com.axonactive.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private String url;
	private String username;
	private String password;
	private List<Item> items;
	
	public Account(){
		url = username = password = "";
		items = new ArrayList<Item>();
	}
	
	public Account(String url, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
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
}
