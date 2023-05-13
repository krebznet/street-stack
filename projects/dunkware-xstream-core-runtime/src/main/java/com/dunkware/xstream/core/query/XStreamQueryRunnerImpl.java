package com.dunkware.xstream.core.query;

import java.util.List;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamQueryRunner;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.model.search.XStreamCriterias;

public class XStreamQueryRunnerImpl implements XStreamQueryRunner {

	private XStream stream; 
	private XStreamCriterias query; 
	
	public void init(XStream stream, XStreamCriterias query) throws XStreamQueryException { 
		
	}
	
	@Override
	public void execute() throws XStreamQueryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean didExecute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XStreamRow> getResults() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canExecute() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	

}
