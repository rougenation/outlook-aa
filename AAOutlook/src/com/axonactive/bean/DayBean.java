package com.axonactive.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.axonactive.dto.Account;
import com.axonactive.dto.TimeCalendar;
import com.axonactive.util.Tool;

@ManagedBean(name = "dayBean")
@SessionScoped
public class DayBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int typeRoom; // mr1 - mr6 (1-6)
	private int typeView; // day - month - week (1-3)
	private List<Account> accounts;
	private List<Account> selectedAccont;
	private List<TimeCalendar> timeCalendar;
	Calendar calendar;
	private String nameRoom;
	private Date display;

	public DayBean() {
		typeRoom = 0;
		typeView = 1;
		timeCalendar = new ArrayList<>();
		accounts = new ArrayList<Account>();
		selectedAccont = new ArrayList<>();
	}

	// Onload
	public void onload() {
		processCalendar();
		accounts.clear();
		accounts = Tool.getAllAccount();
		if (accounts.size() > 1) {
			processSelectedAccount();
			processRenderView();
		}
	}
	
	public void processCalendar(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(session.getAttribute("calendar") == null){
			calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			display = calendar.getTime();
			session.setAttribute("calendar", calendar);
			timeCalendar = Tool.processTimeDay(calendar);
		}else{
			calendar = (Calendar) session.getAttribute("calendar");
			timeCalendar = Tool.processTimeDay(calendar);
		}
	}

	// Process Selected Account
	public void processSelectedAccount() {
		try {
			selectedAccont.clear();
			if(typeRoom == 0){
				nameRoom = "All meeting room";
				selectedAccont = accounts;
				return;
			}
			for (int i = 0; i < accounts.size(); i++) {
				if (typeRoom == accounts.get(i).getId()) {
					selectedAccont.add(accounts.get(i));
					nameRoom = selectedAccont.get(0).getLabel();
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

	public void processViewDay() {
	}

	public void processViewWeek() {

	}

	public void processViewMonth() {

	}

	// Go
	public void go() {
		try {
			System.out.println("Type Room : " + typeRoom);
			System.out.println("Type View : " + typeView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Go to Day Before
	public void goToDayBefore() {
		try {
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.get(Calendar.DAY_OF_MONTH) - 1);
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			display = calendar.getTime();
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("calendar", calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Go to Today
	public void goToToday() {
		try {
			calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			display = calendar.getTime();
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("calendar", calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Go to Day After
	public void goToDayAfter() {
		try {
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.get(Calendar.DAY_OF_MONTH) + 1);
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			display = calendar.getTime();
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("calendar", calendar);
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

	public String getNameRoom() {
		return nameRoom;
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}

	public Date getDisplay() {
		return display;
	}

	public void setDisplay(Date display) {
		this.display = display;
	}
}
