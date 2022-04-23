package com.dunkware.trade.service.stream.server.controller.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.trade.service.stream.json.controller.model.StreamEntitySpec;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.xstream.model.enums.XScriptDataType;
import com.dunkware.xstream.model.script.StreamScript;
import com.dunkware.xstream.model.script.StreamScriptVar;
import com.dunkware.xstream.model.script.StreamSignalType;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xproject.XScriptProject;

public class StreamSessionSpecBuilder {

	
	public static StreamSessionSpec build(StreamSession session) throws Exception { 
		StreamSessionSpec spec = new StreamSessionSpec();
		// entities
		TradeTickerListSpec tickerList = session.getStream().getSpec().get();
		List<StreamEntitySpec> entitySpecs = new ArrayList<StreamEntitySpec>();
		for (TradeTickerSpec ticker : tickerList.getTickers()) {
			StreamEntitySpec entSpec = new StreamEntitySpec();
			entSpec.setId(ticker.getId());
			entSpec.setIndentifier(ticker.getSymbol());
			
		}
		spec.setStreamEntities(entitySpecs);
		spec.setEntityCount(entitySpecs.size());
		spec.setSessionId(session.getSessionId());
		spec.setStartTime(session.getStream().getSpec().getStartTime());
		spec.setStopTime(session.getStream().getSpec().getStopTime());
		spec.setKafkaBrokers(session.getKafkaBrokers());
		spec.setKafkaSignalTopic(session.getKafkaSignalTopic());
		spec.setKafkaSnapshotTopic(session.getKafkaSnapshotTopic());
		spec.setStreamScript(buildStreamScript(session.getStream().getScriptProject(), session));
		spec.setDate(DDate.from(LocalDate.now(DTimeZone.toZoneId(session.getStream().getSpec().getTimeZone()))));
		spec.setStreamIdentifier(session.getStream().getName());
		spec.setTimeZone(session.getStream().getSpec().getTimeZone());
		spec.setSessionId(session.getSessionId());
		return spec;
	}
	
	
	public static StreamScript buildStreamScript(XScriptProject project, StreamSession session) throws Exception {
		
		StreamScript script = new StreamScript();
		script.setVersion(session.getStream().getCurrentVersion().getVersion());
		List<StreamSignalType> sigTypes = new ArrayList<StreamSignalType>();
		for (SignalType sigType : project.getStreamSignals()) {
			StreamSignalType type = new StreamSignalType();
			type.setId(sigType.getId());
			type.setIdentifier(type.getIdentifier());
			type.setName(type.getIdentifier());
			sigTypes.add(type);
		}
		script.setSignalTypes(sigTypes);
		// todo need version.
		List<StreamScriptVar> vars = new ArrayList<StreamScriptVar>();
		for (VarType varType : project.getStreamVars()) {
			StreamScriptVar var = new StreamScriptVar();
			var.setId(varType.getCode());
			var.setIdentifier(varType.getName());
			var.setName(varType.getName());
			var.setDataType(convertScriptDataType(varType.getType()));
			vars.add(var);
		}
		script.setVariables(vars);
		
		return script;
	}
	
	
	public static XScriptDataType convertScriptDataType(com.dunkware.xstream.xScript.DataType scriptType) throws Exception { 
		if(scriptType == com.dunkware.xstream.xScript.DataType.BO_OL) {
			return XScriptDataType.Boolean;
		}
		if(scriptType == com.dunkware.xstream.xScript.DataType.DUB) {
			return XScriptDataType.Double;
		}
		if(scriptType == com.dunkware.xstream.xScript.DataType.INT) { 
			return XScriptDataType.Integer;
			
		}
		if(scriptType == com.dunkware.xstream.xScript.DataType.LONG) { 
			return XScriptDataType.Long;
			
		}
		if(scriptType == com.dunkware.xstream.xScript.DataType.STR) { 
			return XScriptDataType.String;
			
		}
		
		throw new Exception("Script Data Type " + scriptType.name() + " not converted to Stream Script Data type");


		
	}
}
