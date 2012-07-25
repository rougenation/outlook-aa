package com.axonactive.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.axonactive.dto.Account;

public class test {

	public static void main(String[] args) {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.HOUR_OF_DAY, 10);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		System.out.println(cal1.get(Calendar.MINUTE));
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.HOUR_OF_DAY, 10);
		cal2.set(Calendar.MINUTE, 30);
		cal2.set(Calendar.SECOND, 0);
		System.out.println(cal2.get(Calendar.MINUTE));
		
		long beetween = cal2.getTime().getTime() - cal1.getTime().getTime();
		int minutes = (int)( beetween / 1000 / 60);
		System.out.println("minutes : " + minutes);
		//read();
	}
	
	public static void update(String id){
		try{
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void read() {
		try {
			System.out.println("ABC");
			List<Account> accounts = new ArrayList<Account>();
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("src/account.xml");
			Account account = null;
			Element node;
			if (xmlFile.exists()) {
				Document document = builder.build(xmlFile);
				Element root = document.getRootElement();
				List list = root.getChildren("account");
				for (int i = 0; i < list.size(); i++) {
					node = (Element) list.get(i);
					account = new Account();
					account.setId(node.getChildText("id"));
					account.setName(node.getChildText("name"));
					account.setUsername(node.getChildText("username"));
					account.setPassword(node.getChildText("password"));
					accounts.add(account);
					System.out.println("A");
				}
			}

			for (Account acc : accounts) {
				System.out.println("ID : " + acc.getId() + " - Name : "
						+ acc.getName() + " - Username : " + acc.getUsername()
						+ " - Password : " + acc.getPassword());
			}
		} catch (NullPointerException cc) {
			// return new ArrayList<Account>();
		} catch (FileNotFoundException v) {
			// return new ArrayList<Account>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
