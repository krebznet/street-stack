package com.dunkware.xstream.model.signal;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.dunkware.common.util.json.DJson;

public class StreamSignal {
	
	public static void main(String[] args) {
		StreamSignal signal = new StreamSignal();
		signal.setSignalId(3);
		signal.setEntityId(3);
		signal.setStreamId(1);
		signal.setTime(LocalDateTime.now());
		signal.getVars().put(2, 23.3);
		signal.getVars().put(4, 2);
		try {
			String out = DJson.serialize(signal);
			System.out.println(out);
			byte[] bytes = out.getBytes();
			StringBuilder s = new StringBuilder();
			for (byte b : bytes) {
				s.append(b);
			}
			System.out.println(s.toString());
			System.out.println(bytes);
			
			StreamSignal signal2 = DJson.getObjectMapper().readValue(out, StreamSignal.class);
			System.out.println(signal2.getSignalId());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	private int signalId;
	private int streamId;
	private int entityId;
	
	private LocalDateTime time;
	private Map<Integer,Object> vars = new HashMap<Integer,Object>();
	
	private String streamIdent;
	
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
	public Map<Integer, Object> getVars() {
		return vars;
	}
	public void setVars(Map<Integer, Object> vars) {
		this.vars = vars;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	} 
	
	
	
	
	
	
	

}
