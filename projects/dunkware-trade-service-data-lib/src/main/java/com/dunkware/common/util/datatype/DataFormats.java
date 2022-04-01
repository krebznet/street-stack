package com.dunkware.common.util.datatype;

import java.util.Calendar;
import java.util.Locale;

public class DataFormats {

	
	// String Formats
	
	// Truncated Count( DuncanKre...) 
	// SubString(0,5)
	
	
	// Double Formats
	// -- Decimal Count
	// Comma seperated or Not 
	// 3.5M 1.2M 1.4M 100 3K 4.53K 
	// Currency -- $32,232.43 
	// 3.5M 1.4B 1.2K -- Precision Level Name for that
	
	// Numeric -- LONG | DECIMAL | INT | SHORT | 
	
	// Time Format -- Easy -- Give it a string like Java
	 	//HH:mm:ss:SS
	 	 // 12:32:32:03
		// 23:32:23:09
	
	// Date Format Should Be Simple Too 
	
	// DateTime should be a combination of Time/Date format strings
	
	// Format String 
	
	   public static void main(String[] args) {
		      long n = 461012;
		      System.out.format("%d%n", n);      //  -->  "461012"
		      System.out.format("%08d%n", n);    //  -->  "00461012"
		      System.out.format("%+8d%n", n);    //  -->  " +461012"
		      System.out.format("%,8d%n", n);    // -->  " 461,012"
		      System.out.format("%+,8d%n%n", n); //  -->  "+461,012"
		      
		      double pi = Math.PI;

		      System.out.format("%f%n", pi);       // -->  "3.141593"
		      System.out.format("%.3f%n", pi);     // -->  "3.142"
		      System.out.format("%10.3f%n", pi);   // -->  "     3.142"
		      System.out.format("%-10.3f%n", pi);  // -->  "3.142"
		      System.out.format(Locale.FRANCE,
		                        "%-10.4f%n%n", pi); // -->  "3,1416"

		      Calendar c = Calendar.getInstance();
		      System.out.format("%tB %te, %tY%n", c, c, c); // -->  "May 29, 2006"

		      System.out.format("%tl:%tM %tp%n", c, c, c);  // -->  "2:34 am"

		      System.out.format("%tD%n", c);    // -->  "05/29/06"
		    }


}	


