package com.dunkware.xstream.net.core.container.search2.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search2.value.EntityValue;
import com.dunkware.xstream.net.core.container.search2.value.EntityValueAggHistorical;
import com.dunkware.xstream.net.core.container.search2.value.EntityValueAggSession;
import com.dunkware.xstream.net.core.container.search2.value.EntityValueCurrentVar;
import com.dunkware.xstream.net.core.container.search2.value.EntityValueSignalCountHistorical;
import com.dunkware.xstream.net.core.container.search2.value.EntityValueSignalCountSession;
import com.dunkware.xstream.net.model.search.EntityFieldAggHistorical;
import com.dunkware.xstream.net.model.search.EntitySignalCount;
import com.dunkware.xstream.net.model.search.EntitySignalCountType;
import com.dunkware.xstream.net.model.search.SessionEntityFilter;
import com.dunkware.xstream.net.model.search.SessionEntityFilterType;
import com.dunkware.xstream.net.model.search.SessionEntityFilterValue;
import com.dunkware.xstream.net.model.search.SessionEntitySearch;
import com.dunkware.xstream.net.model.search.SessionEntitySearchFilter;
import com.dunkware.xstream.net.model.search.SessionEntitySearchType;
import com.dunkware.xstream.net.model.search.SessionEntityValue;
import com.dunkware.xstream.net.model.search.SessionEntityValueType;

public class EntityFilterPredicateBuilder {
	
	public static List<Predicate<ContainerEntity>> build(SessionEntitySearch search, Container container) throws ContainerSearchException { 
		if(search.getType() == SessionEntitySearchType.Filter) { 
			return buildFilterSearch(search.getFiter(), container);
		}
		throw new ContainerSearchException("Session Entity Search Type " + search.getType().name() + " Not supported");
	}
	
	public static List<Predicate<ContainerEntity>> buildFilterSearch(SessionEntitySearchFilter search, Container container) throws ContainerSearchException { 
		List<Predicate<ContainerEntity>> predicates = new ArrayList<Predicate<ContainerEntity>>();

		for (SessionEntityFilter filter : search.getFilters()) {
			if(filter.getType() == SessionEntityFilterType.SignalCountSession) { 
				// oh this will be lots of fun. 
			}
			if(filter.getType() == SessionEntityFilterType.Value) { 
				SessionEntityFilterValue value = filter.getValue();
				if(value.getValue().getType() == SessionEntityValueType.CurrentValue) { 
					EntityVarCurrentFilter currentValue = new EntityVarCurrentFilter();
					currentValue.init(value, container);
				}
			}
			
			if(filter.getType() == SessionEntityFilterType.SiganCountHistorical) { 
				// check how many days of historical data we have somewhere 
			}
			if(filter.getType() == SessionEntityFilterType.ValueCompare) { 
				// 
			}
		}
		
		return predicates;
	}
	
	
	public static EntityValue createEntityValue(SessionEntityValue value, Container container) throws ContainerSearchException { 
		if(value.getType() == SessionEntityValueType.CurrentValue) { 
			EntityValueCurrentVar currentVar = new EntityValueCurrentVar();
			currentVar.init(value, container);
			return currentVar;
		}
		if(value.getType() == SessionEntityValueType.AggSession) { 
			EntityValueAggSession aggSession = new EntityValueAggSession();
			aggSession.init(value, container);
			return aggSession;
		}
		if(value.getType() == SessionEntityValueType.AggHistorical) { 
			EntityValueAggHistorical aggHistorical = new EntityValueAggHistorical(); 
			aggHistorical.init(value, container);
			return aggHistorical;
		}
		if(value.getType() == SessionEntityValueType.SignalCountSession) { 
			EntityValueSignalCountSession session = new EntityValueSignalCountSession();
			session.init(value, container);
			return session;
		}
		if(value.getType() == SessionEntityValueType.SignalCountHistorical) { 
			EntityValueSignalCountHistorical historical = new EntityValueSignalCountHistorical();
			historical.init(value, container);
			return historical;
		}
		throw new ContainerSearchException("Container Entity Value Not Handled for Type " + value.getType());
		

	}

}
