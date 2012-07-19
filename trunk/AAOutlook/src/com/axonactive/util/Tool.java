package com.axonactive.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.axonactive.dto.Account;
import com.axonactive.dto.TimeCalendar;

public class Tool {
	
	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(date);
	}

	public static List<Account> getAllAccount() {
		List<Account> accounts;
		accounts = new ArrayList<>();
		accounts.add(new Account(1, "Meeting room 1", "mr1@axonactive.vn", "",
				""));
		accounts.add(new Account(2, "Meeting room 2", "mr2@axonactive.vn", "",
				""));
		accounts.add(new Account(3, "Meeting room 3", "mr3@axonactive.vn", "",
				""));
		accounts.add(new Account(4, "Meeting room 4", "mr4@axonactive.vn", "",
				""));
		accounts.add(new Account(5, "Meeting room 5", "mr5@axonactive.vn", "",
				""));
		accounts.add(new Account(6, "Meeting room 6", "mr6@axonactive.vn", "",
				""));
		return accounts;
	}

	public static void main(String[] args) {
		List<TimeCalendar> schedules = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		TimeCalendar schedule = null;
		for (int i = 0; i < 21; i++) {
			schedule = new TimeCalendar(format1.format(calendar.getTime()),
					calendar.getTimeInMillis());
			schedules.add(schedule);
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 30);
		}
		for (TimeCalendar sche : schedules) {
			System.out.println("Label : " + sche.getLabel() + " - Value : "
					+ sche.getValue());
		}
	}
	
	public static List<TimeCalendar> processTimeDay(Calendar calendar){
		List<TimeCalendar> schedules = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		TimeCalendar schedule = null;
		for (int i = 0; i < 21; i++) {
			schedule = new TimeCalendar(format.format(calendar.getTime()),calendar.getTimeInMillis());
			schedules.add(schedule);
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 30);
		}
		return schedules;
	}
}
