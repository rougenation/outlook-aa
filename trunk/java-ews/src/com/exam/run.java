package com.exam;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.CalendarView;
import microsoft.exchange.webservices.data.EmailMessage;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.FolderId;
import microsoft.exchange.webservices.data.Item;
import microsoft.exchange.webservices.data.ItemView;
import microsoft.exchange.webservices.data.Mailbox;
import microsoft.exchange.webservices.data.MessageBody;
import microsoft.exchange.webservices.data.TimeZoneDefinition;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;

public class run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExchangeService service = new ExchangeService();
			ExchangeCredentials wc = new WebCredentials("aavnmr",
					"Qp@7hNxe_y");
			service.setCredentials(wc);
			service.setUrl(new URI(
					"https://axonvn-msvr2.teledata.local/EWS/Exchange.asmx"));

//			Mailbox userMailbox = new Mailbox("mr1@axonactive.vn");
//			FolderId folderId = new FolderId(WellKnownFolderName.Contacts, userMailbox);
//			String AQS = "Category:~>\"CategoryTag\"";
//			FindItemsResults<Item> results;
//			ItemView view = new ItemView(200);
//			results = service.findItems(folderId, AQS, view);
//			for(Item item : results)
//			{
//			   item.get
//			}
			
			// get inbox
			//getInbox(service);
			// System.out.println("------------------------------------------------------------------------");
			// get calendar
//			 getAppointment(service);

			createAppointment(service);

			
//			NameResolutionCollection nrc = service.resolveName("mr");
//			Iterator<NameResolution> ite = nrc.iterator();
//			NameResolution object;
//			List<String> list = new ArrayList<String>();
//			Pattern patternTag = Pattern.compile("^mr[1-9a-z]+");
//			Matcher matcherTag;
//			String mail = "";
//			while (ite.hasNext()) {
//				object = ite.next();
//				if (object != null && object.getMailbox() != null) {
//					EmailAddress mailbox = object.getMailbox();
//					mail = mailbox.getAddress();
//					matcherTag = patternTag.matcher(mail);
//					if(matcherTag.find()){
//						System.out.println("EmailAddress = " +  mailbox.getAddress() + "-" + mailbox.getName());
//						list.add(mailbox.getAddress());
//					}
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createAppointment(ExchangeService service) {
		try {
			Appointment appointment = new Appointment(service);
			TimeZone.setDefault(TimeZone.getTimeZone("UTC+7"));
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date startDate = formatter.parse("2012-09-2 8:00:00");
			Date endDate = formatter.parse("2012-09-2 8:30:00");
			System.out.println("Start : " + startDate);
			appointment.setSubject("Status Meeting");
			appointment.setBody(new MessageBody(
					"The purpose of this meeting is to discuss status."));
			appointment.setStart(startDate);
			appointment.getRequiredAttendees().add("huy.tranquoc@axonactive.vn");
			appointment.setEnd(endDate);
			appointment.setLocation("mr1");
			
			TimeZoneDefinition td = new TimeZoneDefinition();
			
			appointment.setBody(MessageBody.getMessageBodyFromText("Test Body Msg"));
//			appointment.setMeetingTimeZone(timeZoneId, timeZoneName);
			FolderId folderID = new FolderId(WellKnownFolderName.Calendar, new Mailbox("mr1@axonactive.vn"));
			appointment.save(folderID);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getInbox(ExchangeService service) {
		try {
			SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			ItemView view = new ItemView(5);
			// view.setPropertySet(PropertySet.IdOnly);

			FindItemsResults<Item> findResults = service.findItems(
					WellKnownFolderName.Inbox, view);
			for (Item item : findResults.getItems()) {
				System.out.println("Subject : " + item.getSubject() + " - Time : ");
				item.load();
				System.out.println("Body : " + item.getBody() + " - Time : ");
//				ItemId id = new ItemId(item.getId().toString());
				// EmailMessage message = EmailMessage.bind(service, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getAppointment(ExchangeService service) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			// dateFormat.setTimeZone(TimeZone.getTimeZone("ICT"));
			Date startTime = dateFormat.parse("2012-06-12 01:00:00");
			Date endTime = dateFormat.parse("2012-06-12 11:00:00");
			System.out.println("Start : " + startTime);
			System.out.println("End : " + endTime);
			CalendarView calendar = new CalendarView(startTime, endTime);
			FindItemsResults<Appointment> findCalendar = service
					.findAppointments(new FolderId(
							WellKnownFolderName.Calendar, new Mailbox(
									"mr1@axonactive.vn")), calendar);
			Calendar cal = Calendar.getInstance();
			for (Appointment item : findCalendar.getItems()) {
				System.out.println("Subject : " + item.getSubject());
				System.out.println("Location : " + item.getLocation());
				cal.setTime(item.getStart());
				cal.add(Calendar.HOUR_OF_DAY, 7);
				System.out.println("Start time : "
						+ dateFormat.format(cal.getTime()));
				cal.setTime(item.getEnd());
				cal.add(Calendar.HOUR_OF_DAY, 7);
				System.out.println("End time : "
						+ dateFormat.format(cal.getTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
