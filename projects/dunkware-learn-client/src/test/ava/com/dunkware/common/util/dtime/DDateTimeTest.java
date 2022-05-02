package com.dunkware.common.util.dtime;

import com.dunkware.common.util.dtime.DDateTime;

public class DDateTimeTest {

	public static void main(String[] args) {
		DDateTime dt = DDateTime.now(); 
		System.out.println(dt.get().toString());
	}
}
