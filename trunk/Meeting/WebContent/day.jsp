<%@page import="java.text.DateFormat"%>
<%@page import="com.axonactive.util.Tool"%>
<%@page import="com.axonactive.dto.Meeting"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.axonactive.dto.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.axonactive.dto.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Axon Active - Meeting room</title>
<link rel="stylesheet" type="text/css" href="resources/css/reset.css" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
</head>
<body>
	<%
		//use for meeting
		DateFormat df = new SimpleDateFormat("HH:mm");
		DateFormat display = DateFormat.getDateInstance(DateFormat.FULL);
		int index = 0;
		List<Account> accounts = new ArrayList<Account>();
		List<Time> times = new ArrayList<Time>();
		Calendar calendar = Calendar.getInstance();
		if(request.getAttribute("calendar") != null){
			calendar = (Calendar)request.getAttribute("calendar");
		}
		if(request.getAttribute("accounts") != null){
			accounts = (List<Account>)request.getAttribute("accounts");
			if(request.getAttribute("times") != null){
				times = (List<Time>)request.getAttribute("times");
			}
		}else{
			String para = "";
			System.out.println(calendar.get(Calendar.MONTH));
			para = String.format("day=%s&month=%s&year=%s", 
					calendar.get(Calendar.DAY_OF_MONTH) , calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.YEAR));
			response.sendRedirect("day?" + para);
		}				
	%>
	<div class="wrapper">
		<span id="display-time">
			<%=display.format(calendar.getTime())%>
		</span>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<%
					String para = "";
				%>
				<td width="30%" align="left">
					<%						
						para = String.format("day=%s&month=%s&year=%s", 
								calendar.get(Calendar.DAY_OF_MONTH) - 1, calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.YEAR));
					%>
					<a class="link" href="day?<%=para%>">&laquo; Go to day before</a>
				</td>
				<td width="40%" align="center">
					<%
						Calendar today = Calendar.getInstance();
						para = String.format("day=%s&month=%s&year=%s", 
								today.get(Calendar.DAY_OF_MONTH) , today.get(Calendar.MONTH) + 1,today.get(Calendar.YEAR));
					%>
					<a class="link" href="day?<%=para%>">Go to today</a>
				</td>
				<td width="30%" align="right">
					<%						
						para = String.format("day=%s&month=%s&year=%s", 
								calendar.get(Calendar.DAY_OF_MONTH) + 1, calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.YEAR));
					%>
					<a class="link" href="day?<%=para%>">Go to day after &raquo;</a>
				</td>
			</tr>
		</table>
		<table border="0" class="tbl" cellpadding="3" cellspacing="3"
			width="100%">
			<thead>
				<tr>
					<th class="th">Room</th>
					<th class="th">08:00</th>
					<th class="th">08:30</th>
					<th class="th">09:00</th>
					<th class="th">09:30</th>
					<th class="th">10:00</th>
					<th class="th">10:30</th>
					<th class="th">11:00</th>
					<th class="th">11:30</th>
					<th class="th">12:00</th>
					<th class="th">12:30</th>
					<th class="th">13:00</th>
					<th class="th">13:30</th>
					<th class="th">14:00</th>
					<th class="th">14:30</th>
					<th class="th">15:00</th>
					<th class="th">15:30</th>
					<th class="th">16:00</th>
					<th class="th">16:30</th>
					<th class="th">17:00</th>
					<th class="th">17:30</th>
					<th class="th">18:00</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<%
				if(accounts.size() > 0){
					Account account;					
					List<Meeting> meetings = new ArrayList<Meeting>();
					for(int i = 0; i < accounts.size(); i++){
						account = accounts.get(i);
						meetings = Tool.getListMeeting(account.getUsername(), account.getPassword(), calendar.getTime(), calendar.getTime());
				%>
						<td><div class='celldiv'><%=accounts.get(i).getName()%></div></td>
				<%
						if(meetings.size() <= 0){
							for(int j = 0; j < times.size(); j++){
								out.print("<td><div class='celldiv'>...</div></td>");
							}
						}else{
							long beetween = 0;
							int minutes = 0;
							int colspan = 0;
							String start = "";
							Meeting meeting = meetings.get(index);
							for(int j = 0; j < times.size(); j++){
								start = df.format(meeting.getStartTime());
								if(start.equals(times.get(j).getLabel())){
									beetween = meeting.getEndTime().getTime() - meeting.getStartTime().getTime();
									minutes = (int)(beetween / 1000 / 60);
									System.out.println("Minutes : " + minutes);
									colspan = minutes / 30;
									colspan += 1;
									if((times.size() - j) <= colspan){
										out.print("<td class='time' colspan='" + colspan + "'><div class='celldiv'>" + meeting.getSubject() + "</div></td>");
										continue;
									}
									out.print("<td class='time' colspan='" + colspan + "'><div class='celldiv'>" + meeting.getSubject() + "</div></td>");
									j += colspan - 1;
									if(meetings.size() > index){
										index++;
									}
								}else{
									out.print("<td><div class='celldiv'>...</div></td>");
								}
							}
						}
					}
				}else{
				%>
					<td colspan="22">Nothing to view</td>
				<%	
				}
				%>
				</tr>
			</tbody>
		</table>
		<div class="view-mode"></div>
	</div>
</body>
</html>