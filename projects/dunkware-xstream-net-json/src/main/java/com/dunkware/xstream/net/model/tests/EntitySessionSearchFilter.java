package com.dunkware.xstream.net.model.tests;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.net.model.search.Condition;
import com.dunkware.xstream.net.model.search.ConditionNumeric;
import com.dunkware.xstream.net.model.search.ConditionNumericOperator;
import com.dunkware.xstream.net.model.search.ConditionType;
import com.dunkware.xstream.net.model.search.SessionEntityFilter;
import com.dunkware.xstream.net.model.search.SessionEntityFilterType;
import com.dunkware.xstream.net.model.search.SessionEntityFilterValue;
import com.dunkware.xstream.net.model.search.SessionEntitySearch;
import com.dunkware.xstream.net.model.search.SessionEntitySearchFilter;
import com.dunkware.xstream.net.model.search.SessionEntitySearchType;
import com.dunkware.xstream.net.model.search.SessionEntityValue;
import com.dunkware.xstream.net.model.search.SessionEntityValueType;
import com.dunkware.xstream.net.model.spec.EntityField;

public class EntitySessionSearchFilter {

	public static void main(String[] args) {
		EntityField field1 = new EntityField();
		field1.setId(1);
		field1.setIdentifier("VolCount30ec");
		field1.setName("Vol Count 30 Sec");
		
		SessionEntityValue value = new SessionEntityValue();
		value.setType(SessionEntityValueType.CurrentValue);
		value.setField(field1);
		SessionEntityFilter filter1 = new SessionEntityFilter();
		filter1.setEnabled(true);
		filter1.setLabel("test");
		
		SessionEntityFilterValue valueFilter = new SessionEntityFilterValue();
		valueFilter.setValue(value);
		Condition con = new Condition();
		con.setNumeric(null);
		con.setType(ConditionType.Numerical);
		ConditionNumeric numericCond = new ConditionNumeric();
		numericCond.setOperator(ConditionNumericOperator.GreaterThan);
		numericCond.setValue(30.0);
		valueFilter.setValue(value);
		valueFilter.setCondition(con);
		SessionEntityFilter fuck = new SessionEntityFilter();
		fuck.setType(SessionEntityFilterType.Value);
		fuck.setValue(valueFilter);
		SessionEntityFilter filter = new SessionEntityFilter();
		
		SessionEntitySearch search = new SessionEntitySearch();
		SessionEntitySearchFilter searchFilter = new SessionEntitySearchFilter();
		
		List<SessionEntityFilter> filters = new ArrayList<SessionEntityFilter>();
		filters.add(fuck);
		searchFilter.setFilters(filters);
		search.setType(SessionEntitySearchType.Filter);
		search.setFiter(searchFilter);
		search.setName("Test Search");
		try {
			String wow = DJson.serializePretty(search);
			System.out.println(wow);
			
			SessionEntitySearch deserialized = (SessionEntitySearch)DJson.getObjectMapper().readValue(wow, SessionEntitySearch.class);
			System.out.println(deserialized.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		searchFilter.setFilters(filters);
		
		
		
		
	}
}
