package com.dunkware.common.tick;

import com.dunkware.common.tick.proto.TickProto.Tick;

public interface TickHandler {

	public void onTick(Tick tick);
}
