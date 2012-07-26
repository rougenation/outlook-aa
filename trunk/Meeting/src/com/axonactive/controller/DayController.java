package com.axonactive.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axonactive.dto.Account;
import com.axonactive.dto.Time;
import com.axonactive.util.Tool;

@WebServlet("/day")
public class DayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DayController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Calendar calendar;
			if(request.getParameter("day") == null || request.getParameter("month") == null || request.getParameter("year") == null){
				calendar = getNewCalendar();
				view(request,response,calendar);
				return;
			}else{
				int day = Integer.parseInt(request.getParameter("day").toString());
				int month = Integer.parseInt(request.getParameter("month").toString());
				int year = Integer.parseInt(request.getParameter("year").toString());
				calendar = Calendar.getInstance();
				calendar.set(year, month - 1, day,0,0,0);
				view(request,response,calendar);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Calendar getNewCalendar(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar;
	}
	
	protected void view(HttpServletRequest request, HttpServletResponse response, Calendar calendar){
		try{
			String file_url = request.getServletContext().getRealPath("/WEB-INF/account.xml");
			List<Account> accounts = new ArrayList<Account>();
			accounts = Tool.getListAccount(file_url);
			List<Time> times = new ArrayList<Time>();
			times = Tool.getListTime(calendar);
			
			request.setAttribute("times", times); //set attribute times
			request.setAttribute("accounts", accounts); //set attribute accounts
			request.setAttribute("calendar", calendar); // set attribute calendar
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/day.jsp");
			dispatcher.forward(request,response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Calendar calendar;
			if(request.getParameter("time") != null){
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				String time = request.getParameter("time").toString();
				Date date = format.parse(time);
				calendar = Calendar.getInstance();
				calendar.setTime(date);
				view(request,response,calendar);
				System.out.println("Time : " + request.getParameter("time").toString());
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
