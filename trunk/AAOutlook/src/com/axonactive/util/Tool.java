package com.axonactive.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import com.axonactive.dto.Schedule;

public class Tool {

	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(date);
	}

	public static boolean isPostBack(){		 				
		return FacesContext.getCurrentInstance().isPostback();		  
	}
	
	public static void main(String[] args) {
		List<Schedule> schedules = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Schedule schedule = null;
		for (int i = 0; i < 21; i++) {
			schedule = new Schedule(format1.format(calendar.getTime()), calendar.getTimeInMillis());
			schedules.add(schedule);
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 30);
		}
		for (Schedule sche : schedules) {
			System.out.println("Label : " + sche.getLabel() + " - Value : " + sche.getValue());
		}
	}
}
