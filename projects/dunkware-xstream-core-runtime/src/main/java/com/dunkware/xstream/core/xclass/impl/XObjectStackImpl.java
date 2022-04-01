/**
 * 
 */
package com.dunkware.xstream.core.xclass.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XObjectStack;
import com.dunkware.xstream.api.XObjectVar;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
public class XObjectStackImpl implements XObjectStack {
	

	private Stack<XFrame> _frameStack = new Stack<XFrame>();
	private Logger _logger = LoggerFactory.getLogger(getClass());
	private Semaphore _frameSemaphore = new Semaphore(1);

	@Override
	public void pushFrame() {
		try {
			_frameSemaphore.acquire();
			XFrame frame = new XFrame();
			_frameStack.push(frame);
			
		} catch (Exception e) {
			
		} finally { 
			_frameSemaphore.release();
		}
		
	}

	@Override
	public void popFrame() {
		
		
		try {
			_frameSemaphore.acquire();
			_frameStack.pop();
		} catch (Exception e) {
			
		} finally { 
			_frameSemaphore.release();
		}
		
		
	}

	@Override
	public XObjectVar getVar(String name) {
		try {
			_frameSemaphore.acquire();
			XObjectVar var = _frameStack.peek().getVar(name);
			if(var == null) {
					for (XFrame frame : _frameStack) {
						var = frame.getVar(name);
						if(var != null) { 
							return var; 
						}
					}
				
				
			}
			return var;
		} catch (Exception e) {
			return null;
		} finally { 
			_frameSemaphore.release();
		}
		
		
	}
	
	

	@Override
	public  void setVarValue(String name, Object value) {
		try {
			_frameSemaphore.acquire();
			
			if(value == null) {
				_logger.error("Setting Null Value on XOBject Variable " + name);
			}
		
				XObjectVarImpl var = (XObjectVarImpl)_frameStack.peek().getVar(name);
				if(var == null) {
					for (XFrame frame : _frameStack) {
						var = (XObjectVarImpl)frame.getVar(name);
						if(var != null) { 
							var.setValue(value);
							
								
								frame.addVairable(var);
								break;
							
							
						}
					}
				}
		} catch (Exception e) {
			
		} finally { 
			_frameSemaphore.release();
		}
		// That is the Software Stack // 
		
		
	

		
	}

	@Override
	public void setVar(XObjectVar var) {
		try {
			_frameSemaphore.acquire();
			_frameStack.peek().addVairable(var);
		} catch (Exception e) {
			
		} finally { 
			_frameSemaphore.release();
		}
	
	}
	
	private class XFrame {
		
		private Map<String,XObjectVar> _varMap = new HashMap<String,XObjectVar>();
		
		public void addVairable(XObjectVar var) { 
			_varMap.put(var.getName(), var);
		}
		
		public XObjectVar getVar(String name) {
			return _varMap.get(name);
		}
		
	}
	

}
