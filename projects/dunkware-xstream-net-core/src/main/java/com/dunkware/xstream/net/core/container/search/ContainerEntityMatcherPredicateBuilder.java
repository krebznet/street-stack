package com.dunkware.xstream.net.core.container.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.proto.netstream.GNetEntityCriteria;
import com.dunkware.net.proto.netstream.GNetEntityMatcher;
import com.dunkware.net.proto.netstream.GNetEntityVarCriteria;
import com.dunkware.net.proto.netstream.GNetEntityVarValueType;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerSearchException;

public class ContainerEntityMatcherPredicateBuilder {

	public static List<Predicate<ContainerEntity>> build(GNetEntityMatcher matcher, Container container)
			throws ContainerException, ContainerSearchException {
		List<Predicate<ContainerEntity>> preds = new ArrayList<Predicate<ContainerEntity>>();
		for (GNetEntityCriteria entityCriteria : matcher.getVarCriteriasList()) {
			// okay fucked up here but whatever its for angular
			for (GNetEntityVarCriteria varCriteria : entityCriteria.getVarCriteriasList()) {
				if (varCriteria.getVar().getType() == GNetEntityVarValueType.VALUE_NOW) {

				}

				if (varCriteria.getVar().getType() == GNetEntityVarValueType.RANGE_RELATIVE) {

				}
			}
		}

		// maybe relative signal count
		return preds;
	}

}
