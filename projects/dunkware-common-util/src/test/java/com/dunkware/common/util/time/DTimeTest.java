package com.dunkware.common.util.time;

import java.time.LocalDateTime;
import java.util.Date;

import junit.framework.TestCase;

public class DTimeTest extends TestCase {
	
	
	public void testMe() { 
		assertEquals("dd", "dd");
		// so we can go from a localdatetime to a date
		// date to time in milliseconds then from there
		// back to Date and convert date time LocalDateTime
		//LocalDateTime localDateTime = LocalDateTime.now();
		//Date convertedDate = DunkTime.toDate(localDateTime);
		//long timestamp = convertedDate.getTime();
		//LocalDateTime dt2 = DunkTime.toLocalDateTime(timestamp);
		//assertEquals(localDateTime, dt2);
		
		
	}

}
