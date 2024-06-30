package com.dunkware.utils.core.helpers;

import java.util.UUID;

public class DunkUUID {

	public static String randomUUID(int size) { 
		String random =  UUID.randomUUID().toString();
		random = random.replace("-", "x");
		random = random.substring(0, size);
		return random.toUpperCase();
	}
}