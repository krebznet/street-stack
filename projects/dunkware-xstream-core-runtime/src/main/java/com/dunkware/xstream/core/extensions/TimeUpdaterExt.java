package com.dunkware.xstream.core.extensions;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = TimeUpdaterExtType.class)
public class TimeUpdaterExt implements XStreamExtension {

	private TimeUpdaterExtType type;
	private XStream stream; 
	
	private Updater updater;
	
	
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.type = (TimeUpdaterExtType)type;
		this.stream = stream; 
		
	}

	@Override
	public void preStart() throws XStreamException {
		
		
	}

	@Override
	public void start() throws XStreamException {
		updater = new Updater();
		updater.start();
	}

	@Override
	public void preDispose() {
		updater.interrupt();
	}

	@Override
	public void dispose() {
		
		// 
	}

	
	
	@Override
	public void cancel() {
		updater.interrupt();
	}



	private class Updater extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					//LocalDateTime dateTime = LocalDateTime.now(type.getTimeZone()); 
					//Tick tick = TimeTick.encode(dateTime.get());
					//stream.getTickRouter().streamTick(tick);
					Thread.sleep(1000);
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					
					e.printStackTrace();
				}
			}
		}
	}
	
}
