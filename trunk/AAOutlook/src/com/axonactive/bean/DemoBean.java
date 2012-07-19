package com.axonactive.bean;

import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="demoBean")
@SessionScoped
public class DemoBean {

	private ArrayList<String> day;
	
	public DemoBean(){
		day = new ArrayList<>();
	}
	
	public void onload(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.JULY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int totalday = calendar.getMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= totalday; i++) {
			
		}
	}

	public ArrayList<String> getDay() {
		return day;
	}

	public void setDay(ArrayList<String> day) {
		this.day = day;
	}
	
	
}
