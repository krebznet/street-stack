package com.dunkware.net.core.runtime.proto.stream;

import com.dunkware.common.util.enums.DOperator;
import com.dunkware.net.proto.core.GOperator;

public class VarCompareCriteria {

	private VarValueResolver target; 
	private VarValueResolver compare; 
	private String expression; 
	private DOperator operator; 
	private String value; 
}
