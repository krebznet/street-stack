package com.dunkware.xstream.core.mock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import com.dunkware.common.stats.GenericNumber;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamEntityVarListener;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.model.metrics.XStreamVarMetrics;
import com.dunkware.xstream.xScript.VarType;

public class MockXStreamVar implements XStreamEntityVar  {

	@Override
	public void init(XStreamEntity row, VarType varType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
		

	@Override
	public boolean isNumeric() {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public List<GenericNumber> getNumericValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocalDateTime getLocalDateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getLastUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getNumber(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getValues(Object[] data, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VarType getVarType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addVarListener(XStreamEntityVarListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVarListener(XStreamEntityVarListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public XStreamExpression getExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XStreamEntity getRow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDownStreamVar(XStreamEntityVar var) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMaxSizeEnabled(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMaxSizeEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<XStreamEntityVar> getDownStreamVars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<XStreamExpression> getContainedExpressions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public XStreamVarMetrics getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getValueCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
