package com.dunkware.xstream.core.extensions;

import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.reactor.TickReactorFactory;
import com.dunkware.common.tick.reactor.impl.TickReactor;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = TickReactorExtType.class)
public class TickReactorExt implements XStreamExtension, TickHandler {

	private XStream stream;
	private TickReactorExtType type;

	private TickReactor tickReactor;
	private int tps;
	
	private ReactorController controller;

	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.stream = stream;
		this.type = (TickReactorExtType) type;
		this.tps = this.type.getTps();
		try {
			tickReactor = TickReactorFactory.createReactor(this.type.getBlueprint());
		} catch (Exception e) {
			throw new XStreamException("Tick Reactor Factory Exception " + e.toString(), e);
		}

	}

	@Override
	public void preStart() throws XStreamException {
		// data tick types should be add there.
	}

	@Override
	public void start() throws XStreamException {
		try {
			for (TickReactorExtTypeDataTick datatick : type.getDataTicks()) {
				stream.getTickRouter().registerDataTick(datatick.getType(), datatick.getKey());
			}
			tickReactor.getTickRouter().addTickHandler(this);
			tickReactor.start();	
		} catch (Exception e) {
			throw new XStreamException("Tick Reactor Start Exception " + e.toString());
		}
		
		ReactorController controller = new ReactorController();
		controller.start();
		
	}

	
	@Override
	public void cancel() {
		controller.interrupt();
	}

	@Override
	public void preDispose() {
		controller.interrupt();
	}

	@Override
	public void dispose() {
		

	}

	@Override
	public void onTick(Tick tick) {
		this.stream.getTickRouter().streamTick(tick);
	}



	private class ReactorController extends Thread implements TickHandler {

		public void run() {
			//tickReactor.start();
			while(!interrupted()) { 
				try {
					if(stream.getExecutor().hasActiveTasks()) { 
						tickReactor.pause();
						stream.getExecutor().awaitWhileTasksRunning();
						tickReactor.resume();
					}
					
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}

		@Override
		public void onTick(Tick tick) {
			stream.getTickRouter().streamTick(tick);
		}
	}

}
