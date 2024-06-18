package com.dunkware.xstream.core.expressions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.utils.core.helpers.DunkNumber;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ComparisonType;
import com.dunkware.xstream.xScript.ExpressionType;

@AXStreamExpression(type = ComparisonType.class)
public class ComparisonExp extends XStreamExpressionImpl {

	public static final int GREATER = 1;
	public static final int LESS = 2;
	public static final int GREATEREQ = 3;
	public static final int LESSEQ = 4;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ComparisonType myType;
	private XStreamEntity entity;

	private XStreamExpression leftExpression;
	private XStreamExpression rightExpression;

	private int operator = -4;

	private boolean error = false;

	// figure out the operator == man this is tough shit.

	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.entity = row;
		this.myType = (ComparisonType) type;
		// set operator or conver to int for faster execution
		if (myType.getOp().equals(">")) {
			operator = GREATER;
		}

		if (myType.getOp().equals("<")) {
			operator = LESS;
		}
		if (myType.getOp().equals(">=")) {
			operator = GREATEREQ;
		}

		if (myType.getOp().equals("<=")) {
			operator = LESSEQ;
		}
		if (operator == -5) {
			logger.error("Invalid operator in Comparison expression " + myType.getOp());
			row.getStream().incrementErrorCount();
			error = false;
			return;
		}
		try {
			leftExpression = row.createExpressoin(myType.getLeft());
			rightExpression = row.createExpressoin(myType.getRight());

		} catch (Exception e) {
			logger.error("Exception creating equality left/right expressions " + e.toString());
			;
			error = true;
			row.getStream().runtimeError("Expression", "Equality Expression LEft/Right Create failed " + e.toString());
			return;
		}
		leftExpression.init(row, myType.getLeft());
		rightExpression.init(row, myType.getRight());
	}

	@Override
	public void start() {
		leftExpression.start();
		rightExpression.start();
	}

	@Override
	public void dispose() {
		leftExpression.dispose();
		rightExpression.dispose();

	}

	@Override
	public void containedVariables(List<XStreamEntityVar> varList) {
		leftExpression.containedVariables(varList);
		rightExpression.containedVariables(varList);
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		expList.add(leftExpression);
		expList.add(rightExpression);
		leftExpression.containedExpressions(expList);
		rightExpression.containedExpressions(expList);
	}

	@Override
	public ExpressionType getExpType() {
		return myType;
	}

	@Override
	public boolean execute() {
		try {
			leftExpression.execute();
			rightExpression.execute();

			Object leftValue = leftExpression.getValue();
			Object rightValue = rightExpression.getValue();

			int compare = 2;
			if (leftValue instanceof Number && rightValue instanceof Number) {
				compare = DunkNumber.compare((Number) leftValue, (Number) rightValue);
			} else {
				entity.getStream().incrementErrorCount();
				logger.error(
						"Invalid comparison we are looking for greater / less than etc values returned from left / right {}, {}",
						leftValue.getClass().getName(), rightValue.getClass().getName());
				return false;
			}

			switch (operator) {
			case GREATER:
				if(compare == 1) { 
					setValue(Boolean.TRUE);
					return true;
				} else { 
					setValue(Boolean.FALSE);
					return false;
				}
				
			case LESS:
				if(compare == -1) { 
					setValue(Boolean.TRUE);
					return true;
				} else { 
					setValue(Boolean.FALSE);
					return false;
				}
				
			case LESSEQ:
				if(compare == 1 || compare == 0) { 
					setValue(Boolean.TRUE);
					return true;
				} else { 
					setValue(Boolean.FALSE);
					return false;
				}
				
			case GREATEREQ:
				if(compare == 1 || compare == 0) { 
					setValue(Boolean.TRUE);
					return true;
				} else { 
					setValue(Boolean.FALSE);
					return false;
				}
			}

			// no good
			logger.error("Operator type not handled " + myType.getOp());
			entity.getStream().incrementErrorCount();
		} catch (Exception e) {
			logger.error("Equality exp error " + e.toString());
		}

		return false;
	}

	@Override
	public boolean canExecute() {
		if (error) {
			return false;
		}
		if (!leftExpression.canExecute() || rightExpression.canExecute()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Double v1 = 23.0;
		Integer v2 = 23;
		System.out.println(v1.equals(v2));

	}

}
