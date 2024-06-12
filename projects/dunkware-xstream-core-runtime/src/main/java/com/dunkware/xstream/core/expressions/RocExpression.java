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
					// TODO: is this exception?
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
