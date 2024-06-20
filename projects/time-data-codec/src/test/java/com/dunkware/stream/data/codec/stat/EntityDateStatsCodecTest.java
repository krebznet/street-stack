package com.dunkware.stream.data.codec.stat;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.dunkware.time.data.codec.stat.EntityStatsModelCodec;
import com.dunkware.time.data.model.stats.entity.EntityStatsModel;
import com.dunkware.time.data.model.stats.entity.EntityVarStatsModel;

import junit.framework.TestCase;


public class EntityDateStatsCodecTest extends TestCase {

	
	
	public void testOne() throws IOException  { 
		EntityStatsModel stats = new EntityStatsModel();
		stats.setStream(1);
		stats.setEntity(1);
		stats.setDate(LocalDate.of(2023, 1, 1));
		// var id 1 
		stats.getSigcounts().put(1, 100);
		stats.getSigcounts().put(2, 200);
		EntityVarStatsModel stats1 = new EntityVarStatsModel();
		stats1.setVar(1);
		stats1.getStats().put(1, 1.25);
		stats1.getStats().put(2, 2.25);
		stats1.getTimes().put(1, LocalTime.of(9, 30,1));
		stats.getVarstats().put(1, stats1);
		
		byte[] bytes = EntityStatsModelCodec.encode(stats);
		
		EntityStatsModel decoded = EntityStatsModelCodec.decode(bytes);
		
		assertEquals(stats.getStream(), decoded.getStream());
		assertEquals(decoded.getEntity(),stats.getEntity());
		assertEquals(decoded.getDate(),stats.getDate());
		
		assertEquals(decoded.getVarstats().keySet().size(),1);
		assertEquals(decoded.getVarstats().get(1).getStats().get(1),1.25);
		
		
	}
}
