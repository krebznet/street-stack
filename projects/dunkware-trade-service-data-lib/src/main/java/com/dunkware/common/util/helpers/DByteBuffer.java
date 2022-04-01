package com.dunkware.common.util.helpers;

import java.nio.ByteBuffer;
import java.util.List;

public class DByteBuffer {

	/**
	 * returns a byte array with written values in buffer
	 * not capacity. 
	 * @param buffer
	 * @return
	 */
	public static byte[] getTrimmedArray(ByteBuffer b) {
		byte[] barray = new byte[b.limit()];
		b.position(0);
		int i = 0;
		while(i < barray.length) {
			barray[i] = b.get();
			i++;
		}
		return barray;
	}
	
	
	public static void printByteBufferList(List<ByteBuffer> buffers) { 
		int index = 0;
		StringBuilder builder = new StringBuilder();
		for (ByteBuffer byteBuffer : buffers) {
			byte[] array = byteBuffer.array();
			System.out.println("buffer index " + index + " capacity " + byteBuffer.capacity() + " limit " + byteBuffer.limit() +  "position " + byteBuffer.position() + " arraysize " + array.length);
			builder.setLength(0);
			for (byte bite : array) {
				builder.append(bite);
				builder.append(",");
			}
			System.out.println(builder.toString());
			
			
		}
	}
}
