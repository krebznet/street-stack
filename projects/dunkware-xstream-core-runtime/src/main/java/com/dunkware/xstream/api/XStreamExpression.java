package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.xScript.ExpressionType;

public interface XStreamExpression {

	/**
	 * Initializes the expression
	 * @param row
	 * @param expType
	 * @throws XStreamException
	 */
	public void init(XStreamRow row, ExpressionType type) ;
	
	/**
	 * Starts the expression after all variables have been
	 * created. needed to set max size on variables
	 */
	public void start();
	
	/**
	 * Called to clean up any allocated resources
	 */
	public void dispose();

	/**
	 * Returns the expression type
	 * @return
	 */
	public ExpressionType getExpType();

	/**
	 * Gets the current value
	 * @return
	 */
	public Object getValue();
	
	/**
	 * Returns true if value was updated, false if no update 
	 * @return
	 */
	public boolean execute();
	
	/**
	 * this method is invoked before execute()
	 * @return
	 */
	public boolean canExecute();

	
	/**
	 * Add a Expression listener to recieve notification on expression
	 * value updates 
	 * @param list
	 */
	public void addExpressionListener(XStreamExpressionListener list);

	
	/**
	 * remove a Expression listener 
	 * @param list
	 */
	public void removeExpressionListener(XStreamExpressionListener list);
	
	/**
	 * Child classes can override this to provide contained variables
	 * @param varList
	 */
	public void containedVariables(List<XStreamVar> varList);

	/**
	 * Child classes can override this to provide contained expressions
	 * @param expList
	 */
	public void containedExpressions(List<XStreamExpression> expList);

	/**
	 * Child classes can override this to indicate their expression 
	 * is an input type, does not get its value derived from other
	 * expressions 
	 * @return
	 */
	public boolean isInput();

	/**
	 * Forget about this one, its here for a reason. 
	 * @return
	 */
	public boolean excludeDownStream();

}
