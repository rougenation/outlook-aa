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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/reset.css" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
</head>
<body>
	<%
		List<Account> accounts = new ArrayList<Account>();
		List<Time> times = new ArrayList<Time>();
		if(request.getAttribute("accounts") != null){
			System.out.println("Account : ok - Size : " + accounts.size());
			accounts = (List<Account>)request.getAttribute("accounts");
			if(request.getAttribute("times") != null){
				System.out.println("Time : ok - Size : " + times.size());
				times = (List<Time>)request.getAttribute("times");
			}
		}else{
			//do something
		}
	%>
	<div class="wrapper">
		<table border="0" class="tbl" cellpadding="0" cellspacing="0"
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
					for(int i = 0; i < accounts.size(); i++){
						for(int j = 0; j < times.size(); j++){
							//do something
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