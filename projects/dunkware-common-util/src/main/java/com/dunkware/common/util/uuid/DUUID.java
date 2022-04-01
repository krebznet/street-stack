package com.dunkware.common.util.uuid;

import java.util.UUID;

public class DUUID {

	public static String randomUUID(int size) { 
		String random =  UUID.randomUUID().toString();
		random = random.replace("-", "x");
		random = random.substring(0, size);
		return random.toUpperCase();
	}
	

}
