package com.axonactive.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.AppointmentOccurrenceId;
import microsoft.exchange.webservices.data.CalendarResponseMessage;
import microsoft.exchange.webservices.data.CalendarView;
import microsoft.exchange.webservices.data.EmailMessage;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.FindFoldersResults;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.Folder;
import microsoft.exchange.webservices.data.FolderId;
import microsoft.exchange.webservices.data.FolderView;
import microsoft.exchange.webservices.data.Item;
import microsoft.exchange.webservices.data.ItemId;
import microsoft.exchange.webservices.data.ItemView;
import microsoft.exchange.webservices.data.Mailbox;
import microsoft.exchange.webservices.data.MeetingMessage;
import microsoft.exchange.webservices.data.MessageBody;
import microsoft.exchange.webservices.data.PropertySet;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;

public class run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExchangeService service = new ExchangeService();
			ExchangeCredentials wc = new WebCredentials("huytranquoc",
					"PN+JHBF5aZ");
			service.setCredentials(wc);
			service.setUrl(new URI(
					"https://axonvn-msvr2.teledata.local/EWS/Exchange.asmx"));

			// get inbox
			// getInbox(service);
			// System.out.println("------------------------------------------------------------------------");
			// get calendar
			getAppointment(service);
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
				System.out.println("Subject : " + item.getSubject()
						+ " - Time : ");
				ItemId id = new ItemId(item.getId().toString());
				// EmailMessage message = EmailMessage.bind(service, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getAppointment(ExchangeService service) {
		try {
			SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Calendar start = Calendar.getInstance();
			start.set(2012, 6, 4, 6, 0);
			Calendar end = Calendar.getInstance();
			end.set(2012, 6, 4, 20, 0);
			CalendarView calendar = new CalendarView(start.getTime(),
					end.getTime());
			FindItemsResults<Appointment> findCalendar = service
					.findAppointments(new FolderId(
							WellKnownFolderName.Calendar, new Mailbox(
									"mr1@axonactive.vn")), calendar);
			for (Appointment item : findCalendar.getItems()) {
				System.out.println("Subject : " + item.getSubject()
						+ " - Location : " + item.getLocation()
						+ " - Start time : " + simp.format(item.getStart())
						+ " - End time : " + simp.format(item.getEnd()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
