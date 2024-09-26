package com.dunkware.utils.core.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;



public class DunkMath {
	

	private static DecimalFormat df = new DecimalFormat("#.##%");
	
	
	/**
	 * Returns the mean of a range of values. Valid object types are Integer, Long,
	 * Double.
	 * 
	 * @param values
	 * @return
	 */
	public static double getMean(Object[] values) throws Exception {
		double temp = 0;


		int count = 0;
		for (Object object : values) {
			if (object == null) {
				continue;
			}
			if (object instanceof Double) {
				Double value = (Double) object;
				temp += value;
				count++;
				continue;
			}
			if (object instanceof Integer) {
				Integer value = (Integer) object;
				temp += value;
				count++;
				continue;
			}
			if (object instanceof Long) {
				Long value = (Long) object;
				temp += value;
				count++;
				continue;
			}
			throw new Exception("Unknown object type in value[] " + object.getClass().getName());

		}
		// format?
		return temp / count;

	}
	
	public static void main(String[] args) {
		double target =  1.0;
		double compare = 100;
		System.out.println(getPercentageChange(target,compare));
	}
	
	public static double castRoundTo3(double d) {
		return (long) (d * 1000 + 0.5) / 1000.0;
	}
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static double getPercentageChange(double y1, double y2) {
		if(y1 == 0.0)  { 
			return y2;
		}
		if(y2 == 0.0) {
			return y1;
		}
		//double pchange =   ( ( (((y1-y2) / y2)*100)));
		y1 = round(y1,3);
		y2 = round(y2,3);
		
		return (round( ((y1 - y2) / (double)y2),3)) * 100 ;
		

	}

}
