package com.dunkware.xstream.net.core.container.search.entity;

import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.proto.data.GOperator;
import com.dunkware.net.proto.netstream.GNetEntityVarCriteria;
import com.dunkware.net.proto.netstream.GNetEntityVarValueType;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchContext;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.ContainerSearchPredicate;
import com.dunkware.xstream.net.core.container.util.ContainerHelper;

public class EntityVarCriteriaPredicate implements Predicate<ContainerEntity>, ContainerSearchPredicate {



	public static EntityVarCriteriaPredicate newInstance(GNetEntityVarCriteria varCritiera) throws ContainerSearchException {
		return new EntityVarCriteriaPredicate(varCritiera);
	}
	
	GNetEntityVarCriteria criteria;
	GOperator criteriaOperator = null;
	private ContainerSearchContext context;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public EntityVarCriteriaPredicate(GNetEntityVarCriteria varCriteria) throws ContainerSearchException {
		if(varCriteria.getVar().getType() != GNetEntityVarValueType.VALUE_NOW) { 
			throw new ContainerSearchException("Var Value Type " + varCriteria.getVar().getType().name() + " not implemented yet");
		}
		this.criteriaOperator = varCriteria.getOperator();
		this.criteria = varCriteria;
	}
	
	@Override
	public void init(ContainerSearchContext context) throws Exception {
		this.context = context;
	}

	@Override
	public boolean test(ContainerEntity t) {
		if(criteria.getVar().getIdent().equals(t.getIdent())) { 
			if(this.criteriaOperator == GOperator.GT || (this.criteriaOperator == GOperator.GTE) || (this.criteriaOperator == GOperator.LT) || (this.criteriaOperator == GOperator.LTE)) {
				// throw an exception 
				// shit we cannot can only log 
				logger.error("Exception with string ident not using equal or not eqaul your shit if flawed"); 
				context.logError("ident criteria is using an invalid operator that is not EQ or UQ it is " + this.criteriaOperator.name());
				return false;
				
			}
			if(this.criteriaOperator == GOperator.EQ) { 
				if(t.getIdent().equals(t.getIdent())) { 
					return true;
				}
			}
			if(this.criteriaOperator == GOperator.NQ) { 
				if(t.getIdent().equals(t.getIdent())) { 
					return false;
				}
			}
			return false;
		}
		if(!t.varExists(criteria.getVar().getIdent())) { 
			return false;
		}
		if(criteria.getVar().getType() == GNetEntityVarValueType.VALUE_NOW) {
			return testValueNow(t);
		}
		context.logError("Var Criteria value type not handled " + criteria.getVar().getType().name());
		return false;
		
	}
	
	private boolean testValueNow(ContainerEntity t) { 
		Object lastValue = t.getLastVarValue(criteria.getVar().getIdent());
		try {
			return ContainerHelper.testCriteria(criteria.getValue(), lastValue, criteriaOperator);
		} catch (Exception e) {
			context.logError(e.toString());
			return false;
		}
		
		
		
		
	}
	
	
	

}
