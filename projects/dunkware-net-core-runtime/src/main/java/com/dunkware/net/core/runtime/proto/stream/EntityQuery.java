package com.dunkware.net.core.runtime.proto.stream;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.net.core.runtime.proto.core.CalendarRange;
import com.dunkware.net.proto.stream.GEntitySignalCountCriteria;

public class EntityQuery {
	
	private String streamId; 
	private List<String> entities = new ArrayList<String>();
	private List<EntityVarCriteria> varCriterias;
	private List<GEntitySignalCountCriteria> signalCountCriterias;
	private List<VarCompareCriteria> varCompareCriterias;
	private CalendarRange searchRange; 
		
	
}
