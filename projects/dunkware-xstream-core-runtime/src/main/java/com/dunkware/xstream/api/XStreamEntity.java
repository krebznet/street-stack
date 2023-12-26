package com.dunkware.xstream.api;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.xstream.model.metrics.XStreamRowMetrics;
import com.dunkware.xstream.xScript.SignalType;

public interface XStreamEntity {
	
	void start(String id, int identifier, XStream stream);
	
	void dispose();
	
	public String getId();
	
	public XStream getStream();
	
	public Collection<XStreamEntityVar> getVars();
	
	public XStreamEntityVar getVar(String name);
	
	public TickStream getTickStream();
	
	public LocalDateTime getLocalDateTime();
	
	
	/**
	 * Takes a snapshot of all the variable values
	 * with a timestamp and row id used for data 
	 * capturing 
	 * 
	 * @return
	 */
	public XStreamRowSnapshot snapshot();
	
	
	public Map<Integer,Object> varValues();
	
	
	/**
	 * Returns all vars that are numeric
	 * @return
	 */
	List<XStreamEntityVar> getNumericVars();
	
	/**
	 * Returns the row stats at the current point in time
	 * @param varStats flag include varStats in rowStats
	 * @return XStreamRowStats
	 */
	public XStreamRowMetrics getStats(boolean varStats);
	
	/**
	 * Triggers a signal to this row
	 * @param type
	 */
	public void signal(SignalType type);
	
	/**
	 * Adds a XStreamRowListener. 
	 * @param list
	 */
	public void addRowListener(XStreamEntityListener list);
	
	/**
	 * Removes an XStreamRow row listener
	 * @param list
	 */
	public void removeRowListener(XStreamEntityListener list);
	
	/**
	 * Returns a map of signals where the key is the signal name
	 * and value is collection of signals for that signal type. 
	 * @return
	 */
	public List<XStreamRowSignal> getSignals();
	
	/**
	 * returns the signal count
	 * @return
	 */
	public int getSignalCount();
	
	/**
	 * Returns the integer identifier
	 * @return
	 */
	public int getIdentifier();
	
	/**
	 * adds a var listener 
	 * @param listener
	 */
	void addVarListener(XStreamEntityVarListener listener); 
	
	/**
	 * removes a var listener 
	 * @param listener
	 */
	void removeVarListener(XStreamEntityVarListener listener);
	
	/**
	 * Returns a snapshot of variable values that are numeric type
	 * key is the variable id number is the current value 
	 * @return
	 */
	Map<Integer,Number> numericVarSnapshot();
	

	
	
}
