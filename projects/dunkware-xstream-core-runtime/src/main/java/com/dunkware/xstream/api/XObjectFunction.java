/**
 * 
 */
package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.xScript.XFunctionType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
public interface XObjectFunction {

	public Object invoke(List<Object> args) ;

	public XFunctionType getFunctionType();

}
