package com.dunkware.xstream.net.client.test;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.netstream.GNetEntity;
import com.dunkware.net.proto.netstream.GNetEntityCriteria;
import com.dunkware.net.proto.netstream.GNetEntityMatcher;
import com.dunkware.net.proto.netstream.GNetEntityVarCriteria;
import com.dunkware.net.proto.netstream.GNetEntityVarValue;
import com.dunkware.net.proto.netstream.GNetEntityVarValueType;
import com.dunkware.xstream.net.client.StreamClient;
import com.dunkware.xstream.net.client.StreamClientEntitySearch;
import com.dunkware.xstream.net.client.StreamClientEntitySearchCallBack;
import com.dunkware.xstream.net.client.StreamClientFactory;
import com.dunkware.xstream.net.client.StreamClientInput;
import com.dunkware.xstream.net.client.connector.StreamClientKafkaConnectorType;
import com.dunkware.xstream.net.client.core.actions.StreamClientEntitySearchImpl;
import com.squareup.okhttp.Callback;

public class StreamClientTest1 {
	
	public static void main(String[] args) {
		new StreamClientTest1();
		while(true) { 
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} //"SES:23" // DLogging.marker(prefix, id); 
	}
	
	
	private StreamClient client; 
//	private DExecutor executor; 
	public StreamClientTest1() {
	//	executor = new DExecutor(4);
		client = StreamClientFactory.create();
		StreamClientKafkaConnectorType type = new StreamClientKafkaConnectorType();
		type.setRequestURL("http://localhost:8090/container/client/request");
		try {
			StreamClientInput input = new StreamClientInput();
			input.setClientIdentifier("TestClient");
			input.setStream("us_equity");
			input.setConnectorType(type);
			input.setExecutor(new DExecutor(5));
			client.connect(input);
		} catch (Exception e) {
			System.err.println("Client Connect Exception " + e.toString());
			return;
		}
		
		
		GNetEntityVarValue value = GNetEntityVarValue.newBuilder().setType(GNetEntityVarValueType.VALUE_NOW).setIdent("AAPL").setId(9).build();
		GNetEntityVarCriteria varCrit = GNetEntityVarCriteria.newBuilder().setVar(value).setValue("AAPL").setOperator(GOperator.EQ).build();
		GNetEntityCriteria crit = GNetEntityCriteria.newBuilder().addVarCriterias(varCrit).build();
		GNetEntityMatcher matcher = GNetEntityMatcher.newBuilder().addVarCriterias(crit).build();
		try {
			client.entitySearch(matcher, "*", callback);
				
		} catch (Exception e) {
			System.err.println("exception calling entity search " + e.toString());
		}
		
	}
	
	 StreamClientEntitySearchCallBack callback = new StreamClientEntitySearchCallBack() {
			
			@Override
			public void onResponse(StreamClientEntitySearch search) {
				System.out.println("on response invoked");
			}
			
			@Override
			public void onException(StreamClientEntitySearch search) {
				System.out.println("on search exception " + search.getException());
			}
			
			@Override
			public void onComplete(final StreamClientEntitySearch search) {
				System.out.println("on search compelte!");
				for (GNetEntity entity : search.getResults()) {
					System.out.println(entity.getEntIdent() + ":" + entity.getVars());
				}
				// OKAY WE ARE GOOD HERE -- WE HAVE RESULTS THEN LETS DO SOMETHING WITH IT 
				// WTF IT WILL BLOCK ON 
				
			
			}
		}; 

}
