package com.dunkware.common.util.data;

import org.junit.Test;

import junit.framework.TestCase;

public class DataHelperTest extends TestCase {

	@Test
	public void testConverter() throws Exception { 
		assertEquals(false, false);
		Double t1 = 10.0;
		Integer t2 = 10;
		int result = DataHelper.compareData(t1, t2);
		System.out.println(result);
	}
	
}
