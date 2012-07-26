package com.axonactive.util;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.CalendarView;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.FolderId;
import microsoft.exchange.webservices.data.Mailbox;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.axonactive.dto.Account;
import com.axonactive.dto.Meeting;
import com.axonactive.dto.Time;

public class Tool {

	public static List<Time> getListTime(Calendar calendar) {
		List<Time> times = new ArrayList<Time>();
		try{
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Time schedule = null;
			for (int i = 0; i < 21; i++) {
				schedule = new Time(format.format(calendar.getTime()), calendar.getTime().getTime());
				times.add(schedule);
				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 30);
				calendar.set(Calendar.SECOND, 0);
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return times;
	}
	
	public static List<Meeting> getListMeeting(String username,
			String password, Date start, Date end) {
		List<Meeting> meetings = new ArrayList<Meeting>();
		try {
			ExchangeService service = new ExchangeService();
			ExchangeCredentials wc = new WebCredentials(username, password);
			service.setCredentials(wc);
			service.setUrl(new URI("https://axonvn-msvr2.teledata.local/EWS/Exchange.asmx"));
			
			start.setHours(1);
			start.setMinutes(0);
			end.setHours(11);
			end.setMinutes(0);
			System.out.println("Start : " + start);
			System.out.println("End : " + end);
			
			CalendarView calendar = new CalendarView(start, end);
			FindItemsResults<Appointment> findCalendar = service.findAppointments(
					new FolderId(WellKnownFolderName.Calendar, new Mailbox("mr1@axonactive.vn")), calendar);
			SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar temp = Calendar.getInstance();
			Meeting meeting;
			for (Appointment app : findCalendar.getItems()) {
				meeting = new Meeting(app.getId().getUniqueId(), app.getSubject(), app.getLocation(),"");
				temp.setTime(app.getStart());
				temp.add(Calendar.HOUR_OF_DAY, 7);
				meeting.setStartTime(temp.getTime());
				
				temp.setTime(app.getEnd());
				temp.add(Calendar.HOUR_OF_DAY, 7);
				meeting.setEndTime(temp.getTime());
				
				meetings.add(meeting);
				
				System.out.println("A : " +app.getTimeZone() + " - " + simp.format(app.getStart()) + "-" + simp.format(app.getEnd()));
				System.out.println("B : " +app.getTimeZone() + " - " + app.getStart() + "-" + app.getEnd());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meetings;
	}

	public static List<Account> getListAccount(String file_url) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(file_url);
			Element node;
			if (xmlFile.exists()) {
				Document document = builder.build(xmlFile);
				Element root = document.getRootElement();
				List list = root.getChildren("account");
				for (int i = 0; i < list.size(); i++) {
					node = (Element) list.get(i);
					accounts.add(new Account(node.getChildText("id"), 
							node.getChildText("name"), node.getChildText("username"), 
							node.getChildText("password")));
				}
			}
			System.out.println("Size : " + accounts.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}
}
