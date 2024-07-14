package com.dunkwrae.stream.data.cassy.mock;

import java.time.LocalDate;
import java.util.List;

import com.dunkware.stream.data.cassy.entity.SessionEntityStat;
import com.dunkware.stream.data.cassy.mock.SessionEntityStatMockData;

import junit.framework.TestCase;

public class SessionEntityStatMockDataTest extends TestCase {

	
	public void testBuilder() { 
		List<SessionEntityStat> data = SessionEntityStatMockData.build(LocalDate.of(2024, 1, 1), 1, 100, 5, 1,2);
		System.out.println(data.size());
		SessionEntityStat row = data.get(0);
		assertEquals(row.getKey().getDate(), LocalDate.of(2024, 1, 1));
		assertEquals(row.getKey().getEntity(),0);
		assertEquals(row.getElement(), 0);
		assertEquals(row.getStat(), 1);
		assertEquals(data.get(1).getStat(), 2);
	}
}
