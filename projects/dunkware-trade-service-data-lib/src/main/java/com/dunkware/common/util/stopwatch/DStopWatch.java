package com.dunkware.common.util.stopwatch;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DStopWatch   {
	
	public enum Status {
		Started,Stopped,Initialized;
	}
	
	public static DStopWatch  create() {
		
		return new DStopWatch();
		
	}
	
	public static void main(String[] args) {
		DStopWatch w = DStopWatch.create();
		try {
			w.start();
			Thread.sleep(420 );
			w.stop();
			System.out.println(w.getCompletedSeconds());
			w.reset();
			w.start();
			Thread.sleep(1250);
			w.stop();
			System.out.println(w.getCompletedSeconds());
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	}
	

	private volatile DStopWatch.Status _status = DStopWatch.Status.Initialized;
	private Semaphore _statusLock = new Semaphore(1);
	private TimeUnit _timeUnit = TimeUnit.NANOSECONDS;
	private Long _startTime = null;
	private Long _stopTime  = null;
	

	DStopWatch() {
		
		
	}

	public Status getStatus() {
		return _status;
	}
	

	
	public void start()  {
		if(_timeUnit == TimeUnit.NANOSECONDS) {
			_startTime = System.nanoTime();
			_stopTime = null;
			setStatus(Status.Started);
		}
		if(_timeUnit == TimeUnit.MILLISECONDS) {
			_startTime = System.currentTimeMillis();
			_stopTime = null;
		}
		
	}

	
	void setStatus(Status status) {
		_status = Status.Stopped;
	}


	public void stop()  {
		if(getStatus() != Status.Stopped) {
			//exception
		}
		if( _timeUnit == TimeUnit.NANOSECONDS) {
			_stopTime = System.nanoTime();
			setStatus(Status.Stopped);
		}
		if(_timeUnit == TimeUnit.MILLISECONDS) {
			_stopTime = System.currentTimeMillis();
		}
		
	}

	
	public void reset() {
		setStatus(Status.Initialized);
		_startTime = null;
		_stopTime = null;
		
	}

	
	public TimeUnit getTimeUnit() {
		return _timeUnit;
	}

	
	public void reset(TimeUnit timeUnit) {
		_timeUnit = timeUnit;
		reset();
	}

	
	
	
	
	public double getRunningSeconds()  {
		
		
			long dureation = System.nanoTime() - _startTime;
			double seconds = ((double) dureation) / 1E9;
			return seconds;
	}

	
	public double getCompletedSeconds()   {
		long dureation = _stopTime - _startTime;
		double seconds = ((double) dureation) / 1E9;
		return seconds;
	}

	
	
	
	

}