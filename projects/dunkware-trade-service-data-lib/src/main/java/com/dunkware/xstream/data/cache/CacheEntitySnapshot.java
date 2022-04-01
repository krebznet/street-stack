package com.dunkware.xstream.data.cache;

import java.time.LocalDateTime;

public interface CacheEntitySnapshot {
	
	LocalDateTime getTime();
	
	CacheValueSet getVars();

}
