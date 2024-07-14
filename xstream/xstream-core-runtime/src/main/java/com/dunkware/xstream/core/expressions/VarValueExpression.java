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
import com.dunkware.xstream.xScript.VariableValueType;

@AXStreamExpression(type = VariableValueType.class)
public class VarValueExpression extends XStreamExpressionImpl {
	
	private VariableValueType type;
	private XStreamExpression indexExpression = null;
	private boolean useExpression = false;
	private int indexValue = 0;
	private XStreamEntity row;
	private XStreamEntityVar valueVar = null;

	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.row = row;
		this.type = (VariableValueType)type;
		
		if(this.type.getExpType() != null) { 
			useExpression = true;
			indexExpression = row.getStream().getInput().getRegistry().createVarExpression(this.type.getExpType());	
			indexExpression.init(row,this.type.getExpType());
			
		} else { 
			useExpression = false;
			indexValue  = this.type.getIndexInt();
		}
	}

	@Override
	public void start() {
		valueVar = row.getVar(type.getVariable().getName());
		if(indexExpression != null) {
			indexExpression.start();
		}
		if(!useExpression) { 
			valueVar.setMaxSize(indexValue);
		}
		
	}

	@Override
	public void dispose() {
		if(indexExpression != null) {
			indexExpression.dispose();
		}	
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	@Override
	public boolean execute() {
		try {
			if(useExpression == false) {
				Object value = valueVar.getValue(indexValue);
				if(value == getValue()) { 
					return false;
				}
				setValue(value);
				return true;
			}
			indexExpression.execute();
			Object objValue = indexExpression.getValue();
			Integer index = DunkNumber.toInteger(objValue); 
			if(valueVar.getSize() + 1 < index) { 
				valueVar.setMaxSize(index + 1);
				return false;
			}
			Object value = valueVar.getValue(index);
			if(value == getValue()) { 
				return false;
			}
			setValue(value);
			return true;
		} catch (Exception e) {
			throw new XStreamRuntimeException("Exception executing var value " + e.toString());
		}
			
	}

	@Override
	public boolean canExecute() {
		int histSize = valueVar.getSize();
		if(useExpression == false) {
			if(indexValue < histSize) {
				return true;
			}
			return false;
		}
		if(indexExpression.canExecute() == false) { 
			return false;
		}
		// get index expression value and compare to size of value var
		int indexValue = 0;
		try {
			indexExpression.execute();
			Object value = indexExpression.getValue();
			if(value == null) { 
				//// VarValueExp index exp return null
				return false;
			}
			// DConvertHelper.
			indexValue = DunkNumber.toInteger(value);
			// set the max size on variable value
			valueVar.setMaxSize(indexValue + 1);
			if(histSize + 1 < indexValue) { 
				return false; 
			}
		} catch (Exception e) {
			throw new XStreamRuntimeException("Exception casting index expression to int " + e.toString());
		}
		return true;
	}

	@Override
	public void containedVariables(List<XStreamEntityVar> varList) {
		varList.add(valueVar);
		if(indexExpression != null) { 
			indexExpression.containedVariables(varList);
		}
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		if(indexExpression != null) { 
			indexExpression.containedExpressions(expList);
		}
	}

	
	
}
