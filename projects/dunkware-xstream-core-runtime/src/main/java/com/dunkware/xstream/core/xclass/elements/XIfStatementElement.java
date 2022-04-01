/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XElseIfStatementType;
import com.dunkware.xstream.xScript.XElseStatementType;
import com.dunkware.xstream.xScript.XIfStatementType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XIfStatementType.class)
public class XIfStatementElement extends XObjectElementImpl  {

	private XIfStatementType _type;
	private XObjectExpression _expression;

	private List<ElseIfElement> _elseIfElements = new ArrayList<ElseIfElement>();
	private ElseElement _elseElement = null; 
	
	@Override
	public void init()  {
		_type = (XIfStatementType)getType();
		_expression = getXObject().getContext().createXExpression(_type.getExpression());
		// create/init ElseIfStatements if we have any. 
		for (XElseIfStatementType childType : _type.getElseIfElements()) {
			ElseIfElement elseIf = new ElseIfElement(childType);
			_elseIfElements.add(elseIf);
		}
		if(_type.getElseElement() != null) {
			_elseElement = new ElseElement(_type.getElseElement());
		}
		// create children 
		for (XClassElementType type : _type.getElements()) {
			XObjectElement child = getXObject().getContext().createElement(type);
			child.setParent(this);
			child.setType(type);
			child.setXObject(getXObject());
			child.init();
			getChildren().add(child);
		}

	}

	@Override
	public void dispose()  {
		for (XObjectElement child : getChildren()) {
			child.dispose();
		}
		for (ElseIfElement elseIfElement : _elseIfElements) {
			elseIfElement.dispose();
		}
		if(_elseElement != null) { 
			_elseElement.dispose();
		}
	}

	@Override
	public void execute()  {
		// if we can't execute the root expression don't 
		// execute child else/if and else 
		if(_expression.canExecute() == false) {
			return;
		}
		Object expValue = _expression.execute();
		
		// if expression does not return boolean value 
		// then we can't do anything else 
		if (expValue instanceof Boolean == false) {
			return;
		}
		Boolean retBool = (Boolean)expValue;
		if(retBool == true) {
			for (XObjectElement element : getChildren()) {
				element.execute();
			}
			return;
		}
		// check else/if statements 
		for (ElseIfElement elseIfElement : _elseIfElements) {
			if(elseIfElement.getExpression().canExecute() == false) { 
				return;
			}
			Object value = elseIfElement.getExpression().execute();
			if (value instanceof Boolean) {
				Boolean boolValue = (Boolean) value;
				if(boolValue == true) { 
					// execute else/if statement
					// getExp .. if canExecute do that -- 
					for (XObjectElement element : elseIfElement.getChildren()) {
							element.execute();
					}
					return;
				}
			}
		}
		if(_elseElement != null) { 
			for (XObjectElement element : _elseElement.getChildren()) {
				element.execute();
			}
		}
	}
	
	private class ElseIfElement {
		
		private XObjectExpression expression; 
		private List<XObjectElement> elements = new ArrayList<XObjectElement>();
		private XElseIfStatementType _type; 
		
		public ElseIfElement(XElseIfStatementType type) {
			// create expression 
			expression = getXObject().getContext().createXExpression(_type.getExpression());
			// craete elements 
			for (XClassElementType childType : type.getElements()) {
				XObjectElement child = getXObject().getContext().createElement(childType);
				child.setParent(XIfStatementElement.this);
				child.setType(childType);
				child.setXObject(getXObject());
				child.init();
				elements.add(child);
			}
		}
		
		public XObjectExpression getExpression() { 
			return expression;
		}
		
		public List<XObjectElement> getChildren() {
			return elements;
		}
		
		public void dispose()  {
			expression.dispose();
			for (XObjectElement element : elements) {
				element.dispose();
			}
			// children elements; 
		}
	}
	
	private class ElseElement { 
		
		private XElseStatementType _type;
		private List<XObjectElement> elements = new ArrayList<XObjectElement>();
		
		public ElseElement(XElseStatementType type)  { 
			_type = type; 
			for (XClassElementType type2 : _type.getElements()) {
				for (XClassElementType childType : type.getElements()) {
					XObjectElement child = getXObject().getContext().createElement(childType);
					child.setParent(XIfStatementElement.this);
					child.setType(childType);
					child.setXObject(getXObject());
					child.init();
					elements.add(child);
				}
		
			}
			
		}
		
		public List<XObjectElement> getChildren() { 
			return elements; 
		}
		
		public void dispose()  { 
			for (XObjectElement element : elements) {
				element.dispose();
			}
		}
		
		
	}
}
