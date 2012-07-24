package com.axonactive.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.CalendarView;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.FolderId;
import microsoft.exchange.webservices.data.Mailbox;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;

import com.axonactive.dto.Meeting;

public class Tool {

	public static List<Meeting> getListMeeting(String username, String password, Date start, Date end) {
		List<Meeting> meetings = new ArrayList<Meeting>();
		try {
			ExchangeService service = new ExchangeService();
			ExchangeCredentials wc = new WebCredentials("huytranquoc", "PN+JHBF5aZ");
			service.setCredentials(wc);
			service.setUrl(new URI( "https://axonvn-msvr2.teledata.local/EWS/Exchange.asmx"));
			
			CalendarView calendar = new CalendarView(start, end);
			FindItemsResults<Appointment> findCalendar = service.findAppointments(
					new FolderId( WellKnownFolderName.Calendar, new Mailbox("mr1@axonactive.vn")), calendar);
			
			for (Appointment app : findCalendar.getItems()) {
				meetings.add(new Meeting(app.getId().getUniqueId(),app.getSubject(),
						app.getLocation(),"",app.getStart(),app.getEnd()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meetings;
	}
}
