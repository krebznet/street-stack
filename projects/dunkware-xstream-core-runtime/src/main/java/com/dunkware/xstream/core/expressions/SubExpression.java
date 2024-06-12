package com.dunkware.xstream.core.expressions;

import java.util.List;

import com.dunkware.utils.core.helpers.DunkNumber;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SubExpressionType;

@AXStreamExpression(type = SubExpressionType.class)
public class SubExpression extends XStreamExpressionImpl {

	private SubExpressionType type;
	private XStreamEntity row; 
	
	private XStreamExpression targetExp;
	private XStreamExpression compareExp; 
	
	
	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.row = row;
		this.type = (SubExpressionType)type;
		this.targetExp = row.getStream().getInput().getRegistry().createVarExpression(this.type.getTarget());
		this.targetExp.init(row, this.type.getTarget());
		this.compareExp = row.getStream().getInput().getRegistry().createVarExpression(this.type.getCompare());
		this.compareExp.init(row, this.type.getCompare());
		
	}

	@Override
	public void start() {
		this.targetExp.start();
		this.compareExp.start();
	}

	@Override
	public void dispose() {
		this.targetExp.dispose();
		this.compareExp.dispose();
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	@Override
	public boolean execute() {
		if(!targetExp.execute() || !compareExp.execute()) { 
			return false; 
		}
		Object targetValue = targetExp.getValue();
		Object compareValue = compareExp.getValue();
		// check if both ints 
		if (compareValue instanceof Integer && targetValue instanceof Integer) {
			Integer targetInt = (Integer)targetValue;
			Integer compareInt = (Integer) compareValue;
			setValue(targetInt - compareInt);
			return true;
		}
		if (compareValue instanceof Double && targetValue instanceof Double) {
			Double targetInt = (Double)targetValue;
			Double compareInt = (Double) compareValue;
			setValue(targetInt - compareInt);
			return true;
		}
		if (compareValue instanceof Double || targetValue instanceof Double) {
			try {
				Double targetInt = DunkNumber.toDouble(targetValue);
				Double compareInt = DunkNumber.toDouble(compareValue);
				setValue(targetInt - compareInt);
				return true;	
			} catch (Exception e) {
				throw new XStreamRuntimeException("Exception casting sub values to doubles check code line 75" + e.toString());
			}
		}
		if (compareValue instanceof Long &&  targetValue instanceof Long) {
			try {
				Long targetInt = (Long)targetValue;
				Long compareInt = (Long)compareValue;
				setValue(targetInt - compareInt);
				return true;	
			} catch (Exception e) {
				throw new XStreamRuntimeException("Exception casting sub values to doubles check code line 75" + e.toString());
			}
		}
		
		if (compareValue instanceof String || targetValue instanceof String) {
			throw new XStreamRuntimeException("Sub target type(s) are string cannot subtract !!" );
		}
		throw new XStreamRuntimeException("Unhandled data types in sub expression line 97! target type " + targetValue.getClass().getName() + " compare type " + compareValue.getClass().getName());
		
	}

	@Override
	public boolean canExecute() {
		if(targetExp.canExecute() && compareExp.canExecute()) { 
			return true;
		}
		return false; 
	}

	@Override
	public void containedVariables(List<XStreamEntityVar> varList) {
		compareExp.containedVariables(varList);
		targetExp.containedVariables(varList);
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		expList.add(targetExp);
		expList.add(compareExp);
		compareExp.containedExpressions(expList);
		targetExp.containedExpressions(expList);
	}

	
}
