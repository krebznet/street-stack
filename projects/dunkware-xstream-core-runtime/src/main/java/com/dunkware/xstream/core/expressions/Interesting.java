package com.dunkware.xstream.core.expressions;

import java.util.Arrays;
import java.util.List;

import com.dunkware.xstream.xScript.AvgExpressionType;
import com.dunkware.xstream.xScript.SetExpressionType;
import com.dunkware.xstream.xScript.VarStoreType;
import com.dunkware.xstream.xScript.XScript;
import com.dunkware.xstream.xScript.XScriptFactory;
import com.dunkware.xstream.xScript.XVarType;

import javassist.compiler.ast.Pair;

public class Interesting {

	public static void main(String[] args) {
		
	
		
		XVarType var = XScriptFactory.eINSTANCE.createXVarType();
		VarStoreType t = XScriptFactory.eINSTANCE.createVarStoreType();
		t.setCode(1);
		t.setName("TmaSa");
		
		// Relative Variable Session Average 
		AvgExpressionType tt = XScriptFactory.eINSTANCE.createAvgExpressionType();
		SetExpressionType ss  = XScriptFactory.eINSTANCE.createSetExpressionType();
		// 
		//ExpressionType t = XScriptFactory.eINSTANCE.createExpressionType();
		XScript script = XScriptFactory.eINSTANCE.createXScript();
		script.getElements().add(t);
		
		// Expression - input("d"); 
		// Expression - "dd";
		
		
	}
}
