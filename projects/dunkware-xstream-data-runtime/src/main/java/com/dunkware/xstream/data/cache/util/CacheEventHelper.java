package com.dunkware.xstream.data.cache.util;

import java.util.List;

import com.dunkware.net.proto.stream.GEntityVarSnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot.ValueCase;
import com.dunkware.xstream.data.cache.CacheException;
import com.dunkware.xstream.data.cache.CacheValueSet;
import com.dunkware.xstream.data.cache.core.CacheValueSetImpl;

public class CacheEventHelper {
	
	public static CacheValueSet varsToValueSet(List<GEntityVarSnapshot> snapshots) throws CacheException {
		CacheValueSet set = new CacheValueSetImpl();
		for (GEntityVarSnapshot var : snapshots) {
			if(var.getValueCase() == ValueCase.NULLVALUE) { 
				set.setValue(var.getIdentifier(), "nullvalue");
				continue;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.BOOLEANVALUE) {
				set.setValue(var.getIdentifier(), var.getBooleanValue());
				continue;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.DOUBLEVALUE) {
				set.setValue(var.getIdentifier(), var.getDoubleValue());
				continue;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.INTVALUE) {
				set.setValue(var.getIdentifier(), var.getIntValue());
				continue;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.LONGVALUE) {
				set.setValue(var.getIdentifier(), var.getLongValue());
				continue;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.STRINGVALUE) {
				set.setValue(var.getIdentifier(), var.getStringValue());
				continue;
			}
			throw new CacheException("Var Cache Event Helper type " + var.getValueCase().name() + " not handled");
			
		}
		return set;
	}
}
