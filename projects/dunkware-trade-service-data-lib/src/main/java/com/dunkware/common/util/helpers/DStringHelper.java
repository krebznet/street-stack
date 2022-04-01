package com.dunkware.common.util.helpers;

import java.math.BigInteger;

import javax.print.DocFlavor.INPUT_STREAM;

public class DStringHelper {
	

	/**
	 * Strips illegal characters in the string such as \n 
	 * \r and other non characters. 
	 * @param string
	 */
	public static String StripIllegalCharacters(String string) { 
		String results = string; 
		if(string.contains("\r")) { 
			results = results.replace("\r", "");
		}
		if(string.contains("\n")) { 
			results = results.replace("\n", "");
		}
		if(string.contains("\f")) { 
			results = results.replace("\r", "");
			
		}
		if(string.contains("\t")) { 
			try {
				results = results.replace("\t", "");	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return results;
	}
	
	public static String[] splitStringOnFirstInstanceOfCharacter(String string, String sChar) { 
		if(string.contains(sChar)) { 
			int index = string.indexOf(sChar);
			String s1 = string.substring(0, index);
			String s2 = string.substring(index + 1, string.length());
			return new String[]{s1,s2};
		}
		return new String[]{string,string};
	}

	public static boolean isNumeric(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isNumericDecial(String value) {
		if(value.contains(".")) {
			return true;
		}
		return false;
	}
	
	
	public static Integer encodeString(String intput) { 
		BigInteger bigInt = new BigInteger(intput.getBytes());
		return bigInt.intValue();
	}
	
	public static String decodeString(Integer input) { 
		BigInteger bigInt = BigInteger.valueOf((long)input);
		  byte[] bytes = bigInt.toByteArray(); 
		 String test = new String(bytes);
		 return test;
	}
	
	public static String convertArrayToCSV(String[] array) { 
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (String string : array) {
			if(count > 0) {
				builder.append(",");
			}
			builder.append(string);
			count++;
		}
		return builder.toString();
	}
	

}
