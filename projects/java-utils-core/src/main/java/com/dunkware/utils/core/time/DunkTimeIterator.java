package com.dunkware.utils.core.time;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class DunkTimeIterator {
	
	public static DunkTimeIterator build(LocalDateTime start, LocalDateTime stop, int interval, TimeUnit intervalUnit) { 
		return new DunkTimeIterator(start, stop, interval, intervalUnit);
	}
	
	
	private LocalDateTime start;
	private LocalDateTime stop; 
	private LocalDateTime current;
	private int bucketSize;
	private TimeUnit bucketUnit;
	private volatile long count;
	
	private DunkTimeIterator(LocalDateTime start, LocalDateTime stop, int interval, TimeUnit intervalUnit) { 
		this.start = start;
		this.stop = stop;
		this.bucketSize = interval;
		this.bucketUnit = intervalUnit;
		this.current = start;
	} 
		
	
	
	public boolean hasNext() { 
		if(count == 0) { 
			return true;
		}
		return true;
	}
	
	public LocalDateTime next() { 
		if(count == 0) { 
			return current;
		}
		//current = current.plus((long)bucketSize, bucketUnit);
		return null;
	}

}
