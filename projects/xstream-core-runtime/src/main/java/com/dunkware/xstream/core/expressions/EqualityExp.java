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
import com.dunkware.xstream.xScript.EqualityType;
import com.dunkware.xstream.xScript.ExpressionType;

@AXStreamExpression(type = EqualityType.class)
public class EqualityExp extends XStreamExpressionImpl {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private EqualityType myType; 
	private XStreamEntity entity; 
	
	private XStreamExpression leftExpression;
	private XStreamExpression rightExpression; 
	
	private boolean error = false; 
	
	// figure out the operator == man this is tough shit. 
	
	@Override
	public void init(XStreamEntity row, ExpressionType type){
		this.entity = row;
		this.myType = (EqualityType)type; 
		
		try {
			leftExpression = row.createExpressoin(myType.getLeft());
			rightExpression = row.createExpressoin(myType.getRight());	
			
		} catch (Exception e) {
			logger.error("Exception creating equality left/right expressions " + e.toString());;
			error = true; 
			row.getStream().runtimeError("Expression", "Equality Expression LEft/Right Create failed " + e.toString());
			return; 
		}
		leftExpression.init(row,myType.getLeft());
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
			
			boolean equals = false; 
			if (leftValue instanceof Number && rightValue instanceof Number) {
				int compare = DunkNumber.compare((Number)leftValue, (Number)rightValue);
				if(compare == 0) { 
					equals = true;
				} else { 
					equals = false; 
				}
			} else { 
				equals = leftValue.equals(rightValue);
				
			}
			
			if(myType.getOp().equals("==")) { 
				if(equals) { 
					setValue(Boolean.TRUE);
					return true; 
				} else { 
					setValue(Boolean.TRUE);
					return true; 

				}
				
			}
			
			if(myType.getOp().equals("!=")) { 
				if(equals) { 
					setValue(Boolean.FALSE);
					return true; 

				} else { 
					setValue(Boolean.TRUE);
					return true; 
				}
							
			}
			
			// no good 
			logger.error("Operator type not handled " + myType.getOp());
			
			
		} catch (Exception e) {
			logger.error("Equality exp error " + e.toString());
		}
		
		return false;
	}

	@Override
	public boolean canExecute() {
		if(error) { 
			return false; 
		}
		if(!leftExpression.canExecute() || rightExpression.canExecute() ) {
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
