package com.dunkware.xstream.core.expressions;

import java.util.List;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.VariableValueRangeType;

@AXStreamExpression(type = VariableValueRangeType.class)
public class VarValueRangeExpression extends XStreamExpressionImpl {

	private VariableValueRangeType type;
	private XStreamRow row;
	private XStreamVar targetVar = null;
	private int startIndex = 0;
	private int endIndex = 0;
	
	
	@Override
	public void init(XStreamRow row, ExpressionType type) {
		this.row = row;
		this.type = (VariableValueRangeType)type;
		this.startIndex = this.type.getStartIndex();
		this.endIndex = this.type.getEndIndex();
		if(startIndex > endIndex) { 
			throw new XStreamRuntimeException("VarValueRange expression is invalid start index cannot be less than end index " + startIndex + "," + endIndex);
		}
	}

	@Override
	public void start() {
		this.targetVar = row.getVar(type.getTargetVar().getName());
		targetVar.setMaxSize(endIndex);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	@Override
	public boolean execute() {
		if(targetVar.getSize() - 1 < 1) { 
			return false;
		}
		int range = (endIndex - startIndex);
		Object[] data = new Object[range];
		targetVar.getValues(data, startIndex, endIndex);
		setValue(data);
		return true;
	}

	@Override
	public boolean canExecute() {
		if(targetVar.getSize() - 1 < 1) { 
			return false;
		}
		
		if(targetVar.getSize() > endIndex -1) {
			return true; 
		}
		return false;
	}

	@Override
	public void containedVariables(List<XStreamVar> varList) {
		varList.add(targetVar);
	}

}
