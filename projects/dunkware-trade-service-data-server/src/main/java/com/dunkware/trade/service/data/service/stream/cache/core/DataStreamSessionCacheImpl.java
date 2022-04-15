package com.dunkware.trade.service.data.service.stream.cache.core;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.data.cache.CacheExtensionType;
import com.dunkware.xstream.data.cache.CacheStreamInput;
import com.dunkware.xstream.data.cache.ext.CacheKafkaSignalConsumerExtType;
import com.dunkware.xstream.data.cache.ext.CacheKafkaSnapshotConsumerExtType;

public class DataStreamSessionCacheImpl {
	
	
	// Create CacheInput
	/*
	 * List<CacheExtensionType> extTypes = new ArrayList<CacheExtensionType>();
	 * CacheKafkaSignalConsumerExtType sigType = new
	 * CacheKafkaSignalConsumerExtType();
	 * sigType.setKafkaBrokers(spec.getKafkaBrokers());
	 * sigType.setKafkaTopic(spec.getKafkaSignalTopic());
	 * sigType.setKafkaConsumerId("signal_" + spec.getSessionId());
	 * sigType.setTimeZone(spec.getTimeZone()); extTypes.add(sigType);
	 * CacheKafkaSnapshotConsumerExtType snapType = new
	 * CacheKafkaSnapshotConsumerExtType();
	 * snapType.setKafkaBrokers(spec.getKafkaBrokers());
	 * snapType.setKafkaConsumerId("snapshot_" + spec.getSessionId());
	 * snapType.setKafkaTopic(spec.getKafkaSnapshotTopic());
	 * snapType.setTimeZone(spec.getTimeZone());
	 * extTypes.add(snapType);
		CacheStreamInput input = new CacheStreamInput(extTypes,configService.getExecutor(),spec.getTimeZone(),spec.getSessionId());	
		
	 */

}
