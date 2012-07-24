package com.axonactive.controller;

import java.io.IOException;
import java.util.ArrayList;
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
			if(request.getParameter("mode") != null){
				String mode = request.getParameter("mode").toString();
				if(mode.equals("view")){
					view(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void view(HttpServletRequest request, HttpServletResponse response){
		try{
			List<Account> accounts = new ArrayList<Account>();
			accounts = Tool.getListAccount();
			List<Time> times = new ArrayList<Time>();
			times = Tool.getListTime();
			
			request.setAttribute("times", times); //set attribute accounts
			request.setAttribute("accounts", accounts); //set attribute accounts
			
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/day.jsp");
			dispatcher.forward(request,response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
