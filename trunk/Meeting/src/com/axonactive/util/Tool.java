package com.axonactive.util;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.CalendarView;
import microsoft.exchange.webservices.data.EmailAddress;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.FolderId;
import microsoft.exchange.webservices.data.Mailbox;
import microsoft.exchange.webservices.data.NameResolution;
import microsoft.exchange.webservices.data.NameResolutionCollection;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.axonactive.dto.Account;
import com.axonactive.dto.Meeting;
import com.axonactive.dto.Room;
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
	
	public static Account getAccount(String file_url){
		Account account = new Account();
		try{
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(file_url);
			Element node;
			if (xmlFile.exists()) {
				Document document = builder.build(xmlFile);
				Element root = document.getRootElement();
				List list = root.getChildren("account");
				for (int i = 0; i < list.size(); i++) {
					node = (Element) list.get(i);
					account = new Account(node.getChildText("username"), node.getChildText("password"));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			account = new Account();
		}
		return account;
	}
	
	public static List<Meeting> getListMeeting(String url, String username,
			String password, String mailbox, Date start, Date end) {
		List<Meeting> meetings = new ArrayList<Meeting>();
		System.out.println("List Meeintg");
		try {
//			Meeting meeting = new Meeting("1", "KFC", "Axon", "");
//			Calendar time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 8);
//			time.set(Calendar.MINUTE, 0);
//			meeting.setStartTime(time.getTime());
//			time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 10);
//			time.set(Calendar.MINUTE, 0);
//			meeting.setEndTime(time.getTime());
//			meetings.add(meeting);
//			
//			meeting = new Meeting("2", "Meeting", "Axon", "");
//			time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 10);
//			time.set(Calendar.MINUTE, 0);
//			meeting.setStartTime(time.getTime());
//			time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 10);
//			time.set(Calendar.MINUTE, 30);
//			meeting.setEndTime(time.getTime());
//			meetings.add(meeting);
//			
//			meeting = new Meeting("3", "Appointment", "Axon", "");
//			time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 10);
//			time.set(Calendar.MINUTE, 30);
//			meeting.setStartTime(time.getTime());
//			time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 11);
//			time.set(Calendar.MINUTE, 0);
//			meeting.setEndTime(time.getTime());
//			meetings.add(meeting);
//			
//			meeting = new Meeting("4", "Planning", "Axon", "");
//			time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 13);
//			time.set(Calendar.MINUTE, 30);
//			meeting.setStartTime(time.getTime());
//			time = Calendar.getInstance();
//			time.set(Calendar.HOUR_OF_DAY, 15);
//			time.set(Calendar.MINUTE, 0);
//			meeting.setEndTime(time.getTime());
//			meetings.add(meeting);
			
			ExchangeService service = new ExchangeService();
			ExchangeCredentials wc = new WebCredentials(username, password);
			service.setCredentials(wc);
			service.setUrl(new URI(url));
			
			start.setHours(1);
			start.setMinutes(0);
			end.setHours(11);
			end.setMinutes(0);
			System.out.println("Start : " + start);
			System.out.println("End : " + end);
			CalendarView calendar = new CalendarView(start, end);
			FindItemsResults<Appointment> findCalendar = service.findAppointments(
					new FolderId(WellKnownFolderName.Calendar, new Mailbox(mailbox)), calendar);
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
			return new ArrayList<Meeting>();
		}
		return meetings;
	}

	public static List<Room> getListRoom(String url, String username,
			String password) {
		List<Room> rooms = new ArrayList<Room>();
		try {
//			SAXBuilder builder = new SAXBuilder();
//			File xmlFile = new File(file_url);
//			Element node;
//			if (xmlFile.exists()) {
//				Document document = builder.build(xmlFile);
//				Element root = document.getRootElement();
//				List list = root.getChildren("room");
//				for (int i = 0; i < list.size(); i++) {
//					node = (Element) list.get(i);
//					rooms.add(new Room(node.getChildText("id"), 
//							node.getChildText("name"), node.getChildText("mailbox")));
//					System.out.println("ABC");
//				}
//			}
			ExchangeService service = new ExchangeService();
			ExchangeCredentials wc = new WebCredentials(username, password);
			service.setCredentials(wc);
			service.setUrl(new URI(url));
			
			NameResolutionCollection nrc = service.resolveName("mr"); // search with mr keyword
			Iterator<NameResolution> ite = nrc.iterator(); // return to iterator
			NameResolution object; // define an object to assign value
			EmailAddress mailbox; // define an mailbox object
			Room room; // define an room object
			Pattern patternTag = Pattern.compile("^mr[1-9a-z]+"); // regular expression
			Matcher matcherTag;
			String mail = "";
			while (ite.hasNext()) {
				object = ite.next();
				if (object != null && object.getMailbox() != null) {
					mailbox = object.getMailbox();
					mail = mailbox.getAddress();
					matcherTag = patternTag.matcher(mail);
					if(matcherTag.find()){
						room = new Room();
						System.out.println("EmailAddress = " +  mailbox.getAddress() + "-" + mailbox.getName());
						room.setName(mailbox.getName());
						room.setMailbox(mailbox.getAddress());
						rooms.add(room);
					}
				}
			}
			System.out.println("Size : " + rooms.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	public static String getUrl(String file_url){
		String url = "";
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
					url = node.getChildText("url");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
