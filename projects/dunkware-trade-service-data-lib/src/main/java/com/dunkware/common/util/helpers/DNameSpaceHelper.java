package com.dunkware.common.util.helpers;

public class DNameSpaceHelper {

	public static String nextSymbol(String subspace, String namespace, String seperator) throws Exception {
		int subIndex = namespace.lastIndexOf(subspace);
		if(subIndex == -1) { 
			throw new Exception("Subspace " + subspace + " not found in " + namespace);
		}
		subIndex = subIndex + subspace.length();
		String subString1 = namespace.substring(subIndex); 
		if(subString1.length() == namespace.length()) { 
			throw new Exception("Symbol does not exist, subspace is size of namespace");
		}
		if(namespace.length() + 2 < subString1.length()) {
			throw new Exception("Next symbol does not exist from subspace " + subspace + " in namespace " + namespace);
		}
		// remove the symbol separator
		subString1 = subString1.substring(1,subString1.length());
		// if there are no more seperators then we 
		// have the next symbol in this case its the last one
		if(!subString1.contains(seperator)) { 
			return subString1; 
		}
		int nextSeperatorIndex = subString1.indexOf(seperator);
		if(nextSeperatorIndex == -1) { 
			throw new Exception("Expected another seperator but not found, internal problem");
		}
		subString1 = subString1.substring(0,nextSeperatorIndex);
		return subString1;
		
	}
	
	/**
	 * Returns the namespace after the subspace for example
	 * com.dunkware.trade.core as namespace calling 
	 * namespaceAfter(com.dunkware,com.dunktrade.trade.core) returns 
	 * trade.core
	 * @param subspace
	 * @param namespace
	 * @param seperator
	 * @return
	 */
	public static String namespaceAfter(String subspace, String namespace, String seperator) throws Exception { 
		int subIndex = namespace.lastIndexOf(subspace);
		if(subIndex == -1) { 
			throw new Exception("Subspace " + subspace + " not found in " + namespace);
		}
		subIndex = subIndex + subspace.length() + 1;
		String postSpace = namespace.substring(subIndex);
		return postSpace;
	}
	
	
	public static boolean namespaceStartsWith(String startspace, String namespace) { 
		return namespace.startsWith(startspace);
	}
}
