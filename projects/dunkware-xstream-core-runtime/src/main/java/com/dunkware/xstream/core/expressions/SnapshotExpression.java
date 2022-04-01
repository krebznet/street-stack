package com.dunkware.xstream.core.expressions;

import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SnapshotExpressionType;
import com.dunkware.xstream.xScript.StreamTimeUnit;

@AXStreamExpression(type = SnapshotExpressionType.class)
public class SnapshotExpression extends XStreamExpressionImpl {

	private SnapshotExpressionType type;
	
	private XStreamExpression targetExp = null;
	
	private XStreamRow row; 
	
	private Updater updater = new Updater();
	
	@Override
	public void init(XStreamRow row, ExpressionType type) {
		this.type = (SnapshotExpressionType)type;
		this.row = row; 
		targetExp = row.getStream().getInput().getRegistry().createVarExpression(this.type.getTarget());
		targetExp.init(row, this.type.getTarget());
	}

	@Override
	public void start() {
		targetExp.start();
		// resolve second interval
		int interval = 1;
		if(type.getTime().equals(StreamTimeUnit.SECOND)) { 
			interval = type.getInterval();
		}
		if(type.getTime().equals(StreamTimeUnit.MINUTE)) { 
			interval = type.getInterval() * 60;
		}
		if(type.getTime().equals(StreamTimeUnit.HOUR)) {
			interval = (type.getInterval() * 60) * 60 ;
		}
		row.getStream().getClock().scheduleRunnable(updater, interval);
		
	}

	@Override
	public void dispose() {
		targetExp.dispose();
		row.getStream().getClock().unscheduleRunnable(updater);
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public boolean canExecute() {
		return targetExp.canExecute();
	}
	

	@Override
	public boolean excludeDownStream() {
		return true; 
	}

	@Override
	public boolean isInput() {
		return true;
	}

	
	private class Updater implements Runnable { 
		
		public void run() { 
			try {
				//Thread.sleep(50);
			} catch (Exception e) {
				
			}
			if(targetExp.canExecute()) { 
				targetExp.execute();
				setValue(targetExp.getValue());
			}
		}
	}
	

}
