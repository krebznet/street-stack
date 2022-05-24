package com.dunkware.xstream.net.core.container.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.netstream.GNetEntity;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot.ValueCase;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerDataType;
import com.dunkware.xstream.net.core.container.ContainerEntity;
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
	
	public static boolean testCriteria(String criteria, Object value, GOperator operator) throws Exception {
		if (value instanceof Double) {
			Double doubleValue = (Double)value;
			Double criteriaDouble = null; 
			try {
				criteriaDouble = Double.valueOf(criteria);	
			} catch (Exception e) {
				throw new Exception("Criteria String " + criteria + " can't cast to Double " + e.toString());
			}
			switch(operator.getNumber()) { 
			case GOperator.EQ_VALUE:
				if(doubleValue.doubleValue() == criteriaDouble.doubleValue()) { 
					return true;
				}
				return false; 
			case GOperator.GT_VALUE:
				if(doubleValue.doubleValue() > criteriaDouble.doubleValue()) { 
					return true;
				}
				return false; 
			case GOperator.GTE_VALUE:
				if(doubleValue.doubleValue() > criteriaDouble.doubleValue() || doubleValue.doubleValue() == criteriaDouble.doubleValue()) { 
					return true;
				}
				return false; 
			case GOperator.LT_VALUE:
				if(doubleValue.doubleValue() < criteriaDouble.doubleValue()) { 
					return true;
				}
				return false; 
			case GOperator.LTE_VALUE:
				if(doubleValue.doubleValue() < criteriaDouble.doubleValue() || doubleValue.doubleValue() == criteriaDouble.doubleValue()) { 
					return true;
				}
				return false; 
			case GOperator.NQ_VALUE:
				if(doubleValue.doubleValue() != criteriaDouble.doubleValue()) { 
					return true;
				}
				return false; 
			}
		}
		if (value instanceof Long) {
			Long doubleValue = (Long)value;
			Long criteriaDouble = null; 
			try {
				criteriaDouble = Long.valueOf(criteria);	
			} catch (Exception e) {
				throw new Exception("Criteria String " + criteria + " can't cast to Long " + e.toString());
			}
			switch(operator.getNumber()) { 
			case GOperator.EQ_VALUE:
				if(doubleValue.longValue() == criteriaDouble.longValue()) { 
					return true;
				}
				return false; 
			case GOperator.GT_VALUE:
				if(doubleValue.longValue() > criteriaDouble.longValue()) { 
					return true;
				}
				return false; 
			case GOperator.GTE_VALUE:
				if(doubleValue.longValue() > criteriaDouble.longValue() || doubleValue.longValue() == criteriaDouble.longValue()) { 
					return true;
				}
				return false; 
			case GOperator.LT_VALUE:
				if(doubleValue.longValue() < criteriaDouble.longValue()) { 
					return true;
				}
				return false; 
			case GOperator.LTE_VALUE:
				if(doubleValue.longValue() < criteriaDouble.longValue() || doubleValue.longValue() == criteriaDouble.longValue()) { 
					return true;
				}
				return false; 
			case GOperator.NQ_VALUE:
				if(doubleValue.longValue() != criteriaDouble.longValue()) { 
					return true;
				}
				return false; 
			}
		}
		if (value instanceof Integer) {
			Integer doubleValue = (Integer)value;
			Integer criteriaDouble = null; 
			try {
				criteriaDouble = Integer.valueOf(criteria);	
			} catch (Exception e) {
				throw new Exception("Criteria String " + criteria + " can't cast to Long " + e.toString());
			}
			switch(operator.getNumber()) { 
			case GOperator.EQ_VALUE:
				if(doubleValue.intValue() == criteriaDouble.intValue()) { 
					return true;
				}
				return false; 
			case GOperator.GT_VALUE:
				if(doubleValue.intValue() > criteriaDouble.intValue()) { 
					return true;
				}
				return false; 
			case GOperator.GTE_VALUE:
				if(doubleValue.intValue() > criteriaDouble.intValue() || doubleValue.intValue() == criteriaDouble.intValue()) { 
					return true;
				}
				return false; 
			case GOperator.LT_VALUE:
				if(doubleValue.intValue() < criteriaDouble.intValue()) { 
					return true;
				}
				return false; 
			case GOperator.LTE_VALUE:
				if(doubleValue.intValue() < criteriaDouble.intValue() || doubleValue.intValue() == criteriaDouble.intValue()) { 
					return true;
				}
				return false; 
			case GOperator.NQ_VALUE:
				if(doubleValue.intValue() != criteriaDouble.intValue()) { 
					return true;
				}
				return false; 
			}
		}
		
		if (value instanceof Boolean) {
			Boolean bolValue = (Boolean)value;
			Boolean critValue = Boolean.valueOf(criteria);
			switch(operator.getNumber()) { 
			case GOperator.EQ_VALUE:
				return bolValue.booleanValue() == critValue.booleanValue();
			case GOperator.GT_VALUE:
				throw new Exception("Boolean value GT Comparison");
			case GOperator.GTE_VALUE:
				throw new Exception("Boolean value GTE Comparison");
			case GOperator.LT_VALUE:
				throw new Exception("Boolean value LT Comparison");
			case GOperator.LTE_VALUE:
				throw new Exception("Boolean value LTE Comparison");
			case GOperator.NQ_VALUE:
				return bolValue.booleanValue() != critValue.booleanValue();
			}
		}
		
		if (value instanceof String) {
			String stringValue = (String) value;
			switch(operator.getNumber()) { 
			case GOperator.EQ_VALUE:
				if(stringValue.equals(criteria)) { 
					return true;
				}
				return false;
			case GOperator.GT_VALUE:
				throw new Exception("String value GT Comparison");
			case GOperator.GTE_VALUE:
				throw new Exception("String value GTE Comparison");
			case GOperator.LT_VALUE:
				throw new Exception("String value LT Comparison");
			case GOperator.LTE_VALUE:
				throw new Exception("String value LTE Comparison");
			case GOperator.NQ_VALUE:
				if(criteria.equals(stringValue) == false) { 
					return true;
				}
				return false;
			}
		}
		throw new Exception("Testing target value type " + value.getClass().getName() + " not handled");
		
	}
	
	public static Object castStringToDateType(String input, ContainerDataType dataType) throws Exception { 
		if(dataType == ContainerDataType.DOUBLE) {
			return Double.valueOf(input);
		}
		if(dataType == ContainerDataType.BOOL) { 
			return Boolean.valueOf(input);
		}
		if(dataType == ContainerDataType.INT) { 
			return Integer.valueOf(input);
		}
		if(dataType == ContainerDataType.STRING) { 
			return input;
		}
		if(dataType == ContainerDataType.LONG) { 
			return Long.valueOf(input);
		}
		throw new Exception("Container Data Type not castable " + dataType.name());
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
	
	public static GNetEntity toNetEntity(ContainerEntity entity, String retValues) throws Exception {
		Map<String,Object> vars = null;
		String serializedVars = null;
		if(retValues.equals("*")) { 
			vars = entity.getLastVarValues();
		} else { 
			vars = new HashMap<String,Object>();
			String[] retArray = retValues.split(",");
			for (String var : retArray) {
				if(entity.varExists(var)) { 
					vars.put(var, entity.getLastVarValue(var));
				}
			}
		}
		try {
		   serializedVars = DJson.serialize(vars);
		} catch (Exception e) {
			throw new Exception("Json Serialization Failed " + e.toString());
		}
		return GNetEntity.newBuilder().setEntId(entity.getId()).setEntIdent(entity.getIdent()).setVars(serializedVars).build();
	}
}
