package com.dunkware.trade.service.stream.server.controller.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
import com.dunkware.net.proto.core.GDataType;
import com.dunkware.net.proto.stream.GEntitySignalSpec;
import com.dunkware.net.proto.stream.GStreamEntitySpec;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamScriptSpec;
import com.dunkware.net.proto.stream.GStreamSessionSpec;
import com.dunkware.net.proto.stream.GStreamSessionStart;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.net.proto.stream.GStreamVarSpec;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;

public class GStreamHelper {
	
	
	public static GStreamEvent buildSessionStartEvent(StreamSession session) throws Exception { 
		GStreamEvent.Builder builder = GStreamEvent.newBuilder();
		builder.setType(GStreamEventType.SessionStart);
		GStreamSessionStart.Builder startBuilder = GStreamSessionStart.newBuilder();
		startBuilder.setSession(buildSessionSpec(session));
		startBuilder.setTime(GProtoHelper.toTimeStamp(LocalDateTime.now(), session.getStream().getTimeZone()));
		builder.setSessionStart(startBuilder.build());
		return builder.build();
	}
	
	public static GStreamEvent buildSessionStopEvent(StreamSession session) throws Exception { 
		GStreamEvent.Builder builder = GStreamEvent.newBuilder();
		builder.setType(GStreamEventType.SessionStop);
		GStreamSessionStop.Builder startBuilder = GStreamSessionStop.newBuilder();
		startBuilder.setSession(buildSessionSpec(session));
		startBuilder.setTime(GProtoHelper.toTimeStamp(LocalDateTime.now(), session.getStream().getTimeZone()));
		builder.setSessionStop(startBuilder.build());
		return builder.build();
	}
	
	
	public static GStreamSessionSpec buildSessionSpec(StreamSession session) throws Exception { 
		GStreamSessionSpec.Builder builder = GStreamSessionSpec.newBuilder();
		for (TradeTickerSpec ticker : session.getTickerListSpec().getTickers()) {
			GStreamEntitySpec entSpec = GStreamEntitySpec.newBuilder().setId(ticker.getId()).setIdentifier(ticker.getSymbol()).build();
			builder.addEntities(entSpec);
		}
		//session.getEntity().getStream().gets
		//sesssion.get
		builder.setSessionId(session.getSessionId());
		builder.setScript(buildScriptSpec(session.getStream()));
		builder.setStream(session.getStream().getName());
		return builder.build();
	}
	
	public static GStreamScriptSpec buildScriptSpec(StreamController controller) { 
		GStreamScriptSpec.Builder builder = GStreamScriptSpec.newBuilder();
		XScriptProject project = controller.getScriptProject();
		builder.setVersion(controller.getCurrentVersion().getVersion());
		
		// Signal Specs
		
		List<GEntitySignalSpec> entitySignalSpecs = new ArrayList<GEntitySignalSpec>();
		for (SignalType signalType : project.getStreamSignals()) {
			entitySignalSpecs.add(GEntitySignalSpec.newBuilder().setId(signalType.getId()).setIdentifier(signalType.getName()).setLabel(signalType.getName()).build());
			
		}
		builder.addAllSignalTypes(entitySignalSpecs);
		
		// Variable Specs 
		List<GStreamVarSpec> varSpecs = new ArrayList<GStreamVarSpec>();
		for (VarType varType : project.getStreamVars()) {
			GStreamVarSpec.Builder vb = GStreamVarSpec.newBuilder();
			vb.setId(varType.getCode());
			vb.setIdentifier(varType.getName());
			if(varType.getType() == DataType.BO_OL) {
				vb.setDataType(GDataType.BOOL_TYPE);
			}
			if(varType.getType() == DataType.DUB) {
				vb.setDataType(GDataType.DOUBLE_TYPE);
			}
			if(varType.getType() == DataType.INT) {
				vb.setDataType(GDataType.INT_TYPE);
			}
			if(varType.getType() == DataType.STR) {
				vb.setDataType(GDataType.STRING_TYPE);
			}
			if(varType.getType() == DataType.LONG) {
				vb.setDataType(GDataType.LONG_TYPE);
			}
			varSpecs.add(vb.build());
			// add name
		}
		builder.addAllVariables(varSpecs);
		return builder.build();
	}
	
	public static GStreamSpec build(StreamController controller) throws Exception  { 
		GStreamSpec.Builder sb = GStreamSpec.newBuilder();
		sb.setIdentifier(controller.getSpec().getName());
		GStreamScriptSpec.Builder builder = GStreamScriptSpec.newBuilder();
		XScriptProject project = null;
		try {
			project = XscriptBundleHelper.loadProject(controller.getSpec().getBundle());
		} catch (Exception e) {
			throw new Exception("Exception loading XScript Project " + e.toString());
		}
		
		// Signal Specs
		
		List<GEntitySignalSpec> entitySignalSpecs = new ArrayList<GEntitySignalSpec>();
		for (SignalType signalType : project.getStreamSignals()) {
			entitySignalSpecs.add(GEntitySignalSpec.newBuilder().setId(signalType.getId()).setIdentifier(signalType.getName()).setLabel(signalType.getName()).build());
			
		}
		builder.addAllSignalTypes(entitySignalSpecs);
		
		// Variable Specs 
		List<GStreamVarSpec> varSpecs = new ArrayList<GStreamVarSpec>();
		for (VarType varType : project.getStreamVars()) {
			GStreamVarSpec.Builder vb = GStreamVarSpec.newBuilder();
			vb.setId(varType.getCode());
			vb.setIdentifier(varType.getName());
			if(varType.getType() == DataType.BO_OL) {
				vb.setDataType(GDataType.BOOL_TYPE);
			}
			if(varType.getType() == DataType.DUB) {
				vb.setDataType(GDataType.DOUBLE_TYPE);
			}
			if(varType.getType() == DataType.INT) {
				vb.setDataType(GDataType.INT_TYPE);
			}
			if(varType.getType() == DataType.STR) {
				vb.setDataType(GDataType.STRING_TYPE);
			}
			if(varType.getType() == DataType.LONG) {
				vb.setDataType(GDataType.LONG_TYPE);
			}
			varSpecs.add(vb.build());
			// add name
		}
		builder.addAllVariables(varSpecs);
		sb.setScript(builder.build());
		List<GStreamEntitySpec> entSpecs = new ArrayList<GStreamEntitySpec>();
		for (TradeTickerSpec ticker : controller.getTickerList().getTickers()) {
			entSpecs.add(GStreamEntitySpec.newBuilder().setId(ticker.getId()).setIdentifier(ticker.getSymbol()).setLabel(ticker.getSymbol()).build());
		}
		
		return sb.build();
		
		
	}
	

}
