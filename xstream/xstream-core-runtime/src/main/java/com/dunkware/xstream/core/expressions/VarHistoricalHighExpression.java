package com.dunkware.xstream.core.expressions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.VarHistoricalHighType;


@AXStreamExpression(type = VarHistoricalHighType.class)
public class VarHistoricalHighExpression extends XStreamExpressionImpl {
	
	private XStreamEntity row; 
	private VarHistoricalHighType myType; 
	
	private Object resolvedValue = null; 
	private boolean canResolve = false; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//private StreamSessionStatsExt statsExt;

	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.row = row; 
		this.myType = (VarHistoricalHighType)type;
		try {
		//	statsExt = (StreamSessionStatsExt)row.getStream().getExtension(StreamSessionStatsExt.class);
		} catch (Exception e) {
			logger.error("XStream Exception getting StreamStatsExt " + e.toString());;
			canResolve = false;
			return;
		}
		int statConstant = -1; 
		
		
		// it can execute a runnable - Future<>
		try {
			/*
			 * if(statsExt.canResolveVarStat(row, myType.getVar(),
			 * myType.getTimeRange().getValue(),statConstant)) { try { resolvedValue =
			 * statsExt.resolveVarStat(row, myType.getVar(),
			 * myType.getTimeRange().getValue(), statConstant); canResolve = true; } catch
			 * (Exception e) {
			 * logger.error("Exception resolving value after it can resolve true " +
			 * e.toString()); canResolve = false; return; } } else { canResolve = false; }
			 */
		} catch (Exception e) {
			logger.error("Exception calling canResolveVarStat " + e.toString());
			canResolve = false;
			return;
		}
		
	}

	@Override
	public void start() {
		if(canResolve && resolvedValue != null) { 
			setValue(resolvedValue);
		}
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public ExpressionType getExpType() {
		return myType; 
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public boolean canExecute() {
		return canResolve;
	}
	
	

}
