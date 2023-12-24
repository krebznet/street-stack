package com.dunkware.common.util;

import org.junit.Assert;
import org.junit.Test;

public class TestMe {

	 @Test
	    public void shelfCanAcceptAndReturnItem() {
	        Shelf shelf = new Shelf();
	        shelf.put("Orange");
	        Assert.assertTrue(shelf.take("Orange"));
	      shelf = new Shelf();
	      shelf.put("");
	      Assert.assertFalse(shelf.take(""));
	      shelf = new Shelf();
	      shelf.put(null);
	      Assert.assertFalse(shelf.take(null));
	    }
}


