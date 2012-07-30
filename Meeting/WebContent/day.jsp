<%@page import="java.text.DateFormat"%>
<%@page import="com.axonactive.util.Tool"%>
<%@page import="com.axonactive.dto.Meeting"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.axonactive.dto.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.axonactive.dto.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Axon Active - Meeting room</title>
<link rel="stylesheet" type="text/css" href="resources/css/reset.css" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui-1.7.3.custom.css" />
<script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui-1.7.3.custom.min.js"></script>

<script type="text/javascript">
	$('document').ready(function(){
		$("#month-selection" ).datepicker({
			numberOfMonths: 3,
			onSelect: function(dateText, inst) {
				$("#inputDate").val(dateText);
				$('#target').submit();
			}
		});
		$("#inputDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			onSelect: function(dateText, inst) {
				$("#inputDate").val(dateText);
			}
		});
	});
</script>
</head>
<body>
	<%
		//use for meeting
		DateFormat dfDate = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat df = new SimpleDateFormat("HH:mm");
		DateFormat display = DateFormat.getDateInstance(DateFormat.FULL);
		List<Room> rooms = new ArrayList<Room>();
		List<Time> times = new ArrayList<Time>();
		Calendar calendar = Calendar.getInstance();
		if(request.getAttribute("calendar") != null){
			calendar = (Calendar)request.getAttribute("calendar");
		}
		if(request.getAttribute("rooms") != null){
			rooms = (List<Room>)request.getAttribute("rooms");
			if(request.getAttribute("times") != null){
				times = (List<Time>)request.getAttribute("times");
			}
		}else{
			String para = "";
			para = String.format("day=%s&month=%s&year=%s", 
					calendar.get(Calendar.DAY_OF_MONTH) , calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.YEAR));
			response.sendRedirect("day?" + para);
		}
	%>
	<div class="wrapper">
		<div id="month-selection"></div>
		<span id="display-time">
			<%=display.format(calendar.getTime())%>
		</span>
		<div class="search-time">
			<form id="target" action="day" method="post">
				<label>Time : </label>
				<input type="text" name="time" id="inputDate" readonly="readonly" 
					value="<%=dfDate.format(calendar.getTime())%>"/>
				<input type="submit" class="btn" id="filter" value="Filter">
			</form>
		</div>
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
					<th width="4%" class="th">08:00</th>
					<th width="4%" class="th">08:30</th>
					<th width="4%" class="th">09:00</th>
					<th width="4%" class="th">09:30</th>
					<th width="4%" class="th">10:00</th>
					<th width="4%" class="th">10:30</th>
					<th width="4%" class="th">11:00</th>
					<th width="4%" class="th">11:30</th>
					<th width="4%" class="th">12:00</th>
					<th width="4%" class="th">12:30</th>
					<th width="4%" class="th">13:00</th>
					<th width="4%" class="th">13:30</th>
					<th width="4%" class="th">14:00</th>
					<th width="4%" class="th">14:30</th>
					<th width="4%" class="th">15:00</th>
					<th width="4%" class="th">15:30</th>
					<th width="4%" class="th">16:00</th>
					<th width="4%" class="th">16:30</th>
					<th width="4%" class="th">17:00</th>
					<th width="4%" class="th">17:30</th>
					<th width="4%" class="th">18:00</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(rooms.size() > 0){
						Room account;					
						List<Meeting> meetings = new ArrayList<Meeting>();
						for(int i = 0; i < rooms.size(); i++){
				%>
				<tr>
				<%
						account = rooms.get(i);
						meetings = account.getMeetings();
							//meetings = Tool.getListMeeting(account.getUsername(), account.getPassword(), calendar.getTime(), calendar.getTime());
				%>
					<td><div class='celldiv'><%=rooms.get(i).getName()%></div></td>
				<%
						if(meetings.size() <= 0){
							for(int j = 0; j < times.size(); j++){
								out.print("<td><div class='celldiv'>..</div></td>");
							}
						}else{
							int index = 0;
							long beetween = 0;
							int minutes = 0;
							int colspan = 0;
							String start = "";
							Meeting meeting;
							for(int j = 0; j < times.size(); j++){
								meeting = meetings.get(index);
								if(meeting.getDisplayStart().equals(times.get(j).getLabel())){
									if((times.size() - j) <= meeting.getColspan()){
				%>
										<td class='time' colspan="<%=(times.size() - j)%>">
											<div title="<%=meeting.getSubject()%> (<%= meeting.getDisplayStart()%> - <%=meeting.getDisplayEnd() %>)" class='celldiv'>
												<%=meeting.getSubject()%> (<%= meeting.getDisplayStart()%> - <%=df.format(meeting.getDisplayEnd()) %>)
											</div>
										</td>
				<%
										break;
									}
				%>
									<td class='time' colspan="<%=meeting.getColspan()%>">
										<div class='celldiv' title="<%=meeting.getSubject()%> (<%= meeting.getDisplayStart()%> - <%=meeting.getDisplayEnd() %>)">
											<%=meeting.getSubject()%> (<%= meeting.getDisplayStart()%> - <%= meeting.getDisplayEnd() %>)
										</div>
									</td>
				<%
									j += meeting.getColspan() - 1;
									if((meetings.size() - 1) > index){
										index++;
									}
								}else{
				%>
									<td><div class='celldiv'>..</div></td>
				<%
								}
							}
						}
					}
				%>
					</tr>
				<%
				}else{
				%>
					<tr>
						<td colspan="22">Nothing to view</td>
					</tr>
				<%	
				}
				%>
			</tbody>
		</table>
		<div class="view-mode"></div>
	</div>
</body>
</html>