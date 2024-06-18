package com.dunkware.common.util.LocalTime;

import com.dunkware.common.util.LocalTime.LocalDateTime;

public class LocalDateTimeTest {

	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt.get().toString());
	}
}
