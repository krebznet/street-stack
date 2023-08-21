package com.dunkware.xstream.core.expressions;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SetExpressionType;


@AXStreamExpression(type = SetExpressionType.class)
public class VarSetExpression extends XStreamExpressionImpl {
	
	private SetExpressionType type; 
	private XStreamEntity row; 
	
	private List<XStreamExpression> children = new ArrayList<XStreamExpression>();

	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.row = row;
		this.type = (SetExpressionType)type;
		for (ExpressionType expType : this.type.getArgs()) {
			XStreamExpression exp = row.getStream().getInput().getRegistry().createVarExpression(expType);
			exp.init(row,expType);
			children.add(exp);
		}
	}

	@Override
	public void start() {
		for (XStreamExpression xStreamExpression : children) {
			xStreamExpression.start();
		}
	}

	@Override
	public void dispose() {
		for (XStreamExpression xStreamExpression : children) {
			xStreamExpression.dispose();
		}
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	@Override
	public boolean execute() {
		List<Object> values = new ArrayList<Object>();
		for (XStreamExpression expression : children) {
			expression.execute();
			Object value = expression.getValue();
			if (value instanceof Object[]) {
				Object[] valueArray = (Object[]) value;
				for (Object object : valueArray) {
					values.add(object);
				}
			} else { 
				values.add(value);
			}
		}
		setValue(values.toArray(new Object[values.size()]));
		return true;
	}

	@Override
	public boolean canExecute() {
		for (XStreamExpression child : children) {
			if(!child.canExecute()) {
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public void containedVariables(List<XStreamEntityVar> varList) {
		for (XStreamExpression exp : children) {
			exp.containedVariables(varList);
		}
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		for (XStreamExpression xStreamExpression : children) {
			xStreamExpression.containedExpressions(expList);
		}
	}
	
	

	

	
}
