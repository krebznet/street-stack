package com.dunkware.common.util.time;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.json.DJson;

public class DTimeSerailizeTest {
	
	public static void main(String[] args) {
		try {
			DDateTime dateTime = DDateTime.now();
			String serialized = DJson.serialize(dateTime);
			System.out.println(serialized);
			
			DDateTime parsed = DJson.getObjectMapper().readValue(serialized, DDateTime.class);
			System.out.println(parsed.get().toString());
		} catch (Exception e) {
			System.err.println(e.toString());
			// TODO: handle exception
		}
	}

}
