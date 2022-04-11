package com.dunkware.trade.service.web.server.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		
	
		String testIso = "2021-11-28T23:44:12.475Z";
		
		try {
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			String string1 = "2001-07-04T12:08:56.235-0700";
			Date result1 = df1.parse(testIso);

			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			String string2 = "2001-07-04T12:08:56.235-07:00";
			Date result2 = df2.parse(string2);
			System.out.println(result1.toGMTString());
		} catch (Exception e) {
			e.toString();
			// TODO: handle exception
		}
	
	}
}
