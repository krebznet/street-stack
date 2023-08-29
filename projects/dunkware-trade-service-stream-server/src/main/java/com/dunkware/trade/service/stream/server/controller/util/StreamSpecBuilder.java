package com.dunkware.trade.service.stream.server.controller.util;


import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.entity.Entity;
import com.dunkware.xstream.model.spec.StreamSpec;
import com.dunkware.xstream.model.spec.StreamSpecEntityField;
import com.dunkware.xstream.model.spec.StreamSpecEntitySignal;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;

public class StreamSpecBuilder {
	
	
	public static StreamSpec build(StreamController stream) throws Exception  { 
		StreamSpec spec = new StreamSpec();
		spec.setVersion(stream.getCurrentVersion().getVersion());
		spec.setIdentifier(stream.getEntity().getName());
		spec.setEventBrokers(stream.getKafkaBrokers());
		spec.setTimeZone(stream.getTimeZone());
		for (TradeTickerSpec ticker : stream.getTickers()) {
			Entity entity = new Entity();
			entity.setId(ticker.getId());
			entity.setIdentifier(ticker.getSymbol());
			entity.setName(ticker.getName());
			spec.getEntities().add(entity);
			
		}
		for (VarType varType : stream.getScriptProject().getStreamVars()) {
			StreamSpecEntityField field = new StreamSpecEntityField();
			field.setId(varType.getCode());
			field.setIdentifier(varType.getName());
			field.setName(varType.getName());
			spec.getEntityFields().add(field);
		}
		
		for (SignalType type : stream.getScriptProject().getStreamSignals()) {
			StreamSpecEntitySignal sig = new StreamSpecEntitySignal();
			sig.setId(type.getId());
			sig.setIdentifier(type.getName());
			sig.setName(type.getName());
			spec.getEntitySignals().add(sig);
		}
		spec.setId(1);
		spec.setName("US Stocks");;
		return spec;
	}

}
