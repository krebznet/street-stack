package com.dunkwrae.trade.service.data.mock.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySignalSpec;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot;
import com.dunkware.net.proto.stream.GStreamEntitySpec;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamScriptSpec;
import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.net.proto.stream.GStreamVarSpec;
import com.dunkwrae.trade.service.data.mock.input.MockInput;
import com.dunkwrae.trade.service.data.mock.stream.MockStream;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamEntity;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamSignalType;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamVar;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSession;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionEntity;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionSignal;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionVar;
import com.google.protobuf.Timestamp;

public class MockProtoUtil {

	
	public static GStreamScriptSpec createScriptSpec(MockStream stream) { 
		GStreamScriptSpec.Builder builder = GStreamScriptSpec.newBuilder();
		MockInput input = stream.getInput();
		builder.setStreamIdentifier(input.getStreamIdentifier());
		for (MockStreamVar var : stream.getInput().getStreamVars()) {
			builder.addVariables(GStreamVarSpec.newBuilder().setDataType(var.getDataType())
					.setIdentifier(var.getIdentifier()).setId(var.getId()).build());
		}
		for (MockStreamSignalType sigType : input.getStreamSignalTypes()) {
			builder.addSignalTypes(GEntitySignalSpec.newBuilder()
					.setId(sigType.getId()).setIdentifier(sigType.getIdentifier())
					.setLabel(sigType.getName()).build());
		}
		builder.setVersion(stream.getInput().getScriptVersion());
		return builder.build();
	}
	
	public static GStreamSpec createStreamSpec(MockStream stream) { 
		GStreamSpec.Builder builder = GStreamSpec.newBuilder();
		MockInput input = stream.getInput();
		builder.setIdentifier(input.getStreamIdentifier());
		builder.setScript(createScriptSpec(stream));
		for (MockStreamEntity ent : input.getStreamEntities()) {
			builder.addEntities(createEntitySpec(ent));
		}
		return builder.build();
		
	}
	
	public static GStreamEntitySpec createEntitySpec(MockStreamEntity ent) { 
		return GStreamEntitySpec.newBuilder().setId(ent.getId())
				.setIdentifier(ent.getIdentifier())
				.setLabel(ent.getName()).build();
	}
	
	
	public static GStreamEvent entitySnapshotEvent(MockSessionEntity entity, MockSession stream) { 
		GStreamEvent.Builder eventBuilder = GStreamEvent.newBuilder();
		eventBuilder.setType(GStreamEventType.EntitySnapshot);
		// convert to time; 
		GEntitySnapshot.Builder snapBuilder = GEntitySnapshot.newBuilder();
		
		Timestamp timestamp = DProtoHelper.toTimeStamp(LocalDateTime.of(stream.getDate(), stream.getTime()),stream.getInput().getSchedule().getTimeZone());
		
		GEntitySnapshot snap =  snapBuilder.setTime(timestamp).setId(entity.getId()).setIdentifier(entity.getStreamEntity().getIdentifier()).addAllVars(createVarSnapshots(entity)).build();
		
		eventBuilder.setEntitySnapshot(snap);
		return eventBuilder.build();
		
	}
	
	public static List<GEntityVarSnapshot> createVarSnapshots(MockSessionEntity entity) { 
		List<GEntityVarSnapshot> snaps = new ArrayList<GEntityVarSnapshot>();
		for (MockSessionVar var : entity.getVars()) {
			GEntityVarSnapshot.Builder varBuilder = GEntityVarSnapshot.newBuilder();
			varBuilder.setIdentifier(var.getIdentifier());
			varBuilder.setId(var.getId()).setDataType(var.getInputVar().getDataType().getNumber());
			if(var.getValue() != null) { 
				if (var.getValue()instanceof Integer) {
					Integer intValue = (Integer) var.getValue();
					varBuilder.setIntValue(intValue);
					snaps.add(varBuilder.build());
					continue;
				}
				if (var.getValue()instanceof Double) {
					Double intValue = (Double) var.getValue();
					varBuilder.setDoubleValue(intValue);
					snaps.add(varBuilder.build());
					continue;
				}
				if (var.getValue()instanceof String) {
					String intValue = (String) var.getValue();
					varBuilder.setStringValue(intValue);
					snaps.add(varBuilder.build());
					continue;
				}
				if (var.getValue()instanceof Long) {
					Long intValue = (Long) var.getValue();
					varBuilder.setLongValue(intValue);
					snaps.add(varBuilder.build());
					continue;
				}
			}
			
			
		}
		return snaps;
	}
	
	public static GStreamEvent entitySignalEvent(MockSessionEntity entity, MockSessionSignal signal, MockSession stream) { 
		GStreamEvent.Builder eventBuilder = GStreamEvent.newBuilder();
		eventBuilder.setType(GStreamEventType.EntitySignal);
		GEntitySignal.Builder sigbuilder = GEntitySignal.newBuilder();
		sigbuilder.setId(signal.getType().getId());
		sigbuilder.setIdentifier(signal.getType().getIdentifier());
		sigbuilder.setEntityId(entity.getId());
		sigbuilder.setEntityIdentifier(entity.getIdentifier());
		Timestamp timestamp = DProtoHelper.toTimeStamp(LocalDateTime.of(stream.getDate(), stream.getTime()),stream.getInput().getSchedule().getTimeZone());
		sigbuilder.setTime(timestamp);
		sigbuilder.addAllVars(createVarSnapshots(entity));
		eventBuilder.setEntitySignal(sigbuilder.build());
		return eventBuilder.build();
		
	}
}
