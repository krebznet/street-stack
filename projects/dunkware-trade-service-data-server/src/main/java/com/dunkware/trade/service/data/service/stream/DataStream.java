package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.message.StreamMessageHandler;
import com.dunkware.trade.service.data.service.message.StreamMessageService;
import com.dunkware.trade.service.data.service.repository.DataServiceRepository;
import com.dunkware.trade.service.data.service.repository.DataStreamEntity;
import com.dunkware.trade.service.data.service.stream.writers.DataStreamSignalWriter;
import com.dunkware.trade.service.data.service.stream.writers.DataStreamSnapshotWriter;


public class DataStream implements StreamMessageHandler {
	
	@Autowired
	private RuntimeConfig configService; 

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private StreamMessageService messageService;

	@Autowired
	private DataServiceRepository dataRepo;
	
	private DataStreamSnapshotWriter snapshotWriter; 
	private DataStreamSignalWriter signalWriter;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DataStreamEntity streamEntity;
	
	public void load(DataStreamEntity entity) { 
		this.streamEntity = entity;
	}

	
	
	

	
	
	
	
}
