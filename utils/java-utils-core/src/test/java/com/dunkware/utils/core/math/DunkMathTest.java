package com.dunkware.utils.core.math;

import org.junit.Test;

import com.dunkware.utils.core.helpers.DunkMath;

import junit.framework.TestCase;

public class DunkMathTest extends TestCase {
	
	
	@Test
	public void testRound() { 
		Double v1 = 23.23453; 
		Double rounded = DunkMath.round(v1, 2);
		String valueOf = String.valueOf(rounded); 
		assertEquals(valueOf, "23.23");
		
		v1 = 23.369;
		rounded = DunkMath.round(v1, 2);
		valueOf = String.valueOf(rounded); 
		assertEquals(valueOf, "23.37");
	}

}
