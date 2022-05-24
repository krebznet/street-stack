package com.dunkware.trade.service.web.json.matcher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.time.DunkTime;

public class WebEntityMatcherGen {

	public static void main(String[] args) {
		List<WebEntityMatcher> matchers = new ArrayList<WebEntityMatcher>();
		WebEntityMatcher m1 = new WebEntityMatcher();
		m1.setCreated(DunkTime.format(LocalDateTime.now(), DunkTime.YYYY_MM_DD_HH_MM_SS));
		m1.setStream("us_equity");
		m1.setName("Breakout 1");
		m1.setData("todo-will-be-serialized-json of protocol buffers object");
		matchers.add(m1);
		m1 = new WebEntityMatcher();
		m1.setCreated(DunkTime.format(LocalDateTime.now(), DunkTime.YYYY_MM_DD_HH_MM_SS));
		m1.setStream("us_equity");
		m1.setName("Breakout 2");
		m1.setData("todo-will-be-serialized-json of protocol buffers object");
		matchers.add(m1);
		m1 = new WebEntityMatcher();
		m1.setCreated(DunkTime.format(LocalDateTime.now(), DunkTime.YYYY_MM_DD_HH_MM_SS));
		m1.setStream("us_equity");
		m1.setName("Breakout 3");
		m1.setData("todo-will-be-serialized-json of protocol buffers object");
		matchers.add(m1);
		try {
			String  ser = DJson.serialize(matchers);
			System.out.println(ser);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
