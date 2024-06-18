package com.dunkware.stream.data.codec.session.meta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import com.dunkware.stream.data.model.session.StreamSessionModel;

import junit.framework.TestCase;

public class SessionMetaEntityCodecTest extends TestCase {

	public static void main(String[] args) {
		
	}
	
	
	public void testMetaSessionCodec() throws Exception { 
		StreamSessionModel ent = new StreamSessionModel();
		ent.setStream(1);
		ent.setEntities(Arrays.asList(1,3,4,5));
		ent.setDate(LocalDate.of(2024, 3, 1));
		ent.setStart(LocalTime.of(9, 30,0));
		ent.setStop(LocalTime.of(10, 20,20));
		ent.setVars(Arrays.asList(1,2,3,4,5));
		ent.setSignals(Arrays.asList(1,2,3,4,5));
		ent.setStats(Arrays.asList(1,2,3,4,5));
		byte[] encoded = SessionModelCodec.encode(ent);
		StreamSessionModel dec = SessionModelCodec.decode(encoded);
		assertEquals(dec.getStream(), 1);
		assertEquals(dec.getEntities(),ent.getEntities());
		assertEquals(dec.getDate(), ent.getDate());
		assertEquals(dec.getStart(),ent.getStart());
		assertEquals(dec.getStop(),ent.getStop());
		assertEquals(dec.getVars(), ent.getVars());
		assertEquals(dec.getSignals(), ent.getSignals());
		assertEquals(dec.getStats(),ent.getStats());
		
	}
}
