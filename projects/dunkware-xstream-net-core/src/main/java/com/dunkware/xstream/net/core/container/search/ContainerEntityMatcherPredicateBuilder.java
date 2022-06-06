package com.dunkware.xstream.net.core.container.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.proto.stream.GEntityCriteria;
import com.dunkware.net.proto.stream.GEntityCriteriaVarType;
import com.dunkware.net.proto.stream.GEntityMatcher;
import com.dunkware.net.proto.stream.GEntityVarCriteria;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search.entity.EntityVarCriteriaPredicate;

public class ContainerEntityMatcherPredicateBuilder {

	public static List<Predicate<ContainerEntity>> build(GEntityMatcher matcher, Container container)
			throws ContainerException, ContainerSearchException {
		List<Predicate<ContainerEntity>> preds = new ArrayList<Predicate<ContainerEntity>>();
		for (GEntityCriteria entityCriteria : matcher.getVarCriteriasList()) {
			if (entityCriteria.getVarCriteria() != null) {
				GEntityVarCriteria varCriteria = entityCriteria.getVarCriteria();
				if(varCriteria.getVar().getType() != GEntityCriteriaVarType.VALUE_NOW) { 
					throw new ContainerException("Var Value is not now ");
				}
				EntityVarCriteriaPredicate pred = new EntityVarCriteriaPredicate(varCriteria);
				preds.add(pred);
			}

		}
		

		// maybe relative signal count
		return preds;
	}

}
