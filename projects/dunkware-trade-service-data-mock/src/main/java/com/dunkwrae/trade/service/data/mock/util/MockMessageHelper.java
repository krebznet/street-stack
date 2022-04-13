package com.dunkwrae.trade.service.data.mock.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.core.GDataType;
import com.dunkware.trade.service.stream.json.controller.model.StreamEntitySpec;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.xstream.model.enums.XScriptDataType;
import com.dunkware.xstream.model.script.StreamScript;
import com.dunkware.xstream.model.script.StreamScriptBuilder;
import com.dunkwrae.trade.service.data.mock.input.MockInputSignal;
import com.dunkwrae.trade.service.data.mock.input.MockInputVar;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSession;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionEntity;

public class MockMessageHelper {
	
	
	public static StreamSessionSpec createSessionspec(MockSession session) { 
		StreamSessionSpec spec = new StreamSessionSpec();
		spec.setDate(DDate.from(LocalDate.now(DTimeZone.toZoneId(session.getInput().getSchedule().getTimeZone()))));
		spec.setKafkaBrokers(session.getConfigService().getKafkaBrokers());
		spec.setSessionId(session.getSessionId());
		spec.setEntityCount(session.getEntities().size());
		spec.setStartTime(DTime.from(LocalTime.now(DTimeZone.toZoneId(session.getInput().getSchedule().getTimeZone()))));
		String sigtopic = "stream_" + session.getInput().getStreamIdentifier() + "_signals_" + DunkTime.format(spec.getDate().get(),DunkTime.YYYY_MM_DD);
		String snapshot = "stream_" + session.getInput().getStreamIdentifier() + "_snapshots_" + DunkTime.format(spec.getDate().get(),DunkTime.YYYY_MM_DD);
		spec.setKafkaSignalTopic(sigtopic);
		spec.setKafkaSnapshotTopic(snapshot);
		spec.setTimeZone(session.getInput().getSchedule().getTimeZone());
		List<StreamEntitySpec> entities = new ArrayList<StreamEntitySpec>();
		for (MockSessionEntity mockEnt : session.getEntities()) {
			StreamEntitySpec sp = new StreamEntitySpec();
			sp.setId(mockEnt.getId());
			sp.setIndentifier(mockEnt.getIdentifier());
			entities.add(sp);
		}
		spec.setStreamIdentifier(session.getInput().getStreamIdentifier());
		spec.setEntityCount(entities.size());
		spec.setStreamEntities(entities);
		spec.setStreamScript(createStreamScript(session));
		return spec;
		
	}
	
	public static StreamScript createStreamScript(MockSession session) { 
		StreamScriptBuilder builder = StreamScriptBuilder.newBuilder();
		for (MockInputVar iv : session.getInput().getVars()) {
			builder.addVar(iv.getIdentifier(),iv.getName(),iv.getId(),createDataType(iv.getDataType()));
		}
		for (MockInputSignal sf : session.getInput().getSignalFactories()) {
			builder.addSignalType(sf.getStreamSignalType().getIdentifier(),sf.getStreamSignalType().getName(),sf.getStreamSignalType().getId());
		}
		return builder.build();
	}
	
	public static XScriptDataType createDataType(GDataType gType) {
		if(gType ==  GDataType.STRING_TYPE) {
			return XScriptDataType.String;
		}
		if(gType ==  GDataType.BOOL_TYPE) {
			return XScriptDataType.Boolean;
		}
		if(gType ==  GDataType.LONG_TYPE) {
			return XScriptDataType.Long;
		}
		if(gType ==  GDataType.DOUBLE_TYPE) {
			return XScriptDataType.Double;
		}
		return XScriptDataType.Integer;
		
		
		
	}

}
