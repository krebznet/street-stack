package com.dunkware.utils.core.helpers;

public class DunkRandom {

	
	public static int getRandom(int min, int max) {
	    if (min > max) {
	        throw new IllegalArgumentException("Min " + min + " greater than max " + max);
	    }      
	    return (int) ( (long) min + Math.random() * ((long)max - min + 1));
	}
}
