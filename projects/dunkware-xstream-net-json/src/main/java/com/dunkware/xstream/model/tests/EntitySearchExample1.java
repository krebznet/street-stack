package com.dunkware.xstream.model.tests;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.model.search.Condition;
import com.dunkware.xstream.model.search.ConditionNumeric;
import com.dunkware.xstream.model.search.ConditionNumericOperator;
import com.dunkware.xstream.model.search.ConditionType;
import com.dunkware.xstream.model.search.SessionEntityFilter;
import com.dunkware.xstream.model.search.SessionEntityFilterType;
import com.dunkware.xstream.model.search.SessionEntityFilterValue;
import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.model.search.SessionEntitySearchFilter;
import com.dunkware.xstream.model.search.SessionEntitySearchType;
import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.model.search.SessionEntityValueType;
import com.dunkware.xstream.model.spec.StreamSpecEntityField;

public class EntitySearchExample1 {

	public static void main(String[] args) {
		StreamSpecEntityField field1 = new StreamSpecEntityField();
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
		con.setNumeric(numericCond);
		valueFilter.setValue(value);
		valueFilter.setCondition(con);
		filter1.setType(SessionEntityFilterType.Value);
		filter1.setValue(valueFilter);
		
		SessionEntitySearch search = new SessionEntitySearch();
		SessionEntitySearchFilter searchFilter = new SessionEntitySearchFilter();
		List<SessionEntityFilter> filters = new ArrayList<SessionEntityFilter>();
		filters.add(filter1);
		searchFilter.setFilters(filters);
		search.setType(SessionEntitySearchType.Filter);
		search.setFilterSearch(searchFilter);
		search.setName("Test Search");
		
		SessionEntityScanner scanner = new SessionEntityScanner();
		scanner.setSearch(search);
		scanner.setStreamIdentifier("us_equity");
		ArrayList<String> vars = new  ArrayList<String>();
		vars.add("Last");
		//scanner.setVars(vars);
		
		
		try {
			String wow = DJson.serializePretty(scanner);
			System.out.println(wow);
			
			//SessionEntitySearch deserialized = (SessionEntitySearch)DJson.getObjectMapper().readValue(wow, SessionEntitySearch.class);
			//System.out.println(deserialized.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
