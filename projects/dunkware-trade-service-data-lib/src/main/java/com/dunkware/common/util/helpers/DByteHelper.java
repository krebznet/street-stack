package com.dunkware.common.util.helpers;

import java.util.Arrays;

public class DByteHelper {

	public static Byte[] toObjects(byte[] bytesPrim) {
	    Byte[] bytes = new Byte[bytesPrim.length];
	    Arrays.setAll(bytes, n -> bytesPrim[n]);
	    return bytes;
	}
	
	public static byte[] toPrimitive(Byte[] bytes) { 
		byte[] prim = new byte[bytes.length];
		int i = 0;
		for (byte b : bytes) {
			prim[i] = b;
			i++;
		}
		return prim;
	}
}
