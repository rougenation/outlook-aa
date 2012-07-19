package com.axonactive.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.axonactive.dto.Account;
import com.axonactive.dto.TimeCalendar;
import com.axonactive.util.Tool;

@ManagedBean(name = "monthBean")
@SessionScoped
public class MonthBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int typeRoom; // mr1 - mr6
	private int typeView; // day - month - week
	private List<Account> accounts;
	private List<Account> selectedAccont;
	private List<TimeCalendar> timeCalendar;
	
	public MonthBean(){
		typeRoom = 0;
		typeView = 1;
		accounts = new ArrayList<Account>();
		selectedAccont = new ArrayList<>();
	}
	
	//Onload
	public void onload() {
		accounts = Tool.getAllAccount();
		if (accounts.size() > 0) {
			processSelectedAccount();
			processRenderView();
		}
	}

	// Process Selected Account
	public void processSelectedAccount() {
		try {
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i).getId() == typeRoom) {
					selectedAccont.clear();
					selectedAccont.add(accounts.get(i));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Process Render View
	public void processRenderView() {
		try {
			switch (typeView) {
			case 1:
				processViewDay();
				break;
			case 2:
				processViewWeek();
				break;
			case 3:
				processViewMonth();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processViewDay(){
		
	}
	
	public void processViewWeek(){
		
	}
	
	public void processViewMonth(){
		
	}
	
	//Go
	public void go() {
		try {
			System.out.println("Type Room : " + typeRoom);
			System.out.println("Type View : " + typeView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Go to month before
	public void goToMonthBefore() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Go to this month
	public void goToThisMonth() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Go to Month After
	public void goToMonthAfter() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * ********** SETTER AND GETTER BELOW ***********
	 */

	public int getTypeRoom() {
		return typeRoom;
	}

	public void setTypeRoom(int typeRoom) {
		this.typeRoom = typeRoom;
	}

	public int getTypeView() {
		return typeView;
	}

	public void setTypeView(int typeView) {
		this.typeView = typeView;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Account> getSelectedAccont() {
		return selectedAccont;
	}

	public void setSelectedAccont(List<Account> selectedAccont) {
		this.selectedAccont = selectedAccont;
	}

	
	public List<TimeCalendar> getTimeCalendar() {
		return timeCalendar;
	}
	

	public void setTimeCalendar(List<TimeCalendar> timeCalendar) {
		this.timeCalendar = timeCalendar;
	}
}
