package com.dunkware.xstream.core.expressions;

import java.util.List;

import com.dunkware.utils.core.helpers.DunkMath;
import com.dunkware.utils.core.helpers.DunkNumber;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.RocExpressionType;
//TODO: AVINASHANV-15 Computing Expressions
/**
 * this is cool, so for each expression type in the grammar we have a expression 
 * in runtime tha is matched to the script model using annotation. a expression is 
 * associated to an entity variable, this one does the rate of change between two 
 * values, all variables keep historical data so its easy to create value ranges
 * in the past etc. 
 */
@AXStreamExpression(type = RocExpressionType.class)
public class RocExpression extends XStreamExpressionImpl {

	private RocExpressionType type;
	private XStreamEntity row;

	private XStreamExpression targetExp;
	private XStreamExpression compareExp;

	
	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.row = row;
		this.type = (RocExpressionType) type;
		//TODO: AVINASHANV-16 RocExpressionType 
		/*
		 * RocExpressionType is auto generated from xtext scripting framework defined in our grammer
		 * in script its like roc(value1,value2) variable in xscript by default maintain historical
		 * history so we can get values like a range of historical values in the last 5 minutes the
		 * value 5 minutes ago etc. here what we do is the scirpt model has an expression type for 
		 * both values, we use the registry to create a new expression by passing in the expression type
		 * and using annotations it builds an expression. 
		 */
		targetExp = row.getStream().getInput().getRegistry().createVarExpression(this.type.getTarget());
		targetExp.init(row, this.type.getTarget());
		compareExp = row.getStream().getInput().getRegistry().createVarExpression(this.type.getCompare());
		compareExp.init(row, this.type.getCompare());
	}

	@Override
	public void start() {
		targetExp.start();
		compareExp.start();
	}

	@Override
	public void dispose() {
		targetExp.dispose();
		compareExp.dispose();
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	//TODO: AVINASHANV-16 Expression executions
	/**
	 * we have a boolean canExecute in the event the expression like historical high over
	 * last 30 days that does not have enough historical data to compute will return false
	 * whenver that happens the expression and variable linked to it is not resolvable. 
	 * here we simple do the rate of change and we get the values of the expressions
	 * we are doing roc one... good example of an roc is comparing current value of a 
	 * variable like Mtc30sec which is the number of trades wihtin the last 30 seconds
	 * to the highest value that variable has had for the entity in the list 30 days
	 * if its 10X greater we start understanding breakouts. 
	 */
	@Override
	public boolean execute() {
		
		if (canExecute()) {
			try {

				 Double targetValue = null;
				 Double compareValue = null;
				 Double rocValue = null;

				targetExp.execute();
				compareExp.execute();

				Object targetObj = targetExp.getValue();
				Object compObj = compareExp.getValue();

				if (targetObj == null || compObj == null) {
					return false;
				}

				if (targetObj instanceof Double) {
					targetValue = (Double) targetObj;
				}
				if (compObj instanceof Double) {
					compareValue = (Double) compObj;
				}
				if (compObj instanceof Integer) {
					Integer intValue = (Integer) compObj;
					compareValue = DunkNumber.toDouble(intValue);
				}
				if (targetObj instanceof Integer) {
					Integer intValue = (Integer) targetObj;
					targetValue = DunkNumber.toDouble(intValue);
				}
				if (compObj instanceof Long) {
					Long intValue = (Long) compObj;
					compareValue = DunkNumber.toDouble(intValue);
				}
				if (targetObj instanceof Long) {
					Long intValue = (Long) targetObj;
					targetValue = DunkNumber.toDouble(intValue);
				}
				if (targetValue == null || compareValue == null) {
					// is this exception?
					return false;
				}

				if (targetValue == Double.NaN) {
					return false;
				}

				if (String.valueOf(targetValue).equals("NaN")) {
					return false;
				}
				if (String.valueOf(compareValue).equals("NaN")) {
					return false;
				}
				if (targetValue.isInfinite()) {
					return false;
				}
				if (compareValue == Double.NaN) {
					return false;
				}
				if (compareValue.isInfinite()) {
					return false;
				}
				if (targetValue == 0 && compareValue == 0) {
					Object value = getValue();
					if (value != null) {

					}
					if (value instanceof Double) {
						Double doubeleValue = (Double) value;
						if (doubeleValue == 0.0) {
							return false;
						}
					}
					setValue(0.0);
					return true;
				}
				if (targetValue == 0 && compareValue != 0) {
					Object value = getValue();
					if (value != null) {
						if (value instanceof Double) {
							Double doubeleValue = (Double) value;
							if (doubeleValue == Math.abs(compareValue)) {
								return false;
							}
						}
					}
					
					setValue(Math.abs(compareValue));
					return true;
				}
				if (compareValue == 0 && targetValue != 0) {
					Object value = getValue();
					if (value != null) {
						if (value instanceof Double) {
							Double doubeleValue = (Double) value;
							if (doubeleValue == Math.abs(targetValue)) {
								return false;
							}
						}
					}

					setValue(Math.abs(targetValue));
					return true;
				}
				
				rocValue = DunkMath.getPercentageChange(targetValue, compareValue);
				
				Object value = getValue();
				if(value != null) { 
					if (value instanceof Double) {
						Double doubleValue = (Double) value;
						if(doubleValue == rocValue) { 
							return false; 
						}
					}
				}
				
				setValue(rocValue);
				return true;

			} catch (Exception e) {
				throw new XStreamRuntimeException("Execute Roc Expression Exception " + e.toString(),e);
			}

		}
		return false;
	}

	 
	@Override
	public boolean canExecute() {
		if (targetExp.canExecute() && compareExp.canExecute()) {
			return true;
		}
		return false;
	}

	@Override
	public void containedVariables(List<XStreamEntityVar> varList) {
		targetExp.containedVariables(varList);
		compareExp.containedVariables(varList);
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		expList.add(targetExp);
		expList.add(compareExp);
		targetExp.containedExpressions(expList);
		compareExp.containedExpressions(expList);
	}

}
