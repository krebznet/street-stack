package com.dunkware.xstream.core.expressions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.AvgExpressionType;
import com.dunkware.xstream.xScript.ExpressionType;

@AXStreamExpression(type = AvgExpressionType.class)
public class AvgExpression extends XStreamExpressionImpl {

	AvgExpressionType type;
	private XStreamRow row;
	private VarSetExpression targetExp = null;

	@Override
	public void init(XStreamRow row, ExpressionType type) {
		this.type = (AvgExpressionType) type;
		this.row = row;
		targetExp = (VarSetExpression) row.getStream().getInput().getRegistry().createVarExpression(this.type.getTarget());
		targetExp.init(row, this.type.getTarget());

	}

	
	@Override
	public void start() {
		targetExp.start();
	}

	@Override
	public void dispose() {
		targetExp.dispose();
	}

	@Override
	public boolean canExecute() {
		return targetExp.canExecute();
	}

	@Override
	public boolean execute() {
		try {

			targetExp.execute();
			Object[] values = (Object[]) targetExp.getValue();
			if (values != null) {
				Double std = DCalc.getMean(values);
				BigDecimal bd = new BigDecimal(std);
				bd = bd.setScale(2, RoundingMode.HALF_UP);
				setValue(bd.doubleValue());
			} else { 
				throw new XStreamRuntimeException("Avg target exp values[] is null!?!?");
			}
			return true;

		} catch (Exception e) {
			throw new XStreamRuntimeException("Error computing avg " + e.toString(), e);
			

		}

	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}


	@Override
	public void containedVariables(List<XStreamVar> varList) {
		targetExp.containedVariables(varList);
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		expList.add(targetExp);
		targetExp.containedExpressions(expList);

	}

}
