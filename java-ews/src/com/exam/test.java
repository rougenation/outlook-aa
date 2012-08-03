package com.exam;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class test {

	public static void main(String[] args) {
		try{
			List<String> list = new ArrayList<String>();
			list.add("a");
			list.add("b");
			list.add("c");
			list.add("d");
			list.add("a");
			for(int i = 0; i < list.size(); i++){
				//if(list.get(i).equals("a")){
					list.remove(i);
					i--;
				//}
			}
			
			for(String value : list){
				System.out.println("Value : " + value);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
