package com.dunkware.trade.service.data.service.repository;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("data_service_stream_snapshot")
public class DataStreamSnapshotWriterEntity {
	
	private String kafkaClient; 
	private String kafkaGroup;

}
