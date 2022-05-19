package com.dunkware.xstream.net.core.container.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.net.proto.netstream.GEntityVarCriteria;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerSearchException;

public class ContainerEntityMatcherPredicateBuilder {

	public static List<Predicate<ContainerEntity>> build(GEntityMatcher matcher, Container container)
			throws ContainerException, ContainerSearchException {
		List<Predicate<ContainerEntity>> preds = new ArrayList<Predicate<ContainerEntity>>();
		for (GEntityVarCriteria varCriteria : matcher.getVarCriteriasList()) {
			ContainerEntity entity = container.getEntity(varCriteria.getVar().getIdent());
			Object resolvedValue = entity.resolveCriteriaVar(varCriteria.getVar());
			String crtieraValue = varCriteria.getValue();

			if (varCriteria.getOperator() == GOperator.EQ) {

			}
			if (varCriteria.getOperator() == GOperator.GT) {

			}
			if (varCriteria.getOperator() == GOperator.GTE) {

			}
			if (varCriteria.getOperator() == GOperator.LT) {

			}
			if (varCriteria.getOperator() == GOperator.LTE) {

			}
			if (varCriteria.getOperator() == GOperator.NQ) {

			}
		}

		// maybe relative signal count
		return preds;
	}

}
