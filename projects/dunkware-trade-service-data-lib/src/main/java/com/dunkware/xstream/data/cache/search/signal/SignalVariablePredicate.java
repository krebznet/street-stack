package com.dunkware.xstream.data.cache.search.signal;

import java.util.function.Predicate;

import com.dunkware.net.proto.core.GOperator;
import com.dunkware.xstream.data.cache.CacheEntitySignal;

public class SignalVariablePredicate implements Predicate<CacheEntitySignal> {


	public static SignalVariablePredicate newInstance(String var, GOperator operator, double value) {
		return new SignalVariablePredicate(var, operator, value);
	}
	
	private String var; 
	private GOperator operator; 
	private double number; 
	
	public SignalVariablePredicate(String var, GOperator operator, double value) { 
		this.var = var;
		this.operator = operator;
		this.number = value;
	}
	
	@Override
	public boolean test(CacheEntitySignal t) {
		if(t.getVars().hasValue(var) == false) { 
			return false; 
		}
		double compare;
		try {
		 Number bumer = (Number)t.getVars().getValue(var);
		 compare = bumer.doubleValue();
		} catch (Exception e) {
			return false; 
		}
		if(operator == GOperator.GT) { 
			if(compare > number) { 
				return true;
			}
			return false;
		}
		if(operator == GOperator.LT) { 
			if(compare < number) { 
				return true;
			}
			return false;
		}
		if(operator == GOperator.LTE) { 
			if(compare < number || number == compare) { 
				return true;
			}
			return false;
		}
		if(operator == GOperator.GTE) { 
			if(compare > number || number == compare) { 
				return true;
			}
			return false;
		}
		
		if(operator == GOperator.EQ) { 
			if( number == compare) { 
				return true;
			}
			return false;
		}
		
		if(operator == GOperator.NQ) { 
			if( number != compare) { 
				return true;
			}
			return false;
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
