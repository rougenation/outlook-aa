package com.axonactive.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.CalendarView;
import microsoft.exchange.webservices.data.DeleteMode;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.FolderId;
import microsoft.exchange.webservices.data.Mailbox;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;

public class unittest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			ExchangeService service = new ExchangeService();
			ExchangeCredentials wc = new WebCredentials("aavnmr", "Qp@7hNxe_y");
			service.setCredentials(wc);
			service.setUrl(new URI("https://axonvn-msvr2.teledata.local/EWS/Exchange.asmx"));
			
			SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Calendar start = Calendar.getInstance();
			start.set(2012, 6, 4, 1, 0);
			Calendar end = Calendar.getInstance();
			end.set(2012, 6, 4, 11, 0);
			CalendarView calendar = new CalendarView(start.getTime(),
					end.getTime());
			FindItemsResults<Appointment> findCalendar = service.findAppointments(new FolderId(
							WellKnownFolderName.Calendar, new Mailbox(
									"mr1@axonactive.vn")), calendar);
			for (Appointment item : findCalendar.getItems()) {
				System.out.println("Subject : " + item.getSubject()
						+ " - Location : " + item.getLocation()
						+ " - Start time : " + simp.format(item.getStart())
						+ " - End time : " + simp.format(item.getEnd()) + " - Time zone : " + item.getTimeZone());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
