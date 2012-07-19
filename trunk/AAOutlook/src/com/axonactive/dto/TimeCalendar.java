package com.axonactive.dto;

import java.io.Serializable;

public class TimeCalendar implements Serializable{

	private static final long serialVersionUID = 1L;
	private String label;
	private long value;
	
	public TimeCalendar(){
		label = "";
		value = 0;
	}
	
	public TimeCalendar(String label, long value){
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
