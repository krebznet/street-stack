package com.dunkware.xstream.model.stats;

import java.time.LocalTime;

import com.dunkware.common.util.json.DJson;

public class Yoo {

	private LocalTime time;

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	} 
	
	
	
	public static void main(String[] args) {
		Yoo u = new Yoo();
		u.setTime(LocalTime.now());
		try {
			String me = DJson.serializePretty(u);
			System.out.println(me);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
