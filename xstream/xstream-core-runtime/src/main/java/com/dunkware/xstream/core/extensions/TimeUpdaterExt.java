package com.dunkware.xstream.core.extensions;

import java.time.LocalDateTime;

import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.utils.tick.type.TimeTick;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;
//TODO: AVINASHANV-14 XStream Extension
/**
 * Annotated extensions are added to the life scyle of a XStream we don't add functionality 
 * into the core engine but as extensions, every extension has a extension type or a model
 * this annotation matches a model to the runtime class. lots of examples like publishing 
 * signal triggers to kafka, generating variable stats, consuming incoming data streams
 * that adapt to entities etc. 
 */
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
					LocalDateTime dateTime = LocalDateTime.now(stream.getTimeZoneId()); 
					Tick tick = TimeTick.encode(dateTime);
					stream.getTickRouter().consume(tick);
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
