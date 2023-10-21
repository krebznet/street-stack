package com.dunkware.trade.sdk.core.runtime.util;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedDateTime;

public class TradeHelper {

	public static DDateTime dateTime() { 
		 return DDateTime.now(DTimeZone.NewYork);	 
	}
	
	public static DZonedDateTime zonedDateTime() { 
		return DZonedDateTime.now(DTimeZone.NewYork);
	}
	
	public static DTime time() { 
		return DTime.now(DTimeZone.NewYork);
	}
}
