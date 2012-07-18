package com.axonactive.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.axonactive.dto.Account;
import com.axonactive.dto.Schedule;
import com.axonactive.util.Tool;

@ManagedBean(name="calendarBean")
@SessionScoped
public class CalendarBean {
	
	private List<Account> accounts;
	private List<Schedule> schedules;
	private int mode;
	
	public CalendarBean(){
		accounts = new ArrayList<Account>();
		setSchedules(new ArrayList<Schedule>());
		/*
		 * 1 : day
		 * 2 : week
		 * 3 : month
		 * */
		mode = 0;
	}
	
	public void onload(){
		if(!Tool.isPostBack()){
			System.out.println("ABC");
			initalizeDay();
		}
	}

	public void initalizeDay(){
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		schedules.clear();
		Schedule schedule = null;
		for (int i = 0; i < 21; i++) {
			schedule = new Schedule(format1.format(calendar.getTime()), calendar.getTimeInMillis());
			schedules.add(schedule);
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 30);
		}
	}
	
	protected void initalizeMonth(){
		for (int i = 0; i < 21; i++) {
			
		}
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
}
