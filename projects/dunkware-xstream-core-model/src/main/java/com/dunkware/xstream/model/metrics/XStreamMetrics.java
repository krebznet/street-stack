package com.dunkware.xstream.model.metrics;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.json.DJson;

public class XStreamMetrics {

	private long taskCount; 
	private long pendingTaskCount;
	private long pendingTickCount; 
	private long tickCount;
	private long exceptionCount; 
	private long timeSnapshot;
	private long freeMemory;
	private long totalMemory;
	private long availableMemory;
	private long rowCount; 
	
	private List<XStreamRowMetrics> rows = new ArrayList<XStreamRowMetrics>();
	
	private DTime streamTime;
	private DTime realTime;
	
	public static void main(String[] args) {
		int i = 0;
		while(i < 5) { 
			LocalTime time = LocalTime.now();
			try {
				String ser = DJson.serializePretty(time);
				
				System.out.println(ser);
				LocalDateTime ld = LocalDateTime.now();
				System.out.println(DJson.serializePretty(ld));
				Thread.sleep(10);
				i++;
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
	}
	public XStreamMetrics() { 
		
		
	}
	
	
	

	public long getTaskCount() {
		return taskCount;
	}
	
	public void setTaskCount(long taskCount) {
		this.taskCount = taskCount;
	}
	
	public long getPendingTickCount() {
		return pendingTickCount;
	}
	
	public void setPendingTickCount(long pendingTickCount) {
		this.pendingTickCount = pendingTickCount;
	}
	
	public long getTickCount() {
		return tickCount;
	}
	
	public void setTickCount(long tickCount) {
		this.tickCount = tickCount;
	}
	
	public List<XStreamRowMetrics> getRows() {
		return rows;
	}
	
	public void setRows(List<XStreamRowMetrics> rows) {
		this.rows = rows;
	}
	
	public long getPendingTaskCount() {
		return pendingTaskCount;
	}
	
	public void setPendingTaskCount(long pendingTaskCount) {
		this.pendingTaskCount = pendingTaskCount;
	}
	
	public long getExceptionCount() {
		return exceptionCount;
	}
	
	public void setExceptionCount(long exceptionCount) {
		this.exceptionCount = exceptionCount;
	}
	
	public DTime getStreamTime() {
		return streamTime;
	}
	
	public long getTimeSnapshot() {
		return timeSnapshot;
	}

	public void setTimeSnapshot(long timeSnapshot) {
		this.timeSnapshot = timeSnapshot;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getAvailableMemory() {
		return availableMemory;
	}

	public void setAvailableMemory(long availableMemory) {
		this.availableMemory = availableMemory;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public DTime getRealTime() {
		return realTime;
	}

	public void setRealTime(DTime realTime) {
		this.realTime = realTime;
	}

	public void setStreamTime(DTime streamTime) {
		this.streamTime = streamTime;
	} 
	
	
	
	
	
	
	
	
}
