package com.dunkware.common.util.grid.format;

public class GridFormats {

	public static String valueFormatter(GridFormat format)  { 
		if(format == GridFormat.INTEGER) {
			return "numericValueFormatter";
		}
		return null;
	}
}
