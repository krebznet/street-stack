package com.dunkware.trade.service.data.service.stream.session.writers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.bson.Document;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot;

public class DataStreamWriterHelper {

	public static AtomicInteger counter = new AtomicInteger(0);
	public static void main(String[] args) {
		Date fuck = new Date();
		String me = fuck.toInstant().toString();
	
	}
	
	
	public static Document buildSnapshotSerries(GEntitySnapshot snapshot, DTimeZone zone) throws Exception { 
		Document container = new Document();
		LocalDateTime timestamp = DunkTime.toLocalDateTime(snapshot.getTime(), zone);
		container.append("time", timestamp);
		container.append("ent", snapshot.getId());
		Document varDoc = new Document();
		
		for (GEntityVarSnapshot var : snapshot.getVarsList()) {
			
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.BOOLEANVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getBooleanValue());
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.DOUBLEVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getDoubleValue());
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.INTVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getIntValue());
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.LONGVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getLongValue());
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.STRINGVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getStringValue());
			}
		}
		List<Document> varDocs = buildVarSnapshots(snapshot.getVarsList());
		
		container.append("vars", varDocs);
		List<Integer> signals = new ArrayList<Integer>();
		for (GEntitySnapshot.GSnapshotSignal signal : snapshot.getSignalsList()) {
			signals.add(signal.getId());
		}
		container.append("signals", signals);
		if(counter.get() == 0) { 
			System.out.println(container.toJson());
		}
		counter.incrementAndGet();
		if(counter.get() == 100) { 
			System.out.println(container.toJson());
			counter.set(0);
		}
		return container;
		
	}
	
	/**
	 * Yup so just fix the time conversion 
	 * @param bucket
	 * @return
	 * @throws Exception
	 */
	public static Document buildSnapshotBucket(DataStreamSessionSnapshotWriterBucket bucket, DTimeZone timeZone) throws Exception { 
		Document container = new Document();
	
		container.append("start", bucket.getStart());
		LocalDateTime dt2 = DunkTime.toLocalDateTime(DunkTime.toDate(bucket.getStart()));
		LocalDateTime dt3 = DunkTime.toLocalDateTime(DunkTime.toDate(bucket.getStop()));
		
		
		container.append("stop", bucket.getStop());
		container.append("ident", bucket.getIdentifier());
		container.append("id", bucket.getId());
		List<Document> snapshotDocs = new ArrayList<Document>();
		for (GEntitySnapshot snapshot : bucket.getSnapshots()) {
			
			Document snapshotDoc = new Document();
		
			snapshotDoc.append("time",DProtoHelper.toLocalDateTime(snapshot.getTime(), timeZone));
			List<Document> snapshotVars = buildVarSnapshots(snapshot.getVarsList());
			snapshotDoc.append("vars", snapshotVars);
			
			
			
			List<Document> snapshotSignals = new ArrayList<Document>();
			
			
			for (GEntitySnapshot.GSnapshotSignal sig : snapshot.getSignalsList()) {
				Document sd = new Document();
				sd.append("id", sig.getId());
				sd.append("ident", sig.getIdentifier());
				snapshotSignals.add(sd);
			}
			snapshotDoc.append("signals", snapshotSignals);
			snapshotDoc.append("vars", snapshotVars);
			snapshotDocs.add(snapshotDoc);
		}
	
		container.append("snapshots", snapshotDocs);
		return container;
	}

	
	public static Document buildEntitySignal(GEntitySignal signal, DTimeZone timeZone) throws Exception { 
		Document container = new Document();
		LocalDateTime dt = DProtoHelper.toLocalDateTime(signal.getTime(), timeZone);
		container.append("time", dt);
		container.append("id", signal.getId());
		container.append("ident", signal.getIdentifier());
		container.append("eid", signal.getEntityIdentifier());
		container.append("eident", signal.getEntityId());
		container.append("vars", buildVarSnapshotDocument(signal.getEntityId(),signal.getVarsList()));
		return container;
	}
	public static Document buildSnapshot(GEntitySnapshot snapshot, DTimeZone timeZone) throws Exception {

		Document container = new Document();
		container.append("time", DProtoHelper.toLocalDateTime(snapshot.getTime(), timeZone));
		container.append("id", snapshot.getId());
		container.append("ident", snapshot.getIdentifier());
		container.append("vars", buildVarSnapshotDocument(snapshot.getId(),snapshot.getVarsList()));
		List<Document> signals = new ArrayList<Document>();
		for (GEntitySnapshot.GSnapshotSignal signal : snapshot.getSignalsList()) {
			Document sigdoc = new Document();
			sigdoc.append("id", signal.getId());
			sigdoc.append("ident", signal.getIdentifier());
			signals.add(sigdoc);
		}
		container.append("signals", signals);
		return container;
		
	}

	public static Document buildVarSnapshotDocument(int entityId, List<GEntityVarSnapshot> snapshots) throws Exception { 
		Document varDoc = new Document();
		varDoc.append("id", entityId);
		for (GEntityVarSnapshot var : snapshots) {
			boolean handled = false;
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.BOOLEANVALUE) {
				varDoc.append(String.valueOf(String.valueOf(var.getId())), var.getBooleanValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.DOUBLEVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getDoubleValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.INTVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getIntValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.LONGVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getLongValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.STRINGVALUE) {
				varDoc.append(String.valueOf(var.getId()), var.getStringValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.NULLVALUE) {
				handled = true;
				continue;
				
			}
			
			if (!handled) {
				throw new Exception(
						"Var Type " + var.getValueCase().name() + " Not Handled In Build Snapshot MongoDB Object");
			}
	
			
		}
		return varDoc;

		
	}
	public static List<Document> buildVarSnapshots(List<GEntityVarSnapshot> snapshots) throws Exception{
		List<Document> vars = new ArrayList<Document>();
		
		for (GEntityVarSnapshot var : snapshots) {
			Document varDoc = new Document();
			varDoc.append("id", var.getId());
			boolean handled = false;
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.BOOLEANVALUE) {
				varDoc.append("value", var.getBooleanValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.DOUBLEVALUE) {
				varDoc.append("value", var.getDoubleValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.INTVALUE) {
				varDoc.append("value", var.getIntValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.LONGVALUE) {
				varDoc.append("value", var.getLongValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.STRINGVALUE) {
				varDoc.append("value", var.getStringValue());
				handled = true;
			}
			if (var.getValueCase() == GEntityVarSnapshot.ValueCase.NULLVALUE) {
				varDoc.append("value", var.getNullValue());
				handled = true;
			}
			vars.add(varDoc);
			if (!handled) {
				throw new Exception(
						"Var Type " + var.getValueCase().name() + " Not Handled In Build Snapshot MongoDB Object");
			}
		}

		return vars;

	}

}
