package com.dunkware.common.util.helpers;

public class DReflectionHelper {

	
	public static boolean isAssignableFrom(Class clazz, Class clazz2) { 
		boolean results = clazz.isAssignableFrom(clazz2);
		return results;
	}
	
	
	  public static boolean isSubClassOf(Class<?> clazz, Class<?> superClass) {
	        if(Object.class.equals(superClass)) {
	            return true;
	        }

	        for(; !Object.class.equals(clazz); clazz = clazz.getSuperclass()) {
	            if(clazz.getSuperclass().equals(superClass)) {
	                return true;
	            }
	        }

	        return false;
	    }
}
