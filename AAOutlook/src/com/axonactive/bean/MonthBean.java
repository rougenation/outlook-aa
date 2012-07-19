package com.axonactive.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "monthBean")
@SessionScoped
public class MonthBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int typeRoom; // mr1 - mr6
	private int typeView; // day - month - week
	
	public void onload() {

	}

	public void go() {
		try {
			System.out.println("Type Room : " + typeRoom);
			System.out.println("Type View : " + typeView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToMonthBefore() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToThisMonth() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToMonthAfter() {
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
