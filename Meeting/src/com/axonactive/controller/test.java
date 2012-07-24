package com.axonactive.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.axonactive.dto.Account;

public class test {

	public static void main(String[] args) {
		read();
	}

	public static void read() {
		try {
			List<Account> accounts = new ArrayList<Account>();
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("src/data.xml");
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
