package com.dunkware.trade.net.data.server.stream.converters;

import org.bson.Document;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class MongoStreamConverter {

	
	public static Document snapshotToDocument(StreamEntitySnapshot snapshot, DTimeZone timeZone) { 
		return null;
	}
	
	public static Document signalToDocument(StreamEntitySignal signal, DTimeZone timeZone) { 
		return null;
	}
	
	
	public StreamEntitySignal documentToSignal(Document document) { 
		return null;
	}
	
	public StreamEntitySnapshot documentToSnapshot(Document document) { 
		return null;
	}
	
	
	
}
