package com.dunkware.xstream.core.expressions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.AndType;
import com.dunkware.xstream.xScript.ExpressionType;


@AXStreamExpression(type = AndType.class)
public class AndExp extends XStreamExpressionImpl {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AndType myType;
	private XStreamEntity entity;
	private XStreamExpression leftExp;
	private XStreamExpression rightExp;
	private boolean error = false;

	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.entity = row;
		myType = (AndType) type;
		try {
			leftExp = row.createExpressoin(myType.getLeft());
			rightExp = row.createExpressoin(myType.getRight());
		} catch (Exception e) {
			logger.error("Exception creating And left/right type " + myType.getLeft().getClass().getName() + " "
					+ myType.getRight().getClass().getName() + " " + e.toString());
			row.getStream().incrementErrorCount();
			error = false;
			return;
		}
		leftExp.init(row, myType.getLeft());
		rightExp.init(row, myType.getRight());

	}

	@Override
	public void start() {
		leftExp.start();
		rightExp.start();
	}

	@Override
	public void dispose() {
		leftExp.dispose();
		rightExp.dispose();
	}

	@Override
	public ExpressionType getExpType() {
		return myType;
	}

	@Override
	public void containedVariables(List<XStreamEntityVar> varList) {
		leftExp.containedVariables(varList);
		rightExp.containedVariables(varList);
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		expList.add(leftExp);
		expList.add(rightExp);
		leftExp.containedExpressions(expList);
		rightExp.containedExpressions(expList);
	}

	@Override
	public boolean execute() {
		if (!canExecute()) {
			return false;
		}
		leftExp.execute();
		rightExp.execute();

		Object leftValue = leftExp.getValue();
		Object rightValue = rightExp.getValue();

		if (leftValue instanceof Boolean == false || rightValue instanceof Boolean == false) {
			setValue(Boolean.FALSE);
			return true;
		}
		Boolean leftBool = (Boolean) leftValue;
		Boolean rightBool = (Boolean) rightValue;
		if (leftBool == true && rightBool == true) {
			setValue(Boolean.TRUE);
			return true;
		}
		setValue(Boolean.FALSE);
		return true;
	}

	@Override
	public boolean canExecute() {
		if (error) {
			return false;
		}
		if (leftExp.canExecute() != true || rightExp.canExecute() != true) {
			return false;
		}

		return true;

	}

}
