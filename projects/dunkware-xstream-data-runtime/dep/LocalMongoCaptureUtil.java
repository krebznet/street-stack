package com.dunkware.xstream.data.capture;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dunkware.xstream.core.proto.capture.StreamCapture.Signal;
import com.dunkware.xstream.core.proto.capture.StreamCapture.Snapshot;
import com.dunkware.xstream.core.proto.capture.StreamCapture.Var;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class LocalMongoCaptureUtil {
	
	/**
	 * Builds a MongoDB Document from a Capture Snapshot Message
	 * 
	 * 
	 * 
	 * @param snapshot   Snapshot Message
	 * @param timeZoneId : the TimeZoneID of the stream
	 * @return BasicDBObject for Mongo Persistence
	 * 
	 * @throws IngestorException
	 */
	public static BasicDBObject buildSnapshot(Snapshot snapshot, ZoneId timeZoneId) throws Exception {
		BasicDBObject document = new BasicDBObject();
		document.put("entity", snapshot.getEntity());
		boolean handled = false;
		List<BasicDBObject> vars = new ArrayList<BasicDBObject>();
		for (Var var : snapshot.getVarsList()) {
			if (var.getPayloadCase() == Var.PayloadCase.STRINGVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getBooleanValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.DOUBLEVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getDoubleValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.INTEGERVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getIntegerValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.LONGVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getLongValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.STRINGVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getStringValue()));
				handled = true;
			}
			if (!handled) {
				throw new Exception("Var Type " + var.getPayloadCase().name() + " Not Handled In Build Snapshot MongoDB Object");
			}
		}
		// Add the var array
		document.put("vars", vars);
		// Parse The Timestamp:
		// This might not be the most effecient way to do it but is the only I could
		// figured out - dkrebs
		long ts = snapshot.getTimestamp();
		//LocalDateTime dt = Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos()).atZone(timeZoneId).toLocalDateTime();
		//Date date = Date.from(dt.atZone(timeZoneId).toInstant());
		document.put("timestamp", new Date(ts));
		return document;
	}

	/**
	 * Builds a MongDB Document from a captured Signal Message
	 * @param signal
	 * @param timeZoneId
	 * @return
	 * @throws IngestorException
	 */
	public static BasicDBObject buildSignal(Signal signal, ZoneId timeZoneId) throws Exception {
		BasicDBObject document = new BasicDBObject();
		document.put("id", signal.getId());
		document.put("entity", signal.getEntity());
		// TODO: We want to wrap all the variables in a var array
		// TODO: Create document vars array
		List<BasicDBObject> vars = new ArrayList<BasicDBObject>();
		for (Var var : signal.getVarsList()) {
			boolean handled = false;
			if (var.getPayloadCase() == Var.PayloadCase.BOOLEANVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getBooleanValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.DOUBLEVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getDoubleValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.INTEGERVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getIntegerValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.LONGVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getLongValue()));
				handled = true;
			}
			if (var.getPayloadCase() == Var.PayloadCase.STRINGVALUE) {
				vars.add(new BasicDBObject(String.valueOf(var.getId()), var.getStringValue()));
				handled = true;
			}
			if (!handled) {
				throw new Exception("Var Type " + var.getPayloadCase().name() + " Not Handled In Build Snapshot MongoDB Object");
			}
		}
		// Add the var array
		document.put("vars", vars);

		// Parse The Timestamp:
		// This might not be the most effecient way to do it but is the only I could
		// figured out - dkrebs
		long ts = signal.getTimestamp();
		//LocalDateTime dt = Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos()).atZone(timeZoneId).toLocalDateTime();
		//Date date = Date.from(dt.atZone(timeZoneId).toInstant());
		document.put("timestamp", new Date(ts));

		return document;
	}
	
	/**
	 * TODO: Create a 1 minute snapshot bucket document that stores the 60 snapshot messaages 
	 * in one document. 
	 * @param bucket
	 * @return
	 */
	public static BasicDBObject buildSnapshotBucket(MongoCaptureSnapshotBucket bucket, ZoneId zoneId) throws Exception { 
		BasicDBObject document = new BasicDBObject();
		// need to set the entity
		document.put("entity", bucket.getEntity());
		// need to set start and end date times
		document.put("startDateTime", new Date(bucket.getStartDateTime()));
		document.put("endDateTime", new Date(bucket.getEndDateTime()));
		// create a snapshots db list
		BasicDBList snapshots = new BasicDBList();
		// add snapshot objects to this list
		for (Snapshot snapshot : bucket.getSnapshots()) {
			BasicDBObject snapshotDocument = buildSnapshot(snapshot, zoneId);
			snapshots.add(snapshotDocument);
		}
		document.append("snapshots", snapshots);
		return document;
	}

}
