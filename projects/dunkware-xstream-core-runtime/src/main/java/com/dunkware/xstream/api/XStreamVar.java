package com.dunkware.xstream.api;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import com.dunkware.xstream.model.metrics.XStreamVarMetrics;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;
import com.dunkware.xstream.xScript.VarType;

public interface XStreamVar {
	
	public void init(XStreamRow row, VarType varType, List<EntityStatsSessionVar> sessionStats);
	
	public void start();
	
	public void dispose();
	
	public void setValue(Object value);
	
	public LocalDateTime getLocalDateTime();
	
	public LocalTime getLastUpdate();
	
	public Object getValue(int index);
	
	public Number getNumber(int index);
		
	public void getValues(Object[] data, int startIndex, int endIndex);
	
	public VarType getVarType();
	
	public void addVarListener(XStreamVarListener listener);
	
	public void removeVarListener(XStreamVarListener listener);
	
	public int getSize();

	public void setMaxSize(int size);
	
	public XStreamExpression getExpression();
	
	public XStreamRow getRow();
	
	public void addDownStreamVar(XStreamVar var);
	
	public void setMaxSizeEnabled(boolean value);
	
	public boolean isMaxSizeEnabled();
	
	public Collection<XStreamVar> getDownStreamVars();
	
	public Collection<XStreamExpression> getContainedExpressions();
	
	public void update();
	
	public XStreamVarMetrics getStats();
	
	public int getValueCount();
	
	/**
	 * Returns the available stats for the variable. 
	 * @return
	 */
	public List<EntityStatsSessionVar> getStatSessions(); 

}
