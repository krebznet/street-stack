package com.dunkware.xstream.core.expressions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.core.util.XStreamAggHelper;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.RelativeSessionTimeRange;
import com.dunkware.xstream.xScript.SessionAggFunc;
import com.dunkware.xstream.xScript.SessionTimeRange;
import com.dunkware.xstream.xScript.TodaySessionTimeRange;
import com.dunkware.xstream.xScript.VarAggSessionType;

@AXStreamExpression(type = VarAggSessionType.class)
public class VarAggSessionExpression extends XStreamExpressionImpl {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private VarAggSessionType myType;
	private XStreamEntity row;
	private int secondRange = 0;
	private boolean todayRange = false;
	private SessionAggFunc function = null;
	private XStreamEntityVar targetVar;
	private boolean exception = false;

	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.row = row;
		myType = (VarAggSessionType) type;
		SessionTimeRange rnage = myType.getTimeRange();
		function = myType.getFunction();
		try {
			targetVar = row.getVar(myType.getVar().getName());
		} catch (Exception e) {
			logger.error(row.getStream().getSessionMarker(),
					" Getting TargetVar in SessionAggExp Failed " + e.toString());
			exception = true;
		}
		if (rnage instanceof RelativeSessionTimeRange) {
			RelativeSessionTimeRange rt = (RelativeSessionTimeRange) rnage;
			try {
				secondRange = XStreamAggHelper.sessionRelativeRangeSeconds(rt);
			} catch (Exception e) {
				logger.error(row.getStream().getSessionMarker(),
						"Exception computing session range in var ag session " + e.toString());
				exception = true;
				return;
			}
		}
		if (rnage instanceof TodaySessionTimeRange) {
			todayRange = true;
		}

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public ExpressionType getExpType() {
		return myType;
	}

	@Override
	public boolean execute() {

		if (myType.getFunction() == SessionAggFunc.HIGH) {
					// so now we need method to get all the values and 
		}

		if (myType.getFunction() == SessionAggFunc.LOW) {

		}
		
		//setValue(function);
		
		return true;

	}

	@Override
	public boolean canExecute() {
		if (todayRange && targetVar.getSize() > 0) {
			return true;
		}
		if (todayRange && targetVar.getSize() < 1) {
			return false;
		}
		if (!todayRange && targetVar.getSize() < secondRange) {
			return false;
		}
		if (!todayRange && targetVar.getSize() > secondRange || targetVar.getSize() == secondRange) {
			return true;
		}
		logger.error(row.getStream().getSessionMarker(), "Bad code in var agg session can execut");
		return false;
	}

}
