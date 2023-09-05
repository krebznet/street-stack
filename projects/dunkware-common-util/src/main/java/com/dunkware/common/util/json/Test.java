package com.dunkware.common.util.json;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Test {

	public static void main9(String[] args) {

        // 1. get Zoned Date/time
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("Zoned Date/time is :- \n"
                + zonedDateTime);
 
 
        // 2. get Zone
        System.out.println("\nZone is :- \n"
                + zonedDateTime.getZone());
 
 
        // 3. convert ZonedDateTime to Instant using toOffsetDateTime()
        Instant instant = zonedDateTime.toInstant();
        System.out.print("\nConversion of ZonedDateTime to an Instant is :- \n"
                + instant);
        
        ZonedDateTime test = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
        
        System.out.println(test);
	}
	public static void main(String[] args) {
		ZonedDateTime  dt = ZonedDateTime.now(ZoneOffset.ofHours(2));
        
		try {
			System.out.println("Dto to string ");
			System.out.println(dt.toString());
			System.out.println("dto serialized");
			String serialized = DJson.serialize(dt);
			System.out.println(serialized);
			
			ZonedDateTime dt2 = DJson.getObjectMapper().readValue(serialized, ZonedDateTime.class);
			System.out.println("deserialized to string");
			System.out.println(dt2.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
