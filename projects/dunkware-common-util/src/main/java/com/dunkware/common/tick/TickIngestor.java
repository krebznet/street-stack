package com.dunkware.common.tick;

import com.dunkware.common.tick.proto.TickProto.Tick;

public interface TickIngestor {

	public void ingest(Tick tick);
	
}


