package com.dunkware.xstream.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GEntitySnapshot.GSnapshotSignal;
import com.dunkware.net.proto.stream.GEntityVarSnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.comparator.VarNameComparator;
import com.dunkware.xstream.xScript.DataType;
import com.google.protobuf.Timestamp;

public class XStreamEventHelper {
	/**
	 * Builds a EntitySnapshot Stream Event
	 * 
	 * @param row
	 * @param timeZoneId
	 * @return
	 * @throws XStreamEventException
	 */
	public static GStreamEvent buildEntitySnapshotEvent(XStreamRow row, ZoneId timeZoneId,List<XStreamRowSignal> signals, LocalDateTime time)
			throws XStreamEventException {
		GEntitySnapshot snapshot = buildEntitySnapshot(row, timeZoneId,signals,time);
		
		return GStreamEvent.newBuilder().setEntitySnapshot(snapshot).setType(GStreamEventType.EntitySnapshot)
				.setStreamId(row.getStream().getInput().getIdentifier())
				.setSessionId(row.getStream().getInput().getSessionId()).build();
	}

	/**
	 * Builds EntitySignal Stream Event
	 * 
	 * @param row
	 * @param signal
	 * @return
	 * @throws XStreamEventException
	 */
	public static GStreamEvent buildEntitySignalEvent(XStreamRow row, XStreamRowSignal signal)
			throws XStreamEventException {
		GEntitySignal sig = buildEntitySignal(row, signal);
		return GStreamEvent.newBuilder().setEntitySignal(sig).setType(GStreamEventType.EntitySignal)
				.setStreamId(row.getStream().getInput().getIdentifier())
				.setSessionId(row.getStream().getInput().getSessionId()).build();
	}

	/**
	 * Builds an Entity Snapshot with Snapshot vars
	 * 
	 * @param row
	 * @param timeZoneId
	 * @return
	 * @throws XStreamEventException
	 */
	public static GEntitySnapshot buildEntitySnapshot(XStreamRow row, ZoneId timeZoneId, List<XStreamRowSignal> signals, LocalDateTime time)
			throws XStreamEventException {
		GEntitySnapshot.Builder builder = GEntitySnapshot.newBuilder();
		builder.setId(row.getIdentifier());
		builder.setIdentifier(row.getId());
		builder.setStreamId(row.getStream().getInput().getIdentifier());
		builder.setSessionId(row.getStream().getInput().getSessionId());
		builder.setTime(DProtoHelper.toTimeStamp(time,DTimeZone.NewYork));
		List<GEntityVarSnapshot> vars = buildVarSnapshots(row);
		if(signals == null) { 
			signals = new ArrayList<XStreamRowSignal>();
		}
		if (signals != null) {
			for (XStreamRowSignal signal : signals) {
				builder.addSignals(GSnapshotSignal.newBuilder().setId(signal.getSignalType().getId())
						.setIdentifier(signal.getSignalType().getName()).build());
			}
		}
		builder.addAllVars(vars);
		return builder.build();
	}

	/**
	 * Builds an entity signal
	 * 
	 * @param row
	 * @param signal
	 * @return
	 * @throws XStreamEventException
	 */
	public static GEntitySignal buildEntitySignal(XStreamRow row, XStreamRowSignal signal)
			throws XStreamEventException {
		
		GEntitySignal.Builder builder = GEntitySignal.newBuilder();
		builder.setId(signal.getSignalType().getId());
		builder.setIdentifier(signal.getSignalType().getName());
		builder.setEntityId(row.getIdentifier());
		builder.setEntityIdentifier(row.getId());
		builder.setSessionId(row.getStream().getInput().getSessionId());
		builder.setStreamId(row.getStream().getInput().getIdentifier());
		builder.setTime(DProtoHelper.toTimeStamp(signal.getLocalDateTime(),row.getStream().getInput().getTimeZone()));
		builder.addAllVars(buildVarSnapshots(row));
		return builder.build();
	}

	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		System.out.println(DunkTime.format(dt, DunkTime.YYYY_MM_DD_HH_MM_SS));
		Instant instant = Instant.now(); //can be LocalDateTime
		ZoneId systemZone = DTimeZone.toZoneId(DTimeZone.NewYork);
		ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);
		
		Instant instant2 = dt.toInstant(currentOffsetForMyZone);
		Timestamp ts = Timestamp.newBuilder().setSeconds(instant2.getEpochSecond()).setNanos(instant2.getNano()).build();
		System.out.println(ts.toString());
		
		Instant instant3 = Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos());
		LocalDateTime dt2 = LocalDateTime.ofInstant(instant3, currentOffsetForMyZone);
		System.out.println(DunkTime.format(dt2, DunkTime.YYYY_MM_DD_HH_MM_SS));
		
	}
	/**
	 * Converts a map of var values indexed by int id to a List of GEntityVarSnapsho
	 * 
	 * @param values
	 * @return
	 * @throws XStreamEventException
	 */
	public static List<GEntityVarSnapshot> buildVarSnapshots(XStreamRow row) throws XStreamEventException {

		List<GEntityVarSnapshot> snapshots = new ArrayList<GEntityVarSnapshot>();
		for (XStreamVar var : row.getVars()) {
			if(var.getSize() == 0) { 
				continue;
			}
			GEntityVarSnapshot.Builder builder = GEntityVarSnapshot.newBuilder();
			builder.setId(var.getVarType().getCode());
			builder.setIdentifier(var.getVarType().getName());
			builder.setDataType(var.getVarType().getType().getValue());
			DataType dataType = var.getVarType().getType();
			if (var.getSize() == 0) {
				builder.setNullValue("null");
				snapshots.add(builder.build());
				continue;
			}
			if (dataType == DataType.BO_OL) {
				builder.setBooleanValue((Boolean) var.getValue(0));
				snapshots.add(builder.build());
				continue;
			}
			if (dataType == DataType.STR) {
				builder.setStringValue((String) var.getValue(0));
				snapshots.add(builder.build());
				continue;
			}
			if (dataType == DataType.DUB) {
				builder.setDoubleValue((Double) var.getValue(0));
				snapshots.add(builder.build());
				continue;
			}
			if (dataType == DataType.LONG) {
				Object v = var.getValue(0);
				if (v instanceof Integer) {
					Integer intValue = (Integer) v;
					builder.setLongValue(intValue.longValue());
					snapshots.add(builder.build());
					continue;
				}
				if (v instanceof Long) {
					
					Long longValue = (Long) v;
					
					builder.setLongValue(longValue);
					snapshots.add(builder.build());
					continue;
				}

				throw new XStreamEventException("Long value var type is not long or int but is");
			}
			if (dataType == DataType.INT) {
				builder.setIntValue((Integer) var.getValue(0));
				snapshots.add(builder.build());
				continue;
			}
			throw new XStreamEventException("Building Var Snapshot List Object type " + var.getClass().getName()
					+ " not handled " + var.getClass().getName());
		}

		return snapshots;

	}

	/**
	 * Builds a snapshot string of vars
	 * 
	 * @param row
	 * @return
	 */
	public static String varSnapshotString(XStreamRow row) {
		List<XStreamVar> varList = new ArrayList<XStreamVar>();
		varList.addAll(row.getVars());
		Collections.sort(varList, new VarNameComparator());
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (XStreamVar xStreamVar : varList) {
			if (count > 0) {
				builder.append(",");
			}
			builder.append(xStreamVar.getVarType().getName());
			builder.append("=");
			if (xStreamVar.getSize() == 0) {
				builder.append("null");
			} else {
				builder.append(xStreamVar.getValue(0).toString());
			}
			count++;
		}
		return builder.toString();
	}

}
