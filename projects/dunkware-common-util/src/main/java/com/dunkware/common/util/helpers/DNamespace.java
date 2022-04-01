package com.dunkware.common.util.helpers;

public class DNamespace {

	public static String namespacePart(int index, String namespace) throws IndexOutOfBoundsException { 
		String[] parts = namespace.split("\\.");
		if(index > parts.length) {
			throw new IndexOutOfBoundsException("Part " + index + " is greater that part count");
		}
		return parts[index];
	}
	
	public static String subNamespace(int start, int end, String namespace) throws IndexOutOfBoundsException { 
		String[] parts = namespace.split("\\.");
		if(start > end) { 
			throw new IndexOutOfBoundsException("Start index is greater than end index");
		}
		if(end > parts.length) { 
			throw new IndexOutOfBoundsException("end index is greater than part count");
		}
		int diff = end - start;
		int index = start;
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		while(diff > -1) {
			if(first) {
				builder.append(parts[index]);
				first = false;
			} else { 
				builder.append(".");
				builder.append(parts[index]);
			}
			index++;
			diff--;
		}
		return builder.toString();
	}
	
	public static String customNamespacePart(int index, String namespace, String seperator) throws IndexOutOfBoundsException { 
		String[] parts = namespace.split(seperator);
		if(index > parts.length) {
			throw new IndexOutOfBoundsException("Part " + index + " is greater that part count");
		}
		return parts[index];
	}
}
