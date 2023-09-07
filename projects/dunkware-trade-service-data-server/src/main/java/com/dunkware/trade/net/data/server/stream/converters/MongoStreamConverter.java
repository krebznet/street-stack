package com.dunkware.trade.net.data.server.stream.converters;

import java.time.ZonedDateTime;

import org.bson.Document;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class MongoStreamConverter {

	public static Document snapshotToDocument(StreamEntitySnapshot snapshot, DTimeZone timeZone) {
		return null;
	}

	public static Document signalToDocument(StreamEntitySignal signal, DTimeZone timeZone) {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(signal.getDateTime(), DTimeZone.toZoneId(timeZone));
		Document signalDoc = new Document().append("id", signal.getId()).append("ent", signal.getEntity())
				.append("dateTime", signal.getDateTime());
		Document vars = new Document();
		for (Integer varId : signal.getVars().keySet()) {
			vars.append(String.valueOf(varId), signal.getVars().get(varId));
		}
		signalDoc.append("vars", vars);
		return signalDoc;
		
	}

	public StreamEntitySignal documentToSignal(Document document) {
		return null;
	}

	public StreamEntitySnapshot documentToSnapshot(Document document) {
		return null;
	}

}
