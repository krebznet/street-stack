package com.dunkware.xstream.net.core.container.util;

import java.time.LocalDateTime;
import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot.ValueCase;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerDataType;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.ContainerEntitySnapshot;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerValueSet;
import com.dunkware.xstream.net.core.container.core.ContainerEntitySignalImpl;
import com.dunkware.xstream.net.core.container.core.ContainerEntitySnapshotImpl;
import com.dunkware.xstream.net.core.container.core.ContainerValueSetImpl;
import com.google.protobuf.Timestamp;

public class ContainerHelper {
	
	
	public static ContainerEntitySnapshot createSnapshot(GEntitySnapshot snapshot, Container container) throws ContainerException { 
		LocalDateTime dt = convertTimestamp(snapshot.getTime(), container.getTimeZone());
		ContainerValueSet vars = varsToValueSet(snapshot.getVarsList());
		return new ContainerEntitySnapshotImpl(dt,vars,snapshot.getIdentifier(),snapshot.getId());
	}
	
	public static ContainerEntitySignal createSignal(GEntitySignal signal, Container container) throws ContainerException {
		LocalDateTime dt = convertTimestamp(signal.getTime(), container.getTimeZone());
		ContainerValueSet vars = varsToValueSet(signal.getVarsList());
		ContainerEntitySignalImpl impl = new ContainerEntitySignalImpl(container, 
				signal.getId(),
				signal.getIdentifier(),
				signal.getEntityId(),
				signal.getEntityIdentifier(),
				dt,
				vars);
		return impl;
		
	}
	
	public static class TypeVarValue {
		public static Object value; 
		
	}
	
	
	public static LocalDateTime convertTimestamp(Timestamp timestamp, DTimeZone zone) { 
		return DProtoHelper.toLocalDateTime(timestamp, zone);
	}
	
	public static ContainerValueSet varsToValueSet(List<GEntityVarSnapshot> snapshots) throws ContainerException {
		ContainerValueSet set = new ContainerValueSetImpl();
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
			throw new ContainerException("Var Cache Event Helper type " + var.getValueCase().name() + " not handled");
			
		}
		return set;
	}
	
	public static Object getSnapshotVarValue(GEntityVarSnapshot snapshot) { 
		if(snapshot.getValueCase() == GEntityVarSnapshot.ValueCase.BOOLEANVALUE) { 
			return snapshot.getBooleanValue();
		}
		if(snapshot.getValueCase() == GEntityVarSnapshot.ValueCase.DOUBLEVALUE) { 
			return snapshot.getDoubleValue();
		}
		if(snapshot.getValueCase() == GEntityVarSnapshot.ValueCase.INTVALUE) { 
			return snapshot.getIntValue();
		}
		if(snapshot.getValueCase() == GEntityVarSnapshot.ValueCase.LONGVALUE) { 
			return snapshot.getLongValue();
		}
		if(snapshot.getValueCase() == GEntityVarSnapshot.ValueCase.STRINGVALUE) { 
			return snapshot.getStringValue();
		}
		if(snapshot.getValueCase() == GEntityVarSnapshot.ValueCase.NULLVALUE) { 
			return null;
		}
		return null;
	}
	
	public static ContainerDataType getDataType(Object value) throws ContainerException { 
		if (value instanceof Double) {
			return ContainerDataType.DOUBLE;
		}
		if (value instanceof Long) {
			return ContainerDataType.LONG;
		}
		if (value instanceof Integer) {
			return ContainerDataType.INT;
		}
		if (value instanceof String) {
			return ContainerDataType.STRING;
		}
		if (value instanceof Boolean) {
			return ContainerDataType.BOOL;
		}
		throw new ContainerException("Object type " + value.getClass().getName() + " is not matching COntainer data type");
		
	}
}
