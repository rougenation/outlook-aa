package com.axonactive.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "dayBean")
@SessionScoped
public class DayBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int typeRoom; // mr1 - mr6
	private int typeView; // day - month - week
	
	public DayBean() {
		
	}

	public void onload() {

	}

	public void go() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToDayBefore() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToToday() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToDayAfter() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
}
