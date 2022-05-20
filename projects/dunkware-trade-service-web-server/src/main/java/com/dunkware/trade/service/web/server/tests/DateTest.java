package com.dunkware.trade.service.web.server.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dunkware.common.util.json.DJson;

public class DateTest {

	public static void main(String[] args) {
		
	
		String testIso = "2021-11-28T23:44:12.475Z";
		
		try {
		
			Map<String,Object> values = new HashMap<String,Object>();
			values.put("Sma30sec", 32.3);
			values.put("Volume", 32323);
			values.put("ident", "FB");
			String ser = DJson.serialize(values);
			System.out.println(ser);
			Map<String,Object> back = DJson.getObjectMapper().
					readValue(ser, Map.class);
			System.out.println(back.get("ident"));
		} catch (Exception e) {
			System.err.println(e.toString());
			// TODO: handle exception
		}
	
	}
}
