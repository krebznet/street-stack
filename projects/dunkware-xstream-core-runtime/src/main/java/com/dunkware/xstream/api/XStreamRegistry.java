package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public interface XStreamRegistry {
	
	public boolean hasVarExpressionType(ExpressionType type);
	
	public XStreamExpression createVarExpression(ExpressionType type);
	
	public XStreamExtension createExtension(XStreamExtensionType type);
	
	public boolean hasExtensionType(XStreamExtensionType type);
	
	public void registerExtension(Class<? extends XStreamExtensionType> type,Class<? extends XStreamExtension> ext);

	public XObjectElement createObjectElement(XClassElementType type);
	
	public XObjectExpression createObjectExpression(XExpressionType type);
	
	public List<DD> createServices();
}
