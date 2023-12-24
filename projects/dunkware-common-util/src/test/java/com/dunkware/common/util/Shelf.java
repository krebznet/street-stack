package com.dunkware.common.util;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
			
			private List<String> items = new ArrayList<String>();
			
		public void put(String item) {
			if(item != null && item.isEmpty() == false) { 
				items.add(item);
			
			}
		}
		
		public boolean take(String item) { 
			if(items.contains(item)) { 
				items.remove(item);
				return true;
			}
			return false;
		}

}
