package com.dunkware.trade.service.stream.server.controller.session.extensions;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprint;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprintService;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;

@AStreamSessionExt
public class SignalModelsProvider implements StreamSessionExtension {

	
	@Autowired
	private StreamBlueprintService bluePrintService;
	
	private StreamBlueprint bluePrint;

	private StreamSession session; 
	@Override
	public void sessionStarting(StreamSession session) {
		try {
			bluePrint = bluePrintService.getBlueprint(session.getStream().getName());
			// get the fuckin singlas yes 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// TODO Auto-generated method stub
		StreamSessionExtension.super.sessionStarting(session);
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.nodeStarting(node);
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.nodeStarted(node);
	} 
	
	
		
}
