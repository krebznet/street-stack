package com.dunkware.xstream.core.mock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamEntityVarListener;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.model.metrics.XStreamVarMetrics;
import com.dunkware.xstream.xScript.VarType;

public class MockXStreamVar implements XStreamEntityVar  {

	@Override
	public void init(XStreamEntity row, VarType varType) {
		
		
	}

	@Override
	public void start() {
		
		
	}
	
		

	@Override
	public boolean isNumeric() {
		
		return false;
	}

	

	@Override
	public Number getHigh() {
		
		return null;
	}

	@Override
	public LocalTime getHighTime() {
		
		return null;
	}

	@Override
	public Number getLow() {
		
		return null;
	}

	@Override
	public LocalTime getLowTime() {
		
		return null;
	} 

	@Override
	public void dispose() {
		
		
	}

	@Override
	public void setValue(Object value) {
		
		
	}

	@Override
	public LocalDateTime getLocalDateTime() {
		
		return null;
	}

	@Override
	public LocalTime getLastUpdate() {
		
		return null;
	}

	@Override
	public Object getValue(int index) {
		
		return null;
	}

	@Override
	public Number getNumber(int index) {
		
		return null;
	}

	@Override
	public void getValues(Object[] data, int startIndex, int endIndex) {
		
		
	}

	@Override
	public VarType getVarType() {
		
		return null;
	}

	@Override
	public void addVarListener(XStreamEntityVarListener listener) {
		
		
	}

	@Override
	public void removeVarListener(XStreamEntityVarListener listener) {
		
		
	}

	@Override
	public int getSize() {
		
		return 0;
	}

	@Override
	public void setMaxSize(int size) {
		
		
	}

	@Override
	public XStreamExpression getExpression() {
		
		return null;
	}

	@Override
	public XStreamEntity getRow() {
		
		return null;
	}

	@Override
	public void addDownStreamVar(XStreamEntityVar var) {
		
		
	}

	@Override
	public void setMaxSizeEnabled(boolean value) {
		
		
	}

	@Override
	public boolean isMaxSizeEnabled() {
		
		return false;
	}

	@Override
	public Collection<XStreamEntityVar> getDownStreamVars() {
		
		return null;
	}

	@Override
	public Collection<XStreamExpression> getContainedExpressions() {
		
		return null;
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public XStreamVarMetrics getStats() {
		
		return null;
	}

	@Override
	public int getValueCount() {
		
		return 0;
	}

	
}
