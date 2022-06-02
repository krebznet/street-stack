package com.dunkware.trade.service.stream.server.mining;

public interface MiningSession {
	
	// JSON CONFIG
	
	// have a context for a wave 
	// StreamContainer -> start 
	//	-> StreamSession
	 
	// WaveController -> update on a stream client can it run locally with its data set
	/// Wave - in status -> update
		// get the ContainerEntity.match();
		// WaveExit -> wave getOpenValue("ad") - roc - if that -> exit
		// RUNS EVERY DAY AND MAINTAINS METRICSC
	//
	// Then when the wave looks good, you deploy it -> into the stream: 
	// persist in a mongo database somewhere -> 
	
	// Signal Analyzier -> signal trigge 
		// 1 Min: ROC: 
		// 3 Min: ROC
		// 4 Min: ROC
		// 5Min  Avg Variance Max Min

}
