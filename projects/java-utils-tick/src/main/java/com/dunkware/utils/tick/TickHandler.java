package com.dunkware.utils.tick;

import com.dunkware.utils.tick.proto.TickProto.Tick;

public interface TickHandler {

	public void onTick(Tick tick);
}
