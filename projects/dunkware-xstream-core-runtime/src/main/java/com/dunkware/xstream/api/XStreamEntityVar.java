package com.dunkware.xstream.api;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import com.dunkware.xstream.model.metrics.XStreamVarMetrics;
import com.dunkware.xstream.xScript.VarType;

public interface XStreamEntityVar {
	
	public void init(XStreamEntity row, VarType varType);
	
	public void start();
	
	public void dispose();
	
	public void setValue(Object value);
	
	public LocalDateTime getLocalDateTime();
	
	public LocalTime getLastUpdate();
	
	public Object getValue(int index);
	
	public Number getNumber(int index);
		
	public void getValues(Object[] data, int startIndex, int endIndex);
	
	public VarType getVarType();
	
	public void addVarListener(XStreamEntityVarListener listener);
	
	public void removeVarListener(XStreamEntityVarListener listener);
	
	public int getSize();

	public void setMaxSize(int size);
	
	public XStreamExpression getExpression();
	
	public XStreamEntity getRow();
	
	public void addDownStreamVar(XStreamEntityVar var);
	
	public void setMaxSizeEnabled(boolean value);
	
	public boolean isMaxSizeEnabled();
	
	public Collection<XStreamEntityVar> getDownStreamVars();
	
	public Collection<XStreamExpression> getContainedExpressions();
	
	public void update();
	
	public XStreamVarMetrics getStats();
	
	public int getValueCount();
	
}
