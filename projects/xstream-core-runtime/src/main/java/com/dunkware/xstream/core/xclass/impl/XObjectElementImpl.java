package com.dunkware.xstream.core.xclass.impl;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XExpressionType;

public abstract class XObjectElementImpl implements XObjectElement {

		private XClassElementType _type;
		private XObjectElement _parent; 
		private List<XObjectElement> _children = new ArrayList<XObjectElement>();
		private XObject _xObject;
		
		
		@Override
		public void setType(XClassElementType type) {
			_type = type; 
			
		}

		@Override
		public void setParent(XObjectElement element) {
			_parent = element; 
		}

		@Override
		public XObjectElement getParent() {
			return _parent; 
		}

		

		@Override
		public List<XObjectElement> getChildren() {
			return _children;
		}

		@Override
		public void setXObject(XObject object) {
			_xObject = object; 
		}
		
		
		@Override
		public XObject getXObject() {
			return _xObject;
		}


		@Override
		public XClassElementType getType() {
			return _type;
		}

		protected XObjectElement createElement(XClassElementType element)  { 
			XObjectImpl impl = (XObjectImpl)getXObject();
			return impl.getContext().createElement(element);
		}
		
		protected XObjectExpression createExpression(XExpressionType type)  { 
			return getXObject().getContext().createXExpression(type);
		}

}
